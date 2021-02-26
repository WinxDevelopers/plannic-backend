package br.com.plannic.controller;


import br.com.plannic.model.NotasMateria;
import br.com.plannic.model.Usuario;
import br.com.plannic.service.MateriaService;
import br.com.plannic.service.NotasMateriaService;
import org.apache.log4j.MDC;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Array;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/notasMateria")
public class NotasMateriaController {
    @Autowired
    private NotasMateriaService notasMateriaService;



    @PostMapping("/cadastro")
    public ResponseEntity<NotasMateria> save(@Valid @RequestBody NotasMateria notasMateria){
        try {
            MDC.put("nota", notasMateria.getNotaMateria());
            MDC.put("fluxo", "POST save");
            notasMateriaService.save(notasMateria);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
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

    @DeleteMapping
    public ResponseEntity delete(@RequestBody NotasMateria notasMateria) {
        try {
            MDC.put("user_id", notasMateria.getIdUsuario());
            MDC.put("nota", notasMateria.getNotaMateria());
            MDC.put("fluxo", "DELETE delete");
            if (notasMateriaService.delete(notasMateria)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET notas materias");
            return new ResponseEntity<>(notasMateriaService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("/{idusuario}/{idmateria}")
    public ResponseEntity getNotaVsMateria(@PathVariable final Integer idusuario,@PathVariable final Integer idmateria) {
        try{
            MDC.put("fluxo", "GET notas materias do usuario");
            List teste = notasMateriaService.buscaNotavsData(idusuario,idmateria);

            return new ResponseEntity<>(teste ,HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("notamaior/{idusuario}")
    public ResponseEntity getNotaMaior8(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas materias que o usuario tem 8 ou mais");
            return new ResponseEntity<>(notasMateriaService.buscaMaior8(idusuario), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
    @GetMapping("notamenor/{idusuario}")
    public ResponseEntity getNotaMenor8(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas materias que o usuario tem 5 ou menos");
            return new ResponseEntity<>(notasMateriaService.buscaMenor5(idusuario), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
    @GetMapping("notavstipo/{idusuario}")
    public ResponseEntity getNotaVsTipo(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas materias que o usuario tem 5 ou menos");
            List teste =notasMateriaService.buscaNotavsTipoList(idusuario);

            return new ResponseEntity<>(teste, HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }

    @GetMapping("notavsmateira/{idusuario}")
    public ResponseEntity getNotaVsMateria(@PathVariable final Integer idusuario) {
        try{
            MDC.put("fluxo", "GET notas materias que o usuario tem 5 ou menos");
            List lista =notasMateriaService.buscaNotavsMateriaList(idusuario);
            return new ResponseEntity<>(lista, HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }



}
