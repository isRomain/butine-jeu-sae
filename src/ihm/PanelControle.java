package src.ihm;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelControle extends JPanel
{
	private FramePlateau prnt;

	private JTextField fieldLargeur, fieldHauteur, fieldTailleCase;

	private JButton btnCreer;

	public PanelControle (FramePlateau prnt)
	{
		this.prnt = prnt;

		//CREATIONS//
		fieldLargeur     = new JTextField("10", 5);
		fieldHauteur     = new JTextField("10", 5);
		fieldTailleCase  = new JTextField("50", 5);
		btnCreer         = new JButton("Créer");


		//MODIFICATIONS//
		btnCreer.addActionListener(e -> creerGrille());


		//PLACEMENT//
		this.add(new JLabel("Largeur:"));
		this.add(fieldLargeur);

		this.add(new JLabel("Hauteur:"));
		this.add(fieldHauteur);

		this.add(new JLabel("Taille case:"));
		this.add(fieldTailleCase);

		this.add(btnCreer);
	}

	private void creerGrille()
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
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this, "Erreur");
		}
	}
}