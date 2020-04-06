package br.com.zup.estrelas.financas.controller;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import br.com.zup.estrelas.financas.entity.ExampleEntity;
import br.com.zup.estrelas.financas.service.ExampleService;

@RestController
@RequestMapping("/examples")
public class ExampleController {

    @Autowired
    ExampleService service;
    
    @PostMapping
    public ExampleEntity insert(@RequestBody ExampleEntity entity) {
        return service.insereExample(entity);
    }
    
    @GetMapping
    public List<ExampleEntity> findAll(@RequestParam Optional<String> text) {
        return service.findAll(text);
    }

}
