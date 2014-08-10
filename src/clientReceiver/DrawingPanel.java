package clientReceiver;

import gfx.Shape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel{
	
	private HashMap<String, Shape> _shapes;
	private Shape _holderShape;

	public DrawingPanel() {
		_shapes = new HashMap<String, Shape>();
		this.setPreferredSize(new Dimension(200, 200));
		this.setBackground(java.awt.Color.YELLOW);
		this.setVisible(true);
	}
	/*
	 * This method paints the proxy, it loops through the border and paints it
	 * And finally, it loops through the interior of the board game painting it too.
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;//Coercion
		Set<String> keys = _shapes.keySet();
		for (String key : keys) {
			_shapes.get(key).paint(brush);
		}
//		int size = _shapes.size();
//		for (int i = 0; i < size; i++) {
//			_shapes.get(i).paint(brush);
//		}
	}
	
	public void addShape(String name, Shape s) {
		_shapes.put(name, s);
		this.repaint();
	}
	
	public void repaintShape(String key, String color) {
		Shape s = _shapes.get(key);
		if (color.equals("blue")) {
			s.setFillColor(java.awt.Color.BLUE);
		} else if (color.equals("yellow")) {
			s.setFillColor(java.awt.Color.YELLOW);
		} else if (color.equals("green")) {
			s.setFillColor(java.awt.Color.GREEN);
		} else if (color.equals("orange")){
			//undefined color
			s.setFillColor(java.awt.Color.ORANGE);
		}
		this.repaint();
	}
	
	public void changeBackground(String color) {
		if (color.equals("blue")) {
			this.setBackground(java.awt.Color.BLUE);
		} else if (color.equals("yellow")) {
			this.setBackground(java.awt.Color.YELLOW);
		} else if (color.equals("green")) {
			this.setBackground(java.awt.Color.GREEN);
		} else if (color.equals("orange")){
			//undefined color
			this.setBackground(java.awt.Color.ORANGE);
		}
		this.repaint();
	}

	//This method is called when a piece needs to move from the server.
	public void moveShape(String key, String x, String y) {
		Shape s = _shapes.get(key);
		s.setLocation(Double.parseDouble(x), Double.parseDouble(y));
		this.repaint();
	}
	public boolean isEmpty() {
		return _shapes.isEmpty();
	}
	
	public void move(String id, String x, String y) {
		Shape s = _shapes.get(id);
		s.setLocation(Double.parseDouble(x), Double.parseDouble(y));
	}

}
