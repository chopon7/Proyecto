package utils;

import java.awt.Font;

import views.FormularioRegistro;

public class AppFont {

	private static Font base;
	
	static {
		base = null;
				
		try {
			base = Font.createFont(Font.TRUETYPE_FONT, FormularioRegistro.class.getResourceAsStream("/font"));
			base = base.deriveFont(14f);
		}catch(Exception e) {
			base = new Font("Arial", Font.PLAIN, 14);
		}
	}
	
	
	public static Font normal() {
		
		return base.deriveFont(14f);
		
	}
	
	public static Font small() {
		
		return base.deriveFont(12f);
		
	}

	
	public static Font title() {
		
		//FALTA POR COMPLETAR
		return base.deriveFont(Font.BOLD, 20f);
		
	}
	
	
	
	
}
