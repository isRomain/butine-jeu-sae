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
import jeu.Controleur;

public class PanelGrilleJeu extends JPanel
{
	private FramePlateauJeu prnt;

	private Controleur controleur;

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
		this.controleur = prnt.getControleur();
		this.ancienneManche = this.prnt.getMancheActuelle();

		MouseAdapter souris = new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				// Vérifier si une nouvelle manche a commencé
				verifierNouvelleManche();
				caseDepartDeplacement = getCaseDepuisPixel(e.getX(), e.getY());
			
				// Empêcher la sélection d'une case vide
				if (caseDepartDeplacement == null ||
					caseDepartDeplacement.getFleur().equals("vide"))
				{
					caseDepartDeplacement = null;
					return;
				}
				// Empêcher la sélection d'une case vide
				caseArriveeDeplacement = null;

				// Aucun départ encore choisi pour cette manche
				if (departChoisi == null)
				{
					// Obliger le joueur à commencer par un départ
					if (caseDepartDeplacement.getDepart().equals("vide"))
					{
						caseDepartDeplacement = null;
						return;
					}
				
					// Mémoriser le départ choisi et la couleur du chemin
					departChoisi = caseDepartDeplacement;
					couleurCheminCourant = caseDepartDeplacement.getCoulDepart(caseDepartDeplacement.getDepart());
				}
				// Empêcher de changer de départ pendant la manche
				else if (!caseDepartDeplacement.getDepart().equals("vide") && caseDepartDeplacement != departChoisi)
				{
					caseDepartDeplacement = null;
					return;
				}
			}
		
			public void mouseReleased(MouseEvent e)
			{
				caseArriveeDeplacement = getCaseDepuisPixel(e.getX(), e.getY());
			
				// Verifier que tout est valide avant de ajouter le deplacement
				if ( deplacementValide() )
				{

					 // On ajoute le deplacement en affectant la couleur du depart
					if (controleur.ajouterDeplacement(caseDepartDeplacement, caseArriveeDeplacement))
					{
						int indiceDep = caseDepartDeplacement.getIndiceConnection(caseArriveeDeplacement);
						int indiceArr = caseArriveeDeplacement.getIndiceConnection(caseDepartDeplacement);
					
						if (indiceDep != -1)
						caseDepartDeplacement.setCouleurDeplacement(indiceDep, couleurCheminCourant);
					
						if (indiceArr != -1)
						caseArriveeDeplacement.setCouleurDeplacement(indiceArr, couleurCheminCourant);
					
						prnt.deplacementEffectue();
					}

				}
			
				repaint();
			}
		};

		this.addMouseListener(souris);
		this.addMouseMotionListener(souris);
	}

	private boolean deplacementValide()
	{
		  return  caseDepartDeplacement  != null  &&
	 			  caseArriveeDeplacement != null  &&
				  carteAutorise(caseArriveeDeplacement) && // Verifier que la case corresponde a la fleur tiree
	 			  caseDepartDeplacement.estExtremiter() &&
	 			  caseArriveeDeplacement != caseDepartDeplacement &&
	 			  !caseArriveeDeplacement.getFleur().equals("vide") &&
	 			  ( caseDepartDeplacement .getDepart().equals( "vide" ) || caseDepartDeplacement == departChoisi  ) &&
				  ( caseArriveeDeplacement.getDepart().equals( "vide" ) || caseArriveeDeplacement == departChoisi );
	}

	/* Methode appelee a chaque mouvement pour */
	/*    verifier mettre a jour la manche     */
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

	/* Retourne la case d'apres la position */
	/*    de la souris apres deplacement    */
	private Case getCaseDepuisPixel(int pixelX, int pixelY)
	{
		if (this.controleur == null || this.controleur.getGrille() == null)
			return null;

		int taille  = this.controleur.getGrilleTailleCase();
		int largeur = this.controleur.getGrilleLargeur();
		int hauteur = this.controleur.getGrilleHauteur();

		int x = (pixelX - this.decalX) / taille;
		int y = (pixelY - this.decalY) / taille;

		if (pixelX >= this.decalX && pixelY >= this.decalY &&
			x >= 0 && x < largeur &&
			y >= 0 && y < hauteur)
		{
			return this.controleur.getGrilleCase(x, y);
		}

		return null;
	}

	/* Methode appelee pour verifier si la case */
	/*   d'arrivee est bien ce qu'on a pioche   */
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


		// Dessiner l'image de fond
		Image img = Toolkit.getDefaultToolkit().getImage("../images/icones/fond_pre.jpg");
		g.drawImage(img, 0, 0, getWidth(), getHeight(), this);

		if (controleur == null || controleur.getGrille() == null)
			return;

		// Calculer la position x,y des cases
		int largeur = controleur.getGrilleLargeur();
		int hauteur = controleur.getGrilleHauteur();
		int taille  = controleur.getGrilleTailleCase();

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

				g.setColor(controleur.getGrilleCase(x, y).getPlaine());
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
					Case connection = controleur.getGrilleCase(x, y).getConnection(cpt);

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
					if( controleur.getGrilleCase(x, y).getCaseDeplacement(cptDep) != null )
					{
						Case depart  = controleur.getGrilleCase(x, y);
						Case arrivee = controleur.getGrilleCase(x, y).getCaseDeplacement(cptDep);

						g2.setStroke(new BasicStroke(5));
						g2.setColor(depart.getCouleurDeplacement(cptDep));

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

				if (controleur.getGrilleCase(x, y).getDepart() != null)
					g.drawImage(controleur.getGrilleCase(x, y).getImageDepart(), posX, posY, taille, taille, this);

				if (controleur.getGrilleCase(x, y).getFleur() != null)
					g.drawImage(controleur.getGrilleCase(x, y).getImageFleur(), posX, posY, taille, taille, this);
			}
		}
	}
}