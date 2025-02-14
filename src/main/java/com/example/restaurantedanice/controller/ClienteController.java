package com.example.restaurantedanice.controller;

import com.example.restaurantedanice.domain.cliente.*;
import com.example.restaurantedanice.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados){

        var dadosDetalhamentoCliente = service.cadastrarCliente(dados);
        return ResponseEntity.status(HttpStatus.CREATED).body(dadosDetalhamentoCliente);

    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> getAll(@PageableDefault(size = 10, sort ={"nome"}) Pageable pageable){
        var page = service.listarClientes(pageable);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id){
        var dadosDetalhamentoCliente = service.detalharCliente(id);
        return ResponseEntity.ok(dadosDetalhamentoCliente);
    }

    @PutMapping
    public ResponseEntity editar(@RequestBody DadosAtualizacaoCliente dados){

        service.editarCliente(dados);
        return ResponseEntity.ok("Dados atualizados!");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity excluir(@PathVariable Long id){

        service.excluirCliente(id);
        return ResponseEntity.noContent().build();

    }

}


