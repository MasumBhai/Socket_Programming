/*
auther : Abdullah Al Masum
// TODO: 9/14/2020 This is one way simple socket programming where data can transmit server to client
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
	public static final int port = 9090;
	public Server(){
		try {
			ServerSocket listener = new ServerSocket(port);
			System.out.println("Server is waiting for connect...");
			Socket client = listener.accept();
			System.out.println("yes...connected");
			PrintWriter out = new PrintWriter(client.getOutputStream(),true);
			//we are passing date information here to the client
			out.println(new Date().toString());
			System.out.println("Server is about to shut down");
			client.close();
			listener.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		Server DateServer = new Server();
	}
}
