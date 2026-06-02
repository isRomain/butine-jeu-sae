package src.metier;

public class Joueur
{
	private Couleur couleur;

	private Fleur position;

	private int score;

	public Joueur()
	{
		this.score = 0;
	}

	public Joueur(Couleur c)
	{
		this.couleur = c;
		this.score   = 0;
	}

	public int getScore()
	{
		return this.score;
	}
}