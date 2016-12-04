package server.gui;

import java.awt.Color;
import java.util.Random;

public class UserFontColorGenerator {

	private static Random rand = new Random();
	
	public static Color getNewFontColor(){
		int r=rand.nextInt(255);
		int g=rand.nextInt(255);
		int b=rand.nextInt(255);
		return new Color(r, g, b);
	}
	
}
