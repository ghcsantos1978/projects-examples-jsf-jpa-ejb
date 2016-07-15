package br.com.boladeneve.assistenciasocial.negocio.ejb;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.transaction.Transactional;

import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;
import br.com.boladeneve.assistenciasocial.negocio.persistencia.util.view.GenericDAO;

/**
 * Session Bean implementation class MembroSessionBean
 */
@Stateless
@LocalBean
public class MembroSessionBean implements Serializable  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1889002247669094895L;
	
	@Inject
	private GenericDAO<Membro> dao; 
	
	
    public List<Membro> listarMembros(){
    	return (List<Membro>) dao.buscarTodos(0l, dao.buscaQuantidadeTotal(), null);
    }

    public MembroSessionBean(){
    	dao.setClassePersistente(Membro.class);
    }
    
    
    @Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void salvar(Membro membro) {
		dao.salvar(membro);
	}

    @Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
    public void excluir(Membro membro){
		dao.remover(membro);
	}


	public Membro buscarMembro(Long id) {
		return (Membro) dao.buscarPorChave(new Long(id));
	}

    @Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterar(Membro membro) {
		dao.merge(membro);
	}
	
}
