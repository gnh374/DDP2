package assignments.assignment3.nota;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;

public class NotaManager {
    public static SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy");
    public static Calendar cal = Calendar.getInstance();
    static public Nota[] notaList = new Nota[0];

    /**
     * Skips ke hari berikutnya dan update semua entri nota yang sesuai.
     */
    public static void toNextDay(){
        //pindah ke hari selanjutnya
        cal.add(Calendar.DATE, 1);
        // hari tersisa semua nota dikurangi 1
        for (Nota nota : notaList){
            nota.toNextDay();
        }
    
    }

    /**
     * Menambahkan nota baru ke NotaList.
     *
     * @param nota Nota object untuk ditambahkan.
     */
    //untuk menambahkan nota ke dalam list nota
    public static void addNota(Nota nota){
        notaList = Arrays.copyOf(notaList, notaList.length+1);
        notaList[notaList.length-1] = nota;
    }
}
