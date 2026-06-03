package src.ihm;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PanelContruction extends JPanel implements ItemListener
{
	private FramePlateau prnt;

	private JComboBox listType, listCouleur, listFormes;

	private String[]  comboStringType     = {"fleur", "zone"};
	private String[]  comboStringCouleurs = {"neutre", "rouge", "vert", "bleu", "marron"};
	private String[]  comboStringFormes   = {"carre", "rond", "triangle", "croix"};

	private JLabel   lblIcon;

	public PanelContruction (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(5, 1) );

		this.listType    = new JComboBox<String>(comboStringType);
		this.listCouleur = new JComboBox<String>(comboStringCouleurs);
		this.listFormes  = new JComboBox<String>(comboStringFormes);


		this.lblIcon     = new JLabel( new ImageIcon(new ImageIcon("../images/pollens/pollen_carre_neutre.png").getImage()
		.getScaledInstance(128,128,Image.SCALE_SMOOTH)));

		this.listType   .addItemListener(this);
		this.listCouleur.addItemListener(this);
		this.listFormes .addItemListener(this);

		this.add( this.listType    );
		this.add( this.listCouleur );
		this.add( this.listFormes  );
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
}