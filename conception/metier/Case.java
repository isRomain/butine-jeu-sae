package conception.metier;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

public class Case
{
	private int    x, y;
	private Color  plaine;
	private String fleur;
	private String depart;

	private Case[] connections;

	public Case (int x, int y)
	{
		this.x = x;
		this.y = y;

		this.plaine = new Color(255, 255, 255, 200);
		this.fleur  = "vide";
		this.depart = "vide";

		this.connections = new Case[8];
	}

	public void setPlaine ( Color plaine  ) { this.plaine = plaine; }
	public void setFleur  ( String fleur  ) { this.fleur  = fleur ; }
	public void setDepart ( String depart ) { this.depart = depart; }

	public int    getX      () { return this.x;}
	public int    getY      () { return this.y;}
	public Color  getPlaine () { return this.plaine; }
	public String getFleur  () { return this.fleur;  }
	public String getDepart () { return this.depart; }

	public Case   getConnection (int nb) {return this.connections[nb];}

	//On retourne directement l'image de la fleur
	public Image getImageFleur ()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/pollens/pollen_" + this.fleur + ".png");
	}

	//On retourne directement l'image du départ
	public Image getImageDepart ()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/contours/contour_case_" + this.depart + ".png");
	}

	//Trouver toutes les connections des fleurs
	public void trouverConnections (Grille grille)
	{
		//Tout est remis à zero
		for (int cpt = 0; cpt < 8; cpt++)
				this.connections[cpt] = null;

		//Si la case est vide, pas de connection
		if (this.fleur.equals("vide"))
			return;

		for (int cpt = 1; cpt < Math.max(grille.getHauteur(), grille.getLargeur()); cpt++)
		{
			// Vérification dans le sens horaire
			verifierLaCase(grille, 0, this.x - cpt, this.y      ); // Ouest
			verifierLaCase(grille, 1, this.x - cpt, this.y - cpt); // Nord-Ouest
			verifierLaCase(grille, 2, this.x      , this.y - cpt); // Nord
			verifierLaCase(grille, 3, this.x + cpt, this.y - cpt); // Nord-Est
			verifierLaCase(grille, 4, this.x + cpt, this.y      ); // Est
			verifierLaCase(grille, 5, this.x + cpt, this.y + cpt); // Sud-Est
			verifierLaCase(grille, 6, this.x      , this.y + cpt); // Sud
			verifierLaCase(grille, 7, this.x - cpt, this.y + cpt); // Sud-Ouest
		}
	}

	private void verifierLaCase (Grille grille, int cpt, int x, int y)
	{
		if (grille.getCase(x, y) != null) //Hop, pas d'ereurs
		{
			if (!grille.getCase(x, y).getFleur().equals("vide") && this.connections[cpt] == null) // On cherche la première fleur sur son passage
			{
				this.connections[cpt] = grille.getCase(x, y);
			}
		}
	}
}