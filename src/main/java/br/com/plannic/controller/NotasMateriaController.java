package br.com.plannic.controller;


import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.MateriaService;
import br.com.plannic.service.NotasMateriaService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notasMateria")
public class NotasMateriaController {
    @Autowired
    private NotasMateriaService notasMateriaService;



    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de notas da materia")
    public ResponseEntity<NotasMateria> save(@Valid @RequestBody NotasMateria notasMateria){
        try {
//            MDC.put("nota", notasMateria.getNotaMateria());
//            MDC.put("fluxo", "POST save");
            notasMateriaService.save(notasMateria);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    @ApiOperation(value = "Realiza a atualização de nota da materia ")
    public ResponseEntity update(@RequestBody NotasMateria notasMateria) {
        try {
            MDC.put("user_id", notasMateria.getIdUsuario());
            MDC.put("nota", notasMateria.getNotaMateria());
            MDC.put("fluxo", "PUT update");
            if(notasMateriaService.update(notasMateria)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Realiza a deleção de nota da materia")
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
//            MDC.put("user_id", notasMateria.getIdUsuario());
//            MDC.put("nota", notasMateria.getNotaMateria());
            MDC.put("fluxo", "DELETE delete");
            if (notasMateriaService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ApiOperation(value = "Realiza a busca de todas as notas cadastradas")
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET notas materias");
            return new ResponseEntity<>(notasMateriaService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("notasvsMateria/{idusuario}")
    @ApiOperation(value = "Busca da media das notas e sua materia, para criação do grafico")
    public ResponseEntity getNotaVsMatsferia(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas materias do usuario");
            List teste = notasMateriaService.notasVsMateria(idusuario);

            return new ResponseEntity<>(teste ,HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("notamaior/{idusuario}")
    @ApiOperation(value = "Verifica se o usuario e qualificado para ser tutor, para criação do emblema")
    public ResponseEntity getNotaMaior8(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas materias que o usuario tem 8 ou mais");
            return new ResponseEntity<>(notasMateriaService.buscaMaior8(idusuario), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
    @GetMapping("notamenor/{idusuario}")
    @ApiOperation(value = "Verifica se o usuario e qualificado para ser tutor, para criação do emblema")
    public ResponseEntity getNotaMenor8(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas materias que o usuario tem 5 ou menos");
            return new ResponseEntity<>(notasMateriaService.buscaMenor4(idusuario), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
    @GetMapping("notastipo/{idusuario}")
    @ApiOperation(value = "Busca a media da nota de cada tipo de estudo, para criação do grafico")
    public ResponseEntity getNotaVsTipo(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas materias que o usuario tem 5 ou menos");
            List teste =notasMateriaService.buscaNotavsTipoList(idusuario);

            return new ResponseEntity<>(teste, HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("notasvsdata/{idusuario}/{idmateira}")
    @ApiOperation(value = "Busca a media da nota de cada materia trazendo as datas, para criação do grafico")
    public ResponseEntity getNotaVsData(@PathVariable final Integer idusuario,@PathVariable final Integer idmateira) {
        try{
            MDC.put("fluxo", "GET notas materias que o usuario tem 5 ou menos");
            List lista =notasMateriaService.buscaNotavsData(idusuario,idmateira);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("notasvstipo/{idusuario}")
    @ApiOperation(value = "Busca a media da nota de cada tipo de estudo, para criação do grafico")
    public ResponseEntity getNotasvsTipo(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas vs tipo de estudo");
            List lista =notasMateriaService.notasVsTipoEstudo(idusuario);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("horasvsnota/{idusuario}")
    @ApiOperation(value = "Busca as horas gasta e a nota da materia, para criação do grafico")
    public ResponseEntity getHorasVsNota(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas vs tipo de estudo");
            List list = notasMateriaService.horasVsEstudo(idusuario);
            return new ResponseEntity<>(list, HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }





}
