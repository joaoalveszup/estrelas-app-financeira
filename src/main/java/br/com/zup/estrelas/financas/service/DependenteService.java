package br.com.zup.estrelas.financas.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.enuns.Parentesco;
import br.com.zup.estrelas.financas.repository.DependenteRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;

@Service
public class DependenteService {

    private static final int RENDA_MIN_DEPENDENTE = 1;

    @Autowired
    DependenteRepository dependenteRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    // fazer um método genérico para o salário

    public Dependente insereDependente(Dependente dependente) {

        Usuario usuario = usuarioRepository.findById(dependente.getIdUsuario()).get();

        for (Dependente dependenteUsuario : usuario.getDependentes()) {

            if (dependenteUsuario.getParentesco().equals(Parentesco.CONJUGE)) {
                return null;
            }

        }

        if (dependente.getRenda() >= RENDA_MIN_DEPENDENTE) {
            usuario.setSalarioLiquido(usuario.getSalarioLiquido() + dependente.getRenda());
            this.usuarioRepository.save(usuario);
        }
        return this.dependenteRepository.save(dependente);
    }

    public Dependente modificaDependente(Long idDependente, Dependente dependente) {

        Dependente dependenteBanco = dependenteRepository.findById(idDependente).get();
        Usuario usuario = usuarioRepository.findById(dependenteBanco.getIdUsuario()).get();

        dependenteBanco.setNome(dependente.getNome());

        Float salarioAntigo = dependenteBanco.getRenda();
        dependenteBanco.setRenda(dependente.getRenda());
        Float salarioAtual = dependenteBanco.getRenda();

        usuario.setSalarioLiquido(usuario.getSalarioLiquido() + salarioAtual - salarioAntigo);
        this.usuarioRepository.save(usuario);
        return this.dependenteRepository.save(dependenteBanco);
    }

    public Dependente buscaDependente(Long idDependente) {
        return this.dependenteRepository.findById(idDependente).get();
    }

    public List<Dependente> buscaDependentes() {
        return (List<Dependente>) dependenteRepository.findAll();
    }


    public void deletaDependente(Long idDependente) {

        Dependente dependente = buscaDependente(idDependente);
        Usuario usuario = usuarioRepository.findById(dependente.getIdUsuario()).get();

        if (dependente.getRenda() >= RENDA_MIN_DEPENDENTE) {
            usuario.setSalarioLiquido(usuario.getSalarioLiquido() - dependente.getRenda());
            this.usuarioRepository.save(usuario);
        }
        this.dependenteRepository.deleteById(idDependente);
    }
}


