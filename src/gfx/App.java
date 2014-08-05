package gfx;

/**
 * The purpose of this class is so that you have 
 * something to test your shape with. You must make at
 * least one subclass of your Shape to test in this GUI.
 * When you run cs015_runDemo gfx, this is what you see.
 * Instructions for its use are below.
 * 
 *Comments for the GFX assignment are in the lines 43 and 44.
 */

public class App extends cs015.prj.Shape.ViewerFrame {

    public App(String s){
	super(s);

	/*
	 * Pass in one of your Shape subclasses here. You might be wondering
	 * where your shapes will get their container from. For a container,
	 * cs015.prj.Shape.ViewerFrame has a protected drawing panel. It is 
	 * named _dp.
	 * 
	 * Since this class is a subclass of cs015.prj.Shape.ViewerFrame, 
	 * you have access to the _dp instance variable from within this 
	 * class. 
	 *
	 * Thus, to use the ViewerFrame to test your Shape, all you need is 
	 * this:
	 *
	 * gfx.Ellipse ellipse = new gfx.Ellipse(_dp);
	 *
	 * Where Ellipse is one of your Shape subclasses.
	 * Then you need to call:
	 *
	 * this.setShape(ellipse);
	 *
	 * and you should be all set! Just compile everything and run as:
	 * java gfx.App
	 *
	 */
	ColorTriangle triangle = new ColorTriangle(_drawingPanel);
	ColorRectangle rectangle = new ColorRectangle(_drawingPanel);
	ColorEllipse ellipse = new ColorEllipse(_drawingPanel);// Instance of my shape subclass.
	this.setShape(ellipse);// Here I'm setting my shape subclass in the application.
	// this.setShape(<your shape subclass here>);


    }

    /* Still don't need to worry about this yet. It just starts the App. */
    public static void main(String[] argv){
	new App("Shape Viewer");
    }

}
