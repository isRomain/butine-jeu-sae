package src.ihm;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelControle extends JPanel implements ActionListener
{
	private FramePlateau prnt;

	private JTextField fieldLargeur, fieldHauteur, fieldTailleCase, fieldNbCouleurs;

	private JButton btnCreer;

	private JComboBox<String> comboPlaine;
	private Color[] couleursPlaine = {
		new Color(255, 214, 165), // capucine
		new Color(255, 179, 186), // pivoine
		new Color(255, 223, 186), // chèvrefeuille
		new Color(255, 255, 186), // primevère
		new Color(204, 236, 197), // menthe
		new Color(186, 225, 255), // myosotis
		new Color(196, 217, 255), // lavande
		new Color(215, 196, 255), // lilas
		new Color(243, 198, 240), // glycine
		new Color(255, 198, 224), // cerisier
		new Color(186, 240, 230), // sauge
		new Color(225, 243, 198), // tilleul
	};

	public PanelControle (FramePlateau prnt)
	{
		this.prnt = prnt;

		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		fieldLargeur     = new JTextField("10", 5);
		fieldHauteur     = new JTextField("10", 5);
		fieldTailleCase  = new JTextField("50", 5);
		btnCreer         = new JButton("Créer");
		comboPlaine      = new JComboBox<String>(new String[]{"Capucine", "Pivoine", "Chèvrefeuille",
		"Primevère", "Menthe", "Myosotis", "Lavande", "Lilas", "Glycine", "Cerisier", "Sauge", "Tilleul"});
		fieldNbCouleurs  = new JTextField("2", 1);


		/*---------------------------*/
		/*  Placement des composants */
		/*---------------------------*/
		this.add(new JLabel("Largeur:"));
		this.add(fieldLargeur);

		this.add(new JLabel("Hauteur:"));
		this.add(fieldHauteur);

		this.add(new JLabel("Taille case:"));
		this.add(fieldTailleCase);

		this.add(new JLabel("Nombre de couleurs:"));
		this.add(fieldNbCouleurs);

		this.add(btnCreer);

		this.add(new JLabel("Zone:"));
		this.add(comboPlaine);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		btnCreer.addActionListener( this );
		comboPlaine.addActionListener( this );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == this.comboPlaine )
		{
			this.prnt.setCouleurPlaine( couleursPlaine[ comboPlaine.getSelectedIndex() ] );
		}

		if( e.getSource() == this.btnCreer )
		{
			try
			{
				int largeur = Integer.parseInt( fieldLargeur.getText()   );
				int hauteur = Integer.parseInt( fieldHauteur.getText()   );
				int taille  = Integer.parseInt( fieldTailleCase.getText());
				
				if (largeur >  0  && hauteur >  0 && taille > 0 &&
					largeur <= 10 && hauteur <= 10)
				{
					prnt.setGrille( largeur, hauteur, taille );
					this.prnt.lancerJeu();
				}
				else
				{
					JOptionPane.showMessageDialog(this, "Erreur: les valeurs doivent être comprises entre 1 et 10");
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Erreur");
			}
		}
		
	}
}