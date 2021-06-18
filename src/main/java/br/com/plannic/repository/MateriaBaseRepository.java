package br.com.plannic.repository;

import br.com.plannic.model.MateriaBase;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaBaseRepository extends JpaRepository<MateriaBase, Integer> {
    MateriaBase findById(int id);
}