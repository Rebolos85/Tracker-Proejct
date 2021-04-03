
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Frame;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextPane;
import javax.swing.JWindow;
import javax.swing.JScrollBar;				////if there is free time resolve issues here
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.awt.SystemColor;
import javax.swing.border.BevelBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.JEditorPane;
import javax.swing.JTextArea;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * This class contains code for the Track Order Page
 * 
 * @author Roberto A Rebolos Jr, Romel Alcantara and John Tex Lear Ortaliz Rara
 * @since 2020 -19 -11
 */

public class TrackPage {

    final String url = "jdbc:mysql:///transporthubfinalproject?useSSL=false";					//replace url from testdb to actual database
    final String user = "root";															
    final String password = "Rebolos143#";
    
	JFrame frame;
	private JTextField textField;
	private JTextPane itemTextField;
	private JScrollPane scroll;
	
	private JLabel stala1;
	private JLabel stala2;
	private JLabel stala3;
	private JLabel stala4;
	private JLabel stala5;
	private JLabel stala6;
	private JLabel stala7;
	private JLabel stala8;
	private JLabel stala9;
	private JLabel stala10;
	
	private JLabel dala1;
	private JLabel dala2;
	private JLabel dala3;
	private JLabel dala4;
	private JLabel dala5;
	private JLabel dala6;
	private JLabel dala7;
	private JLabel dala8;
	private JLabel dala9;
	private JLabel dala10;
	
	private JLabel lola1;
	private JLabel lola2;
	private JLabel lola3;
	private JLabel lola4;
	private JLabel lola5;
	private JLabel lola6;
	private JLabel lola7;
	private JLabel lola8;
	private JLabel lola9;
	private JLabel lola10;
	
	private static int orderIdNum;
	
	
	
