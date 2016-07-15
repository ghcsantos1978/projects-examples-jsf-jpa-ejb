package br.com.boladeneve.assistenciasocial.negocio.facade;

import java.util.List;

import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;

public interface MembroFacade {

	public List<Membro> listarTodos();
	public void salvar(Membro membro);
	public void excluir(Membro membro);
	public Membro buscarMembro(Long id);
	public void alterar(Membro membro);
}
