package br.com.drogaria.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.factory.ConexaoFactory;

public class FabricanteDAO {
	public void salvar(Fabricante f) throws SQLException {
		
		// Ao invés de usar String, usamos StringBuilder, pois essa não precisa concatenar strings com "+". Afeta apenas na aparencia do código.
		StringBuilder sql = new StringBuilder();
		
		// Criação do comando SQL - sempre deixar espaço antes de fechar aspas
		sql.append("insert into fabricante ");
		sql.append("(descricao) ");
		sql.append("values (?) ");
		
		// Abre conexão
		Connection conexao = ConexaoFactory.conectar();		
		
		// Cria PreparedStatement para executar os comandos e converte o sql para string, ja que estava como StringBuilder
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		//Atribui para o "?" o valor f passado no parâmetro, o 1 significa que é no primeiro "?" da classe
		comando.setString(1, f.getDescricao());
		
		// Executa comando sql
		comando.executeUpdate();		
	}
	
	public void excluir(Fabricante f) throws SQLException {
		
		// Ao invés de usar String, usamos StringBuilder, pois essa não precisa concatenar strings com "+". Afeta apenas na aparencia do código.
		StringBuilder sql = new StringBuilder();
			
		// Criação do comando SQL - sempre deixar espaço antes de fechar aspas
		sql.append("delete from fabricante ");
		sql.append("where codigo = ? ");			
			
		// Abre conexão
		Connection conexao = ConexaoFactory.conectar();		
		
		// Cria PreparedStatement para executar os comandos e converte o sql para string, ja que estava como StringBuilder
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		//Atribui para o "?" o valor f passado no parâmetro, o 1 significa que é no primeiro "?" da classe
		comando.setLong(1, f.getCodigo());;
		
		// Executa comando sql
		comando.executeUpdate();
	}
	
	public void editar(Fabricante f) throws SQLException{
		// Ao invés de usar String, usamos StringBuilder, pois essa não precisa concatenar strings com "+". Afeta apenas na aparencia do código.
		StringBuilder sql = new StringBuilder();
		
		// Criação do comando SQL - sempre deixar espaço antes de fechar aspas
		sql.append("update fabricante ");
		sql.append("set descricao = ? ");	
		sql.append("where codigo = ? ");	
		
		// Abre conexão
		Connection conexao = ConexaoFactory.conectar();		
		
		// Cria PreparedStatement para executar os comandos e converte o sql para string, ja que estava como StringBuilder
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		//Atribui para o "?" o valor f passado no parâmetro, o 1 significa que é no primeiro "?" da classe e o 2 significa a segunda "?"
		comando.setString(1, f.getDescricao());
		comando.setLong(2, f.getCodigo());
		
		// Executa comando sql
		comando.executeUpdate();
		
	}
	
