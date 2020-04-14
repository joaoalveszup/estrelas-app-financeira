package br.com.zup.estrelas.financas.service;

import br.com.zup.estrelas.financas.dto.UsuarioDto;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public Usuario insereUsuario(UsuarioDto usuarioDto) {
        return this.usuarioRepository.save(new Usuario(usuarioDto));
    }

    public Optional<Usuario> buscaUsuario(Long idUsuario) {
        return this.usuarioRepository.findById(idUsuario);
    }
}
