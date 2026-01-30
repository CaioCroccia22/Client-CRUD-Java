package dao;

import java.util.Collection;

import domain.Cliente;

public interface IClienteDAO {
	public boolean cadastrar(Cliente cliente);
	
	public void excluir(Long cpf);
	
	public void alterar(Cliente Cliente);
	
	public Cliente consultar(long cpf);
	
	public Collection<Cliente> buscarTodos();
	
}
