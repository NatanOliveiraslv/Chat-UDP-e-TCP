package ChatUDP;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class ServidorUDP {
	
	public static final int PORTA = 5000;
	private DatagramSocket serverSocket;
	
	public void start() throws IOException {
		try {
			serverSocket = new DatagramSocket(PORTA);
			System.out.println("Serivor UDP inicado na porta " + PORTA);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoopConexaoCliente();
	}
	
	private void LoopConexaoCliente() throws IOException {
		
		byte[] dadosRecebidos = new byte[1024];
		byte[] dadsoEnviados = new byte[1024];
		
		while(true) {
			
			DatagramPacket pacoteRecebido = new DatagramPacket(dadosRecebidos, dadosRecebidos.length);
			serverSocket.receive(pacoteRecebido);
			
			String msg = new String(pacoteRecebido.getData());
			
			System.out.println("Mensagem recebida do cliente " + pacoteRecebido.getAddress() + ": " + msg);
			
			dadsoEnviados = msg.getBytes();
			
			DatagramPacket paoteEnviado = new DatagramPacket(dadsoEnviados,msg.length(), pacoteRecebido.getAddress(), pacoteRecebido.getPort());
			
			serverSocket.send(paoteEnviado);
			}
	}
	
	public static void main(String[] args) {
		try {
			ServidorUDP servidor = new ServidorUDP();
			servidor.start();
		} catch (Exception e) {
			System.out.print("Erro ao iniciar o servidor:" + e.getMessage());
		}
		System.out.println("Servidor finalizado");
	}
}
