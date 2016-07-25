package br.com.boladeneve.assistenciasocial.negocio.ejb.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;

import br.com.boladeneve.assistenciasocial.negocio.ejb.MembroBean;

@Stateless
public class EJBFactory {

    @EJB
    private MembroBean membroBean;

    @Produces @EJBBean
    public MembroBean getMembroBean() {
		return membroBean;
	}

    
 
}
