package ClientePreguntados;

import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import Packages.EndClientConectionRequest;

public class ClosingListener implements WindowListener {

	
	public void windowActivated(WindowEvent e) {
		
		
	}

	
	public void windowClosed(WindowEvent e) {
		
		
	}

	
	public void windowClosing(WindowEvent e) {
        EndClientConectionRequest er = new EndClientConectionRequest();
        Connection.sendPackage(er);
        System.exit(0);
	}

	
	public void windowDeactivated(WindowEvent e) {
		
		
	}

	
	public void windowDeiconified(WindowEvent e) {
		
		
	}

	
	public void windowIconified(WindowEvent e) {
		
		
	}

	
	public void windowOpened(WindowEvent e) {
		
		
	}

}
