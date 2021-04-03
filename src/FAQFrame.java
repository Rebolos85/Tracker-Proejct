import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseAdapter;


/**
 * <h1>FAORulesAndRegulationOfTransportHubCompany!</h1>
 * The FAOFrame program is mostly simply display the headers 
 * and definition of our FAQ
 * 
 * @author Roberto A Rebolos & Romel Alcantara & John Tex Lear Ortaliz Rara
 * @since 2020 -19 -11
 *
 */
public class FAQFrame extends JFrame {
	
	
	
    /*
     * mainPanel
     */
    private JPanel contentPane;


    

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
    private Image logoOfCompany = new ImageIcon(SignUpForm.class.getResource("resources/logo.png")).getImage().getScaledInstance(90, 90, Image.SCALE_SMOOTH);

    private Image backgroundLogo = new ImageIcon(SignUpForm.class.getResource("resources/rightPanel.png")).getImage().getScaledInstance(850, 800, Image.SCALE_SMOOTH);

    
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
                    FAQFrame frame = new FAQFrame();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /*
     * No argument constructor and We initialized the objects
     *
     */
    public FAQFrame() {
        myFrameLayoutFAQ();



        logoAttach();
        definitionOfTransportHub();

        affliatedOrganization();
        determineTheScopeOfCompany();

        forgotMyPassword();
        masterKeyDefinition();
        rulesOfCreatingAnAccount();







    }

    /**setting the layout of my frame and
     *  the mainPanel
     *  @return nothing
     */
    public void myFrameLayoutFAQ() {

        setBounds(100, 100, 700, 800);
        contentPane = new JPanel();
        contentPane.setBorder(new LineBorder(new Color(75, 0, 130)));
        setContentPane(contentPane);
        contentPane.setLayout(null);
    }




    /** setting the layout for the logo of the company
     *  the mainPanel
     *  @return nothing
     */
    public void logoAttach() {

        JLabel LogoLabel = new JLabel("");
        LogoLabel.setVerticalTextPosition(SwingConstants.TOP);
        LogoLabel.setVerticalAlignment(SwingConstants.TOP);
        LogoLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        LogoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        LogoLabel.setBounds(557, 26, 101, 97);
        LogoLabel.setIcon(new ImageIcon(logoOfCompany));
        contentPane.add(LogoLabel);
    }


    /** These are the JLabel and Definition of the Transporthub Company
     * @return nothing
     */
    public void definitionOfTransportHub() {

        JLabel faoHeaderLabel = new JLabel("FAQ");
        faoHeaderLabel.setForeground(Color.WHITE);
        faoHeaderLabel.setFont(new Font("Berlin Sans FB", Font.BOLD, 20));
        faoHeaderLabel.setBounds(63, 77, 46, 20);
        contentPane.add(faoHeaderLabel);

        JLabel TransporthubLabel = new JLabel("What is TransportHub?\r\n\r\n");
        TransporthubLabel.setForeground(Color.WHITE);
        TransporthubLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        TransporthubLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        TransporthubLabel.setHorizontalAlignment(SwingConstants.LEFT);
        TransporthubLabel.setBounds(63, 108, 189, 33);
        contentPane.add(TransporthubLabel);


        JLabel firstDefinitionLabel = new JLabel("-TransportHub is a program developed in partner with a certain business to handle delivery  management ");
        firstDefinitionLabel.setForeground(Color.WHITE);
        firstDefinitionLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        firstDefinitionLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        firstDefinitionLabel.setVerticalAlignment(SwingConstants.TOP);
        firstDefinitionLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        firstDefinitionLabel.setBounds(63, 141, 627, 20);
        contentPane.add(firstDefinitionLabel);

        JLabel secondDefinitionLabel = new JLabel("system, this program allows customers of said business to provide transparency during transaction, to view");
        secondDefinitionLabel.setForeground(Color.WHITE);
        secondDefinitionLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        secondDefinitionLabel.setVerticalAlignment(SwingConstants.TOP);
        secondDefinitionLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        secondDefinitionLabel.setBounds(63, 160, 611, 20);
        contentPane.add(secondDefinitionLabel);

        JLabel thirdDefinitionLabel = new JLabel("the current status of packages delivered by supported manufacturer in all parts of the country.");
        thirdDefinitionLabel.setForeground(Color.WHITE);
        thirdDefinitionLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        thirdDefinitionLabel.setVerticalAlignment(SwingConstants.TOP);
        thirdDefinitionLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        thirdDefinitionLabel.setBounds(63, 179, 570, 20);
        contentPane.add(thirdDefinitionLabel);
    }

