package server;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class Server {
	
	private ArrayList<Holder> _sockets;
	private int _nextUniqueID = 0;
	
	@SuppressWarnings("resource")
	public Server(int portNumber) throws IOException, ClassNotFoundException {
		_sockets = new ArrayList<Holder>();
		ServerSocket serverSocket = new ServerSocket(portNumber);
		System.out.println("Server started");
		
		while (true) {
				Socket clientSocket = serverSocket.accept();
				PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
				String fromClient = in.readLine();
				System.out.println(fromClient);
				if (fromClient.equals("Connect")) {
					
					/*File file = new File("sample.txt");
					 
					if file doesnt exists, then create it
					if (!file.exists()) {
						file.createNewFile();
					}*/
					//FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
					//BufferedWriter bw = new BufferedWriter(fw);
					//bw.write(fromClient + "\n");
					//bw.close();
					
					out.println(_nextUniqueID);
					//Send the message
					this.sendMessage("Connect");
					this.sendMessage(in.readLine());//The name of whoever connected
					this.sendMessage(in.readLine());//Send the connection message
				} else if (fromClient.equals("Receive")) {
					out.println("Connection Established: Receive");
//					File file = new File("sample.txt");
//					 
//					// if file doesn't exists, then create it
//					if (!file.exists()) {
//						_sockets.add(new Holder(clientSocket, out));
//						continue;
//					}
//					FileReader fw = new FileReader(file.getAbsoluteFile());
//					BufferedReader bw = new BufferedReader(fw);
//					String line;
//					while ((line = in.readLine()) != null) {
//						out.println(line);
//					}
//					bw.close();
					_sockets.add(new Holder(clientSocket, out));
					System.out.println("The size of the arraylist is: " + _sockets.size());
				}  else if (fromClient.equals("Send")) {
					//This gets called every time. We have create and update.
					fromClient = in.readLine();
					if (fromClient.equals("Create")) {
						//We create a new object and send it to the receiver
						this.sendMessage("Create");
						this.sendMessage(in.readLine());//Identifier of the artist
						this.sendMessage(in.readLine());//The figure id
						this.sendMessage(in.readLine());//We send the type (Ellipse, square or triangle)
						//The color is blue by default
					} else if (fromClient.equals("Update")) {
						//We send the HashMap to the receiver.
						this.sendMessage("Update");
						this.sendMessage(in.readLine());//The artist identifier
						this.sendMessage(in.readLine());//The figure identifier
						String typeOfChange = in.readLine();
						this.sendMessage(typeOfChange);
						
						if (typeOfChange.equals("Background")) {
							this.sendMessage(in.readLine());//Type of color
						} else if (typeOfChange.equals("Location")) {
							this.sendMessage(in.readLine());//x
							this.sendMessage(in.readLine());//y
						}
						
					} else if (fromClient.equals("Disconnect")) {
						//Invalid command
						this.sendMessage("Disconnect");
						this.sendMessage(in.readLine());//This is the artist id
					} else if (formClient.equals("Text")){
						this.sendMessage("Text");
						this.sendMessage(in.readLine);//identifier
						this.sendMessage(in.readLine);//message
					} else {

					}
				} else {
					out.println("Wrong Protocol");
					clientSocket.close();
					out.close();
					in.close();
				}
		}
	}
	
	public void sendMessage(String message) {
		int size = _sockets.size();
		for (int i = 0; i < size; i++) {
			//Figure out if client is not there, do some error checking. 
			System.out.println(_sockets.get(i).getSocket().isConnected());
			_sockets.get(i).getWriter().println(message);
		}
	}
	
	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		try {
			new Server(4444);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
