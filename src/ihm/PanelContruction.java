package src.ihm;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

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
	private String[]  comboStringPlaines  = {"Capucine", "Pivoine", "Chèvrefeuille", "Primevère", "Menthe", "Myosotis", "Lavande", "Lilas", "Glycine", "Cerisier", "Sauge", "Tilleul"};

	private JLabel   lblIcon;

	public PanelContruction (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(5, 1) );

		this.listType    = new JComboBox<String>(comboStringType);
		this.listCouleur = new JComboBox<String>(comboStringCouleurs);
		this.listFormes  = new JComboBox<String>(comboStringFormes);

		this.listPlaines = new JComboBox<String>(comboStringPlaines);

		this.lblIcon     = new JLabel( new ImageIcon(new ImageIcon("../images/pollens/pollen_carre_neutre.png").getImage()
		.getScaledInstance(128,128,Image.SCALE_SMOOTH)));

		this.listType   .addItemListener(this);
		this.listCouleur.addItemListener(this);
		this.listFormes .addItemListener(this);
		this.listPlaines.addActionListener(this);
		this.listFormes.addItemListener(this);

		this.add( this.listType    );
		this.add( this.listCouleur );
		this.add( this.listFormes  );
		this.add( this.listPlaines );
		this.add( this.lblIcon );

	}

	public void itemStateChanged(ItemEvent e)
	{
		System.out.println(this.listCouleur.getSelectedItem());
		this.lblIcon.setIcon( new ImageIcon(new ImageIcon("../images/pollens/pollen_" + 
		this.listFormes.getSelectedItem() + "_" + this.listCouleur.getSelectedItem() + ".png")
		.getImage().getScaledInstance(128,128,Image.SCALE_SMOOTH)));

		this.lblIcon.repaint();

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if( e.getSource() == this.listPlaines )
		{
			this.prnt.setCouleurPlaine( couleursPlaine[ this.listPlaines.getSelectedIndex() ] );
		}
	}
}