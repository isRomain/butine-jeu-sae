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
	private PanelImporter  panelControle;
	private PanelChoixNiveau panelChoixNiveau;

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
		this.panelAccueil  = new PanelAccueil    ( this.ctrl );


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

	public void lancerJeu(Grille grille)
	{
		this.grille = grille;

		this.remove(this.panelChoixNiveau);

		PanelGrilleJeu panelGrilleJeu = new PanelGrilleJeu( this, grille );
		PanelCarte     panelCarte     = new PanelCarte    ( this.ctrl    );

		this.add( panelGrilleJeu, BorderLayout.CENTER );
		this.add( panelCarte    , BorderLayout.NORTH  );
	
		this.revalidate();
		this.repaint();
	}

	public Controleur getControleur() { return this.ctrl; }

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
}
