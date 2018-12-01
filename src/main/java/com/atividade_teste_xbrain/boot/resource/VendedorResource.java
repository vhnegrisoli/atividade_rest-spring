package com.atividade_teste_xbrain.boot.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*
 * A classe VendedorResource é uma classe controladora responsável por 
 * mapear as URLs de acesso aos serviços REST do projeto.
 * 
 * @author Victor Hugo Negrisoli
 * @version 1.0
 */

import org.springframework.web.bind.annotation.RestController;

import com.atividade_teste_xbrain.boot.domain.Vendedor;
import com.atividade_teste_xbrain.boot.service.VendedorService;

@RestController
@RequestMapping(value = "/api_vendedor")
public class VendedorResource {

	@Autowired
	VendedorService vendedorService;
	/*
	 * O método buscaTodos é responsável por buscar todos os objetos Vendedor no banco de dados
	 * através da instanciação de VendedorService e retornar uma lista de objetos de Vendedores em
	 * formato JSON para a URL /api_vendedor/vendedores.
	 * 
	 * @return List Vendedor Lista de objetos do tipo Vendedor a ser retornada com todos os objetos
	 * Vendedor que estiverem no banco de dados.
	 */
	@GetMapping("/vendedores")
	public List<Vendedor> buscaTodos() {
		return vendedorService.buscarTodos();
	}
	
}
