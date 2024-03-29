package assignments.assignment3.user.menu;
import assignments.assignment1.NotaGenerator;
import assignments.assignment3.nota.Nota;
import assignments.assignment3.nota.NotaManager;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.nota.service.SetrikaService;
import assignments.assignment3.user.Member;
import static assignments.assignment3.nota.NotaManager.cal;
import static assignments.assignment3.nota.NotaManager.fmt;
import java.util.Arrays;
public class MemberSystem extends SystemCLI {
    /**
     * Memproses pilihan dari Member yang masuk ke sistem ini sesuai dengan menu specific.
     *
     * @param choice -> pilihan pengguna.
     * @return true jika user log.
     */
    @Override
    
    protected boolean processChoice(int choice) {
        boolean logout = false;
        //jika memilih ingin laundry
        if (choice ==1){
            System.out.println("Masukan paket laundry: ");
            //menampilkan paket dan harga
            NotaGenerator.showPaket();
            //meminta input pake yg diinginkan
            String paket = in.nextLine();
            String paketLower = paket.toLowerCase();
            //looping untuk meminta input terus sampai input paket yang dimasukkan valid
            Boolean jalan = true;
            while(jalan){
                //jika input paket sudah valid looping akan berhenti
                if (paketLower.equals("express") || paketLower.equals("fast") || paketLower.equals("reguler")){
                    break;
                }
                //menampilkan menu paket dengan memanggil method showPaket dan meminta ulang input user
                else if (paketLower.equals("?")){
                    displayMenu();
                    System.out.println("Masukkan paket laundry: ");
                    paket = in.nextLine();
                    paketLower = paket.toLowerCase();
                }
                //jika input paket belum valid
                else{
                    System.out.printf("Paket %s tidak diketahui\n", paket);
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    System.out.println("Masukkan paket laundry: ");
                    paket = in.nextLine();
                    paketLower = paket.toLowerCase();
                    //menampilkan menu paket dengan memanggil method showPaket dan meminta ulang input user
                    if (paket.equals("?") ){
                        displayMenu();;
                        System.out.println("Masukkan paket laundry: ");
                        paket = in.nextLine();
                        paketLower = paket.toLowerCase();
                    }   
                }
            }
            System.out.println("Masukkan berat cucian Anda [Kg]: ");
            //meminta input berat
            String stringBerat = in.nextLine();
            int berat;
            //looping untuk meminta input berat sampai valid
            while(true){
                //mengecek apakah berat yang dimasukkan adalah integer, jika iya looping berhenti
                try{
                    berat = Integer.parseInt(stringBerat);
                    if (berat <= 0){
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        stringBerat = in.nextLine();
                    }
                    else{
                        break;
                    }
                }
                //jika bukan integer minta input ulang
                catch (Exception e){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    stringBerat = in.nextLine();
                }
            }
            //jika berat kurang dari 2 kg, berat dianggap 2 kg
            if (berat < 2){
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }
            String tanggalMasuk = fmt.format(cal.getTime());
            Nota nota = new Nota(loginMember, berat, paket, tanggalMasuk );
            System.out.print("Apakah kamu ingin cucianmu disetrika oleh staff professional kami?\nHanya tambah 1000 / kg\n[Ketik x untuk tidak mau]: ");
            String mauSetrika = in.nextLine();
            //jika ingin disterika
            if (!mauSetrika.toLowerCase().equals("x")){
                //membuat objek service setrika
                SetrikaService setrika = new SetrikaService();
                //menambahkan ke list service
                nota.addService(setrika);
            }
            System.out.print("Mau diantar oleh kurir kami? Dijamin aman dan cepat sampai tujuan!\nCuma 2000 / 4kg, kemudian 500 / kg\n[Ketik x untuk tidak mau]: ");
            String mauAntar = in.nextLine();
            //jika mau diantar
            if (!mauAntar.toLowerCase().equals("x")){
                //membuat objek service antar
                AntarService antar = new AntarService();
                //menambahkan ke list service
                nota.addService(antar);
            }
            //menambahkan nota ke list nota
            NotaManager.addNota(nota);
            loginMember.addNota(nota);
            nota.calculateHarga();
            System.out.println("Nota berhasil dibuat!");
       
        }
        else if (choice ==2){
            //list semua nota
            Nota [] arrayNota = NotaManager.notaList;
            for (int i = 0; i < arrayNota.length; i++){
                //id member yang sedang login
                String idMember = loginMember.getId();
                //jika id member yang sedang login sesuai dengan id member yang memiliki nota
                if (idMember.equals(arrayNota[i].getMember().getId())){
                    System.out.println(arrayNota[i]);
                }

            }

        }
        //logout
        else if (choice ==3){
            logout = true;
        }
        return logout;
    }

    /**
     * Displays specific menu untuk Member biasa.
     */
    //menu khusus untuk member
    @Override
    protected void displaySpecificMenu() {
        System.out.println("1. Saya ingin laundry");
        System.out.println("2. Lihat detail nota saya");
        System.out.println("3. Logout");
    }

    /**
     * Menambahkan Member baru ke sistem.
     *
     * @param member -> Member baru yang akan ditambahkan.
     */
    public void addMember(Member member) {
        memberList= Arrays.copyOf(memberList, memberList.length+1);
        memberList [memberList.length-1] = member;
    }
}