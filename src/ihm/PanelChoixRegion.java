package src.ihm;

import java.awt.Color;
import java.awt.event.*;
import javax.swing.*;

public class PanelChoixRegion extends JPanel implements ActionListener
{
	private FramePlateau prnt;

	private JButton btnValider;

	private String[] comboStringPlaines  = {"vide", "Capucine", "Pivoine", "Chèvrefeuille", "Primevère", "Menthe", "Myosotis", "Lavande", "Lilas", "Glycine", "Cerisier", "Sauge", "Tilleul"};

	private JComboBox<String> listPlaines;

	private Color[] couleursPlaine =
	{
		new Color(255, 255, 255, 200), //vide
		new Color(255, 214, 165, 200), // capucine
		new Color(255, 179, 186, 200), // pivoine
		new Color(255, 223, 186, 200), // chèvrefeuille
		new Color(255, 255, 186, 200), // primevère
		new Color(204, 236, 197, 200), // menthe
		new Color(186, 225, 255, 200), // myosotis
		new Color(196, 217, 255, 200), // lavande
		new Color(215, 196, 255, 200), // lilas
		new Color(243, 198, 240, 200), // glycine
		new Color(255, 198, 224, 200), // cerisier
		new Color(186, 240, 230, 200), // sauge
		new Color(225, 243, 198, 200), // tilleul
	};


	public PanelChoixRegion( FramePlateau prnt )
	{
		this.prnt = prnt;

		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		this.btnValider  = new JButton( "Valider" ); 

		this.listPlaines = new JComboBox<String>(comboStringPlaines);

		/*----------------------------*/
		/* Positionner les composants */
		/*----------------------------*/
		this.add( new JLabel("Préciser vos régions :  ") );
		this.add( this.listPlaines );
		this.add( this.btnValider );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.btnValider .addActionListener( this );
		this.listPlaines.addActionListener( this );
	}


	public void actionPerformed( ActionEvent e )
	{
		if( e.getSource() == this.listPlaines )
		{
		    this.prnt.setCouleurPlaine( this.couleursPlaine[this.listPlaines.getSelectedIndex()] );
		}

		if( e.getSource() == this.btnValider )
		{
		   this.prnt.afficherChoixFleurs();
		}
    }
}