package src.ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelContruction extends JPanel implements ItemListener
{
	private FramePlateau prnt;

	private JComboBox listtype, listCouleur, listFormes;
	private String[]  comboStringType     = {"fleur", "zone"};
	private String[]  comboStringCouleurs = {"neutre", "rouge", "vert", "bleu", "marron"};
	private String[]  comboStringFormes   = {"carre", "rond", "triangle", "croix"};

	private JButton   bouton;

	public PanelContruction (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(1, 4) );

		this.listtype    = new JComboBox<String>(comboStringType);
		this.listCouleur = new JComboBox<String>(comboStringCouleurs);
		this.listFormes  = new JComboBox<String>(comboStringFormes);
		this.bouton      = new JButton();

		this.listtype.addItemListener(this);
		this.listCouleur.addItemListener(this);
		this.listFormes.addItemListener(this);

		this.add( listtype   );
		this.add( listCouleur);
		this.add( listFormes );

		this.add( bouton );
	}

	public void itemStateChanged(ItemEvent e)
	{
		System.out.println(this.listCouleur.getSelectedItem());
		this.bouton.setIcon( new ImageIcon("../images/pollen_" + this.listFormes.getSelectedItem() + "_" + this.listCouleur.getSelectedItem() + ".jpg") );
	}
}