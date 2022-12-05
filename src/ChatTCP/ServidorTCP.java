package ChatTCP;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServidorTCP {
	
	public static final int PORTA = 5000;
	private ServerSocket serverSocket;
	
	public void start() throws IOException {
		try {
			serverSocket = new ServerSocket(PORTA);
			System.out.println("Serivor TCP inicado na porta " + PORTA);
		} catch (IOException e) {
			e.printStackTrace();
		}
		LoopConexaoCliente();
	}
	
	private void LoopConexaoCliente() throws IOException {
		Socket socket = serverSocket.accept();
		System.out.println("Cliente " + socket.getRemoteSocketAddress() + " conectou");
		while(true) {
			DataInputStream entrada = new DataInputStream(socket.getInputStream());
			String msg = entrada.readUTF();
			System.out.println("Mensagem recebida do cliente " + socket.getRemoteSocketAddress() + ": " + msg);
			DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
			saida.writeUTF(msg);
			}
	}
	
	public static void main(String[] args) {
		try {
			ServidorTCP servidor = new ServidorTCP();
			servidor.start();
		} catch (Exception e) {
			System.out.print("Erro ao iniciar o servidor:" + e.getMessage());
		}
		System.out.println("Servidor finalizado");
	}
}
