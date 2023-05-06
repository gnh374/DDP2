package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    int sudahSetrika;
    @Override
    public String doWork() {
        //jika sudah disetrika
        this.sudahSetrika++;
        return "Sedang menyeterika...";
    }

    @Override
    public boolean isDone() {
        //jika sudah disetrika, service sudah selesai
        if (this.sudahSetrika>0){
            return true;
        }
        //jika belum
        return false;
    }

    @Override
    public long getHarga(int berat) {
        return berat * 1000;
    }

    @Override
    public String getServiceName() {
        return "Setrika";
    }
}
