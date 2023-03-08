package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Nota {
    private int idNota;
    private String paket;
    private Member member;
    private int berat;
    private String tanggalMasuk;
    private int sisaHariPengerjaan;
    private boolean isReady;
    // TODO: tambahkan attributes yang diperlukan untuk class ini
    public Nota(){
        
    }
    public Nota(Member member, String paket, int berat, String tanggalMasuk, int idNota) {
        this.member = member;
        this.paket = paket;
        this.berat = berat;
        this.tanggalMasuk =  tanggalMasuk;
        this.idNota = idNota;
        this.sisaHariPengerjaan = NotaGenerator.jumlahHariPengerjaan(this.paket);
        this.isReady = false;
    }

    public void setSisaHariPengerjaan(){
        this.sisaHariPengerjaan -=1;
    }
    public int getIdNota(){
        return this.idNota;
    }

    public int getSisaHariPengerjaan(){
        return this.sisaHariPengerjaan;
    }

    public boolean getIsReady(){
        return this.isReady;
    }
    public void setIsReady(){
        this.isReady = true;
    }
}
