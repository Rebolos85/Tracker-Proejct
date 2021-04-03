import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import com.mysql.cj.xdevapi.Statement;
import java.sql.Connection;
import java.sql.ResultSet;

import java.awt.Color;
import java.awt.Container;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.SwingConstants;
import javax.swing.WindowConstants;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


/**
 *  <h1>Login Application</h1>
 * The Login Application is were the manager where will the manager will sign up
 * Once the username and password was successfully implemented there will be a pop up MasterKeyPassword
 * so that to determine that they're really a manager because we value security in our system and also 
 * we apply the design principle of constraint here so first we disabled button if the JTextField was empty
 * and we provided feedback so that the user will have a good user experience while using this system
 * 
 * @author Roberto A Rebolos & Romel Alcantara & John Tex Lear Ortaliz Rara
 * @since 2020 -19 -11
 *
 */
public class LogInForm extends JFrame {

    // mainPanel
    private JPanel contentPane;
    private JLabel closeButtonLabel;
    private JLabel createAnAccountLabel;
    private JLabel minimizeLabel;
    private JLabel leftLogoLabel;
    private JLabel hidePassword;
    private JLabel showPasswordLabel;
    //TopPanel for design a logo
    private JPanel topPanel;

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
     * @exceptipn NullPointerException-  If name is null
     */
    private Image logoOfLogIn = new ImageIcon(LogInForm.class.getResource("resources/Logo.png")).getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    private Image managerLogo = new ImageIcon(LogInForm.class.getResource("resources/round.png")).getImage().getScaledInstance(120, 120, Image.SCALE_SMOOTH);
    private Image loginBackground = new ImageIcon(LogInForm.class.getResource("resources/rightPanel.png")).getImage().getScaledInstance(950, 750, Image.SCALE_SMOOTH);
    private Image showPasswordIcon = new ImageIcon(LogInForm.class.getResource("resources/showConfirmPasswordIcon.png")).getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    private Image hidePasswordIcon = new ImageIcon(LogInForm.class.getResource("resources/HidePasswordIcon.png")).getImage().getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    private Image logInButton = new ImageIcon(LogInForm.class.getResource("resources/btnsignUp.png")).getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH);
    private Image cancelButton = new ImageIcon(LogInForm.class.getResource("resources/btnLoginSwitch.png")).getImage().getScaledInstance(150, 80, Image.SCALE_SMOOTH);
    private Image transportHubLogo = new ImageIcon(LogInForm.class.getResource("resources/Logo.png")).getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);

    private JTextField usernameTextField;
    private JPasswordField paswwordTextField;

    //Login and cancel button
    private JButton buttonLogIn;
    private JButton buttonCancel;

    // storing the input from the user
    private char[] passwordInput;

    // storing the JOptionPaneDialog
    private String message;

    /*These are all our object that implements Listeners
      and we make this as instance so that the scope of this will be
      global
     */
    private MyDocumentListenerForUsername myDocumentListenerForUsername;
    private MyDocumentListenerForPassword myDocumentListenerForPassword;
    private MyMouseListenerForCloseLabel myMouseListenerForCloseLabel;
    private MyActionListenerForLogin myActionListenerForLogin;
    private JLabel lblClickHereTo;
    private JLabel backgroundLabel;


   /**
    * Main method or called as Driver method is the starting point for any java program and this will Launch our application
    * @param args is unused
    * @return nothing
    */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    LogInForm frame = new LogInForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /**
     * No arugment constructor were we initialized our objects
     */
    public LogInForm() {

        myFrameLayout();
        myCloseLabel();

        myTopPanel();
        myAttachListeners();
        myLabels();
        myLogoName();

    }


    /**
     * This is our frameLayout and panel
     *
     * @return nothing
     */
    public void myFrameLayout() {

        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 850, 580);
        // setting the JFrame to the center
        setLocationRelativeTo(null);
        contentPane = new JPanel();
        contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(148, 0, 211)));
        setContentPane(contentPane);
        contentPane.setLayout(null);

    }

    /**
     * This is our close Jlabel where the user will exit the JFrame
     *
     * @return nothing
     */
    public void myCloseLabel() {
        closeButtonLabel = new JLabel("X");
        closeButtonLabel.setVerifyInputWhenFocusTarget(false);
        closeButtonLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        closeButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeButtonLabel.setForeground(Color.BLACK);
        closeButtonLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        closeButtonLabel.setBackground(Color.BLACK);
        closeButtonLabel.setBounds(830, 0, 20, 22);
        contentPane.add(closeButtonLabel);
    }


    /**
     * This is our topPanel
     *
     * @return nothing
     */
    public void myTopPanel() {
        topPanel = new JPanel();
        topPanel.setBounds(0, 0, 850, 52);
        contentPane.add(topPanel);
        topPanel.setLayout(null);


    }

    /**
     * This is our logo of the company name Transporthub and JLabel
     *
     * @return nothing
     */
    public void myLogoName() {
        JLabel TransportHubLabel = new JLabel("TransportHub");
        TransportHubLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TransportHubLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        TransportHubLabel.setBounds(43, 11, 120, 35);
        topPanel.add(TransportHubLabel);

        JLabel frameLogo = new JLabel("");
        frameLogo.setHorizontalAlignment(SwingConstants.CENTER);
        frameLogo.setBounds(10, 11, 30, 30);
        frameLogo.setIcon(new ImageIcon(logoOfLogIn));
        topPanel.add(frameLogo);

    }

    /**
     * These are all our attachListener were it become as inner class
     *
     * @return nothing
     */

    public void myAttachListeners() {
        myDocumentListenerForUsername = new MyDocumentListenerForUsername();
        usernameTextField = new JTextField();
        usernameTextField.setColumns(10);
        usernameTextField.setBounds(550, 277, 217, 28);
        usernameTextField.getDocument().addDocumentListener(myDocumentListenerForUsername);
        contentPane.add(usernameTextField);
        myDocumentListenerForPassword = new MyDocumentListenerForPassword();
        paswwordTextField = new JPasswordField();
        paswwordTextField.setSelectedTextColor(Color.WHITE);
        paswwordTextField.setBounds(550, 319, 217, 28);
        paswwordTextField.getDocument().addDocumentListener(myDocumentListenerForPassword);
        contentPane.add(paswwordTextField);

        JLabel createAnAccountLabel = new JLabel("Click here to create an account");
        createAnAccountLabel.setForeground(Color.WHITE);

        /**
         * Attaching Listener to let user to create an account
         * @param MouseAdapter - the mouse listener
         */
        createAnAccountLabel.addMouseListener(new MouseAdapter() {

            /**
             * Letting user click create an account by using JLabel Click here to create an account
             * and handling the events that may possible occur
             * @return nothing
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                // displaying the SignUpForm
               SignUpForm signUpForm = new SignUpForm();
               /**
              * we dispose the log in button because we personally believed
                * that it may become to annoying to user to have many frame that will prompt                 * in the screen
                 */
               dispose();                // setting the frame to be visible
                signUpForm.setVisible(true);
                // setting the frame in the center
                signUpForm.setLocationRelativeTo(null);
                /**
                 * Setting the signUpForm frame to not be resizable because
                 * the layout may be disarrange
                 */
                signUpForm.setResizable(false);

            }
        });
        createAnAccountLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        createAnAccountLabel.setHorizontalAlignment(SwingConstants.CENTER);
        createAnAccountLabel.setBounds(495, 494, 262, 14);
        contentPane.add(createAnAccountLabel);

        //Instantiating the objects and make this our inner class
        myMouseListenerForCloseLabel = new MyMouseListenerForCloseLabel();
        closeButtonLabel.addMouseListener(myMouseListenerForCloseLabel);
        ;


        minimizeLabel = new JLabel("-");

        /**
         * Attaching listener to let user ICONIFIED the frame
         * because we personally believed that letting user to iconified
         * the frame can have a good user experience
         * @return MouseAdapter -  the mouse listener
         */
        minimizeLabel.addMouseListener(new MouseAdapter() {
            @Override
            /**
             * setting the frame to be ICONIFIED and waiting for the event may occur in the
             * Jlabel minimizeLabel
             * @return nothing
             */
            public void mouseClicked(MouseEvent e) {
                setState(ICONIFIED);
            }

        });
        minimizeLabel.setBounds(816, 3, 12, 12);

        minimizeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        minimizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        minimizeLabel.setForeground(Color.BLACK);
        minimizeLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        minimizeLabel.setBackground(Color.BLACK);

        topPanel.add(minimizeLabel);

    }


    /**
     * These are all our JLabel that have been instantiated
     *
     * @return nothing
     */
    public void myLabels() {
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        usernameLabel.setBounds(462, 278, 78, 23);
        contentPane.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setForeground(Color.WHITE);
        passwordLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        passwordLabel.setBounds(462, 320, 78, 23);
        contentPane.add(passwordLabel);


        buttonCancel = new JButton("Cancel");
        /**
         * Attaching Listener to the JButtonCancel because
         * we personally believed that the system should let the user
         * to undo or delete because it can have a good user expereince
         * and this is waiting for an event that may occur in the JButton buttonCancel
         * @param ActionListener-  the ActionListener to be added
         */
        buttonCancel.addActionListener(new ActionListener() {
            /**
             * Waiting for an event that may occur and letting user to dispose the frame
             * @param e - Invoked when an action occurs.
             */
            public void actionPerformed(ActionEvent e) {
                // disposing the JFrame login
                dispose();
            }
        });
        buttonCancel.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonCancel.setForeground(Color.BLACK);
        buttonCancel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        buttonCancel.setIcon(new ImageIcon(cancelButton));
        buttonCancel.setBounds(640, 397, 120, 55);

        contentPane.add(buttonCancel);


        myActionListenerForLogin = new MyActionListenerForLogin();
        buttonLogIn = new JButton("Log in");
        buttonLogIn.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonLogIn.setForeground(Color.BLACK);
        buttonLogIn.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        buttonLogIn.setBounds(469, 397, 120, 55);
        buttonLogIn.setIcon(new ImageIcon(logInButton));
        buttonLogIn.addActionListener(myActionListenerForLogin);
        buttonLogIn.setEnabled(false);
        contentPane.add(buttonLogIn);


        JLabel lblNewLabel_1 = new JLabel("");
        lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel_1.setBounds(582, 127, 120, 120);
        lblNewLabel_1.setIcon(new ImageIcon(managerLogo));
        contentPane.add(lblNewLabel_1);

        leftLogoLabel = new JLabel("");
        leftLogoLabel.setVerticalAlignment(SwingConstants.TOP);
        leftLogoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        leftLogoLabel.setHorizontalAlignment(SwingConstants.LEFT);
        leftLogoLabel.setBounds(72, 188, 250, 264);
        leftLogoLabel.setIcon(new ImageIcon(transportHubLogo));
        contentPane.add(leftLogoLabel);

        hidePassword = new JLabel("");
        hidePassword.setBounds(777, 316, 26, 26);
        hidePassword.setIcon(new ImageIcon(hidePasswordIcon));
        hidePassword.setVisible(true);

        /**
         * Attaching Listener to let user to show the the password through alphabet
         * @return MouseAdapter- the mouse listener
         */
        hidePassword.addMouseListener(new MouseAdapter() {
            /**
             * Waiting for an event that may occur and letting user to show the real password
             * @param e -Invoked when the mouse button has been clicked (pressed and released) on a component.
             * @return nothing
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                hidePassword.setVisible(false);
                showPasswordLabel.setVisible(true);
                paswwordTextField.setEchoChar((char) 0);
            }
        });

        contentPane.add(hidePassword);

        showPasswordLabel = new JLabel("");
        showPasswordLabel.setBounds(777, 316, 26, 26);
        showPasswordLabel.setIcon(new ImageIcon(showPasswordIcon));
        showPasswordLabel.setVisible(false);

        /**
         * AttachingListener to let user hide the password
         * and the hide icon will be display to perceived that
         * the password has been hidden
         * @param MouseAdapter - the mouse listener
         *
         */
        showPasswordLabel.addMouseListener(new MouseAdapter() {
            /**
             * Waiting for an event hat may occur in the JLabel and letting user to hide
             * the password with asterisk when he click the JLabel icon hide
             * @param e Invoked when the mouse button has been clicked (pressed and released) on a component.
             * @return nothingg
             */
            @Override
            public void mouseClicked(MouseEvent e) {
                hidePassword.setVisible(true);
                showPasswordLabel.setVisible(false);
                // setting the password to asterisk
                paswwordTextField.setEchoChar('*');

            }
        });

        contentPane.add(showPasswordLabel);
        
        lblClickHereTo = new JLabel("Click here to go Track Page");
        lblClickHereTo.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
                try {
                   TrackPage trackpage = new TrackPage();
                   trackpage.frame.setVisible(true);

                } catch (Exception ye) {
                    ye.printStackTrace();
                }
            }
        	
        });
        lblClickHereTo.setHorizontalAlignment(SwingConstants.CENTER);
        lblClickHereTo.setForeground(Color.WHITE);
        lblClickHereTo.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        lblClickHereTo.setBounds(62, 494, 262, 18);
        contentPane.add(lblClickHereTo);
        
        backgroundLabel = new JLabel("");
        backgroundLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundLabel.setBounds(0, 50, 850, 530);
        backgroundLabel.setIcon(new ImageIcon(loginBackground));
        contentPane.add(backgroundLabel);
    }


    /*
     In this class we're checking the username is empty then we disabled
       the JButton log in to help user to not not have prone on errors
     */
    class MyDocumentListenerForUsername implements DocumentListener {
        /**
         * Checking the insert input from the user
         *
         * @param e -Interface for document change notification
         * @return nothing
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            checkingIfThePasswordIsEmpty();

        }

        /**
         * Checking the removeupdate input from the user
         *
         * @param e -Interface for document change notification
         * @return nothing
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            checkingIfThePasswordIsEmpty();

        }

        /**
         * Checking the changesUpdate that was happened in the JTextField username
         *
         * @param e -Interface for document change notification
         * @return nothing
         */
        @Override
        public void changedUpdate(DocumentEvent e) {
            checkingIfThePasswordIsEmpty();
        }

        /**
         * Checking if the username is empty because we want
         * our user will have a good user experience
         * and they're not prone to errors which can lead
         * to bad user experience while they're using the system
         *
         * @return noting
         */
        private void checkingIfThePasswordIsEmpty() {
            // getting the user input
            String usernameEmpty = usernameTextField.getText();
            //Checking if username is empty disabled the button signup
            if (usernameEmpty.isEmpty()) {
                buttonLogIn.setEnabled(false);
                // otherwise buttonLogin will be enabled
            } else {
                buttonLogIn.setEnabled(true);
            }
        }
    }

    /*
      In this class we're checking the username is empty then we disabled
       he JButton log in to help user to not not have prone on errors
     */
    class MyDocumentListenerForPassword implements DocumentListener {
        /**
         * Checking the insert input that was happened JTextField username
         *
         * @param e -Interface for document change notification
         * @return nothing
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            checkingIfThePasswordIsEmpty();

        }

        /**
         * Checking the removeUpdate that was happened from the user input
         *
         * @param e -Interface for document change notification
         * @return nothing
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            checkingIfThePasswordIsEmpty();

        }

        /**
         * Checking the changeUpdate that was happend in the JTextField username input from the user
         *
         * @param e -Interface for document change notification
         * @return nothing
         */

        @Override
        public void changedUpdate(DocumentEvent e) {
            checkingIfThePasswordIsEmpty();

        }


        private void checkingIfThePasswordIsEmpty() {
            passwordInput = paswwordTextField.getPassword();

            if (passwordInput.length == 0) {
                buttonLogIn.setEnabled(false);
            } else {
                buttonLogIn.setEnabled(true);
            }
        }

    }

    /* In this class we want to check if the user want to close the frame
       and cancel because our team personally believed that
       in order that the system will have a  good user experience is letting them
       to choose options
     */
    class MyMouseListenerForCloseLabel implements MouseListener {

        /**
         * In this method we're checking if user want to exit the frame or not
         * and this method is waiting for an event that may occur in the JLabel close listener
         *
         * @param e -An event which indicates that a mouse action occurred in a component
         * @return nothing
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            int userChoices = JOptionPane.showConfirmDialog(null,
                    "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION);

            if (userChoices == JOptionPane.YES_OPTION) {
            	Container frame = closeButtonLabel.getParent();
	            do 
	                frame = frame.getParent(); 
	            while (!(frame instanceof JFrame));                                      
	            ((JFrame) frame).dispose();
            } else {
                LogInForm.this.setVisible(true);
            }

        }

        /**
         * Invoked when a mouse button has been pressed on a component.
         *
         * @param e - An event which indicates that a mouse action occurred in a component
         * @retun nothing
         */
        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        /**
         * Invoked when a mouse button has been released on a component.
         *
         * @param e- An event which indicates that a mouse action occurred in a component
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        /**
         * Invoked when the mouse enters a component.
         *
         * @param e -An event which indicates that a mouse action occurred in a component
         * @return nothing
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        /**
         * Invoked when the mouse exits a component.
         *
         * @param - An event which indicates that a mouse action occurred in a component.
         * @return nothing
         */
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

    /*
     This class is verifying the user input and the database
    username and password if they watch
     */
    class MyActionListenerForLogin implements ActionListener {


        /**
         * In this method we're checking if the userinput and password
         * that was inputed in the JTextField and JPassword is match
         * with the data that was stored in the database
         *
         * @param e - Invoked when an action occurs.
         * @return nothing
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            try {
                // storing the database username
                String databaseUsername;
                // storing the database password
                String databasePassword;
                // getting the user input in the username
                String usernameInput = usernameTextField.getText();
                // getting the user input in the password
                String passwordInput = String.valueOf(paswwordTextField.getText());

                /*
                 * SQL query that will select the username and password from the
                 * relation manager_account
                 */
                String queryInTheDatabase = "SELECT username, password FROM manager_account";

                boolean wrongPassword = false;

                /**
                 * Getting a connection to to the given database URL
                 *
                 * @param jdbc:mysql://localhost:3306 -a database url of the form
                 *                                    jdbc:subprotocol:subname
                 * @param root - the database user on whose behalf the
                 *                                    connection is being made
                 * @param Rebolos143#  - the user's password
                 * @exception SQLTimeoutException -when the driver has determined that the
                 *                                timeout value specified by the setLoginTimeout
                 *                                method has been exceeded
                 * @exception SQLException – if a database access error occurs or the url
                 *                                is null
                 */
                Connection connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/transporthubfinalproject", "root", "Rebolos143#");

                /**
                 * Object for sending parameterized SQL statements to the database.
                 *
                 * @param parameterIndex - the index
                 * @param second         - the value
                 * @exception SQLException -if parameterIndex does not correspond to a parameter
                 *                         marker in the SQL statement;
                 */
                PreparedStatement stmt = connection.prepareStatement(queryInTheDatabase);

                /**
                 * Executing the SQL query in this PreparedStatement object and returns the ResultSet
                 * @return ResultSet -object that contains the data produced by the query; never null
                 * @exception SQLTimeoutException -hen the driver has determined that the timeout value that was specified by the setQueryTimeout method has been exceeded
                 * @eception SQLException -if a database access error occur
                 */
                ResultSet rs = stmt.executeQuery();
                /** he first call to the method next makes the first row the current row
                 *  the second call makes the second row the current row,
                 * @return boolean true -if the new current row is valid  if there are no more rows
                 */
                while (rs.next()) {
                    /**
                     * Storing the username and passowrd from the retrieves
                     * the value of the designated column in the current row of this ResultSel
                     *
                     * @return column value -  if the value is SQL NULL, the value returned is null
                     * @param columnLabel– the label for the column specified with the SQL AS clause.
                     * @exception SQLException -  if the columnLabel is not valid
                     *
                     */
                    databaseUsername = rs.getString("username");
                    databasePassword = rs.getString("password");

                     /*
                       Checking the database username and userInput number and password if match
                       then there will be JOptionDialog will prompt to let user
                       to input a masterkey password that was declared within the program
                      */
                    if (e.getSource() == buttonLogIn && databaseUsername.equals(usernameInput) && databasePassword.equals(passwordInput)) {
                    	wrongPassword= true;
                         
                        try {
                           MasterKeyFrame window = new MasterKeyFrame();
                           
                            window.setVisible(true);
                          

                        } catch (Exception ye) {
                            ye.printStackTrace();
                        }
                    }
                      
                  }

       
                

                /** Waiting for this to happen when it is automatically closed.
                 * @exception SQLException - if a database access error occurs
                 */
                rs.close();

             
                   if(!wrongPassword) {
                	   usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                       paswwordTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                       JOptionPane.showMessageDialog(null, "Please Check Username and Password ");
                   }
              


                //Catching the unhandle exception
            } catch (Exception error) {
                error.printStackTrace();
            }

        }
    }
}
        


    


