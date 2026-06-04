package src.ihm;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelChoixFleurs extends JPanel implements ItemListener
{
	private FramePlateau prnt;

	private JComboBox listFormes, listDepart;
	private String[]  comboStringFormes   = {"vide", "carre", "rond", "triangle", "croix"};
	private String[]  comboStringDepart   = {"vide", "rouge", "vert", "bleu", "marron", "orange", "violet"};

	private JLabel   labelFleur, labelDepart;

	public PanelChoixFleurs (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(5, 1) );

		this.listFormes  = new JComboBox<String>(this.comboStringFormes);
		this.listDepart  = new JComboBox<String>(this.comboStringDepart);
		this.labelDepart = new JLabel();
		this.labelFleur  = new JLabel();

		this.listDepart.setVisible( false );



		this.listFormes.addItemListener(this);
		this.listDepart.addItemListener(this);


		this.add( this.listFormes );
		this.add( this.labelFleur );
		this.add( this.listDepart );
		this.add( this.labelDepart);
	}

	public void itemStateChanged(ItemEvent e)
	{
		this.listDepart.setVisible( ! this.listFormes.getSelectedItem().equals("vide") );

		this.labelFleur.setIcon ( new ImageIcon("../images/pollens/pollen_" + this.listFormes.getSelectedItem() + ".png"));
		this.labelDepart.setIcon( new ImageIcon("../images/contours/contour_case_" + this.listDepart.getSelectedItem() + ".png"));

		this.prnt.setFleur (this.listFormes.getSelectedItem() + "");
		this.prnt.setDepart(this.listDepart.getSelectedItem() + "");

		this.labelFleur.repaint();
	}
}