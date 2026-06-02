package src.ihm;

import java.awt.*;
import javax.swing.*;
import src.Controleur;
import src.metier.Grille;

public class FramePlateau extends JFrame
{
	private PanelGrille panelGrille;
	private JTextField fieldLargeur;
	private JTextField fieldHauteur;
	private JTextField fieldTailleCase;

	public FramePlateau(Controleur ctrl)
	{
		this.setTitle("Grille");
		this.setSize(750, 750);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panelHaut = new JPanel();
		panelHaut.add(new JLabel("Largeur:"));
		fieldLargeur = new JTextField("10", 5);
		panelHaut.add(fieldLargeur);

		panelHaut.add(new JLabel("Hauteur:"));
		fieldHauteur = new JTextField("10", 5);
		panelHaut.add(fieldHauteur);

		panelHaut.add(new JLabel("Taille case:"));
		fieldTailleCase = new JTextField("50", 5);
		panelHaut.add(fieldTailleCase);

		JButton btnCreer = new JButton("Créer");
		btnCreer.addActionListener(e -> creerGrille());
		panelHaut.add(btnCreer);

		panelGrille = new PanelGrille(null);

		this.getContentPane().setLayout(new BorderLayout());
		this.getContentPane().add(panelHaut, BorderLayout.NORTH);
		this.getContentPane().add(panelGrille, BorderLayout.CENTER);

		this.setVisible(true);
	}

	private void creerGrille()
	{
		try
		{
			int largeur = Integer.parseInt(fieldLargeur.getText());
			int hauteur = Integer.parseInt(fieldHauteur.getText());
			int taille = Integer.parseInt(fieldTailleCase.getText());

			if ( largeur > 10 || hauteur > 10) {
				JOptionPane.showMessageDialog(this, "Merci de choisir un hauteur et largeur inférieur ou égale à 10");
			}

			if (largeur > 0 && hauteur > 0 && taille > 0)
			{
				Grille grille = new Grille(largeur, hauteur);
				panelGrille.setGrille(grille);
				panelGrille.setTailleCase(taille);
			}
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(this, "Erreur");
		}
	}
}