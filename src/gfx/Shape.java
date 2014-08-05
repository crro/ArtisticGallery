package gfx;

/**
 * Welcome to the beginning of your very own graphics package!
 * This graphics package will be used in most of your assignments from 
 * now on.
 *
 * This should look A LOT like the code you've seen in lecture (HINT HINT).
 * At first glance this class looks really dense, but don't worry most of
 * the methods you have to fill in wont be very long.
 *
 * REMEMBER most of the code you will write here will be code you've seen 
 * before (WINK WINK).
 *
 * All of the accessor methods return a dummy value so that this file can 
 * be compiled from the start.
 *
 * Feel free to add other functionality, but keep in mind this is a shape 
 * and shouldn't have capabilities that are more specific to say 
 * bees or something.
 *
 * Some bells and whistles you might want to add:
 *  - set transparency (look at java.awt.Color in the docs)
 *  - anti aliasing (getting rid of jagged edges..)
 *
 * @dcorrea ]
 *
 *The comments for GFX are in lines 45, 60-65, 73, 81, 86, 91, 96, 101, 106, 114, 119, 124, 133-136, 138, 139, 145, 150, 155, 159, 169, 174, 179, 184, 199-206.
 */
public abstract class Shape implements cs015.prj.Shape.ShapeInterface {

	/** Used to store some geometric data for this shape. */
	private java.awt.geom.RectangularShape _shape;

	/** Reference to containing subclass of JPanel. */
	private javax.swing.JPanel _panel;

	/** Border and Fill Colors. */
	private java.awt.Color _borderColor, _fillColor;

	/** Rotation (must be in radians). */
	private double _rotationAngle;

	/** Border Width */
	private int _borderWidth;

	/** Indicates whether or not the shape should wrap. */
	private boolean _wrapping;

	/** Whether or not the shape should paint itself. */
	private boolean _isVisible;

	/** 
	 * Initialize all instance variables here.  You'll need to store the
	 * containing subclass of JPanel to deal with wrapping and some of the
	 * extra credit stuff.
	 */
	public Shape(javax.swing.JPanel container, 
		java.awt.geom.RectangularShape s) {
		_shape = s;//Here I'm instantiating _shape by making it point to the parameter "s" received by the constructor of this class of type java.awt.geom.RectangularShape.
		_panel = container;//Here I'm instantiating _container by making it point to the parameter "container" received by the constructor of this class of type javax.swing.JPanel.
		this.setBorderColor(java.awt.Color.BLACK);//Here I'm setting the initial border color of the shape that appears in the drawing panel as black.
		this.setFillColor(java.awt.Color.BLACK);//Here I'm setting the initial color of the whole figure of the shape that appear in the drwing panel as black.	
	}

	/**
	 * Should return the x location of the top left corner of 
	 * shape's bounding box.
	 */
	public double getX() {
		return _shape.getX();//I'm getting the shape's bounding box x location by calling this method so that it gets returned.
	}

	/** 
	 * Should return the y location of the top left corner of 
	 * shape's bounding box.
	 */
	public double getY() {
		return _shape.getY();//I'm getting the shape's bounding box y location by calling this method so that it gets returned.
	}

	/** Should return height of shape's bounding box. */
	public double getHeight() {
		return _shape.getHeight();//I'm getting the shape's bounding box height by calling this method so that it gets returned.
	}

	/** Should return width of shape's bounding box. */
	public double getWidth() {
		return _shape.getWidth();//I'm getting the shape's bounding box width by calling this method so that it gets returned.
	}

	/** Should return the border color you are storing. */
	public java.awt.Color getBorderColor() {
		return _borderColor;//I'm setting the _borderColor to be returned.
	}

	/** Should return the fill color you are storing. */
	public java.awt.Color getFillColor() {
		return _fillColor;//I'm setting the _fillColor to be returned.
	}

	/** Should return the rotation you are storing. */
	public double getRotation() {
		return _rotationAngle*180/Math.PI;//I'm converting the radians back into degrees before they get returned. 
	}

	/** 
	 * Should return the width of the brush stroke for 
	 * the outline of your shape.
	 */
	public int getBorderWidth() {
		return _borderWidth;// I'm setting the _borderWidth to be returned.
	}

	/** Should return whether or not the shape is wrapping. */
	public boolean getWrapping() {
		return _wrapping;//I'm setting the _wrapping to be returned.
	}

	/** Should return whether or not the shape is visible. */
	public boolean getVisible() {
		return _isVisible;//I'm setting the _isVisible to be returned.
	}

