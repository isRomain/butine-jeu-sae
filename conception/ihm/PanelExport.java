package conception.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class PanelExport extends JPanel implements ActionListener
{

	private FramePlateau prnt;

	private JButton btnExporter;
	private JButton btnPrecedent;

	private ImageIcon iconPrec;

	public PanelExport(FramePlateau prnt)
	{
		this.prnt = prnt;

		this.setLayout(new BorderLayout());
		this.setOpaque(false);

		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/
		this.btnExporter = new JButton("Exporter");

		// Creation et positionnement de l'image en bouton
		this.iconPrec = new ImageIcon(
			new ImageIcon("../images/icones/icon_precedent.png")
			.getImage()
			.getScaledInstance(60, 60, Image.SCALE_SMOOTH)
		);

		this.btnPrecedent = new JButton(iconPrec);
        this.btnPrecedent.setPreferredSize( new Dimension(60, 60) );
        this.btnPrecedent.setMinimumSize  ( new Dimension(60, 60) );
        this.btnPrecedent.setMaximumSize  ( new Dimension(60, 60) );
        this.btnPrecedent.setOpaque(false);

		stylerBouton(this.btnExporter, new Color(245, 180, 40));

		/*---------------------------*/
		/* Placement des composants  */
		/*---------------------------*/
		JPanel panelGauche = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelGauche.setOpaque(false);
		panelGauche.add(this.btnPrecedent);

		JPanel panelCentre = new JPanel(new FlowLayout(FlowLayout.CENTER));
		panelCentre.setOpaque(false);
		panelCentre.add(this.btnExporter);

		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelCentre, BorderLayout.CENTER);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.btnExporter.addActionListener(this);
		this.btnPrecedent.addActionListener(this);
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

		if (e.getSource() == this.btnPrecedent)
		{
			this.prnt.retourChoixFleurs();
		}
	}
}
