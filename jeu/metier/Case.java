package jeu.metier;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;

public class Case
{
	private int     x, y;
	private Color   plaine;
	private String  fleur;
	private String  depart;
	private boolean estTraverser;

	private Color[]  tabCouleurDepart   = { Color.BLACK, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY, Color.ORANGE, Color.PINK };

	private Color   derniereCouleur;
	private Color[] couleursDeplacement;

	private Case[] connections;
	private Case[] casesDeplacement;

	public Case (int x, int y)
	{
		this.x = x;
		this.y = y;

		this.plaine = new Color(255, 255, 255, 200);
		this.fleur  = "vide";
		this.depart = "vide";

		this.estTraverser = false;

		this.couleursDeplacement = new Color[8];

		for (int i = 0; i < 8; i++)
		{
			this.couleursDeplacement[i] = Color.BLACK;
		}

		this.connections      = new Case[8];
		this.casesDeplacement = new Case[8];
	}

	public void setCouleurDeplacement(int nb, Color couleur)
    {
		this.derniereCouleur         = couleur;
    	this.couleursDeplacement[nb] = couleur;
    }
    
    public Color getCouleurDeplacement(int nb)
    {
    	return this.couleursDeplacement[nb];
    }

	public void setPlaine       ( Color   plaine ) { this.plaine = plaine;    }
	public void setFleur        ( String  fleur  ) { this.fleur  = fleur ;    }
	public void setDepart       ( String  depart ) { this.depart = depart;    }
	public void setEstTraverser ( boolean val    ) { this.estTraverser = val; }

	public void ajouterConnection ( int nb, Case caseConnection ) { this.connections[nb] = caseConnection; }

	public int     getX            () { return this.x;            }
	public int     getY            () { return this.y;            }
	public Color   getPlaine       () { return this.plaine;       }
	public String  getFleur        () { return this.fleur;        }
	public String  getDepart       () { return this.depart;       }
	public Boolean getEstTraverser () { return this.estTraverser; }

	/* Retourn la couleur de Dapart en fonction du nom */
	/*       de la couleur de la case de depart        */
	public Color getCoulDepart  ( String coul )
	{
		  switch( coul )
		  {
			  case "rouge"  : return this.tabCouleurDepart[1];

			  case "vert"   : return this.tabCouleurDepart[2];

			  case "bleu"   : return this.tabCouleurDepart[3];

			  case "marron" : return this.tabCouleurDepart[4];

			  case "orange" : return this.tabCouleurDepart[5];

			  case "violet" : return this.tabCouleurDepart[6];
		  }

		  return this.tabCouleurDepart[0];
	}

	public Case   getConnection      (int nb) { return this.connections[nb];      }
	public Case   getCaseDeplacement (int nb) { return this.casesDeplacement[nb]; }

	//On retourne directement l'image de la fleur
	public Image getImageFleur ()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/pollens/pollen_" + this.fleur + ".png");
	}

	//On retourne directement l'image du départ
	public Image getImageDepart ()
	{
		return Toolkit.getDefaultToolkit().getImage("../images/contours/contour_case_" + this.depart + ".png");
	}

	//Trouver toutes les connections des fleurs
	public void trouverConnections (Grille grille)
	{
		//Tout est remis à zero
		for (int cpt = 0; cpt < 8; cpt++)
				this.connections[cpt] = null;

		//Si la case est vide, pas de connection
		if (this.fleur.equals("vide"))
			return;

		for (int cpt = 1; cpt < Math.max(grille.getHauteur(), grille.getLargeur()); cpt++)
		{
			// Vérification dans le sens horaire
			verifierCaseNonNulle( grille, 0, this.x - cpt, this.y       ); // Ouest
			verifierCaseNonNulle( grille, 1, this.x - cpt, this.y - cpt ); // Nord-Ouest
			verifierCaseNonNulle( grille, 2, this.x      , this.y - cpt ); // Nord
			verifierCaseNonNulle( grille, 3, this.x + cpt, this.y - cpt ); // Nord-Est
			verifierCaseNonNulle( grille, 4, this.x + cpt, this.y       ); // Est
			verifierCaseNonNulle( grille, 5, this.x + cpt, this.y + cpt ); // Sud-Est
			verifierCaseNonNulle( grille, 6, this.x      , this.y + cpt ); // Sud
			verifierCaseNonNulle( grille, 7, this.x - cpt, this.y + cpt ); // Sud-Ouest
		}
	}

	private void verifierCaseNonNulle (Grille grille, int cpt, int x, int y)
	{
		if (grille.getCase(x, y) != null) //Hop, pas d'ereurs
		{
			if (!grille.getCase(x, y).getFleur().equals("vide") && this.connections[cpt] == null) // On cherche la première fleur sur son passage
			{
				this.connections[cpt] = grille.getCase(x, y);
			}
		}
	}

	/* Verifie si l''ajout est possible et ajoute la case donne  */
	/*       en parametre dans le tableau casesDeplacement       */
    /*               a la case qui convienne                     */
	public boolean ajouterDeplacement (Case deplaceA)
	{
		for (int cpt = 0; cpt < 8; cpt ++)
		{
			if (this.connections[cpt] == deplaceA)
			{
				if (this.connections[cpt] == this.casesDeplacement[cpt])
				{
					return false;
				}

				this.casesDeplacement[cpt] = this.connections[cpt];
				return true;
			}
		}
		return false;
	}

	public boolean estExtremiter ()
	{
		int nbDeplacement = 0;
		for (int cpt = 0; cpt < 8; cpt++)
		{
			if (this.casesDeplacement[cpt] != null && this.getCouleurDeplacement(cpt) == this.derniereCouleur)
				nbDeplacement++;
		}

		if (nbDeplacement%2 != 0 || (nbDeplacement == 0 && !this.depart.equals("vide")) )
			return true;
		return false;
	}

	/* Retourne l'indice de la case entree en parametre */
	/*       dans le tableu de connections              */
	public int getIndiceConnection(Case autre)
	{
		for (int i = 0; i < 8; i++)
		{
			if (this.connections[i] == autre)
				return i;
		}

		return -1;
	}
}