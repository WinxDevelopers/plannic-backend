package br.com.plannic.repository;

import br.com.plannic.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia,Integer> {
    Materia findById(int id);
    Materia findByIdSugestao(int idSugestao);
    Materia deleteByIdMateria(int id);
    Materia findByIdMateria(int id);
}