    /** This method is the affliated with a business JLabels and definition
     * @return nothing
     */
    public void affliatedOrganization() {
        JLabel affiliatedHeaderLabel = new JLabel("Do I Need to be Affiliated with a Business or Organization to use this?");
        affiliatedHeaderLabel.setForeground(Color.WHITE);
        affiliatedHeaderLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        affiliatedHeaderLabel.setVerticalAlignment(SwingConstants.TOP);
        affiliatedHeaderLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        affiliatedHeaderLabel.setBounds(63, 223, 570, 20);
        contentPane.add(affiliatedHeaderLabel);

        JLabel secondAffliatedLabel = new JLabel("Customers can view the status of their order, as long as they have their order id number");
        secondAffliatedLabel.setForeground(Color.WHITE);
        secondAffliatedLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        secondAffliatedLabel.setVerticalAlignment(SwingConstants.TOP);
        secondAffliatedLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        secondAffliatedLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        secondAffliatedLabel.setBounds(60, 254, 611, 20);
        contentPane.add(secondAffliatedLabel);

        JLabel thirdLabel = new JLabel("However editing information would require an account.");
        thirdLabel.setForeground(Color.WHITE);
        thirdLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        thirdLabel.setVerticalAlignment(SwingConstants.TOP);
        thirdLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        thirdLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        thirdLabel.setBounds(63, 274, 570, 20);
        contentPane.add(thirdLabel);
    }
       /**
        * This is the scope of the company JLabels and description
        * @return nothing
        */
    public void determineTheScopeOfCompany() {
        JLabel philippinesHeader = new JLabel("Can I use this even outside of the Philippines?");
        philippinesHeader.setForeground(Color.WHITE);
        philippinesHeader.setVerticalTextPosition(SwingConstants.BOTTOM);
        philippinesHeader.setVerticalAlignment(SwingConstants.TOP);
        philippinesHeader.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        philippinesHeader.setBounds(63, 328, 381, 20);
        contentPane.add(philippinesHeader);

        JLabel firstDefPHLabel = new JLabel("-When using the program, it is not limited to the location of the person using however it is personalized to");
        firstDefPHLabel.setForeground(Color.WHITE);
        firstDefPHLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        firstDefPHLabel.setVerticalAlignment(SwingConstants.TOP);
        firstDefPHLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        firstDefPHLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        firstDefPHLabel.setBounds(63, 359, 603, 20);
        contentPane.add(firstDefPHLabel);

        JLabel secondPHLabel = new JLabel("all delivery WITHIN the country exclusively.");
        secondPHLabel.setForeground(Color.WHITE);
        secondPHLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        secondPHLabel.setVerticalAlignment(SwingConstants.TOP);
        secondPHLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        secondPHLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        secondPHLabel.setBounds(63, 379, 263, 20);
        contentPane.add(secondPHLabel);
    }

    /**
     * This is the rules and regulations of you forgot your password
     * @return nothing
     */
    public void forgotMyPassword() {
        JLabel ForgotMyPasswordLabel = new JLabel("I Forgot my Password?");
        ForgotMyPasswordLabel.setForeground(Color.WHITE);
        ForgotMyPasswordLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        ForgotMyPasswordLabel.setVerticalAlignment(SwingConstants.TOP);
        ForgotMyPasswordLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        ForgotMyPasswordLabel.setBounds(63, 433, 189, 20);
        contentPane.add(ForgotMyPasswordLabel);

        JLabel firstLabel = new JLabel("-One of the prime reasons TransportHub is known for is its security therefore to avoid any more losses of ");
        firstLabel.setForeground(Color.WHITE);
        firstLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        firstLabel.setVerticalAlignment(SwingConstants.TOP);
        firstLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        firstLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        firstLabel.setBounds(63, 464, 603, 20);
        contentPane.add(firstLabel);

        JLabel secondLabel = new JLabel("and or breaches once you forget your password you must create a new one nevertheless all data is");
        secondLabel.setForeground(Color.WHITE);
        secondLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        secondLabel.setVerticalAlignment(SwingConstants.TOP);
        secondLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        secondLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        secondLabel.setBounds(63, 481, 587, 20);
        contentPane.add(secondLabel);

        JLabel thirdLabelPassword = new JLabel(" still stored within the program but you will need the Master Key to either access or modify orders.");
        thirdLabelPassword.setForeground(Color.WHITE);
        thirdLabelPassword.setVerticalTextPosition(SwingConstants.BOTTOM);
        thirdLabelPassword.setVerticalAlignment(SwingConstants.TOP);
        thirdLabelPassword.setHorizontalTextPosition(SwingConstants.RIGHT);
        thirdLabelPassword.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        thirdLabelPassword.setBounds(63, 499, 603, 20);
        contentPane.add(thirdLabelPassword);
    }

