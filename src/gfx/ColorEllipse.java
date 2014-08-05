package gfx;
/**
*@dcorrea
*/
public class ColorEllipse extends Shape {//I'm making this class a subclass of the Shape class
	public ColorEllipse(javax.swing.JPanel container){//I'm making this shape's subclass ask for a container of type javax.swing.JPanel when isntantiated. 
		super(container, new java.awt.geom.Ellipse2D.Double());//I'm passing the superclass constructor the container that will be 
								       //received when the shape is created and I'm also passing a new ellipse of type java.awt.geom.Ellipse2D.Double.
	}
}	
