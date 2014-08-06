package clientReceiver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.text.BadLocationException;

public class MainPanel extends JPanel{
	private int _activeArtists;
	private HashMap<String, DisplayPanel> _artists;
	private JPanel _artistPanel;
	private JButton _connectButton;
	
	
	
	public MainPanel(String hostName, int portNum) throws BadLocationException {
		this.setLayout(new BorderLayout());
		_activeArtists = 1;
		_artists = new HashMap<String, DisplayPanel>();
		_artistPanel = new JPanel();
		_artistPanel.setLayout(new GridLayout(2,2));
		
		DisplayPanel panel = new DisplayPanel();
		_artists.put("One", panel);
		_artistPanel.add(panel);
		panel = new DisplayPanel();
		_artists.put("Two", panel);
		_artistPanel.add(panel);
		panel = new DisplayPanel();
		_artists.put("Three", panel);
		_artistPanel.add(panel);
		panel = new DisplayPanel();
		_artists.put("Four", panel);
		_artistPanel.add(panel);
		
		this.add(_artistPanel, BorderLayout.CENTER);
		
		_connectButton =  new JButton("Connect");
		_connectButton.addActionListener(new connectListener());
		
		this.add(_connectButton, BorderLayout.SOUTH);
		
		this.setSize(1200, 1200);
		this.setPreferredSize(new Dimension(900, 900));
		this.setVisible(true);
//		try (Socket socket = new Socket("localhost", 4444)) {
//			receive(socket);
//		} catch (UnknownHostException e1) {
//			//Handle error cases 
//			e1.printStackTrace();
//		}catch (IOException e1) {
//			//Handle error cases
//			e1.printStackTrace();
//		} catch (BadLocationException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
		System.out.println("Yolo");
		
	}
	
	private class connectListener implements ActionListener{
		private int _type;
		public connectListener() {
		}

		@Override
		public void actionPerformed(ActionEvent e) {
//			DisplayPanel dp = _artists.remove("One");
//			DrawingPanel drawP = dp.getDrawingPanel();
//			NetworkEllipse shape = new NetworkEllipse(dp.getDrawingPanel(), "0");
//			shape.setSize(30,30);
//			shape.setVisible(true);
//			shape.setFillColor(java.awt.Color.BLUE);
//			shape.setBorderWidth(1);
//			shape.setLocation(30,30);
//			drawP.addShape("0", shape);
			
			
		}


	}
	
	public void receive(Socket soc) throws IOException, BadLocationException {
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
				DisplayPanel dp;
				if (fromServer.equals("Connect")) {
					
					//connect message
					//Send the message to the textArea
					if (_activeArtists < 4) {
						switch (_activeArtists) {
							case 1:
								dp = _artists.remove("One");
								String artistName = in.readLine();
								_artists.put(artistName, dp);
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
					//create message
					String artistName = in.readLine();
					dp = _artists.get(artistName);
					String id = in.readLine();
					String typeOfFigure = in.readLine();
					if (typeOfFigure.equals("triangle")) {
						//we need to find a way to uniquely identify figures and 
						//have that sent directly to the server. Maybe smart figure?
					} else if (typeOfFigure.equals("ellipse")) {
						DrawingPanel drawP = dp.getDrawingPanel(); 
						NetworkEllipse shape = new NetworkEllipse(dp.getDrawingPanel(), id);
						shape.setSize(30,30);
						shape.setVisible(true);
						shape.setFillColor(java.awt.Color.BLUE);
						shape.setBorderWidth(1);
						shape.setLocation(30,30);
						drawP.addShape(id, shape);
						return;
					} else if (typeOfFigure.equals("rectangle")) {
						
						DrawingPanel drawP = dp.getDrawingPanel(); 
						NetworkRectangle shape  = new NetworkRectangle(drawP, id);
						shape.setSize(30,30);
						shape.setVisible(true);
						shape.setFillColor(java.awt.Color.BLUE);
						shape.setBorderWidth(1);
						shape.setLocation(30,30);
						drawP.addShape(id, shape);
					} else {
						//wrong figure
					}
				} else if (fromServer.equals("Update")) {
					//update
					//2 or three depends on the action
					String artistName = in.readLine();
					DrawingPanel drawP = _artists.get(artistName).getDrawingPanel();
					String identifier = in.readLine();//figure
					String typeOfChange = in.readLine();
					if (typeOfChange.equals("Background")) {
						
						
					} else if (typeOfChange.equals("Location")) {
						
						
					} else {
						//no real message
					}
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
	public void createShape(String artist, String id, String type) {
		String artistName = artist;
		DisplayPanel dp = _artists.get("One");
		String typeOfFigure = type;
		if (typeOfFigure.equals("triangle")) {
			//we need to find a way to uniquely identify figures and 
			//have that sent directly to the server. Maybe smart figure?
		} else if (typeOfFigure.equals("ellipse")) {
			DrawingPanel drawP = dp.getDrawingPanel(); 
			NetworkEllipse shape = new NetworkEllipse(dp.getDrawingPanel(), id);
			shape.setSize(30,30);
			shape.setVisible(true);
			shape.setFillColor(java.awt.Color.BLUE);
			shape.setBorderWidth(1);
			shape.setLocation(30,30);
			drawP.addShape(id, shape);
			return;
		} else if (typeOfFigure.equals("rectangle")) {
			
			DrawingPanel drawP = dp.getDrawingPanel(); 
			NetworkRectangle shape  = new NetworkRectangle(drawP, id);
			shape.setSize(30,30);
			shape.setVisible(true);
			shape.setFillColor(java.awt.Color.BLUE);
			shape.setBorderWidth(1);
			shape.setLocation(30,30);
			drawP.addShape(id, shape);
		} else {
			//wrong figure
		}
	}
}
