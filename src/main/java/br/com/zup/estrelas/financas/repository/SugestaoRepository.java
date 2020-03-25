package br.com.zup.estrelas.financas.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Sugestao;

@Repository
public interface SugestaoRepository extends CrudRepository <Sugestao, Long> {
    

}
