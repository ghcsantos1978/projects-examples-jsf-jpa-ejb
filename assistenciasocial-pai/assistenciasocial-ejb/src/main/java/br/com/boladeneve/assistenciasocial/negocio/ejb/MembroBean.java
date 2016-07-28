package br.com.boladeneve.assistenciasocial.negocio.ejb;

import java.util.List;

import javax.ejb.Local;

import br.com.boladeneve.assistenciasocial.negocio.entidade.Membro;

@Local
public interface MembroBean {
	
	public List<Membro> listarTodos();
	public void salvar(Membro membro);
	public void excluir(Membro membro);
	public Membro buscarMembro(Long id);
	public Membro alterar(Membro membro);
	public void atualizarBD();
}
