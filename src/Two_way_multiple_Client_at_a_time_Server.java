import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Two_way_multiple_Client_at_a_time_Server implements Runnable {

	public static String[] characters = {"Goku", "Jiren", "Vegeta", "Master Roshi", "Freiza", "Beerus Sama", "Marjin Buu", "Goten", "Gohan", "Pecolo", "Krilin", "Chi-chi", "Videl", "Mr. Satan", "Yamcha", "Champa Sama", "Whis", "Vados", "Zeno Sama", "Trunks", "Bulma"};
	public static String[] behaviours = {"love chi-chi", "send text to chi-chi", "Funny character", "left the chatBox", "where is my dragon Ball!", "love delicious food", "loves Vegeta", "Entered into the chatBox", "Champion of the Tournament", "is Envious right now", "send text to bulma", "loves Videl ", "feeling Excited", "the Destroyer", "hate Freiza", "need senso bean now", "Powerful!! be careful", "Super Saiyan", "Savior of the planet", "loves Bulma"};

	public static final int Server_Port = 9090;
	String request = "";
	private Socket client;
	private PrintWriter Out;
	private BufferedReader In;

	public static String getRandom() {
		String name = characters[(int) (Math.random() * characters.length)];
		String act = behaviours[(int) (Math.random() * behaviours.length)];
		return name + " <----> " + act;
	}

	public Two_way_multiple_Client_at_a_time_Server(Socket clientSocket) throws IOException {
		this.client = clientSocket;
		Out = new PrintWriter(client.getOutputStream(),true);
		In = new BufferedReader(new InputStreamReader(this.client.getInputStream()));
	}

	@Override
	public void run() throws NullPointerException{
		try {
			while (true) {
					request = In.readLine();
				if (request.contains("hi")) {
					Out.println(getRandom());
				} else {
					Out.println("type 'hi' to play or 'bye' to quit");
				}
			}
		} catch (IOException e) {
			System.err.println("Error on requesting...");
			System.err.println(e.getStackTrace());
		} finally {
			try {
				Out.close();
				In.close();
			} catch (IOException e) {
				System.err.println(e.getStackTrace());
			}
		}
	}

}
