//import package yang dibutuhkan
package assignments.assignment2;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Scanner;
import java.util.ArrayList; 
import assignments.assignment1.NotaGenerator;
import static assignments.assignment1.NotaGenerator.*;

public class MainMenu {
    private static final Scanner input = new Scanner(System.in);
    private static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    private static Calendar cal = Calendar.getInstance();
    //membuat array list nota
    private static ArrayList<Nota> notaList = new ArrayList<Nota>();
    //membuat array list id
    private static ArrayList<String> idList = new ArrayList<String>();
    //membuat array list member
    private static ArrayList<Member> memberList = new ArrayList<Member>(); 
    //counter id nota
    private static int idNota = 0;

    public static void main(String[] args) {
        boolean isRunning = true;
        while (isRunning) {
            printMenu();
            System.out.print("Pilihan : ");
            String command = input.nextLine();
            System.out.println("================================");
            switch (command){
                case "1" -> handleGenerateUser();
                case "2" -> handleGenerateNota();
                case "3" -> handleListNota();
                case "4" -> handleListUser();
                case "5" -> handleAmbilCucian();
                case "6" -> handleNextDay();
                case "0" -> isRunning = false;
                default -> System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
        System.out.println("Terima kasih telah menggunakan NotaGenerator!");
    }

    private static void handleGenerateUser() {
        System.out.println("Masukkan nama Anda:");
                //meminta input nama
                String nama = input.nextLine();
                //memvalidasi jika nama dikosongkan
                while (nama.length() == 0){
                    System.out.println("Nama tidak boleh kosong");
                    System.out.println("Masukkan nama Anda:");
                    nama = input.nextLine();
                }
                System.out.println("Masukkan nomor handphone Anda:");
                //meminta input nomor HP
                String nomorHandphone = input.nextLine();
                //memvalidasi jika nomor Handphone dikosongkan
                while (nomorHandphone.length() == 0){
                    System.out.println("Field nomor hp hanya menerima digit.");
                    System.out.println("Masukkan nomor handphone Anda:");
                    nomorHandphone = input.nextLine();
                }

                 //memvalidasi nomor hp yang dimasukkan user
                int cekNomor;
                do {
                    cekNomor = 1;
                    //mengiterasi setiap digit pada nomor HP untuk memastikan bahwa semuanya angka
                    for (int i = 0; i < nomorHandphone.length(); i++){ 
                        if (!(Character.isDigit(nomorHandphone.charAt(i)))){
                            System.out.println("Field nomor hp hanya menerima digit.");
                            String nomorHandphoneBaru = input.nextLine();
                            nomorHandphone = nomorHandphoneBaru;
                            cekNomor = 0;
                            break;
                        }
                    }
                } while (cekNomor == 0);

                // membuat objek member
                Member member = new Member(nama, nomorHandphone);
                //mengecek apakah id member sudah ada sebelumnya atau belum
                if (idList.indexOf(member.getId()) == -1){
                    //jika belum tambahkan ke list
                    memberList.add(member);
                    idList.add(member.getId());
                    System.out.printf("Berhasil membuat member dengan ID %s!\n", member.getId());
                }
                //jika id sudah ada sebelumnya
                else{
                    System.out.printf("Member dengan nama %s dan nomor hp %s sudah ada!\n", nama, nomorHandphone);
                }

    }

    private static void handleGenerateNota() {
        System.out.println("Masukan ID member: ");
        //meminta input id member
        String idMember = input.nextLine();
        int diskon = 0;
        //jika id member ada
        if (idList.indexOf(idMember) != -1){
            //akses objek member bersangkutan dari array list
            Member member = memberList.get(idList.indexOf(idMember));
            //tambahkan bonus couter
            member.setBonusCounter();
            //ambil jumlah bonus counter
            diskon = member.getBonusCounter();
            System.out.print("Masukkan paket laundry: \n");
            //meminta input paket laundry yang diinginkan
            String paket = input.nextLine();
            //mengubah input menjadi huruf kecil semua
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
                    showPaket();
                    System.out.println("Masukkan paket laundry: ");
                    paket = input.nextLine();
                    paketLower = paket.toLowerCase();
                }
                //jika input paket belum valid
                else{
                    System.out.printf("Paket %s tidak diketahui\n", paket);
                    System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                    System.out.println("Masukkan paket laundry: ");
                    paket = input.nextLine();
                    paketLower = paket.toLowerCase();
                    //menampilkan menu paket dengan memanggil method showPaket dan meminta ulang input user
                    if (paket.equals("?") ){
                        NotaGenerator.showPaket();
                        System.out.println("Masukkan paket laundry: ");
                        paket = input.nextLine();
                        paketLower = paket.toLowerCase();
                    }   
                }
            }
            System.out.println("Masukkan berat cucian Anda [Kg]: ");
            //meminta input berat
            String stringBerat = input.nextLine();
            int berat;
            //looping untuk meminta input berat sampai valid
            while(true){
                //mengecek apakah berat yang dimasukkan adalah integer, jika iya looping berhenti
                try{
                    berat = Integer.parseInt(stringBerat);
                    if (berat <= 0){
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        stringBerat = input.nextLine();
                    }
                    else{
                        break;
                    }
                }
                //jika bukan integer minta input ulang
                catch (Exception e){
                    System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                    stringBerat = input.nextLine();
                }
            }
            //jika berat kurang dari 2 kg, berat dianggap 2 kg
            if (berat < 2){
                System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                berat = 2;
            }

            //menyimpan tanggal dari calender, men=mformat lalu simpan ke variabel tanggal masuk
            String tanggalMasuk = fmt.format(cal.getTime());
            System.out.println("Berhasil menambahkan nota!");
            System.out.printf("[ID Nota = %d]\n",idNota);
            //buat objek nota baru
            Nota nota = new Nota(member, paketLower, berat, tanggalMasuk, idNota);
            //tambahkan ke array list nota
            notaList.add(nota);
            //print nota dengan memanggil method generateNota
            System.out.println(NotaGenerator.generateNota(idMember, paket, berat, tanggalMasuk,diskon));
            //jika nota belum siap
            if (!nota.getIsReady()){
                System.out.println("Status      	: Belum bisa diambil :(");
            }
            //tambahkan counter id nota
            idNota += 1;

        }

        //jika id tidak ditemukan
        else{
            System.out.printf("Member dengan ID %s tidak ditemukan!\n", idMember);
        }
    }

