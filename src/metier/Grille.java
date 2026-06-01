package src.metier;

public class Grille
{
	private int largeur;
	private int hauteur;
	private int[][] donnees;

	public Grille(int largeur, int hauteur)
	{
		this.largeur = largeur;
		this.hauteur = hauteur;
		this.donnees = new int[hauteur][largeur];
	}

	public int getLargeur()
	{
		return largeur;
	}

	public int getHauteur()
	{
		return hauteur;
	}

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
}
