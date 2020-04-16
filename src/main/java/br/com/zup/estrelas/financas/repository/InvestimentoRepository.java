package br.com.zup.estrelas.financas.repository;

import br.com.zup.estrelas.financas.entity.Investimento;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface InvestimentoRepository extends JpaRepository<Investimento, Long> {

    long countByPagoAndIdObjetivo(Boolean pago, Long idObjetivo);

    List<Investimento> findByPagoAndIdObjetivo(Boolean Pago, Long idObjetivo);

    Investimento findByIdInvestimentoAndIdObjetivo(Long idInvestimento, Long idObjetivo);

    List<Investimento> findAllByIdObjetivo(Long idObjetivo);

    @Query(value = "select SUM(i.valor) from Investimento i where i.idObjetivo in :objetivos and i.dataVencimento between :dataInicio and :dataFim")
    Double sumInvestimentos(List<Long> objetivos, LocalDate dataInicio, LocalDate dataFim);

}
