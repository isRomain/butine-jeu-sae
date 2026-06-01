package src.ihm;

import javax.swing.*;

import src.Controleur;

public class FramePlateau extends JFrame
{

	public FramePlateau ( Controleur ctrl )
	{
		this.setTitle   ( "Dessin");
		this.setSize    ( 640,660 );
		this.setLocation(  50, 50 );


		// Gestion de la fermeture de la fenêtre
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
	}
}