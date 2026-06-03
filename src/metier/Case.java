package src.metier;

import java.awt.Color;

public class Case
{
	private Fleur  fleur;
	private Color plaine;

	public Case ()
	{
		this.fleur  = null;
		this.plaine = new Color(255, 255, 255);
	}

	public void setFleur  (Fleur fleur ) {this.fleur = fleur;  }
	public void setPlaine (Color plaine) {this.plaine = plaine;}

	public Fleur  getFleur () {return this.fleur; }
	public Color getPlaine () {return this.plaine;}
}