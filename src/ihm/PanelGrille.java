package src.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import javax.swing.JPanel;
import src.metier.Grille;

public class PanelGrille extends JPanel
{
	private FramePlateau prnt;

	private Grille grille;
	private int decalX = 0;
	private int decalY = 0;

	private Color couleurPlaine = new Color(255, 200, 150);
	private String formeFleur, couleurDepart;

	public PanelGrille(FramePlateau prnt)
	{
		this.prnt = prnt;

		MouseAdapter souris = new MouseAdapter()
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
		this.addMouseMotionListener(souris);
	}

	public void setGrille(Grille grille)
	{
		this.grille = grille;
		this.repaint();
	}

	public void setCouleurPlaine(Color couleur)
	{
		this.couleurPlaine = couleur;
	}

	public void setFleur(String forme)
	{
		this.formeFleur = forme;
	}

	public void setDepart(String forme)
	{
		this.couleurDepart = forme;
	}
	
	public boolean verifierRegions()
	{
		return this.grille.regionsConnexes();
	}

	private void actionCase(int pixelX, int pixelY)
	{
		int taille = grille.getTailleCase();
		int x = (pixelX - decalX) / taille;
		int y = (pixelY - decalY) / taille;

		if (pixelX >= decalX && pixelY >= decalY &&
			x >= 0 && x < grille.getLargeur() &&
			y >= 0 && y < grille.getHauteur())
		{ 
			if (this.couleurPlaine != null)
			{
				grille.getCase(x, y).setPlaine(couleurPlaine);
				this.repaint();
			}
			if (this.formeFleur != null)
			{
				grille.getCase(x, y).setFleur(this.formeFleur);
				this.repaint();
			}
			if (this.couleurDepart != null)
			{
				grille.getCase(x, y).setDepart(this.couleurDepart);
				this.repaint();
			}
		}
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
				int posX = decalX + x * grille.getTailleCase();
				int posY = decalY + y * grille.getTailleCase();

				Color couleurCase = grille.getCase(x, y).getPlaine();
				g.setColor(couleurCase);
				g.fillRect(posX, posY, grille.getTailleCase(), grille.getTailleCase());

				g.setColor(Color.BLACK);
				g.drawRect(posX, posY, grille.getTailleCase(), grille.getTailleCase());

				String depart = grille.getCase(x, y).getDepart();
				if (depart != null)
				{
					g.drawImage(grille.getCase(x, y).getImageDepart(), posX, posY, grille.getTailleCase(), grille.getTailleCase(), this);
				}
				
				String fleur = grille.getCase(x, y).getFleur();
				if (fleur != null)
				{
					g.drawImage(grille.getCase(x, y).getImageFleur(), posX, posY, grille.getTailleCase(), grille.getTailleCase(), this);
				}
			}
		}
	}
}