	/** 
	 * Set the location of shape. Make sure to wrap if the wrap 
	 * boolean is true. Refer to the help session slides to see
	 * how wrapping is done.
	 */
	public void setLocation(double x, double y) {
		if (_wrapping) { //Here I'm saying that if _wrapping is true, then the following part of the method must be executed.
			double newX = ((x % _panel.getSize().width) + _panel.getSize().width) % _panel.getSize().width;//I created a new local variable of type double to held the x location in case of wrapping
			double newY = ((y % _panel.getSize().height) + _panel.getSize().height) % _panel.getSize().height;//I created a new local variable of type double to held the y location in case of wrapping.
			_shape.setFrame(newX, newY, _shape.getWidth(), _shape.getHeight());//Now I use the local variables I just created to determine the new position of the shape in case of wrapping.
		}
		else {//In case _wrapping is not true then the following part of the method must be executed.
			_shape.setFrame(x, y, _shape.getWidth(), _shape.getHeight());//Here, if wrapping is not occuring then the location is the one sent originally as actual parameters to this method.
		}
	}

	/** Set the size of shape. */
	public void setSize(double width, double height) {
		_shape.setFrame(_shape.getX(), _shape.getY(), width, height);//I'm setting the new size of the shape (by using the actual parameters passed) but conserving the shape's location.
	}

	/** Set the border color. */
	public void setBorderColor(java.awt.Color c) {
		_borderColor = c;//Here I'm making _borderColor point to the color received by the method.
	}

	/** Set the fill color. */
	public void setFillColor(java.awt.Color c) {
		_fillColor = c;//Here I'm making _fillColor point to the color received by the method.
	}

	/** Set the color of the whole shape. */
	public void setColor(java.awt.Color c) {//Here I'm setting both _fillColor and _borderColor point to the same color received by the method.
		_fillColor = c;
		_borderColor = c;
	}

	/**
	 * Set the rotation of the shape. Refer to the lecture to see
	 * how this should be done
	 */
	public void setRotation(double degrees) {
		_rotationAngle = degrees*Math.PI/180;//First I converted the degrees into radians because that is what java uses. Then I'm making _rotationAngle point to this value. 
	}

	/** Set how thick the shapes outline will be. */
	public void setBorderWidth(int width) {
		_borderWidth = width;//I'm setting _borderWidth point to the width (of type int) received by the method.
	}

	/** Set whether or not the shape should wrap. */
	public void setWrapping(boolean wrap) {
		_wrapping = wrap;//I'm setting the value(true or false) of the boolean _wrapping point to the value received by this method. 
	}

	/** Set whether or not the shape should paint itself. */
	public void setVisible(boolean visible) {
		_isVisible = visible;//I'm setting the value(true or false) of the boolean _isVisible point to the value received by this method.
	}

	/**
	 * This method is best explained in pseudocode:
	 *	If shape is visible
	 *	    rotate graphics
	 *	    set the brush stroke (width) of the graphics
	 *	    set the color of the graphics to the fill color of the shape
	 *	    fill the shape
	 *	    set the color of the graphics to the border color of the shape
	 *	    draw the shape
	 *	    un-rotate the graphics
	 */
	public void paint(java.awt.Graphics2D brush) {
		if (_isVisible) {//I'm saying that if _isVisible is true then the following part of the method must be executed, if it is false then it should skip it.
			brush.rotate(_rotationAngle, _shape.getCenterX(), _shape.getCenterY());//I'm rotating the brush using the center as reference.
			brush.setStroke(new java.awt.BasicStroke(_borderWidth));//I'm setting the stroke of the brush to be equal to the vauel of _borderWidth.
			brush.setColor(_fillColor);//I'm setting the color of the brush equal to the color of _fillColor.
			brush.fill(_shape);//I'm making the brush fill the shape with the color of _fillColor.
			brush.setColor(_borderColor);//I'm setting the color of the brush equal to the color of _borderColor.
			brush.draw(_shape);//I'm making the brush draw the shape with the color of _borderColor
			brush.rotate(-_rotationAngle, _shape.getCenterX(), _shape.getCenterY());//I'm unrotating the brush using the center as reference. This makes the image look as if it was rotated.
		}
	}

	/** 
	 * Should return true if the point is within the shape.  
	 * There's a special case for when the shape is rotated,
	 * which we have handled for you.
	 *
	 * YOU DO NOT NEED TO TOUCH THIS METHOD.
	 */
	public boolean contains(java.awt.Point p) {
		if (0 != _rotationAngle) {
			double x = _shape.getCenterX();
			double y = _shape.getCenterY();
			java.awt.geom.AffineTransform trans = java.awt.geom.AffineTransform.getRotateInstance(_rotationAngle, x, y);
			java.awt.Shape s = trans.createTransformedShape(_shape);
			return s.contains(p);
		}
		return _shape.contains(p);
	}

	/**
	 * This should be called when the shape is clicked.
	 * You'll want to overwrite this in subclasses to do something useful.
	 * Should stay empty in this class 
	 */
	public void react() {}
}
