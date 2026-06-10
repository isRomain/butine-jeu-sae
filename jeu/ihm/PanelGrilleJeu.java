package jeu.ihm;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
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

	private Case caseDepartDeplacement;
	private Case caseArriveeDeplacement;
	private Case departChoisi = null;

	private Color couleurCheminCourant = Color.BLACK;

	private boolean aUnDepart = false;

	private int ancienneManche;


	public PanelGrilleJeu(FramePlateauJeu prnt, Grille grille)
	{
		this.prnt   = prnt;
		this.grille = grille; 
		this.ancienneManche = this.prnt.getMancheActuelle();

		MouseAdapter souris = new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				verifierNouvelleManche();
				caseDepartDeplacement = getCaseDepuisPixel(e.getX(), e.getY());
			
				// Verifier si la case depart est vide
				if (caseDepartDeplacement == null ||
					caseDepartDeplacement.getFleur().equals("vide"))
				{
					caseDepartDeplacement = null;
					return;
				}
				caseArriveeDeplacement = null;

				// Verifier qu'on peut se deplacer qu'a partir du depart choisi
				if (!caseDepartDeplacement.getDepart().equals("vide"))
				{
					if (departChoisi == null)
					{
						departChoisi = caseDepartDeplacement;
						couleurCheminCourant = caseDepartDeplacement.getCoulDepart(caseDepartDeplacement.getDepart());
					}
					else if (caseDepartDeplacement != departChoisi)
					{
						caseDepartDeplacement = null;
						return;
					}
				}
			}
		
			public void mouseReleased(MouseEvent e)
			{
				caseArriveeDeplacement = getCaseDepuisPixel(e.getX(), e.getY());
			
				// Verifier que tout est valide avant de ajouter le deplacement
				if ( caseDepartDeplacement  != null &&
	 				 caseArriveeDeplacement != null &&
	 				 caseArriveeDeplacement != caseDepartDeplacement &&
	 				 !caseArriveeDeplacement.getFleur().equals("vide") &&
	 				 carteAutorise(caseArriveeDeplacement) &&
	 				 caseDepartDeplacement.estExtremiter() &&
	 				 (caseDepartDeplacement.getDepart().equals("vide") || caseDepartDeplacement == departChoisi) )
				{

					 // On ajoute le deplacement en affectant la couleur du depart
					 if ( grille.ajouterDeplacement( caseDepartDeplacement, caseArriveeDeplacement ) )
					 {
						  caseDepartDeplacement .setCouleurDeplacement(couleurCheminCourant);
						  caseArriveeDeplacement.setCouleurDeplacement(couleurCheminCourant);
						  prnt.deplacementEffectue();
					 }

				}
			
				repaint();
			}
		};

		this.addMouseListener(souris);
		this.addMouseMotionListener(souris);
	}

	public Grille getGrille() { return this.grille; }

	private void verifierNouvelleManche()
	{
		if (this.ancienneManche != this.prnt.getMancheActuelle())
		{
			this.ancienneManche = this.prnt.getMancheActuelle();

			this.departChoisi = null;
			this.caseDepartDeplacement = null;
			this.caseArriveeDeplacement = null;
			this.couleurCheminCourant = Color.BLACK;
		}
	}

	private int getMancheActuelle()
	{
		return this.prnt.getMancheActuelle();
	}

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

		Graphics2D g2 = (Graphics2D) g;

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
						g2.setStroke(new BasicStroke(1));
						g2.setColor(new Color(0, 0, 0, 55)); // noir transparent
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

						   g2.setStroke(new BasicStroke(5));
						   g2.setColor(depart.getCouleurDeplacement());

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