    /**
     * Definition of what is master key and the Header
     * @return nothing
     */
    public void masterKeyDefinition() {
        JLabel masterKeyHeaderLabel = new JLabel("What Is My Master Key?");
        masterKeyHeaderLabel.setForeground(Color.WHITE);
        masterKeyHeaderLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        masterKeyHeaderLabel.setVerticalAlignment(SwingConstants.TOP);
        masterKeyHeaderLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        masterKeyHeaderLabel.setBounds(63, 531, 208, 20);
        contentPane.add(masterKeyHeaderLabel);

        JLabel firstMasterKeyLabel = new JLabel("-The Master Key is provided to a certain individual one at a time, either the current/past manager would ");
        firstMasterKeyLabel.setForeground(Color.WHITE);
        firstMasterKeyLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        firstMasterKeyLabel.setVerticalAlignment(SwingConstants.TOP);
        firstMasterKeyLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        firstMasterKeyLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        firstMasterKeyLabel.setBounds(63, 562, 603, 20);
        contentPane.add(firstMasterKeyLabel);

        JLabel secondMasterKeyLabel = new JLabel("provide it to you or any supervisor present.");
        secondMasterKeyLabel.setForeground(Color.WHITE);
        secondMasterKeyLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        secondMasterKeyLabel.setVerticalAlignment(SwingConstants.TOP);
        secondMasterKeyLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        secondMasterKeyLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        secondMasterKeyLabel.setBounds(63, 582, 263, 20);
        contentPane.add(secondMasterKeyLabel);
    }

    /**
     * This is the Header & Definition
     * of rules and regulations on creating an account
     * @return nothing
     */
    public void rulesOfCreatingAnAccount() {
        JLabel accountLabel = new JLabel("Can Anyone Make an Account?");
        accountLabel.setForeground(Color.WHITE);
        accountLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        accountLabel.setVerticalAlignment(SwingConstants.TOP);
        accountLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 20));
        accountLabel.setBounds(63, 618, 263, 20);
        contentPane.add(accountLabel);

        JLabel eligibleLabel = new JLabel("-Anyone eligible within the partnered manufacturer can make an account however NO DUPLICATES ");
        eligibleLabel.setForeground(Color.WHITE);
        eligibleLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        eligibleLabel.setVerticalAlignment(SwingConstants.TOP);
        eligibleLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        eligibleLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        eligibleLabel.setBounds(63, 649, 603, 20);
        contentPane.add(eligibleLabel);

        JLabel sharePasswordLabel = new JLabel(", NO SHARING OF PASSWORD is strongly advised furthermore a Manager is highly suited to handle");
        sharePasswordLabel.setForeground(Color.WHITE);
        sharePasswordLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        sharePasswordLabel.setVerticalAlignment(SwingConstants.TOP);
        sharePasswordLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        sharePasswordLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        sharePasswordLabel.setBounds(63, 670, 603, 20);
        contentPane.add(sharePasswordLabel);

        JLabel dataLabel = new JLabel("the data.");
        dataLabel.setForeground(Color.WHITE);
        dataLabel.setVerticalTextPosition(SwingConstants.BOTTOM);
        dataLabel.setVerticalAlignment(SwingConstants.TOP);
        dataLabel.setHorizontalTextPosition(SwingConstants.RIGHT);
        dataLabel.setFont(new Font("Berlin Sans FB", Font.PLAIN, 14));
        dataLabel.setBounds(63, 695, 68, 20);
        contentPane.add(dataLabel);

        JLabel lblNewLabel = new JLabel("");
        lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        lblNewLabel.setHorizontalAlignment(SwingConstants.LEFT);
        lblNewLabel.setBounds(-41, 0, 731, 761);
        lblNewLabel.setIcon(new ImageIcon(backgroundLogo));
        contentPane.add(lblNewLabel);
    }








}