package jeu.metier;

import java.awt.Color;
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
				this.placerCroisement(caseDepart, caseDestination);
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

	public boolean seCroise(Case caseA, Case caseB)
	{
		int direction = caseA.getIndiceConnection(caseB);
	
		if (direction == -1)
			return true; // pas de liaison entre les deux, donc déplacement refuse
	
		for (int cpt = 1; cpt < Math.max(Math.abs(caseA.getX() - caseB.getX()), Math.abs(caseA.getY() - caseB.getY())); cpt++)
		{
			Case c = null;
		
			switch (direction)
			{
				case 0 : c = this.getCase(caseA.getX() - cpt, caseA.getY()      ); break;
				case 1 : c = this.getCase(caseA.getX() - cpt, caseA.getY() - cpt); break;
				case 2 : c = this.getCase(caseA.getX()      , caseA.getY() - cpt); break;
				case 3 : c = this.getCase(caseA.getX() + cpt, caseA.getY() - cpt); break;
				case 4 : c = this.getCase(caseA.getX() + cpt, caseA.getY()      ); break;
				case 5 : c = this.getCase(caseA.getX() + cpt, caseA.getY() + cpt); break;
				case 6 : c = this.getCase(caseA.getX()      , caseA.getY() + cpt); break;
				case 7 : c = this.getCase(caseA.getX() - cpt, caseA.getY() + cpt); break;
			}
		
			if (c != null && c.getEstTraverser())
				return true;
		}
	
		return false;
	}

	private void placerCroisement (Case caseA, Case caseB)
	{
		for (int cpt = 1; cpt < Math.max( Math.abs(caseA.getX() - caseB.getX()), Math.abs(caseA.getY() - caseB.getY()) ); cpt++)
		{
			switch (caseA.getIndiceConnection(caseB))
			{
				case 0 : this.getCase( caseA.getX() - cpt, caseA.getY()       ).setEstTraverser(true); break;
				case 1 : this.getCase( caseA.getX() - cpt, caseA.getY() - cpt ).setEstTraverser(true); break;
				case 2 : this.getCase( caseA.getX()      , caseA.getY() - cpt ).setEstTraverser(true); break;
				case 3 : this.getCase( caseA.getX() + cpt, caseA.getY() - cpt ).setEstTraverser(true); break;
				case 4 : this.getCase( caseA.getX() + cpt, caseA.getY()       ).setEstTraverser(true); break;
				case 5 : this.getCase( caseA.getX() + cpt, caseA.getY() + cpt ).setEstTraverser(true); break;
				case 6 : this.getCase( caseA.getX()      , caseA.getY() + cpt ).setEstTraverser(true); break;
				case 7 : this.getCase( caseA.getX() - cpt, caseA.getY() + cpt ).setEstTraverser(true); break;
			}
		}
	}
}