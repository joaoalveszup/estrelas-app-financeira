package br.com.zup.estrelas.financas.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Despesa;


@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
 
    
    List<Despesa> findAllByVencimentoBetween(LocalDate inicioData, LocalDate fimData);
    
}