    private static void handleListNota() {
        String status;
        System.out.printf("Terdaftar %d nota dalam sistem.\n", notaList.size());
        //menentukan status setiap nota dan mengeprintnya
        for (Nota i : notaList){
            if (i.getIsReady()){
                status = "Sudah dapat diambil!";
            }
            else{
                status = "Belum bisa diambil :(";
            }
            System.out.printf("- [%d] Status      	: %s\n", i.getIdNota(), status);
        }
        System.out.println();
    }

    private static void handleListUser() {
        System.out.printf("Terdaftar %d member dalam sistem\n", memberList.size());
        // mengeprint setiap member yg ada di array list member
        for (Member i : memberList){
            System.out.printf("- %s : %s\n", i.getId(),i.getNama());
        }
    }

    private static void handleAmbilCucian() {
        System.out.println("Masukan ID nota yang akan diambil: ");
        //meminta input id nota
        String idNota = input.nextLine();
        int idNotaInt;
        //mengecek apakah id nota berbentuk angka dan tidak negatif
        while (true){
            try {
                idNotaInt = Integer.parseInt((idNota));
                if (idNotaInt <0){
                    System.out.println("ID nota berbentuk angka!");
                    idNota = input.nextLine();
                }
                else {
                    break;
                }
            }
            
            catch(Exception e){
                System.out.println("ID nota berbentuk angka!");
                idNota = input.nextLine();
            }
        }
        
        boolean idAda = false;
        //menginisiasi objek nota baru
        Nota nota = new Nota();
        //mengecek apakah id Nota ada atau tidak
        for (Nota i : notaList){
            if (i.getIdNota() == idNotaInt){
                idAda = true;
                nota = i;
                break;
            }
        }

        //jika nota dengan id tersebut ada
        if (idAda){
            //jika sudah siap, hapus nota
            if (nota.getIsReady()){
                notaList.remove(nota);
                System.out.printf("Nota dengan ID %d berhasil diambil!\n", idNotaInt);
            }
            //jika belum siap
            else{
                System.out.printf("Nota dengan ID %d gagal diambil!\n", idNotaInt);
            }
        }
        //jika nota dengan id tersebut tidak ada
        else{
            System.out.printf("Nota dengan ID %d tidak ditemukan!\n", idNotaInt);
        }
    }
    
    private static void handleNextDay() {
        System.out.println("Dek Depe tidur hari ini... zzz...");
        //menambahkan 1 hari ke tanggal
        cal.add(Calendar.DATE, 1);
        //mencari nota yang sisa hari pengerjaan <=0
        for (Nota i : notaList){
            //mengurangi sisa hari pengerjaan
            i.setSisaHariPengerjaan();
            if (i.getSisaHariPengerjaan() <= 0){
                System.out.printf("Laundry dengan nota ID %d sudah dapat diambil!\n", i.getIdNota());
                i.setIsReady();
            }
        }

        System.out.println("Selamat pagi dunia!");
        System.out.println("Dek Depe: It's CuciCuci Time.");
    }

    private static void printMenu() {
        System.out.println("\nSelamat datang di CuciCuci!");
        System.out.printf("Sekarang Tanggal: %s\n", fmt.format(cal.getTime()));
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate User");
        System.out.println("[2] Generate Nota");
        System.out.println("[3] List Nota");
        System.out.println("[4] List User");
        System.out.println("[5] Ambil Cucian");
        System.out.println("[6] Next Day");
        System.out.println("[0] Exit");
    }

}
