package br.com.drogaria.test;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.com.drogaria.dao.ProdutoDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.domain.Produto;

public class ProdutoDAOTeste {
	
	public void salvar() throws SQLException {
		Produto p = new Produto();
		p.setDescricao("Teste");
		p.setPreco(2.45);
		p.setQuantidade(13L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(19L);
		
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();		
		dao.salvar(p);
	}
	
	
	public void listar() throws SQLException{
		ProdutoDAO dao = new ProdutoDAO();
		
		ArrayList<Produto> lista = dao.listar();
		
		for(Produto p : lista) {
			System.out.println("Codigo: " + p.getCodigo());
			System.out.println("Descrição: " + p.getDescricao());
			System.out.println("Preço: " + p.getPreco());
			System.out.println("Quantidade: " + p.getQuantidade());
			System.out.println("Código do Fabricante: " + p.getFabricante().getCodigo());
			System.out.println("Descrição do Fabricante: " + p.getFabricante().getDescricao());
			System.out.println("");
		}
	}
	
	
	public void excluir() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(4L);
		
		ProdutoDAO dao = new ProdutoDAO();
		dao.excluir(p);
		
	}
	
	@Test
	public void editar() throws SQLException{
		Produto p = new Produto();
		p.setCodigo(5L);
		p.setDescricao("cataflan pomada 60gr");
		p.setPreco(22.50);
		p.setQuantidade(30L);
		
		Fabricante f = new Fabricante();
		f.setCodigo(19L);
		p.setFabricante(f);
		
		ProdutoDAO dao = new ProdutoDAO();
		
		dao.editar(p);
	}
}
