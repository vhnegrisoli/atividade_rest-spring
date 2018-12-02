package com.atividade_teste_xbrain.boot.domain;

/*
 * A classe VendedorResult é responsável por receber os dados que estão definidos na consulta nativa
 * estabelecida em VendedorRepository para retornar os vendedores, a quantidade total de vendas
 * e a média diária estabelecida por dois parâmetros, uma data inicial e uma data final.
 * 
 * @author Victor Hugo Negrisoli
 * @version 1.0
 */

import javax.persistence.ColumnResult;
import javax.persistence.ConstructorResult;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SqlResultSetMapping;

@Entity
@SqlResultSetMapping(name = "VendedorResultSet", classes = {
		@ConstructorResult(targetClass = VendedorResultSet.class, columns = {
				@ColumnResult(name = "vendedor", type = String.class),
				@ColumnResult(name = "total", type = Integer.class),
				@ColumnResult(name = "media", type = Integer.class)}) })
public class VendedorResultSet {
	private String vendedor;
	@Id
	private int total;
	private double media;

	public VendedorResultSet(String vendedor, int total, double media) {
		super();
		this.vendedor = vendedor;
		this.total = total;
		this.media = media;
	}

	public String getVendedor() {
		return vendedor;
	}

	public void setVendedor(String vendedor) {
		this.vendedor = vendedor;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public double getMedia() {
		return media;
	}

	public void setMedia(double media) {
		this.media = media;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(media);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + total;
		result = prime * result + ((vendedor == null) ? 0 : vendedor.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VendedorResultSet other = (VendedorResultSet) obj;
		if (Double.doubleToLongBits(media) != Double.doubleToLongBits(other.media))
			return false;
		if (total != other.total)
			return false;
		if (vendedor == null) {
			if (other.vendedor != null)
				return false;
		} else if (!vendedor.equals(other.vendedor))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "VendedorResultSet [vendedor=" + vendedor + ", total=" + total + ", media=" + media + "]";
	}
}
