package br.com.zup.estrelas.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import br.com.zup.estrelas.financas.entity.Investimento;

public interface InvestimentoRepository extends JpaRepository<Investimento, Long>{

}
