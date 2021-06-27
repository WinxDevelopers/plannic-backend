package br.com.plannic.repository;

import br.com.plannic.model.Usuario;
import br.com.plannic.model.UsuarioFuncao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioFuncaoRepository extends JpaRepository<UsuarioFuncao, Integer> {
    UsuarioFuncao findById(int id);
    UsuarioFuncao findByUsuario(Usuario usuario);
}
