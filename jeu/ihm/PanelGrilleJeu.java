package jeu.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import jeu.metier.Case;
import jeu.metier.Grille;

public class PanelGrilleJeu extends JPanel
{
	private FramePlateauJeu prnt;

	private Grille grille;

	private int decalX = 0;
	private int decalY = 0;

	private Color couleurPlaine;
	private String formeFleur, couleurDepart;

	//private boolean deplacementValide = false;

	private Case caseDepartDeplacement;
	private Case caseArriveeDeplacement;

	//private Case caseSurvolee;

	//private ArrayList<Case[]> traitsDeplacement = new ArrayList<Case[]>();

	public PanelGrilleJeu(FramePlateauJeu prnt, Grille grille)
	{
		this.prnt   = prnt;
		this.grille = grille; 

		//this.traitsDeplacement = new ArrayList<Case[]>();

		MouseAdapter souris = new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				caseDepartDeplacement = getCaseDepuisPixel(e.getX(), e.getY());
			
				if (caseDepartDeplacement == null ||
					caseDepartDeplacement.getFleur().equals("vide"))
				{
					caseDepartDeplacement = null;
				}
			
				caseArriveeDeplacement = null;
				//deplacementValide = false;
				//repaint();
			}
		
			public void mouseReleased(MouseEvent e)
			{
				caseArriveeDeplacement = getCaseDepuisPixel(e.getX(), e.getY());
			
				if ( caseDepartDeplacement  != null &&
					 caseArriveeDeplacement != null &&
					 caseArriveeDeplacement != caseDepartDeplacement &&
					 !caseArriveeDeplacement.getFleur().equals("vide") &&
				     grille.ajouterDeplacement( caseDepartDeplacement, caseArriveeDeplacement ) && 
				     sontConnectees(caseDepartDeplacement, caseArriveeDeplacement) &&
				     carteAutorise(caseArriveeDeplacement) )
				{
					 grille.ajouterDeplacement( caseDepartDeplacement, caseArriveeDeplacement );
				}
			
				//caseSurvolee = null;
				//deplacementValide = false;
				repaint();
			}
		};

		this.addMouseListener(souris);
		this.addMouseMotionListener(souris);
	}

	public Grille getGrille() { return this.grille; }

	private Case getCaseDepuisPixel(int pixelX, int pixelY)
	{
		if (this.grille == null)
			return null;

		int taille = this.grille.getTailleCase();

		int x = (pixelX - this.decalX) / taille;
		int y = (pixelY - this.decalY) / taille;

		if (pixelX >= this.decalX && pixelY >= this.decalY &&
			x >= 0 && x < this.grille.getLargeur() &&
			y >= 0 && y < this.grille.getHauteur())
		{
			return this.grille.getCase(x, y);
		}

		return null;
	}

	private boolean sontConnectees(Case depart, Case arrivee)
	{
		if (depart == null || arrivee == null)
			return false;

		for (int i = 0; i < 8; i++)
		{
			if (depart.getConnection(i) == arrivee)
				return true;
		}

		return false;
	}

	private boolean carteAutorise(Case arrivee)
	{
		String formeCarte = this.prnt.getControleur().getFormeCarte();

		if (formeCarte.equals("reine"))
			return true;

		return arrivee.getFleur().equals(formeCarte);
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Image img = Toolkit.getDefaultToolkit().getImage("../images/icones/fond_pre.jpg");
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		if (grille == null)
			return;

		int largeur = grille.getLargeur();
		int hauteur = grille.getHauteur();
		int taille  = grille.getTailleCase();

		int largeurGrille = largeur * taille;
		int hauteurGrille = hauteur * taille;

		decalX = (this.getWidth()  - largeurGrille) / 2;
		decalY = (this.getHeight() - hauteurGrille) / 2;

		/*----------------------*/
		/* Dessiner les régions */
		/*----------------------*/
		for (int y = 0; y < hauteur; y++)
		{
			for (int x = 0; x < largeur; x++)
			{
				int posX = decalX + x * taille;
				int posY = decalY + y * taille;

				g.setColor(grille.getCase(x, y).getPlaine());
				g.fillRect(posX, posY, taille, taille);
			}
		}

		/*-----------------------------------*/
		/* Dessiner les traits de liasons    */
		/*-----------------------------------*/
		for (int y = 0; y < hauteur; y++)
		{
			for (int x = 0; x < largeur; x++)
			{
				int posX = decalX + x * taille;
				int posY = decalY + y * taille;

				for (int cpt = 0; cpt < 8; cpt++)
				{
					Case connection = grille.getCase(x, y).getConnection(cpt);

					if (connection != null)
					{
						g.setColor(Color.BLACK);
						g.drawLine(
							posX + taille / 2,
							posY + taille / 2,
							decalX + connection.getX() * taille + taille / 2,
							decalY + connection.getY() * taille + taille / 2
						);
					}
				}
			}
		}

		/*-------------------------------------*/
		/* Dessiner les traits de déplacements */
		/*-------------------------------------*/
		/*g.setColor(Color.RED);

		for (Case[] trait : this.traitsDeplacement)
		{
			Case depart  = trait[0];
			Case arrivee = trait[1];
		
			int x1 = decalX + depart.getX()  * taille + taille / 2;
			int y1 = decalY + depart.getY()  * taille + taille / 2;
		
			int x2 = decalX + arrivee.getX() * taille + taille / 2;
			int y2 = decalY + arrivee.getY() * taille + taille / 2;
		
			g.drawLine(x1, y1, x2, y2);
		}*/


		g.setColor(Color.RED);
		for (int y = 0; y < hauteur; y++)
		{
			for (int x = 0; x < largeur; x++)
			{
				 for( int cptDep = 0; cptDep < 8; cptDep++ )
				 {
					  if( grille.getCase(x, y).getCaseDeplacement(cptDep) != null )
					  {
						   Case depart  = grille.getCase(x, y);
						   Case arrivee = grille.getCase(x, y).getCaseDeplacement(cptDep);

						   int x1 = decalX + depart.getX()  * taille + taille / 2;
						   int y1 = decalY + depart.getY()  * taille + taille / 2;
								   
						   int x2 = decalX + arrivee.getX() * taille + taille / 2;
						   int y2 = decalY + arrivee.getY() * taille + taille / 2;

						   g.drawLine(x1, y1, x2, y2);
					  }
				 }
			}
		}

		/*---------------------------*/
		/* Trait temporaire du drag  */
		/*---------------------------*/
		/*if (this.caseDepartDeplacement != null /* && this.caseSurvolee != null )
		{
			g.setColor(Color.RED);

			int x1 = decalX + this.caseDepartDeplacement.getX() * taille + taille / 2;
			int y1 = decalY + this.caseDepartDeplacement.getY() * taille + taille / 2;

			int x2 = decalX + this.caseArriveeDeplacement.getX() * taille + taille / 2;
			int y2 = decalY + this.caseArriveeDeplacement.getY() * taille + taille / 2;

			g.drawLine(x1, y1, x2, y2);
		}*/

		/*---------------------------*/
		/* Dessiner départs/fleurs   */
		/*---------------------------*/
		for (int y = 0; y < hauteur; y++)
		{
			for (int x = 0; x < largeur; x++)
			{
				int posX = decalX + x * taille;
				int posY = decalY + y * taille;

				if (grille.getCase(x, y).getDepart() != null)
					g.drawImage(grille.getCase(x, y).getImageDepart(), posX, posY, taille, taille, this);

				if (grille.getCase(x, y).getFleur() != null)
					g.drawImage(grille.getCase(x, y).getImageFleur(), posX, posY, taille, taille, this);
			}
		}
	}
}