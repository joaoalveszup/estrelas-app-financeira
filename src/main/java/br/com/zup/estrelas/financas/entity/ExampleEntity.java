package br.com.zup.estrelas.financas.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExampleEntity {

    @Id
    @Column(name = "example_id")
    private Long exampleId;
    
    @Column(name = "example_text", nullable = true)
    private String exampleText;

}
