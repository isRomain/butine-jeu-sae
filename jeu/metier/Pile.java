package jeu.metier;

import java.util.ArrayList;
import java.util.Collections;

class Pile
{

	// On énumere toutes les cartes
	private String[] cartes = {"carte_clair_carre"   ,
	                           "carte_clair_croix"   ,
							   "carte_clair_reine"   ,
							   "carte_clair_rond"    ,
							   "carte_clair_triangle",
							   "carte_fonce_carre"   ,
							   "carte_fonce_croix"   ,
							   "carte_fonce_reine"   ,
							   "carte_fonce_rond"    ,
							   "carte_fonce_triangle"};

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