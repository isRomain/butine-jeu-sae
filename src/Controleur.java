package src;

import src.ihm.*;
import src.metier.*;

public class Controleur
{
	private Metier metier;
	private FramePlateau frame;

	public Controleur()
	{
		this.metier = new Metier();
		this.frame  = new FramePlateau(this);
	}

	// Méthodes du jeu en appelant Metier
	/* public boolean deplacer... */
	/* public Fleur   getFleur... */

	public static void main(String[] args)
	{
		new Controleur();
	}
}