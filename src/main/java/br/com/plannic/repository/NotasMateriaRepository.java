package br.com.plannic.repository;

import br.com.plannic.model.NotasMateria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotasMateriaRepository extends JpaRepository<NotasMateria, Integer> {
    NotasMateria findById(int id);
}
