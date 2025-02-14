package com.example.restaurantedanice.service;

import com.example.restaurantedanice.domain.comida.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ComidaService {

    @Autowired
    private ComidaRepository repository;

    //C.R.U.D

    public DadosDetalhamentoComida cadastrarComida(DadosCadastroComida dados){

        var comida = new Comida(dados);
        repository.save(comida);
        var dadosDetalhamentoComida = new DadosDetalhamentoComida(comida);
        return dadosDetalhamentoComida;

    }

    public Page<DadosListagemComida> listarComidas(Pageable pageable){

        var page = repository.findAll(pageable).map(DadosListagemComida::new);
        return page;

    }

    public DadosDetalhamentoComida detalharComida(Long id){

        var comida = repository.getReferenceById(id);
        var dadosDetalhamentoComida = new DadosDetalhamentoComida(comida);
        return dadosDetalhamentoComida;

    }

    public void atualizarComida(DadosAtualizacaoComida dados){

        var comida = repository.getReferenceById(dados.id());
        comida.atualizarDados(dados);

    }

    public void excluirComida(Long id) throws EntityNotFoundException{
        var comida = repository.getReferenceById(id);
        if (comida.equals(null)){
            throw new EntityNotFoundException();
        }
        repository.delete(comida);
    }



}
