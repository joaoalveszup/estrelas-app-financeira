package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import br.com.zup.estrelas.financas.entity.Objetivo;
import br.com.zup.estrelas.financas.repository.ObjetivoRepository;

@Service
public class ObjetivoService {

    @Autowired
    ObjetivoRepository objetivoRepository;

    public Objetivo insereObjetivo(Objetivo objetivo) {
        return this.objetivoRepository.save(objetivo);
    }

    public Objetivo buscaObjetivo(Long idObjetivo) {
        return this.objetivoRepository.findById(idObjetivo).get();
    }

    public List<Objetivo> listaObjetivos() {
        return this.objetivoRepository.findAll();
    }

    public Objetivo atualizarObjetivo(Long idObjetivo, Objetivo objetivo) {
        Objetivo objetivoAtual = objetivoRepository.findById(idObjetivo).get();

        objetivoAtual.setNome(objetivo.getNome());

        return this.objetivoRepository.save(objetivoAtual);
    }

    public void deletaObjetivo(@PathVariable Long idObjetivo) {
        this.objetivoRepository.deleteById(idObjetivo);
    }
}
