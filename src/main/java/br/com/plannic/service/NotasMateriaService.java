package br.com.plannic.service;

import br.com.plannic.dto.*;
import br.com.plannic.model.Agendamento;
import br.com.plannic.model.Materia;
import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import br.com.plannic.repository.AgendamentoRepository;
import br.com.plannic.repository.MateriaRepository;
import br.com.plannic.repository.NotasMateriaRepository;
import br.com.plannic.repository.UsuarioRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAccessor;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;

@Service
@Transactional
public class NotasMateriaService {

    private NotasMateriaRepository repository;
    private UsuarioRepository usuarioRepository;
    private final MateriaRepository materiaRepository;
    private final MateriaRepository materiaRepository1;
    private final AgendamentoRepository agendamentoRepository;


    private static Logger logger = Logger.getLogger(NotasMateriaService.class);

    public NotasMateriaService(NotasMateriaRepository repository, MateriaRepository materiaRepository, MateriaRepository materiaRepository1, UsuarioRepository usuarioRepository, MateriaRepository materiaRepository11, AgendamentoRepository agendamentoRepository) {
        this.repository = repository;
        this.materiaRepository = materiaRepository;
        this.usuarioRepository = usuarioRepository;
        this.materiaRepository1 = materiaRepository1;
        this.agendamentoRepository = agendamentoRepository;
    }


