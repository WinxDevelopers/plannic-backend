package br.com.plannic.repository;

import br.com.plannic.model.NotasMateria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotasMateriaRepository extends JpaRepository<NotasMateria,Long> {
}
