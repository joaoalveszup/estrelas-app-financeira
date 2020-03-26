package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.repository.DependenteRepository;

@Service
public class DependenteService {

    @Autowired
    DependenteRepository dependenteRepository;


    public Dependente insereDependente(Dependente dependente) {
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
        dependenteBanco.setParentesco(dependente.getParentesco());
        dependenteBanco.setRenda(dependente.getRenda());

        return this.dependenteRepository.save(dependenteBanco);
    }

    public void deletaDependente(Long idDependente) {
        this.dependenteRepository.deleteById(idDependente);
    }


}


