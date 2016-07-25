package br.com.boladeneve.assistenciasocial.negocio.facade;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.inject.Named;
import javax.transaction.Transactional;

import br.com.boladeneve.assistenciasocial.negocio.ejb.MembroBean;
import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;
import br.com.boladeneve.assistenciasocial.negocio.persistencia.util.view.GenericDAO;


/*@Stateless
@LocalBean
@TransactionManagement(TransactionManagementType.CONTAINER)
@Transactional
@TransactionAttribute(TransactionAttributeType.REQUIRED)
*/
@Named("membroFacade")
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

	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void salvar(Membro membro) throws SQLException {
		try{
			for (int i = 0;i < 10;i++){
				membro = new Membro();
				membro.setNome("aaaaa " + i);
				membro.setEndereco("rua " + i);
				membroBean.salvar(membro);
				if (i == 7){
					throw new SQLException();
				}
			}
			membro = membroBean.buscarMembro(91l);
			excluir(membro);
			membro = membroBean.buscarMembro(92l);
			excluir(membro);
			membro = membroBean.buscarMembro(100l);
			membro.setNome("gustavo santos 123");
			alterar(membro);
		}
		catch(Exception e){
			throw new SQLException(e);
		}
	}

	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void excluir(Membro membro) {
		//membroBean.excluir(membro);
		//membroBean.remover(membro);
		membroBean.excluir(membro);
	}

	public Membro buscarMembro(Long id) {
		return membroBean.buscarMembro(id);
	}

	@Transactional
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void alterar(Membro membro) throws SQLException {
		try {
			if (membro.getId()!=null){
				throw new SQLException();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
