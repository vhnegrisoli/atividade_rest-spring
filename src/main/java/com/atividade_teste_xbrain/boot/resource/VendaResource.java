package com.atividade_teste_xbrain.boot.resource;

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

/*
 * A classe VendaResource é uma classe controladora responsável por 
 * mapear as URLs de acesso aos serviços REST de venda do projeto.
 * 
 * @author Victor Hugo Negrisoli
 * @version 1.0
 */

import org.springframework.web.bind.annotation.RestController;

import com.atividade_teste_xbrain.boot.domain.Venda;
import com.atividade_teste_xbrain.boot.service.VendaService;

@RestController
@RequestMapping("/api_vendas")
public class VendaResource {

	@Autowired
	VendaService vendaService;
	
	/*
	 * O método buscaTodos é responsável por buscar todos os objetos Venda no
	 * banco de dados através da instanciação de VendaService e retornar uma
	 * lista de objetos de Venda em formato JSON
	 * 
	 * @return List Venda Lista de objetos do tipo Venda a ser retornada com
	 * todos os objetos Venda que estiverem no banco de dados.
	 */
	
	@GetMapping("/vendas")
	public List<Venda> buscaTodos() {
		return vendaService.buscarTodos();
	}
	
	/*
	 * O método buscaUm é responsável por receber um parâmetro identificador do tipo inteiro e buscar
	 * na classe VendaService através do método findById() da JPA por um objeto específico e retornar
	 * para o controlador REST.
	 * 
	 * @param Integer id	Inteiro que representa o identificador a ser buscado
	 * @return Venda venda	Objeto do tipo Venda referenciado pelo id passado por parâmetro
	 */
	
	@RequestMapping(value = "/vendas/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscaUm (@PathVariable Integer id) {
		Optional<Venda> venda = vendaService.buscarUm(id);
		return ResponseEntity.ok().body(venda);
	}
	
	/*
	 * O método criaVenda é responsável por receber, via POST, uma requisição
	 * em formato JSON para criar um objeto Venda e inserir no banco de dados 
	 * através da instância de VendaService pelo método save.
	 * 
	 * @param Venda vendedor Objeto do tipo Venda recebido por uma requisição POST para
	 * inserir no banco de dados
	 * @return Venda vendedor Objeto do tipo venda a ser inserido no banco de dados
	 */
	
	@PostMapping("/venda")
	public Venda criaVenda(@RequestBody Venda venda) {
		return vendaService.inserir(venda);
	}
	
}
