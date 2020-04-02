package br.com.zup.estrelas.financas.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.enums.Parentesco;

@Repository
public interface DependenteRepository extends JpaRepository<Dependente, Long> {

    List<Dependente> findAllByIdUsuario(Long idUsuario);

    List<Dependente> findByIdUsuarioAndParentesco(Long idUsuario, Parentesco parentesco);

    Optional<Dependente> findByIdUsuarioAndIdDependente(Long idUsuario, Long idDependente);

}
