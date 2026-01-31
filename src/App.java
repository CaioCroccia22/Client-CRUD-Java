import javax.swing.JOptionPane;

import dao.ClienteMapDAO;
import dao.IClienteDAO;

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
		
		while (!isOpcaoValida(opcao)) {
			if ("".equals(opcao)) {
				sair();
			}
			
			opcao = JOptionPane.showInputDialog(
					null,
					"Digite 1 para cadastro,"
							+ "2 para consulta" +
							"3 para exclusão"   +
							"4 para alteração"  +
							"ou 5 para saída", 
					 "Cadastro",
					JOptionPane.INFORMATION_MESSAGE);
		}
			
		if ("1".equals(opcao)) {
			
		}
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
}
