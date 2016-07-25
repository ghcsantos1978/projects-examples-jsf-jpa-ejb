package br.com.boladeneve.assistenciasocial.negocio.facade;

import java.sql.SQLException;
import java.util.List;

import javax.ejb.Local;

import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;


public interface MembroFacade {

	public List<Membro> listarTodos();
	public void salvar(Membro membro) throws SQLException;
	public void excluir(Membro membro);
	public Membro buscarMembro(Long id);
	public void alterar(Membro membro) throws SQLException;
}
