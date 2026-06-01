package src.ihm;

import src.Controleur;

import javax.swing.JFrame;

public class FramePlateau extends JFrame
{

	public FramePlateau ( Controleur ctrl )
	{
		this.setTitle   ( "Butine!" );
		this.setSize    ( 640,660   );
		this.setLocation(  50, 50   );

		this.add( new PanelPlateau(ctrl) );

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.setVisible(true);
	}
}