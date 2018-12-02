package com.atividade_teste_xbrain.boot.repository;

/*
 * A interface VendedorRepository tem como objetivo realizar as operações de CRUD com o banco
 * de dados configurado. Operações de FindById, findAll, save, entre outras.
 *
 *@author Victor Hugo Negrisoli
 *@version 1.0
 *
 */


import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.atividade_teste_xbrain.boot.domain.Vendedor;

@Repository
public interface VendedorRepository extends JpaRepository<Vendedor, Integer> {
	
	
	/*
	 * O método vendedoresQuery é responsável por, através de uma consulta nativa
	 * estabelecida, receber dois parâmetros de data e buscar os vendedores, a quantidade
	 * total de vendas e a média de vendas diária entre os períodos, e adicioná-los a uma
	 * lista do tipo Object, que será armazenado pela classe VendedorResultSet.
	 * 
	 * @param String data1 String que representa a data inicial a ser buscada na consulta
	 * @param String data2 String que representa a data final a ser buscada na consulta
	 * @return List<Object> object Lista do tipo Object que corresponde aos dados retornados
	 * pela consulta.
	 */
	
	@Query(value = "SELECT v.nome_vendedor AS vendedor, count(vd.id_venda) AS total,"
			+ " CAST(COUNT(vd.ID_VENDA) / DATEDIFF('day', ?1, ?2) as NUMERIC(7,2)) AS media"
			+ " FROM Vendedor v LEFT JOIN Venda vd "
			+ " ON v.id_vendedor = vd.id_vendedor "
			+ " WHERE vd.DATA_VENDA BETWEEN ?1 AND ?2"
			+ " GROUP BY v.nome_vendedor", nativeQuery = true)
	public List<Object> vendedoresQuery(String data1, String data2);
	
}
