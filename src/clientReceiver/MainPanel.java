package clientReceiver;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
		_activeArtists = 0;
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
	
	
	public void createShape(String artist, String id, String type) {
		String artistName = artist;
		DisplayPanel dp = _artists.get(artist);
		String typeOfFigure = type;
		if (typeOfFigure.equals("triangle")) {
			DrawingPanel drawP = dp.getDrawingPanel(); 
			NetworkTriangle shape = new NetworkTriangle(drawP, id);
			shape.setSize(30,30);
			shape.setVisible(true);
			shape.setFillColor(java.awt.Color.BLUE);
			shape.setBorderWidth(1);
			shape.setLocation(30,30);
			drawP.addShape(id, shape);
			return;
		} else if (typeOfFigure.equals("ellipse")) {
			DrawingPanel drawP = dp.getDrawingPanel(); 
			NetworkEllipse shape = new NetworkEllipse(drawP, id);
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
	public void updateShape(String artistName, String id, 
		String typeOfChange, String update1, String update2) {
		//2 or three depends on the action
		DrawingPanel drawP = _artists.get(artistName).getDrawingPanel();
		if (typeOfChange.equals("Background")) {
			drawP.changeBackground(update1);
		} else if (typeOfChange.equals("Location")) {
			drawP.moveShape(id, update1, update2);
		} else if (typeOfChange.equals("FillColor")){
			drawP.repaintShape(id, update1);
			
		}
	}
	public void addToPanel(String artistName, String welcomeMessage) {
		DisplayPanel dp;
		if (_activeArtists < 4) {
			switch (_activeArtists) {
				case 0:
					dp = _artists.remove("One");
					_artists.put(artistName, dp);
					dp.addText(welcomeMessage);
					break;
				case 1:
					dp = _artists.remove("Two");
					_artists.put(artistName, dp);
					dp.addText(welcomeMessage);
					break;
				case 2:
					dp = _artists.remove("Three");
					_artists.put(artistName, dp);
					dp.addText(welcomeMessage);

					break;
				case 3:
					dp = _artists.remove("Four");
					_artists.put(artistName, dp);
					dp.addText(welcomeMessage);
					break;
				default:
			}
			_activeArtists++;
		} else {
						//dismiss it
		}
	}

	public void removeFromPanels(String artistName) {
		DisplayPanel dp;
		if (_activeArtists > 1) {
			switch (_activeArtists) {
				case 1:
					dp = _artists.remove(artistName);
					_artists.put("One", dp);
					break;
				case 2:
					dp = _artists.remove(artistName);
					_artists.put("Two", dp);
					break;
				case 3:
					dp = _artists.remove(artistName);
					_artists.put("Three", dp);
					break;
				case 4:
					dp = _artists.remove(artistName);
					_artists.put("Four", dp);
					break;
				default:
			}
			_activeArtists--;
		} else {
			//There are no artists to remove
		}
	}

	public void sendText(String artistName, String text) {
		DisplayPanel dp = _artists.get(artistName);
		dp.addText(text);
	}
}
