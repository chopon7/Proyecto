package main;

import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;

import views.Ventana;

public class Main {

	public static void main(String[] args) {
		
		try {
	        UIManager.setLookAndFeel(new FlatLightLaf());
	    } catch (Exception ex) {
	        System.err.println("Fallo al inicializar FlatLaf");
	    }
		
		new Ventana();

	}

}
