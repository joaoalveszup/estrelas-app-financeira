package br.com.zup.estrelas.financas.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Objetivo;

@Repository
public interface ObjetivoRepository extends JpaRepository<Objetivo, Long> {

    List<Objetivo> findAllByIdUsuario(Long idUsuario);

    Optional<Objetivo> findByIdUsuarioAndIdObjetivo(Long idUsuario, Long idObjetivo);

    @Query("select o.idObjetivo from Objetivo o where o.idUsuario = :idUsuario")
    List<Long> findAllIdsFromUser(Long idUsuario);
}
