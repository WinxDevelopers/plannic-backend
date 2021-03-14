package br.com.plannic.repository;


import br.com.plannic.model.Usuario;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    Usuario findByEmail(String username);
    Usuario findByIdUsuario(int id);
    Usuario findByIdUsuario(int id, Sort sort);

    @Query("SELECT u FROM Usuario u WHERE u.CodVerifica = ?1")
     Usuario findByVerificationCode(String code);
}
