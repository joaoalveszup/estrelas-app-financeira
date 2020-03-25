package br.com.zup.estrelas.financas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
