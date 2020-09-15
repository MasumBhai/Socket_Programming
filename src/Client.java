// TODO: 9/14/2020 drawBack is if client runs the programme before server, then it fails to connect
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {
		public static final String Server_ip = "127.0.0.1"; //no matter what,this ip address will work on every computer
		public static final int Server_port = 9090;


	public Client(){
		try {
			Socket socket = new Socket(Server_ip,Server_port);
			BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
			PrintWriter out = new PrintWriter(socket.getOutputStream(),true);
			while (true) {
				System.out.println("> ");
				String command = keyboard.readLine();
				if(command.equals("bye")) {break;}
				out.println(command);

				String serverResponse = input.readLine();
				System.out.println("Server says : "+ serverResponse);
			}
			socket.close();
			System.exit(0);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Write something...");
		Client myClientObj = new Client();
	}
}
