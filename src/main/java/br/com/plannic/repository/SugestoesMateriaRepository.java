package br.com.plannic.repository;

import br.com.plannic.model.SugestoesMateria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SugestoesMateriaRepository extends JpaRepository<SugestoesMateria, Integer> {
    SugestoesMateria findById(int id);
}
