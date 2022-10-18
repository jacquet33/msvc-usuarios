package services;

import models.entity.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Optional<Usuario> findByID(Long id);
    Usuario save(Usuario user);
    void delete(Long id);
}
