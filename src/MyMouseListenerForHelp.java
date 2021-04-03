import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.WindowConstants;

/**
 * <h1>The Listener for Help JLabel!</h1>
 * The Help mouse listener is used here to let user
 *  view if they dont know what is the valid input for creating an account
 *  in our system and we want them to have a good user experience because 
 *  our team believed that in order we could have a good user experience
 *  we should not let our users to adjust with the system and the system should meet
 *  the requirements of the targets user.
 *  
 * @author Roberto A Rebolos & Romel Alcantara & John Tex Lear Ortaliz Rara
 * @since 2020 -19 -11
 *
 */
public class MyMouseListenerForHelp implements MouseListener {

	

	@Override
	public void mouseClicked(MouseEvent e) {
	   HelpLabelFrame frame = new HelpLabelFrame();
	   frame.setVisible(true);
	   frame.setResizable(false);
	   frame.setTitle("Example For Sign Up");
	   frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
	   frame.setLocationRelativeTo(null);
	   
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}