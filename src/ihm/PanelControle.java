package src.ihm;

import java.awt.event.*;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelControle extends JPanel implements ActionListener
{
	private FramePlateau prnt;

	private JTextField fieldLargeur, fieldHauteur, fieldTailleCase;

	private JButton btnCreer;

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

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		btnCreer.addActionListener( this );
	}

	@Override
	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == this.btnCreer )
		{
			try
			{
				int largeur = Integer.parseInt( fieldLargeur.getText()   );
				int hauteur = Integer.parseInt( fieldHauteur.getText()   );
				int taille  = Integer.parseInt( fieldTailleCase.getText());
				
				if (largeur > 0 && hauteur > 0 && taille > 0)
				{
					prnt.setGrille( largeur, hauteur, taille );
				}
			}
			catch (NumberFormatException ex)
			{
				JOptionPane.showMessageDialog(this, "Erreur");
			}
		}
		
	}
}