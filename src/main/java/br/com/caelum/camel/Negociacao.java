package br.com.caelum.camel;

import java.util.Date;

public class Negociacao {

	private double preco;
    private int quantidade;
    private Date data;
    
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	public int getQuantidade() {
		return quantidade;
	}
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
    
	@Override
    public String toString() {
        return "Negociacao [preco=" + preco + ", quantidade=" + quantidade + "]";
    }
}
