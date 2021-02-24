package br.com.plannic.controller;

import br.com.plannic.model.Agendamento;
import br.com.plannic.model.NotasMateria;
import br.com.plannic.service.AgendamentoService;
import br.com.plannic.service.NotasMateriaService;
import org.apache.log4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
@RestController
@RequestMapping("/agendamento")
public class AgendamentoController {

    private AgendamentoService agendamentoService;

    @PostMapping
    public ResponseEntity<Agendamento> save(@Valid @RequestBody Agendamento agendamento){
        try {
            MDC.put("id_agendamento", agendamento.getIdAgendamento());
            MDC.put("fluxo", "POST save");
            agendamentoService.save(agendamento);
        }finally{
            MDC.clear();
        }
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @PutMapping
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

    @DeleteMapping
    public ResponseEntity delete(@RequestBody Agendamento agendamento) {
        try {
            MDC.put("user_id", agendamento.getIdAgendamento());
            MDC.put("tipo_estudo", agendamento.getTipoEstudo());
            MDC.put("fluxo", "DELETE delete");
            if (agendamentoService.delete(agendamento)) {
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
            MDC.put("fluxo", "GET agendamentos");
            return new ResponseEntity<>(agendamentoService.getAll(), HttpStatus.OK);
        }finally {
            MDC.clear();
        }
    }
}
