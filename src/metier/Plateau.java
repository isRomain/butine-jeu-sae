package src.metier;

import java.util.ArrayList;

public class Plateau
{
	private ArrayList<Fleur>   ensFleurs;
	private ArrayList<Sentier> ensSentiers;
	private ArrayList<Plaine>  ensPlaines;

	private int largeur;
	private int hauteur;

	public Plateau()
	{
		this.ensFleurs   = new ArrayList<Fleur>();
		this.ensSentiers = new ArrayList<Sentier>();
		this.ensPlaines  = new ArrayList<Plaine>();
	}

	public ArrayList<Fleur> getEnsFleurs()
	{
		return this.ensFleurs;
	}
}