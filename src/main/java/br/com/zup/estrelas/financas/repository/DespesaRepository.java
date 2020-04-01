package br.com.zup.estrelas.financas.repository;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Despesa;


@Repository
public interface DespesaRepository extends JpaRepository<Despesa, Long> {
    
    List<Despesa> findAllByIdUsuarioAndVencimentoBetween(Long idUsuario, LocalDate inicioData, LocalDate fimData);
    
     Despesa findByIdUsuarioAndIdDespesa(Long idUsuario, Long idDespesa);
     
     List<Despesa> findAllByIdUsuario(Long idUsuario);
}
