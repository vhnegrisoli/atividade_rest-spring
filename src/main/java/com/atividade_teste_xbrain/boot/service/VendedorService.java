package com.atividade_teste_xbrain.boot.service;

/*
 * A classe VendedorService tem como objetivo oferecer um serviço de acesso aos objetos de banco 
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
import com.atividade_teste_xbrain.boot.domain.Vendedor;
import com.atividade_teste_xbrain.boot.repository.VendedorRepository;

@Service
public class VendedorService {

	@Autowired
	private VendedorRepository vendedorRepository;

	/*
	 * O método buscar é responsável por retornar todos os o objetos do tipo
	 * Vendedor que estão no banco de dados através da instanciação de
	 * vendedorRepository.
	 * 
	 * @return List vendedor Lista de objetos do tipo Vendedor contendo todos os
	 * objetos de Vendedor que estão no banco de dados
	 */

	public List<Vendedor> buscarTodos() {
		return vendedorRepository.findAll();
	}

	/*
	 * O método buscarUm é responsável por retornar um objeto do tipo
	 * Vendedor com base no parâmetro identificado Integer id através da
	 * instanciação de vendedorRepository.
	 * 
	 * @param Integer id Inteiro representando a chave primária de um objeto do banco de dados
	 * @return Vendedor vendedor Objeto do tipo Vendedor com base no identificador passado como parâmetro
	 */
	
	public Optional<Vendedor> buscarUm(Integer id) {
		return vendedorRepository.findById(id);
	}

	/*
	 * O método inserir é responsável por criar um objeto do tipo
	 * Vendedor no banco de dados através da instanciação de
	 * vendedorRepository.
	 * 
	 * @param Vendedor vendedor Objeto do tipo Vendedor passado como parâmetro para ser inserido no banco 
	 * de ddados
	 * @return Vendedor vendedor Objeto do tipo Vendedor que será inserido no banco de dados
	 */
	
	public Vendedor inserir(Vendedor vendedor) {
		return vendedorRepository.save(vendedor);
	}
	
	/*
	 * O método consultaPersonalizada é responsável por receber duas Strings que representam
	 * uma data inicial e uma data final e retornar, através do acesso à classe VendedorRepository,
	 * pelo método vendedoresQuery, uma lista do tipo Object que representa os vendedores, a quantidade
	 * total de vendas e a média diária com base nos parâmetros determinados.
	 * 
	 * @param String data1 String que representa a data inicial a ser buscada na consulta
	 * @param String data2 String que representa a data final a ser buscada na consulta
	 * @return List<Object> object Lista do tipo Object que corresponde aos dados retornados
	 * pela consulta.
	 */
	
	public List<Object> consultaPersonalizada(String data1, String data2) {
		return vendedorRepository.vendedoresQuery(data1, data2);
	}
}
