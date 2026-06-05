package src.ihm;

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

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import src.metier.Case;
import src.metier.Grille;

public class PanelCreeGrille extends JPanel implements ActionListener
{
	private FramePlateau prnt;

	private JTextField fieldLargeur, fieldHauteur, fieldTailleCase;

	private JButton btnCreer, btnModifier;

	public PanelCreeGrille (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(8, 1) );


		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		fieldLargeur     = new JTextField("10", 5);
		fieldHauteur     = new JTextField("10", 5);
		fieldTailleCase  = new JTextField("50", 5);
		btnCreer         = new JButton("Créer");
		btnModifier      = new JButton("Modifier");

		stylerBouton( btnCreer,    new Color(245, 180,  40) );
		stylerBouton( btnModifier, new Color(120, 170,  90) );

		stylerChamp( fieldLargeur    );
		stylerChamp( fieldHauteur    );
		stylerChamp( fieldTailleCase );


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
		this.add(btnModifier);


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
			try
			{
				int largeur = Integer.parseInt( fieldLargeur.getText()   );
				int hauteur = Integer.parseInt( fieldHauteur.getText()   );
				int taille  = Integer.parseInt( fieldTailleCase.getText());
				
				if (largeur >  0  && hauteur >  0  && taille >  0 &&
					largeur <= 10 && hauteur <= 10 && taille <= 100 )
				{
					prnt.setGrille( largeur, hauteur, taille );
					this.prnt.lancerJeu();
				}
				else
				{
					if( taille > 100 )
					{
						JOptionPane.showMessageDialog(this, "Erreur: La taille doit être comprise entre 1 et 100");
					}
					else
					{
						JOptionPane.showMessageDialog(this, "Erreur: la valeur doit être comprise entre 1 et 10");
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
			JFileChooser chooser = new JFileChooser();
			int value = chooser.showOpenDialog(this);
		
			if(value == JFileChooser.APPROVE_OPTION)
			{
		
				BufferedReader br = null;
				try {
					br = new BufferedReader(new FileReader(chooser.getSelectedFile().getAbsolutePath()));
		
					// Les 3 premières lignes : hauteur, largeur, taille d'une case
					int hauteur    = Integer.parseInt(br.readLine().trim());
					int largeur    = Integer.parseInt(br.readLine().trim());
					int tailleCase = Integer.parseInt(br.readLine().trim());
		
					// On peut avoir une ligne vide pour séparer l'en-tête des données
					String line = br.readLine();
					if (line != null && line.trim().isEmpty()) line = br.readLine();
		
					// Création de la grille avec les dimensions lues
					Grille grille = new Grille(largeur, hauteur, tailleCase);
		
					// Pour chaque ligne de la grille
					for (int lig = 0; lig < hauteur; lig++)
					{
						if (line == null) throw new IOException("Ligne manquante à la hauteur " + lig);
		
						// Séparation par ' | ' pour obtenir chaque case
						String[] cases = line.split("\\|");
						
						if (cases.length < largeur) throw new IOException("Nombre de cases insuffisant sur la ligne " + lig + " (attendu: " + largeur + ", obtenu: " + cases.length + ")");
		
						for (int col = 0; col < largeur; col++)
						{
							// Chaque case contient : couleur;fleur;depart
							String[] champs = cases[col].trim().split(";");
							
							if (champs.length < 3) throw new IOException("Champs insuffisants à la position [" + lig + "," + col + "]");
		
							String p      = champs[0].trim();
							String fleur  = champs[1].trim();
							String depart = champs[2].trim();
		
							// Valeur par défaut de la couleur
							Color plaine = new Color(255, 255, 255, 200);
		
							// Parser une couleur "R,G,B" ou "R,G,B,A"
							if (!p.isEmpty() && p.contains(","))
							{
								String[] c = p.split(",");
								try
								{
									int r = Integer.parseInt(c[0].trim());
									int g = Integer.parseInt(c[1].trim());
									int b = Integer.parseInt(c[2].trim());
									int a = (c.length > 3) ? Integer.parseInt(c[3].trim()) : 255;

									plaine = new Color(r, g, b, a);
								}
								catch (Exception ignored)
								{
									// En cas d'erreur, on garde la couleur par défaut
								}
							}
		
							// Création et remplissage de la case
							Case cs = new Case(lig, col);

							cs.setPlaine(plaine);
							cs.setFleur (fleur.isEmpty()  ? "vide" : fleur);
							cs.setDepart(depart.isEmpty() ? "vide" : depart);
							
							grille.setCase(col, lig, cs);
						}
		
						// Lire la ligne suivante du fichier
						line = br.readLine();
					}
		
					prnt.setGrille( grille );
					this.prnt.lancerJeu();
					System.out.println("Grille trouvée et jeu lancé");
		
				}
				catch (Exception error)
				{
					System.err.println("Erreur lors du chargement de la grille : " + error.getMessage());
					error.printStackTrace();
				}
				finally
				{
					if (br != null)
					{
						try {
							br.close();
						}
						catch (IOException e2)
						{
							e2.printStackTrace();
						}
					}
				}
			}
		}	
	}
}