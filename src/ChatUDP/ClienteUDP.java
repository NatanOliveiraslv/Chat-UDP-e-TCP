package ChatUDP;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class ClienteUDP {
	
	private static final String ENDERECO_SERVIDOR = "192.168.0.13";
	public static final int PORTA = 5000;
	
	public void start() throws UnknownHostException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		DatagramSocket clientSocket = new DatagramSocket();
		
		InetAddress IPAddress = InetAddress.getByName(ENDERECO_SERVIDOR);
		System.out.println("Ciente conectado ao servidor UDP em: " + ENDERECO_SERVIDOR + ":" + PORTA); //Servidor.PORTA

		byte[] dadosRecebidos = new byte[1024];
		byte[] dadsoEnviados = new byte[1024];
		
		String msg = new String();
		do {
			System.out.println("Digite uma menssagem (ou sair para finalizar): ");
			msg = in.readLine();
			dadsoEnviados = msg.getBytes();
			DatagramPacket pacoteEnviado = new DatagramPacket(dadsoEnviados,dadsoEnviados.length, IPAddress, PORTA);
			clientSocket.send(pacoteEnviado);
			
			DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
			clientSocket.receive(pacoteRecebido);
			
		} while(!msg.equalsIgnoreCase("Sair"));
	}
	
	public static void main(String[] args) {
		try {
			ClienteUDP cliente = new ClienteUDP();
			cliente.start();
		} catch (Exception e){
			System.out.println("Erro ao iniciar cliente: " + e.getMessage());
		}
		
		System.out.println("Cliente finalizado!");
	}

}
