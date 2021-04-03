import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Color;
import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;


/**
 *  <h1>MasterKey Frame</h1>
 *  In this program we want the users to enter the master key password after
 *  he/she successfully enter the password and username after he/she created the account
 *  that was declared within the program and in order for us to provide more
 *  security we provide another password that is really hard to guess by user
 *  because it has special characters digits, letters and etc.
 * @author Roberto Rebolos Jr
 * @since 2020 -19 -11
 *
 */
public class MasterKeyFrame extends JFrame {

	
	
	
	// mainPanel
	private JPanel contentPane;
	private JPasswordField passwordField;
	private JButton btnNewButton;
	private JButton btnCancel;	
	private JLabel labelLogIn;
	private char[] firstPassword;
	
	/**
     * These Images Objects are used to getResoures Image in the source folder
     * called res and to resize the images using the getScaledInstance because it
     * renders the specified width height
     *
     * @return the image icon
     * @param of getResource() name of the desired resource
     * @param of getScaledInstance() width and height – the width to which to scale
     * the image.
     * @return a scaled version of the image
     * @exception IllegalArgumentException – if width or height is zero.
     * @exception NullPointerException-  If name is null
     */
    private Image logoOfFrame = new ImageIcon(MasterKeyFrame.class.getResource("resources/iconLockForManager.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    private Image background = new ImageIcon(MasterKeyFrame.class.getResource("resources/rightPanel.png")).getImage().getScaledInstance(360, 230, Image.SCALE_SMOOTH);
    private Image logoFrame  = new ImageIcon(MasterKeyFrame.class.getResource("resources/logo.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    private MyDocumentListenerForJPasswordField myDocumentListenerForJPasswordField;
    private MyActionListenerforOkButton myActionListenerforOkButton;
	/**
	
  /**
    * Main method or called as Driver method is the starting point for any java program and this will Launch our application
    * @param args is unused
    * @return nothing
    */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MasterKeyFrame frame = new MasterKeyFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MasterKeyFrame() {
		setUndecorated(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 320, 180);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 320, 23);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel iconTransporthub = new JLabel("");
		iconTransporthub.setHorizontalTextPosition(SwingConstants.LEFT);
		iconTransporthub.setHorizontalAlignment(SwingConstants.CENTER);
		iconTransporthub.setBounds(5, 0, 20, 20);
		iconTransporthub.setIcon(new ImageIcon(logoFrame));
		panel.add(iconTransporthub);
		
		JLabel lblNewLabel_3 = new JLabel("Transporthub");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.LEFT);
		lblNewLabel_3.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(28, 0, 80, 23);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("X");
		lblNewLabel_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Container frame = lblNewLabel_4.getParent();
	            do 
	                frame = frame.getParent(); 
	            while (!(frame instanceof JFrame));                                      
	            ((JFrame) frame).dispose();
			}
			
		});
		lblNewLabel_4.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
		lblNewLabel_4.setHorizontalTextPosition(SwingConstants.RIGHT);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setBounds(306, 3, 15, 14);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_4_1 = new JLabel("-");
		lblNewLabel_4_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				setState(ICONIFIED);
			}
		});
		lblNewLabel_4_1.setHorizontalTextPosition(SwingConstants.CENTER);
		lblNewLabel_4_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 17));
		lblNewLabel_4_1.setBounds(293, 3, 15, 14);
	
		panel.add(lblNewLabel_4_1);
		
		JLabel iconLabel = new JLabel("");
		iconLabel.setBounds(32, 68, 30, 32);
		iconLabel.setIcon(new ImageIcon(logoOfFrame));
		contentPane.add(iconLabel);
		myDocumentListenerForJPasswordField = new MyDocumentListenerForJPasswordField();
		passwordField = new JPasswordField();
	

		passwordField.setBounds(72, 68, 216, 23);
		passwordField.getDocument().addDocumentListener(myDocumentListenerForJPasswordField);
		contentPane.add(passwordField);
		
		myActionListenerforOkButton = new MyActionListenerforOkButton();
		 btnNewButton = new JButton("OK");
		 btnNewButton.setEnabled(false);
		btnNewButton.setBounds(101, 129, 79, 23);
		btnNewButton.addActionListener(myActionListenerforOkButton);
		contentPane.add(btnNewButton);
		
		 btnCancel = new JButton("Cancel");
		 btnCancel.setEnabled(false);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int userChoices = JOptionPane.showConfirmDialog(null,
						"Are you sure youw want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION);

				// giving user choices if he or she will stay on the application or exit
				if (userChoices == JOptionPane.YES_OPTION) {
				   System.exit(0);
				} else {
					MasterKeyFrame.this.setVisible(true);
				}
			}
		});
		btnCancel.setBounds(209, 129, 79, 23);
		contentPane.add(btnCancel);
		
		JLabel lblNewLabel_1 = new JLabel("Please input the password");
		lblNewLabel_1.setForeground(new Color(255, 255, 255));
		
		
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(71, 43, 151, 14);
		contentPane.add(lblNewLabel_1);
		
        labelLogIn = new JLabel("");
        labelLogIn.setForeground(new Color(255, 255, 255));
		labelLogIn.setBounds(58, 102, 230, 14);
		contentPane.add(labelLogIn);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 22, 320, 158);
		lblNewLabel.setIcon(new ImageIcon(background));
		contentPane.add(lblNewLabel);
	}
	
	
	 /*
	  * In this class we want our user restrict first the JButton cancel and OK
	  * because we personally believed that it can help user to prevent errors while
	  * using the system
	  */
	 class MyDocumentListenerForJPasswordField implements DocumentListener {
     
		

	        /**
	         * Determining when style of some of the text in the listened-to document
	         * changes
	         * @return nothing
	         * @param e - Interface for document change notifications.
	         *
	         */
		@Override
		public void insertUpdate(DocumentEvent e) {
			disabledTheButton();
			
		}
		/**
         * Determinining when text is inserted into the listened-to document.
         * @return nothing
         * @param e -This provides detailed information to Document observers about how
         *          the Document changed.
         */

		@Override
		public void removeUpdate(DocumentEvent e) {
			disabledTheButton();
			
		}
		
		/**
         * Determining when text was removed from the listened document
         *@return nothing
         * @param e -his provides detailed information to Document observers about how
         *          the Document changed
         *
         */

		@Override
		public void changedUpdate(DocumentEvent e) {
			disabledTheButton();
			
		}
	
		/**
		 * in this method we want to disabled the button if the user has not enter anything yet
		 * @return nothing
		 */
		private void disabledTheButton() {
			firstPassword = passwordField.getPassword();
			
			// setting the password to asterisk to provide more security
		    passwordField.setEchoChar('*');
			if(firstPassword.length == 0 ) {
			   
		       passwordField.setBorder(new LineBorder(new Color(255, 255, 255)));
				btnNewButton.setEnabled(false);
				btnCancel.setEnabled(false);
			}else {
				btnNewButton.setEnabled(true);
				btnCancel.setEnabled(true);
			}
		}
		
	 }
 	
	 
	 /*
	  * Listening to any events occurs if user will click the OK button and determining the password
	  * that was enter by the user if he guess it he will goes to our main frame and we declared here the
	  * password within the program called char[] masterkeyPassword
	  *
	  */
	class MyActionListenerforOkButton implements ActionListener {
	
		
		/**
		 * checking if the user really guess the password right then he could continue to the main frame
		 * and in this method we provides border in our JPasswordField because our team personally believed
		 * that having feedback on what user does in the system will have a great user experience
		 * @return nothing 
		 */
		@Override
		public void actionPerformed(ActionEvent e) {
			char[] masterKeyPassword = {'a','1','E','7','m','7','r','0','g','1','n','-','3','c','e','#','#','#'};
			
			if(e.getSource() == btnNewButton  && Arrays.equals(firstPassword,masterKeyPassword)) {
				passwordField.setBorder(new LineBorder(new Color(0, 255, 0)));
				 labelLogIn.setText("The password is you entered is correct");
				 labelLogIn.setBorder(new LineBorder(new Color(0, 255, 0)));
			
				// disposing the MasterKeyFrame
				 dispose();
				 JOptionPane.showMessageDialog(null, "You logged in successfully","Sucess",JOptionPane.INFORMATION_MESSAGE);
				 AddUpdateOrderPage window = new AddUpdateOrderPage();
	                window.frame.setVisible(true);

				
	             
	               
	                
	            
			
		   // displaying the password was not correct so taht the user could know.
			}else {
			   passwordField.setBorder(new LineBorder(new Color(255, 0, 0), 2));
				labelLogIn.setText("The password you enter is not correct");
				labelLogIn.setForeground(new Color(255, 0, 0));
				labelLogIn.setBorder(new LineBorder(new Color(255, 0, 0), 2));
				
			}
		}
		
	}
}
