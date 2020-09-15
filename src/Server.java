/*
auther : Abdullah Al Masum
// TODO: 9/14/2020 This is one way simple socket programming where data can transmit server to client
 */

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
	public static final int port = 9090;
	private static ArrayList<Two_way_multiple_Client_at_a_time_Server> clients = new ArrayList<>();
	private static ExecutorService pool = Executors.newFixedThreadPool(20);

	public Server() {
	}

	public static void main(String[] args) throws Exception {
		ServerSocket listener;
		Socket client;
		PrintWriter out;
		BufferedReader in;
		String request = "";
		listener = new ServerSocket(port);
		System.out.println("Server is waiting for connect...");

		while(true) {
			client = listener.accept();
			System.out.println("yes...connected");
			Two_way_multiple_Client_at_a_time_Server clientThread = new Two_way_multiple_Client_at_a_time_Server(client);
			clients.add(clientThread);

			pool.execute(clientThread);
		}
	}
}