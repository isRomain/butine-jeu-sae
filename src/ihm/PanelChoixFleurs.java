package src.ihm;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

		this.listDepart.setVisible( false );

		this.btnValider = new JButton("Valider");

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/

		this.listFormes.addItemListener(this);
		this.listDepart.addItemListener(this);
		
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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if ( e.getSource() == this.btnValider )
		{
			this.prnt.afficherExport();
		}
	}
}