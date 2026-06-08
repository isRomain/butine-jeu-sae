package conception.metier;

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

/*Vérifie que chaque couleur de plaine forme une seule région connexe.
On parcourt la grille ; à la première case non visitée d'une couleur on
remplit toute sa région. Si on rencontre ensuite une couleur déjà vue
(donc séparée en plusieurs morceaux), la grille n'est pas valide.*/
	public boolean regionsConnexes()
	{
		boolean[][]      visite       = new boolean[largeur][hauteur]; // cases déjà traitées
		ArrayList<Color> couleursVues = new ArrayList<Color>();        // couleurs de région déjà rencontrées

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

/*Remplissage par récursivité : marque comme visitées
toutes les cases de la même couleur connectées à (x, y), en se propageant
dans les 4 directions. S'arrête hors grille, sur une case déjà visitée ou
d'une autre couleur.*/
	private void remplir(int x, int y, Color couleur, boolean[][] visite)
	{
		if (x < 0 || x >= largeur || y < 0 || y >= hauteur)
			return;

		if (visite[x][y] || !getCase(x, y).getPlaine().equals(couleur))
			return;

		visite[x][y] = true;

		remplir( x + 1, y, couleur, visite );
		remplir( x - 1, y, couleur, visite );
		remplir( x, y + 1, couleur, visite );
		remplir( x, y - 1, couleur, visite );
	}

/*Vérifie qu'il existe au moins un départ et qu'aucun départ n'est dupliqué.
Les cases dont le départ vaut "vide" sont ignorées.
return true si la grille contient des départs tous distincts, false sinon*/
	public boolean departUnique ()
	{
		String departs = ""; // concaténation des départs déjà rencontrés
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

	public void trouverConnections()
	{
		for (int x = 0; x < largeur; x++)
			for (int y = 0; y < hauteur; y++)
				this.cases[x][y].trouverConnections(this);
	}
}
