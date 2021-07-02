package br.com.plannic.repository;

import br.com.plannic.model.NotasUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface NotasUsuarioRepository extends JpaRepository<NotasUsuario, Integer> {
    NotasUsuario findById(int id);

    @Query("SELECT u FROM NotasUsuario u WHERE u.idTutoria = ?1 AND u.idAvalia = ?2")
        NotasUsuario findByIdTutoriaTutor(Integer idTutoria, Integer idTutor);

    @Query("SELECT u FROM NotasUsuario u WHERE u.idTutoria = ?1 AND u.idAvaliado = ?2")
        NotasUsuario findByIdTutoriaAluno(Integer idTutoria, Integer idAluno);

    @Query("SELECT u FROM NotasUsuario u WHERE u.idAvalia = ?1 AND u.ativo = true")
        List<NotasUsuario> findAvaliar(Integer idAvalia);

    @Query("SELECT u FROM NotasUsuario u WHERE u.idAvaliado = ?1 AND u.ativo = false AND u.nota != null")
        List<NotasUsuario> findNotaUsuario(Integer idAvaliado);
}
