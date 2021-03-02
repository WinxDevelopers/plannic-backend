//package br.com.plannic.service;
//
//
//import br.com.plannic.model.NotasTutor;
//import br.com.plannic.model.Usuario;
//import br.com.plannic.repository.NotasTutorRepository;
//import org.apache.log4j.Logger;
//import org.apache.log4j.MDC;
//import org.modelmapper.ModelMapper;
//import org.springframework.stereotype.Service;
//
//import javax.transaction.Transactional;
//import java.util.Collections;
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@Service
//@Transactional
//public class NotasTutorService {
//
//    private NotasTutorRepository repository;
//
//    private static Logger logger = Logger.getLogger(NotasTutorService.class);
//
//    public List<NotasTutor> getAll() {
//        ModelMapper mapper = new ModelMapper();
//        List<NotasTutor> notasTutors = repository.findAll();
//
//        if (!notasTutors.isEmpty()) {
//            logger.info("Notas recuperadas");
//            return  notasTutors
//                    .stream()
//                    .map(notasTutor -> mapper.map(notasTutor, NotasTutor.class))
//                    .collect(Collectors.toList());
//        }
//        return Collections.emptyList();
//    }
//
//    public void save(NotasTutor notasTutor) {
//        ModelMapper mapper = new ModelMapper();
//        var notasTutorSalva = repository.save(mapper.map(notasTutor, NotasTutor.class));
//        MDC.put("user_id", notasTutorSalva.getIdUsuario());
//        logger.info("Nota salva");
//
//
//    }
//
//
//    public boolean update(NotasTutor notasTutor) {
//        Optional<NotasTutor> notasTutors = this.repository.findById(notasTutor.getIdUsuarioTutor());
//
//        if (notasTutors.isPresent()) {
//            logger.info("Nota atualizado");
//            ModelMapper mapper = new ModelMapper();
//            repository.save(mapper.map(notasTutor, NotasTutor.class));
//            return true;
//        }
//
//        return false;
//    }
//
//
//    public boolean delete(NotasTutor notasTutor) {
//        Optional<NotasTutor> notasTutors = this.repository.findById(notasTutor.getIdUsuarioTutor());
//
//        if (notasTutors.isPresent()) {
//            logger.info("Nota deletada");
//            this.repository.deleteById(notasTutor.getIdUsuarioTutor());
//            return true;
//        }
//
//        return false;
//    }
//
//
//}
