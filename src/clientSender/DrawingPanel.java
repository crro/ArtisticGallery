package clientSender;

import gfx.Shape;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JPanel;

public class DrawingPanel extends JPanel {
	private HashMap<String, Shape> _shapes;
	private Shape _holderShape;
	private String _holderId;
	private MainPanel _mp;

	public DrawingPanel(MainPanel mp) {
		_shapes = new HashMap<String, Shape>();
		this.setSize(500,500);
		this.setPreferredSize(new Dimension(500, 500));
		this.setBackground(java.awt.Color.WHITE);
		this.addMouseListener(new ClickListener());
		this.addMouseMotionListener(new MotionListener());
		_mp = mp;
	}
	/*
	 * This method paints the proxy, it loops through the border and paints it
	 * And finally, it loops through the interior of the board game painting it too.
	 */
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D brush = (Graphics2D) g;//Coercion
		Set<String> set = _shapes.keySet();
		for (String key : set) {
			_shapes.get(key).paint(brush);
		}
	}
	
	public void addShape(String id, Shape s) {
		_shapes.put(id, s);
		this.repaint();
	}
	private class ClickListener implements MouseListener {
		
		public void mouseClicked(MouseEvent e) {
			Set<String> set = _shapes.keySet();
			boolean select = false;
			for (String key : set) {
				if (_shapes.get(key).contains(e.getPoint())) {
					select = true;
					_holderShape = _shapes.get(key);
					_holderId = key;
				}
			}
			if (!select) {
				for (String key : set) {
					_shapes.get(key).setBorderColor(java.awt.Color.GREEN);
					_shapes.get(key).setBorderWidth(1);
				}
				_holderShape = null;
				_holderId = null;
				DrawingPanel.this.repaint();				
			}
		}
		public void mousePressed (MouseEvent e) {
			Set<String> set = _shapes.keySet();
			for (String key : set) {
				if (_shapes.get(key).contains(e.getPoint())) {
					_shapes.get(key).setBorderColor(java.awt.Color.RED);
					_shapes.get(key).setBorderWidth(2);			
					_holderShape = _shapes.get(key);
					_holderId = key;
					break;
				}
			}
			for (String key : set) {
				if (key == _holderId) {
					continue;
				}
				_shapes.get(key).setBorderColor(java.awt.Color.GREEN);
				_shapes.get(key).setBorderWidth(1);
				
			}
			DrawingPanel.this.repaint();
		}
		public void mouseReleased (MouseEvent e) {
			
		}
		public void mouseEntered (MouseEvent e) {}
		public void mouseExited (MouseEvent e) {}
	}
	private class MotionListener implements MouseMotionListener {
		
		public MotionListener() {
		}
		public void mouseDragged(MouseEvent e) {
			if (_holderShape != null) {
				_holderShape.setLocation(e.getX(), e.getY());
				DrawingPanel.this.repaint();
				_mp.sendUpdate("Location", _holderId, String.valueOf(e.getX()), String.valueOf(e.getY()));
			}
			
			//Here is where we need to send the signal to the server
			//We probably need a reference to the MainPanel here.
		}
		public void mouseMoved(MouseEvent e) {
		}
		
	}
	
	public String getHolderId() {
		return _holderId;
	}
	
	public void setHolderColor(java.awt.Color color) {
		if (_holderShape != null) {
			_holderShape.setFillColor(color);			
		}
		//this.repaint();
	}
	
	
	
	public void changeWidth(double width) {
		if (_holderShape != null) {
			_holderShape.setSize(width, _holderShape.getHeight());
			this.repaint();
			_mp.sendUpdate("Size", _holderId, String.valueOf(width), String.valueOf(_holderShape.getHeight()));
		}
	}
	public void changeHeight(double height) {
		if (_holderShape != null) {
			_holderShape.setSize(_holderShape.getWidth(), height);
			this.repaint();
			_mp.sendUpdate("Size", _holderId, String.valueOf(_holderShape.getWidth()),  String.valueOf(height));
		}
	}

}
