import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JList;
import java.awt.Color;


/**
 * <h1>The Valid Input ForThe SignUp!</h1>
 * The HelpLabelFrame is implemented because we want our users
 *  to know what only the valid input so that we used aJ JLabel
 *  and put all the valid input for the signUp and also we extend
 *  the JFrame to have a container of all our JLabel
 * @author Roberto A Rebolos & Romel Alcantara & John Tex Lear Ortaliz Rara
 * @since 2020 -19 -11
 */
public class HelpLabelFrame extends JFrame {

	
	// mainPanel
	private JPanel contentPane;
	/**
     * These Images Objects are used to getResoures Image in the source folder
     * called res and to resize the images using the getScaledInstance because it
     * renders the specified width height
     *
     * @return the image icon
     * @param of getResource() name of the desired resource
     * @param of getScaledInstance() width and height – the width to which to scale
     *           the image.
     * @return a scaled version of the image
     * @exception IllegalArgumentException – if width or height is zero.
     *
     */
	private Image rightBackground = new ImageIcon(SignUpForm.class.getResource("resources/rightPanel.png")).getImage()
			.getScaledInstance(500, 500, Image.SCALE_SMOOTH);
	
	private Image leftLogo = new ImageIcon(SignUpForm.class.getResource("resources/Logo.png")).getImage()
			.getScaledInstance(90, 90, Image.SCALE_SMOOTH);
	
