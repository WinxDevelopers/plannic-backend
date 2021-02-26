package br.com.plannic.service;

import br.com.plannic.dto.MateriaVsNotaDTO;
import br.com.plannic.model.NotasMateria;
import br.com.plannic.dto.TipoNotaVsNotaDTO;
import br.com.plannic.repository.MateriaRepository;
import br.com.plannic.repository.NotasMateriaRepository;
import org.apache.log4j.Logger;
import org.apache.log4j.MDC;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.averagingDouble;

@Service
@Transactional
public class NotasMateriaService {

    private NotasMateriaRepository repository;
    private  MateriaRepository materiaRepository;


    private static Logger logger = Logger.getLogger(NotasMateriaService.class);

    public NotasMateriaService(NotasMateriaRepository repository) {
        this.repository = repository;
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


    public boolean delete(NotasMateria notasMateria) {
        Optional<NotasMateria> notasMaterias = Optional.ofNullable(this.repository.findById(notasMateria.getIdMateria()));

        if (notasMaterias.isPresent()) {
            logger.info("Notas da materia deletada");
            this.repository.deleteById(notasMateria.getIdMateria());
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

    public List<NotasMateria> buscaMaior8(Integer idusuario){
        List<NotasMateria> notasMaterias = repository.findAll();
        Double mediaLimite = notasMaterias.stream().map(NotasMateria::getNotaMateria)
                .collect(averagingDouble(Double::doubleValue));
        List<NotasMateria> filtered =
                notasMaterias.stream()
                        .filter(t -> t.getIdUsuario()== idusuario && t.getNotaMateria() >= 8)
                        .collect(Collectors.toList());
        return  filtered;
    }

    public List<NotasMateria> buscaMenor5(Integer idusuario){
        List<NotasMateria> notasMaterias = repository.findAll();
        List<NotasMateria> filtered =
                notasMaterias.stream()
                        .filter(t -> t.getIdUsuario()== idusuario && t.getNotaMateria() <= 5)
                        .collect(Collectors.toList());
        return  filtered;
    }

//    public JSONArray buscaNotavsTipo(Integer idusuario){
//        JSONObject result = new JSONObject();
//        JSONArray addresses = new JSONArray();
//        result.put("addresses", addresses);
//        List<NotasMateria> notasMaterias = repository.findAll();
//        Map<String, Double> filtered = notasMaterias.stream().
//                filter(t -> t.getIdUsuario()== idusuario)
//                .collect(Collectors.groupingBy(NotasMateria::getTipoNota,
//                         Collectors.mapping(NotasMateria::getNotaMateria,
//                                 averagingDouble(Double::doubleValue))));
//
//        filtered.entrySet().stream()       //iterate the map
//                .map(e -> {                 //build an object
//                    HashMap address = new HashMap();
//                    address.put("address", e.getKey());
//                    address.put("domain", e.getValue());
//                    return address;
//                })
//                .forEach(addresses::put);   //insert into the array
//
//
//
//         return  addresses;
//    }

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

    public  List<MateriaVsNotaDTO> buscaNotavsMateriaList(Integer idusuario) {

        List<NotasMateria> notasMaterias = repository.findAll(Sort.by("dataNota").descending());

        List<MateriaVsNotaDTO> result = notasMaterias.stream()
                .filter(t -> t.getIdUsuario()== idusuario)
                .collect(Collectors.groupingBy(NotasMateria::getIdMateria,
                        Collectors.mapping(NotasMateria::getNotaMateria,
                                averagingDouble(Double::doubleValue))))
                .entrySet().stream()
                .map(MateriaVsNotaDTO:: new )
                .collect(Collectors.toList());

        return  result;
    }

//    public Map<Integer, Double> buscaNotavsMateria(Integer idusuario){
//        List<NotasMateria> notasMaterias = repository.findAll();
////        Double mediaLimite = notasMaterias.stream().map(NotasMateria::getNotaMateria)
////                .collect(averagingDouble(Double::doubleValue));
//        Map<Integer, Double> filtered = notasMaterias.stream()
//                .filter(t -> t.getIdUsuario()== idusuario )
//                .collect(Collectors.groupingBy(NotasMateria::getIdMateria,
//                        Collectors.mapping(NotasMateria::getNotaMateria,
//                                averagingDouble(Double::doubleValue))));
//        return  filtered;
//    }



}
