package src.metier;

public class Carte
{
	private char forme;
	private boolean couleur;

	public Carte () {}
	
	public Carte (char f, boolean c)
	{
		this.forme   = f;
		this.couleur = c;
	}

	public void setForme   (char f   ) {this.forme   = f;}
	public void setCouleur (boolean c) {this.couleur = c;}

	public char    getForme   () {return this.forme;  }
	public boolean getCouleur () {return this.couleur;}
}