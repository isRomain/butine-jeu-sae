package src.ihm;

import java.awt.event.*;
import javax.swing.*;

public class PanelChoixFleurs extends JPanel implements ActionListener
{
     private FramePlateau prnt;

     private JButton btnValider;

     public PanelChoixFleurs( FramePlateau prnt )
     {
           this.prnt =  prnt;
           /*--------------------------*/
           /* Creatioon des composants */
           /*--------------------------*/
           this.btnValider = new JButton( "Valider" );

           /*---------------------------*/
           /* Postionner les composants */
           /*---------------------------*/
           this.add( new JLabel( "Positionner vos fleurs : " ) );

           /*---------------------------*/
           /* Activation des composants */
           /*---------------------------*/
           this.btnValider.addActionListener( this );

     }

     // passer a la prochaine page ou panel
     @Override
     public void actionPerformed( ActionEvent e )
     {
          
     }
}
