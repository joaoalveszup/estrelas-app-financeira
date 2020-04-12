package br.com.zup.estrelas.financas.controller;

import br.com.zup.estrelas.financas.dto.UsuarioDto;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.service.UsuarioService;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    
    @Autowired
    UsuarioService usuarioService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Usuario insereUsuario(@RequestBody UsuarioDto usuarioDto) {
        return this.usuarioService.insereUsuario(usuarioDto);
    }


    @GetMapping(path="/{idUsuario}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Optional<Usuario> buscaUsuario(@PathVariable Long idUsuario) {
        return this.usuarioService.buscaUsuario(idUsuario);
    }

}
