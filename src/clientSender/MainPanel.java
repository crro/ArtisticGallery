package clientSender;

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

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


public class MainPanel extends JPanel {
	private JTextArea _messageArea;
	private JButton _rectangleBtn;
	private JButton _circleButton;
	private DrawingPanel _dPanel;
	private String _identifier;
	private String _artistName;
	private int _nextIdentifier;
	
	public MainPanel() {
		//Setting th Layout
		this.setLayout(new BorderLayout());
		_messageArea = new JTextArea();
		_messageArea.setSize(200, 500);
		_messageArea.setPreferredSize(new Dimension(200, 500));
		_messageArea.setVisible(true);
		_artistName = "Pietro";
		_nextIdentifier = 0;
		
		this.setSize(725, 700);
		this.setPreferredSize(new Dimension(725, 700));
		this.setVisible(true);
		//Adding the text panel and then adding the JTextArea
		JPanel textPanel = new JPanel();
		textPanel.setLayout(new BorderLayout());
		textPanel.setSize(200, 500);
		textPanel.setPreferredSize(new Dimension(200, 500));
		JPanel kPanel  = new JPanel();
		kPanel.add(_messageArea);
		textPanel.add(kPanel, BorderLayout.CENTER);
		JButton sendButton = new JButton("Send");
		sendButton.addActionListener(new SendListener());
		JPanel hPanel = new JPanel();
		hPanel.add(sendButton);
		textPanel.add(hPanel, BorderLayout.SOUTH);
		
		
		this.add(textPanel, BorderLayout.EAST);
		
		JSlider height = new JSlider(JSlider.VERTICAL, 0, 500, 30);
		height.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent arg0) {
				JSlider js = (JSlider) arg0.getSource();
				_dPanel.changeHeight(js.getValue());
			}
		});
		JSlider width = new JSlider(0, 500, 30);
		width.addChangeListener(new ChangeListener() {
			
			@Override
			public void stateChanged(ChangeEvent e) {
				JSlider js = (JSlider) e.getSource();
				_dPanel.changeWidth(js.getValue());
			}
		});
		_dPanel = new DrawingPanel(this);
		JPanel dcPanel = new JPanel();
		dcPanel.setLayout(new BorderLayout());
		dcPanel.add(_dPanel, BorderLayout.CENTER);
		dcPanel.add(height, BorderLayout.EAST);
		dcPanel.add(width, BorderLayout.SOUTH);
		this.add(dcPanel, BorderLayout.CENTER);
		
		
		//So this will be the SoutPanel
		JPanel southPanel = new JPanel();
		southPanel.setLayout(new GridLayout(1,4));
		
		//Background Color block-----------------------------
		JPanel backgroundColorP = new JPanel();
		backgroundColorP.setLayout(new GridLayout(3,1));
		JLabel title = new JLabel("Background Color", JLabel.CENTER);
		//adding it to the background panel
		backgroundColorP.add(title);
			//blue
		JButton blueButton = new JButton(" blue  ");
		blueButton.addActionListener(new BackgroundListener("blue"));
			//orange
		JButton orangeButton = new JButton("orange");
		orangeButton.addActionListener(new BackgroundListener("orange"));
		
		JPanel aPanel = new JPanel();
		aPanel.add(orangeButton);
		aPanel.add(blueButton);
		//adding them to the background
		backgroundColorP.add(aPanel);
			//yellow
		JButton yellowButton = new JButton("yellow  ");
		yellowButton.addActionListener(new BackgroundListener("yellow"));
			//green
		JButton greenButton = new JButton("green ");
		greenButton.addActionListener(new BackgroundListener("green"));
		
		JPanel bPanel = new JPanel();
		bPanel.add(yellowButton);
		bPanel.add(greenButton);
		//Adding to BACKGROUND
		backgroundColorP.add(bPanel);
		
		//figure Color Block----------------------------
		JPanel figureColorP = new JPanel();
		figureColorP.setLayout(new GridLayout(3,1));
		JLabel titleF = new JLabel("Fill Color", JLabel.CENTER);
		//adding it to the background panel
		figureColorP.add(titleF);
			//blue
		JButton blueButtonF = new JButton(" blue  ");
		blueButtonF.addActionListener(new FillerListener("blue"));
			//orange
		JButton orangeButtonF = new JButton("orange");
		orangeButtonF.addActionListener(new FillerListener("orange"));
		
		JPanel cPanel = new JPanel();
		cPanel.add(orangeButtonF);
		cPanel.add(blueButtonF);
		//adding them to the background
		figureColorP.add(cPanel);
			//yellow
		JButton yellowButtonF = new JButton("yellow  ");
		yellowButtonF.addActionListener(new FillerListener("yellow"));
			//green
		JButton greenButtonF = new JButton("green ");
		greenButtonF.addActionListener(new FillerListener("green"));
		
		JPanel dPanel = new JPanel();
		dPanel.add(yellowButtonF);
		dPanel.add(greenButtonF);
		//Adding to BACKGROUND
		figureColorP.add(dPanel);
		
		//Figures Panel--------------------------
		JPanel figureChooseP = new JPanel();
		figureChooseP.setLayout(new GridLayout(4,1));
		
		JButton rectangleButton = new JButton("Rectangle");
		rectangleButton.addActionListener(new addShapeListener(1));
		JPanel ePanel = new JPanel();
		ePanel.add(rectangleButton);
		
		
		JButton triangleButton = new JButton("Triangle");
		triangleButton.addActionListener(new addShapeListener(3));
		JPanel gPanel = new JPanel();
		gPanel.add(triangleButton);
		
		JButton circleButton = new JButton("Circle");
		circleButton.addActionListener(new addShapeListener(2));
		JPanel fPanel = new JPanel();
		fPanel.add(circleButton);
		
		figureChooseP.add(new JLabel("Figure", JLabel.CENTER));
		figureChooseP.add(ePanel);
		figureChooseP.add(gPanel);
		figureChooseP.add(fPanel);
		
		//Send and Logout Block-----------------
		JPanel sendLogPanel = new JPanel();
		sendLogPanel.setLayout(new BorderLayout());
		
		JButton logButton = new JButton("Logout");
		logButton.addActionListener(new LogoutListener());
		JPanel iPanel = new JPanel();
		iPanel.setBorder(new EmptyBorder(0 , 0 , 50, 15));
		iPanel.add(logButton);
		
		//sendLogPanel.add(hPanel);
		sendLogPanel.add(iPanel, BorderLayout.SOUTH);
		
		//redo
			
		southPanel.add(backgroundColorP);
		southPanel.add(figureColorP);
		southPanel.add(figureChooseP);
		southPanel.add(sendLogPanel);
		this.add(southPanel, BorderLayout.SOUTH);
		this.connect();
	}
	
	private class FillerListener implements ActionListener{
		private String _color;
		public FillerListener(String color) {
			_color = color;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (_color.equals("blue")) {
				_dPanel.setHolderColor(java.awt.Color.BLUE);
				_dPanel.repaint();
				sendUpdate("FillColor", _dPanel.getHolderId(), "blue", null);
			} else if (_color.equals("green")) {
				_dPanel.setHolderColor(java.awt.Color.GREEN);
				_dPanel.repaint();
				sendUpdate("FillColor", _dPanel.getHolderId(), "green", null);
			} else if (_color.equals("orange")) {
				_dPanel.setHolderColor(java.awt.Color.ORANGE);
				_dPanel.repaint();
				sendUpdate("FillColor", _dPanel.getHolderId(), "orange", null);
			}else if (_color.equals("yellow")) {
				_dPanel.setHolderColor(java.awt.Color.YELLOW);
				_dPanel.repaint();
				sendUpdate("FillColor", _dPanel.getHolderId(), "yellow", null);
			} else {
			}
		}
	}
	
	private class BackgroundListener implements ActionListener{
		private String _color;
		public BackgroundListener(String color) {
			_color = color;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (_color.equals("blue")) {
				_dPanel.setBackground(java.awt.Color.BLUE);
				_dPanel.repaint();
				sendUpdate("Background", null, "blue", null);
			} else if (_color.equals("green")) {
				_dPanel.setBackground(java.awt.Color.GREEN);
				_dPanel.repaint();
				sendUpdate("Background", null, "green", null);
			} else if (_color.equals("orange")) {
				_dPanel.setBackground(java.awt.Color.ORANGE);
				_dPanel.repaint();
				sendUpdate("Background", null, "orange", null);
			}else if (_color.equals("yellow")) {
				_dPanel.setBackground(java.awt.Color.YELLOW);
				_dPanel.repaint();
				sendUpdate("Background", null, "yellow", null);
			} else {
				
			}
		}
	}
	
	private class LogoutListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			try (
					Socket soc = new Socket("localhost", 4444);
					PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
					BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
					) {
				out.println("Send");
				out.println("Disconnect");
				out.println(_artistName);
				System.exit(0);
			} catch (UnknownHostException e1) {
				// TODO Auto-generated catch block
				_messageArea.setText("Unknown Host. \nClose and try again later");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				_messageArea.setText("Server Unavailable. \nClose and try again later");
			}
		}
	}
	
	private class SendListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String text = _messageArea.getText();
			sendText(text);
			 _messageArea.setText("");
		}

	}
	
	public void sendText(String message) {
		try (
				Socket soc = new Socket("localhost", 4444);
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				) {
			out.println("Send");
			out.println("Text");
			out.println(_artistName);
			out.println(message + "\n");
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			_messageArea.setText("Unknown Host. \nClose and try again later");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			_messageArea.setText("Server Unavailable. \nClose and try again later");
		}
	}
	
	private class addShapeListener implements ActionListener{
		private int _type;
		public addShapeListener(int type) {
			_type = type;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			if (_type == 1) {
				NetworkRectangle shape = new NetworkRectangle(_dPanel, Integer.toString(_nextIdentifier));
				_nextIdentifier++;
				//send it to the server
				shape.setSize(30,30);
				shape.setVisible(true);
				shape.setFillColor(java.awt.Color.BLUE);
				shape.setBorderColor(java.awt.Color.GREEN);
				shape.setBorderWidth(1);
				shape.setLocation(30,30);
				_dPanel.addShape(shape.getIdentifier(), shape);
				sendCreate("rectangle", shape.getIdentifier());
			} else if (_type == 2){
				NetworkEllipse shape = new NetworkEllipse(_dPanel, Integer.toString(_nextIdentifier));
				_nextIdentifier++;
				//send it to the server
				shape.setSize(30,30);
				shape.setVisible(true);
				shape.setFillColor(java.awt.Color.BLUE);
				shape.setBorderColor(java.awt.Color.GREEN);
				shape.setBorderWidth(1);
				shape.setLocation(30,30);
				_dPanel.addShape(shape.getIdentifier(), shape);
				sendCreate("ellipse", shape.getIdentifier());
			} else if (_type == 3) {
				NetworkTriangle shape = new NetworkTriangle(_dPanel, Integer.toString(_nextIdentifier));
				_nextIdentifier++;
				//send it to the server
				shape.setSize(30,30);
				shape.setVisible(true);
				shape.setFillColor(java.awt.Color.BLUE);
				shape.setBorderColor(java.awt.Color.GREEN);
				shape.setBorderWidth(1);
				shape.setLocation(30,30);
				_dPanel.addShape(shape.getIdentifier(), shape);
				sendCreate("triangle", shape.getIdentifier());
			} else {
				
			}
			
		}


	}

	public void connect() {
		try (
				Socket soc = new Socket("localhost", 4444);
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				) {

			out.println("Connect");
			//We receive the unique identifier given by the server
			String identifier = in.readLine();
			if (identifier != null) {
				_identifier = identifier;
			}
			out.println(_artistName);
			out.println(_artistName + " is online now.\n");

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			_messageArea.setText("Unknown Host. \nClose and try again later");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			_messageArea.setText("Server Unavailable. \nClose and try again later");
		}
	}
	
	public void sendCreate(String type, String identifier) {
		try (
				Socket soc = new Socket("localhost", 4444);
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				) {
			out.println("Send");
			out.println("Create");
			out.println(_artistName);
			out.println(identifier);
			out.println(type);
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			_messageArea.setText("Unknown Host. \nClose and try again later");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			_messageArea.setText("Server Unavailable. \nClose and try again later");
		}
	}
	
	public void sendUpdate(String typeOfChange, String figureId, String update1, String update2) {
		if (typeOfChange.equals("FillColor") && figureId == null) {
			return;
		}
		try (
				Socket soc = new Socket("localhost", 4444);
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				) {
			out.println("Send");
			out.println("Update");
			out.println(_artistName);
			out.println(figureId);//figure identifier
			if (typeOfChange.equals("Background")) {
				out.println("Background");
				out.println(update1);
			} else if (typeOfChange.equals("Location")) {
				out.println("Location");
				out.println(update1);
				out.println(update2);
			} else if (typeOfChange.equals("FillColor")){
				out.println("FillColor");
				out.println(update1);
			} else if (typeOfChange.equals("Size")){
				out.println("Size");
				out.println(update1);
				out.println(update2);
			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			_messageArea.setText("Unknown Host. \nClose and try again later");
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			_messageArea.setText("Server Unavailable. \nClose and try again later");
		}

	}
}
