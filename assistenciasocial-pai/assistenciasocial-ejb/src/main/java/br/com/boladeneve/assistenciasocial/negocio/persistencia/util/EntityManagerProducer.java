package br.com.boladeneve.assistenciasocial.negocio.persistencia.util;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


/*@Stateless
@EntityProducer
*/
public class EntityManagerProducer implements Serializable {

	/**
	 * 
	 */
	  private static final long serialVersionUID = -5683287850148833617L;
	
	  /*@PersistenceContext(unitName="assistenciasocialPU")
	  private EntityManager entityManager;

	  @Produces
	  public EntityManager entityManager(){
	    return entityManager;
	  }
	  */	
	
}
