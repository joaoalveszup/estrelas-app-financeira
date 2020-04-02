package br.com.zup.estrelas.financas.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Avaliacao;
import br.com.zup.estrelas.financas.entity.Usuario;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    Avaliacao findByUsuario(Usuario Usuario);

    Avaliacao findFirstByIdUsuario(Long idUsuario);
    
    Optional<Avaliacao> findByIdUsuarioAndIdAvaliacao(Long idUsuario, Long idAvaliacao);


}
