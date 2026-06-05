package src.ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelExport extends JPanel implements ActionListener
{

	private FramePlateau prnt;
	private JButton btnExporter;

	public PanelExport(FramePlateau prnt) 
	{
		this.prnt = prnt;


		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		this.btnExporter = new JButton("Exporter");

		stylerBouton( this.btnExporter, new Color(245, 180, 40) );


		/*---------------------------*/
		/*  Placement des composants */
		/*---------------------------*/
		this.add( this.btnExporter );


		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.btnExporter.addActionListener( this );
		
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

	public void actionPerformed(ActionEvent e)
	{
		if ( e.getSource() == this.btnExporter )
		{
			this.prnt.ExporterGrille();
			JOptionPane.showMessageDialog(this, "La grille a bien été exportée !");
		}
	}
}
