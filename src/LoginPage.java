import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

public class LoginPage implements ActionListener {

    JFrame frame = new JFrame();
    JButton loginButton = new JButton("Login");
    JButton resetButton = new JButton("Reset");
    JTextField userIDField = new JTextField();
    JPasswordField userPasswordField = new JPasswordField();
    JLabel userIDLabel = new JLabel("userID:");
    JLabel userPasswordLabel = new JLabel("password:");
    JLabel messageLabel = new JLabel("THIS ");

    HashMap<String,String> loginInfo = new HashMap<>();

    LoginPage(HashMap<String,String> loginInfoOriginal){
        loginInfo = loginInfoOriginal;

        userIDLabel.setBounds(50,100,75,25);
        userPasswordLabel.setBounds(50,150,75,25);

        messageLabel.setFont(new Font(null,Font.BOLD, 25));
        messageLabel.setBounds(200,250,250,35);

        userIDField.setBounds(126,100,200,25);
        userPasswordField.setBounds(125,150,75,25);

      loginButton.setBounds(126,200,100,25);

        loginButton.addActionListener(this);
      /*
        resetButton.setBounds(226,200,100,25);
        resetButton.addActionListener(this);
       */


        frame.add(userIDField);
        frame.add(userPasswordField);
        frame.add(messageLabel);
        frame.add(userIDLabel);
        frame.add(userPasswordLabel);


        frame.add(loginButton);
      //  frame.add(resetButton);





        frame.getContentPane().setLayout(new GridBagLayout());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420,420);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
