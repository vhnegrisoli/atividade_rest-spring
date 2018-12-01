package com.atividade_teste_xbrain.boot.repository;

/*
 * A interface VendedorRepository tem como objetivo realizar as operações de CRUD com o banco
 * de dados configurado. Operações de FindById, findAll, save, entre outras.
 *
 *@author Victor Hugo Negrisoli
 *@version 1.0
 *
 */

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.atividade_teste_xbrain.boot.domain.Venda;

@Repository
public interface VendaRepository extends JpaRepository<Venda, Integer> {

}
