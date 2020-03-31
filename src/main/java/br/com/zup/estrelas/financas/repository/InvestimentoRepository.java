package br.com.zup.estrelas.financas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Investimento;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long>{

    long countByPagoAndIdObjetivo(Boolean pago, Long idObjetivo);
    List<Investimento> findByPagoAndIdObjetivo(Boolean Pago, Long idObjetivo);
    
}
