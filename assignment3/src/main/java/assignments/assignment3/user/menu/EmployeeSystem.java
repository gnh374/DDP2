package assignments.assignment3.user.menu;

import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.nota.service.LaundryService;
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
        if (choice == 1){
            System.out.printf("Stand back! %s beginning to nyuci!\n", loginMember.getNama());
            for (Nota nota : NotaManager.notaList){
                for (int i = 0 ; i <= nota.getServices().length; i++){
                    if (nota.isDone()){
                        System.out.printf("Nota %d : Sudah selesai.\n", nota.getId());
                        break;
                    }
                    else if (!nota.getServices()[i].isDone()){
                                System.out.printf("Nota %d : %s\n", nota.getId(),nota.getServices()[i].doWork());
                                if (i == nota.getServices().length-1){
                                    nota.setIsDone();
                                }
                                break;
                            }
                    }
            }
        }
        else if (choice ==2){
            for (Nota nota : NotaManager.notaList){
                System.out.println(nota.getNotaStatus());
            }
        }

        else if (choice == 3){
            logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Employee.
     */
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. It's nyuci time");
        System.out.println("2. Display List Nota");
        System.out.println("3. Logout");
    }
}