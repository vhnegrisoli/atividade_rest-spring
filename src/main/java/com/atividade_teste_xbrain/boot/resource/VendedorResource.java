package com.atividade_teste_xbrain.boot.resource;

/*
 * A classe VendedorResource é uma classe controladora responsável por 
 * mapear as URLs de acesso aos serviços REST do projeto.
 * 
 * @author Victor Hugo Negrisoli
 * @version 1.0
 */

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;

import com.atividade_teste_xbrain.boot.domain.Vendedor;
import com.atividade_teste_xbrain.boot.service.VendedorService;

@RestController
@RequestMapping(value = "/api_vendedor")
public class VendedorResource {

	@Autowired
	VendedorService vendedorService;

	/*
	 * O método buscaTodos é responsável por buscar todos os objetos Vendedor no
	 * banco de dados através da instanciação de VendedorService e retornar uma
	 * lista de objetos de Vendedores em formato JSON para a URL
	 * /api_vendedor/vendedores.
	 * 
	 * @return List Vendedor Lista de objetos do tipo Vendedor a ser retornada com
	 * todos os objetos Vendedor que estiverem no banco de dados.
	 */

	@GetMapping("/vendedores")
	public List<Vendedor> buscaTodos() {
		return vendedorService.buscarTodos();
	}

	/*
	 * O método buscaUm é responsável por receber um parâmetro identificador do tipo inteiro e buscar
	 * na classe VendedorService através do método findById() da JPA por um objeto específico e retornar
	 * para o controlador REST.
	 * 
	 * @param Integer id	Inteiro que representa o identificador a ser buscado
	 * @return Vendedor vendedor	Objeto do tipo Vendedor referenciado pelo id passado por parâmetro
	 */
	@RequestMapping(value = "/vendedores/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscaUm(@PathVariable Integer id) {
		Optional<Vendedor> vendedor = vendedorService.buscarUm(id);
		return ResponseEntity.ok().body(vendedor);
	}
	
	/*
	 * O método criaVendedor é responsável por receber, via POST, uma requisição
	 * em formato JSON para criar um objeto Vendedor e inserir no banco de dados 
	 * através da instância de VendedorService pelo método save.
	 * 
	 * @param Vendedor vendedor Objeto do tipo Vendedor recebido por uma requisição POST para
	 * inserir no banco de dados
	 * @return Vendedor vendedor Objeto do tipo vendedor a ser inserido no banco de dados
	 */
	@PostMapping("/vendedor")
	public Vendedor criaVendedor(@RequestBody Vendedor vendedor) {
		return vendedorService.inserir(vendedor);
	}

}
