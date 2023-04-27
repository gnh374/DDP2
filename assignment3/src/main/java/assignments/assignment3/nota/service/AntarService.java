package assignments.assignment3.nota.service;

import assignments.assignment3.nota.Nota;

public class AntarService implements LaundryService{
    int sudahAntar;
    @Override
    public String doWork() {
        this.sudahAntar++;
        return "Sedang mengantar...";
    }

    @Override
    public boolean isDone() {
        if (this.sudahAntar>0){
            return true;
        }
        return false;
    }
    @Override
    public long getHarga(int berat) {
        long harga = berat * 500;
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
