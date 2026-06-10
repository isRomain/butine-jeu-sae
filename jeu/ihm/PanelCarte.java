package jeu.ihm;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import jeu.Controleur;
import jeu.metier.Pile;

public class PanelCarte extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private Image imgAccueil;

	private JPanel  panelTour, panelPoint, panelCentre;

	private JLabel labelTour, labelPoint, labelCarteTiree;

	private JButton btnPioche;

	private Pile pile;

	public PanelCarte (Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(50, 250));

		this.imgAccueil = new ImageIcon("../images/icones/bandeau_haut.png").getImage();

		this.pile = new Pile();

		this.panelTour    = new JPanel();
		this.panelPoint   = new JPanel();
		this.panelCentre  = new JPanel( new FlowLayout(FlowLayout.CENTER, 20, 10) );
		this.panelCentre.setOpaque(false);

		this.labelTour    = new JLabel("Tour: 0" );
		this.labelPoint   = new JLabel("Points: 0");

		this.btnPioche = new JButton( chargerIcone("../images/cartes/carte_dos.png") );
		this.btnPioche.setPreferredSize( new Dimension(160, 200) );
		this.btnPioche.setContentAreaFilled(false);
		this.btnPioche.setBorderPainted(false);
		this.btnPioche.setFocusPainted(false);
		this.btnPioche.setCursor( new Cursor(Cursor.HAND_CURSOR) );

		this.labelCarteTiree = new JLabel();
		this.labelCarteTiree.setPreferredSize( new Dimension(160, 200) );

		this.panelCentre.add( this.btnPioche       );
		this.panelCentre.add( this.labelCarteTiree );

		this.add( this.panelTour  , BorderLayout.WEST   );
		this.add( this.panelPoint , BorderLayout.EAST   );
		this.add( this.panelCentre, BorderLayout.CENTER );

		this.panelTour.add ( this.labelTour  );
		this.panelPoint.add( this.labelPoint );

		this.btnPioche.addActionListener( this );

		this.repaint();
	}

	private ImageIcon chargerIcone(String chemin)
	{
		return new ImageIcon(new ImageIcon(chemin).getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH));
	}

	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() == this.btnPioche )
		{
			String carte = this.pile.piocher();

			if (carte == null)
			{
				this.btnPioche.setEnabled(false);
				this.btnPioche.setIcon(null);
				this.btnPioche.setText("pile vide");
				return;
			}

			this.labelCarteTiree.setIcon( chargerIcone("../images/cartes/" + carte) );

			this.ctrl.setFormeCarte( carte.split("_")[2].replace(".png", "") );
		}
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(imgAccueil, 0, 0, getWidth(), getHeight(), this);
	}
}