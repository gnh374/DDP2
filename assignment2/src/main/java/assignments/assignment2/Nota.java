package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    //membuat data field
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;
    // no arg constructor
    public Nota(){
        
    }
    //constructor dengan argumen
    public Nota(Member member, String paket, int berat, String tanggalMasuk, int idNota) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk = tanggalMasuk;
        this.idNota = idNota;
        //mengakses method jumlahHariPengerjaan
        this.sisaHariPengerjaan = NotaGenerator.jumlahHariPengerjaan(this.paket.toLowerCase());
        this.isReady = false;
    }
    
    //method untuk mengurangi sisa hari pengerjaan
    public void setSisaHariPengerjaan(){
        this.sisaHariPengerjaan -= 1;
    }
    //method untuk mendapatkan id nota
    public int getIdNota(){
        return this.idNota;
    }

    //method untuk mendapatkan sisa hari pengerjaan
    public int getSisaHariPengerjaan(){
        return this.sisaHariPengerjaan;
    }

    //method untuk menyimpan status nota
    public boolean getIsReady(){
        return this.isReady;
    }

    //method untuk mengeset status nota jika sudah selesai
    public void setIsReady(){
        this.isReady = true;
    }
}
