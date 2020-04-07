package br.com.zup.estrelas.financas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.dto.CriaDependenteDto;
import br.com.zup.estrelas.financas.dto.DependenteDto;
import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.enums.Parentesco;
import br.com.zup.estrelas.financas.exceptions.DependenteException;
import br.com.zup.estrelas.financas.repository.DependenteRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;

@Service
public class DependenteService {

    private static final String MENSAGEM_EXCEPTION_CONJUGE =
            "Já existe um cônjuge na sua lista de dependentes. Por favor, insira outro parentesco!";

    private static final String MENSAGEM_EXCEPTION_GENERICA =
            "Esse dependente não corresponde ao usuário informado!";

    private static final int RENDA_MIN_DEPENDENTE = 1;

    @Autowired
    DependenteRepository dependenteRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Dependente insereDependente(CriaDependenteDto criaDependenteDto, Long idUsuario)
            throws DependenteException {

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        validaDependente(usuario);

        if (criaDependenteDto.getRenda() >= RENDA_MIN_DEPENDENTE) {
            usuario.setSalarioLiquido(usuario.getSalarioLiquido() + criaDependenteDto.getRenda());
            this.usuarioRepository.save(usuario);
        }

        Dependente dependente = Dependente.fromCriacaoDto(criaDependenteDto, idUsuario);
        dependente.setIdUsuario(idUsuario);

        return this.dependenteRepository.save(dependente);
    }

    private void validaDependente(Usuario usuario) throws DependenteException {
        for (Dependente dependenteUsuario : usuario.getDependentes()) {

            if (dependenteUsuario.getParentesco().equals(Parentesco.CONJUGE)) {
                throw new DependenteException(MENSAGEM_EXCEPTION_CONJUGE);
            }
        }
    }

    public Dependente modificaDependente(DependenteDto dependenteDto, Long idUsuario,
            Long idDependente) {

        Dependente dependenteBanco = dependenteRepository.findById(idDependente).get();

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        dependenteBanco.setNome(dependenteDto.getNome());

        Float salarioAntigo = dependenteBanco.getRenda();
        dependenteBanco.setRenda(dependenteDto.getRenda());
        Float salarioAtual = dependenteBanco.getRenda();

        usuario.setSalarioLiquido(usuario.getSalarioLiquido() + salarioAtual - salarioAntigo);
        this.usuarioRepository.save(usuario);

        return this.dependenteRepository.save(dependenteBanco);
    }

    public DependenteDto buscaDependente(Long idUsuario, Long idDependente)
            throws DependenteException {

        Optional<Dependente> dependente =
                this.dependenteRepository.findByIdUsuarioAndIdDependente(idUsuario, idDependente);

        this.dependenteRepository.findByIdUsuarioAndIdDependente(idUsuario, idDependente)
                .orElseThrow(() -> new DependenteException(MENSAGEM_EXCEPTION_GENERICA));

        return DependenteDto.fromDto(dependente.get());
    }

    public List<DependenteDto> buscaDependentes(Long idUsuario, Optional<Parentesco> parentesco) {

        if (parentesco.isPresent()) {

            List<Dependente> listaDependente = this.dependenteRepository
                    .findAllByIdUsuarioAndParentesco(idUsuario, parentesco.get());
            return criaListaDto(listaDependente);
        }

        List<Dependente> listaDependente = this.dependenteRepository.findAllByIdUsuario(idUsuario);

        return criaListaDto(listaDependente);
    }

    private List<DependenteDto> criaListaDto(List<Dependente> listaDependente) {
        List<DependenteDto> listaDependenteDto = new ArrayList<DependenteDto>();

        for (Dependente dependente : listaDependente) {
            listaDependenteDto.add(DependenteDto.fromDto(dependente));
        }
        return listaDependenteDto;
    }

    public void deletaDependente(DependenteDto dependente, Long idUsuario, Long idDependente)
            throws DependenteException {

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        if (dependente.getRenda() >= RENDA_MIN_DEPENDENTE) {
            usuario.setSalarioLiquido(usuario.getSalarioLiquido() - dependente.getRenda());
            this.usuarioRepository.save(usuario);
        }

        this.dependenteRepository.findByIdUsuarioAndIdDependente(idUsuario, idDependente)
                .orElseThrow(() -> new DependenteException(MENSAGEM_EXCEPTION_GENERICA));

        this.dependenteRepository.deleteById(idDependente);
    }

}

