//mengimport package yang dibutuhkan
package assignments.assignment1;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;


public class NotaGenerator {
    //membuat objek scanneer untuk meminta input user
    private static final Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        //variabel untuk menentukan kapan looping berhenti
        boolean programJalan = true;
        while(programJalan){
            printMenu();
            System.out.print("Pilihan: ");
            //meminta input user fitur apa yang ingin diakses
            String pilihan = input.nextLine();
            System.out.println("================================");
            
            //looping berhenti
            if (pilihan.equals("0")){
                System.out.println("Terima kasih telah menggunakan NotaGenerator!");
                programJalan = false;

            //menjalankan method generateId
            }
            else if (pilihan.equals("1")){
                System.out.println("Masukkan nama Anda:");
                //meminta input nama
                String nama = input.nextLine();
                //memvalidasi jika nama dikosongkan
                while (nama.length()==0){
                    System.out.println("Nama tidak boleh kosong");
                    System.out.println("Masukkan nama Anda:");
                    nama = input.nextLine();
                }
                System.out.println("Masukkan nomor handphone Anda:");
                //meminta input nomor HP
                String nomorHandphone = input.nextLine();
                //memvalidasi jika nomor Handphone dikosongkan
                while (nomorHandphone.length()==0){
                    System.out.println("Nomor hp hanya menerima digit");
                    System.out.println("Masukkan nomor handphone Anda:");
                    nomorHandphone = input.nextLine();
                }
                

                //memanggil method generateId dan mengeprint id pelanggan
                System.out.printf("ID ANDA: %s\n",generateId(nama, nomorHandphone));
            }
            // menjalankan method generateNota
            else if (pilihan.equals("2")){
                System.out.println("Masukkan nama Anda:");
                //meminta input nama
                String nama = input.nextLine();
                //memvalidasi jika nama dikosongkan
                while (nama.length()==0){
                    System.out.println("Nama tidak boleh kosong");
                    System.out.println("Masukkan nama Anda:");
                    nama = input.nextLine();
                }
                System.out.println("Masukkan nomor handphone Anda:");
                //meminta input nomorHp
                String nomorHandphone = input.nextLine();
                //memvalidasi jika nomor Handphone dikosongkan
                while (nomorHandphone.length()==0){
                    System.out.println("Nomor hp hanya menerima digit");
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
                            System.out.println("Nomor hp hanya menerima digit");
                            String nomorHandphoneBaru = input.nextLine();
                            nomorHandphone = nomorHandphoneBaru;
                            cekNomor = 0;
                            break;
                        }
                    }
                } while (cekNomor == 0);


