package server;

import java.io.PrintWriter;
import java.net.Socket;

public class Holder {

	private Socket _socket;
	private PrintWriter _writer;
	
	public Holder(Socket socket, PrintWriter writer) {
		_socket = socket;
		_writer = writer;
	}

	public Socket getSocket() {
		return _socket;
	}

	public void setSocket(Socket socket) {
		_socket = socket;
	}

	public PrintWriter getWriter() {
		return _writer;
	}

	public void setWriter(PrintWriter writer) {
		_writer = writer;
	}

}
