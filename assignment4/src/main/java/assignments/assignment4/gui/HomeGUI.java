package assignments.assignment4.gui;

import assignments.assignment3.nota.NotaManager;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static assignments.assignment3.nota.NotaManager.toNextDay;

public class HomeGUI extends JPanel {
    public static final String KEY = "HOME";
    private JLabel titleLabel;
    private JLabel dateLabel;
    private JPanel mainPanel;
    private JButton loginButton;
    private JButton registerButton;
    private JButton toNextDayButton;

    public HomeGUI(){
        super(new BorderLayout()); // Setup layout

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
        //set up layout
        GridBagConstraints constraints = new GridBagConstraints();
        String tanggal = NotaManager.fmt.format(NotaManager.cal.getTime());
        constraints.weightx = 1.0;
        constraints.weighty = 0.5;
        constraints.gridx = 0;
        titleLabel = new JLabel("Selamat datang di CuciCuci System!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        constraints.gridy = 0;
        mainPanel.add(titleLabel,constraints);
        dateLabel = new JLabel(String.format("Hari ini: %s", tanggal));
        constraints.gridy = 4;
        mainPanel.add(dateLabel,constraints);;
        loginButton = new JButton("Login");
        constraints.gridy = 1;
        //jika login button di click akan menjalankan method handleToLogin()
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleToLogin();
            }
        });
        mainPanel.add(loginButton,constraints);
        registerButton = new JButton("Register");
        constraints.gridy = 2;
        //jika register button di click akan menjalankan method handleToRegister()
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleToRegister();
            }
        });
        mainPanel.add(registerButton,constraints);
        toNextDayButton = new JButton("Next Day");
        constraints.gridy = 3;
        //jika Next Day button di click akan menjalankan method handleNextDay()
        toNextDayButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleNextDay();
            }
        });
        mainPanel.add(toNextDayButton,constraints);
        
    }

    /**
     * Method untuk pergi ke halaman register.
     * Akan dipanggil jika pengguna menekan "registerButton"
     * */
    //navigate to register panel
    private static void handleToRegister() {
        MainFrame layarUtama = MainFrame.getInstance();
        layarUtama.navigateTo(RegisterGUI.KEY);
    }

    /**
     * Method untuk pergi ke halaman login.
     * Akan dipanggil jika pengguna menekan "loginButton"
     * */
    //navigate to login panel
    private static void handleToLogin() {
        MainFrame layarUtama = MainFrame.getInstance();
        layarUtama.navigateTo(LoginGUI.KEY);
    }

    /**
     * Method untuk skip hari.
     * Akan dipanggil jika pengguna menekan "toNextDayButton"
     * */
    //menambahkann hari dan menjalankan method NotaManager.toNextDay()
    private void handleNextDay() {
        JOptionPane.showMessageDialog(null, "Kamu tidur hari ini...zzz...","Information", JOptionPane.INFORMATION_MESSAGE);
        NotaManager.toNextDay();
        dateLabel.setText(String.format("Hari ini: %s", NotaManager.fmt.format(NotaManager.cal.getTime())));
    }
}
