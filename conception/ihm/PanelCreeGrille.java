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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelCreeGrille extends JPanel implements ActionListener
{
	private FramePlateau prnt;

	private JTextField fieldLargeur;
	private JTextField fieldHauteur;
	private JTextField fieldTailleCase;

	private JButton btnCreer, btnModifier;

	public PanelCreeGrille (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(8, 1) );


		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		fieldLargeur     = new JTextField( "10", 5 );
		fieldHauteur     = new JTextField( "10", 5 );
		fieldTailleCase  = new JTextField( "50", 5 );

		btnCreer         = new JButton( "Créer"    );
		btnModifier      = new JButton( "Modifier" );

		stylerBouton( btnCreer,    new Color(245, 180,  40) );
		stylerBouton( btnModifier, new Color(120, 170,  90) );

		stylerChamp( fieldLargeur    );
		stylerChamp( fieldHauteur    );
		stylerChamp( fieldTailleCase );


		/*---------------------------*/
		/*  Placement des composants */
		/*---------------------------*/
		this.add( new JLabel( "Largeur:" ) );
		this.add( fieldLargeur );

		this.add( new JLabel( "Hauteur:" ) );
		this.add(fieldHauteur);

		this.add( new JLabel( "Taille case:" ) );
		this.add(fieldTailleCase);

		this.add( btnCreer    );
		this.add( btnModifier );


		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		btnCreer   .addActionListener( this );
		btnModifier.addActionListener( this );
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

	private void stylerChamp( JTextField champ )
	{
		champ.setFont( new Font("Arial", Font.PLAIN, 14) );
		champ.setHorizontalAlignment( JTextField.CENTER );
		champ.setBackground( new Color(255, 250, 235) );
		champ.setBorder( BorderFactory.createCompoundBorder(
			BorderFactory.createLineBorder( new Color(245, 180, 40), 2 ),
			BorderFactory.createEmptyBorder(6, 8, 6, 8) ) );
	}

	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == this.btnCreer )
		{

			/* 
				Lorsque l'utilisateur souhaite créer un plateau, les dimensions (largeur, hauteur et taille des cases) sont récupérées.

				Ensuite, on vérifie que :

				- La largeur et la hauteur doivent être supérieure à 0 et inférieure ou égale à 10. 
				- La taille des cases doit être supérieure à 0 et inférieure ou égale à 100.

				Puis la grille est créée avec les bonnes dimensions et la conception du plateau se lance
			*/

			try
			{
				int largeur = Integer.parseInt( fieldLargeur.getText()    );
				int hauteur = Integer.parseInt( fieldHauteur.getText()    );
				int taille  = Integer.parseInt( fieldTailleCase.getText() );
				
				if (largeur >  0  && hauteur >  0  && taille >  0 &&
					largeur <= 10 && hauteur <= 10 && taille <= 100 )
				{
					prnt.setGrille( largeur, hauteur, taille );
					this.prnt.lancerConception();
				}
				else
				{
					if( taille > 100 )
					{
						JOptionPane.showMessageDialog( this, "Erreur: La taille doit être comprise entre 1 et 100" );
					}
					else
					{
						JOptionPane.showMessageDialog( this, "Erreur: la valeur doit être comprise entre 1 et 10" );
					}
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Erreur");
			}
		}

		if ( e.getSource() == this.btnModifier )
		{
			JFileChooser chooser = new JFileChooser( ".." );
			int value = chooser.showOpenDialog(this);
		
			if(value == JFileChooser.APPROVE_OPTION)
			{
		
				/* On importe la grille a à partir du JFileChooser */
				Grille grille = this.prnt.ImporterGrille(chooser.getSelectedFile().getAbsolutePath());

				if (grille == null) 
				{ 
					JOptionPane.showMessageDialog(this, "Veuillez sélectionner un fichier contenant les données d'une grille valide !");
					return;
				}

				prnt.setGrille( grille );
				grille.trouverConnections();

				this.prnt.lancerConception();
				System.out.println("Grille trouvée et jeu lancé");
				
			}
		}	
	}
}