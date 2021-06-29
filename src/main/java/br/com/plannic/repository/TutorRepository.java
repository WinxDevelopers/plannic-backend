package br.com.plannic.repository;

import br.com.plannic.model.Aluno;
import br.com.plannic.model.Tutor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorRepository extends JpaRepository<Tutor,Integer> {
    Tutor findById(int id);

    @Query("SELECT u FROM Tutor u WHERE u.idUsuarioTutor <> ?1 AND u.idMateriaBase = ?2")
        Tutor findByIdMateriaBase(Integer id, Integer idMateriaBase);

    @Query("SELECT u FROM Tutor u WHERE u.idUsuarioTutor = ?1 AND u.idMateriaBase = ?2")
        Tutor findByTutoria(Integer id, Integer idMateriaBase);
}