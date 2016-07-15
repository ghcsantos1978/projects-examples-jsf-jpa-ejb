package br.com.boladeneve.assistenciasocial.negocio.persistencia.util.view;

import java.util.List;

import javax.persistence.criteria.Order;

import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

public interface GenericDAO<T> {

	
	public void refresh(Class object);
	
	 public void flush() ;
	
	 public  void clear();

	 public  void merge(T objeto);
	
	 public  void salvar(T objeto);
	
	 public  void remover(T objeto);
	
	 public  void removerPorChave(Integer chave);

	 public T buscarPorChave(Long chave);
	
	 public List<T> buscarPorExemplo(T objeto, Order... ordenacoes);

	 public List<T> buscarPorExemplo(T objeto, Integer indiceInicial,
		   Integer indiceFinal, Order... ordenacoes);
	
	 public Long buscaQuantidadeTotal();

	 public List<T> buscarTodos(Order... ordenacoes);
	
	 public List<T> buscarTodos(Long indiceInicial,
		   Long indiceFinal, Order... ordenacoes);
	 
	 public String adicionaOrderByHql(Order... ordenacoes);

	 public Class getClassePersistente();
	

	 public Criteria criaCriteria();
	

	 public Example criaExemplo(T objeto);
	 
	 public void setClassePersistente(Class classePersistente);

}
