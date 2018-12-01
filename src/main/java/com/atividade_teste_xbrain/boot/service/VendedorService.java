package com.atividade_teste_xbrain.boot.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

/*
 * A classe VendedorService tem como objetivo oferecer um serviço de acesso aos objetos de banco 
 * de dados das interfaces Repository que são especificadas e mapeadas pela JPA.
 * 
 * @author Victor Hugo Negrisoli
 * @version 1.0
 * 
 */

import org.springframework.stereotype.Service;

import com.atividade_teste_xbrain.boot.domain.Vendedor;
import com.atividade_teste_xbrain.boot.repository.VendedorRepository;

@Service
public class VendedorService {
	
	@Autowired
	private VendedorRepository vendedorRepository;
	
	/*
	 * O método buscar é responsável por retornar todos os o objetos
	 * do tipo Vendedor que estão no banco de dados através da instanciação de
	 * vendedorRepository.
	 * 
	 * @Return List vendedor Lista de objetos do tipo Vendedor contendo todos os objetos de Vendedor
	 * que estão no banco de dados
	 */
	
	public List<Vendedor> buscarTodos() {
		return vendedorRepository.findAll();
	}
	
}
