package assignments.assignment3.nota;
import assignments.assignment3.nota.service.AntarService;
import assignments.assignment3.nota.service.CuciService;
import assignments.assignment3.nota.service.LaundryService;
import assignments.assignment3.user.Member;
import java.util.Arrays;
import assignments.assignment1.NotaGenerator;
public class Nota {
    private Member member;
    private String paket;
    private LaundryService[] services = new LaundryService [1];
    private int sisaHariPengerjaan;
    private  int berat;
    //id Nota
    private int id;
    private String tanggalMasuk;
    private boolean isDone;
    private int indexService = 0;
    private long totalHarga;
    private int hariTelat;
    static public int totalNota;

    public Nota(Member member, int berat, String paket, String tanggal) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggal;
        this.id = totalNota++;
        //mengakses method jumlahHariPengerjaan
        this.sisaHariPengerjaan = NotaGenerator.jumlahHariPengerjaan(this.paket.toLowerCase());
        this.isDone = false;
        CuciService cuci = new CuciService();
        //default setiap melaundry akan mengambil service cuci
        services[this.indexService++]= cuci;
    }
    //menambahkan service
    public void addService(LaundryService service){
        this.services = Arrays.copyOf(this.services, this.services.length+1 );
        this.services[this.services.length-1] = service;
    }

    public String kerjakan(){
        return "";
    }
    public void toNextDay() {
        //mengurangi sisa hari pengerjaan
        this.sisaHariPengerjaan-=1;
        //jika sudah lewat sisa hari namun nota belum selesai
        if (this.sisaHariPengerjaan < 0 && !this.isDone){
            //hitung jumlah hari telat
            this.hariTelat++;
            //dapat kompensasi
            hitungKompensasi();
        }
    }

    public void hitungKompensasi(){
        //jika total harga > 0 kurangi 2000 setiap telat
        if (this.totalHarga > 0){
            this.totalHarga -= 2000;
        }
       
    }
    //menghitung harga beserta service
    public long calculateHarga(){
        this.totalHarga += NotaGenerator.hitungHarga(this.paket.toLowerCase(), this.berat);
        for (LaundryService service : this.services){
            this.totalHarga += service.getHarga(berat);
        }
        return this.totalHarga;
    }
    //status
    public String getNotaStatus(){
        //jika sudah selesai
        if (this.isDone){
            return String.format("Nota %d : Sudah selesai.", this.id);
        }
        else{
            return String.format("Nota %d : Belum selesai.", this.id);
        }
    }

    @Override
    public String toString(){
       String nota = String.format("[ID Nota = %d]\n%s\n--- SERVICE LIST ---\n", this.id, NotaGenerator.generateNota(this.member.getId(), this.paket, this.berat, this.tanggalMasuk));
       for (LaundryService service: this.services){
            nota += String.format("- %s @ Rp.%d\n", service.getServiceName(), service.getHarga(this.berat));
        }
        if (this.getHariTelat()>0){
            nota+=String.format("Harga Akhir: %d Ada kompensasi keterlambatan %d * 2000 hari\n", this.getTotalHarga(), this.getHariTelat());
        }
        else{
            nota+=String.format("Harga Akhir: %d\n\n", this.getTotalHarga());
        }
        return nota;

    }
    //mengubah nota menjadi selesai
    public void setIsDone(){
        this.isDone = true;
    }
    // Dibawah ini adalah getter
    public String getPaket() {
        return paket;
    }

    public int getBerat() {
        return berat;
    }
    public int getHariTelat(){
        return this.hariTelat;
    }
    public int getId() {
        return this.id;
    }
    public long getTotalHarga() {
        return this.totalHarga;
    }
    public String getTanggal() {
        return tanggalMasuk;
    }

    public int getSisaHariPengerjaan(){
        return sisaHariPengerjaan;
    }
    public boolean isDone() {
        return isDone;
    }

    public LaundryService[] getServices(){
        return services;
    }

    public Member getMember(){
        return this.member;
    }
}
