package clientReceiver;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;

public class App {

	public App() throws BadLocationException {
		//Create the frame
		JFrame frame = new JFrame("Client Receiver");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Create the panel and its containing elements
		MainPanel rec =  new MainPanel("localhost", 4444);
		frame.add(rec);
		frame.pack();
		frame.setVisible(true);
		
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
