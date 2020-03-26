package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Avaliacao;
import br.com.zup.estrelas.financas.repository.AvaliacaoRepository;


@Service
public class AvaliacaoService {

    private static final int MAX_CARACTERE = 400;
    private static final int NOTA_MIN = 0;
    private static final int NOTA_MAX = 5;

    @Autowired
    AvaliacaoRepository repository;

    public Avaliacao insereAvaliacao(Avaliacao avaliacao) {
        if (this.validaAvaliacao(avaliacao)) {
            return repository.save(avaliacao);
        }

        return null;

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
        if (this.validaAvaliacao(avaliacao)) {
            Avaliacao avaliacaoBanco = repository.findById(idAvaliacao).get();
            avaliacaoBanco.setComentario(avaliacao.getComentario());
            avaliacaoBanco.setIdUsuario(avaliacao.getIdUsuario());
            avaliacaoBanco.setNotaAvaliacao(avaliacao.getNotaAvaliacao());
            return repository.save(avaliacaoBanco);


        }
        return null;
    }

    private boolean validaAvaliacao(Avaliacao avaliacao) {
        if (avaliacao.getNotaAvaliacao() < NOTA_MIN || avaliacao.getNotaAvaliacao() > NOTA_MAX) {
            return false;
        }
        if (avaliacao.getComentario().length() > MAX_CARACTERE) {

            return false;
        }
        return true;
    }
}


