package src.metier;

import java.awt.Image;
import java.awt.Toolkit;

public class Fleur
{
	private int x;
	private int y;

	private String forme;
	private String couleur;

	public Fleur(int x, int y, String forme, String couleur)
	{ 
		this.x       = x;
		this.y       = y;
		this.forme   = forme;
		this.couleur = couleur;
	}

	public int getX() { return this.x; }
	public int getY() { return this.y; }
	public String getForme   () {return this.forme;  }
	public String getCouleur () {return this.couleur;}

	public Image getImage()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/pollens/pollen_" + this.forme + "_" + this.couleur + ".png");
	}
}