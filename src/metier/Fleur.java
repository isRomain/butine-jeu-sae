package src.metier;

public class Fleur
{
	private int x;
	private int y;

	private char forme;
	private Couleur couleur;

	public Fleur()
	{
	}

	public Fleur(int x, int y, char forme, String plaine)
	{ 
		this.x      = x;
		this.y      = y;
		this.forme  = forme;
	}

	public int getX() { return this.x; }
	public int getY() { return this.y; }

    public void setCouleur( Couleur coul ) { this.couleur = coul; }

	public char getForme()
	{
		return this.forme;
	}
}