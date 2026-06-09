package jeu.ihm;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import jeu.Controleur;

public class PanelCarte extends JPanel
{
	private Controleur ctrl;

	private JPanel  panelTour, panelPoint;

	private JLabel labelTour, labelPoint;

	public PanelCarte (Controleur ctrl)
	{
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(50, 250));

		this.panelTour    = new JPanel();
		this.panelPoint   = new JPanel();

		this.labelTour    = new JLabel("Tour: 0/?" );
		this.labelPoint   = new JLabel("Points: 98");


		this.add( this.panelTour , BorderLayout.WEST );
		this.add( this.panelPoint, BorderLayout.EAST );

		this.panelTour.add ( this.labelTour  );
		this.panelPoint.add( this.labelPoint );

		this.repaint();
	}

	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		Image img = Toolkit.getDefaultToolkit().getImage("../images/cartes/carte_clair_carre.png");
		g.drawImage(img, this.getWidth()/2 - 100, 0, 200, 250, this);
	}
}