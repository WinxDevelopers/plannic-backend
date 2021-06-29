package br.com.plannic.repository;

import br.com.plannic.model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno,Integer> {
    Aluno findById(int id);

    @Query("SELECT u FROM Aluno u WHERE u.idUsuarioAluno <> ?1 AND u.idMateriaBase = ?2")
        Aluno findByIdMateriaBase(Integer id, Integer idMateriaBase);

    @Query("SELECT u FROM Aluno u WHERE u.idUsuarioAluno = ?1 AND u.idMateriaBase = ?2")
        Aluno findByTutoria(Integer id, Integer idMateriaBase);

}