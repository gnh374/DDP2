package assignments.assignment3.nota.service;

public class CuciService implements LaundryService{
    int sudahCuci;
    @Override
    public String doWork() {
        //sudah dicuci
        this.sudahCuci++;
        return "Sedang mencuci...";
    }

    @Override
    public boolean isDone() {
        //jika sudah dicuci, service sudah selesai
        if (this.sudahCuci>0){
            return true;
        }
        //jika belum selesai
        return false;
    }

    @Override
    public long getHarga(int berat) {
        return 0;
    }

    @Override
    public String getServiceName() {
        return "Cuci";
    }
}
