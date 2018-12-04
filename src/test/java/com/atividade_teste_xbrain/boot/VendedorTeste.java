package com.atividade_teste_xbrain.boot;

/*
 * A classe VendedorTeste é responsável por testar alguns dos principais métodos da
 * API desenvolvida para vendedor. O teste é feito com os métodos buscaTodos, buscaUm e 
 * consultaPersonalizada.
 * 
 * @author Victor Hugo Negrisoli
 * @version 1.0
 */


import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.atividade_teste_xbrain.boot.domain.Venda;
import com.atividade_teste_xbrain.boot.domain.Vendedor;
import com.atividade_teste_xbrain.boot.repository.VendaRepository;
import com.atividade_teste_xbrain.boot.repository.VendedorRepository;

@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
public class VendedorTeste {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private VendedorRepository vendedorRepository;

	@Autowired
	private VendaRepository vendaRepository;
	
	/*
	 * O método testeFindAll é responsável por testar o método findAll de VendedorRepository
	 * e verificar se a criação e retorno dos objetos do tipo Vendedor estão sendo retornados
	 * da maneira esperada.
	 */
	
	@Test
	public void testeFindAll() {
		Vendedor vendedor = new Vendedor();
		vendedor.setNomeVendedor("Victor Hugo Negrisoli");
		entityManager.persist(vendedor);
		entityManager.flush();
		Vendedor vendedor2 = new Vendedor();
		vendedor2.setNomeVendedor("Rafael Nonino");
		entityManager.persist(vendedor2);
		entityManager.flush();
		List<Vendedor> vendedores = vendedorRepository.findAll();
		assertThat(vendedores.size()).isEqualTo(2);
		assertThat(vendedores.get(0)).isEqualTo(vendedor);
		assertThat(vendedores.get(1)).isEqualTo(vendedor2);
	}

	/*
	 * O método testeFindbyId é responsável por testar o método findById de VendedorRepository
	 * e verificar se a criação e retorno de um dos objetos do tipo Vendedor está sendo retornado
	 * da maneira esperada.
	 */
	
	@Test
	public void testeFindById() {
		Vendedor vendedor = new Vendedor();
		vendedor.setNomeVendedor("Victor Hugo Negrisoli");
		entityManager.persist(vendedor);
		entityManager.flush();
		Optional<Vendedor> vendedor2 = vendedorRepository.findById(vendedor.getIdVendedor());
		assertThat(vendedor2.get().getNomeVendedor()).isEqualTo(vendedor.getNomeVendedor());
	}

	/*
	 * O método testeConsultaPersonalizada é responsável por testar o método vendedoresQuery de VendedorRepository
	 * e verificar se a consulta executada e retorno dos campos do objeto do tipo Vendedor estão sendo retornados
	 * da maneira esperada. É feita uma solicitação de uma consulta e retorna-se um objeto do tipo
	 * Object, que é convertido em um arquivo JSON e comparado com o resultado esperado.
	 */
	
	@Test
	public void testeConsultaPersonalizada() throws JsonGenerationException, JsonMappingException, IOException {
		Vendedor vendedor = new Vendedor();
		vendedor.setNomeVendedor("Victor Hugo Negrisoli");

		Venda venda = new Venda();
		Venda venda2 = new Venda();
		Venda venda3 = new Venda();
		Venda venda4 = new Venda();
		Venda venda5 = new Venda();
		Venda venda6 = new Venda();
		Venda venda7 = new Venda();

		venda.setDataVenda("2018-05-01");
		venda2.setDataVenda("2018-05-01");
		venda3.setDataVenda("2018-05-02");
		venda4.setDataVenda("2018-05-03");
		venda5.setDataVenda("2018-05-03");
		venda6.setDataVenda("2018-05-03");
		venda7.setDataVenda("2018-05-03");
		venda.setIdVendedor(vendedor);
		venda2.setIdVendedor(vendedor);
		venda3.setIdVendedor(vendedor);
		venda4.setIdVendedor(vendedor);
		venda5.setIdVendedor(vendedor);
		venda6.setIdVendedor(vendedor);
		venda7.setIdVendedor(vendedor);

		entityManager.persist(vendedor);
		entityManager.persist(venda);
		entityManager.persist(venda2);
		entityManager.persist(venda3);
		entityManager.persist(venda4);
		entityManager.persist(venda5);
		entityManager.persist(venda6);
		entityManager.persist(venda7);
		entityManager.flush();

		String data_inicial = "2018-05-01";
		String data_final = "2018-05-03";
		List<Object> resultadoAPI = vendedorRepository.vendedoresQuery(data_inicial, data_final);
		ObjectMapper mapper = new ObjectMapper();
		mapper.writeValue(new File("file.json"), resultadoAPI);
		File jsonFile = new File("file.json");
		BufferedReader lerJson = new BufferedReader(new FileReader(jsonFile));
		String jsonString = "";
		String compare = "";
		while ((jsonString = lerJson.readLine()) != null) {
			compare = jsonString;
		}
		// Esperado:
		// Vendedor = Victor Hugo Negrisoli
		// Quantidade = 7
		// Media = 3

		assertThat(compare.trim()).isEqualTo("[[\"Victor Hugo Negrisoli\",7,3.00]]");
	}

}
