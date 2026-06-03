package src.ihm;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JPanel;
import src.metier.Grille;

public class PanelGrille extends JPanel
{
	private Grille grille;
	private int decalX = 0;
	private int decalY = 0;

	private Color couleurPlaine = new Color(255, 200, 150);

	public PanelGrille()
	{
		this.setBackground(Color.WHITE);

		this.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				colorierCase(e.getX(), e.getY());
			}
		});
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

	private void colorierCase(int pixelX, int pixelY)
	{
		if (grille == null)
			return;

		int taille = grille.getTailleCase();
		int x = (pixelX - decalX) / taille;
		int y = (pixelY - decalY) / taille;

		if (pixelX >= decalX && pixelY >= decalY &&
			x >= 0 && x < grille.getLargeur() &&
			y >= 0 && y < grille.getHauteur())
		{
			grille.setCouleurPlaine(x, y, couleurPlaine);
			this.repaint();
		}
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

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

				Color couleurCase = grille.getCouleurPlaine(x, y);
				if (couleurCase != null)
				{
					g.setColor(couleurCase);
					g.fillRect(posX, posY, grille.getTailleCase(), grille.getTailleCase());
				}

				g.setColor(Color.BLACK);
				g.drawRect(posX, posY, grille.getTailleCase(), grille.getTailleCase());
			}
		}
	}
}
