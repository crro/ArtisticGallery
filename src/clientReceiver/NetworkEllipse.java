package clientReceiver;

import gfx.ColorEllipse;

public class NetworkEllipse extends ColorEllipse {
	private String _identifier;
	

	public NetworkEllipse(DrawingPanel dp, String id) {
		super(dp);
		_identifier = id;
	}
	public String getIdentifier() {
		return _identifier;
	}
	
	public void setIdentifier(String id) {
		_identifier = id;
	}

}