	/**
	 * Launches the application.
	 * @param args Inputs of the user
	 */
	public static void main(String[] args) {
		
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TrackPage window = new TrackPage();
					window.frame.setVisible(true);
					 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}


	
	/**
	 * Create the application.
	 */
	public TrackPage() {
		initialize();
	}
//////////////////////////////////////////////////////////////////////////////// Program Frame Initialization code begins here
	/**
	 * Initializes frame content
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1000, 760);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setUndecorated(true);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		
		JPanel header = new JPanel();
		header.setBackground(Color.WHITE);
		header.setBounds(0, 0, 1000, 30);
		frame.getContentPane().add(header);
		header.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("X");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Container frame = lblNewLabel.getParent();
	            do 
	                frame = frame.getParent(); 
	            while (!(frame instanceof JFrame));                                      
	            ((JFrame) frame).dispose();
			}
		});
		lblNewLabel.setBounds(987, 10, 13, 13);
		header.add(lblNewLabel);
		
		JLabel lblNewLabel_5 = new JLabel("-");
		lblNewLabel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				frame.setState(Frame.ICONIFIED);
			}
		});
		lblNewLabel_5.setFont(new Font("Tahoma", Font.BOLD, 18));
		lblNewLabel_5.setBounds(970, 10, 22, 13);
		header.add(lblNewLabel_5);
		
		
		
		JPanel body = new JPanel();
		body.setBounds(1, 30, 1000, 730);
		frame.getContentPane().add(body);
		body.setBackground(new Color(51, 37, 78));
		body.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Enter Order Number Here");
		lblNewLabel_1.setForeground(new Color(240, 255, 240));
		lblNewLabel_1.setBounds(403, 60, 202, 13);
		body.add(lblNewLabel_1);
		lblNewLabel_1.setFont(new Font("Berlin Sans FB", Font.BOLD, 16));
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
		
		});
		textField.setBounds(289, 83, 418, 25);
		body.add(textField);
		textField.setColumns(10);
																						/////////////////////////Track order button code begins here
		JButton btnNewButton = new JButton("Track Order");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e){
				
				try {
					
				TrackPage yeet = new TrackPage();
				orderIdNum = Integer.parseInt(textField.getText());
				ArrayList<String> yeetstat = yeet.retrievestatusstat(orderIdNum);
				ArrayList<String> yeetdate = yeet.retrievestatusdate(orderIdNum);
				ArrayList<String> yeetlocation = yeet.retrievestatuslocation(orderIdNum);
				
//				for (int m = 0; m < 10; m++) {
//			       	System.out.print(yeetstat.get(m)+", ");
//			       	System.out.print(yeetdate.get(m)+", ");
//			       	System.out.println(yeetlocation.get(m));
//				   }
//				   System.out.println("");
				   
				stala1.setText(yeetstat.get(0));
				stala2.setText(yeetstat.get(1));
				stala3.setText(yeetstat.get(2));
				stala4.setText(yeetstat.get(3));
				stala5.setText(yeetstat.get(4));
				stala6.setText(yeetstat.get(5));
				stala7.setText(yeetstat.get(6));
				stala8.setText(yeetstat.get(7));
				stala9.setText(yeetstat.get(8));
				stala10.setText(yeetstat.get(9));
				
				dala1.setText(yeetdate.get(0));
				dala2.setText(yeetdate.get(1));
				dala3.setText(yeetdate.get(2));
				dala4.setText(yeetdate.get(3));
				dala5.setText(yeetdate.get(4));
				dala6.setText(yeetdate.get(5));
				dala7.setText(yeetdate.get(6));
				dala8.setText(yeetdate.get(7));
				dala9.setText(yeetdate.get(8));
				dala10.setText(yeetdate.get(9));
				
				lola1.setText(yeetlocation.get(0));
				lola2.setText(yeetlocation.get(1));
				lola3.setText(yeetlocation.get(2));
				lola4.setText(yeetlocation.get(3));
				lola5.setText(yeetlocation.get(4));
				lola6.setText(yeetlocation.get(5));
				lola7.setText(yeetlocation.get(6));
				lola8.setText(yeetlocation.get(7));
				lola9.setText(yeetlocation.get(8));
				lola10.setText(yeetlocation.get(9));
				
				
				
				String yeetS = yeet.retrieveitem(orderIdNum);				////reinstate this code block when you're done fixing retrieve status
				itemTextField.setText(yeetS);
				System.out.println("String yeetS from retrieve items");
				System.out.println(yeetS);
				
				System.out.println("Item Order ID # is: " +orderIdNum);
				} catch(Exception ex)	{
				textField.setText("Incorrect Input");
				}
				}
			}
		);																				/////////////////////////Track order button code ends here
		btnNewButton.setBounds(441, 119, 122, 21);
		body.add(btnNewButton);
		
		JLabel lblNewLabel_2 = new JLabel("Order Item/s");
		lblNewLabel_2.setFont(new Font("Berlin Sans FB", Font.BOLD, 16));
		lblNewLabel_2.setForeground(new Color(240, 255, 240));
		lblNewLabel_2.setBounds(183, 161, 96, 13);
		body.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(289, 161, 418, 58);
		body.add(scrollPane);
		
		itemTextField = new JTextPane();
		itemTextField.setEditable(false);
		scrollPane.setViewportView(itemTextField);
		itemTextField.setText(" ");
		
		JLabel lblNewLabel_3 = new JLabel("Order Status");
		lblNewLabel_3.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
		lblNewLabel_3.setForeground(new Color(253, 245, 230));
		lblNewLabel_3.setBounds(463, 229, 85, 31);
		body.add(lblNewLabel_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(164, 267, 710, 440);
		body.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel panel_location = new JPanel();
		panel_location.setBounds(0, 0, 141, 40);
		panel_location.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_location.setBackground(SystemColor.control);
		panel_2.add(panel_location);
		
		JLabel lblNewLabel_4 = new JLabel("Location");
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_location.add(lblNewLabel_4);
				
		JPanel panel_location_1_1 = new JPanel();
		panel_location_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_1.setBackground(SystemColor.menu);
		panel_location_1_1.setBounds(0, 40, 141, 40);
		panel_2.add(panel_location_1_1);
		
		lola1 = new JLabel(" ");
		lola1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_1.add(lola1);
		
		JPanel panel_location_1_2 = new JPanel();
		panel_location_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_2.setBackground(SystemColor.menu);
		panel_location_1_2.setBounds(0, 80, 141, 40);
		panel_2.add(panel_location_1_2);
		
		lola2 = new JLabel(" ");
		lola2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_2.add(lola2);
		
		JPanel panel_location_1_3 = new JPanel();
		panel_location_1_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_3.setBackground(SystemColor.menu);
		panel_location_1_3.setBounds(0, 120, 141, 40);
		panel_2.add(panel_location_1_3);
		
		lola3 = new JLabel(" ");
		lola3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_3.add(lola3);
		
		JPanel panel_location_1_4 = new JPanel();
		panel_location_1_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_4.setBackground(SystemColor.menu);
		panel_location_1_4.setBounds(0, 160, 141, 40);
		panel_2.add(panel_location_1_4);
		
		lola4 = new JLabel(" ");
		lola4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_4.add(lola4);
		
		JPanel panel_location_1_5 = new JPanel();
		panel_location_1_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_5.setBackground(SystemColor.menu);
		panel_location_1_5.setBounds(0, 200, 141, 40);
		panel_2.add(panel_location_1_5);
		
		lola5 = new JLabel(" ");
		lola5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_5.add(lola5);
		
		JPanel panel_location_1_6 = new JPanel();
		panel_location_1_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_6.setBackground(SystemColor.menu);
		panel_location_1_6.setBounds(0, 240, 141, 40);
		panel_2.add(panel_location_1_6);
		
		lola6 = new JLabel(" ");
		lola6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_6.add(lola6);
		
		JPanel panel_location_1_7 = new JPanel();
		panel_location_1_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_7.setBackground(SystemColor.menu);
		panel_location_1_7.setBounds(0, 280, 141, 40);
		panel_2.add(panel_location_1_7);
		
		lola7 = new JLabel(" ");
		lola7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_7.add(lola7);
		
		JPanel panel_location_1_8 = new JPanel();
		panel_location_1_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_8.setBackground(SystemColor.menu);
		panel_location_1_8.setBounds(0, 320, 141, 40);
		panel_2.add(panel_location_1_8);
		
		lola8 = new JLabel(" ");
		lola8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_8.add(lola8);
		
		JPanel panel_location_1_9 = new JPanel();
		panel_location_1_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_9.setBackground(SystemColor.menu);
		panel_location_1_9.setBounds(0, 360, 141, 40);
		panel_2.add(panel_location_1_9);
		
		lola9 = new JLabel(" ");
		lola9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_9.add(lola9);
		
		JPanel panel_location_1_10 = new JPanel();
		panel_location_1_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_location_1_10.setBackground(SystemColor.menu);
		panel_location_1_10.setBounds(0, 400, 141, 40);
		panel_2.add(panel_location_1_10);
		
		lola10 = new JLabel(" ");
		lola10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_location_1_10.add(lola10);
		
		JPanel panel_date = new JPanel();
		panel_date.setBounds(140, 0, 102, 40);
		panel_date.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_date.setBackground(SystemColor.menu);
		panel_2.add(panel_date);
		
		JLabel lblNewLabel_4_1 = new JLabel("Date");
		lblNewLabel_4_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_date.add(lblNewLabel_4_1);		
		
		JPanel panel_date_1_1 = new JPanel();
		panel_date_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_1.setBackground(SystemColor.menu);
		panel_date_1_1.setBounds(140, 40, 102, 40);
		panel_2.add(panel_date_1_1);
		
		dala1 = new JLabel(" ");
		dala1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_1.add(dala1);
		
		JPanel panel_date_1_2 = new JPanel();
		panel_date_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_2.setBackground(SystemColor.menu);
		panel_date_1_2.setBounds(140, 80, 102, 40);
		panel_2.add(panel_date_1_2);
		
		dala2 = new JLabel(" ");
		dala2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_2.add(dala2);
		
		JPanel panel_date_1_3 = new JPanel();
		panel_date_1_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_3.setBackground(SystemColor.menu);
		panel_date_1_3.setBounds(140, 120, 102, 40);
		panel_2.add(panel_date_1_3);
		
		dala3 = new JLabel(" ");
		dala3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_3.add(dala3);
		
		JPanel panel_date_1_4 = new JPanel();
		panel_date_1_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_4.setBackground(SystemColor.menu);
		panel_date_1_4.setBounds(140, 160, 102, 40);
		panel_2.add(panel_date_1_4);
		
		dala4 = new JLabel(" ");
		dala4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_4.add(dala4);
		
		JPanel panel_date_1_5 = new JPanel();
		panel_date_1_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_5.setBackground(SystemColor.menu);
		panel_date_1_5.setBounds(140, 200, 102, 40);
		panel_2.add(panel_date_1_5);
		
		dala5 = new JLabel(" ");
		dala5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_5.add(dala5);
		
		JPanel panel_date_1_6 = new JPanel();
		panel_date_1_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_6.setBackground(SystemColor.menu);
		panel_date_1_6.setBounds(140, 240, 102, 40);
		panel_2.add(panel_date_1_6);
		
		dala6 = new JLabel(" ");
		dala6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_6.add(dala6);
		
		JPanel panel_date_1_7 = new JPanel();
		panel_date_1_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_7.setBackground(SystemColor.menu);
		panel_date_1_7.setBounds(140, 280, 102, 40);
		panel_2.add(panel_date_1_7);
		
		dala7 = new JLabel(" ");
		dala7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_7.add(dala7);
		
		JPanel panel_date_1_8 = new JPanel();
		panel_date_1_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_8.setBackground(SystemColor.menu);
		panel_date_1_8.setBounds(140, 320, 102, 40);
		panel_2.add(panel_date_1_8);
		
		dala8 = new JLabel(" ");
		dala8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_8.add(dala8);
		
		JPanel panel_date_1_9 = new JPanel();
		panel_date_1_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_9.setBackground(SystemColor.menu);
		panel_date_1_9.setBounds(140, 360, 102, 40);
		panel_2.add(panel_date_1_9);
		
		dala9 = new JLabel(" ");
		dala9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_9.add(dala9);
		
		JPanel panel_date_1_10 = new JPanel();
		panel_date_1_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_date_1_10.setBackground(SystemColor.menu);
		panel_date_1_10.setBounds(140, 400, 102, 40);
		panel_2.add(panel_date_1_10);
		
		dala10 = new JLabel(" ");
		dala10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_date_1_10.add(dala10);
			
		JPanel panel_status = new JPanel();
		panel_status.setBounds(241, 0, 469, 40);
		panel_status.setBorder(new LineBorder(new Color(0, 0, 0), 2));
		panel_status.setBackground(SystemColor.menu);
		panel_2.add(panel_status);
		
		JLabel lblNewLabel_4_2 = new JLabel("Status");
		lblNewLabel_4_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		panel_status.add(lblNewLabel_4_2);
		
		JPanel panel_status_1_1 = new JPanel();
		panel_status_1_1.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_1.setBackground(SystemColor.menu);
		panel_status_1_1.setBounds(241, 40, 469, 40);
		panel_2.add(panel_status_1_1);
		
		stala1 = new JLabel(" ");
		stala1.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_1.add(stala1);
		
		JPanel panel_status_1_2 = new JPanel();
		panel_status_1_2.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_2.setBackground(SystemColor.menu);
		panel_status_1_2.setBounds(241, 80, 469, 40);
		panel_2.add(panel_status_1_2);
		
		stala2 = new JLabel(" ");
		stala2.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_2.add(stala2);
		
		JPanel panel_status_1_3 = new JPanel();
		panel_status_1_3.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_3.setBackground(SystemColor.menu);
		panel_status_1_3.setBounds(241, 120, 469, 40);
		panel_2.add(panel_status_1_3);
		
		stala3 = new JLabel(" ");
		stala3.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_3.add(stala3);
		
		JPanel panel_status_1_4 = new JPanel();
		panel_status_1_4.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_4.setBackground(SystemColor.menu);
		panel_status_1_4.setBounds(241, 160, 469, 40);
		panel_2.add(panel_status_1_4);
		
		stala4 = new JLabel(" ");
		stala4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_4.add(stala4);
		
		JPanel panel_status_1_5 = new JPanel();
		panel_status_1_5.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_5.setBackground(SystemColor.menu);
		panel_status_1_5.setBounds(241, 200, 469, 40);
		panel_2.add(panel_status_1_5);
		
		stala5 = new JLabel(" ");
		stala5.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_5.add(stala5);
		
		JPanel panel_status_1_6 = new JPanel();
		panel_status_1_6.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_6.setBackground(SystemColor.menu);
		panel_status_1_6.setBounds(241, 240, 469, 40);
		panel_2.add(panel_status_1_6);
		
		stala6 = new JLabel(" ");
		stala6.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_6.add(stala6);
		
		JPanel panel_status_1_7 = new JPanel();
		panel_status_1_7.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_7.setBackground(SystemColor.menu);
		panel_status_1_7.setBounds(241, 280, 469, 40);
		panel_2.add(panel_status_1_7);
		
		stala7 = new JLabel(" ");
		stala7.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_7.add(stala7);
		
		JPanel panel_status_1_8 = new JPanel();
		panel_status_1_8.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_8.setBackground(SystemColor.menu);
		panel_status_1_8.setBounds(241, 320, 469, 40);
		panel_2.add(panel_status_1_8);
		
		stala8 = new JLabel(" ");
		stala8.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_8.add(stala8);
		
		JPanel panel_status_1_9 = new JPanel();
		panel_status_1_9.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_9.setBackground(SystemColor.menu);
		panel_status_1_9.setBounds(241, 360, 469, 40);
		panel_2.add(panel_status_1_9);
		
		stala9 = new JLabel(" ");
		stala9.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_9.add(stala9);
		
		JPanel panel_status_1_10 = new JPanel();
		panel_status_1_10.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_status_1_10.setBackground(SystemColor.menu);
		panel_status_1_10.setBounds(241, 400, 469, 40);
		panel_2.add(panel_status_1_10);
		
		stala10 = new JLabel(" ");
		stala10.setFont(new Font("Tahoma", Font.PLAIN, 16));
		panel_status_1_10.add(stala10);
		
		JPanel panel = new JPanel();
		panel.setBounds(144, 300, -101, 25);
		body.add(panel);
		
	}
	
	//////////////////////////////////////////////////////////////////////////// Program frame code ends here
	
	//////////////////////////////////////////////////////////////////////////// MySQL database interfacing code starts here
	
	
	/**
	 * Retrieves the status of the deliveries
	 * 
	 * <p>
	 * This method always returns immediately, whether or not the 
	 * image exists. When this applet attempts to draw the image on
	 * the screen, the data will be loaded. The graphics primitives 
	 * that draw the image will incrementally paint on the screen. 
	 * 
	 * @param  i 	The order number of the order statuses we are searching
	 * @throws	Exception Occurs when an error occurred when accessing the database.
	 * @return      The status of the order's delivery
	 */
	
