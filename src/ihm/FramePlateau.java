package src.ihm;

import javax.swing.*;
import src.Controleur;

public class FramePlateau extends JFrame
{
	private Controleur   ctrl;
	private PanelPlateau panelPlateau;

	public FramePlateau(Controleur ctrl)
	{
		this.ctrl = ctrl;

        this.setTitle("Plateau");
		this.setSize(750, 750);


		this.panelPlateau = new PanelPlateau(ctrl);
		this.add(this.panelPlateau);
		

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}