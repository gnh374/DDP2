package assignments.assignment2;

import assignments.assignment1.NotaGenerator;

public class Member {
    private String nama;
    private String noHp;
    private String id;
    private int bonusCounter = 0;
    public Member(String nama, String noHp) {
        this.nama = nama;
        this.noHp = noHp;
        this.id = NotaGenerator.generateId(this.nama, this.noHp);
    }

    public String getId(){
        return this.id;
    }

    public String getNama(){
        return this.nama;
    }

    public void setBonusCounter(){
        if (this.bonusCounter == 3){
            this.bonusCounter = 0;
        }
        else {
            this.bonusCounter += 1;
        }
    }

    public int getBonusCounter(){
        return this.bonusCounter;
    }


}
