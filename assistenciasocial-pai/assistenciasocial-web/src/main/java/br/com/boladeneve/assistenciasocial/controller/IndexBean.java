package br.com.boladeneve.assistenciasocial.controller;

import java.io.IOException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@RequestScoped
public class IndexBean {

	public void redirect(){
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect("paginas/cadastro_membro.xhtml");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
