package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.enuns.Parentesco;
import br.com.zup.estrelas.financas.repository.DependenteRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;

@Service
public class DependenteService {

    @Autowired
    DependenteRepository dependenteRepository;

    @Autowired
    UsuarioRepository usuarioRepository;
    
    // fazer um método para alterar salario do user passando como parametro o dependente

    public Dependente insereDependente(Dependente dependente) {

        Usuario usuario = usuarioRepository.findById(dependente.getIdUsuario()).get();

        for (Dependente dependenteUsuario : usuario.getDependentes()) {
            
            if (dependenteUsuario.getParentesco().equals(Parentesco.CONJUGE) && dependente.getParentesco().equals(Parentesco.CONJUGE)) {
                return null;
            }
        }
        
        if (dependente.getRenda() >= 1) {
            usuario.setSalarioLiquido(usuario.getSalarioLiquido() + dependente.getRenda());
        this.usuarioRepository.save(usuario);
        }
        return this.dependenteRepository.save(dependente);

    }

    public Dependente buscaDependente(Long idDependente) {
        return this.dependenteRepository.findById(idDependente).get();
    }

    public List<Dependente> buscaDependentes() {
        return (List<Dependente>) dependenteRepository.findAll();
    }

    public Dependente modificaDependente(Long idDependente, Dependente dependente) {

        Dependente dependenteBanco = dependenteRepository.findById(idDependente).get();

        dependenteBanco.setNome(dependente.getNome());
        dependenteBanco.setRenda(dependente.getRenda());

        return this.dependenteRepository.save(dependenteBanco);
    }

    public void deletaDependente(Long idDependente) {
        this.dependenteRepository.deleteById(idDependente);
    }

}


