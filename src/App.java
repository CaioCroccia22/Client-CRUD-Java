import java.util.Arrays;


import javax.swing.JOptionPane;

import dao.ClienteMapDAO;
import dao.IClienteDAO;
import domain.Cliente;

public class App {
	
	private static IClienteDAO iClienteDAO;
	
	public static void main(String args[]) {
		
		iClienteDAO = new ClienteMapDAO();
		boolean continuar = true;
                
                
                while(continuar){
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

                    }

                    if ("2".equals(opcao)) {
                            consultarLista(iClienteDAO);				

                    }

                    if ("3".equals(opcao)) {
                            excluirDaLista(iClienteDAO);				

                    }

                    if ("4".equals(opcao)) {
                            alterarDaLista(iClienteDAO);				

                    }

                    if("5".equals(opcao)) {
                            sair();
                            continuar = false;
                    }
                
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
                    String cadastro = JOptionPane.showInputDialog(
                            null,
                            "Cadastro realizado com sucesso, deseja cadastrar mais 1 para sim e 2 para não?",
                            "Cadastro com sucesso",
                            JOptionPane.INFORMATION_MESSAGE);
                    
                    if ("1".equals(cadastro)) {
                        cadastrar(iClienteDAO);
                        
                    } 
                } 
                
                else {
                    JOptionPane.showMessageDialog(null, 
					"Erro ao cadastrar, tente novamente",
					"Pedir para cadastrar",
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
		
			JOptionPane.showMessageDialog(null, iClienteDAO.consultar(Long.parseLong(dados.trim()))
				);
			
		} 

        private static void excluirDaLista(IClienteDAO iClienteDAO){
           if(iClienteDAO.buscarTodos().size() <= 0) {
			
			JOptionPane.showMessageDialog(null, 
					"A lista ainda está vazia, porfavor realize o cadastro",
					"Pedir para cadastrar",
					JOptionPane.INFORMATION_MESSAGE);
			
			cadastrar(iClienteDAO);
			
		}
       
           String dado = JOptionPane.showInputDialog(null, 
					"Digite o CPF que deseja excluir",
					"Pedir para cadastrar",
					JOptionPane.INFORMATION_MESSAGE);
           
           Cliente cliente = iClienteDAO.consultar(Long.parseLong(dado.trim()));
           
            if (cliente != null) {
                iClienteDAO.excluir(Long.valueOf(dado.trim()));
                JOptionPane.showMessageDialog(null, "Cliente excluído com sucesso", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente não encontrado", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        
			
			
       }

        private static void alterarDaLista(IClienteDAO iClienteDAO){
            String cpf = JOptionPane.showInputDialog(null, 
				"Digite o CPF do cliente",
				"Pedir cpf",
				JOptionPane.INFORMATION_MESSAGE);
            
            Cliente clienteExiste = iClienteDAO.consultar(Long.parseLong(cpf.trim()));
            
            if (clienteExiste != null) {
                String dados = JOptionPane.showInputDialog(null, 
				"Digite os dados do cliente  separados por vírgulas, ex: Nome, CPF, Telefone, Endereço, Número, Cidade, Estado",
				"Alterar",
				JOptionPane.INFORMATION_MESSAGE);
		
		
                String[] dadosSeparados = dados.split(",");

                Cliente cliente = new Cliente(dadosSeparados[0], 
                                    dadosSeparados[1],
                                    dadosSeparados[2],
                                    dadosSeparados[3],
                                    dadosSeparados[4],
                                    dadosSeparados[5],
                                    dadosSeparados[6]);

                iClienteDAO.alterar(cliente);
            
            } else {
                JOptionPane.showMessageDialog(null,
                "Cliente não encontrado",
                "Erro",
                JOptionPane.ERROR_MESSAGE);
            
            }
        }


}

