package conception.ihm;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.*;

import javax.swing.*;

public class PanelChoixFleurs extends JPanel implements ActionListener, ItemListener
{
	private FramePlateau prnt;

	private JComboBox<String> listFormes, listDepart;
	private String[]  comboStringFormes   = {"vide", "carre", "rond", "triangle", "croix"};
	private String[]  comboStringDepart   = {"vide", "rouge", "vert", "bleu", "marron", "orange", "violet"};

	private JLabel   labelFleur, labelDepart;

	private JButton  btnValider;
    private JButton btnPrecedent;

	private ImageIcon iconPrec;

	public PanelChoixFleurs (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout(new BorderLayout());
		this.setOpaque(false);


		/*-------------------------*/
		/* Création des composants */
		/*-------------------------*/
		this.listFormes  = new JComboBox<String>(this.comboStringFormes);
		this.listDepart  = new JComboBox<String>(this.comboStringDepart);
		this.labelDepart = new JLabel();
		this.labelFleur  = new JLabel();
		this.btnValider  = new JButton("Valider");

		this.listDepart.setVisible( false );
		stylerBouton( this.btnValider, new Color(245, 180, 40) );
		stylerCombo ( this.listFormes );
		stylerCombo ( this.listDepart );

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


		/*-------------------------*/
		/* Ajout des composants    */
		/*-------------------------*/
		JPanel panelGauche = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
		panelGauche.setOpaque(false);
		panelGauche.add(this.btnPrecedent);

		JPanel panelCentre = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
		panelCentre.setOpaque(false);
		panelCentre.add(this.listFormes);
		panelCentre.add(this.labelFleur);
		panelCentre.add(this.listDepart);
		panelCentre.add(this.labelDepart);
		panelCentre.add(this.btnValider);

		this.add(panelGauche, BorderLayout.WEST);
		this.add(panelCentre, BorderLayout.CENTER);

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.listFormes .addItemListener   ( this );
		this.listDepart .addItemListener   ( this );
		this.btnValider .addActionListener ( this );
		this.btnPrecedent.addActionListener( this );
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

	public void itemStateChanged(ItemEvent e)
	{
		this.labelFleur.setIcon ( new ImageIcon("../images/pollens/pollen_" + this.listFormes.getSelectedItem() + ".png"));
		
		if (this.listFormes.getSelectedItem().equals("vide") )
		{
			this.listDepart. setVisible(false );
			this.labelDepart.setIcon   (null  );
			this.prnt.       setDepart ("vide");
		}
		else
		{
			this.listDepart. setVisible(true);
			this.labelDepart.setIcon( new ImageIcon("../images/contours/contour_case_" + this.listDepart.getSelectedItem() + ".png"));
			this.prnt.setDepart(this.listDepart.getSelectedItem() + "");
		}

		this.prnt.setFleur (this.listFormes.getSelectedItem() + "");
	}

	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == this.btnValider )
		{
			this.prnt.afficherExport();
		}

		if( e.getSource() == this.btnPrecedent )
		{
			 this.prnt.retourChoixRegion();
		}
	}
}