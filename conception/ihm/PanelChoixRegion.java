package conception.ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelChoixRegion extends JPanel implements ActionListener
{
	private FramePlateau prnt;

	private JButton btnValider;

	private String[] comboStringPlaines  = {"Capucine", "Pivoine", "Chèvrefeuille", "Primevère", "Menthe", "Myosotis", "Lavande", "Lilas", "Glycine", "Cerisier", "Sauge", "Tilleul"};

	private JComboBox<String> listPlaines;

	private Color[] couleursPlaine =
	{
		new Color(255, 214, 165, 200), // capucine
		new Color(255, 179, 186, 200), // pivoine
		new Color(255, 203, 186, 200), // chèvrefeuille
		new Color(255, 235, 206, 200), // primevère
		new Color(204, 236, 197, 200), // menthe
		new Color(186, 225, 255, 200), // myosotis
		new Color(196, 217, 255, 200), // lavande
		new Color(215, 196, 255, 200), // lilas
		new Color(223, 178, 240, 200), // glycine
		new Color(255, 248, 204, 200), // cerisier
		new Color(186, 240, 230, 200), // sauge
		new Color(225, 203, 158, 200), // tilleul
	};


	public PanelChoixRegion( FramePlateau prnt )
	{
		this.prnt = prnt;

		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		this.btnValider  = new JButton( "Valider" );
		this.listPlaines = new JComboBox<String>(comboStringPlaines);

		stylerBouton( this.btnValider, new Color(245, 180, 40) );
		stylerCombo ( this.listPlaines );


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


	private void stylerBouton( JButton btn, Color couleur )
	{
		btn.setBackground( couleur );
		btn.setForeground( Color.WHITE );
		btn.setFont( new Font("Arial", Font.BOLD, 14) );
		btn.setFocusPainted( false );
		btn.setCursor( new Cursor(Cursor.HAND_CURSOR) );
		btn.setBorder( BorderFactory.createEmptyBorder(8, 16, 8, 16) );
	}

	private void stylerCombo( JComboBox<String> combo )
	{
		combo.setFont( new Font("Arial", Font.PLAIN, 14) );
		combo.setBackground( new Color(255, 250, 235) );
		combo.setCursor( new Cursor(Cursor.HAND_CURSOR) );
		combo.setBorder( BorderFactory.createLineBorder( new Color(245, 180, 40), 2 ) );
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