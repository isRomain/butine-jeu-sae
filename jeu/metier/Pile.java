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

	public Pile ()
	{
		this.pile = new ArrayList<String>();

    	Collections.addAll(this.pile, this.cartes);
    	Collections.shuffle(this.pile);
	}

	public String piocher()
	{
		if (this.cptCarte++ >= 10) return null;

		return this.pile.remove(0);
	}
}