	public Fabricante buscarPorCodigo(Fabricante f) throws SQLException {
		// Ao invés de usar String, usamos StringBuilder, pois essa não precisa concatenar strings com "+". Afeta apenas na aparencia do código.
		StringBuilder sql = new StringBuilder();
		
		// Criação do comando SQL - sempre deixar espaço antes de fechar aspas
		sql.append("select codigo, descricao ");
		sql.append("from fabricante ");	
		sql.append("where codigo = ? ");
		
		// Abre conexão
		Connection conexao = ConexaoFactory.conectar();	
		
		// Cria PreparedStatement para executar os comandos e converte o sql para string, ja que estava como StringBuilder
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		//Atribui para o "?" o valor f passado no parâmetro, o 1 significa que é no primeiro "?" da classe e o 2 significa a segunda "?"		
		comando.setLong(1, f.getCodigo());
		
		// Executa comando sql		
		ResultSet resultado = comando.executeQuery();
		
		Fabricante retorno = null;
		
		if(resultado.next()) {
			retorno = new Fabricante();
			retorno.setCodigo(resultado.getLong("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		return retorno;
	}
	
	public ArrayList<Fabricante> listar() throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("select codigo, descricao ");
		sql.append("from fabricante ");
		sql.append("order by descricao asc ");
		
		// Abre conexão
		Connection conexao = ConexaoFactory.conectar();	
		
		//criado para fazer comandos no banco de dados
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		//Criado para armazenar o resultado da Query, pois nesse caso retorna algo do banco de dados
		ResultSet resultado = comando.executeQuery();
		
		//Cria uma lista de Fabricante para receber do dados vindo do banco
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
		
		//Percorre todos os resultados
		while(resultado.next()) {			
			Fabricante f = new Fabricante();
			//f recebe os dados das colunas vindas do banco
			f.setCodigo(resultado.getLong("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			//add os dados na lista
			lista.add(f);
		}
		return lista;
	}
	
	public ArrayList<Fabricante> buscarPorDescricao(Fabricante f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		
		sql.append("select codigo, descricao ");
		sql.append("from fabricante ");
		sql.append("where descricao like ? ");
		sql.append("order by descricao asc ");
		
		// Abre conexão
		Connection conexao = ConexaoFactory.conectar();	
		
		//criado para fazer comandos no banco de dados
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		//Atribui para o "?" o valor f passado no parâmetro, o 1 significa que é no primeiro "?" da classe e o 2 significa a segunda "?"		
		comando.setString(1, "%" + f.getDescricao() + "%");
		
		//Criado para armazenar o resultado da Query, pois nesse caso retorna algo do banco de dados
				ResultSet resultado = comando.executeQuery();
				
		//Cria uma lista de Fabricante para receber do dados vindo do banco
		ArrayList<Fabricante> lista = new ArrayList<Fabricante>();
				
		//Percorre todos os resultados
		while(resultado.next()) {			
			Fabricante item = new Fabricante();
			//f recebe os dados das colunas vindas do banco
			item.setCodigo(resultado.getLong("codigo"));
			item.setDescricao(resultado.getString("descricao"));
					
			//add os dados na lista
			lista.add(item);
		}
		return lista;
	}
	
	public static void main(String[] args) {
		/*
		//Testando o método salvar
		Fabricante f1 = new Fabricante();
		Fabricante f2 = new Fabricante();
		
		f1.setDescricao("Descricao 8");
		f2.setDescricao("Descricao 9");
		
		FabricanteDAO fdao = new FabricanteDAO();
		try {
		fdao.salvar(f1);
		fdao.salvar(f2);
		System.out.println("Fabricantes foram salvos com sucesso");
		}catch(SQLException e) {
			e.printStackTrace();
			System.out.println("Ocorreu erro ao salvar um dos fabricantes");
		}
		*/
		
		//---------------------------------------------------------------------------
		
		/*		 
		//Testando o método excluir
		Fabricante f1 = new Fabricante();
		f1.setCodigo(2L);
		
		Fabricante f2 = new Fabricante();
		f2.setCodigo(1L);
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			fdao.excluir(f1);
			fdao.excluir(f2);
			System.out.println("Excluido com sucesso!");
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("Falha ao excluir");
		}
		*/
		
		//---------------------------------------------------------------------------
		
		/*
		//Testando método editar
		Fabricante f1 = new Fabricante();
		
		f1.setCodigo(3L);
		f1.setDescricao("Descricao 3 Mahoi");
		
		FabricanteDAO fdao = new FabricanteDAO();
		try {
			fdao.editar(f1);
			System.out.println("Alteração realizada com sucesso!");
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("Falha ao editar fabricante");
		}
		*/
		
		//---------------------------------------------------------------------------
		
		/*
		//Teste de consulta BuscarporCodigo		
		Fabricante f1 = new Fabricante();
		f1.setCodigo(3L);
		
		Fabricante f2 = new Fabricante();
		f2.setCodigo(7L);
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			Fabricante f3 = fdao.buscarPorCodigo(f1);
			Fabricante f4 = fdao.buscarPorCodigo(f2);
			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);
		} catch (SQLException e) {			
			e.printStackTrace();
			System.out.println("Ocorreu erro ao tentar pesquisar um dos Fabricantes");
		}
		*/
		
		//---------------------------------------------------------------------------
		
		/*
		//Teste de listar
		FabricanteDAO fdao = new FabricanteDAO();
		try {
			ArrayList<Fabricante> lista = fdao.listar();
			
			for (Fabricante fabricante : lista) {
				System.out.println("Resultado: " + fabricante);
			}
		} catch (SQLException e) {	
			System.out.println("Ocorreu um erro ao listar os Fabricantes!");
			e.printStackTrace();
		}
		*/
		
		//---------------------------------------------------------------------------
		
		/*
		//teste de busca por descrição
		Fabricante f1 = new Fabricante();
		f1.setDescricao("2");
		
		FabricanteDAO fdao = new FabricanteDAO();
		
		try {
			ArrayList<Fabricante> lista =  fdao.buscarPorDescricao(f1);
			for (Fabricante fabricante : lista) {
				System.out.println("Resultado: " + fabricante);
			}
		} catch (SQLException e) {	
			System.out.println("Ocorreu um erro ao buscaro um fabricante pela descrição");
			e.printStackTrace();
		}
		*/	
		
	}
}
