package jeu.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
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

	private boolean estDeplacee;

	private Case caseDepartDeplacement;
	private Case caseArriveeDeplacement;

	//private Case caseSurvolee;

	private ArrayList<Case[]> traitsDeplacement = new ArrayList<Case[]>();

	public PanelGrilleJeu(FramePlateauJeu prnt, Grille grille)
	{
		this.prnt   = prnt;
		this.grille = grille; 

		this.traitsDeplacement = new ArrayList<Case[]>();

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
				estDeplacee = false;
				repaint();
			}
		
			public void mouseDragged(MouseEvent e)
			{
				//caseSurvolee = getCaseDepuisPixel(e.getX(), e.getY());
				/*repaint();*/
			}
		
			public void mouseReleased(MouseEvent e)
			{
				caseArriveeDeplacement = getCaseDepuisPixel(e.getX(), e.getY());
			
				if (caseDepartDeplacement != null &&
					caseArriveeDeplacement != null &&
					!caseArriveeDeplacement.getFleur().equals("vide") &&
					caseArriveeDeplacement != caseDepartDeplacement)
				{
					traitsDeplacement.add( new Case[] { caseDepartDeplacement, caseArriveeDeplacement } );
					estDeplacee = true;
				}
			
				//caseSurvolee = null;
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

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Image img = Toolkit.getDefaultToolkit().getImage("../images/icones/fond_pre.jpg");
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);


		if (grille == null)
			return;

		int largeur = grille.getLargeur();
		int hauteur = grille.getHauteur();

		int largeurGrille = largeur * grille.getTailleCase();
		int hauteurGrille = hauteur * grille.getTailleCase();

		decalX = (this.getWidth() - largeurGrille) / 2;
		decalY = (this.getHeight() - hauteurGrille) / 2;

		for (int y = 0; y < hauteur; y++)
		{
			for (int x = 0; x < largeur; x++)
			{
				/*-------------------------------------*/
				/* Dessiner les traits de deplacements */
				/*-------------------------------------*/
				g.setColor(Color.RED);
				for (Case[] trait : this.traitsDeplacement)
				{
					Case depart  = trait[0];
					Case arrivee = trait[1];

					if( depart.getConnection(x) == arrivee )
					{
						int taille = this.grille.getTailleCase();
				
						int x1 = this.decalX + depart.getX() * taille + taille / 2;
						int y1 = this.decalY + depart.getY() * taille + taille / 2;
						
						int x2 = this.decalX + arrivee.getX() * taille + taille / 2;
						int y2 = this.decalY + arrivee.getY() * taille + taille / 2;
						
						g.drawLine(x1, y1, x2, y2);
					}
				}

				g.setColor(Color.RED);

				/*--------------------------------*/
				/* Dessiner les traits de liasons */
				/*--------------------------------*/
				int posX = decalX + x * grille.getTailleCase();
				int posY = decalY + y * grille.getTailleCase();

				Color couleurCase = grille.getCase(x, y).getPlaine();
				
				g.setColor(couleurCase);
				g.fillRect(posX, posY, grille.getTailleCase(), grille.getTailleCase());

				for (int cpt = 0; cpt < 8; cpt++)
				{
						 Case connection = grille.getCase(x, y).getConnection(cpt);
						 if (connection != null)
						 {
						 	g.setColor(Color.BLACK);
						 	g.drawLine(posX + grille.getTailleCase()/2, posY + grille.getTailleCase()/2, (decalX + connection.getX() * grille.getTailleCase()) + grille.getTailleCase()/2, (decalY + connection.getY() * grille.getTailleCase()) + grille.getTailleCase()/2);
						 }
				}

				// Dessiner les departes des fleurs
				if (grille.getCase(x, y).getDepart() != null)
				{
					g.drawImage(grille.getCase(x, y).getImageDepart(), posX, posY, grille.getTailleCase(), grille.getTailleCase(), this);
				}

				// Dessiner les fleurs
				if (grille.getCase(x, y).getFleur() != null)
				{
					g.drawImage(grille.getCase(x, y).getImageFleur(), posX, posY, grille.getTailleCase(), grille.getTailleCase(), this);
				}
			}
		}
	}
}