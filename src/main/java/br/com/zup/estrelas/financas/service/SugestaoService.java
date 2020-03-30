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
        sugestaoBanco.setTitulo(sugestao.getTitulo());
        sugestaoBanco.setDescricao(sugestao.getDescricao());
        sugestaoBanco.setTipoSugestao(sugestao.getTipoSugestao());
        return this.sugestaoRepository.save(sugestaoBanco);
    }
}
