package br.com.plannic.repository;

import br.com.plannic.model.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria,Integer> {
    Tutoria findById(int id);
    Tutoria findByIdUsuarioTutor(int idUsuarioTutor);
    Tutoria findByIdUsuarioAluno(int idUsuarioAluno);

    @Query("SELECT t FROM Tutoria t WHERE t.idUsuarioAluno IS NOT NULL AND  t.idUsuarioTutor IS NULL AND dt.idUsuarioAluno <> ?1")
        Tutoria findByAlunos(int id);

    @Query("SELECT t FROM Tutoria t WHERE t.idUsuarioAluno IS NULL AND  t.idUsuarioTutor IS NOT NULL AND dt.iUsuarioTutor <> ?1")
        Tutoria findByTutores(int id);
}