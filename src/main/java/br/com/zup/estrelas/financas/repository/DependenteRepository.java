package br.com.zup.estrelas.financas.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Dependente;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {
    
    Dependente findByIdUsuarioAndIdDependente(Long idUsuario, Long idDependente);
    
    List<Dependente> findAllByIdUsuario(Long idUsuario);

}
