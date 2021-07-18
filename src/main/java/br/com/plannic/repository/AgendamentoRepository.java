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
            "WHERE a.temponotificacao != 'N' and ((recorrencia = 'N' and recorrenciainicio = current_date) or " +
            "(recorrencia = 'dia' and recorrenciainicio <= current_date and recorrenciafim >= current_date) or " +
            "(recorrencia = 'semana' and recorrenciainicio <= current_date and recorrenciafim >= current_date and (recorrenciainicio + interval '1 week') = current_date) or " +
            "(recorrencia = 'mes' and recorrenciainicio <= current_date and recorrenciafim >= current_date and (recorrenciainicio + interval '1 month') = current_date)) " +
            "and (a.horainicio - ((cast(a.temponotificacao as integer) / 60 ) * interval '1 minute')) = cast(to_timestamp(to_char(now(),'HH24:MI:00'),'HH24:MI:00')as time)",
            nativeQuery = true )
    List<Agendamento> findAllAgendamentosTelegram();
}
