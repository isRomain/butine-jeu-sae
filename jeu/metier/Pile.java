package jeu.metier;

import java.util.ArrayList;
import java.util.Collections;

class Pile
{

	// On énumere toutes les cartes
	private String[] cartes = {"clair_carre"   ,
	                           "clair_croix"   ,
							   "clair_reine"   ,
							   "clair_rond"    ,
							   "clair_triangle",
							   "fonce_carre"   ,
							   "fonce_croix"   ,
							   "fonce_reine"   ,
							   "fonce_rond"    ,
							   "fonce_triangle"};

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