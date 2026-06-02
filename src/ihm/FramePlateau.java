package src.ihm;

import java.awt.BorderLayout;

import javax.swing.JFrame;

import src.Controleur;

public class FramePlateau extends JFrame
{
	private Controleur ctrl;

	private PanelGrille panelGrille;

	private PanelControle panelControle;

	public FramePlateau(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setTitle("Butine !");
		this.setSize(750, 750);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		panelGrille   = new PanelGrille  ();
		panelControle = new PanelControle(this);

		this.setLayout(new BorderLayout());
		this.add(panelControle, BorderLayout.NORTH);
		this.add(panelGrille,   BorderLayout.CENTER);

		this.setVisible(true);
	}

	public void setGrille (int largeur, int hauteur, int taille)
	{
		this.panelGrille.setGrille( this.ctrl.creerGrille(largeur, hauteur, taille));
	}
}