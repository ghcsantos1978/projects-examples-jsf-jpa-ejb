package br.com.boladeneve.assistenciasocial.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;
import br.com.boladeneve.assistenciasocial.negocio.entidade.UF;
import br.com.boladeneve.assistenciasocial.negocio.facade.MembroFacade;

@Named("membroMB")
@ViewScoped
public class MembroMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1775244581540406599L;
	
	@Inject
	private MembroFacade facade;

	
	public MembroFacade getFacade() {
		return facade;
	}
	
	
	public void setFacade(MembroFacade facade) {
		this.facade = facade;
	}

	private Membro membro;
	private Membro membroSelecionado;
	
	public Membro getMembroSelecionado() {
		return membroSelecionado;
	}


	public void setMembroSelecionado(Membro membroSelecionado) {
		this.membroSelecionado = membroSelecionado;
		this.membro = membroSelecionado;
	}


	public void setMembro(Membro membro) {
		this.membro = membro;
	}

	private List<UF> listaUF;
	private List<Membro> listaMembros;
	private List<Membro> listaMembrosSelecionados;
	
	
	public List<Membro> getListaMembrosSelecionados() {
		return listaMembrosSelecionados;
	}

	public void setListaMembrosSelecionados(List<Membro> listaMembrosSelecionados) {
		this.listaMembrosSelecionados = listaMembrosSelecionados;
	}

	public List<Membro> getListaMembros() {
		return listaMembros;
	}

	public void setListaMembros(List<Membro> listaMembros) {
		this.listaMembros = listaMembros;
	}

	public List<UF> getListaUF() {
		return listaUF;
	}

	public void setListaUF(List<UF> listaUF) {
		this.listaUF = listaUF;
	}

	public Membro getMembro() {
		return membro;
	}

	
	@PostConstruct
	public void init(){
		limpar();
		listar();
	}

	public void novo(){
		limpar();
	}
	
	public String salvar(){
		//se tiver id é porque é alteração então busca no banco o objeto atachado para não ter problema de detached entity
		if (membro!=null && membro.getId()!=null){
			membro = facade.buscarMembro(membro.getId());
			facade.alterar(membro);
		}
		else{
			facade.salvar(membro);
			limpar();
		}
		listar();
		return null;
	}
	
	public void listar(){
		listaMembros = facade.listarTodos();
	}

	public void buscarMembro(Long id){
		this.membro = facade.buscarMembro(id);
	}

	public void limpar(){
		membro = new Membro();
		listaUF = new ArrayList<UF>();
		UF uf = new UF();
		uf.setSigla("RJ");
		uf.setDescricao("RJ");
		listaUF.add(uf);
		
		uf = new UF();
		uf.setSigla("SP");
		uf.setDescricao("SP");
		listaUF.add(uf);
		
		uf = new UF();
		uf.setSigla("MG");
		uf.setDescricao("MG");
		listaUF.add(uf);
		
		uf = new UF();
		uf.setSigla("PO");
		uf.setDescricao("PO");
		listaUF.add(uf);
		
	}

	
	public void excluir(){
		membro = facade.buscarMembro(membro.getId());
		facade.excluir(membro);
		listaMembros = facade.listarTodos();
	}
	
	public void selecionaLinhaTabela(SelectEvent e) {
		membro = new Membro();
		membro = (Membro) e.getObject();
    }
	
}
