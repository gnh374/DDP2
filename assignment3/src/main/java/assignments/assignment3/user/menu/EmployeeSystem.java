package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.user.Employee;
import assignments.assignment3.user.Member;

import static assignments.assignment3.nota.NotaManager.notaList;

public class EmployeeSystem extends SystemCLI {

    /**
     * Membuat object baru EmployeeSystem dan mendaftarkan Employee pada CuciCuci
     */
    public EmployeeSystem() {
        memberList = new Member[]{
                new Employee("Dek Depe", "akuDDP"),
                new Employee("Depram", "musiktualembut"),
                new Employee("Lita Duo", "gitCommitPush"),
                new Employee("Ivan Hoshimachi", "SuamiSahSuisei"),
        };
    }

    /**
     * Memproses pilihan dari employee yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    protected boolean processChoice(int choice) {
        boolean logout = false;
        //jika ingin mencuci
        if (choice == 1){
            System.out.printf("Stand back! %s beginning to nyuci!\n", loginMember.getNama());
            //mengiterate semua nota yang ada dalam list nota
            for (Nota nota : NotaManager.notaList){
                for (int i = 0 ; i < nota.getServices().length; i++){
                    //jika nota sudah selesai
                    if (nota.isDone()){
                        System.out.printf("Nota %d : Sudah selesai.\n", nota.getId());
                        break;
                    }
                    //jika belum selesai berarti ada service yg belum selesai
                    else if (!nota.getServices()[i].isDone()){
                                //
                                System.out.printf("Nota %d : %s\n", nota.getId(),nota.getServices()[i].doWork());
                                //jika ini merupakan service terakhir
                                if (i == nota.getServices().length-1){
                                    //nota sudah selesai
                                    nota.setIsDone();
                                }
                                break;
                            }
                    }
            }
        }
        //menampilkan list semua nota dan statusnya
        else if (choice ==2){
            for (Nota nota : NotaManager.notaList){
                System.out.println(nota.getNotaStatus());
            }
        }
        //logout
        else if (choice == 3){
            logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */

     //menu untuk employee
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}