package br.com.boladeneve.assistenciasocial.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;
import br.com.boladeneve.assistenciasocial.negocio.entidade.UF;
import br.com.boladeneve.assistenciasocial.negocio.facade.MembroFacade;

@javax.faces.view.ViewScoped
@Named("membroMB")
public class MembroMB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1775244581540406599L;
	
	@Inject
	private MembroFacade membroFacade;	

	
	public MembroFacade getMembroFacade() {
		return membroFacade;
	}
	
	
	public void setMembroFacade(MembroFacade facade) {
		this.membroFacade = facade;
	}

	private Membro membro;
	private Membro membroSelecionado;
	
	public Membro getMembroSelecionado() {
		return membroSelecionado;
	}


	public void setMembroSelecionado(Membro membroSelecionado) {
		this.membroSelecionado = membroSelecionado;
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
		carregaListas();
	}

	public void novo(){
		limpar();
	}
	
	public String salvar(){
		//se tiver id é porque é alteração então busca no banco o objeto atachado para não ter problema de detached entity
		try{
			if (membro!=null && membro.getId()!=null){
				membroFacade.alterar(membro);
			}
			else{
				membroFacade.salvar(membro);
				limpar();
			}
			listar();
		}
		catch(Exception e){
			FacesContext.getCurrentInstance().addMessage("Erro", new FacesMessage("Erro na transação"));
		}
		return null;
	}
	
	public void listar(){
		listaMembros = membroFacade.listarTodos();
	}

	public void buscarMembro(Long id){
		this.membro = membroFacade.buscarMembro(id);
	}

	public void limpar(){
		membro = new Membro();
	}

	public void carregaListas(){
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
		//this.membro = facade.buscarMembro(this.membro.getId());
		membroFacade.excluir(this.membro);
		listaMembros = membroFacade.listarTodos();
		limpar();
	}
	
	public void selecionaLinhaTabela(SelectEvent e) {
		this.membro = (Membro) e.getObject();
		this.membro = membroFacade.buscarMembro(membro.getId());
    }
	
}
