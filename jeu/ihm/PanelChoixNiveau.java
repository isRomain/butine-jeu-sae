package jeu.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import jeu.metier.Grille;

public class PanelChoixNiveau extends JPanel implements ActionListener
{
	private FramePlateauJeu prnt;

	private JButton   btnPrecedent;
	private JButton[] tabBtnNiveaux;

	private ImageIcon iconPrec;

	private Image imgAccueil;

	public PanelChoixNiveau( FramePlateauJeu prnt )
	{
		this.prnt = prnt;

		this.imgAccueil = new ImageIcon("../images/icones/fond_ruche_accueil_selection.png").getImage();

		this.setLayout( new BorderLayout() );
		this.setOpaque(false);

		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		this.iconPrec = new ImageIcon(new ImageIcon("../images/icones/icon_precedent.png").getImage().getScaledInstance(60, 60, Image.SCALE_SMOOTH));

		this.btnPrecedent = new JButton(iconPrec);
		this.btnPrecedent.setPreferredSize( new Dimension(60, 60) );
		this.btnPrecedent.setFocusPainted(false);
		this.btnPrecedent.setOpaque(false);

		this.tabBtnNiveaux = new JButton[10];

		JPanel panelNiveaux = new JPanel( new GridLayout(2, 5, 5, 5) );
		panelNiveaux.setOpaque(false);
		panelNiveaux.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10) );

		for (int i = 0; i < this.tabBtnNiveaux.length; i++)
		{
			ImageIcon icon = new ImageIcon(
				new ImageIcon("../images/niveaux/niv" + (i + 1) + ".png")
				.getImage()
				.getScaledInstance(360, 420, Image.SCALE_SMOOTH)
			);
		
			this.tabBtnNiveaux[i] = new JButton(icon);
			this.tabBtnNiveaux[i].setText("");
		
			this.tabBtnNiveaux[i].setPreferredSize(new Dimension(280, 220));
		
			this.tabBtnNiveaux[i].setBackground(new Color(245, 180, 40));
			this.tabBtnNiveaux[i].setBorder(BorderFactory.createLineBorder(new Color(120, 80, 20), 4));
		
			this.tabBtnNiveaux[i].setFocusPainted(false);
			this.tabBtnNiveaux[i].setContentAreaFilled(true);
			this.tabBtnNiveaux[i].setOpaque(true);
		
			this.tabBtnNiveaux[i].setCursor(new Cursor(Cursor.HAND_CURSOR));

			this.tabBtnNiveaux[i].addActionListener( this );

			panelNiveaux.add(this.tabBtnNiveaux[i]);
		}

		/*----------------------------*/
		/* Positionner les composants */
		/*----------------------------*/
		JPanel panelGauche = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelGauche.setOpaque(false);
		panelGauche.add(this.btnPrecedent);

		JLabel lblTitre = new JLabel("Choisissez un niveau : ");
		lblTitre.setHorizontalAlignment(JLabel.CENTER);
		lblTitre.setFont(new Font("Arial", Font.BOLD, 32));

		JPanel panelHaut = new JPanel(new BorderLayout());
		panelHaut.setOpaque(false);

		panelHaut.add(panelGauche, BorderLayout.WEST);
		panelHaut.add(lblTitre, BorderLayout.CENTER);

		this.add(panelHaut, BorderLayout.NORTH);
		this.add(panelNiveaux, BorderLayout.CENTER);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.btnPrecedent.addActionListener( this );
	}

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnPrecedent )
		{
			this.prnt.retourAccueilNiveau();
			return;
		}

		for (int i = 0; i < this.tabBtnNiveaux.length; i++)
		{
			if ( e.getSource() == this.tabBtnNiveaux[i] )
			{
				Grille grille = this.prnt.ImporterGrille("../niveaux_data/niveau" + (i + 1) + ".data");

				if (grille == null)
				{
					JOptionPane.showMessageDialog(this, "Impossible de charger le niveau " + (i + 1));
					return;
				}

				grille.trouverConnections();

				this.prnt.lancerJeu( grille, false );
				return;
			}
		}
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.drawImage(imgAccueil, 0, 0, getWidth(), getHeight(), this);
	}
}
