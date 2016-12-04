package gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Random;
import java.util.Vector;

public class UserColorFont {

	private Color color;
	private Random rand;
	
	public Color getNewFontColor(){
		rand = new Random();
		int r=rand.nextInt(255);
		int g=rand.nextInt(255);
		int b=rand.nextInt(255);
		return (color=new Color(r, g, b));
	}
	
}
