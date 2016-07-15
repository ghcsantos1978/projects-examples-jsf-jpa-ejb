package br.com.boladeneve.assistenciasocial.negocio.facade;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;

import br.com.boladeneve.assistenciasocial.negocio.ejb.MembroSessionBean;
import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;


public class MembroFacadeImpl implements MembroFacade,Serializable {

	
	public MembroFacadeImpl(){
		System.out.println("teste");
	}
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5033485976609372349L;
	
	@EJB
	private MembroSessionBean bean;
	
	@Override
	public List<Membro> listarTodos() {
		return bean.listarMembros();
	}

	@Override
	public void salvar(Membro membro) {
		bean.salvar(membro);
	}

	@Override
	public void excluir(Membro membro) {
		bean.excluir(membro);
	}

	@Override
	public Membro buscarMembro(Long id) {
		return bean.buscarMembro(id);
	}

	public void alterar(Membro membro){
		bean.alterar(membro);
	}
	
}
