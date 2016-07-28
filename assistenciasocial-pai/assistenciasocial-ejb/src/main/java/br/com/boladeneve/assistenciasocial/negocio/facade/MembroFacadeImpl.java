package br.com.boladeneve.assistenciasocial.negocio.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.omg.PortableServer.ThreadPolicyOperations;

import br.com.boladeneve.assistenciasocial.negocio.ejb.MembroBean;
import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;


/*@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@Transactional
@TransactionAttribute(TransactionAttributeType.REQUIRED)
*/
@Named("membroFacade")
@Transactional(rollbackOn=Exception.class)
public class MembroFacadeImpl implements MembroFacade,Serializable {

	public MembroFacadeImpl(){
		System.out.println("tesste");
	}

	@Inject
	private MembroBean membroBean;
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5033485976609372349L;

	
	
	@Override
	public List<Membro> listarTodos() {
		// TODO Auto-generated method stub
		return membroBean.listarTodos();
		//return membroBean.listarTodos();
	}

	public void salvar(Membro membro) throws SQLException {
		membroBean.salvar(membro);
	}

	public void excluir(Membro membro) {
		membroBean.excluir(membro);
	}

	public Membro buscarMembro(Long id) {
		return membroBean.buscarMembro(id);
	}

	public void alterar(Membro membro) throws SQLException {
		membroBean.alterar(membro);
		//membroBean.alterar(membro);
	}
	
	/*@EJB
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
	*/
	
	
	
}
