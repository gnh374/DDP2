package assignments.assignment1;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class NotaGenerator {
    private static final Scanner input = new Scanner(System.in);

    /**
     * Method main, program utama kalian berjalan disini.
     */
    public static void main(String[] args) {
        boolean programJalan = true;
        while(programJalan){
            printMenu();
            System.out.print("Pilihan: ");
            String pilihan = input.nextLine();
            System.out.println("================================");
            
             
            if (pilihan.equals("0")){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                programJalan=false;
            }
            else if (pilihan.equals("1")){
                System.out.println("Masukkan nama Anda:");
                String nama = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHandphone = input.nextLine();

                System.out.printf("ID ANDA: %s\n",generateId(nama, nomorHandphone));
            }
            else if (pilihan.equals("2")){
                System.out.println("Masukkan nama Anda:");
                String nama = input.nextLine();
                System.out.println("Masukkan nomor handphone Anda:");
                String nomorHandphone = input.nextLine();

                String id = generateId(nama, nomorHandphone);
                System.out.print("Masukkan tanggal terima: \n");
                String tanggal = input.nextLine();
                System.out.print("Masukkan paket laundry: \n");
                String paket = input.nextLine();
                String paketLower = paket.toLowerCase();
                Boolean jalan = true;
                while(jalan){
                    if (paketLower.equals("express") || paketLower.equals("fast") || paketLower.equals("reguler")){
                        break;
                    }
                    else{
                        System.out.printf("Paket %s tidak diketahui\n",paket);
                        System.out.println("[ketik ? untuk mencari tahu jenis paket]");
                        System.out.println("Masukkan paket laundry: ");
                        paket = input.nextLine();
                        paketLower = paket.toLowerCase();
                        if (paket.equals("?") ){
                            showPaket();
                            System.out.println("Masukkan paket laundry: ");
                            paket = input.nextLine();
                            paketLower = paket.toLowerCase();
                        }   
                    }
                }
                System.out.println("Masukkan berat cucian Anda [Kg]: ");
                String stringBerat = input.nextLine();
                int berat;
                while(true){
                    try{
                            berat = Integer.parseInt(stringBerat);
                            break;
                    }
                    catch (NumberFormatException exception){
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        stringBerat = input.nextLine();
                        }
                }
                System.out.println("Nota Laundry");
                System.out.println(generateNota(id, paketLower, berat, tanggal)); 
               
            }
            else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
    }
    /**
     * Method untuk menampilkan menu di NotaGenerator.
     */
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }

    /**
     * Method untuk menampilkan paket.
     */
    private static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    /**
     * Method untuk membuat ID dari nama dan nomor handphone.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing
     *
     * @return String ID anggota dengan format [NAMADEPAN]-[nomorHP]-[2digitChecksum]
     */
    public static String generateId(String nama, String nomorHP){
        if (nama.indexOf(" ")!=-1){
            int index =nama.indexOf(" ");
            nama = nama.substring(0, index);
        }
        String namaUppercase = nama.toUpperCase();
        int cekNomor;
        do {
            cekNomor = 1;
            for (int i =0; i<nomorHP.length();i++){ 
                if (!(Character.isDigit(nomorHP.charAt(i)))){
                    System.out.println("Nomor hp hanya menerima digit");
                    String nomorHandphoneBaru = input.next();
                    input.nextLine();
                    nomorHP=nomorHandphoneBaru;
                    cekNomor=0;
                    break;
                }
            }
        } while (cekNomor==0);
        
        String id = namaUppercase+"-"+nomorHP;
        int checkSum=0;
        for (int i=0;i<id.length();i++){
            int ascii = id.charAt(i);
            if (ascii>=65 && ascii<=90){
                checkSum+=ascii-64;
               
            }
            else if(ascii>=48 && ascii<=57){
                checkSum+=ascii-48;
              
            }
            else{
                checkSum+=7;
            }
        
    }
    String checkSumFinal = Integer.toString(checkSum);
        if (checkSumFinal.length()==1){
            checkSumFinal="0"+checkSumFinal;
        }
    id = id+"-"+checkSumFinal;
    
    return id;
}
    /**
     *
     * Method untuk membuat Nota.
     * Parameter dan return type dari method ini tidak boleh diganti agar tidak mengganggu testing.
     *
     * @return string nota dengan format di bawah:
     *         <p>ID    : [id]
     *         <p>Paket : [paket]
     *         <p>Harga :
     *         <p>[berat] kg x [hargaPaketPerKg] = [totalHarga]
     *         <p>Tanggal Terima  : [tanggalTerima]
     *         <p>Tanggal Selesai : [tanggalTerima + LamaHariPaket]
     */

    public static String generateNota(String id, String paket, int berat, String tanggalTerima){
        // TODO: Implement generate nota sesuai soal.
        int lamaWaktu=0;
        long harga=0;

        if (paket.equals("express")){
            lamaWaktu=1;
            harga=12000; 
        }
        else if(paket.equals("fast")){
            lamaWaktu=2;
            harga=10000;
        }
        else if (paket.equals("reguler")){
            lamaWaktu=3;
            harga=7000;
        }

        if (berat<2){
            System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
            berat=2;
        }

        long totalHarga = harga*berat;
        String tanggalSelesai=hitungTanggal(tanggalTerima, lamaWaktu);
        String output ="ID    : " + id + "\nPaket : " + paket + "\nHarga :\n" + berat + " kg x " + harga + " = " + totalHarga + "\nTanggal Terima  : " + tanggalTerima + "\nTanggal Selesai : " + tanggalSelesai;
        
        return output;
    }
    public static String hitungTanggal(String tanggal, int lamaWaktu){
        DateTimeFormatter formatTaggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tanggalTerima = LocalDate.parse(tanggal,formatTaggal);
        LocalDate tanggalSelesai = tanggalTerima.plusDays(lamaWaktu);
        String form= tanggalSelesai.format(formatTaggal);
        return form;
    }

}


