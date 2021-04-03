import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JOptionPane;

/**
 * <h1>DisplayingTheFAOFrame</h1>
 * The DisplayFAOFrame is the rules and regulations of the system
 * we used JLabel to display text so that the user can read it
 * 
 * @author Roberto A Rebolos & Romel Alcantara & John Tex Lear Ortaliz Rara
 * @since 2020 -19 -11
 *
 */
public class MyMouseListenerForFAQ implements MouseListener {

	/**
	 * Handling the event may occur when the JLabel was click
	 * @return nothing
	 * @param - An event which indicates that a mouse action occurred in a component.
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
         FAQFrame FaqFrame = new FAQFrame();
          FaqFrame.setVisible(true);
           FaqFrame.setTitle("FAQ");
           FaqFrame.setResizable(false);
           FaqFrame.setLocationRelativeTo(null);
           
           FaqFrame.setDefaultCloseOperation(FaqFrame.DISPOSE_ON_CLOSE);
        
          
	}
	/**
	 * @return nothing
	 * @param -An event which indicates that a mouse action occurred in a component.
	 */

	@Override
	public void mousePressed(MouseEvent e) {		
    // TODO Auto-generated method stub
		
	}
	/**
	 * @return nothing
	 * @param - An event which indicates that a mouse action occurred in a component.
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub		
	}

	/**
	 * @return nothing
	 * @param - An event which indicates that a mouse action occurred in a component.
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub		
	}
	/**
	 * @return nothing
	 * @param - An event which indicates that a mouse action occurred in a component.
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}