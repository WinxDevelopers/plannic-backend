package br.com.plannic.repository;

import br.com.plannic.model.Funcao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FuncaoRepository extends JpaRepository<Funcao, Integer> {
    Funcao findById(int id);
}
