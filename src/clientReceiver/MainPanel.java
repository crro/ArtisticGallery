package clientReceiver;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

public class MainPanel extends JPanel{
	private int _activeArtists;
	private HashMap<String, DisplayPanel> _artists;
	
	
	
	public MainPanel(String hostName, int portNum) throws BadLocationException {
		this.setLayout(new GridLayout(2,2));
		_activeArtists = 0;
		_artists = new HashMap<String, DisplayPanel>();
		
		DisplayPanel panel = new DisplayPanel();
		_artists.put("One", panel);
		this.add(panel);
		panel = new DisplayPanel();
		_artists.put("Two", panel);
		this.add(panel);
		panel = new DisplayPanel();
		_artists.put("Three", panel);
		this.add(panel);
		panel = new DisplayPanel();
		_artists.put("Four", panel);
		this.add(panel);
		
		this.setSize(1200, 1200);
		this.setPreferredSize(new Dimension(900, 900));
		this.setVisible(true);
		try (Socket socket = new Socket(hostName, portNum)) {
			this.receive(socket);
		} catch (UnknownHostException e) {
			//Handle error cases 
		}catch (IOException e) {
			//Handle error cases
		}
		
	}
	
	public void receive(Socket soc) throws IOException, BadLocationException {
		try (
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
		) {
			//Establish the handshake
			out.println("Receive");
			
			//Start connection
			String fromServer;
			//StyledDocument doc = _textPane.getStyledDocument();
			
			//doc.insertString(doc.getLength(), in.readLine() + "\n", null);
			while((fromServer = in.readLine()) != null) {
				DisplayPanel dp;
				if (fromServer.equals("Connect")) {
					//Send the message to the textArea
					if (_activeArtists < 4) {
						switch (_activeArtists) {
							case 1:
								dp = _artists.remove("One");
								_artists.put(in.readLine(), dp);
								dp.addText(in.readLine());
								break;
							case 2:
								dp = _artists.remove("Two");
								_artists.put(in.readLine(), dp);
								dp.addText(in.readLine());
								break;
							case 3:
								dp = _artists.remove("Three");
								_artists.put(in.readLine(), dp);
								dp.addText(in.readLine());
								
								break;
							case 4:
								dp = _artists.remove("Four");
								_artists.put(in.readLine(), dp);
								dp.addText(in.readLine());
								break;
							default:
						}
						_activeArtists++;
					} else {
						//dismiss it
					}
				} else if (fromServer.equals("Create")) {
					//three message
					String artistName = in.readLine();
					dp = _artists.get(artistName);
					
					String typeOfFigure = in.readLine();
					if (typeOfFigure.equals("triangle")) {
						//we need to find a way to uniquely identify figures and 
						//have that sent directly to the server. Maybe smart figure?
					} else if (typeOfFigure.equals("ellipse")) {
						Network
					} else if (typeOfFigure.equals("rectangle")) {
						
					} else {
						//wrong ficture
					}
				} else if (fromServer.equals("Update")) {
					//2 or three depends on the action
				} else {
					//no real message
				}
//				String[] words = fromServer.split(" ");
//				String name = words[0];
//				int nameSize = words[0].length();
//				String message = fromServer.substring(nameSize);
//				SimpleAttributeSet attributes = new SimpleAttributeSet();
//			    attributes.addAttribute(StyleConstants.CharacterConstants.Bold, Boolean.TRUE);
//				doc.insertString(doc.getLength(), name, attributes);
//				doc.insertString(doc.getLength(), message + "\n", null);
			}
		}
		
	}
}
