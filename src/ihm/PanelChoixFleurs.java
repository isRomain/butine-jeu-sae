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

	private JComboBox listFormes;
	private String[]  comboStringFormes   = {"carre", "rond", "triangle", "croix"};

	private JLabel   lblIcon;

	public PanelChoixFleurs (FramePlateau prnt)
	{
		this.prnt = prnt;
		this.setLayout( new GridLayout(5, 1) );

		this.listFormes  = new JComboBox<String>(comboStringFormes);
		this.lblIcon     = new JLabel(new ImageIcon("../images/pollens/pollen_carre.png"));

		this.listFormes .addItemListener(this);

		this.add( this.listFormes  );
		this.add( this.lblIcon );

		this.prnt.setFleur("carre");
	}

	public void itemStateChanged(ItemEvent e)
	{
		this.lblIcon.setIcon( new ImageIcon("../images/pollens/pollen_" + this.listFormes.getSelectedItem() + ".png"));

		this.prnt.setFleur(this.listFormes.getSelectedItem() + "");

		this.lblIcon.repaint();
	}
}