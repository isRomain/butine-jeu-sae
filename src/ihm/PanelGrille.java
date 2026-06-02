package src.ihm;

import java.awt.*;
import javax.swing.*;
import src.metier.Grille;

public class PanelGrille extends JPanel
{
	private Grille grille;
	private int tailleCase = 50;
	private int decalX = 0;
	private int decalY = 0;

	public PanelGrille(Grille grille)
	{
		this.grille = grille;
		this.setBackground(Color.WHITE);
	}

	public void setGrille(Grille grille)
	{
		this.grille = grille;
		this.repaint();
	}

	public void setTailleCase(int taille)
	{
		this.tailleCase = taille;
		this.repaint();
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		if (grille == null)
			return;

		int largeur = grille.getLargeur();
		int hauteur = grille.getHauteur();

		int largeurGrille = largeur * tailleCase;
		int hauteurGrille = hauteur * tailleCase;
		decalX = (this.getWidth() - largeurGrille) / 2;
		decalY = (this.getHeight() - hauteurGrille) / 2;

		for (int y = 0; y < hauteur; y++)
		{
			for (int x = 0; x < largeur; x++)
			{
				int posX = decalX + x * tailleCase;
				int posY = decalY + y * tailleCase;

				g.setColor(Color.BLACK);
				g.drawRect(posX, posY, tailleCase, tailleCase);
			}
		}
	}
}
