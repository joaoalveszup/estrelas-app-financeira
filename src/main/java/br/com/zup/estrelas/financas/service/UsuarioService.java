package br.com.zup.estrelas.financas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.UsuarioDto;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;

@Service
public class UsuarioService {
    
    @Autowired
    UsuarioRepository usuarioRepository;
    
    public UsuarioDto insereUsuario(UsuarioDto usuarioDto) {
        return this.usuarioRepository.save(usuarioDto);
    }
}
