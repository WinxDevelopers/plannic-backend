package br.com.plannic.repository;

import br.com.plannic.model.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria,Integer> {
    Tutoria findById(int id);

    @Query("SELECT u FROM Tutoria u WHERE u.idUsuarioTutor = ?1")
        List<Tutoria> findByIdUsuarioTutor(Integer idUsuarioTutor);

    @Query("SELECT u FROM Tutoria u WHERE u.idUsuarioAluno = ?1")
        List<Tutoria> findByIdUsuarioAluno(Integer idUsuarioAluno);
}