    public List<NotasMateria> getAll() {
        ModelMapper mapper = new ModelMapper();
        List<NotasMateria> notasMaterias = repository.findAll();

        if (!notasMaterias.isEmpty()) {
            logger.info("Notas das materias recuperadas");
            return  notasMaterias
                    .stream()
                    .map(notasMateria -> mapper.map(notasMateria, NotasMateria.class))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList();
    }

    public void save(NotasMateria notasMateria) {
        ModelMapper mapper = new ModelMapper();
        var notaMateriaSalva = repository.save(mapper.map(notasMateria, NotasMateria.class));
        MDC.put("user_id", notaMateriaSalva.getIdUsuario());
        logger.info("Nota da materia salva");

    }


    public boolean update(NotasMateria notasMateria) {
        Optional<NotasMateria> notasMaterias = Optional.ofNullable(this.repository.findById(notasMateria.getIdMateria()));

        if (notasMaterias.isPresent()) {
            logger.info("Nota da materia atualizado");
            ModelMapper mapper = new ModelMapper();
            repository.save(mapper.map(notasMateria, NotasMateria.class));
            return true;
        }

        return false;
    }


    public boolean delete(int id) {
        Optional<NotasMateria> notasMaterias = Optional.ofNullable(this.repository.findById(id));

        if (notasMaterias.isPresent()) {
            logger.info("Notas da materia deletada");
            this.repository.deleteById(id);
            return true;
        }

        return false;
    }

    public List<NotasMateria> buscaNotavsData(Integer idusuario,Integer idmateria){
        List<NotasMateria> notasMaterias = repository.findAll(Sort.by("dataNota").descending());
        List<NotasMateria> filtered =
                notasMaterias.stream()
                        .filter(t -> t.getIdUsuario()== idusuario && t.getIdMateria()== idmateria)
                        .collect(Collectors.toList());
        return  filtered;
    }

    public List<NotasMateriaDTO> buscaMaior8(Integer idusuario) {

        Usuario user = usuarioRepository.findByIdUsuario(idusuario);

        List<NotasMateriaDTO> list =  new ArrayList<>();

        user.getNotasMateria().forEach(
                nm ->
                        list.add(
                                new NotasMateriaDTO(
                                        nm.getNotaMateria(),
                                        user.getMaterias().stream().filter(mat-> mat.getIdMateria() == nm.getIdMateria()).findFirst().orElse(new Materia("")).getNomeMateria() )

                        ));

        Map<String, Double> groupByMateria =
                list.stream().collect(Collectors.groupingBy(NotasMateriaDTO::getNomeMateria,Collectors.averagingDouble(NotasMateriaDTO::getNotaMateria)));

        List<NotasMateriaDTO> NotasMateriaDTO = new ArrayList<>();

        groupByMateria.entrySet().stream()
                .filter(mat-> mat.getValue()>=8)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .forEach(
                (k,v) -> NotasMateriaDTO.add(new NotasMateriaDTO(v,k))
        );
        return NotasMateriaDTO;


    }

    public List<NotasMateriaDTO> buscaMenor4(Integer idusuario) {

        Usuario user = usuarioRepository.findByIdUsuario(idusuario);

        List<NotasMateriaDTO> list =  new ArrayList<>();

        user.getNotasMateria().forEach(
                nm ->
                        list.add(
                                new NotasMateriaDTO(
                                        nm.getNotaMateria(),
                                        user.getMaterias().stream().filter(mat-> mat.getIdMateria() == nm.getIdMateria()).findFirst().orElse(new Materia("")).getNomeMateria() )

                        ));

        Map<String, Double> groupByMateria =
                list.stream().collect(Collectors.groupingBy(NotasMateriaDTO::getNomeMateria,Collectors.averagingDouble(NotasMateriaDTO::getNotaMateria)));

        List<NotasMateriaDTO> NotasMateriaDTO = new ArrayList<>();

        groupByMateria.entrySet().stream()
                .filter(mat-> mat.getValue()<=4)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue))
                .forEach(
                        (k,v) -> NotasMateriaDTO.add(new NotasMateriaDTO(v,k))
                );
        return NotasMateriaDTO;


    }

    public  List<TipoNotaVsNotaDTO> buscaNotavsTipoList(Integer idusuario) {

        List<NotasMateria> notasMaterias = repository.findAll(Sort.by("dataNota").descending());

        List<TipoNotaVsNotaDTO> result = notasMaterias.stream()
                .filter(t -> t.getIdUsuario()== idusuario)
                .collect(Collectors.groupingBy(NotasMateria::getTipoNota,
                        Collectors.mapping(NotasMateria::getNotaMateria,
                                averagingDouble(Double::doubleValue))))
                .entrySet().stream()
                .map(TipoNotaVsNotaDTO:: new )
                .collect(Collectors.toList());

        return  result;
    }


    public List<TipoEstudoNotaDTO> notasVsTipoEstudo(Integer idusuario) {

        Usuario user = usuarioRepository.findByIdUsuario(idusuario);

        List<NotasVsTipoEstudoDTO> list =  new ArrayList<>();

        user.getNotasMateria().forEach(
                nm ->
                        list.add(
                                new NotasVsTipoEstudoDTO(
                        nm.getIdMateria(),
                        nm.getNotaMateria(),
                        user.getAgendamentos().stream().filter(ag-> ag.getIdMateria() == nm.getIdMateria()).findFirst().orElse(new Agendamento("")).getTipoEstudo() )

                ));

        Map<String, Double> groupByAgendamentoTipo =
                list.stream().collect(Collectors.groupingBy(NotasVsTipoEstudoDTO::getTipoEstudo,Collectors.averagingDouble(NotasVsTipoEstudoDTO::getNota)));


        List<TipoEstudoNotaDTO> tipoEstudoNotaDTO = new ArrayList<>();

        groupByAgendamentoTipo.forEach(
                (k,v) -> tipoEstudoNotaDTO.add(new TipoEstudoNotaDTO(v,k))
        );
        return tipoEstudoNotaDTO;


    }

    public List<HorasVsEstudoDTO> horasVsEstudo(Integer idusuario) {

        Usuario user = usuarioRepository.findByIdUsuario(idusuario);

        List<HorasVsEstudoDTO> list =  new ArrayList<>();

        Map<Integer, Long> groupbyMateria =
                user.getAgendamentos().stream().collect(Collectors.groupingBy(Agendamento::getIdMateria,Collectors.summingLong(Agendamento::getMinEstudo)));

        groupbyMateria.forEach(
                (idmateria, minestudo) -> list.add( new HorasVsEstudoDTO(
                        idmateria,
                        minestudo,
                        user.getMaterias().stream().filter(m-> m.getIdMateria() == idmateria).findFirst().get().getNomeMateria(),
                        user.getNotasMateria().stream().filter(nt->nt.getIdMateria() == idmateria).collect(Collectors.averagingDouble(NotasMateria::getNotaMateria))
                ))
        );

        return list;

    }

    public List<NotasMateriaDTO> notasVsMateria(Integer idusuario) {

        Usuario user = usuarioRepository.findByIdUsuario(idusuario);

        List<NotasMateriaDTO> list =  new ArrayList<>();

        user.getNotasMateria().forEach(
                nm ->
                        list.add(
                                new NotasMateriaDTO(
                                        nm.getNotaMateria(),
                                        user.getMaterias().stream().filter(mat-> mat.getIdMateria() == nm.getIdMateria()).findFirst().orElse(new Materia("")).getNomeMateria() )

                        ));

        Map<String, Double> groupByMateria =
                list.stream().collect(Collectors.groupingBy(NotasMateriaDTO::getNomeMateria,Collectors.averagingDouble(NotasMateriaDTO::getNotaMateria)));


        List<NotasMateriaDTO> NotasMateriaDTO = new ArrayList<>();

        groupByMateria.forEach(
                (k,v) -> NotasMateriaDTO.add(new NotasMateriaDTO(v,k))
        );
        return NotasMateriaDTO;


    }
}
