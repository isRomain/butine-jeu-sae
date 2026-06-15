package jeu.ihm;

import java.awt.BorderLayout;
import java.awt.Toolkit;
import javax.swing.*;
import jeu.Controleur;
import jeu.metier.Grille;

public class FramePlateauJeu extends JFrame
{
	private Controleur       ctrl;

	private PanelAccueil     panelAccueil;
	private PanelImporter    panelControle;
	private PanelChoixNiveau panelChoixNiveau;
	private PanelGrilleJeu   panelGrilleJeu;
	private PanelCarte       panelCarte;

	private Grille grille;

	public FramePlateauJeu(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout(new BorderLayout());
		this.setIconImage( Toolkit.getDefaultToolkit().getImage("../images/icones/abeille.png") );

		
		this.setTitle("Butine !");
		this.setSize(750, 750);


		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.panelControle = new PanelImporter ( this );
		this.panelAccueil  = new PanelAccueil  ( this.ctrl );


		/*-------------------------*/
		/* Ajout des composants    */
		/*-------------------------*/
		this.add(panelControle, BorderLayout.WEST);
		this.add(panelAccueil,  BorderLayout.CENTER);


		this.setVisible(true);
	}

	public void afficherChoixNiveau()
	{
		this.remove(this.panelControle);
		this.remove(this.panelAccueil);

		this.panelChoixNiveau = new PanelChoixNiveau( this );

		this.add(this.panelChoixNiveau, BorderLayout.CENTER);

		this.revalidate();
		this.repaint();
	}

	public void retourAccueilNiveau()
	{
		this.remove(this.panelChoixNiveau);

		this.add( this.panelControle, BorderLayout.WEST   );
		this.add( this.panelAccueil,  BorderLayout.CENTER );

		this.revalidate();
		this.repaint();
	}

	public void lancerJeu(Grille grille, boolean estImporter)
	{
		this.grille = grille;
		this.ctrl.setGrille(grille);
		
		if (estImporter)
		{
			this.remove(this.panelControle);
			this.remove(this.panelAccueil);
		}

		if (false == estImporter) this.remove(this.panelChoixNiveau);
	
		this.panelCarte     = new PanelCarte(this.ctrl, grille.nbDeparts());
		this.panelGrilleJeu = new PanelGrilleJeu( this.ctrl );
	
		this.add(this.panelCarte,     BorderLayout.NORTH);
		this.add(this.panelGrilleJeu, BorderLayout.CENTER);
	
		this.revalidate();
		this.repaint();
	}

	public void deplacementEffectue()
	{
		this.panelCarte.piocher();
		this.panelCarte.setPoints(this.ctrl.calculerScore());
	}

	public Grille ImporterGrille(String path)
	{
		try
		{
			return this.ctrl.importerGrille(path);
		}
		catch (Exception err)
		{
			return null;
		}
	}

	public int getMancheActuelle()
	{
		return this.panelCarte.getMancheActuelle();
	}
}
