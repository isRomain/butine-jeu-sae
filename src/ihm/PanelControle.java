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

	private JTextField fieldLargeur, fieldHauteur, fieldTailleCase;

	private JButton btnCreer;

	private JComboBox<String> comboPlaine;
	private Color[] couleursPlaine = {
		new Color(255, 200, 150), // orange
		new Color(200, 230, 200), // vert
		new Color(200, 210, 240), // bleu
		new Color(240, 200, 230), // violet
		new Color(139, 69 , 19 ), // marron
		new Color(255, 0  , 0  ), // rouge
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
		comboPlaine      = new JComboBox<String>(new String[]{"Orange", "Vert", "Bleu", "Violet", "Marron", "Rouge"});


		/*---------------------------*/
		/*  Placement des composants */
		/*---------------------------*/
		this.add(new JLabel("Largeur:"));
		this.add(fieldLargeur);

		this.add(new JLabel("Hauteur:"));
		this.add(fieldHauteur);

		this.add(new JLabel("Taille case:"));
		this.add(fieldTailleCase);

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