                //memanggil method generateId untuk membuat ID pelanggan
                String id = generateId(nama, nomorHandphone);
                System.out.print("Masukkan tanggal terima: \n");
                //meminta input tanggal terima
                String tanggal = input.nextLine();
                System.out.print("Masukkan paket laundry: \n");
                //meminta input paket laundry yang diinginkan
                String paket = input.nextLine();
                //mengubah input menjadi huruf kecil semua
                String paketLower = paket.toLowerCase();
                //looping untuk meminta input terus sampai input paket yang dimasukkan valid
                int diskon = input.nextInt();
                input.nextLine();
                Boolean jalan = true;
                while(jalan){
                    //jika input paket sudah valid looping akan berhenti
                    if (paketLower.equals("express") || paketLower.equals("fast") || paketLower.equals("reguler")){
                        break;
                    }
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
                            showPaket();
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
                //looping untuk meminta input berat sampau valid
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
                    catch (NumberFormatException exception){
                        System.out.println("Harap masukkan berat cucian Anda dalam bentuk bilangan positif.");
                        stringBerat = input.nextLine();
                        }
                }

      
                //jika berat kurang dari 2 kg, berat dianggap 2 kg
                if (berat < 2){
                    System.out.println("Cucian kurang dari 2 kg, maka cucian akan dianggap sebagai 2 kg");
                    berat = 2;
                }
                System.out.println("Nota Laundry");
                // memanggil method generateNota dan mengeprint returnntya
                System.out.println(generateNota(id, paket, berat, tanggal,diskon)); 
                
            }
            //jika user memasukkan perintah yang lain
            else{
                System.out.println("Perintah tidak diketahui, silakan periksa kembali.");
            }
        }
    }
    // method untuk mengeprint menu
    private static void printMenu() {
        System.out.println("Selamat datang di NotaGenerator!");
        System.out.println("==============Menu==============");
        System.out.println("[1] Generate ID");
        System.out.println("[2] Generate Nota");
        System.out.println("[0] Exit");
    }
    // method untuk mengeprin paket yang tersedia
    public static void showPaket() {
        System.out.println("+-------------Paket-------------+");
        System.out.println("| Express | 1 Hari | 12000 / Kg |");
        System.out.println("| Fast    | 2 Hari | 10000 / Kg |");
        System.out.println("| Reguler | 3 Hari |  7000 / Kg |");
        System.out.println("+-------------------------------+");
    }

    public static String generateId(String nama, String nomorHP){
        //mengambil kata pertama dari input nama user
        if (nama.indexOf(" ") != -1){
            int index =nama.indexOf(" ");
            nama = nama.substring(0, index);
        }
        // mengubah nama menjadi hurus kapital semua
        String namaUppercase = nama.toUpperCase();
        String id = namaUppercase+"-"+nomorHP;
        //menghitung chceckSum
        int checkSum = 0;
        for (int i = 0; i < id.length(); i++){
            //mengambil ascii dari setiap char
            int ascii = id.charAt(i);
            //untuk A-Z
            if (ascii >= 65 && ascii <= 90){
                checkSum += ascii - 64;
            //untuk 0-9   
            }
            else if(ascii >= 48 && ascii <= 57){
                checkSum += ascii - 48;
            //untuk lainnya  
            }
            else{
                checkSum += 7;
            }
        
    }
    //merubah integer checksum menjadi string dan menambahkan 0 jika hanya 1 digit
    String checkSumFinal = Integer.toString(checkSum);
        if (checkSumFinal.length() < 2){
            checkSumFinal = "0" + checkSumFinal;
        }
        else if (checkSumFinal.length() > 2){
            checkSumFinal = checkSumFinal.substring(checkSumFinal.length()-2, checkSumFinal.length());
        }
    //menggabungkan id sebelumnya dengan check sum
    id = id + "-" + checkSumFinal;
    
    return id;
}
    public static int jumlahHariPengerjaan(String paket){
        int lamaWaktu = 0;
        if (paket.equals("express")){
            lamaWaktu = 1;
        }
        else if(paket.equals("fast")){
            lamaWaktu = 2;
        }
        else if (paket.equals("reguler")){
            lamaWaktu = 3;
        }

        return lamaWaktu;
    }
    public static String generateNota(String id, String paket, int berat, String tanggalTerima, int diskon){
        int lamaWaktu = 0;
        long harga = 0;
        //mengubah paket menjadi huruf kecil semua
        String paketLower = paket.toLowerCase();
        //mengassign value lamaWaktu dan harga sesuai paket
        if (paketLower.equals("express")){
            lamaWaktu = 1;
            harga = 12000; 
        }
        else if(paketLower.equals("fast")){
            lamaWaktu = 2;
            harga = 10000;
        }
        else if (paketLower.equals("reguler")){
            lamaWaktu = 3;
            harga = 7000;
        }

        //memanggil method hitung tanggal untuk menentukan tanggal selesai
        String tanggalSelesai = hitungTanggal(tanggalTerima, lamaWaktu);
        long totalHarga=0;
        long setelahDiskon=0;
        String output;
        //menghitung total harga
        if (diskon % 3 ==0){
            totalHarga = harga * berat;
            setelahDiskon = totalHarga* 1/2;
            output ="ID    : " + id + "\nPaket : " + paket + "\nHarga :\n" + berat + " kg x " + harga + " = " + totalHarga + " = " + setelahDiskon + " (Discount member 50%!!!)" + "\nTanggal Terima  : " + tanggalTerima + "\nTanggal Selesai : " + tanggalSelesai;
        }
        else{
            totalHarga = harga * berat;
            output ="ID    : " + id + "\nPaket : " + paket + "\nHarga :\n" + berat + " kg x " + harga + " = " + totalHarga + "\nTanggal Terima  : " + tanggalTerima + "\nTanggal Selesai : " + tanggalSelesai;
        }
        
        
       
        
        return output;
    }
    // method untuk menghitung tanggal selesai
    public static String hitungTanggal(String tanggal, int lamaWaktu){
        //membuat custom pattern
        DateTimeFormatter formatTanggal = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        //membuat instance tanggal terima dari input tanggal user dengan format sesuai formatTanggal
        LocalDate tanggalTerima = LocalDate.parse(tanggal, formatTanggal);
        //menghitung tanggal selesai
        LocalDate tanggalSelesai = tanggalTerima.plusDays(lamaWaktu);
        //memformat tanggalSelelsai dengan formatTanggal
        String form = tanggalSelesai.format(formatTanggal);
        return form;
    }

}


