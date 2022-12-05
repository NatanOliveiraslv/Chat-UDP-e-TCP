package ChatTCP;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class ClienteTCP {
	
	private static final String ENDERECO_SERVIDOR = "192.168.0.13";
	private Socket socket;
	public static final int PORTA = 5000;
	private Scanner scanner;
	
	public ClienteTCP() {
		scanner = new Scanner(System.in);
	}
	
	public void start() throws UnknownHostException, IOException {
		socket = new Socket(ENDERECO_SERVIDOR, PORTA); //Servidor.PORTA
		System.out.println("Ciente conectado ao servidor TCP em: " + ENDERECO_SERVIDOR + ":" + ServidorTCP.PORTA); //Servidor.PORTA
		menssagemLoop();
	}
	
	private void menssagemLoop() throws IOException {
		String msg;
		do {
			System.out.println("Digite uma menssagem (ou sair para finalizar): ");
			msg = scanner.nextLine();
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
			saida.writeUTF(msg);
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			entrada.readUTF();
		} while(!msg.equalsIgnoreCase("Sair"));
	}
	
	public static void main(String[] args) {
		try {
			ClienteTCP cliente = new ClienteTCP();
			cliente.start();
		} catch (Exception e){
			System.out.println("Erro ao iniciar cliente: " + e.getMessage());
		}
		
		System.out.println("Cliente finalizado!");
	}

}
