package clientSender;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class App {
	
	public App() {
		JFrame frame = new JFrame("Client Sender");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		MainPanel mp = new MainPanel();
		frame.add(mp, BorderLayout.CENTER);
		frame.pack();
		frame.setVisible(true);
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new App();
		
	}

}
