package src.ihm;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import src.Controleur;

public class PanelAccueil extends JPanel
{
	private Controleur ctrl;

	private Image imgAccueil;

	public PanelAccueil( Controleur ctrl )
	{
		this.ctrl = ctrl;   

		this.imgAccueil = new ImageIcon(this.ctrl.getImageAcceuil()).getImage();
	}

	public void paintComponent( Graphics g )
	{
		super.paintComponent( g );
		g.drawImage(imgAccueil, 0, 0, getWidth(), getHeight(), this);
	}
}