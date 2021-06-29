package br.com.plannic.repository;

import br.com.plannic.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Integer> {
    Tutor findById(int id);
}