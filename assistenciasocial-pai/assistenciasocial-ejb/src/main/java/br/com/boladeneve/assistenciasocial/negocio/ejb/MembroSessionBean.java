package br.com.boladeneve.assistenciasocial.negocio.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;
import br.com.boladeneve.assistenciasocial.negocio.persistencia.util.view.GenericDAO;

/**
 * Session Bean implementation class MembroSessionBean
 */
@Stateless
@Named("membroBean")
@LocalBean
@Transactional
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class MembroSessionBean implements Serializable,MembroBean  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1889002247669094895L;
	
	@Inject
	private GenericDAO<Membro> dao; 
	
	
    public List<Membro> listarMembros(){
		return null;
    }
    
    public MembroSessionBean(){
    	System.out.println("construtor MembroSessionBean");
    }
    
    public void salvar(Membro membro) {
		dao.setClassePersistente(Membro.class);
    	dao.salvar(membro);
	}

    public void excluir(Membro membro){
		dao.setClassePersistente(Membro.class);
    	dao.remover(membro);
	}


	public Membro buscarMembro(Long id) {
		dao.setClassePersistente(Membro.class);
		return (Membro) dao.buscarPorChave(new Long(id));
	}

	public void alterar(Membro membro) {
		dao.setClassePersistente(Membro.class);
		dao.merge(membro);
	}

	@Override
	public List<Membro> listarTodos() {
		dao.setClassePersistente(Membro.class);
		// TODO Auto-generated method stub
		return (List<Membro>) dao.buscarTodos(0l, dao.buscaQuantidadeTotal(), null);
	}
	
}
