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

        // Set up main panel
        initGUI();
    }

    /**
     * Method untuk menginisialisasi GUI.
     * Selama funsionalitas sesuai dengan soal, tidak apa apa tidak 100% sama.
     * Be creative and have fun!
     * */
    private void initGUI() {
        //setup layout
        JPanel pilihan = new JPanel(new GridLayout(2, 3));
        paketLabel = new JLabel("Paket Laundry: ");
        pilihan.add( paketLabel);
        String [] paket = {"Express", "Fast", "Reguler"};
        paketComboBox = new JComboBox<>(paket);
        paketComboBox.setSelectedIndex(0);
        pilihan.add(paketComboBox);
        showPaketButton = new JButton("Show Paket");
        //jika button Show Paket diklik maka akan menjalankan methode showPaket()
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
        //jika button Buat Nota di klik makan akan menjalankan methode creareNota()
        createNotaButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                createNota();
            }
        });
        button.add(createNotaButton);
        backButton = new JButton("Kembali");
         //jika button Buat Nota di klik makan akan menjalankan methode handleBack()
        backButton.addActionListener(new ActionListener() {
            public void actionPerformed (ActionEvent e){
                handleBack();
            }
        });
        button.add(backButton);
        add(button, BorderLayout.SOUTH);
    }

    /**
     * Menampilkan list paket pada user.
     * Akan dipanggil jika pengguna menekan "showPaketButton"
     * */
    //menampilkan menu paket
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
    //membuat nota baru
    private void createNota() {
        //menentukan paket yang dipilih user berdasarkan combobox
        String paket = (String) paketComboBox.getSelectedItem();
        int berat;
        //boolean untuk menentukan apakah berat kurang dari 2 atau tidak
        boolean beratKurang = false;
        //validasi berat
        //jika berat bukan angka/ berat negatif akan eror dan ditangkap
        try {
            berat = Integer.parseInt(beratTextField.getText());
            
            if (berat<=0){
                throw new NumberFormatException();
            }
            //jika berat kurang dari 2 akan dianggap 2
            if (berat <2){
                beratKurang = true;
                berat = 2;
            }
            String tanggal = this.fmt.format(this.cal.getTime());
            //membuat nota baru
            Nota nota = new Nota(memberSystemGUI.getLoggedInMember(), berat, paket,tanggal );
            //jika checkbox setrika di pilih
            if (setrikaCheckBox.isSelected()){
                SetrikaService setrika = new SetrikaService();
                //menambahkan ke list service
                nota.addService(setrika);
            }
            //jika checkbox antar di pilih
            if (antarCheckBox.isSelected()){
                //membuat objek service antar
                AntarService antar = new AntarService();
                //menambahkan ke list service
                nota.addService(antar);
            }   
            //menambahkan nota ke list nota pada NotaManager
                NotaManager.addNota(nota);
                //menambahkan nota ke list nota tiap member
                memberSystemGUI.getLoggedInMember().addNota(nota);
                //menghitung harga
                nota.calculateHarga();
                //jika berat <2 beri pemberitahuan
                if (beratKurang){
                    JOptionPane.showMessageDialog(this, "Cucian kurang dari 2kg. Maka akan dianggap sebagai 2 kg.", "Paket Information", JOptionPane.INFORMATION_MESSAGE);
                }
                //pemberitahuan nota berhasil dibuat
                JOptionPane.showMessageDialog(this, "Nota berhasil dibuat", "Paket Information", JOptionPane.INFORMATION_MESSAGE);
                //set semua textfield menjadu kososng, dan combobox kembali ke default
                paketComboBox.setSelectedIndex(0);
                beratTextField.setText("");
                setrikaCheckBox.setSelected(false);
                antarCheckBox.setSelected(false);
        }
        
        //jika terjadi eror, munculkan pemberitahuan dan kosongkan field berat
        catch (Exception e){
            JOptionPane.showMessageDialog(this, "Berat harus berupa angka", "Paket Information", JOptionPane.ERROR_MESSAGE);
            beratTextField.setText("");
        }
        

       
    }

    /**
     * Method untuk kembali ke halaman home.
     * Akan dipanggil jika pengguna menekan "backButton"
     * */
    private void handleBack() {
        //settingan dikembalikan kedefault dan kosong semua dan kembali ke member
        paketComboBox.setSelectedIndex(0);
        beratTextField.setText("");
        setrikaCheckBox.setSelected(false);
        antarCheckBox.setSelected(false);
        MainFrame.getInstance().navigateTo("MEMBER");
    }
}