																			//////////////////////////////////////////////////////////////////////////	
	public ArrayList<String> retrievestatusstat(int i) throws Exception {		
		
		ArrayList<String> status = new ArrayList<String>();
	    try {
	 Connection connection = DriverManager.getConnection(url, user, password);//Establishing connection
	 System.out.println("Connected With the database successfully");
	 //Using SQL SELECT Query
	        PreparedStatement preparedStatement=connection.prepareStatement("select * from order_status where order_id_num = ?");
	        preparedStatement.setInt(1, i);
	        //Creating Java ResultSet object
	        ResultSet resultSet=preparedStatement.executeQuery();
	        
	        for (int m = 0; m < 10; m++) {
	        	try {
	        	  resultSet.next();
	        	  status.add(resultSet.getString(4));
	        	} catch (SQLException yeet) {
	        	  status.add(" ");
	        	}
	        	}
	        System.out.println(" ");
	        

	 } catch (SQLException e) {
	 System.out.println(e);
	 System.out.println("Error while connecting to the database");
	 } 
	 return status;   
	 }
	
	/**
	 * Retrieves the order's status date information from the database.
	 * <p>
	 * Method uses the order ID number passed to it to determine
	 * which order record to retrieve information from.
	 *
	 * @param 	i	The ID number of the order selected.
	 * @throws	Exception Occurs when an error occurred when accessing the database.
	 * @return 	An array containing information of all the date status information of an order.
	 */	
	public ArrayList<String> retrievestatusdate(int i) throws Exception {
		
		ArrayList<String> date = new ArrayList<String>();
	    try {
	    	
	 Connection connection = DriverManager.getConnection(url, user, password);//Establishing connection
	 System.out.println("Connected With the database successfully");
	 //Using SQL SELECT Query
	        PreparedStatement preparedStatement=connection.prepareStatement("select * from order_status where order_id_num = ?");
	        preparedStatement.setInt(1, i);
	        //Creating Java ResultSet object
	        ResultSet resultSet=preparedStatement.executeQuery();
	        
	        for (int m = 0; m < 10; m++) {
	        	try {
	        	  resultSet.next();
	        	  date.add(resultSet.getString(5));
	        	} catch (SQLException yeet) {
	        	  date.add(" ");
	        	}
	        	}
	        System.out.println(" ");
	        
	 } catch (SQLException e) {
	 System.out.println(e);
	 System.out.println("Error while connecting to the database");
	 }
	 return date;   
	 }
	
