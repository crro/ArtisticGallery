package clientReceiver;

import gfx.Shape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	
	private HashMap<String, Shape> _shapes;
	private Shape _holderShape;

	public DrawingPanel() {
		_shapes = new HashMap<String, Shape>();
		this.setPreferredSize(new Dimension(200, 200));
		this.setBackground(java.awt.Color.YELLOW);
	}
	/*
	 * This method paints the proxy, it loops through the border and paints it
	 * And finally, it loops through the interior of the board game painting it too.
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;//Coercion
		int size = _shapes.size();
		for (int i = 0; i < size; i++) {
			_shapes.get(i).paint(brush);
		}
	}
	
	public void addShape(String name, Shape s) {
		_shapes.put(name, s);
		this.repaint();
	}
	
	//This method is called when a piece needs to move from the server.
	public void move() {
			
	}
	public boolean isEmpty() {
		return _shapes.isEmpty();
	}
	

}
