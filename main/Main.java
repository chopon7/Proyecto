package main;

import javax.swing.UIManager;

import views.FormularioRegistro;
import views.Ventana;

public class Main {

	public static void main(String[] args) {
		
		//Ventana miVentana = new Ventana();
		
		FormularioRegistro formulario = new FormularioRegistro();

		try {
	        UIManager.setLookAndFeel(
	            UIManager.getSystemLookAndFeelClassName()
	        );
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		
		
		
		
	}

}
