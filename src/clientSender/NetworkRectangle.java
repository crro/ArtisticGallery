package clientSender;

import gfx.ColorRectangle;

public class NetworkRectangle extends ColorRectangle {
	private String _identifier;
	
	public NetworkRectangle(DrawingPanel dp, String id) {
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
