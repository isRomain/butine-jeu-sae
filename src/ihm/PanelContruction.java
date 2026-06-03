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

	private JComboBox listType, listCouleur, listFormes, listPlaines;

	private String[]  comboStringType     = {"fleur", "zone"};
	private String[]  comboStringCouleurs = {"neutre", "rouge", "vert", "bleu", "marron"};
	private String[]  comboStringFormes   = {"carre", "rond", "triangle", "croix"};
	private String[]  comboStringPlaines   = {"Capucine", "Pivoine", "Chèvrefeuille", "Primevère", "Menthe", "Myosotis", "Lavande", "Lilas", "Glycine", "Cerisier", "Sauge", "Tilleul"};

	private JButton   btnIcon;

	public PanelContruction (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(1, 4) );

		this.listType    = new JComboBox<String>(comboStringType);
		this.listCouleur = new JComboBox<String>(comboStringCouleurs);
		this.listFormes  = new JComboBox<String>(comboStringFormes);

		this.listPlaines = new JComboBox<String>(comboStringPlaines);

		this.btnIcon      = new JButton();

		this.listType   .addItemListener(this);
		this.listCouleur.addItemListener(this);
		this.listFormes .addItemListener(this);

		this.add( this.listType    );
		this.add( this.listCouleur );
		this.add( this.listFormes  );
		this.add( this.listPlaines );

		this.add( this.btnIcon );
	}

	public void itemStateChanged(ItemEvent e)
	{
		System.out.println(this.listCouleur.getSelectedItem());
		this.btnIcon.setIcon( new ImageIcon("../images/pollen_" + this.listFormes.getSelectedItem() + "_" + this.listCouleur.getSelectedItem() + ".jpg") );
	}
}