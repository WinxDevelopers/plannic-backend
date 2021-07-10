package br.com.plannic.repository;

import br.com.plannic.model.TelegramUsuario;
import br.com.plannic.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TelegramUsuarioRepository extends JpaRepository<TelegramUsuario,Integer> {
    TelegramUsuario findByIdUsuario(int id);

}
