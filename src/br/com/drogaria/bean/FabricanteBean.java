package br.com.drogaria.bean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.drogaria.dao.FabricanteDAO;
import br.com.drogaria.domain.Fabricante;
import br.com.drogaria.util.JSFUtil;

@ManagedBean(name = "FabricanteBean")
@ViewScoped
public class FabricanteBean {
	private Fabricante fabricante;
	private ArrayList<Fabricante> itens;
	private ArrayList<Fabricante> itensFiltrados;
	
	
	public ArrayList<Fabricante> getItens() {
		return itens;
	}

	public void setItens(ArrayList<Fabricante> itens) {
		this.itens = itens;
	}

	public ArrayList<Fabricante> getItensFiltrados() {
		return itensFiltrados;
	}

	public void setItensFiltrados(ArrayList<Fabricante> itensFiltrados) {
		this.itensFiltrados = itensFiltrados;
	}

	public Fabricante getFabricante() {
		return fabricante;
	}

	public void setFabricante(Fabricante fabricante) {
		this.fabricante = fabricante;
	}

	@PostConstruct
	public void prepararPesquisa() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			itens = dao.listar();			
		} catch (SQLException e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao listar Fabricantes");
		}
	}
	
	public void prepararNovo() {
		fabricante = new Fabricante();
	}
	
	public void novo() {		
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.salvar(fabricante);		
			itens = dao.listar();			
			JSFUtil.adicionarMensagemSucesso("Fabricante salvo com sucesso!");
		}catch (Exception e) {
			e.printStackTrace();
			JSFUtil.adicionarMensagemErro("Erro ao salvar Fabricante..." + e.getMessage());
		}
	}
	
		
	public void excluir() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.excluir(fabricante);
			
			//Recarregar a pagina após Remover algo
			itens = dao.listar();			
			JSFUtil.adicionarMensagemSucesso("Fabricante removido com sucesso!");
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao remover Fabricante..." + e.getMessage());
		}
	}
	
	
	public void editar() {
		try {
			FabricanteDAO dao = new FabricanteDAO();
			dao.editar(fabricante);
			
			//Recarregar a pagina após Remover algo
			itens = dao.listar();
			JSFUtil.adicionarMensagemSucesso("Fabricante editado com sucesso!");
			
		} catch (Exception e) {
			JSFUtil.adicionarMensagemErro("Erro ao editar Fabricante..." + e.getMessage());
		}
	}
}
