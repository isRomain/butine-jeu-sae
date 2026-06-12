package jeu.ihm;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import jeu.Controleur;
import jeu.metier.Pile;

public class PanelCarte extends JPanel implements ActionListener
{
	private Controleur ctrl;

	private Image imgAccueil;

	private JLabel labelTour;
	private JLabel labelPoint;
	private JLabel labelCarteTiree;
	private JLabel lblManche;

	private JButton btnPioche;

	private Pile pile;

	private int nbManches;
	private int mancheActuelle;
	private int cartesFoncees;

	public PanelCarte (Controleur ctrl, int nbManches)
	{
		JPanel panelTour;
		JPanel panelPoint;
		JPanel panelCentre;
		this.ctrl = ctrl;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(50, 250));

		/*-------------------------*/
		/* Creation des composants */
		/*-------------------------*/

		this.imgAccueil = new ImageIcon("../images/icones/bandeau_haut.png").getImage();

		this.pile = new Pile();

		this.nbManches      = nbManches;
		this.mancheActuelle = 1;
		this.cartesFoncees  = 0;

		panelTour    = new JPanel();
		panelPoint   = new JPanel();
		panelCentre  = new JPanel( new FlowLayout(FlowLayout.CENTER, 20, 10) );
		panelCentre.setOpaque(false);

		this.labelTour    = new JLabel("Manche: 1/" + nbManches );
		this.labelPoint   = new JLabel("Points: 0");
		this.lblManche    = new JLabel("", JLabel.CENTER);
        this.lblManche.setFont(this.lblManche.getFont().deriveFont(24f));

		this.btnPioche = new JButton( chargerIcone("../images/cartes/carte_dos.png") );
		this.btnPioche.setPreferredSize( new Dimension(160, 200) );
		this.btnPioche.setContentAreaFilled(false);
		this.btnPioche.setBorderPainted(false);
		this.btnPioche.setFocusPainted(false);
		this.btnPioche.setCursor( new Cursor(Cursor.HAND_CURSOR) );

		this.labelCarteTiree = new JLabel();
		this.labelCarteTiree.setPreferredSize( new Dimension(160, 200) );

		panelCentre.add( this.btnPioche       );
		panelCentre.add( this.labelCarteTiree );

		/*----------------------*/
		/* Ajout des composants */
		/*----------------------*/
		this.add( panelTour  , BorderLayout.WEST    );
		this.add( panelPoint , BorderLayout.EAST    );
		this.add( panelCentre, BorderLayout.CENTER  );

		this.add(this.lblManche, BorderLayout.SOUTH );

		/*---------------------------*/
		/* Activation des composants */
		/*---------------------------*/
		panelTour .add( this.labelTour  );
		panelPoint.add( this.labelPoint );

		this.btnPioche.addActionListener( this );

		this.btnPioche.addMouseListener(new MouseAdapter()
		{
			public void mouseEntered(MouseEvent e)
			{
				btnPioche.setPreferredSize(new Dimension(175, 215));
				btnPioche.revalidate();
			}
		
			public void mouseExited(MouseEvent e)
			{
				btnPioche.setPreferredSize(new Dimension(160, 200));
				btnPioche.revalidate();
			}
		});

		this.repaint();
	}

	public int getMancheActuelle()
	{
		return this.mancheActuelle;
	}

	private ImageIcon chargerIcone(String chemin)
	{
		return new ImageIcon(new ImageIcon(chemin).getImage().getScaledInstance(160, 200, Image.SCALE_SMOOTH));
	}

	public void piocher()
	{
		String carte = this.pile.piocher();

		if (carte == null)
			return;

		if (carte.contains("fonce"))
		{
			this.cartesFoncees++;

			if (this.cartesFoncees == 5)
			{
				finManche();
				return;
			}
		}

		animerCarte("../images/cartes/" + carte);
		this.ctrl.setFormeCarte(carte.split("_")[2].replace(".png", ""));
	}

	public void piocherManuellement()
	{
		piocher();
	}

	private void animerCarte(String chemin)
	{
		final int[] taille = {80};

		Timer timer = new Timer(10, null);

		timer.addActionListener(e ->
		{
			this.labelCarteTiree.setIcon(
				new ImageIcon(
					new ImageIcon(chemin)
					.getImage()
					.getScaledInstance(taille[0], taille[0] + 40, Image.SCALE_SMOOTH)
				)
			);

			taille[0] += 8;

			if (taille[0] >= 160)
			{
				this.labelCarteTiree.setIcon(chargerIcone(chemin));
				timer.stop();
			}
		});

		timer.start();
	}

	private void finManche()
	{
		if (this.mancheActuelle >= this.nbManches)
		{
			this.ctrl.setFormeCarte("vide");
			this.btnPioche.setEnabled(false);
			this.lblManche.setText("Partie terminée");
			return;
		}

		this.mancheActuelle++;
		this.cartesFoncees = 0;
		this.pile          = new Pile();

		this.ctrl.setFormeCarte("vide");
		this.labelCarteTiree.setIcon(null);
		this.btnPioche.setEnabled(false);

		this.labelTour.setText("Manche: " + this.mancheActuelle + "/" + this.nbManches);
		this.lblManche.setText("Début de la manche : " + this.mancheActuelle + ",    Choisissez un autre depart !");

		Timer timer = new Timer(2000, e ->
		{
			this.lblManche.setText("");
			this.btnPioche.setEnabled(true);
		});
		
		timer.setRepeats(false);
		timer.start();
	}

	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == this.btnPioche)
		{
			piocherManuellement();
		}
	}
	
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		g.drawImage(imgAccueil, 0, 0, getWidth(), getHeight(), this);
	}
}
