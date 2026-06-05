package src;

import java.awt.Color;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import src.ihm.FramePlateau;

import src.metier.Case;
import src.metier.Grille;

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

	public Grille creerGrille(Grille grille)
	{
		return grille;
	}

	public String getImageAcceuil() { return this.ficImgAccueil; }

	public void ExporterGrille(Grille grille) throws IOException
	{
		try 
		{
			BufferedWriter bw = new BufferedWriter(new FileWriter("../test.txt"));

			bw.write(Integer.toString(grille.getHauteur()));
			bw.newLine();
			bw.write(Integer.toString(grille.getLargeur()));
			bw.newLine();
			bw.write(Integer.toString(grille.getTailleCase()));
			bw.newLine();

			bw.newLine();
	
			for (int lig = 0; lig < grille.getHauteur(); lig++) 
			{
				String sRet = "";
				for (int col = 0; col < grille.getLargeur(); col++) 
				{
					Case  c  = grille.getCase(col, lig);
					Color pl = c.getPlaine();

					String couleur;

					int r = pl.getRed();
					int g = pl.getGreen();
					int b = pl.getBlue();
					int a = pl.getAlpha();

					couleur = r + "," + g + "," + b + "," + a;
	
					String fleur  = c.getFleur ();
					String depart = c.getDepart();
	
					sRet += (couleur) + ";"+ fleur + ";" + depart;

					if (col < grille.getLargeur() - 1) 
						sRet += " | ";
				}

				bw.write(sRet);
				bw.newLine();
			}

			bw.close();
		} 
		catch (Exception err)
		{
			System.err.println(err);
		}


	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}