package clientReceiver;

import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextPane;

public class DisplayPanel extends JPanel {
	private DrawingPanel _dp;
	private JTextArea _textPane;
	
	public DisplayPanel() {
		this.setLayout(new BorderLayout());
		_dp = new DrawingPanel();
		this.add(_dp, BorderLayout.CENTER);
		_textPane = new JTextArea();
		_textPane.setSize(500, 100);
		_textPane.setVisible(true);
		this.add(_textPane, BorderLayout.SOUTH);
		this.setVisible(true);
		
	}
	
	public void addText(String text) {
		_textPane.setText("");
		_textPane.append(text);
	}
	
	public DrawingPanel getDrawingPanel() {
		return _dp;
	}

}
