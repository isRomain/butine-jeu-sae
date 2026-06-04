package src;

import src.ihm.*;
import src.metier.*;

public class Controleur
{
	private FramePlateau frame;

	private String ficImgAccueil;

	public Controleur()
	{
		this.ficImgAccueil = "../images/icones/butine_ecran_accueil.png";
		this.frame  = new FramePlateau( this );
	}

	public Grille creerGrille(int largeur, int hauteur, int taille)
	{
		return new Grille( largeur, hauteur, taille );
	}

	public String getImageAcceuil() { return this.ficImgAccueil; }

	public void ExporterGrille(Grille grille)
    {
        for (int lig = 0; lig < grille.getHauteur(); lig++) 
        {
            for (int col = 0; col < grille.getLargeur(); col++) 
            {
                System.out.print(grille.getCase(col, lig).getFleur() + " | ");
            }

            System.out.println();
        }
    }

	public static void main(String[] args)
	{
		new Controleur();
	}
}