package jeu.metier;

public class Pile
{
	private final int NB_CARTES = 10;

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

	// Liste contenant les cartes mélangées
	private String[] pile;

	private int cptCarte = NB_CARTES;

	public Pile ()
	{
		this.pile = new String[NB_CARTES];

		int rnd;
		for (int cpt = 0; cpt < NB_CARTES; cpt ++)
		{
			do
			{
				rnd = (int) (Math.random() * NB_CARTES);
			}
			while (cartes[rnd] == null);

			pile  [cpt] = cartes[rnd];
			cartes[rnd] = null;
		}
	}
}