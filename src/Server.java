/*
auther : Abdullah Al Masum
// TODO: 9/14/2020 This is one way simple socket programming where data can transmit server to client
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {
	public static final int port = 9090;
	public static String[] characters = {"Goku", "Jiren", "Vegeta", "Master Roshi", "Freiza", "Beerus Sama", "Marjin Buu", "Goten", "Gohan", "Pecolo", "Krilin", "Chi-chi", "Videl", "Mr. Satan", "Yamcha", "Champa Sama", "Whis", "Vados", "Zeno Sama", "Trunks", "Bulma"};
	public static String[] behaviours = {"love chi-chi", "send text to chi-chi", "Funny character", "left the chatBox", "where is my dragon Ball!", "love delicious food", "loves Vegeta", "Entered into the chatBox", "Champion of the Tournament", "is Envious right now", "send text to bulma", "loves Videl ", "feeling Excited", "the Destroyer", "hate Freiza", "need senso bean now", "Powerful!! be careful", "Super Saiyan", "Savior of the planet", "loves Bulma"};

	public Server() {
	}

	public static String getRandom() {
		String name = characters[(int) (Math.random() * characters.length)];
		String act = behaviours[(int) (Math.random() * behaviours.length)];
		return name + " <----> " + act;
	}

	public static void main(String[] args) throws Exception {
		ServerSocket listener;
		Socket client;
		PrintWriter out;
		BufferedReader in;
		String request="";
		listener = new ServerSocket(port);
		System.out.println("Server is waiting for connect...");
		client = listener.accept();
		System.out.println("yes...connected");
		out = new PrintWriter(client.getOutputStream(), true);
		//we are passing date information here to the client
		//out.println(new Date().toString());
		in = new BufferedReader(new InputStreamReader(client.getInputStream()));
		try {
			while (true) {
				try {
					// TODO: 9/15/2020 get rid of this nullpointer Exception 
					request = in.readLine();
				} catch (NullPointerException e) {
					e.printStackTrace();
				}
				if (request.contains("hi")) {
					out.println(getRandom());
				} else {
					out.println("write 'hi' to play...or write 'bye'to quit");
				}
			}
		} finally {
			out.close();
			in.close();
			System.out.println("Server is about to shut down");
			client.close();
			listener.close();
		}
	}
}