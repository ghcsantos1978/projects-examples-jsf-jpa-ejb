package br.com.boladeneve.assistenciasocial.negocio.persistencia.util;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.criteria.Order;
import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;

import br.com.boladeneve.assistenciasocial.negocio.persistencia.util.view.GenericDAO;

/**
 * Session Bean implementation Object GenericDAOImpl
 */

@Stateless
@Named
@LocalBean
@Transactional
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class GenericDAOImpl<T>implements GenericDAO<T>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4424304693550760340L;

	
	/*private static EntityManagerFactory entityFactory;

	
	private static EntityManagerFactory getEntityFactory() {
		if (entityFactory == null){
			entityFactory = Persistence.createEntityManagerFactory("assistenciasocialPU");
		}
		return entityFactory;
	}
	*/


	@PersistenceContext(unitName="assistenciasocialPU")
	@Produces
	private static EntityManager manager;
	
	
	/*private static EntityManager getManager() {
		if (manager == null){
			manager = getEntityFactory().createEntityManager();
		}
		return manager;
	}
	*/
	
	
	
	private static Class classePersistente;

	
	public GenericDAOImpl(){
		System.out.println("teste");
	}
	
	public void setClassePersistente(Class classePersistente) {
		this.classePersistente = classePersistente;
	}
	
	 /**
	  * Atualiza o objeto que se encontra em memória.
	  *
	  * @param object
	  *            objeto a ser atualizado
	  */
	 public void refresh(T object) {
		 manager.refresh(object);
	 }
	
	 /**
	  * Executa o flush no entity manager.
	  *
	  */
	 public void flush() {
		 manager.flush();
	 }
	
	 /**
	  * Executa o flush no entity manager.
	  *
	  */
	 public void clear() {
		 flush();
		 manager.clear();
	 }
	
	 /**
	  * Executa o merge do objeto que se encontra em memória.
	  *
	  * @param objeto
	  *            a ser realizado o merge
	  * @return objeto que foi executado o merge
	  */
	 public void merge(T objeto) {
		 manager.joinTransaction();
		 objeto =  manager.merge(objeto);
		 flush();
	 }
	
	 /**
	  * Salva o objeto atual na base de dados.
	  *
	  * @param objeto
	  *            a ser salvo
	  */
	 public void salvar(T objeto) {
		 manager.joinTransaction();
		 manager.persist(objeto);
	 }
	
	 /**
	  * Remove o objeto da base de dados.
	  *
	  * @param objeto
	  *            a ser removido
	  */
	 public void remover(T objeto) {
		 manager.joinTransaction();
		 manager.remove(manager.contains(objeto)?objeto:manager.merge(objeto));
	 }
	
	 /**
	  * Remove o objeto uma vez passado sua chave como parâmetro.
	  *
	  * @param chave
	  *            identificadora do objeto
	  */
	 public void removerPorChave(Integer chave) {
		 manager.joinTransaction();
		 manager.createQuery(
	    "delete from " + getClassePersistente().getName()
	      + " where id = " + chave).executeUpdate();
	 }
	
	 /**
	  * Busca o objeto uma vez passado sua chave como parâmetro.
	  *
	  * @param chave
	  *            identificador
	  * @return Objeto do tipo T
	  */
	 public T buscarPorChave(Long chave) {
		  T instance = null;
		  try {
		   instance = (T)  manager.find(getClassePersistente(), chave);
		  } catch (RuntimeException re) {
		   re.printStackTrace();
		  }
		  return instance;
	 }
	
	 /**
	  * Busca o objeto de acordo com o objeto preenchido com os valores passado
	  * como exemplo.
	  *
	  * @param objeto
	  *            utilizado para realizar a busca
	  * @param ordenacoes
	  *            lista de critérios de ordenação
	  * @return Lista de objetos retornada
	  */
	 public List<T> buscarPorExemplo(T objeto, Order... ordenacoes) {
		  Session session = (Session)  manager.getDelegate();
		  Example example = criaExemplo(objeto);
		  Criteria criteria = session.createCriteria(objeto.getClass()).add(
		    example);
		  if (ordenacoes!=null){
			  for (int i = 0; i < ordenacoes.length; i++) {
				   criteria.addOrder((org.hibernate.criterion.Order) ordenacoes[i]);
				  }
		  }
		  return (List) criteria.list();
	 }
	
	 /**
	  * Busca o objeto de acordo com o objeto preenchido com os valores passado
	  * como exemplo.
	  *
	  * @param objeto
	  * @param indiceInicial
	  * @param indiceFinal
	  * @param ordenacoes
	  *            lista de critérios de ordenação.
	  * @return Lista de orden
	  */
	 public List buscarPorExemplo(T objeto, Integer indiceInicial,
		  
		  Integer indiceFinal, Order... ordenacoes) {
		  Example example = criaExemplo(objeto);
		  Criteria criteria = criaCriteria().add(example);
		  criteria.setFirstResult(indiceInicial);
		  criteria.setMaxResults(indiceFinal);
		  
		  if (ordenacoes!=null){
			  for (int i = 0; i < ordenacoes.length; i++) {
				   criteria.addOrder((org.hibernate.criterion.Order) ordenacoes[i]);
		  }
	  }
	
	  return (List) criteria.list();
	 }
	
	 /**
	  * Retorna a quantidade total de objetos para aquela entidade específica.
	  *
	  * @return quantidade total de objetos
	  */
	 public Long buscaQuantidadeTotal() {
	  Criteria criteria = criaCriteria();
	  criteria.setProjection(Projections.rowCount());
	  return (Long) criteria.uniqueResult();
	 }
	
	 /**
	  * Busca todos os objetos para aquela entidade específica.
	  *
	  * @param ordenacoes
	  *            lista de ordenações para pesquisa
	  * @return lista de todos os objetos da entidade
	  */
	 public List buscarTodos(Order... ordenacoes) {
		  List results = null;
		  try {
		   Query query =  manager.createQuery(
		     "from " + getClassePersistente().getName()
		       + adicionaOrderByHql(ordenacoes));
		   results = query.getResultList();
		  } catch (RuntimeException re) {
		   re.printStackTrace();
		  }
		  return results;
	 }
	
	 /**
	  *
	  * Busca todos os objetos de uma entidade específica de um índice inicial
	  * até um índice final.
	  *
	  * @param indiceInicial
	  *            indice inicial da busca
	  * @param indiceFinal
	  *            indice final da pesquisa.
	  * @param ordenacoes
	  *            lista de ordenação a ser criado
	  * @return uma lista de objetos do tipo T
	  */
	 public List<T> buscarTodos(Long indiceInicial,
		   Long indiceFinal, Order... ordenacoes) {
		   List<T> results = null;
		  try {
		   Query query =  manager.createQuery(
		     "from " + getClassePersistente().getName()
		       + adicionaOrderByHql(ordenacoes));
		   query.setFirstResult(indiceInicial.intValue());
		   query.setMaxResults(indiceFinal.intValue());
		
		   results = (List<T>) query.getResultList();
		  } catch (RuntimeException re) {
		   re.printStackTrace();
		  }
		  return results;
	 }
	
	 
	
	 /**
	  * Adiciona o orderBy no final da query a ser utilizada.
	  *
	  * @param ordenacoes
	  *            a serem utilizadas para a busca
	  * @return string com o orderBy
	  */
	 public String adicionaOrderByHql(Order... ordenacoes) {
		  String result = "";
		  if (ordenacoes!=null && ordenacoes.length > 0) {
			   StringBuilder builder = new StringBuilder(" order by ");
			   for (int i = 0; i < ordenacoes.length - 1; i++) {
			    builder.append(ordenacoes[i].toString());
			    builder.append(", ");
				   builder.append(ordenacoes[ordenacoes.length - 1]);
				   result = builder.toString();
			   }
		  }
		  return result;
	 }
	
	 /**
	  * Busca a classe persistente do objeto utilizado na classe.
	  *
	  * @return classe persistente
	  */
	 public  Class getClassePersistente() {
	  return classePersistente;
	 }
	
	 /**
	  * Retorna o objeto da clases Criteria.
	  *
	  * @return um objeto do tipo Criteria do Hibernate
	  */
	 public Criteria criaCriteria() {
		  Session session = (Session)  manager.getDelegate();
		  return session.createCriteria(getClassePersistente());
	 }
	
	 /**
	  * Método utilizado para criar o objeto Example. Este objeto é utilizado
	  * para realizar a busca por exemplo.
	  *
	  * @param objeto
	  *            sobre o qual o Example será criado
	  * @return em objeto do tipo Example
	  */
	 public Example criaExemplo(T objeto) {
	
		  Example example = Example.create(objeto);
		  example.enableLike(MatchMode.ANYWHERE);
		  example.excludeZeroes();
		  example.ignoreCase();
		
		  return example;
	 }
}
