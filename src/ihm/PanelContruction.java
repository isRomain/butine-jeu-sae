package src.ihm;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PanelContruction extends JPanel implements ItemListener, ActionListener
{
	private FramePlateau prnt;

	private JComboBox listType, listCouleur, listFormes, listPlaines;

	private Color[] couleursPlaine = {
		new Color(255, 214, 165), // capucine
		new Color(255, 179, 186), // pivoine
		new Color(255, 223, 186), // chèvrefeuille
		new Color(255, 255, 186), // primevère
		new Color(204, 236, 197), // menthe
		new Color(186, 225, 255), // myosotis
		new Color(196, 217, 255), // lavande
		new Color(215, 196, 255), // lilas
		new Color(243, 198, 240), // glycine
		new Color(255, 198, 224), // cerisier
		new Color(186, 240, 230), // sauge
		new Color(225, 243, 198), // tilleul
	};

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
		this.listPlaines.addActionListener(this);

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

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if( e.getSource() == this.listPlaines )
			{
				this.prnt.setCouleurPlaine( couleursPlaine[ this.listPlaines.getSelectedIndex() ] );
			}
	
	}
}