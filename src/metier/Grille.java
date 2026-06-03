package src.metier;

public class Grille
{
	private int largeur;
	private int hauteur;
	private int tailleCase;
	private Case[][] cases;

	public Grille(int largeur, int hauteur, int tailleCase)
	{
		this.largeur    = largeur;
		this.hauteur    = hauteur;
		this.tailleCase = tailleCase;
		this.cases = new Case[largeur][hauteur];

		for (int x = 0; x < largeur; x++)
			for (int y = 0; y < hauteur; y++)
			{
				this.cases[x][y] = new Case();
			}
	}

	public int getLargeur   () {return largeur;}
	public int getHauteur   () {return hauteur;}
	public int getTailleCase() {return this.tailleCase;}

	public Case getCase(int x, int y)
	{
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur)
			return cases[y][x];
		return null;
	}

	public void setCase(int x, int y, Case vCase)
	{
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur)
			cases[y][x] = vCase;
	}
}