	/**
     * Driver method are is starting point for any java program and this will Launch the application
     * @param arg Unused
     * @return nothing
     * 
     */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HelpLabelFrame  frame = new HelpLabelFrame ();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	// Initializing our objects
	public HelpLabelFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel_2 = new JLabel("Firstname:");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_2.setBounds(77, 105, 69, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("John");
		lblNewLabel_3.setForeground(Color.WHITE);
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_3.setBounds(156, 106, 58, 12);
		contentPane.add(lblNewLabel_3);
		
		JLabel lastname = new JLabel("Lastname");
		lastname.setForeground(Color.WHITE);
		lastname.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lastname.setBounds(79, 130, 67, 14);
		contentPane.add(lastname);
		
		JLabel lastnameExample = new JLabel("Redoble");
		lastnameExample.setForeground(Color.WHITE);
		lastnameExample.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lastnameExample.setBounds(156, 129, 76, 12);
		contentPane.add(lastnameExample);
		
		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setForeground(Color.WHITE);
		usernameLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		usernameLabel.setBounds(71, 177, 75, 14);
		contentPane.add(usernameLabel);
		
		JLabel lblNewLabel_6 = new JLabel("Example");
		lblNewLabel_6.setForeground(Color.WHITE);
		lblNewLabel_6.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_6.setBounds(157, 81, 75, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("Password:");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_7.setBounds(77, 202, 69, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel usernameExample = new JLabel("Rebolos143#");
		usernameExample.setForeground(Color.WHITE);
		usernameExample.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		usernameExample.setBounds(156, 177, 85, 14);
		contentPane.add(usernameExample);
		
		JLabel confirmPasswordLabel = new JLabel("Confirm Password:");
		confirmPasswordLabel.setForeground(Color.WHITE);
		confirmPasswordLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		confirmPasswordLabel.setBounds(22, 227, 124, 16);
		contentPane.add(confirmPasswordLabel);
		
		JLabel firstPasswordExample = new JLabel("Natalie143#");
		firstPasswordExample.setForeground(Color.WHITE);
		firstPasswordExample.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		firstPasswordExample.setBounds(157, 203, 85, 13);
		contentPane.add(firstPasswordExample);
		
		JLabel confirmPasswordExample = new JLabel("Must be the same with the first password");
		confirmPasswordExample.setForeground(Color.WHITE);
		confirmPasswordExample.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		confirmPasswordExample.setBounds(156, 228, 268, 14);
		contentPane.add(confirmPasswordExample);
		
		JLabel masterKeyIDLabel = new JLabel("Master KeyID:");
		masterKeyIDLabel.setForeground(Color.WHITE);
		masterKeyIDLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		masterKeyIDLabel.setBounds(51, 254, 95, 17);
		contentPane.add(masterKeyIDLabel);
		
		JLabel mustOnlyContainInteger = new JLabel("Must only contain integer like [1-9]");
		mustOnlyContainInteger.setForeground(Color.WHITE);
		mustOnlyContainInteger.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		mustOnlyContainInteger.setBounds(156, 251, 223, 20);
		contentPane.add(mustOnlyContainInteger);
		
		JLabel ageLabel = new JLabel("Age:");
		ageLabel.setForeground(Color.WHITE);
		ageLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		ageLabel.setBounds(92, 275, 35, 20);
		contentPane.add(ageLabel);
		
		JLabel ageExample = new JLabel("18 above & less than 61");
		ageExample.setForeground(Color.WHITE);
		ageExample.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		ageExample.setBounds(156, 278, 153, 14);
		contentPane.add(ageExample);
		
		JLabel dayLabel = new JLabel("Day:");
		dayLabel.setForeground(Color.WHITE);
		dayLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		dayLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dayLabel.setBounds(92, 303, 35, 18);
		contentPane.add(dayLabel);
		
		JLabel dayExample = new JLabel("Must not greater 31");
		dayExample.setForeground(Color.WHITE);
		dayExample.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		dayExample.setBounds(156, 304, 128, 17);
		contentPane.add(dayExample);
		
		JLabel yearLabel = new JLabel("Year:");
		yearLabel.setForeground(Color.WHITE);
		yearLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
		yearLabel.setBounds(92, 357, 35, 14);
		contentPane.add(yearLabel);
		
		JLabel yearExample = new JLabel("Must not less than 1650 or \r\n atleast 2002\r\n\r\n");
		yearExample.setForeground(Color.WHITE);
		yearExample.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		yearExample.setBounds(156, 357, 279, 14);
		contentPane.add(yearExample);
		
		JLabel lblNewLabel_18 = new JLabel("Middle Name:");
		lblNewLabel_18.setForeground(Color.WHITE);
		lblNewLabel_18.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_18.setBounds(56, 152, 100, 14);
		contentPane.add(lblNewLabel_18);
		
		JLabel lblNewLabel_19 = new JLabel("Must not contain integer");
		lblNewLabel_19.setForeground(Color.WHITE);
		lblNewLabel_19.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_19.setBounds(156, 152, 159, 14);
		contentPane.add(lblNewLabel_19);
		
		JLabel logoDesignLabel = new JLabel("");
		logoDesignLabel.setVerticalAlignment(SwingConstants.TOP);
		logoDesignLabel.setHorizontalAlignment(SwingConstants.CENTER);
		logoDesignLabel.setHorizontalTextPosition(SwingConstants.CENTER);
		 logoDesignLabel.setIcon(new ImageIcon(leftLogo));
		logoDesignLabel.setBounds(317, 22, 107, 97);
		contentPane.add(logoDesignLabel);
		
		JLabel monthLabel = new JLabel("Month:");
		monthLabel.setForeground(Color.WHITE);
		monthLabel.setHorizontalAlignment(SwingConstants.CENTER);
		monthLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		monthLabel.setBounds(87, 332, 44, 14);
		contentPane.add(monthLabel);
		
		JLabel monthExample = new JLabel("Must oly number from 1 -12 ");
		monthExample.setForeground(Color.WHITE);
		monthExample.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		monthExample.setBounds(156, 332, 176, 14);
		contentPane.add(monthExample);
		
		JLabel background = new JLabel("");
		background.setHorizontalAlignment(SwingConstants.CENTER);
		background.setHorizontalTextPosition(SwingConstants.LEFT);
		background.setBounds(0, 0, 435, 411);
		background.setIcon(new ImageIcon(rightBackground));
		contentPane.add(background);
	}
}