package clientSender;

import gfx.Shape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	private ArrayList<Shape> _shapes;
	private Shape _holderShape;

	public DrawingPanel() {
		_shapes = new ArrayList<Shape>();
		this.setPreferredSize(new Dimension(500, 500));
		this.setBackground(java.awt.Color.WHITE);
		this.addMouseListener(new ClickListener());
		this.addMouseMotionListener(new MotionListener());
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
	
	public void addShape(Shape s) {
		_shapes.add(s);
		this.repaint();
	}
	private class ClickListener implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {
			System.out.println("clicked");
			System.out.print("x: " + e.getX() + " ");
			System.out.println("y: " + e.getY());
			int size = _shapes.size();
			for (int i = 0; i < size; i++) {
				if (_shapes.get(i).contains(e.getPoint())) {
					//here we can get
				}
			}
		}
		public void mousePressed (MouseEvent e) {
			System.out.println("pressed");
			int size = _shapes.size();
			for (int i = 0; i < size; i++) {
				if (_shapes.get(i).contains(e.getPoint())) {
					_shapes.get(i).setBorderColor(java.awt.Color.RED);
					_shapes.get(i).setBorderWidth(2);
					DrawingPanel.this.repaint();
					_holderShape = _shapes.get(i);
					break;
				}
			}
		}
		public void mouseReleased (MouseEvent e) {
			System.out.println("released");
			int size = _shapes.size();
			for (int i = 0; i < size; i++) {
				if (_shapes.get(i).contains(e.getPoint())) {
					_shapes.get(i).setBorderColor(java.awt.Color.GREEN);
					_shapes.get(i).setBorderWidth(1);
					DrawingPanel.this.repaint();
					_holderShape = null;
				}
			}
		}
		public void mouseEntered (MouseEvent e) {}
		public void mouseExited (MouseEvent e) {}
	}
	private class MotionListener implements MouseMotionListener {
		
		public MotionListener() {
		}
		public void mouseDragged(MouseEvent e) {
			System.out.println("clicked before dragf");
			System.out.print("x: " + e.getX() + " ");
			System.out.println("y: " + e.getY());
			_holderShape.setLocation(e.getX(), e.getY());
			DrawingPanel.this.repaint();
//			int size = _shapes.size();
//			for (int i = 0; i < size; i++) {
//				if (_shapes.get(i).contains(e.getPoint())) {
//					_shapes.get(i).setLocation(e.getX(), e.getY());
//					DrawingPanel.this.repaint();
//				}
//			}
		}
		public void mouseMoved(MouseEvent e) {
			System.out.print("x: " + e.getX() + " ");
			System.out.println("y: " + e.getY());
		}
		
	}

}
