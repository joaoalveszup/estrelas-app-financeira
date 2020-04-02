package br.com.zup.estrelas.financas.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.exceptions.DependentesException;
import br.com.zup.estrelas.financas.dto.CriaDependenteDto;
import br.com.zup.estrelas.financas.dto.DependenteDto;
import br.com.zup.estrelas.financas.entity.Dependente;
import br.com.zup.estrelas.financas.entity.Usuario;
import br.com.zup.estrelas.financas.enums.Parentesco;
import br.com.zup.estrelas.financas.repository.DependenteRepository;
import br.com.zup.estrelas.financas.repository.UsuarioRepository;

@Service
public class DependenteService {

    private static final String MENSAGEM_EXCEPTION_CONJUGE =
            "Já existe um cônjuge na sua lista de dependentes. Por favor, insira outro parentesco!";

    private static final String MENSAGEM_EXCEPTION_GENERICA =
            " Esse dependente não corresponde ao usuário informado! ";

    private static final int RENDA_MIN_DEPENDENTE = 1;

    @Autowired
    DependenteRepository dependenteRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    public Dependente insereDependente(CriaDependenteDto criaDependenteDto, Long idUsuario)
            throws DependentesException {

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        for (Dependente dependenteUsuario : usuario.getDependentes()) {

            if (dependenteUsuario.getParentesco().equals(Parentesco.CONJUGE)) {
                throw new DependentesException(MENSAGEM_EXCEPTION_CONJUGE);
            }
        }

        if (criaDependenteDto.getRenda() >= RENDA_MIN_DEPENDENTE) {
            usuario.setSalarioLiquido(usuario.getSalarioLiquido() + criaDependenteDto.getRenda());
            this.usuarioRepository.save(usuario);
        }

        Dependente dependenteEntidade = Dependente.fromCriacaoDto(criaDependenteDto, idUsuario);
        dependenteEntidade.setIdUsuario(idUsuario);

        return this.dependenteRepository.save(dependenteEntidade);
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
            throws DependentesException {

        Dependente dependente = this.dependenteRepository
                .findByIdUsuarioAndIdDependente(idUsuario, idDependente).get();

        this.dependenteRepository.findByIdUsuarioAndIdDependente(idUsuario, idDependente)
                .orElseThrow(() -> new DependentesException(MENSAGEM_EXCEPTION_GENERICA));

        return DependenteDto.fromDto(dependente);
    }

    public List<DependenteDto> buscaDependentes(Long idUsuario) {

        List<Dependente> listaDependente = this.dependenteRepository.findAllByIdUsuario(idUsuario);
        List<DependenteDto> listaDependenteDto = new ArrayList<DependenteDto>();

        for (Dependente dependente : listaDependente) {
            listaDependenteDto.add(DependenteDto.fromDto(dependente));
        }
        return listaDependenteDto;
    }

    public void deletaDependente(DependenteDto dependente, Long idUsuario, Long idDependente)
            throws DependentesException {

        Usuario usuario = usuarioRepository.findById(idUsuario).get();

        if (dependente.getRenda() >= RENDA_MIN_DEPENDENTE) {
            usuario.setSalarioLiquido(usuario.getSalarioLiquido() - dependente.getRenda());
            this.usuarioRepository.save(usuario);
        }

        this.dependenteRepository.findByIdUsuarioAndIdDependente(idUsuario, idDependente)
                .orElseThrow(() -> new DependentesException(MENSAGEM_EXCEPTION_GENERICA));

        this.dependenteRepository.deleteById(idDependente);
    }

    public List<DependenteDto> buscaDependentePorParentesco(Long idUsuario, Parentesco parentesco) {

        List<Dependente> listaDependentePorParentesco =
                this.dependenteRepository.findByIdUsuarioAndParentesco(idUsuario, parentesco);
        List<DependenteDto> listaDependenteDto = new ArrayList<DependenteDto>();

        for (Dependente dependente : listaDependentePorParentesco) {
            listaDependenteDto.add(DependenteDto.fromDto(dependente));
        }

        return listaDependenteDto;
    }
}
