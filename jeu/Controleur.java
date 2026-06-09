package jeu;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import jeu.ihm.FramePlateauJeu;
import jeu.metier.Case;
import jeu.metier.Grille;

public class Controleur
{
	private FramePlateauJeu frame;
	private Musique         musique;

	private String ficImgAccueil;

	public Controleur()
	{
		this.ficImgAccueil = "../images/icones/butine_ecran_accueil.png";

		this.musique = new Musique("../sons/musique.wav");
		this.musique.jouer();

		this.frame  = new FramePlateauJeu( this );
	}

	public String getImageAcceuil() { return this.ficImgAccueil; }

	public Grille importerGrille(String chemin)
	{
		BufferedReader br = null;
		try 
		{
			br = new BufferedReader(new FileReader(chemin));

			int hauteur    = Integer.parseInt(br.readLine().trim());
			int largeur    = Integer.parseInt(br.readLine().trim());
			int tailleCase = Integer.parseInt(br.readLine().trim());

			String line = br.readLine();
			if (line != null && line.trim().isEmpty()) line = br.readLine();

			Grille grille = new Grille(largeur, hauteur, tailleCase);

			for (int lig = 0; lig < hauteur; lig++)
			{
				// Si il il n'y a pas de ligne existante
				if (line == null)
				{
					System.err.println("Ligne manquante à la hauteur " + lig);
					return null;
				}	

				// Séparation par ' | ' pour obtenir chaque case
				String[] cases = line.split("\\|");
				
				if (cases.length < largeur)
				{
					System.err.println("Nombre de cases insuffisant sur la ligne " + lig + " (attendu: " + largeur + ", obtenu: " + cases.length + ")");
					return null;
				}

				for (int col = 0; col < largeur; col++)
				{
					String[] champs = cases[col].trim().split(";");
					
					if (champs.length < 3)
					{
						System.err.println("Champs insuffisants à la position [" + lig + "," + col + "]");
						return null;
					}	

					String zoneCouleur = champs[0].trim(); 
					String fleur       = champs[1].trim();
					String depart      = champs[2].trim();

					Color plaine = new Color(255, 255, 255, 200);
					if (!zoneCouleur.isEmpty() && zoneCouleur.contains(","))
					{
						String[] c = zoneCouleur.split(",");
						
						try
						{
							int r = Integer.parseInt(c[0].trim());
							int g = Integer.parseInt(c[1].trim());
							int b = Integer.parseInt(c[2].trim());
							int a = Integer.parseInt(c[3].trim());

							plaine = new Color(r, g, b, a);
						}
						catch (Exception err) {}
					}

					Case cs = new Case(col, lig);

					cs.setPlaine(plaine);
					cs.setFleur (fleur);
					cs.setDepart(depart);
					
					grille.setCase(col, lig, cs);
				}

				line = br.readLine();
			}

			br.close();

			return grille;

		}
		catch (Exception error)
		{
			System.err.println("Erreur lors du chargement de la grille : " + error.getMessage());

			return null;
		}
	}

	public static void main(String[] args)
	{
		new Controleur();
	}
}
