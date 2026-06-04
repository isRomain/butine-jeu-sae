package src.ihm;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

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

		/*---------------------------*/
		/*  Placement des composants */
		/*---------------------------*/
		
		this.add( this.btnExporter );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

		this.btnExporter.addActionListener( this );
		
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == this.btnExporter )
		{
			this.prnt.ExporterGrille();
		}
	}
}
