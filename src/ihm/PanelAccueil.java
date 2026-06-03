package src.ihm;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.*;
import src.Controleur;

public class PanelAccueil extends JPanel
{
      private Controleur ctrl;

      private Image imgAccueil;
      private Image imgLogo;

      public PanelAccueil( Controleur ctrl )
      {
           this.ctrl = ctrl;   

           this.imgAccueil = new ImageIcon(this.ctrl.getImageAcceuil()).getImage();
           this.imgLogo    = new ImageIcon("../images/ruche-removebg-preview.png").getImage();
      }

      public void paintComponent( Graphics g )
      {
      	super.paintComponent( g );

      	g.drawImage(imgAccueil, 0, 0, getWidth(), getHeight(), this);

      	// Titre
      	g.setFont(new Font("Arial", Font.BOLD, 36));

      	String titre = "BUTINE !";
      	int x = (getWidth() - g.getFontMetrics().stringWidth(titre)) / 2;

      	g.setColor(new Color(255,215,0)); // jaune abeille
      	g.drawString(titre, x, 80);

      	// Texte explicatif
      	g.setFont(new Font("Arial", Font.PLAIN, 18));
      	g.setColor(Color.BLACK);

      	String txt1 = "Bienvenue dans le Grand Pré.";
      	g.drawString(txt1, (getWidth()-g.getFontMetrics().stringWidth(txt1))/2, 120);

      	String txt2 = "Guidez votre colonie d'abeilles à travers les fleurs";
      	g.drawString(txt2, (getWidth()-g.getFontMetrics().stringWidth(txt2))/2, 180);

      	String txt3 = "et récoltez le plus de nectar possible.";
      	g.drawString(txt3, (getWidth()-g.getFontMetrics().stringWidth(txt3))/2, 205);

      	String txt4 = "Choisissez les dimensions de votre grille";
      	g.drawString(txt4, (getWidth()-g.getFontMetrics().stringWidth(txt4))/2, 260);

      	String txt5 = "à l'aide des paramètres ci-dessus.";
      	g.drawString(txt5, (getWidth()-g.getFontMetrics().stringWidth(txt5))/2, 285);

      	String txt6 = "Cliquez ensuite sur \"Créer\" pour commencer la partie.";
      	g.drawString(txt6, (getWidth()-g.getFontMetrics().stringWidth(txt6))/2, 340);

            int largeurLogo = 150;
            int hauteurLogo = 150;

            int xLogo = (getWidth() - largeurLogo) / 2;
            int yLogo = 380; // juste sous le texte

            g.drawImage( imgLogo, xLogo, yLogo, largeurLogo, hauteurLogo, this );
      }
}