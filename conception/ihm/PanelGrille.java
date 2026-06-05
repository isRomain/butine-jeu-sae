package conception.ihm;

import conception.metier.Case;
import conception.metier.Grille;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;

public class PanelGrille extends JPanel
{
	private FramePlateau prnt;

	private Grille grille;
	private int decalX = 0;
	private int decalY = 0;

	private Color couleurPlaine;
	private String formeFleur, couleurDepart;

	private boolean afficherTraits = true;
	private boolean afficherFleurs = true;

	public PanelGrille(FramePlateau prnt)
	{
		this.prnt = prnt;
		this.couleurPlaine = new Color(255, 214, 165, 200);
		this.formeFleur = this.couleurDepart = "vide";

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

	public Grille getGrille() { return this.grille; }

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
				grille.trouverConnections();
				this.repaint();
			}
			if (this.couleurDepart != null && !this.formeFleur.equals("vide"))
			{
				grille.getCase(x, y).setDepart(this.couleurDepart);
				this.repaint();
			}
		}
	}

	public void effacerTraits()
	{
		 this.afficherTraits = false;
	}

	public void afficherTraits()
	{
		 this.afficherTraits = true;
	}

	public void activerFleurs()
	{
		 this.afficherFleurs = true;
	}

	public void desactiverFleurs()
	{
		 this.afficherFleurs = false;
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

				if( this.afficherTraits )
				{
					 g.setColor(Color.BLACK);
				     g.drawRect(posX, posY, grille.getTailleCase(), grille.getTailleCase());
				}

				if (grille.getCase(x, y).getDepart() != null)
				{
					g.drawImage(grille.getCase(x, y).getImageDepart(), posX, posY, grille.getTailleCase(), grille.getTailleCase(), this);
				}

				if (grille.getCase(x, y).getFleur() != null && this.afficherFleurs)
				{
					g.drawImage(grille.getCase(x, y).getImageFleur(), posX, posY, grille.getTailleCase(), grille.getTailleCase(), this);
				}

				for (int cpt = 0; cpt < 8; cpt++)
				{
					if( this.afficherFleurs )
					{
						 Case connection = grille.getCase(x, y).getConnection(cpt);
						 if (connection != null)
						 {
						 	g.setColor(Color.BLACK);
						 	g.drawLine(posX + grille.getTailleCase()/2, posY + grille.getTailleCase()/2, (decalX + connection.getX() * grille.getTailleCase()) + grille.getTailleCase()/2, (decalY + connection.getY() * grille.getTailleCase()) + grille.getTailleCase()/2);
						 }
					}
				}
			}
		}
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

	public boolean departUnique()
	{
		return this.grille.departUnique();
	}
}