package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import src.Controleur;

public class FramePlateau extends JFrame
{
	private Controleur    ctrl;

	private PanelAccueil      panelAccueil;
	private PanelCreeGrille   panelControle;
	private PanelGrille       panelGrille;
	private PanelChoixRegion  panelChoixReg;
	private PanelChoixFleurs  panelChoixFleurs;
	

	public FramePlateau(Controleur ctrl)
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
		this.panelGrille      = new PanelGrille     ( this );
		this.panelControle    = new PanelCreeGrille ( this );
		this.panelAccueil     = new PanelAccueil    ( this.ctrl );


		/*-------------------------*/
		/* Ajout des composants    */
		/*-------------------------*/
		this.add(panelControle, BorderLayout.WEST);
		this.add(panelAccueil,  BorderLayout.CENTER);


		this.setVisible(true);
	}

	public void lancerJeu()
	{
		this.remove(this.panelAccueil);
		this.remove(this.panelControle);

		this.panelChoixReg    = new PanelChoixRegion( this );
	
		this.add(this.panelChoixReg, BorderLayout.NORTH);
		this.add(this.panelGrille,   BorderLayout.CENTER);
	
		this.revalidate();
		this.repaint();
	}

	public void afficherChoixFleurs()
	{
		if (!this.panelGrille.verifierRegions())
		{
			JOptionPane.showMessageDialog(this,
				"Chaque région doit former un seul bloc d'un seul tenant.",
				"Régions invalides", JOptionPane.ERROR_MESSAGE);
			return;
		}

		this.panelGrille.setCouleurPlaine(null);
	
		this.remove(this.panelChoixReg);

		this.panelChoixFleurs = new PanelChoixFleurs( this );
	
		this.add( this.panelChoixFleurs, BorderLayout.NORTH );
		this.add( this.panelGrille,      BorderLayout.CENTER);
	
		this.revalidate();
		this.repaint();
	}

	public void setGrille (int largeur, int hauteur, int taille)
	{
		this.panelGrille.setGrille( this.ctrl.creerGrille(largeur, hauteur, taille));
	}

	public void setCouleurPlaine (Color couleur)
	{
		this.panelGrille.setCouleurPlaine( couleur );
	}

	public void setFleur (String forme)
	{
		this.panelGrille.setFleur(forme);
	}

	public void setDepart (String forme)
	{
		this.panelGrille.setDepart(forme);
	}
}