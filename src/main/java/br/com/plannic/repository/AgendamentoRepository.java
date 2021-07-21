package br.com.plannic.repository;

import br.com.plannic.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    Agendamento findById(int id);

    @Query(value = "" +
            "SELECT * " +
            "FROM public.agendamento a " +
            "WHERE a.temponotificacao != 'N' and recorrenciainicio = current_date " +
            "and (a.horainicio - ((cast(a.temponotificacao as integer) / 60 ) * interval '1 minute')) = cast(to_timestamp(to_char(now(),'HH24:MI:00'),'HH24:MI:00')as time)",
            nativeQuery = true )
    List<Agendamento> findAllAgendamentosTelegram();
}