	/**
	 * Retrieves the order's status location information from the database.
	 * <p>
	 * Method uses the order ID number passed to it to determine
	 * which order's status records to retrieve information from.
	 *
	 * @param 	i	The ID number of the order selected.
	 * @throws	Exception Occurs when an error occurred when accessing the database.
	 * @return 	An array containing information of all the date status information of an order.
	 */	
	public ArrayList<String> retrievestatuslocation(int i) throws Exception {
		
		ArrayList<String> location = new ArrayList<String>();
	    try {
	    	
	 Connection connection = DriverManager.getConnection(url, user, password);//Establishing connection
	 System.out.println("Connected With the database successfully");
	 //Using SQL SELECT Query
	        PreparedStatement preparedStatement=connection.prepareStatement("select * from order_status where order_id_num = ?");
	        preparedStatement.setInt(1, i);
	        //Creating Java ResultSet object
	        ResultSet resultSet=preparedStatement.executeQuery();
	        
	        for (int m = 0; m < 10; m++) {
	        	try {
	        	  resultSet.next();
	        	  location.add(resultSet.getString(6));
	        	} catch (SQLException yeet) {
	        	  location.add(" ");
	        	}
	        	}
	        System.out.println(" ");

	 } catch (SQLException e) {
	 System.out.println(e);
	 System.out.println("Error while connecting to the database");
	 }
	 return location;   
	 }																				
																			//////////////////////////////////////////////////////////////////////////
	
