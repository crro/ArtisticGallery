package clientSender;

import gfx.ColorTriangle;

public class NetworkTriangle extends ColorTriangle {
	private String _identifier;
	public NetworkTriangle(DrawingPanel dp, String id) {
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
