package src.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;
import javax.swing.JFrame;
import src.Controleur;

public class FramePlateau extends JFrame
{
	private Controleur    ctrl;

	private PanelAccueil      panelAccueil;
	private PanelCreeGrille   panelControle;
	private PanelGrille       panelGrille;
	private PanelChoixRegion  panelChoixReg;
	private PanelChoixFleurs  panelChoixFleurs;
	private PanelContruction  panelContruction;
	

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
		this.panelGrille      = new PanelGrille();
		this.panelControle    = new PanelCreeGrille ( this );
		this.panelContruction = new PanelContruction( this );
		this.panelAccueil     = new PanelAccueil    ( this.ctrl );
		this.panelChoixReg    = new PanelChoixRegion( this );
		this.panelChoixFleurs = new PanelChoixFleurs( this );

		this.add(panelControle, BorderLayout.WEST);
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
	
		this.add(this.panelChoixReg, BorderLayout.NORTH);
		this.add(this.panelGrille,   BorderLayout.CENTER);
	
		this.revalidate();
		this.repaint();
	}

	/*public void AfficherPanelJeu()
	{
		this.add(panelControle, BorderLayout.NORTH);
		this.add(panelGrille,   BorderLayout.CENTER);
	}*/

	// methode pour afficher le deuxieme panel de choix de fleurs
	public void afficherChoixFleurs()
	{
		this.panelGrille.desactiverColoriage();
	
		this.remove(this.panelChoixReg);
	
		this.add( this.panelChoixFleurs, BorderLayout.NORTH );
		this.add( this.panelGrille,      BorderLayout.CENTER);
	
		this.revalidate();
		this.repaint();
	}
	
}