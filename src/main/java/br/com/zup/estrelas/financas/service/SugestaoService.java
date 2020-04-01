package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.repository.SugestaoRepository;

@Service
public class SugestaoService {

    @Autowired
    SugestaoRepository sugestaoRepository;

    public Sugestao insereSugestao(Sugestao sugestao) {
        if (limitaCaratereTitulo(sugestao.getTitulo())
                || limitaCaratereDescricao(sugestao.getDescricao())) {
            return null;
        }

        return this.sugestaoRepository.save(sugestao);
    }

    public Sugestao buscaSugestao(Long idSugestao) {
        return this.sugestaoRepository.findById(idSugestao).get();
    }

    public List<Sugestao> buscaSugestoes() {
        return (List<Sugestao>) sugestaoRepository.findAll();
    }

    public void deleteSugestao(Long idSugestao) {
        this.sugestaoRepository.deleteById(idSugestao);
    }

    public Sugestao alteraSugestao(Long idSugestao, Sugestao sugestao) {
        Sugestao sugestaoBanco = sugestaoRepository.findById(idSugestao).get();

        if (limitaCaratereDescricao(sugestao.getDescricao())
                || limitaCaratereTitulo(sugestao.getTitulo())) {
            return null;
        }
        if (validaTituloEDescricao(sugestao.getTitulo(), sugestao.getDescricao())) {
            sugestaoBanco.setDescricao(sugestao.getDescricao());
            sugestaoBanco.setTitulo(sugestao.getTitulo());
        }
        return this.sugestaoRepository.save(sugestaoBanco);
    }

    public boolean validaTituloEDescricao(String titulo, String descricao) {
        if (titulo.equals(titulo) || descricao.equals(descricao)) {
            return true;
        }
        return false;
    }

    public boolean limitaCaratereTitulo(String titulo) {

        if (titulo.length() > 40) {
            return true;
        }
        return false;
    }

    public boolean limitaCaratereDescricao(String descricao) {
        if (descricao.length() > 400) {
            return true;
        }
        return false;
    }

}

