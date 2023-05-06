package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    int sudahAntar;
    @Override
    public String doWork() {
        //sudah diantar
        this.sudahAntar++;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        //jika sudah diantar, service sudah selesai
        if (this.sudahAntar>0){
            return true;
        }
        //jika belum
        return false;
    }
    @Override
    public long getHarga(int berat) {
        //menghitung harga
        long harga = berat * 500;
        //jika kurang dari 2000
        if (harga < 2000){
            return 2000;
        }
        return harga;
    }

    @Override
    public String getServiceName() {
        return "Antar";
    }
}
