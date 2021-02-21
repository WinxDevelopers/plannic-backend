package br.com.plannic.repository;


import br.com.plannic.model.Materia;
import br.com.plannic.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaRepository extends JpaRepository<Materia,Integer> {
    Materia findById(int id);
}
