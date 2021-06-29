package br.com.plannic.repository;

import br.com.plannic.model.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria,Integer> {
    Tutoria findById(int id);

    @Query("SELECT u FROM Tutoria u WHERE u.idUsuarioAluno IS NOT NULL AND  u.idUsuarioTutor = ?1")
        Tutoria findByIdUsuarioTutor(Integer idUsuarioTutor);

    @Query("SELECT u FROM Tutoria u WHERE u.idUsuarioAluno = ?1 AND  u.idUsuarioTutor IS NOT NULL")
        Tutoria findByIdUsuarioAluno(Integer idUsuarioAluno);

    @Query("SELECT u FROM Tutoria u WHERE u.idUsuarioAluno IS NOT NULL AND  u.idUsuarioTutor IS NULL AND u.idUsuarioAluno <> ?1")
        Tutoria findByAlunos(Integer idUsuarioAluno);

    @Query("SELECT u FROM Tutoria u WHERE u.idUsuarioAluno IS NULL AND  u.idUsuarioTutor IS NOT NULL AND u.idUsuarioTutor <> ?1")
        Tutoria findByTutores(Integer idUsuarioTutor);
}