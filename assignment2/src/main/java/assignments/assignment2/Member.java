package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    //membuat data field
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter = 0;
    //membuat constructor
    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
        //memanggil generateId untuk membuat id
        this.id = NotaGenerator.generateId(this.nama, this.noHp);
    }

    //method untuk mendapatkan id
    public String getId(){
        return this.id;
    }

    //method untuk mendapatkan nama
    public String getNama(){
        return this.nama;
    }

    
    //method untuk menambahkan bonus counter
    public void setBonusCounter(int jumlahPemesanan){
        this.bonusCounter = jumlahPemesanan;
    }

    
    //method untuk mendapatkan bonus counter
    public int getBonusCounter(){
        return this.bonusCounter;
    }


}
