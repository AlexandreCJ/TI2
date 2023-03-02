package app;

import java.util.*;

import dao.DAO;
import dao.UsuarioDAO;
import model.Usuario;

public class Principal {
	
	public static void main(String[] args) throws Exception {
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		
		boolean continuarExe  = true;
		Usuario usuario;
		int codigo;
		
		do {
			try {
				int opcao = menu();
				switch(opcao) {
					case 1:
						listeUsuarios();
					break;
					case 2:
						usuario = new Usuario(lerCodigo(),lerLogin(),lerSenha(),lerSexo());
						if(usuarioDAO.insert(usuario) == true) System.out.println("\tUsuario inserido com sucesso!");
					break;
					case 3:
						System.out.println("Qual usuario deseja atualizar?");
						listeUsuarios();
						codigo = lerCodigo();
						if(atualizeUsuario(codigo)) System.out.println("Usuario atualizado com sucesso!");
					break;
					case 4:
						System.out.println("Qual usuario deseja atualizar?");
						listeUsuarios();
						codigo = lerCodigo();
						if(usuarioDAO.delete(codigo)) System.out.println("Usuario exluido com sucesso!");
					break;
					case 0:
						//Fim de execucao
						continuarExe = false;
					break;
					default:
						//nothing here
					break;
				}
			} catch(InputMismatchException e) {
				System.out.println(e.getMessage());
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
		}while(continuarExe);
		System.in.close();

	}
	
	
	
	
	public static int menu() {
		int resp;
		boolean respInvalida = false;
		Scanner sc = new Scanner(System.in);
			
			do {
				resp = -1;
				try {
					System.out.println("1 - Listar os usuarios.\n"
							+ "2 - Inserir um novo usuario.\n"
							+ "3 - Atualizar um usuario.\n"
							+ "4 - Excluir um usuario.\n"
							+ "0 - Sair.");
					System.out.print("Digite sua opcao: ");
					resp = sc.nextInt();
					respInvalida = resp < 0 || resp > 4;
					if(respInvalida) System.out.println("Digite apenas valores previstos!");
				
				}catch(InputMismatchException e ) {
					System.out.println("ERRO! Apenas valores inteiros sao validos!");
					respInvalida = true;
				}
			}while(respInvalida);
			
		return resp;
	}
	
	public static void listeUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> usuarios = usuarioDAO.get();
		for(Usuario u : usuarios){
			System.out.println(u.toString());
		}
	}
	
	public static int lerCodigo() {
		boolean inteiro = false;
		int codigo = -1;
		while(!inteiro || codigo < 0) {
			try {
				System.out.print("\nDigite o codigo: ");
				Scanner sc = new Scanner(System.in);
				codigo = sc.nextInt();
				sc.nextLine();
				inteiro = true;
				if(codigo < 0 ) System.out.println("Apenas valores maiores ou iguais a 0 (zero) sao permitidos");
			}catch(InputMismatchException e ) {
				System.out.println("ERRO! Apenas valores inteiros sao validos!");
			}	
		}
		return codigo;
	}
	public static String lerLogin() {
		String login = "";
		try {
			System.out.print("Digite o seu login: ");
			Scanner sc = new Scanner(System.in);
			login = sc.nextLine();
		}catch(InputMismatchException e ) {
			//nada
		}	
		return login;
	}
	public static String lerSenha() {
		String senha = "";
		try {
			System.out.print("Digite a senha: ");
			Scanner sc = new Scanner(System.in);
			senha = sc.nextLine();
		}catch(InputMismatchException e ) {
			//nada
		}	
		return senha;
	}
	public static char lerSexo() {
		boolean escolhaNaoPrevista;
		char resp = '0';
		do {
			try {
				System.out.println("Escolha o sexo: [M / F]");
				
				System.out.print("Digite sua opcao: ");
				Scanner sc = new Scanner(System.in);
				resp = sc.next().charAt(0);
				resp = Character.toUpperCase(resp);
				System.out.println(resp);
				escolhaNaoPrevista = (resp != 'M') && (resp != 'F');
				if(escolhaNaoPrevista) System.out.println("Digite apenas 'M' ou 'F'");
			}catch(InputMismatchException e) {
				escolhaNaoPrevista = true;
			}
		}while(escolhaNaoPrevista);
		return resp;
	}
	
	public static boolean atualizeUsuario(int codigo) {
		UsuarioDAO uDAO = new UsuarioDAO();
		Usuario u = uDAO.get(codigo);
		try {
			System.out.print("Digite o novo login: ");
			Scanner sc = new Scanner(System.in);
			u.setLogin(sc.nextLine());
			System.out.print("Digite o nova senha: ");
			u.setSenha(sc.nextLine());
		}catch(InputMismatchException e) {
			
		}
		return uDAO.update(u);
	}
	
}