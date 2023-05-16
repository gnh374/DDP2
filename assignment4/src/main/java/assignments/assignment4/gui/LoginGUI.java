package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import javax.xml.validation.ValidatorHandler;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginGUI extends JPanel {
    public static final String KEY = "LOGIN";
    private JPanel mainPanel;
    private JLabel idLabel;
    private JTextField idTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JButton backButton;
    private LoginManager loginManager;

    public LoginGUI(LoginManager loginManager) {
        super(new BorderLayout()); // Setup layout, Feel free to make any changes
        this.loginManager = loginManager;

        // Set up main panel, Feel free to make any changes
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        initGUI();

        add(mainPanel, BorderLayout.CENTER);
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.gridx = 0;
        idLabel = new JLabel("Masukkan ID Anda: ");
        constraints.gridy = 0;
        mainPanel.add( idLabel, constraints);
        idTextField = new JTextField("");
        idTextField.setPreferredSize(new Dimension(500, 15));
        constraints.gridy = 1;
        mainPanel.add(idTextField, constraints);
        passwordLabel= new JLabel("Masukkan password Anda: ");
        constraints.gridy = 2;
        mainPanel.add(passwordLabel, constraints);
        passwordField = new JPasswordField("");
        constraints.gridy = 3;
        passwordField.setPreferredSize(new Dimension(500, 15));
        mainPanel.add(passwordField, constraints);
        loginButton = new JButton("Login");
        constraints.gridy = 4;
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleLogin();
            }
        });
        mainPanel.add(loginButton, constraints);
        backButton = new JButton("Kembali");
        constraints.gridy = 5;
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleBack();
            }
        });
        mainPanel.add(backButton, constraints);
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        MainFrame layarUtama = MainFrame.getInstance();
        idTextField.setText("");
        passwordField.setText("");
        layarUtama.navigateTo(HomeGUI.KEY);
    }

    /**
     * Method untuk login pada sistem.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    public void reset(){
        idTextField.setText("");
        passwordField.setText("");
    }
    private void handleLogin() {
        MainFrame layarUtama = MainFrame.getInstance();
        SystemCLI systemCLI = loginManager.getSystem(idTextField.getText());
        //jika id tidak ada
        if(systemCLI == null){
            JOptionPane.showMessageDialog(null,"ID or password invalid", "Information", JOptionPane.ERROR_MESSAGE);
            idTextField.setText("");
            passwordField.setText("");
        }
        else{
            layarUtama.login(idTextField.getText(), String.valueOf(passwordField.getPassword()));
    
        } 
    }
}
