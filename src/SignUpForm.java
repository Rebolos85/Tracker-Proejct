import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.MaskFormatter;

import com.sun.jdi.connect.spi.Connection;


/**
 * <h1>Sign Up Application</h1>
 * The Sign Up Application is where the manager will sign up for creating an account
 * and In this application we want only the valid input for each JLabels that have JTextField
 * and we provide feedback if the user input is incorrect with color border of the JTextField red because and
 * if it's correct the border will be color green and we used regular expression for the patterns of the valid input
 * This system is only more focus on generation a feeback because our team believed that in order that we could create a good usablity system 
 * we should have feedback on what he does while using the system and also we have a requirements for the Password to be inputed and
 * the age should be atleast 18 to 60 and the year should be also 1960 to 2002
 *  also we disabled our JTextField if one of the JTextField is empty and we provide an icon which user could easily perceived the feedback
 * 
 * @author Roberto A Rebolos Jr, Romel Alcantara and John Tex Lear Ortaliz Rara
 * @since 2020 -19 -11
 *
 */
public class SignUpForm extends JFrame {

    private JPanel contentPane;

    //information used to connect to the database
    final String url = "jdbc:mysql:///transporthubfinalproject?useSSL=false";					//replace url from testdb to actual database
    final String user = "root";															
    final String password = "Rebolos143#";
    
    /*These are all our JTextField Components
     * and also our JPasswordField
     */
    private JTextField firstnameTextField;
    private JTextField lastnameTextField;
    private JTextField middleInitialTextField;
    private JTextField usernameTextField;
    private JPasswordField firstPasswordField;
    private JPasswordField confirmPasswordField;
    private JTextField masteyKeyIdTextField;
    private JTextField dayTextField;
    private JTextField monthTextField;
    private JTextField yearTextField;

    private JTextField ageTextField;

    // These are all our JButton
    private JButton buttonSignUp;
    private JButton btnCancel;

    // These are all the instance JLabel components 
    private JLabel lblAge;
    private JLabel lblDate;
    private JLabel passwordLabel;
    private JLabel confirmPasswordLabel;
    private JLabel eigthCharactersLabel;
    private JLabel eightIconLabel;
    private JLabel capitalLabel;
    private JLabel digitSpecialLabel;
    private JLabel digitLabel;
    private JLabel capitalAndLower;
    private JLabel determinePasswordLength;
    private JLabel gobackToLogin;

    
    
    // Storing the firstPasswordField
    private char[] firstPassword;
    // Storing the secondPasswordField
    private char[] confirmPassword;

    
    /*
     * All the Objects that we Instantiated inside in the constructor so
     * that we could make an inner classes and provide an event handlers 
     * 
     */
    private MyDocumentListener myDocumentListener;
    private MyActionListenerForSignup myActionListenerForSignup;
    private MyMouseListenerForGoBackToLogIn myMouseListenerForGoBackToLogIn;
    private MyMouseListenerForShowIcon myMouseListenerForShowIcon;
    private MyMouseListenerForHidePassword myMouseListenerForHidePassword;
    private MyMouseListenerforHideConfirmPassword myMouseListenerforHideConfirmPassword;
    private MyMouseListenerForShowConfirmPassword myMouseListenerForShowConfirmPassword;
    private MyDocumentListenerForChecking myDocumentForChecking;

    /*
     * These variables are used to stored the the input from the JTextField
     *
     */
    private String managerFirstNameTextField;
    private String managerLastnNameTextField;
    private String managerUsername;
    private String managerMiddleInitial;
    private String managerMasterKeyId;
    private String managerAge;
    private String managerDayRegister;
    private String managerMonthRegister;
    private String managerYearRegister;
    private String managerPhoneNumber;
    private String firstPasswordTextField;
    private String confirmPassowrdField;

    /*
     * These variables integer are used to stored the casting of String to Integer
     * using Integer.parseInt() methods
     *
     */
    private int determineTheLength;
    private int managerByAge;
    private int dayOfBirth;
    private int yearCreateAccount;
    private int managerMonth;

    /*
     * These variables are used to determine the input in the JPasswordTextField
     */
    private boolean hasDigitAndChar, hasCapitalAndLower, hasNoInput;

   
     /*
      * We make our phone number to be formatted to easily perceived
      * by the user the number to be inputed in the JTextFieldFormatted
      * because we personally believed that dividing the number into
      * sub group can make our system having a good user experience
      */
    private JFormattedTextField phoneNumberTextField;
    /*
     * This will be the format of our phone number 
     * so that to restrict our to not input more than twelve number
     * and also we want our user to perceived the number by dividing
     * into sub groups
     *
     */
    private MaskFormatter forPhoneNumber;

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
    private Image logoOfFrame = new ImageIcon(SignUpForm.class.getResource("resources/Logo.png")).getImage()
            .getScaledInstance(32, 32, Image.SCALE_SMOOTH);
    private Image imageLogoFOrCheck = new ImageIcon(SignUpForm.class.getResource("resources/checkIcon.png")).getImage()
            .getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    private Image rightBackground = new ImageIcon(SignUpForm.class.getResource("resources/rightPanel.png")).getImage()
            .getScaledInstance(1200, 950, Image.SCALE_SMOOTH);
    private Image leftLogo = new ImageIcon(SignUpForm.class.getResource("resources/Logo.png")).getImage()
            .getScaledInstance(300, 300, Image.SCALE_SMOOTH);
    private Image wrongIcon = new ImageIcon(SignUpForm.class.getResource("resources/Close-icon.png")).getImage()
            .getScaledInstance(16, 16, Image.SCALE_SMOOTH);
    private Image transportHubLogo = new ImageIcon(SignUpForm.class.getResource("resources/Logo.png")).getImage()
            .getScaledInstance(300, 300, Image.SCALE_SMOOTH);
    private Image showPasswordIcon = new ImageIcon(SignUpForm.class.getResource("resources/showPasswordIcon.png")).getImage()
            .getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    private Image hidePasswordIcon = new ImageIcon(SignUpForm.class.getResource("resources/HidePasswordIcon.png")).getImage()
            .getScaledInstance(26, 26, Image.SCALE_SMOOTH);
    private Image signUpButtonIcon = new ImageIcon(SignUpForm.class.getResource("resources/btnSignUp.png")).getImage()
            .getScaledInstance(150, 80, Image.SCALE_SMOOTH);
    private Image logInButtonicon = new ImageIcon(SignUpForm.class.getResource("resources/btnLoginSwitch.png")).getImage()
            .getScaledInstance(150, 80, Image.SCALE_SMOOTH);
    private Image showConfirmPasswordIcon = new ImageIcon(
            SignUpForm.class.getResource("resources/showConfirmPasswordIcon.png")).getImage().getScaledInstance(26, 26,
            Image.SCALE_SMOOTH);
    private Image hideConfirmPasswordIcon = new ImageIcon(
            SignUpForm.class.getResource("resources/confirmPasswordHideIcon.png")).getImage().getScaledInstance(26, 26,
            Image.SCALE_SMOOTH);

    /*
     * These are all the JLabels that i used and i make it as instance variable
     * because I need to access this outside of the constructor or methods and These
     * variables i used to have an interactions if users are incorrectly type
     * something in the system by displaying a text of what he does.
     */
    private JLabel showThePassword;
    private JLabel hideIconPassword;
    private JLabel confirmPasswordHideLabel;
    private JLabel confirmPasswordShowLabel;
    private JLabel FaqLabel;
    private JLabel frameLogo;
    private JLabel TransportHubLabel;
    private JLabel leftSideLogo;
    private JLabel leftLogoLabel;
    private JLabel lblMasterkeyId;
    private JLabel determineTheDuplicates;
    private JLabel determineTheDuplicatesPassword;
    private JLabel requiredPassword;
    private JLabel backgroundSignUpLabel;

