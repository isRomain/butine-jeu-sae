package src.metier;

import java.awt.Color;

public class Grille
{
	private int largeur;
	private int hauteur;
	private int tailleCase;
	private int[][] donnees;
	private Color[][] plaines;

	public Grille(int largeur, int hauteur, int tailleCase)
	{
		this.largeur    = largeur;
		this.hauteur    = hauteur;
		this.tailleCase = tailleCase;
		this.donnees    = new int[hauteur][largeur];
		this.plaines    = new Color[hauteur][largeur];
	}

	public int getLargeur   () {return largeur;}
	public int getHauteur   () {return hauteur;}
	public int getTailleCase() {return this.tailleCase;}

	public int getValeur(int x, int y)
	{
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur)
			return donnees[y][x];
		return 0;
	}

	public void setValeur(int x, int y, int valeur)
	{
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur)
			donnees[y][x] = valeur;
	}

	public Color getCouleurPlaine(int x, int y)
	{
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur)
			return plaines[y][x];
		return null;
	}

	public void setCouleurPlaine(int x, int y, Color couleur)
	{
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur)
			plaines[y][x] = couleur;
	}
}
