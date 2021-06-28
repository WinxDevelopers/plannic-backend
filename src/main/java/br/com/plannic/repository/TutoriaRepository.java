package br.com.plannic.repository;

import br.com.plannic.model.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria,Integer> {
    Tutoria findById(int id);

}