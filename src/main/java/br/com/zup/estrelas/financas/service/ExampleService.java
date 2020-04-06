package br.com.zup.estrelas.financas.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import br.com.zup.estrelas.financas.entity.ExampleEntity;
import br.com.zup.estrelas.financas.repository.ExampleRepository;

@Service
public class ExampleService {

    @Autowired
    ExampleRepository repository;
    
    public ExampleEntity insereExample(ExampleEntity entity) {
        
        // Não é permitido inserir entidades com um texto que tenha um 
        // tamanho maior do que 200
        if (entity.getExampleText().length() > 200) {
            return null;
        }
        
        return repository.save(entity);
    }
    
    public List<ExampleEntity> findAll(Optional<String> text) {
        
        if (text.isPresent()) {
            return repository.findAllByExampleTextStartingWith(text.get());
        }
        
        return this.repository.findAll();
    }
    
}
