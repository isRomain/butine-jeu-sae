package conception.ihm;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelChoixFleurs extends JPanel implements ActionListener, ItemListener
{
	private FramePlateau prnt;

	private JComboBox<String> listFormes, listDepart;
	private String[]  comboStringFormes   = {"vide", "carre", "rond", "triangle", "croix"};
	private String[]  comboStringDepart   = {"vide", "rouge", "vert", "bleu", "marron", "orange", "violet"};

	private JLabel   labelFleur, labelDepart;

	private JButton  btnValider;

	public PanelChoixFleurs (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new FlowLayout() );


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


		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		this.listFormes.addItemListener  (this);
		this.listDepart.addItemListener  (this);
		this.btnValider.addActionListener(this);


		/*-------------------------*/
		/* Ajout des composants    */
		/*-------------------------*/
		this.add( this.listFormes  );
		this.add( this.labelFleur  );
		this.add( this.listDepart  );
		this.add( this.labelDepart );
		this.add( this.btnValider  ); 
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
			this.listDepart. setVisible(false);
			this.labelDepart.setIcon   (null );
		}
		else
		{
			this.listDepart.setVisible(true);
			this.labelDepart.setIcon( new ImageIcon("../images/contours/contour_case_" + this.listDepart.getSelectedItem() + ".png"));
		}

		this.prnt.setFleur (this.listFormes.getSelectedItem() + "");
		this.prnt.setDepart(this.listDepart.getSelectedItem() + "");
	}

	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == this.btnValider )
		{
			this.prnt.afficherExport();
		}
	}
}