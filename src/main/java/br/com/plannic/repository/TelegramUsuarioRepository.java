package br.com.plannic.repository;

import br.com.plannic.model.TelegramUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TelegramUsuarioRepository extends JpaRepository<TelegramUsuario,Integer> {
}
