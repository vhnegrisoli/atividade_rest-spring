package com.atividade_teste_xbrain.boot;

/*
 * A classe VendaTeste é responsável por testar alguns dos principais métodos da
 * API desenvolvida para vendedor. O teste é feito com os métodos buscaTodos e buscaUm,
 * testando também seus métodos de inserção no banco de dados.
 * 
 * @author Victor Hugo Negrisoli
 * @version 1.0
 */


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.atividade_teste_xbrain.boot.domain.Venda;
import com.atividade_teste_xbrain.boot.domain.Vendedor;
import com.atividade_teste_xbrain.boot.repository.VendaRepository;
import com.atividade_teste_xbrain.boot.repository.VendedorRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class VendaTeste {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private VendaRepository vendaRepository;

	/*
	 * O método testeFindAll é responsável por testar o método findAll de
	 * VendaRepository e verificar se a criação e retorno dos objetos do tipo
	 * Venda estão sendo retornados da maneira esperada.
	 */

	@Test
	public void testeFindAll() {
		Vendedor vendedor = new Vendedor();
		vendedor.setNomeVendedor("Victor Hugo Negrisoli");
		Venda venda = new Venda();
		Venda venda2 = new Venda();
		venda.setDataVenda("2018-05-01");
		venda2.setDataVenda("2018-12-01");
		venda.setIdVendedor(vendedor);
		venda2.setIdVendedor(vendedor);
		entityManager.persist(vendedor);
		entityManager.persist(venda);
		entityManager.persist(venda2);
		entityManager.flush();

		List<Venda> vendas = vendaRepository.findAll();
		assertThat(vendas.size()).isEqualTo(2);
		assertThat(vendas.get(0)).isEqualTo(venda);
		assertThat(vendas.get(1)).isEqualTo(venda2);
	}

	/*
	 * O método testeFindbyId é responsável por testar o método findById de
	 * VendaRepository e verificar se a criação e retorno de um dos objetos do
	 * tipo Venda está sendo retornado da maneira esperada.
	 */

	@Test
	public void testeFindById() {
		Vendedor vendedor = new Vendedor();
		vendedor.setNomeVendedor("Victor Hugo Negrisoli");
		
		Venda venda = new Venda();
		venda.setDataVenda("2018-05-01");
		venda.setIdVendedor(vendedor);
		
		entityManager.persist(vendedor);
		entityManager.persist(venda);
		entityManager.flush();
		
		Optional<Venda> vendaRetornada = vendaRepository.findById(1);
		
		assertThat(vendaRetornada.get().getDataVenda()).isEqualTo(venda.getDataVenda());
		assertThat(vendaRetornada.get().getDataVenda()).isEqualTo("2018-05-01");		
		assertThat(vendaRetornada.get().getIdVendedor().getNomeVendedor()).isEqualTo(vendedor.getNomeVendedor());		
		
	}
}
