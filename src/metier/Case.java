package src.metier;

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

	public Image getImageFleur ()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/pollens/pollen_" + this.fleur + ".png");
	}

	public Image getImageDepart ()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/contours/contour_case_" + this.depart + ".png");
	}

	public void trouverConnections (Grille grille)
	{
		for (int cpt = 0; cpt < 8; cpt++)
				this.connections[cpt] = null;

		if (this.fleur.equals("vide"))
			return;

		for (int check = this.x - 1; check > - 1; check--)
		{
			if (!grille.getCase(check, this.y).getFleur().equals("vide"))
			{
				this.connections[0] = grille.getCase(check, this.y);
				break;
			}
		}

		for (int check = this.y - 1; check > - 1; check--)
		{
			if (!grille.getCase(this.x, check).getFleur().equals("vide"))
			{
				this.connections[2] = grille.getCase(this.x, check);
				break;
			}
		}

		for (int check = this.x + 1; check < grille.getLargeur(); check++)
		{
			if (!grille.getCase(check, this.y).getFleur().equals("vide"))
			{
				this.connections[4] = grille.getCase(check, this.y);
				break;
			}
		}

		for (int check = this.y + 1; check < grille.getHauteur(); check++)
		{
			if (!grille.getCase(this.x, check).getFleur().equals("vide"))
			{
				this.connections[6] = grille.getCase(this.x, check);
				break;
			}
		}

		int tempX = this.x - 1;
		int tempY = this.y - 1;
		while (grille.getCase(tempX, tempY) != null)
		{
			if (!grille.getCase(tempX, tempY).getFleur().equals("vide"))
			{
				this.connections[1] = grille.getCase(tempX, tempY);
				break;
			}
			tempX -= 1;
			tempY -= 1;
		}

		tempX = this.x + 1;
		tempY = this.y - 1;
		while (grille.getCase(tempX, tempY) != null)
		{
			if (!grille.getCase(tempX, tempY).getFleur().equals("vide"))
			{
				this.connections[3] = grille.getCase(tempX, tempY);
				break;
			}
			tempX += 1;
			tempY -= 1;
		}

		tempX = this.x + 1;
		tempY = this.y + 1;
		while (grille.getCase(tempX, tempY) != null)
		{
			if (!grille.getCase(tempX, tempY).getFleur().equals("vide"))
			{
				this.connections[5] = grille.getCase(tempX, tempY);
				break;
			}
			tempX += 1;
			tempY += 1;
		}

		tempX = this.x - 1;
		tempY = this.y + 1;
		while (grille.getCase(tempX, tempY) != null)
		{
			if (!grille.getCase(tempX, tempY).getFleur().equals("vide"))
			{
				this.connections[7] = grille.getCase(tempX, tempY);
				break;
			}
			tempX -= 1;
			tempY += 1;
		}
	}
}