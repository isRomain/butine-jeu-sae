package jeu.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
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

	public PanelGrilleJeu(FramePlateauJeu prnt, Grille grille)
	{
		this.prnt   = prnt;
		this.grille = grille; 

		/*MouseAdapter souris = new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				actionCase(e.getX(), e.getY());
			}

			public void mouseDragged(MouseEvent e)
			{
				actionCase(e.getX(), e.getY());
			}
		};

		this.addMouseListener(souris);
		this.addMouseMotionListener(souris);*/
	}

	public Grille getGrille() { return this.grille; }

	/*private void actionCase(int pixelX, int pixelY)
	{
		int taille = grille.getTailleCase();
		int x = (pixelX - decalX) / taille;
		int y = (pixelY - decalY) / taille;

		if (pixelX >= decalX && pixelY >= decalY &&
			x >= 0 && x < grille.getLargeur() &&
			y >= 0 && y < grille.getHauteur())
		{ 
			if (this.couleurPlaine != null && !this.estPanelExport)
			{
				grille.getCase(x, y).setPlaine(couleurPlaine);
				this.repaint();
			}
			if (this.formeFleur != null && this.modeFleurs)
			{
				grille.getCase(x, y).setFleur(this.formeFleur);
				grille.trouverConnections();
				this.repaint();
			}
			if (this.couleurDepart != null && this.modeFleurs)
			{
				grille.getCase(x, y).setDepart(this.couleurDepart);
				this.repaint();
			}
		}
	}*/

	/*public void activerRegions()
	{
		this.couleurPlaine  = new Color(255, 214, 165, 200);
		this.modeFleurs     = false;
		this.estPanelExport = false;
	}*/

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

		decalX = (this.getWidth()  - largeurGrille) / 2;
		decalY = (this.getHeight() - hauteurGrille) / 2;

		for (int etapes = 0; etapes < 3; etapes++)
		{
			for (int y = 0; y < hauteur; y++)
			{
				for (int x = 0; x < largeur; x++)
				{
					int posX = decalX + x * grille.getTailleCase();
					int posY = decalY + y * grille.getTailleCase();

					Color couleurCase = grille.getCase(x, y).getPlaine();
					
					switch (etapes)
					{
						case 0:
						g.setColor(couleurCase);
						g.fillRect(posX, posY, grille.getTailleCase(), grille.getTailleCase());
					
						case 1:
						for (int cpt = 0; cpt < 8; cpt++)
						{
							Case connection = grille.getCase(x, y).getConnection(cpt);
							if (connection != null)
							{
								g.setColor(Color.BLACK);
								g.drawLine(posX + grille.getTailleCase()/2, posY + grille.getTailleCase()/2, (decalX + connection.getX() * grille.getTailleCase()) + grille.getTailleCase()/2, (decalY + connection.getY() * grille.getTailleCase()) + grille.getTailleCase()/2);
							}
						}
					
						case 2:
						if (grille.getCase(x, y).getDepart() != null)
						{
							g.drawImage(grille.getCase(x, y).getImageDepart(), posX, posY, grille.getTailleCase(), grille.getTailleCase(), this);
						}

						if (grille.getCase(x, y).getFleur() != null)
						{
							g.drawImage(grille.getCase(x, y).getImageFleur(), posX, posY, grille.getTailleCase(), grille.getTailleCase(), this);
						}
					}
				}
			}
		}
	}

	/*public void setGrille(Grille grille)
	{
		this.grille = grille;
		this.repaint();
	}*/
}