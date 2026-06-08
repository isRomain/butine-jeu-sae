package jeu;

import jeu.ihm.FramePlateau;
import jeu.metier.Case;
import jeu.metier.Grille;
import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;

public class Controleur
{
	private FramePlateau frame;

	private String ficImgAccueil;

	public Controleur()
	{
		this.ficImgAccueil = "../images/icones/butine_ecran_accueil.png";
		this.frame  = new FramePlateau( this );
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

					String p      = champs[0].trim();
					String fleur  = champs[1].trim();
					String depart = champs[2].trim();

					Color plaine = new Color(255, 255, 255, 200);

					if (!p.isEmpty() && p.contains(","))
					{
						String[] c = p.split(",");
						try
						{
							int r = Integer.parseInt(c[0].trim());
							int g = Integer.parseInt(c[1].trim());
							int b = Integer.parseInt(c[2].trim());
							int a = (c.length > 3) ? Integer.parseInt(c[3].trim()) : 255;

							plaine = new Color(r, g, b, a);
						}
						catch (Exception ignored) {}
					}

					Case cs = new Case(col, lig);

					cs.setPlaine(plaine);
					cs.setFleur (fleur.isEmpty()  ? "vide" : fleur);
					cs.setDepart(depart.isEmpty() ? "vide" : depart);

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
