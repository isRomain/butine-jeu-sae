package conception.ihm;

import conception.metier.Grille;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelCreeGrille extends JPanel implements ActionListener
{
	private FramePlateau prnt;

	private JButton btnNiveaux, btnPlateau;

	public PanelCreeGrille (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(2, 1, 0, 10) );


		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		this.btnNiveaux = new JButton("Sélectionner un niveau");
		this.btnPlateau = new JButton("Jouer sur un plateau créé");

		stylerBouton( this.btnNiveaux, new Color(245, 180,  40) );
		stylerBouton( this.btnPlateau, new Color(120, 170,  90) );


		/*---------------------------*/
		/*  Placement des composants */
		/*---------------------------*/
		this.add( this.btnNiveaux );
		this.add( this.btnPlateau );


		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.btnNiveaux.addActionListener( this );
		this.btnPlateau.addActionListener( this );
	}

	private void stylerBouton( JButton btn, final Color couleur )
	{
		btn.setBackground( couleur );
		btn.setForeground( Color.WHITE );
		btn.setFont( new Font("Arial", Font.BOLD, 14) );
		btn.setFocusPainted( false );
		btn.setCursor( new Cursor(Cursor.HAND_CURSOR) );
		btn.setBorder( BorderFactory.createEmptyBorder(8, 16, 8, 16) );
	}

	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == this.btnNiveaux )
		{
			this.prnt.afficherChoixNiveau();
		}

		if ( e.getSource() == this.btnPlateau )
		{
			JFileChooser chooser = new JFileChooser( ".." );
			int value = chooser.showOpenDialog(this);

			if(value == JFileChooser.APPROVE_OPTION)
			{

				Grille grille = this.prnt.ImporterGrille(chooser.getSelectedFile().getAbsolutePath());

				if (grille == null)
				{
					JOptionPane.showMessageDialog(this, "Veuillez sélectionner un fichier contenant les données d'une grille valide !");
					return;
				}

				prnt.setGrille( grille );
				grille.trouverConnections();

				this.prnt.lancerJeu();
			}
		}
	}
}