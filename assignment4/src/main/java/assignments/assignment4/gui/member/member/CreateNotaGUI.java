package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import assignments.assignment4.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class CreateNotaGUI extends JPanel {
    public static final String KEY = "CREATE_NOTA";
    private JLabel paketLabel;
    private JComboBox<String> paketComboBox;
    private JButton showPaketButton;
    private JLabel beratLabel;
    private JTextField beratTextField;
    private JCheckBox setrikaCheckBox;
    private JCheckBox antarCheckBox;
    private JButton createNotaButton;
    private JButton backButton;
    private final SimpleDateFormat fmt;
    private final Calendar cal;
    private final MemberSystemGUI memberSystemGUI;

    public CreateNotaGUI(MemberSystemGUI memberSystemGUI) {
        setLayout(new BorderLayout());
        this.memberSystemGUI = memberSystemGUI;
        this.fmt = NotaManager.fmt;
        this.cal = NotaManager.cal;

        // Set up main panel, Feel free to make any changes
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        JPanel pilihan = new JPanel(new GridLayout(2, 3));
        paketLabel = new JLabel("Paket Laundry: ");
        pilihan.add( paketLabel);
        String [] paket = {"Express", "Fast", "Reguler"};
        paketComboBox = new JComboBox<>(paket);
        paketComboBox.setSelectedIndex(0);
        pilihan.add(paketComboBox);
        showPaketButton = new JButton("Show Paket");
        showPaketButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                showPaket();
            }
            
        }
        );
        pilihan.add(showPaketButton);
        beratLabel = new JLabel("Berat cucian (Kg): ");
        pilihan.add(beratLabel);
        beratTextField = new JTextField();
        pilihan.add(beratTextField);
        add(pilihan, BorderLayout.NORTH);

        JPanel checkBox= new JPanel(new GridLayout(2, 1));
        setrikaCheckBox = new JCheckBox("Tambah Setrika Service (1000/kg)");
        checkBox.add(setrikaCheckBox);
        antarCheckBox = new JCheckBox("Tambah Antar Service (2000 /4kg pertama, kemudian 500/kg)");
        checkBox.add(antarCheckBox);
        add(checkBox, BorderLayout.CENTER);


        JPanel button = new JPanel(new GridLayout(2, 1));
        createNotaButton = new JButton("Buat Nota");
        createNotaButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                createNota();
            }
        });
        button.add(createNotaButton);
        backButton = new JButton("Kembali");
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleBack();
            }
        });
        button.add(backButton);
        add(button, BorderLayout.SOUTH);
        // TODO
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    private void showPaket() {
        String paketInfo = """
                        <html><pre>
                        +-------------Paket-------------+
                        | Express | 1 Hari | 12000 / Kg |
                        | Fast    | 2 Hari | 10000 / Kg |
                        | Reguler | 3 Hari |  7000 / Kg |
                        +-------------------------------+
                        </pre></html>
                        """;

        JLabel label = new JLabel(paketInfo);
        label.setFont(new Font("monospaced", Font.PLAIN, 12));
        JOptionPane.showMessageDialog(this, label, "Paket Information", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Method untuk melakukan pengecekan input user dan mendaftarkan nota yang sudah valid pada sistem.
     * Akan dipanggil jika pengguna menekan "createNotaButton"
     * */
    private void createNota() {
        String paket = (String)paketComboBox.getSelectedItem();
        int berat;
        boolean beratKurang = false;
        try {
            berat = Integer.parseInt(beratTextField.getText());
            
            if (berat<0){
                throw new NumberFormatException();
            }
            if (berat <2){
                beratKurang = true;
                berat = 2;
            }
            String tanggal = this.fmt.format(this.cal.getTime());
            Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), berat, paket,tanggal );
            if (setrikaCheckBox.isSelected()){
                SetrikaService setrika = new SetrikaService();
                //menambahkan ke list service
                nota.addService(setrika);
            }
            if (antarCheckBox.isSelected()){
                //membuat objek service antar
                AntarService antar = new AntarService();
                //menambahkan ke list service
                nota.addService(antar);
            }
                NotaManager.addNota(nota);
                memberSystemGUI.getLoggedInMember().addNota(nota);
                nota.calculateHarga();
                if (beratKurang){
                    JOptionPane.showMessageDialog(this, "Cucian kurang dari 2kg. Maka akan dianggap sebagai 2 kg.", "Paket Information", JOptionPane.INFORMATION_MESSAGE);
                }
                JOptionPane.showMessageDialog(this, "Nota berhasil dibuat", "Paket Information", JOptionPane.INFORMATION_MESSAGE);
                paketComboBox.setSelectedIndex(0);
                beratTextField.setText("");
                setrikaCheckBox.setSelected(false);
                antarCheckBox.setSelected(false);
        }
        
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "Berat harus berupa angka", "Paket Information", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
        }
        

        // TODO
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        MainFrame.getInstance().navigateTo("MEMBER");
        // TODO
    }
}
