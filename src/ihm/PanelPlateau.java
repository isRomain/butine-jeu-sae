package src.ihm;

import src.Controleur;

import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelPlateau extends JPanel
{

	private Controleur ctrl;

	private JLabel[][] cells;

	public PanelPlateau (Controleur ctrl)
	{
		this.ctrl = ctrl;

		this.setLayout( new GridLayout(7, 7) );

		this.cells = new JLabel[7][7];
		
		for (int x = 0; x < 7; x++)
		{
			for (int y = 0; y < 7; y++)
			{
				this.cells[x][y] = new JLabel( new ImageIcon( "./src/images/Flower.png" ) );
				this.add( this.cells[x][y] );
			}
		}
	}
}