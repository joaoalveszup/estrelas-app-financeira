package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Avaliacao;
import br.com.zup.estrelas.financas.repository.AvaliacaoRepository;

@Service
public class AvaliacaoService {

    @Autowired
    AvaliacaoRepository repository;

    public Avaliacao insereAvaliacao(Avaliacao avaliacao) {
        return repository.save(avaliacao);

    }

    public Avaliacao buscaAvaliacao(Long idAvaliacao) {
        return repository.findById(idAvaliacao).get();
    }

    public List<Avaliacao> buscaAvaliacoes() {
        return (List<Avaliacao>) repository.findAll();
    }

    public void deletaAvaliacao(Long idAvaliacao) {
        this.repository.deleteById(idAvaliacao);
    }

    public Avaliacao alteraAvaliacao(Long idAvaliacao, Avaliacao avaliacao) {
        Avaliacao avaliacaoBanco = repository.findById(idAvaliacao).get();
        avaliacaoBanco.setComentario(avaliacao.getComentario());
        avaliacaoBanco.setIdUsuario(avaliacao.getIdUsuario());
        avaliacaoBanco.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
        return repository.save(avaliacaoBanco);

    }
}
