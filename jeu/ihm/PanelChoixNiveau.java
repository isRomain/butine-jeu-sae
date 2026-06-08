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
import javax.swing.JPanel;

public class PanelChoixNiveau extends JPanel implements ActionListener
{
	private FramePlateau prnt;

	private JButton   btnPrecedent;
	private JButton[] btnNiveaux;

	private ImageIcon iconPrec;

	private Image imgAccueil;

	public PanelChoixNiveau( FramePlateau prnt )
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
		this.btnPrecedent.setOpaque(false);

		this.btnNiveaux = new JButton[10];

		JPanel panelNiveaux = new JPanel( new GridLayout(2, 5, 5, 5) );
		panelNiveaux.setOpaque(false);
		panelNiveaux.setBorder( BorderFactory.createEmptyBorder(10, 10, 10, 10) );

		for (int i = 0; i < this.btnNiveaux.length; i++)
		{
			ImageIcon icon = new ImageIcon(
				new ImageIcon("../images/niveaux/niv" + (i + 1) + ".png")
				.getImage()
				.getScaledInstance(360, 420, Image.SCALE_SMOOTH)
			);
		
			this.btnNiveaux[i] = new JButton(icon);
			this.btnNiveaux[i].setText("");
		
			this.btnNiveaux[i].setPreferredSize(new Dimension(280, 220));
		
			this.btnNiveaux[i].setBackground(new Color(245, 180, 40));
			this.btnNiveaux[i].setBorder(BorderFactory.createLineBorder(new Color(120, 80, 20), 4));
		
			this.btnNiveaux[i].setFocusPainted(false);
			this.btnNiveaux[i].setContentAreaFilled(true);
			this.btnNiveaux[i].setOpaque(true);
		
			this.btnNiveaux[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
		
			panelNiveaux.add(this.btnNiveaux[i]);
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

	private void stylerBouton( JButton btn, Color couleur )
	{
		btn.setBackground( couleur );
		btn.setForeground( Color.WHITE );
		btn.setFont( new Font("Arial", Font.BOLD, 20) );
		btn.setFocusPainted( false );
		btn.setCursor( new Cursor(Cursor.HAND_CURSOR) );
		btn.setBorder( BorderFactory.createEmptyBorder(8, 16, 8, 16) );
	}

	public void actionPerformed( ActionEvent e )
	{
		if ( e.getSource() == this.btnPrecedent )
		{
			this.prnt.retourAccueilNiveau();
		}
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.drawImage(imgAccueil, 0, 0, getWidth(), getHeight(), this);
	}
}