	/**
     * Retrieve list of ordered items from the database.
     * <p>
     * Method uses the order ID number passed to it to determine
     * which order record to retrieve information from.
     *
     * @param 	i	The ID number of the order selected.
     * @throws	Exception Occurs when information inputed does not match database format.
     * @return 	A String containing the list of ordered items.
     */	
	public String retrieveitem(int i) throws Exception {
		 String itemlist = "itemlist initial value/ if this appears then something went wrong - terribly wrong";
	    try {
	    	
	 Connection connection = DriverManager.getConnection(url, user, password);//Establishing connection
	 System.out.println("Connected With the database successfully");
	 //Using SQL SELECT Query
	        PreparedStatement preparedStatement=connection.prepareStatement("select * from order_item where order_id_num = ?");
	        preparedStatement.setInt(1, i);
	        //Creating Java ResultSet object
	        ResultSet resultSet=preparedStatement.executeQuery();
	        itemlist = "";
	        while(resultSet.next()){
	             String orderitemidnum=resultSet.getString(1);
	             String orderidnum=resultSet.getString(2);
	             String itemname=resultSet.getString(3);
	             String quantity=resultSet.getString(4);
	             //Printing Results
	             System.out.println(orderitemidnum +" "+ orderidnum +" "+itemname +" "+ quantity);
	             itemlist = itemlist + itemname +" - "+ quantity +",  ";
	             
	        }
	        System.out.println("Itemlist:");
	        System.out.println(itemlist);
	        System.out.println("");
	 
	 } catch (SQLException e) {
	 System.out.println(e);
	 System.out.println("Error while connecting to the database");
	 
	 }
		return itemlist;
	 }
}