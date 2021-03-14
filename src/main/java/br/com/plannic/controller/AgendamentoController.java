package br.com.plannic.controller;

import br.com.plannic.model.Agendamento;
import br.com.plannic.model.NotasMateria;
import br.com.plannic.service.AgendamentoService;
import br.com.plannic.service.MateriaService;
import br.com.plannic.service.NotasMateriaService;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.MDC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    @Autowired
    public AgendamentoController(AgendamentoService agendamentoService) {
        this.agendamentoService = agendamentoService;
    }

    @PostMapping("/cadastro")
    @ApiOperation(value = "Realiza o cadastro de agendamentos")
    public ResponseEntity<Agendamento> save(@Valid @RequestBody Agendamento agendamento){
        try {
            MDC.put("name", agendamento.getIdAgendamento());
            MDC.put("fluxo", "POST save");
            agendamentoService.save(agendamento);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
    @ApiOperation(value = "Realiza a atualização de agendamentos")
    public ResponseEntity update(@RequestBody Agendamento agendamento) {
        try {
            MDC.put("user_id", agendamento.getIdAgendamento());
            MDC.put("tipo_estudo", agendamento.getTipoEstudo());
            MDC.put("fluxo", "PUT update");
            if(agendamentoService.update(agendamento)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Realiza a deleção de agendamentos")
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
//            MDC.put("user_id", agendamento.getIdAgendamento());
//            MDC.put("tipo_estudo", agendamento.getTipoEstudo());
            MDC.put("fluxo", "DELETE delete");
            if (agendamentoService.delete(id)) {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    @ApiOperation(value = "Realiza a busca de agendamentos cadastrados")
    public ResponseEntity getAll() {
        try{
            MDC.put("fluxo", "GET agendamentos");
            return new ResponseEntity<>(agendamentoService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
