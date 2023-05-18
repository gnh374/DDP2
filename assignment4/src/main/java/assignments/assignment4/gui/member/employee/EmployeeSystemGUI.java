package assignments.assignment4.gui.member.employee;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;

import assignments.assignment3.user.menu.SystemCLI;
import assignments.assignment4.gui.member.AbstractMemberGUI;

import javax.swing.*;
import java.awt.event.ActionListener;

public class EmployeeSystemGUI extends AbstractMemberGUI {
    public static final String KEY = "EMPLOYEE";

    public EmployeeSystemGUI(SystemCLI systemCLI) {
        super(systemCLI);
    }


    @Override
    public String getPageName(){
        return KEY;
    }

    /**
     * Method ini mensupply buttons yang sesuai dengan requirements Employee.
     * Button yang disediakan method ini BELUM memiliki ActionListener.
     *
     * @return Array of JButton, berisi button yang sudah stylize namun belum ada ActionListener.
     * */
    @Override
    protected JButton[] createButtons() {
        // membuat button untuk pilihan employee
        JButton nyuci = new JButton("It's Nyuci time");
        JButton displayNota= new JButton("Display Nota");
      
        
        return new JButton[]{ nyuci, displayNota
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
                e -> cuci(),
                e -> displayNota(),
        };
    }

    /**
     * Menampilkan semua Nota yang ada pada sistem.
     * Akan dipanggil jika pengguna menekan button pertama pada createButtons
     * */
    //method untuk display nota
    private void displayNota() {
        String print = "";
        //jika belum ada nota
        if (NotaManager.notaList.length ==0){
            JOptionPane.showMessageDialog(null,"Nota belum ada", "Information", JOptionPane.ERROR_MESSAGE);
        }
        //jika sudah ada nota
        else{
            for (Nota nota : NotaManager.notaList){
                print+=(nota.getNotaStatus()+"\n");
            }
            JOptionPane.showMessageDialog(null,print, "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }

    /**
     * Menampilkan dan melakukan action mencuci.
     * Akan dipanggil jika pengguna menekan button kedua pada createButtons
     * */
    //jika tombol it's cuci time di klik
    private void cuci() {
        JOptionPane.showMessageDialog(null, String.format("Stand back! %s beginning to nyuci!\n", loggedInMember.getNama()), "Information", JOptionPane.INFORMATION_MESSAGE);
        String notaPrint = "";
        //jika belum ada nota
        if (NotaManager.notaList.length ==0){
            JOptionPane.showMessageDialog(null,"Nothing to cuci here", "Information", JOptionPane.ERROR_MESSAGE);
        }
        //jika sudah ada nota
        else{
            //print semua nota yg ada
            for (Nota nota : NotaManager.notaList){
                for (int i = 0 ; i < nota.getServices().length; i++){
                    //jika nota sudah selesai
                    if (nota.isDone()){
                        notaPrint+= String.format("Nota %d : Sudah selesai.\n", nota.getId())+"\n";
                        break;
                    }
                    //jika belum selesai berarti ada service yg belum selesai
                    else if (!nota.getServices()[i].isDone()){
                                //
                                notaPrint+=String.format("Nota %d : %s\n", nota.getId(),nota.getServices()[i].doWork())+"\n";
                                //jika ini merupakan service terakhir
                                if (i == nota.getServices().length-1){
                                    //nota sudah selesai
                                    nota.setIsDone();
                                }
                                break;
                            }
                    }
            }
            JOptionPane.showMessageDialog(null,notaPrint, "Information", JOptionPane.INFORMATION_MESSAGE);
        }
        
    }
}