    /*
     * Initializing the objects
     *
     */
    public SignUpForm() {

        setUndecorated(true);
        setVisible(true);
        setDefaultCloseOperation(this.EXIT_ON_CLOSE);
        setBounds(100, 100, 1000, 950);
        setLocationRelativeTo(null);
        // main panel
        contentPane = new JPanel();

        contentPane.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(139, 0, 139)));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        /*
         * Instantiating the Object myDocumentForChecking so that we could used this as
         * our inner class
         */
        myDocumentForChecking = new MyDocumentListenerForChecking();

        firstnameTextField = new JTextField();
        firstnameTextField.setBounds(710, 120, 217, 28);
        firstnameTextField.setColumns(10);

        /**
         * handling the events occurs in the JTextField
         *
         * @param myDocumentForChecking – the observer to register
         * @returns getDocument return the model
         */
        firstnameTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(firstnameTextField);

        lastnameTextField = new JTextField();
        lastnameTextField.setColumns(10);
        lastnameTextField.setBounds(710, 159, 217, 28);

        /**
         * handling the events occurs in the lastname JTextField
         *
         * @param myDocumentForChecking – the observer to register
         * @returns getDocument return the model
         */
        lastnameTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(lastnameTextField);

        JLabel middleInitialLabel = new JLabel("Middle Name");
        middleInitialLabel.setForeground(Color.WHITE);
        middleInitialLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        middleInitialLabel.setHorizontalAlignment(SwingConstants.CENTER);
        middleInitialLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        middleInitialLabel.setBounds(590, 199, 90, 23);
        contentPane.add(middleInitialLabel);

        middleInitialTextField = new JTextField();
        middleInitialTextField.setColumns(10);
        middleInitialTextField.setBounds(710, 198, 217, 28);

        /**
         * handling the events occurs in the middileInitial JTextField
         *
         * @param myDocumentForChecking – the observer to register
         * @returns getDocument return the model
         */
        middleInitialTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(middleInitialTextField);

        JLabel usernameLabel_1 = new JLabel("Username");
        usernameLabel_1.setForeground(Color.WHITE);
        usernameLabel_1.setHorizontalTextPosition(SwingConstants.LEADING);
        usernameLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel_1.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        usernameLabel_1.setBounds(590, 238, 73, 23);
        contentPane.add(usernameLabel_1);

        usernameTextField = new JTextField();
        usernameTextField.setColumns(10);
        usernameTextField.setBounds(710, 237, 217, 28);

        /**
         * handling the events occurs in the usernameTextField
         *
         * @param myDocumentForChecking – the observer to register
         * @returns getDocument return the model
         */
        usernameTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(usernameTextField);

        passwordLabel = new JLabel("Password");
        passwordLabel.setForeground(Color.WHITE);

        passwordLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        passwordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        passwordLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        passwordLabel.setBounds(590, 276, 70, 25);

        contentPane.add(passwordLabel);

        confirmPasswordLabel = new JLabel("Confirm Password");
        confirmPasswordLabel.setForeground(Color.WHITE);
        confirmPasswordLabel.setHorizontalTextPosition(SwingConstants.LEADING);
        confirmPasswordLabel.setHorizontalAlignment(SwingConstants.CENTER);
        confirmPasswordLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        confirmPasswordLabel.setBounds(569, 449, 138, 25);
        contentPane.add(confirmPasswordLabel);

        /*
         * Instantiating the objects so that i could use it as my inner class implement an
         * interface so that i could determine the event occurs.
         */
        myDocumentListener = new MyDocumentListener();

        firstPasswordField = new JPasswordField();
        firstPasswordField.setSelectedTextColor(Color.WHITE);

        firstPasswordField.setBounds(710, 276, 217, 28);
        contentPane.add(firstPasswordField);

        /**
         * handling the events occurs in the firstPasswordField JPasswordField
         *
         * @param myDocumentListener – the observer to register
         * @returns getDocument return the model
         */
        firstPasswordField.getDocument().addDocumentListener(myDocumentListener);

        confirmPasswordField = new JPasswordField(10);
        confirmPasswordField.setSelectedTextColor(Color.WHITE);
        confirmPasswordField.setForeground(Color.BLACK);
        confirmPasswordField.setBorder(null);
        confirmPasswordField.setBounds(710, 449, 217, 28);

        /**
         * handling the events occurs in the JPasswordField
         *
         * @param myDocumentListener – the observer to register
         * @returns getDocument return the model
         */
        confirmPasswordField.getDocument().addDocumentListener(myDocumentListener);
        contentPane.add(confirmPasswordField);

        eightIconLabel = new JLabel("");
        eightIconLabel.setEnabled(false);
        eightIconLabel.setIcon(new ImageIcon(wrongIcon));
        eightIconLabel.setBounds(652, 359, 18, 14);
        contentPane.add(eightIconLabel);

        eigthCharactersLabel = new JLabel("Password should contain atleast 8 characters ");
        eigthCharactersLabel.setEnabled(false);
        eigthCharactersLabel.setBounds(671, 356, 280, 22);
        contentPane.add(eigthCharactersLabel);

        digitSpecialLabel = new JLabel("");
        digitSpecialLabel.setHorizontalAlignment(SwingConstants.CENTER);
        digitSpecialLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        digitSpecialLabel.setEnabled(false);
        digitSpecialLabel.setIcon(new ImageIcon(wrongIcon));
        digitSpecialLabel.setBounds(652, 396, 17, 14);
        contentPane.add(digitSpecialLabel);

        capitalLabel = new JLabel("Password should contain Capital Case & LowerCase");
        capitalLabel.setEnabled(false);
        capitalLabel.setBounds(671, 375, 340, 22);
        contentPane.add(capitalLabel);

        capitalAndLower = new JLabel("");
        capitalAndLower.setEnabled(false);
        capitalAndLower.setIcon(new ImageIcon(wrongIcon));
        capitalAndLower.setBounds(652, 378, 17, 14);
        contentPane.add(capitalAndLower);

        digitLabel = new JLabel("Password should contain digit and  Special Characters ");
        digitLabel.setEnabled(false);
        digitLabel.setBounds(671, 393, 330, 20);
        contentPane.add(digitLabel);

        determinePasswordLength = new JLabel("");
        determinePasswordLength.setBounds(675, 323, 102, 22);
        contentPane.add(determinePasswordLength);

        JLabel PhoneNumber = new JLabel("Phone number");
        PhoneNumber.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        PhoneNumber.setForeground(new Color(255, 255, 255));
        PhoneNumber.setBounds(590, 534, 115, 28);
        contentPane.add(PhoneNumber);

        myActionListenerForSignup = new MyActionListenerForSignup();
        buttonSignUp = new JButton("Sign up");
        buttonSignUp.setEnabled(false);
        buttonSignUp.setHorizontalTextPosition(SwingConstants.CENTER);
        buttonSignUp.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        buttonSignUp.setIcon(new ImageIcon(signUpButtonIcon));
        buttonSignUp.setBounds(585, 794, 120, 55);
        buttonSignUp.addActionListener(myActionListenerForSignup);
        contentPane.add(buttonSignUp);

        btnCancel = new JButton("Cancel");
        /**
         * Handling the event occurs in btnCancel and disposing the JFrame SignUp
         */
        btnCancel.addActionListener(e -> dispose());

        btnCancel.setHorizontalTextPosition(SwingConstants.CENTER);
        btnCancel.setForeground(Color.BLACK);
        btnCancel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        btnCancel.setIcon(new ImageIcon(logInButtonicon));
        btnCancel.setBounds(789, 794, 120, 55);
        contentPane.add(btnCancel);

        myMouseListenerForGoBackToLogIn = new MyMouseListenerForGoBackToLogIn();
        gobackToLogin = new JLabel("Click here to log in");
        gobackToLogin.setForeground(Color.WHITE);
        gobackToLogin.setFont(new Font("Berlin Sans FB", Font.PLAIN, 18));
        /**
         * Handling the event occurs and letting user to go back to Login JFrame
         *
         * @param myMouseListenerGoBackToLogin – the mouse listener
         *
         */
        gobackToLogin.addMouseListener(myMouseListenerForGoBackToLogIn);
        gobackToLogin.setBounds(679, 874, 139, 36);
        contentPane.add(gobackToLogin);

        JLabel closeButtonLabel = new JLabel("X");
        closeButtonLabel.setBackground(Color.BLACK);
        closeButtonLabel.setForeground(Color.BLACK);
        closeButtonLabel.addMouseListener(new MouseAdapter() {

            /*
             * Handling the event occurs using mouseclick listener in the X JLabel and if
             * the user select the yes the JFrame will be dispose
             *
             */
            @Override
            public void mouseClicked(MouseEvent e) {

//				if(JOptionPane.showConfirmDialog(null,"Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_CANCEL_OPTION) == 0);

                int userChoices = JOptionPane.showConfirmDialog(null,
                        "Are you sure you want to close this application?", "Confirmation", JOptionPane.YES_NO_OPTION);
                
                //If user will select Yes The frame will be exit
                if (userChoices == JOptionPane.YES_OPTION) {
                	Container frame = closeButtonLabel.getParent();
    	            do 
    	                frame = frame.getParent(); 
    	            while (!(frame instanceof JFrame));                                      
    	            ((JFrame) frame).dispose();
                } else {
                	//otherwise the frame will be visible
                    SignUpForm.this.setVisible(true);
                }
            }
        });

        closeButtonLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        closeButtonLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        closeButtonLabel.setHorizontalAlignment(SwingConstants.CENTER);
        closeButtonLabel.setVerifyInputWhenFocusTarget(false);
        closeButtonLabel.setBounds(980, 0, 20, 22);
        contentPane.add(closeButtonLabel);

        JLabel minimizeLabel = new JLabel("-");
        /*
         * Handling the event occurs by the user and the JFrame will be iconified if
         * user will select the lebel -
         */
        minimizeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setState(ICONIFIED);
            }
        });
        minimizeLabel.setBackground(Color.BLACK);
        minimizeLabel.setForeground(Color.BLACK);
        minimizeLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        minimizeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        minimizeLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        minimizeLabel.setBounds(962, 4, 18, 14);
        contentPane.add(minimizeLabel);

        JLabel usernameLabel = new JLabel("Firstname");
        usernameLabel.setForeground(Color.WHITE);
        usernameLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        usernameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        usernameLabel.setBounds(590, 118, 71, 28);
        contentPane.add(usernameLabel);

        JLabel LastnameLabel = new JLabel("Lastname");
        LastnameLabel.setForeground(Color.WHITE);

        LastnameLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        LastnameLabel.setHorizontalAlignment(SwingConstants.CENTER);
        LastnameLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        LastnameLabel.setBounds(590, 157, 71, 28);
        contentPane.add(LastnameLabel);

        lblAge = new JLabel("Age");
        lblAge.setForeground(Color.WHITE);
        lblAge.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblAge.setBounds(590, 573, 30, 20);
        contentPane.add(lblAge);

        lblDate = new JLabel("Day");
        lblDate.setForeground(Color.WHITE);
        lblDate.setFont(new Font("Tahoma", Font.PLAIN, 16));
        lblDate.setBounds(590, 621, 46, 23);
        contentPane.add(lblDate);

        dayTextField = new JTextField();

        dayTextField.setBounds(710, 620, 217, 28);
        dayTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(dayTextField);
        dayTextField.setColumns(10);

        JLabel cityLabel = new JLabel("Month");
        cityLabel.setForeground(Color.WHITE);
        cityLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        cityLabel.setBounds(590, 664, 46, 14);
        contentPane.add(cityLabel);

        ageTextField = new JTextField();
        ageTextField.setBounds(710, 571, 217, 28);
        /**
         * handling the events occurs in the age JTextField
         *
         * @param myDocumentForChecking – the observer to register
         * @returns getDocument return the model
         */
        ageTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(ageTextField);
        ageTextField.setColumns(10);

        monthTextField = new JTextField();
        monthTextField.setColumns(10);
        monthTextField.setBounds(710, 659, 217, 28);
        /**
         * handling the events occurs in the JTextField
         *
         * @param myDocumentForChecking – the observer to register
         * @returns getDocument return the model
         */
        monthTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(monthTextField);

        yearTextField = new JTextField();
        yearTextField.setColumns(10);
        yearTextField.setBounds(710, 698, 217, 28);
        /**
         * handling the events occurs in the JTextField
         *
         * @param myDocumentForChecking – the observer to register
         * @returns getDocument return the model
         */
        yearTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(yearTextField);

        JPanel topPanel = new JPanel();
        topPanel.setBackground(Color.WHITE);
        topPanel.setBounds(0, 0, 1000, 57);
        contentPane.add(topPanel);
        topPanel.setLayout(null);
        //Instantiating the object so taht i could use this as my inner class
        MyMouseListenerForHelp myMouseListener = new MyMouseListenerForHelp();
        JLabel lblNewLabel = new JLabel("Help");
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lblNewLabel.setBounds(643, 11, 89, 35);
        /**
         * Handling the event occurs of help JLabel which you can found in the Sign Up
         * JFrame
         */
        lblNewLabel.addMouseListener(myMouseListener);
        topPanel.add(lblNewLabel);
        //Instantiating the object so taht i could use this as my inner class
        MyMouseListenerForFAQ myMouseListenerFAQ = new MyMouseListenerForFAQ();
        FaqLabel = new JLabel("FAQ");
        FaqLabel.setHorizontalAlignment(SwingConstants.CENTER);
        FaqLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        FaqLabel.setBounds(365, 11, 89, 35);
        /**
         * Handling the event occurs of help FAQ which you can found in the Sign Up
         * JFrame
         * @param myMouseListenerFAQ -  the mouse listener
         */
        FaqLabel.addMouseListener(myMouseListenerFAQ);
        topPanel.add(FaqLabel);

        frameLogo = new JLabel("");
        frameLogo.setHorizontalAlignment(SwingConstants.CENTER);
        frameLogo.setBounds(3, 11, 30, 30);
        frameLogo.setIcon(new ImageIcon(logoOfFrame));
        topPanel.add(frameLogo);

        TransportHubLabel = new JLabel("TransportHub");
        TransportHubLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        TransportHubLabel.setHorizontalAlignment(SwingConstants.CENTER);
        TransportHubLabel.setBounds(35, 11, 120, 35);
        topPanel.add(TransportHubLabel);

        try {

            /**
             * setting a mask for the JTextField phone number so that the output will be be
             * divided and easy to read
             */
            forPhoneNumber = new MaskFormatter("(####)- ###- (####)");

            // Catching the  exception may disrupt the code
        } catch (java.text.ParseException e) {
            e.printStackTrace();
        }
