package br.com.plannic.repository;

import br.com.plannic.model.Agendamento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AgendamentoRepository extends JpaRepository<Agendamento, Integer> {
    Agendamento findById(int id);
}
