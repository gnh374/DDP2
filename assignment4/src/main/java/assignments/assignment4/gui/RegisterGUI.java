package assignments.assignment4.gui;

import assignments.assignment3.LoginManager;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterGUI extends JPanel {
    public static final String KEY = "REGISTER";
    private JPanel mainPanel;
    private JLabel nameLabel;
    private JTextField nameTextField;
    private JLabel phoneLabel;
    private JTextField phoneTextField;
    private JLabel passwordLabel;
    private JPasswordField passwordField;
    private JButton registerButton;
    private LoginManager loginManager;
    private JButton backButton;

    public RegisterGUI(LoginManager loginManager) {
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
        nameLabel = new JLabel("Masukkan nama Anda: ");
        nameTextField = new JTextField();
        nameTextField.setPreferredSize(new Dimension(500, 15));
        phoneLabel= new JLabel("Masukkan nomor handphone Anda: ");
        phoneTextField = new JTextField();
        phoneTextField.setPreferredSize(new Dimension(500,15));
        passwordLabel = new JLabel("Masukkan password Anda: ");
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(500,15));
        GridBagConstraints c1 = new GridBagConstraints();
        c1.gridx = 0;
        c1.gridy =0;
        c1.weightx = 1;
        c1.weighty = 0.5;
        GridBagConstraints c2 = new GridBagConstraints();
        c2.gridx = 0;
        c2.gridy =1;
        c2.weightx = 1;
        c2.weighty = 0.5;
        GridBagConstraints c3 = new GridBagConstraints();
        c3.gridx = 0;
        c3.gridy =2;
        c3.weightx = 1;
        c3.weighty = 0.5;
        GridBagConstraints c4 = new GridBagConstraints();
        c4.gridx = 0;
        c4.gridy =3;
        c4.weightx = 1;
        c4.weighty = 0.5;
        GridBagConstraints c5 = new GridBagConstraints();
        c5.gridx = 0;
        c5.gridy =4;
        c5.weightx = 1;
        c5.weighty = 0.5;
        GridBagConstraints c6 = new GridBagConstraints();
        c6.gridx = 0;
        c6.gridy =5;
        c6.weightx = 1;
        c6.weighty = 0.5;

        mainPanel.add(nameLabel,c1);
        mainPanel.add(nameTextField,c2);
        mainPanel.add(phoneLabel,c3);
        mainPanel.add(phoneTextField,c4);
        mainPanel.add(passwordLabel,c5);
        mainPanel.add(passwordField,c6);

        registerButton = new JButton("Register");
        c6.gridy=6;
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleRegister();
            }
        });
        mainPanel.add(registerButton,c6);

        backButton = new JButton("Kembali");
        c6.gridy=7;
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleBack();
            }
        });
        mainPanel.add(backButton,c6);


    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        //TODO
        MainFrame mainFrame = MainFrame.getInstance();
        phoneTextField.setText("");
        nameTextField.setText("");
        passwordField.setText("");
        mainFrame.navigateTo(HomeGUI.KEY);
    }

    /**
    * Method untuk mendaftarkan member pada sistem.
    * Akan dipanggil jika pengguna menekan "registerButton"
    * */
    private void handleRegister() {
        // TODO
        String nama = nameTextField.getText();
        String noHp = phoneTextField.getText();
        char[] pw = passwordField.getPassword();
        String password = new String(pw);
        if (nama.equals("") | noHp.equals("") |password.equals("")){
            JOptionPane.showMessageDialog(null,"Semua field di atas wajib diisi", "Information", JOptionPane.ERROR_MESSAGE);
            
        }
        else{
            boolean nomorValid = true;
                //mengiterasi setiap digit pada nomor HP untuk memastikan bahwa semuanya angka
                for (int i = 0; i < noHp.length(); i++){ 
                    if (!(Character.isDigit(noHp.charAt(i)))){
                        nomorValid=false;
                        break;
                    }
                }
                if (!nomorValid){
                    JOptionPane.showMessageDialog(null,"Nomor Handphone harus berisi angka","information" ,JOptionPane.ERROR_MESSAGE);
                    phoneTextField.setText("");
                }
                else{
                    Member registeredMember = loginManager.register(nama, noHp, password);
                    //jika tidak bisa register karena id sudah ada
                    if(registeredMember == null){
                        JOptionPane.showMessageDialog(null, String.format("User dengan nama %s dan nomor hp %s sudah ada!",nama, noHp), "Information", JOptionPane.ERROR_MESSAGE);
                        handleBack();
                        nameTextField.setText("");
                        passwordField.setText("");
                        phoneTextField.setText("");
                    }
                    //jika bisa register
                    else{
                        JOptionPane.showMessageDialog(null, String.format("Berhasil membuat user dengan ID %s!", registeredMember.getId()), "Information", JOptionPane.INFORMATION_MESSAGE);
                        handleBack();
                        nameTextField.setText("");
                        passwordField.setText("");
                        phoneTextField.setText("");
                    }

                    
                }
                

       
        }

        
    }
    
}