//        myPropertyChangeListener = new MyPropertyChangeListener();

        /**
         * Masking the input of the phone number so that it would be distinguishable the
         * numbers that was inputed of in the JTextField phone number.
         *
         * @return forPhoneNumber - AbstractFormatter to use for formatting.
         */
        phoneNumberTextField = new JFormattedTextField(forPhoneNumber);
        phoneNumberTextField.setBounds(710, 540, 217, 28);
        phoneNumberTextField.getDocument().addDocumentListener(myDocumentForChecking);
        contentPane.add(phoneNumberTextField);

        // Instantiating the object so that i could use as inner clases and put an
        // interface
        myMouseListenerForShowIcon = new MyMouseListenerForShowIcon();
        hideIconPassword = new JLabel("");
        hideIconPassword.setBounds(934, 276, 26, 26);
        // setting an icon
        hideIconPassword.setIcon(new ImageIcon(hidePasswordIcon));
        /**
         * handling the event occurs of hideconPassowrd so that the JPasswordTextField
         * inputed will be asterisk
         *
         * @return myMouseListenerForShow - the mouse listener
         */

        hideIconPassword.addMouseListener(myMouseListenerForShowIcon);
        contentPane.add(hideIconPassword);

        // Instantiating the Object so taht i could used it as my inner class
        myMouseListenerForHidePassword = new MyMouseListenerForHidePassword();

        showThePassword = new JLabel("");
        showThePassword.setBounds(934, 276, 26, 26);
        showThePassword.setIcon(new ImageIcon(showPasswordIcon));
        /**
         * handling the event occurs of showPassword so that the JPasswordTextField
         * inputed will be asterisk
         *
         * @return myMouseListenerForHidePassword - the mouse listener
         */
        showThePassword.addMouseListener(myMouseListenerForHidePassword);
        showThePassword.setVisible(false);

        contentPane.add(showThePassword);

        // Instanting the object so that i could used as my inner class
        myMouseListenerforHideConfirmPassword = new MyMouseListenerforHideConfirmPassword();
        // Instanting the object so that i could used as my inner class
        myMouseListenerForShowConfirmPassword = new MyMouseListenerForShowConfirmPassword();
        confirmPasswordHideLabel = new JLabel("");
        confirmPasswordHideLabel.setBounds(933, 451, 27, 26);
        // setting an icon of my confirmPasswordHideLabel
        confirmPasswordHideLabel.setIcon(new ImageIcon(hideConfirmPasswordIcon));
        /**
         * handling the event occurs of confirmPasswordHideLabel so that the
         * JPasswordTextField inputed will be asterisk
         *
         * @return myMouseListenerForHidePassword - the mouse listener
         */
        confirmPasswordHideLabel.addMouseListener(myMouseListenerforHideConfirmPassword);
        contentPane.add(confirmPasswordHideLabel);

        confirmPasswordShowLabel = new JLabel("");
        confirmPasswordShowLabel.setBounds(937, 451, 26, 26);

        // setting an icon
        confirmPasswordShowLabel.setIcon(new ImageIcon(showConfirmPasswordIcon));

        confirmPasswordShowLabel.addMouseListener(myMouseListenerForShowConfirmPassword);
        // setting the showLabel password first so that it would not be visible in the
        // SignUpJFrame
        confirmPasswordShowLabel.setVisible(false);
        contentPane.add(confirmPasswordShowLabel);

        JLabel yearLabel = new JLabel("Year");
        yearLabel.setForeground(Color.WHITE);
        yearLabel.setHorizontalAlignment(SwingConstants.CENTER);
        yearLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
        yearLabel.setBounds(590, 703, 38, 14);
        contentPane.add(yearLabel);

        leftLogoLabel = new JLabel("");
        leftLogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        leftLogoLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        leftLogoLabel.setBounds(102, 337, 294, 364);
        leftLogoLabel.setIcon(new ImageIcon(transportHubLogo));
        contentPane.add(leftLogoLabel);

        lblMasterkeyId = new JLabel("MasterKey ID");
        lblMasterkeyId.setForeground(Color.WHITE);
        lblMasterkeyId.setFont(new Font("Berlin Sans FB", Font.PLAIN, 16));
        lblMasterkeyId.setBounds(590, 495, 100, 28);
        contentPane.add(lblMasterkeyId);

        masteyKeyIdTextField = new JTextField();
        masteyKeyIdTextField.setBounds(710, 501, 217, 28);
        contentPane.add(masteyKeyIdTextField);
        masteyKeyIdTextField.setColumns(10);

        determineTheDuplicates = new JLabel("");
        determineTheDuplicates.setHorizontalAlignment(SwingConstants.LEFT);
        determineTheDuplicates.setBounds(597, 735, 354, 22);
        contentPane.add(determineTheDuplicates);

        determineTheDuplicatesPassword = new JLabel("");
        determineTheDuplicatesPassword.setForeground(Color.WHITE);
        determineTheDuplicatesPassword.setHorizontalTextPosition(SwingConstants.CENTER);
        determineTheDuplicatesPassword.setBounds(652, 755, 225, 14);
        contentPane.add(determineTheDuplicatesPassword);

        requiredPassword = new JLabel("");
        requiredPassword.setBounds(597, 780, 330, 14);
        contentPane.add(requiredPassword);
        
        backgroundSignUpLabel = new JLabel("");
        backgroundSignUpLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        backgroundSignUpLabel.setHorizontalAlignment(SwingConstants.CENTER);
        backgroundSignUpLabel.setBounds(-104, 56, 1105, 903);
        backgroundSignUpLabel.setIcon(new ImageIcon(rightBackground));
        contentPane.add(backgroundSignUpLabel);

    }


    /**
     * Main method or called as Driver method is the starting point for any java program and this will Launch our application
     * @param args is unused
     * @return nothing
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    SignUpForm frame = new SignUpForm();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*
     * In this class we're checking if the all the JTextField is empty by using
     * Documentlistener so that to receive notifications of changes to a text
     * document.
     */
    class MyDocumentListenerForChecking implements DocumentListener {

        /**
         * Determining when style of some of the text in the listened-to document
         * changes
         * @return nothing
         * @param e - Interface for document change notifications.
         *
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
           checkingIfInformationComplete();

        }

        /**
         * Determinining when text is inserted into the listened-to document.
         * @return nothing
         * @param e -This provides detailed information to Document observers about how
         *          the Document changed.
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            checkingIfInformationComplete();

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
           checkingIfInformationComplete();
        }

        /*
         * Getting the input from the JTextField and checking whether the JTextField was
         * empty and if it's empty the buttonSignUp will be disabled so that to avoid
         * user incorrect selection.
         */
        public void checkingIfInformationComplete() {
            managerFirstNameTextField = firstnameTextField.getText();
            managerLastnNameTextField = lastnameTextField.getText();
            managerUsername = usernameTextField.getText();
            managerMiddleInitial = middleInitialTextField.getText();
            managerAge = ageTextField.getText();
            managerDayRegister = dayTextField.getText();
            managerMonthRegister = monthTextField.getText();
            managerYearRegister = yearTextField.getText();
            managerPhoneNumber = phoneNumberTextField.getText();
            managerMasterKeyId = masteyKeyIdTextField.getText();


            /* Checking if the JTextField is empty then the buttonSignUp may disabled
             * because we want our program to restrict to user to click the sign up button
             * so that the user may havbe a good user experience
             */
            if ((managerFirstNameTextField.isEmpty() || managerLastnNameTextField.isEmpty())
                    || (managerDayRegister.isEmpty() || managerYearRegister.isEmpty())) {
                buttonSignUp.setEnabled(false);

                if ((managerUsername.isEmpty() || managerMiddleInitial.isEmpty())
                        || (managerAge.isEmpty() || managerPhoneNumber.isEmpty())) {
                    buttonSignUp.setEnabled(false);

                }

            } else {
                // if the buttonSignUp are not empty then the bubton listener will be enabled to
                // true.
                buttonSignUp.setEnabled(true);

            }

        }

    }

    /*
     * In this class MyDocumentListener we're checking the the password that was
     * inputed in the JPasswordField and the changes that was occured in the
     * JPasswordField
     */
    class MyDocumentListener implements DocumentListener {

        /**
         * In this method insertUpdate we want to check the input in the JPasswordField
         * firstPasswordField
         * @return nothing
         * @param e -This provides detailed information to Document observers about how
         *          the Document changed
         */
        @Override
        public void insertUpdate(DocumentEvent e) {
            checkPassword();
            checkingTheLengthOfFirstPassword();

        }

        /**
         * In this method insertUpdate we want to check the removed input in the
         * JPasswordField firstPasswordField
         *@return nothing
         * @param e -This provides detailed information to Document observers about how
         *          the Document changed
         */
        @Override
        public void removeUpdate(DocumentEvent e) {
            checkPassword();
            checkingTheLengthOfFirstPassword();

        }

        /**
         * In this method insertUpdate we want to check the changedUpdate in the
         * JPasswordField firstPasswordField
         * @return nothing
         * @param e -This provides detailed information to Document observers about how
         *          the Document changed
         */
        @Override
        public void changedUpdate(DocumentEvent e) {
            checkPassword();
            checkingTheLengthOfFirstPassword();

        }

        /**
         * In this method checkPassword we want to check JPasswordField firstPassword to
         * validate the user input and give them a feedback on what is the requirements
         * of password.
         *
         * @return boolean that can used to validate outside of the for loop
         */
        private boolean checkPassword() {
            /**
             * getting the password inputed in JPasswordField array of char[]
             *
             * @return firstPasswordField
             */
            firstPassword = firstPasswordField.getPassword();
            /**
             * getting the password inputed in confirm
             *
             * @return confirmPasswordField
             */
            confirmPassword = confirmPasswordField.getPassword();

            /*Checking the input of JPasswordField firstPassword and display a feedback.
             * because we want our program to let the users of this system that
             * they have a good user interaction by displaying a feedback on what he does
             * while using the system
             */
            for (char ch : firstPassword) {

                if (Character.isDigit(ch)) {
                    determinePasswordLength.setText("Too weak");
                    determinePasswordLength.setForeground(Color.white);
                    digitLabel.setEnabled(true);
                    digitLabel.setForeground(Color.white);
                    digitSpecialLabel.setEnabled(true);
                    digitSpecialLabel.setIcon(new ImageIcon(imageLogoFOrCheck));
                    hasDigitAndChar = true;

                } else if (Character.isUpperCase(ch) || Character.isLowerCase(ch)) {
                    determinePasswordLength.setText("Too weak");
                    capitalLabel.setEnabled(true);
                    capitalLabel.setForeground(Color.white);
                    capitalAndLower.setEnabled(true);
                    capitalAndLower.setIcon(new ImageIcon(imageLogoFOrCheck));
                    hasCapitalAndLower = true;
                } else {
                    determinePasswordLength.setText("");
                }

            }

            return false;
        }

        /**
         * In this method we want to validate the length if it pass in our requirements
         * that was display in below of JPasswordField firstPassword and we provide a
         * last validating and display a feedback by using JLabel
         *
         * @return boolean to check the length of JPasswordField firstPassword
         *
         *
         */
        public boolean checkingTheLengthOfFirstPassword() {

            boolean hasFifthteenDigit = false;
            boolean hasTenDigit = false;
            boolean hasEightDigit = false;

            /*
              Checking the input of first password if it's lessthan 7 then we display too weak
               because we want the password to be secured by having atleast 8 characters or morethan.
             */
            if (firstPassword.length <= 7) {
                firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                determinePasswordLength.setText("Too weak");
                determinePasswordLength.setForeground(Color.white);

            }
            /*
              Checking the input of JPasswordFieldfirst password if it's passed with the requirement of the length
               then we display a feedback check icon with green color so that means it is valid
             */
            if (firstPassword.length == 8 || firstPassword.length == 9 || firstPassword.length == 10
                    || firstPassword.length == 11) {
                determinePasswordLength.setText("");
                eigthCharactersLabel.setForeground(Color.WHITE);
                eigthCharactersLabel.setEnabled(true);
                eightIconLabel.setEnabled(true);
                eightIconLabel.setIcon(new ImageIcon(imageLogoFOrCheck));

                hasEightDigit = true;
            }
             /*
              Checking the input of JPasswordField first password if it's passed with the requirement of the length
               then we display a feedback check icon with green color so that means it is valid
             */

            if (firstPassword.length == 12 || firstPassword.length == 13 || firstPassword.length == 14) {
                eigthCharactersLabel.setForeground(Color.WHITE);
                eigthCharactersLabel.setEnabled(true);
                eightIconLabel.setEnabled(true);
                hasTenDigit = true;
            }
             /*
              Checking the input of JPasswordField first password if it's passed with the requirement of the length
               then we display a feedback check icon with green color so that means it is valid
             */
            if (firstPassword.length == 15 || firstPassword.length == 16 || firstPassword.length == 17
                    || firstPassword.length == 18 || firstPassword.length == 19 || firstPassword.length == 19) {
                eigthCharactersLabel.setForeground(Color.WHITE);
                eigthCharactersLabel.setEnabled(true);
                eightIconLabel.setEnabled(true);

                hasFifthteenDigit = true;
            }

             /*
               Setting to enabled all the icon so when the firstPassword.length was == 0
               then we display a feedback red border and wrongIcon
              */
            if (firstPassword.length == 0) {
                determinePasswordLength.setText("");
                determinePasswordLength.setForeground(Color.WHITE);
                firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                eightIconLabel.setIcon(new ImageIcon(wrongIcon));
                eightIconLabel.setEnabled(false);
                eigthCharactersLabel.setEnabled(false);
                digitLabel.setEnabled(false);
                digitSpecialLabel.setIcon(new ImageIcon(wrongIcon));
                digitSpecialLabel.setEnabled(false);
                digitSpecialLabel.setIcon(new ImageIcon(wrongIcon));
                capitalAndLower.setEnabled(false);
                capitalAndLower.setIcon(new ImageIcon(wrongIcon));

            }


           /*
             Checking the user input if the user really have valid password
             and capital or lower
             and hasEightDigit and displaying a feedback
            */
            if (hasDigitAndChar && (hasCapitalAndLower && hasEightDigit)) {
                determinePasswordLength.setText("Okay");
                determinePasswordLength.setForeground(new Color(255, 153, 0));

                firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 153, 0)));

            }
            /*
             Checking the user input if has greater than length and
             he followed the requirements then we display a feedback
            */

            if (hasDigitAndChar && (hasCapitalAndLower && hasTenDigit)) {

                determinePasswordLength.setText("Great!");
                determinePasswordLength.setForeground(new Color(0, 255, 102));
                firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));

            }

            /*Checking if the password is have 15 charcters >= 20
             * and the user followed the requirements of the system
            */

            if (hasDigitAndChar && (hasCapitalAndLower && hasFifthteenDigit)) {

                determinePasswordLength.setText("Super great!");
                determinePasswordLength.setForeground(new Color(0, 255, 255));
                firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 255)));
            }

           /*
            *Displaying a feedback on the confirmpassword so that easily perceived by the user
            * that the password didn't match with the firstpassword length
            */
            if (confirmPassword.length <= 7) {
                confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

            }

            /*
             *Displaying a feedback on the confirmpassword so that easily perceived by the user
             * that the password length that he provided is okay
             */
            if (confirmPassword.length == 8 || confirmPassword.length == 9 || confirmPassword.length == 10
                    || firstPassword.length == 11) {
               confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 153, 0)));

            }
            /*
             *Displaying a feedback on the confirmpassword so that easily perceived by the user
             * that the password length that he provided is great
             */
            if (confirmPassword.length == 12 || confirmPassword.length == 13 || confirmPassword.length == 14) {
                confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
            }

            /*
             *Displaying a feedback on the confirmpassword so that easily perceived by the user
             * that the password length that he provided is super great
             */
            if (confirmPassword.length == 15 || confirmPassword.length == 16 || confirmPassword.length == 17
                    || confirmPassword.length == 18 || confirmPassword.length == 19 || confirmPassword.length == 19) {
                confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 255)));
            }
            return false;

        }

    }

    /*
     * In this class we want the user to let them show the password and hide if they
     * click the JLabel
     */
    class MyMouseListenerForShowIcon implements MouseListener {

        /**
         *  the hideIcon if the user want to show the real password with letters
         * @return nothing
         * @param e -An event which indicates that a mouse action occurred in a component
         *
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            hideIconPassword.setVisible(false);
            showThePassword.setVisible(true);
            // showing the real password through letters
            firstPasswordField.setEchoChar((char) 0);

        }
        /**
         * @param e -Invoked when a mouse button has been pressed on a component.
         *  @return nothing
         *
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }

        /**
         * @param e -Invoked when a mouse button has been released on a component.
         *
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }

        /**
         * @param e -Invoked when the mouse enters a component
         * @return nothing
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }

        /**
         * @param e -Invoked when the mouse exits a component.
         * @return nothing
         */
        @Override
        public void mouseExited(MouseEvent e) {

        }

    }

    /*
     * In this class we want the user to let them show the password and hide if they
     * click the JLabel
     */

    class MyMouseListenerForHidePassword implements MouseListener {

        /**
         *  hiding the showPasswordLabel if the user want to show the hide password with asterisk
         * @return nothing
         * @param e -An event which indicates that a mouse action occurred in a component
         *
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            hideIconPassword.setVisible(true);
            showThePassword.setVisible(false);
            // putting an asterisk on user input password
            firstPasswordField.setEchoChar('*');
        }

        /**
         * @param e -Invoked when a mouse button has been pressed on a component.
         *  @return nothing
         *
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }
        /**
         * @param e -Invoked when a mouse button has been released on a component.
         * @return nothing
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        /**
         * @param e -Invoked when the mouse enters a component
         * @return nothing
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        /**
         * @param e -Invoked when the mouse exits a component.
         * @return nothing
         */

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

    /*
     * In this class we want the user to let them show the password and hide if they
     * click the JLabel
     */
    class MyMouseListenerForShowConfirmPassword implements MouseListener {

        /**
         *  hiding the showPasswordLabel if the user want to show the hide password with asterisk
         * @return nothing
         * @param e -An event which indicates that a mouse action occurred in a component
         *
         */
        @Override
        public void mouseClicked(MouseEvent e) {

            confirmPasswordHideLabel.setVisible(true);
            confirmPasswordShowLabel.setVisible(false);
            confirmPasswordField.setEchoChar('*');

        }
        /**
         * @param e -Invoked when a mouse button has been pressed on a component.
         *  @return nothing
         *
         */
        @Override
        public void mousePressed(MouseEvent e) {

        }
        /**
         * @param e -Invoked when a mouse button has been released on a component.
         * @return nothing
         */
        @Override
        public void mouseReleased(MouseEvent e) {

        }
        /**
         * @param e -Invoked when the mouse enters a component
         * @return nothing
         */
        @Override
        public void mouseEntered(MouseEvent e) {

        }
        /**
         * @param e -Invoked when the mouse exits a component.
         * @return nothing
         */
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

    /*
     * In this class we want the user to let them show the password and hide if they
     * click the JLabel
     */
    class MyMouseListenerforHideConfirmPassword implements MouseListener {

        /**
         *  the hide Icon if the user want to show the real password with letters in the confirmPassword
         * @return nothing
         * @param e -An event which indicates that a mouse action occurred in a component
         *
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            confirmPasswordHideLabel.setVisible(false);
            confirmPasswordShowLabel.setVisible(true);
            confirmPasswordField.setEchoChar((char) 0);
        }
        /**
         * @param e -Invoked when a mouse button has been pressed on a component.
         *  @return nothing
         *
         */
        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        /**
         * @param e -Invoked when a mouse button has been released on a component.
         * @return nothing
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        /**
         * @param e -Invoked when the mouse enters a component
         * @return nothing
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        /**
         * @param e -Invoked when the mouse exits a component.
         * @return nothing
         */
        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

    /*
     * In this class we want the user to let them go back to the JFrame so that we
     * can make sure that the System has a good user experience
     */
    class MyMouseListenerForGoBackToLogIn implements MouseListener {

        /**
         * Letting user to go back in the log in screen if he clicked the JLabel click here to log in
         * @param -e Invoked when the mouse button has been clicked (pressed and released) on a component.
         * @return nothingg
         */
        @Override
        public void mouseClicked(MouseEvent e) {
            // Instanting the LogInForm to display
            LogInForm login = new LogInForm();
            // disposing the SignUpForm JFrame
            dispose();
            // setting log in to visible
            login.setVisible(true);
            // setting the log in to Center
            login.setLocationRelativeTo(null);
            // setting the JFrame not to be resizable
            login.setResizable(false);
        }

        /**
         * @param e -Invoked when a mouse button has been pressed on a component.
         *  @return nothing
         *
         */
        @Override
        public void mousePressed(MouseEvent e) {
            // TODO Auto-generated method stub

        }

        /**
         * @param e -Invoked when a mouse button has been released on a component.
         * @return nothing
         */
        @Override
        public void mouseReleased(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        /**
         * @param e -Invoked when the mouse enters a component
         * @return nothing
         */
        @Override
        public void mouseEntered(MouseEvent e) {
            // TODO Auto-generated method stub

        }
        /**
         * @param e -Invoked when the mouse exits a component.
         * @return nothing
         */

        @Override
        public void mouseExited(MouseEvent e) {
            // TODO Auto-generated method stub

        }

    }

    /*
     * In this class we want to to check the last validation of each the JComponents
     * so that we can be sure on account information that was given is valid on the
     * requirements of the System.
     */

    class MyActionListenerForSignup implements ActionListener {

        /**
         * Setting a Regular expression of what is the valid input and we checked it
         * using if statement
         *
         * @returns The source of this pattern
         *
         */
        private final String NAME_REGEX = Pattern.compile("^[a-z,A-Z ,.'-]+$", Pattern.CASE_INSENSITIVE).pattern();

        /**
         * Setting a regular expersion of username so that we want to be sure the valid
         * input that will be inputed in database.
         */
        private static final String USERNAME_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[*.!@$%^&(){}\\[\\]:;<>,?/\\\\~_+-=|]).{8,}$";

        /**
         * Setting a regular expersion of password so that we want to be sure the valid
         * input and secured password by implementing a long password with having digits
         * and special characters
         */
        private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";


        /**
         * Waiting an events that when user click the log in and we provide
         * logic inside of this method to determine the valid personal account
         * @return nothing
         */
        @Override
        public void actionPerformed(ActionEvent e) {

            firstPasswordTextField = String.valueOf(firstPasswordField.getText());
            confirmPassowrdField = String.valueOf(confirmPasswordField.getText());
            /*
             * We used try and catch here to validate the invalid input because we want only
             * numbers on age, day managerMonth and year
             *
             */
            try {
                // Parsing the String to Integer
                managerByAge = Integer.parseInt(managerAge);
                dayOfBirth = Integer.parseInt(managerDayRegister);
                // Parsing the String to Integer
                managerMonth = Integer.parseInt(managerMonthRegister);
                // Parsing the String to Integer
                yearCreateAccount = Integer.parseInt(managerYearRegister);

                // checking if the firstname doesn't maches on name_regex
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)) {
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstnameTextField.setText("");
                }
                // checking if the managerLastname doesn't maches on name_regex
                if (e.getSource() == buttonSignUp && !managerLastnNameTextField.matches(NAME_REGEX)) {
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                // checking if the firstPasswordTextField doesn't matches on password patttern
                if (e.getSource() == buttonSignUp && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                // checking if the managerMiddleInitial doesn't matches on name_regex
                if (e.getSource() == buttonSignUp && !managerMiddleInitial.matches(NAME_REGEX)) {
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                // checking if the managerMiddleInitial doesn't matches name regex and the
                // firstPasswordTextField
                if (e.getSource() == buttonSignUp && !managerMiddleInitial.matches(NAME_REGEX)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }
                // checking if the managerUsername doesn't matches on username_regex
                if (e.getSource() == buttonSignUp && !managerUsername.matches(USERNAME_REGEX)) {
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }

                // checking if the managerUsername doesn't matches on username_regex and
                // firstPasswordTextField doesn't matches on password pattern
                if (e.getSource() == buttonSignUp && !managerUsername.matches(USERNAME_REGEX)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }
                // checking if the firstpassword doens't match to the confirm password and also
                // the firstpassword doesn't maches on password pattern.
                if (e.getSource() == buttonSignUp && (!firstPasswordTextField.equals(confirmPassowrdField)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN))) {
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                // checking the manager age if it doens't match to the required age
                if (e.getSource() == buttonSignUp && (managerByAge < 17 || managerByAge > 61)) {
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                /*
                 * checking the manager age if it doens't match to the required age and the
                 * first password doesn't match to password pattern giving them a feedback red
                 * border to perceived that the input was wrong
                 */
                if (e.getSource() == buttonSignUp && (managerByAge < 17 || managerByAge > 61)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }
                /*
                 * checking the manager age if it doens't match to the required age first
                 * password and confirm password and firstpassword doesn't match to password
                 * pattern and giving them a feedback red border to perceived that the input was
                 * wrong.
                 */
                if (e.getSource() == buttonSignUp
                        && (managerByAge < 17 || managerByAge > 61 && !Arrays.equals(firstPassword, confirmPassword)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN))) {
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }
                /*
                 * checking the birthday if it doens't match to the required number of days and
                 * the age first password and confirm password and giving them a feedback red
                 * border to perceived that the input was wrong.
                 */
                if (e.getSource() == buttonSignUp && (dayOfBirth > 31 || dayOfBirth == 0)
                        && (managerByAge < 17 || managerByAge > 61) && !Arrays.equals(firstPassword, confirmPassword)) {
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                }

                /*
                 * checking the birthday if it doens't match to the required number of days and
                 * the age first password and confirm password and firstpassword doesn't match
                 * to password pattern and giving them a feedback red border to perceived that
                 * the input was wrong.
                 */
                if (e.getSource() == buttonSignUp && (dayOfBirth > 31 || dayOfBirth == 0)
                        && (managerByAge < 17 || managerByAge > 61) && !Arrays.equals(firstPassword, confirmPassword)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                // Checking the month if its matches on the required month only and give a
                // feedback
                if (e.getSource() == buttonSignUp && managerMonth > 12) {
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }

                /*
                 * Checking if the firstPassword doesn't match to the password pattern and the
                 * manager month is match to the required number of month and
                 */
                if (e.getSource() == buttonSignUp && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                // Checking if the birth year doens't match to the required year
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                /*
                 * Checking if the birth year doens't match to the required year first password
                 * doens't match to password pattern.
                 */
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                /*
                 * Checking if the birth year doens't match to the required year and also the
                 * month.
                 */
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)
                        && managerMonth > 12) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }

                /*
                 * Checking if the birth year doens't match to the required year ,the month and
                 * firstpasword doens't match on the password pattern.
                 */
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)
                        && managerMonth > 12 && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                /*
                 * Checking if the birth year doens't match to the required year and
                 * firstPassword, confirmpassword doesn't match
                 */
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)
                        && !Arrays.equals(firstPassword, confirmPassword)) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                }
                /*
                 * Checking if the birth year doens't match to the required year and
                 * firstPassword, confirmpassword doesn't match and firstPassword doesn't match
                 * on password pattern.
                 */
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)
                        && !Arrays.equals(firstPassword, confirmPassword)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }
                /*
                 * Checking if the birth year doens't match to the required year and
                 * firstPassword, confirmpassword doesn't match and the month
                 */
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)
                        && !Arrays.equals(firstPassword, confirmPassword) && managerMonth > 12) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                /*
                 * Checking if the birth year doens't match to the required year and
                 * firstPassword, confirmpassword doesn't match , also month and lastly
                 * firstPasswordTextField pattern
                 *
                 */
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)
                        && !Arrays.equals(firstPassword, confirmPassword) && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }

                /*
                 * Checking if the birth year doens't match to the required year and
                 * firstPassword, confirmpassword doesn't match and lastly
                 * firstPasswordTextField pattern
                 */
                if (e.getSource() == buttonSignUp && (yearCreateAccount < 1959 || yearCreateAccount > 2002)
                        && !Arrays.equals(firstPassword, confirmPassword)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }

                // Checking if the managerByAge doesn't match required age and also the number
                // of days & month.
                if (e.getSource() == buttonSignUp && (managerByAge < 17 && dayOfBirth > 31 && managerMonth > 12)) {
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                // Checking if the managerByAge doesn't match required age and also the number
                // of days, month firstPasswordTextField
                if (e.getSource() == buttonSignUp && (managerByAge < 17 && dayOfBirth > 31 && managerMonth > 12)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }

                /*
                 * Checking the age if it's doesn't match to the required age and also the month
                 * and also firstPassword,confirm password check they dont match.
                 */
                if (e.getSource() == buttonSignUp && (managerByAge < 17 && dayOfBirth > 31)
                        && (!Arrays.equals(firstPassword, confirmPassword)) && managerMonth > 12) {
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                /*
                 * Checking if the age, day doesn't match to the required age,month and also the
                 * firstpassword and confirm password
                 */
                if (e.getSource() == buttonSignUp && (managerByAge < 17 && dayOfBirth > 31)
                        && (!Arrays.equals(firstPassword, confirmPassword)) && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }

                /*
                 * Checking if the age, day, firstPasswordField doesn't match to the required
                 * age,month, passwordpattern and also the firstpassword and confirm password
                 */
                if (e.getSource() == buttonSignUp && (managerByAge < 17 && dayOfBirth > 31)
                        && (!Arrays.equals(firstPassword, confirmPassword)) && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }

                // Checking lastname,middleInitial,Firstname if they dont match on the required
                // pattern.
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }
                // Checking lastname,middleInitial,Firstname,FirstPassword if they dont match on
                // the required pattern
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,usernameFirstPassword if they dont
                 * match on the required pattern input
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && !managerUsername.matches(USERNAME_REGEX)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,usernameFirstPassword if they dont
                 * match on the required pattern input and also the managerMonth
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && !managerUsername.matches(USERNAME_REGEX) && managerMonth > 12) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                /*
                 * Checking lastname,middleInitial,Firstname,FirstPassword if they dont match on
                 * the required pattern input and also the managerMonth and age
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61) && managerMonth > 12) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,FirstPassword,age if they dont
                 * match to the required input.
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61) && managerMonth > 12) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,,FirstPassword,age and the month if
                 * they dont match to the required pattern and data limit
                 */

                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61) && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,age if they dont match to
                 * the required pattern and limit of numbers
                 */

                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,age and the month if they
                 * dont match to the required pattern and limit of numbers
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 18 || managerByAge > 61)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,FirstPassword,age if they
                 * dont match to the required pattern and limit of numbers
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 18 || managerByAge > 61)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,FirstPassword,age and
                 * month if they dont match to the required pattern and limit of numbers
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61) && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,FirstPassword,age if they dont
                 * match to the required pattern and limit of numbers and lastly the first
                 * password and confirm password
                 */

                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61)
                        && (!Arrays.equals(firstPassword, confirmPassword))) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                }
                /*
                 * Checking lastname,middleInitial,Firstname,FirstPassword,age if they dont
                 * match to the required pattern and limit of numbers and lastly the first
                 * password and confirm password
                 */

                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61) && (!Arrays.equals(firstPassword, confirmPassword))
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }

                /*
                 * Checking lastname,middleInitial,Firstname,age and the month if they dont
                 * match to the required pattern and limit of numbers and lastly the first
                 * password and confirm password
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61)
                        && (!Arrays.equals(firstPassword, confirmPassword) && managerMonth > 12)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }
                /*
                 * Checking lastname,middleInitial,Firstname,age,FirstPasswordField and the
                 * month if they dont match to the required pattern and limit of numbers and
                 * lastly the first password and confirm password
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerMiddleInitial.matches(NAME_REGEX)
                        && (managerByAge < 17 || managerByAge > 61)
                        && (!Arrays.equals(firstPassword, confirmPassword) && managerMonth > 12)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));
                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,age and the month if they
                 * dont match to the required pattern and limit of numbers and lastly the first
                 * password and confirm password
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (!Arrays.equals(firstPassword, confirmPassword))) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");

                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,age and the month if they
                 * dont match to the required pattern and limit of numbers and lastly the first
                 * password and confirm password
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (!Arrays.equals(firstPassword, confirmPassword)) && managerMonth > 12) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,age and the month if they
                 * dont match to the required pattern and limit of numbers and lastly the first
                 * password and confirm password
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (!Arrays.equals(firstPassword, confirmPassword)) && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,age and the month if they
                 * dont match to the required pattern and limit of numbers
                 *
                 */

                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPassword,age and the
                 * month if they dont match to the required pattern and limit of numbers
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0) && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPasswordage and the
                 * month if they dont match to the required pattern and limit of numbers
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0) && managerMonth > 12) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,age and the month if they
                 * dont match to the required pattern and limit of numbers
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0) && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,age if they dont match to
                 * the required pattern and limit of numbers the firstPassword, confirmpassowrd
                 * doesn't match
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0) && (!Arrays.equals(firstPassword, confirmPassword))) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPassword,age,day if
                 * they dont match to the required pattern and limit of numbers the
                 * firstPassword, confirmpassowrd doesn't match
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0) && (!Arrays.equals(firstPassword, confirmPassword)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN))) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPassword,age,year,day
                 * if they dont match to the required pattern and limit of numbers
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0)
                        && (yearCreateAccount <= 1960 || yearCreateAccount > 2002)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPassword,age,day and
                 * year if they dont match to the required pattern and limit of numbers
                 *
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0)
                        && (yearCreateAccount <= 1960 || yearCreateAccount > 2002)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPassword,age,day and
                 * the month, year if they dont match to the required pattern and limit of
                 * numbers and the firstPasswordField and confirmPassword doesn't match
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0) && (!Arrays.equals(firstPassword, confirmPassword))
                        && managerMonth > 12) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPassword,age,day and
                 * the month, year if they dont match to the required pattern and limit of
                 * numbers and the firstPasswordField and confirmPassword doesn't match
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0) && (!Arrays.equals(firstPassword, confirmPassword)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN))
                        && managerMonth > 12) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPassword,age,day and
                 * the month, year if they dont match to the required pattern and limit of
                 * numbers and the firstPasswordField and confirmPassword doesn't match
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0)
                        && (yearCreateAccount <= 1960 || yearCreateAccount > 2002)
                        && (!Arrays.equals(firstPassword, confirmPassword))) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,firstPassword,age,day and
                 * year if they dont match to the required pattern and limit of numbers and the
                 * firstPasswordField and confirmPassword doesn't match
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0)
                        && (yearCreateAccount <= 1960 || yearCreateAccount > 2002)
                        && (!Arrays.equals(firstPassword, confirmPassword)
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN))) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setText("");
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                    confirmPasswordField.setText("");
                }
                /*
                 * Checking lastname,middleInitial,Firstname,username,age,day and year if they
                 * dont match to the required pattern and limit of numbers and the
                 * firstPasswordField and confirmPassword doesn't match
                 */

                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0)
                        && (yearCreateAccount <= 1960 || yearCreateAccount > 2002)
                        && (!Arrays.equals(firstPassword, confirmPassword)) && managerMonth > 12) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    ageTextField.setText("");
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

                }

                /*
                 * Checking lastname,middleInitial,Firstname,username,age,day and the month year
                 * if they dont match to the required pattern and limit of numbers and the
                 * firstPasswordField and confirmPassword doesn't match
                 */
                if (e.getSource() == buttonSignUp && !managerFirstNameTextField.matches(NAME_REGEX)
                        && !managerLastnNameTextField.matches(NAME_REGEX) && !managerUsername.matches(USERNAME_REGEX)
                        && !managerMiddleInitial.matches(NAME_REGEX) && (managerByAge < 17 || managerByAge > 61)
                        && (dayOfBirth > 31 || dayOfBirth == 0)
                        && (yearCreateAccount <= 1960 || yearCreateAccount > 2002)
                        && (!Arrays.equals(firstPassword, confirmPassword)) && managerMonth > 12
                        && !firstPasswordTextField.matches(PASSWORD_PATTERN)) {
                    firstnameTextField.setText("");
                    firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    lastnameTextField.setText("");
                    lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    middleInitialTextField.setText("");
                    middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    usernameTextField.setText("");
                    usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    firstPasswordField.setText("");
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    confirmPasswordField.setText("");
                    ageTextField.setText("");
                    dayTextField.setText("");
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    monthTextField.setText("");
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    yearTextField.setText("");
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                    requiredPassword.setText("Please follow the required length of password and format");
                    requiredPassword.setForeground(Color.red);
                    requiredPassword.setBorder(new LineBorder(new Color(255, 0, 0)));

                }

            } catch (NumberFormatException error) {
                // Catching all the exceptions that could disrupt our code
                ageTextField.setText(" ");
                ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                ageTextField.setText("");
                dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                dayTextField.setText("");
                yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                yearTextField.setText("");
                buttonSignUp.setEnabled(false);
            }

            try {
                /*
                 * In this nested if we only the valid input of the sign up JFrame and we
                 * provide a feedback green border because green can be considered as correct by
                 * it's correct and also we provided JOptionPane with message that the account
                 * was successfully created and we provide also limit on the age,year dayOfBirth
                 *
                 */
                if ((e.getSource() == buttonSignUp && firstPasswordTextField.equals(confirmPassowrdField)
                        && firstPasswordTextField.matches(PASSWORD_PATTERN)
                        && (managerByAge >= 18 || managerByAge <= 60)
                        && (yearCreateAccount >= 1960 || yearCreateAccount <= 2002)
                        && (dayOfBirth <= 31 || dayOfBirth != 0) && managerMonth <= 12)) {
                    // providing a feedback green border to perceived by the user that input was
                    // correct.
                    firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                    confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                    ageTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                    yearTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                    monthTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                    dayTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                    phoneNumberTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));

                    /*
                     * In this inner if statement we validate the
                     * managerFirstname,Lastname,MiddleInitial managerUsername by using regular
                     * expression.
                     */
                    if ((e.getSource() == buttonSignUp && managerFirstNameTextField.matches(NAME_REGEX))
                            && (managerLastnNameTextField.matches(NAME_REGEX)
                            && managerMiddleInitial.matches(NAME_REGEX)
                            && managerUsername.matches(USERNAME_REGEX))) {

                        // providing a feedaback green border to perceived that it's correct input.
                        firstnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                        lastnameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                        middleInitialTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                        usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(0, 255, 102)));
                        buttonSignUp.setEnabled(true);

                        // Preparing the SQL Queries INSERT INTO and specifying the parameters to be
                        // inserted
                        String sql = "INSERT INTO manager_account (firstname,lastname, middleInitial,username,password, confirmPassword,phonenumber,masterKeyID,age,day,month,year)"
                                + "VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";

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
                        java.sql.Connection connection = DriverManager.getConnection(url, user, password);

                        // Instantiating PreparedStatement and passing the sql queries

                        PreparedStatement preparedStatement = connection.prepareStatement(sql);
                        /**
                         * Passing the value on the database so that it will be put in the database
                         *
                         * @param parameterIndex - the index
                         * @param second         - the value
                         * @exception SQLException -if parameterIndex does not correspond to a parameter
                         *                         marker in the SQL statement;
                         */
                        preparedStatement.setString(1, managerFirstNameTextField);
                        preparedStatement.setString(2, managerLastnNameTextField);
                        preparedStatement.setString(3, managerMiddleInitial);
                        preparedStatement.setString(4, managerUsername);
                        preparedStatement.setString(5, firstPasswordTextField);
                        preparedStatement.setString(6, confirmPassowrdField);
                        preparedStatement.setString(7, managerMasterKeyId);
                        preparedStatement.setString(8, managerPhoneNumber);
                        preparedStatement.setInt(9, managerByAge);
                        preparedStatement.setInt(10, dayOfBirth);
                        preparedStatement.setInt(11, managerMonth);
                        preparedStatement.setInt(12, yearCreateAccount);

                        int x = preparedStatement.executeUpdate();

                        JOptionPane.showMessageDialog(null, "The account was successfully created", "Successfully",
                                JOptionPane.INFORMATION_MESSAGE);

                    }
                }
                // Catching all Exception that could disrupt the system
            } catch (Exception error) {
                determineTheDuplicates.setText("Username is already taken input a unique username!");
                determineTheDuplicates.setForeground(Color.red);
                determineTheDuplicatesPassword
                        .setText("The password was duplicated in our system please input another passsword!");
                determineTheDuplicatesPassword.setForeground(Color.red);
                usernameTextField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                firstPasswordField.setText("");
                firstPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));
                confirmPasswordField.setText("");
                confirmPasswordField.setBorder(new MatteBorder(2, 2, 2, 2, (Color) new Color(255, 0, 0)));

            }

        }
    }
}