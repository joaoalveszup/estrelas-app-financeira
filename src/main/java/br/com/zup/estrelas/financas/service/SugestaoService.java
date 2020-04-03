package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.SugestaoRequestDto;
import br.com.zup.estrelas.financas.dto.SugestaoResponseDto;
import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.exception.ValidaCampoECaratereException;
import br.com.zup.estrelas.financas.repository.SugestaoRepository;

@Service
public class SugestaoService {

    @Autowired
    SugestaoRepository sugestaoRepository;


    public SugestaoResponseDto insereSugestao(SugestaoResponseDto sugestaoResponseDto)
            throws ValidaCampoECaratereException {
        if (minLimitaCaraterDescricao(sugestaoResponseDto.getDescricao())
                || minLimitaCaratereTitulo(sugestaoResponseDto.getTitulo()))
            throw new ValidaCampoECaratereException(
                    "Tendo problemas com inserçao da sua sugestao? 1. Verifique se seu titulo contem entre 3 e 40 carateres. "); 
                          
        {
            if (maxLimitaCaratereTitulo(sugestaoResponseDto.getTitulo())
                    || maxLimitaCaratereDescricao(sugestaoResponseDto.getDescricao())) {
                throw new ValidaCampoECaratereException(
                        "Tendo problemas com inserçao da sua sugestao? Verifique se a sua descricao contem entre 3 e 400 carateres ");
            }

        }
        Sugestao sugestao =
                this.sugestaoRepository.save(Sugestao.fromSugestaDto(sugestaoResponseDto));
        return SugestaoResponseDto.fromSugestaoResponseDto(sugestao);

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

    public SugestaoResponseDto alteraSugestao(Long idSugestao,
            SugestaoResponseDto sugestaoResponseDto) throws ValidaCampoECaratereException {
        Sugestao sugestao = Sugestao.fromSugestaDto(sugestaoResponseDto);
        Sugestao sugestaoBanco = sugestaoRepository.findById(idSugestao).get();
        if (minLimitaCaraterDescricao(sugestao.getDescricao())
                || minLimitaCaratereTitulo(sugestao.getTitulo()))
            throw new ValidaCampoECaratereException(
                    "Tendo problemas com inserçao da sua sugestao? 1. Verifique se seu titulo contem entre 3 e 40 carateres. "); 
                          {
            if (maxLimitaCaratereDescricao(sugestao.getDescricao())
                    || maxLimitaCaratereTitulo(sugestao.getTitulo())) {

                throw new ValidaCampoECaratereException(
                        "Tendo problemas com inserçao da sua sugestao? Verifique se a sua descricao contem entre 3 e 400 carateres");
            }
        }

        sugestaoBanco.setDescricao(sugestao.getDescricao());
        sugestaoBanco.setTitulo(sugestao.getTitulo());


        return SugestaoResponseDto
                .fromSugestaoResponseDto(this.sugestaoRepository.save(sugestaoBanco));
    }

    public boolean validaTituloEDescricao(String titulo, String descricao) {
        return titulo.equals(titulo) || descricao.equals(descricao);
    }

    public boolean maxLimitaCaratereTitulo(String titulo) {
        return titulo.length() > 40;
    }

    static public boolean maxLimitaCaratereDescricao(String descricao) {
        return descricao.length() > 400;
    }

    public boolean minLimitaCaratereTitulo(String titulo) {
        return titulo.length() < 3;

    }

    public boolean minLimitaCaraterDescricao(String descricao) {
        return descricao.length() < 3;

    }
}

