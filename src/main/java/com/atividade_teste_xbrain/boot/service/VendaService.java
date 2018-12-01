package com.atividade_teste_xbrain.boot.service;

/*
 * A classe VendedaService tem como objetivo oferecer um serviço de acesso aos objetos de banco 
 * de dados das interfaces Repository que são especificadas e mapeadas pela JPA.
 * 
 * @author Victor Hugo Negrisoli
 * @version 1.0
 * 
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atividade_teste_xbrain.boot.domain.Venda;
import com.atividade_teste_xbrain.boot.repository.VendaRepository;

@Service
public class VendaService {
	
	@Autowired
	VendaRepository vendaRepository;
	
	/*
	 * O método buscar é responsável por retornar todos os o objetos do tipo
	 * Venda que estão no banco de dados através da instanciação de
	 * vendaRepository.
	 * 
	 * @return List vendedor Lista de objetos do tipo Venda contendo todos os
	 * objetos de Venda que estão no banco de dados
	 */

	public List<Venda> buscarTodos() {
		return vendaRepository.findAll();
	}

	/*
	 * O método buscarUm é responsável por retornar um objeto do tipo
	 * Venda com base no parâmetro identificado Integer id através da
	 * instanciação de vendaRepository.
	 * 
	 * @param Integer id Inteiro representando a chave primária de um objeto do banco de dados
	 * @return Venda venda Objeto do tipo Venda com base no identificador passado como parâmetro
	 */
	
	public Optional<Venda> buscarUm(Integer id) {
		return vendaRepository.findById(id);
	}

	/*
	 * O método inserir é responsável por criar um objeto do tipo
	 * Venda no banco de dados através da instanciação de
	 * vendaRepository.
	 * 
	 * @param Venda venda Objeto do tipo Venda passado como parâmetro para ser inserido no banco 
	 * de dados
	 * @return Venda venda Objeto do tipo Venda que será inserido no banco de dados
	 */
	
	public Venda inserir(Venda venda) {
		return vendaRepository.save(venda);
	}
	
}
