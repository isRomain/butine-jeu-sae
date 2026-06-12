package jeu.metier;

import java.awt.Color;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Grille
{
	private int largeur;
	private int hauteur;
	private int tailleCase;
	private Case[][] cases;

	public Grille(int largeur, int hauteur, int tailleCase)
	{
		this.largeur    = largeur;
		this.hauteur    = hauteur;
		this.tailleCase = tailleCase;
		this.cases      = new Case[largeur][hauteur];

		for (int x = 0; x < largeur; x++)
			for (int y = 0; y < hauteur; y++)
			{
				this.cases[x][y] = new Case(x, y);
			}
	}

	public int getLargeur   () { return largeur;         }
	public int getHauteur   () { return hauteur;         }
	public int getTailleCase() { return this.tailleCase; }

	public Case getCase(int x, int y)
	{
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur) return cases[x][y];
		return null;
	}

	public void setCase(int x, int y, Case vCase)
	{
		if (x >= 0 && x < largeur && y >= 0 && y < hauteur) cases[x][y] = vCase;
	}

	public boolean regionsConnexes()
	{
		boolean[][]      visite       = new boolean[largeur][hauteur];
		ArrayList<Color> couleursVues = new ArrayList<Color>();

		for (int x = 0; x < largeur; x++)
		{
			for (int y = 0; y < hauteur; y++)
			{
				Color couleur = getCase(x, y).getPlaine();

				if (!visite[x][y])
				{
					if (couleursVues.contains(couleur)) return false;

					couleursVues.add(couleur);
					remplir(x, y, couleur, visite);
				}
			}
		}

		return true;
	}

	private void remplir(int x, int y, Color couleur, boolean[][] visite)
	{
		if (x < 0 || x >= largeur || y < 0 || y >= hauteur)
			return;

		if (visite[x][y] || !getCase(x, y).getPlaine().equals(couleur))
			return;

		visite[x][y] = true;

		remplir(x + 1, y, couleur, visite);
		remplir(x - 1, y, couleur, visite);
		remplir(x, y + 1, couleur, visite);
		remplir(x, y - 1, couleur, visite);
	}

	public boolean departUnique ()
	{
		String departs = "";
		for (int x = 0; x < this.largeur; x++)
			for (int y = 0; y < this.hauteur; y++)
				if (!this.cases[x][y].getDepart().equals("vide"))
				{
					if (departs.contains(this.cases[x][y].getDepart()))
						return false;
					
					departs += this.cases[x][y].getDepart();
				}

		if (departs.equals(""))
			return false;
		
		return true;
	}

	public int nbDeparts()
	{
		int nb = 0;
		for (int x = 0; x < this.largeur; x++)
			for (int y = 0; y < this.hauteur; y++)
				if (!this.cases[x][y].getDepart().equals("vide"))
					nb++;
		return nb;
	}

	public void trouverConnections()
	{
		for (int x = 0; x < largeur; x++)
			for (int y = 0; y < hauteur; y++)
				this.cases[x][y].trouverConnections(this);
	}

	public boolean ajouterDeplacement(Case caseDepart, Case caseDestination)
	{
		if (this.seCroise(caseDepart, caseDestination))
			return false;

		if (caseDepart.ajouterDeplacement(caseDestination))
		{
			if (caseDestination.ajouterDeplacement(caseDepart))
			{
				return true;
			}
		}

		return false;
	}

	public Case[] getCaseDepart ()
	{
		Case[] listDepart = new Case[this.nbDeparts()];
		int cpt = 0;
		for (int x = 0; x < this.largeur; x++)
		{
			for (int y = 0; y < this.hauteur; y++)
			{
				if (!this.getCase(x, y).getDepart().equals("vide"))
				{
					listDepart[cpt] = this.getCase(x, y);
					cpt++;
				}
			}
		}
		return listDepart;
	}

	/* Methode qui retourne un boolean si les case */
	/*     entree en parametres se croisent        */
	public boolean seCroise(Case caseA, Case caseB)
	{
		// Création de la ligne correspondant au nouveau déplacement
		Line2D.Double nouvelleLigne =
			new Line2D.Double(
				caseA.getX(),
				caseA.getY(),
				caseB.getX(),
				caseB.getY()
			);

		// Parcours de toutes les cases de la grill
		for (int x = 0; x < this.largeur; x++)
		{
			for (int y = 0; y < this.hauteur; y++)
			{
				Case depart = this.getCase(x, y);

				// Parcours de toutes les cases de la grill
				for (int cpt = 0; cpt < 8; cpt++)
				{
					Case arrivee = depart.getCaseDeplacement(cpt);

					if (arrivee == null)
						continue;

					// On ignore les lignes qui partagent une extrémité
					if (depart  == caseA || depart == caseB ||
						arrivee == caseA || arrivee == caseB)
						continue;

					// Création de la ligne du déplacement existant
					Line2D.Double ligneExistante =
						new Line2D.Double(
							depart.getX(),
							depart.getY(),
							arrivee.getX(),
							arrivee.getY()
						);

					if (nouvelleLigne.intersectsLine(ligneExistante))
						return true;
				}
			}
		}

		return false;
	}

	public int calculerScore()
	{
		int total = 0;

		ArrayList<Color> couleurs = couleursUtilisees();
		for (int i = 0; i < couleurs.size(); i++)
			total += scoreCouleur(couleurs.get(i));

		return total;
	}

	// Les couleurs présentes parmi les déplacement
	private ArrayList<Color> couleursUtilisees()
	{
		ArrayList<Color> couleurs = new ArrayList<Color>();

		for (int x = 0; x < this.largeur; x++)
			for (int y = 0; y < this.hauteur; y++)
				for (int cpt = 0; cpt < 8; cpt++)
					if (this.cases[x][y].getCaseDeplacement(cpt) != null)
					{
						Color couleur = this.cases[x][y].getCouleurDeplacement(cpt);
						if (!couleurs.contains(couleur))
							couleurs.add(couleur);
					}

		return couleurs;
	}

	private int scoreCouleur(Color couleur)
	{
		boolean[][] surLeChemin = new boolean[this.largeur][this.hauteur];
		boolean[][] estFleur    = new boolean[this.largeur][this.hauteur];

		// On parcourt tous les déplacements de cette couleur
		for (int x = 0; x < this.largeur; x++)
			for (int y = 0; y < this.hauteur; y++)
				for (int cpt = 0; cpt < 8; cpt++)
				{
					Case depart  = this.cases[x][y];
					Case arrivee = depart.getCaseDeplacement(cpt);

					if (arrivee != null && depart.getCouleurDeplacement(cpt).equals(couleur))
					{
						estFleur[depart.getX() ][depart.getY() ] = true;
						estFleur[arrivee.getX()][arrivee.getY()] = true;

						// On marque les cases que le trait traverse
						int dx = signe(arrivee.getX() - depart.getX());
						int dy = signe(arrivee.getY() - depart.getY());

						int cx = depart.getX();
						int cy = depart.getY();
						while (cx != arrivee.getX() || cy != arrivee.getY())
						{
							surLeChemin[cx][cy] = true;
							cx += dx;
							cy += dy;
						}
						surLeChemin[arrivee.getX()][arrivee.getY()] = true;
					}
				}

		// nombre de zones touchées par le chemin
		ArrayList<Color> zonesTouchees = new ArrayList<Color>();
		for (int x = 0; x < this.largeur; x++)
			for (int y = 0; y < this.hauteur; y++)
				if (surLeChemin[x][y])
				{
					Color zone = this.cases[x][y].getPlaine();
					if (!zonesTouchees.contains(zone))
						zonesTouchees.add(zone);
				}

		int facteur1 = zonesTouchees.size();

		// on compte les fleurs du chemin et on garde le max
		int facteur2 = 0;
		for (int i = 0; i < zonesTouchees.size(); i++)
		{
			Color zone = zonesTouchees.get(i);

			int nbFleurs = 0;
			for (int x = 0; x < this.largeur; x++)
				for (int y = 0; y < this.hauteur; y++)
					if (estFleur[x][y] && this.cases[x][y].getPlaine().equals(zone))
						nbFleurs++;

			if (nbFleurs > facteur2)
				facteur2 = nbFleurs;
		}

		return facteur1 * facteur2;
	}

	private int signe(int valeur)
	{
		if (valeur > 0) return  1;
		if (valeur < 0) return -1;
		return 0;
	}
}