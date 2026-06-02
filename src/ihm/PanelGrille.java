package src.ihm;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import src.metier.Grille;

public class PanelGrille extends JPanel
{
	private Grille grille;
	private int decalX = 0;
	private int decalY = 0;

	public PanelGrille()
	{
		this.setBackground(Color.WHITE);
	}

	public void setGrille(Grille grille)
	{
		this.grille = grille;
		this.repaint();
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

				g.setColor(Color.BLACK);
				g.drawRect(posX, posY, grille.getTailleCase(), grille.getTailleCase());
			}
		}
	}
}
