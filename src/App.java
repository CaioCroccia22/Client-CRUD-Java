import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.swing.JOptionPane;

import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

public class App {
	
	private static IClienteDAO iClienteDAO;
	
	public static void main(String args[]) {
		
		iClienteDAO = new ClienteMapDAO();
		
	
		
		String opcao = JOptionPane.showInputDialog(
			null,
			"Digite 1 para cadastro,"
					+ "2 para consulta" +
					"3 para exclusão"   +
					"4 para alteração"  +
					"ou 5 para saída", 
			 "Cadastro",
			JOptionPane.INFORMATION_MESSAGE);
		
		
		
		opcao = VerificaOpcao(opcao);
			
			
		if ("1".equals(opcao)) {
			cadastrar(iClienteDAO);
			System.exit(0);
		}
		
		if ("2".equals(opcao)) {
			consultarLista(iClienteDAO);				
			
		}
		
		if("5".equals(opcao)) {
			sair();
		}
	}
	
	public static String VerificaOpcao(String opcao) {
		while (!isOpcaoValida(opcao)) {
			
			if ("".equals(opcao)) {
				sair();}
		
			opcao = JOptionPane.showInputDialog(null,
					"Digite 1 para cadastro,"
							+ "2 para consulta" +
							"3 para exclusão"   +
							"4 para alteração"  +
							"ou 5 para saída", 
					 "Cadastro", JOptionPane.INFORMATION_MESSAGE);
			
			VerificaOpcao(opcao);
		}
		
		return opcao;
	}
	
	public static boolean isOpcaoValida(String opcao) {
		if ("1".equals(opcao) || "2".equals(opcao) || "3".equals(opcao)
				|| "4".equals(opcao) || "5".equals(opcao)) {
			
			return true;
		}
		
			return false;
	}
	
	public static void sair() {
		JOptionPane.showMessageDialog(
				null,
				"Até logo",
				"Sair",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public static void cadastrar(IClienteDAO iClienteDAO) {
		String dados = JOptionPane.showInputDialog(null, 
				"Digite os dados do cliente  separados por vírgulas, ex: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado",
				"Cadastro",
				JOptionPane.INFORMATION_MESSAGE);
		
		
		String[] dadosSeparados = dados.split(",");
		
		System.out.print("Dados serapados" + Arrays.toString(dadosSeparados));
		System.out.print("Tamanho: " + dadosSeparados.length);
		
		if (dadosSeparados.length != 7) {
			JOptionPane.showMessageDialog(
					null,
					"Você não preencheu todos os dados corretamente",
					"Sair",
					JOptionPane.INFORMATION_MESSAGE);
			System.exit(0);
		}
		
		Cliente cliente = new Cliente(dadosSeparados[0], 
				dadosSeparados[1],
				dadosSeparados[2],
				dadosSeparados[3],
				dadosSeparados[4],
				dadosSeparados[5],
				dadosSeparados[6]);
		
		if (cliente != null) {
			iClienteDAO.cadastrar(cliente);
			JOptionPane.showMessageDialog(
					null,
					"Cadastro realizado com sucesso",
					"Cadastro com sucesso",
					JOptionPane.INFORMATION_MESSAGE);
			
		}
		
	}

	private static void consultarLista(IClienteDAO iClienteDAO) {
		if(iClienteDAO.buscarTodos().size() <= 0) {
			
			JOptionPane.showMessageDialog(null, 
					"A lista ainda está vazia, porfavor realize o cadastro",
					"Pedir para cadastrar",
					JOptionPane.INFORMATION_MESSAGE);
			
			cadastrar(iClienteDAO);
			consultarLista(iClienteDAO);
		}
		
		
		String dados = JOptionPane.showInputDialog(null, 
				"Digite o CPF do cliente",
				"Buscar",
				JOptionPane.INFORMATION_MESSAGE);
		
			JOptionPane.showMessageDialog(null, iClienteDAO.consultar(
					Long.valueOf(dados.trim()))
				);
			
		} 
}

