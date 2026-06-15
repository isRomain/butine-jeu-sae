package jeu.metier;

import java.util.ArrayList;
import java.util.Collections;

public class Pile
{

	// On énumere toutes les cartes
	private String[] cartes = {"carte_clair_carre.png"   ,
	                           "carte_clair_croix.png"   ,
							   "carte_clair_reine.png"   ,
							   "carte_clair_rond.png"    ,
							   "carte_clair_triangle.png",
							   "carte_fonce_carre.png"   ,
							   "carte_fonce_croix.png"   ,
							   "carte_fonce_reine.png"   ,
							   "carte_fonce_rond.png"    ,
							   "carte_fonce_triangle.png"};

	// Liste qui va contenir les cartes mélangées
	private ArrayList<String> pile;

	private int cptCarte;

	// Carte forcée par le mode débug (null = pioche normale)
	private String prochaineCarte;

	public Pile ()
	{
		this.pile = new ArrayList<String>();

    	Collections.addAll(this.pile, this.cartes);
    	Collections.shuffle(this.pile);

		this.prochaineCarte = null;
	}

	// Liste des 10 cartes (utilisee par le mode debug)
	public String[] getCartes() { return this.cartes; }

	// Force la carte qui sera renvoyee a la prochaine pioche
	public void forcerProchaine(String carte) { this.prochaineCarte = carte; }

	public String piocher()
	{
		if (this.cptCarte++ >= 10) return null;

		if (this.prochaineCarte != null)
		{
			String carte = this.prochaineCarte;
			this.prochaineCarte = null;
			this.pile.remove(carte); // garde la pile coherente
			return carte;
		}

		return this.pile.remove(0);
	}
}