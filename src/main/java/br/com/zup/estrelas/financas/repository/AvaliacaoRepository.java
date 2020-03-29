package br.com.zup.estrelas.financas.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.zup.estrelas.financas.entity.Avaliacao;
import br.com.zup.estrelas.financas.entity.Usuario;

@Repository
public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

    Avaliacao findByUsuario(Usuario usuario);

}
