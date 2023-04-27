package assignments.assignment3.nota.service;

public class SetrikaService implements LaundryService{
    int sudahSetrika;
    @Override
    public String doWork() {
        this.sudahSetrika++;
        return "Sedang menyeterika...";
    }

    @Override
    public boolean isDone() {
        if (this.sudahSetrika>0){
            return true;
        }
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
