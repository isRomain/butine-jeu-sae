package src.ihm;

import java.awt.*;
import javax.swing.*;
import src.Controleur;

public class PanelPlateau extends JPanel
{
	private Controleur ctrl;

	public PanelPlateau(Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout( new GridLayout( 7,7 ) );
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// Dessin des plaines
		// Dessin des sentiers
		// Dessin des fleurs
		// Dessin des chemins colorés
	}
}