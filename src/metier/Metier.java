package src.metier;

import java.util.ArrayList;

public class Metier
{
	private Plateau plateau;
	private ArrayList<Joueur> ensJoueurs;
	private ArrayList<Carte>  ensCartes;

	public Metier()
	{
		this.plateau    = new Plateau();
		this.ensJoueurs = new ArrayList<Joueur>();
		this.ensCartes  = new ArrayList<Carte>();
	}

	public Plateau getPlateau()
	{
		return this.plateau;
	}
}