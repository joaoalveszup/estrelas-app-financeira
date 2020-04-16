package br.com.zup.estrelas.financas.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Sugestao;
import br.com.zup.estrelas.financas.enums.TipoSugestao;

@Repository
public interface SugestaoRepository extends JpaRepository<Sugestao, Long>{

    List<Sugestao> findByTipoSugestao( Optional<TipoSugestao> tipoSugestao);
    
}
