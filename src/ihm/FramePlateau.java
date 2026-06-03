package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JFrame;
import src.Controleur;

public class FramePlateau extends JFrame
{
	private Controleur    ctrl;

	private PanelGrille   panelGrille;
	private PanelCreeGrille panelControle;
	private PanelContruction panelContruction;
	private PanelAccueil  panelAccueil;

	public FramePlateau(Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());

		this.setTitle("Butine !");
		this.setSize(750, 750);


		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		panelGrille   = new PanelGrille  ();
		panelControle = new PanelCreeGrille(this);
		panelContruction = new PanelContruction(this);
		panelAccueil  = new PanelAccueil( this.ctrl );

		this.add(panelControle, BorderLayout.NORTH);
		this.add(panelAccueil,  BorderLayout.CENTER);


		this.setVisible(true);
	}

	public void setGrille (int largeur, int hauteur, int taille)
	{
		this.panelGrille.setGrille( this.ctrl.creerGrille(largeur, hauteur, taille));
	}

	public void setCouleurPlaine (Color couleur)
	{
		this.panelGrille.setCouleurPlaine( couleur );
	}

	public void lancerJeu()
	{
		this.remove(this.panelAccueil);
		this.remove(this.panelControle);
	
		this.add(panelContruction, BorderLayout.NORTH);
		this.add(panelGrille,   BorderLayout.CENTER);
	
		this.revalidate();
		this.repaint();
	}

	public void AfficherPanelJeu()
	{
		this.add(panelControle, BorderLayout.NORTH);
		this.add(panelGrille,   BorderLayout.CENTER);
	}

}