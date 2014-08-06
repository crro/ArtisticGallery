package clientSender;

import gfx.ColorEllipse;
import gfx.ColorRectangle;
import gfx.Shape;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;


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
		_messageArea.setPreferredSize(new Dimension(200, 400));
		_messageArea.setVisible(true);
		_artistName = "Picasso";
		_nextIdentifier = 0;
		
		this.setSize(500, 500);
		this.setPreferredSize(new Dimension(500, 500));
		this.setVisible(true);
		JPanel textPanel = new JPanel();
		textPanel.add(_messageArea);
		this.add(textPanel, BorderLayout.EAST);
		
		_dPanel = new DrawingPanel();
		this.add(_dPanel, BorderLayout.CENTER);
		
		JPanel shapePanel  =  new JPanel();
		_rectangleBtn =  new JButton("Rectangle");
		_rectangleBtn.addActionListener(new addShapeListener(1));
		
		_circleButton =  new JButton("Circle");
		_circleButton.addActionListener(new addShapeListener(2));
		
		
		shapePanel.add(_circleButton);
		shapePanel.add(_rectangleBtn);
		this.add(shapePanel, BorderLayout.SOUTH);
		this.connect();
	}
	
	private class addShapeListener implements ActionListener{
		private int _type;
		public addShapeListener(int type) {
			_type = type;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			
			System.out.println("YOLOOOO");
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
				_dPanel.addShape(shape);
				sendCreate("rectangle", shape.getIdentifier());
			} else {
				NetworkEllipse shape = new NetworkEllipse(_dPanel, Integer.toString(_nextIdentifier));
				_nextIdentifier++;
				//send it to the server
				shape.setSize(30,30);
				shape.setVisible(true);
				shape.setFillColor(java.awt.Color.BLUE);
				shape.setBorderColor(java.awt.Color.GREEN);
				shape.setBorderWidth(1);
				shape.setLocation(30,30);
				_dPanel.addShape(shape);
				sendCreate("ellipse", shape.getIdentifier());
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
			out.println(_artistName + "is online now.");

		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public void sendUpdate(String type, String update1, String update2) {
		try (
				Socket soc = new Socket("localhost", 4444);
				PrintWriter out = new PrintWriter(soc.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(soc.getInputStream()));
				) {
			out.println("Send");
			out.println("Update");
			out.println(_artistName);
			if (type.equals("Background")) {
				out.println(update1);
			} else if (type.equals("Location")) {
				out.println(update1);
				out.println(update2);
			} else {

			}
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
