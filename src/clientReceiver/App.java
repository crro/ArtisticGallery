package clientReceiver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public class App {
	private MainPanel _mPanel;

	public App() throws BadLocationException {
		//Create the frame
		JFrame frame = new JFrame("Client Receiver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Create the panel and its containing elements
		_mPanel =  new MainPanel("localhost", 4444);
		frame.add(_mPanel);
		frame.pack();
		frame.setVisible(true);
		try (Socket socket = new Socket("localhost", 4444)) {
			receive(socket);
		} catch (UnknownHostException e1) {
			//Handle error cases 
			e1.printStackTrace();
		}catch (IOException e1) {
			//Handle error cases
			e1.printStackTrace();
		}
	}
	public void receive(Socket soc) throws IOException {
		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
		) {
			//Establish the handshake
			out.println("Receive");
			//return;
			//Start connection
			String fromServer;
			//StyledDocument doc = _textPane.getStyledDocument();
			
			//doc.insertString(doc.getLength(), in.readLine() + "\n", null);
			while((fromServer = in.readLine()) != null) {
				if (fromServer.equals("Connect")) {
					_mPanel.addToPanel(in.readLine(), in.readLine());
				}
				if (fromServer.equals("Create")) {
					_mPanel.createShape(in.readLine(), in.readLine(), in.readLine());
				} else if (fromServer.equals("Update")){
					//no real message
					String artistName = in.readLine();
					String figureId = in.readLine();
					String typeOfChange = in.readLine();
					if (typeOfChange.equals("Background")) {
						//we read once for the color
						_mPanel.updateShape(artistName, figureId,
							typeOfChange, in.readLine(), null);
					} else if (typeOfChange.equals("Location")) {
						//we read twice for the x and y
						_mPanel.updateShape(artistName, figureId,
							typeOfChange, in.readLine(), in.readLine());
					} else {
						//unkown update
					}
				} else if (fromServer.equals("Disconnect")){
					_mPanel.removeFromPanels(in.readLine());
				} else if (fromServer.equals("Text")){
					_mPanel.sendText(in.readLine(), in.readLine());//artistName, text
				} else {

				}
			}
		}
	}
	
	/**
	 * @param args
	 * @throws BadLocationException 
	 */
	public static void main(String[] args) throws BadLocationException {
		// TODO parse commandline arguments		
		new App();
	}

}
