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
        this.sisaHariPengerjaan = NotaGenerator.jumlahHariPengerjaan(this.paket);
        this.isDone = false;
        CuciService cuci = new CuciService();
        services[this.indexService++]= cuci;
    }

    public void addService(LaundryService service){
        this.services = Arrays.copyOf(this.services, this.services.length+1 );
        this.services[this.services.length-1] = service;
    }

    public String kerjakan(){
        return "";
    }
    public void toNextDay() {
        this.sisaHariPengerjaan-=1;
        if (this.sisaHariPengerjaan < 0 && !this.isDone){
            this.hariTelat++;
            hitungKompensasi();
        }
    }

    public void hitungKompensasi(){
        if (this.totalHarga > 0){
            this.totalHarga -= 2000;
        }
       
    }
    public long calculateHarga(){
        this.totalHarga += NotaGenerator.hitungHarga(this.paket, this.berat);
        for (LaundryService service : this.services){
            this.totalHarga += service.getHarga(berat);
        }
        return this.totalHarga;
    }

    public String getNotaStatus(){
        if (this.isDone){
            return String.format("Nota %d : Sudah selesai.", this.id);
        }
        else{
            return String.format("Nota %d : Belum selesai.", this.id);
        }
    }

    @Override
    public String toString(){
        return String.format("[ID Nota = %d]\n%s\n--- SERVICE LIST ---", this.id, NotaGenerator.generateNota(this.member.getId(), this.paket, this.berat, this.tanggalMasuk));
    }
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
