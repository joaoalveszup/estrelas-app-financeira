package br.com.zup.estrelas.financas.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.SugestaoRequestDto;
import br.com.zup.estrelas.financas.dto.SugestaoResponseDto;
import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.exceptions.ValidaCampoECaratereException;
import br.com.zup.estrelas.financas.repository.SugestaoRepository;

@Service
public class SugestaoService {

    private static final String ERRO_LIMITE_CARACTERE_INCORRETO = "Erro! Título ou descricão contém menos de 3 carateres"
            + " ou título tem mais de 40 caratares ou a descricao tem mais de 400 caratares. "
            + "Por favor, insira quantidade de caracteres corretos";
    public final int MIN_LIMITE_DE_CARATERE_TITULO_E_DESCRICAO = 3;
    public final int MAX_LIMITE_CARATERE_TITULO = 40;
    public final int MAX_LIMITE_CARATERE_DESCRICAO = 400;

    @Autowired
    SugestaoRepository sugestaoRepository;


    public SugestaoResponseDto insereSugestao(SugestaoRequestDto sugestaoRequestDto)
            throws ValidaCampoECaratereException {

        testaLimiteCaractere(sugestaoRequestDto);
        
        Sugestao sugestao =
                this.sugestaoRepository.save(Sugestao.fromSugestaoRequestDto(sugestaoRequestDto));
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
            SugestaoRequestDto sugestaoRequestDto) throws ValidaCampoECaratereException {
        Sugestao sugestao = Sugestao.fromSugestaoRequestDto(sugestaoRequestDto);
        Sugestao sugestaoBanco = sugestaoRepository.findById(idSugestao).get();

        testaLimiteCaractere(sugestaoRequestDto);
       
        sugestaoBanco.setDescricao(sugestao.getDescricao());
        sugestaoBanco.setTitulo(sugestao.getTitulo());
        return SugestaoResponseDto
                .fromSugestaoResponseDto(this.sugestaoRepository.save(sugestaoBanco));
    }



    public boolean maxLimitaCaratereTituloEDescricao(String titulo, String descricao) {
        return titulo.length() > MAX_LIMITE_CARATERE_TITULO
                || descricao.length() > MAX_LIMITE_CARATERE_DESCRICAO;
    }

    public boolean minLimitaCaratereTituloEDescricao(String titulo, String descricao) {
        return titulo.length() < MIN_LIMITE_DE_CARATERE_TITULO_E_DESCRICAO
                || descricao.length() < MIN_LIMITE_DE_CARATERE_TITULO_E_DESCRICAO;

    }

    public void testaLimiteCaractere(SugestaoRequestDto sugestaoRequestDto) throws ValidaCampoECaratereException {
        if (minLimitaCaratereTituloEDescricao(sugestaoRequestDto.getTitulo(),
                sugestaoRequestDto.getDescricao())
                || maxLimitaCaratereTituloEDescricao(sugestaoRequestDto.getTitulo(),
                        sugestaoRequestDto.getDescricao())) {
            throw new ValidaCampoECaratereException(
                    ERRO_LIMITE_CARACTERE_INCORRETO);
        }
    }


}

