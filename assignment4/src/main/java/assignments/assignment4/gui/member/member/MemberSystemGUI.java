package assignments.assignment4.gui.member.member;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.user.Member;
import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.MainFrame;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class MemberSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "MEMBER";

    public MemberSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
       
    }

    @Override
    public String getPageName(){
        return KEY;
    }

    public Member getLoggedInMember(){
        return loggedInMember;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements MemberSystem.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // membuat button yg dibutuhkan pada member page
        JButton laundry = new JButton("Saya ingin laundry");
        JButton detailNota= new JButton("Lihat Detail Nota saya");
        return new JButton[]{laundry, detailNota
        };
    }

    /**
     * Method ini mensupply ActionListener korespondensi dengan button yang dibuat createButtons()
     * sesuai dengan requirements MemberSystem.
     *
     * @return Array of ActionListener.
     * */
    @Override
    protected ActionListener[] createActionListeners() {
        return new ActionListener[]{
                e -> createNota(),
                e -> showDetailNota(),
        };
    }

    /**
     * Menampilkan detail Nota milik loggedInMember.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    private void showDetailNota() {
        String printNota = "";
        //jika loggedin member belum membuat nota
        if ( loggedInMember.getNotaList().length==0){
            printNota = "Belum pernah Laundry di cucicuci nih :()";
            //jtext area dan scrollpane agar bisa discroll
            JTextArea textArea = new JTextArea(printNota);
            JScrollPane scrollPane = new JScrollPane(textArea);
            //size
            scrollPane.setPreferredSize(new java.awt.Dimension(300, 200));
            //dialog box belum pernah laundry
            JOptionPane.showMessageDialog(null, scrollPane,"Information", JOptionPane.INFORMATION_MESSAGE);
            
        }
        //jika sudah ada nota
        else{
            //masukkan info nota ke string
            for (Nota nota : loggedInMember.getNotaList()){
                printNota+=nota+"\n";
    
            }
            JTextArea textArea = new JTextArea(printNota);
            JScrollPane scrollPane = new JScrollPane(textArea);
            scrollPane.setPreferredSize(new java.awt.Dimension(300, 200));
            //dialog box isi nota
            JOptionPane.showMessageDialog(null, scrollPane,"Information", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    /**
     * Pergi ke halaman CreateNotaGUI.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    private void createNota() {
        MainFrame.getInstance().navigateTo("CREATE_NOTA");
        
    }

}
