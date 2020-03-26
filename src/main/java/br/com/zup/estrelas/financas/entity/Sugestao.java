package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Sugestao {


    @Column(name = "id_sugestao")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSugestao;

    @Column
    private String titulo;

    @Column
    private String descricao;

    @Column
    private TipoSugestao tipoSugestao;

<<<<<<< HEAD

=======
>>>>>>> 5f958be4d0370dd72a2bd98e2d2be7592caf20d7
    public Long getIdSugestao() {
        return idSugestao;
    }

    public void setIdSugestao(Long idSugestao) {
        this.idSugestao = idSugestao;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public TipoSugestao getTipoSugestao() {
        return tipoSugestao;
    }

    public void setTipoSugestao(TipoSugestao tipoSugestao) {
        this.tipoSugestao = tipoSugestao;
    }

<<<<<<< HEAD
}

=======

}
>>>>>>> 5f958be4d0370dd72a2bd98e2d2be7592caf20d7

