package src.metier;

import java.awt.Color;

public class Sentier
{
	private Fleur fleur1;
	private Fleur fleur2;

	private Color couleur;

	public Sentier()
	{
	}

	public Sentier(Fleur f1, Fleur f2)
	{
		this.fleur1 = f1;
		this.fleur2 = f2;
	}

	public Fleur getFleur1() { return this.fleur1; }
	public Fleur getFleur2() { return this.fleur2; }
}