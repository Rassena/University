package OSLab5;

import java.util.ArrayList;

public class Procesor {
    ArrayList <Proces> procesy = new ArrayList<>();
    int maxZapelnienie =0;
    int zapelnienie = 0;


    public void dodajProces(Proces proces){
        procesy.add(proces);
        zapelnienie += proces.ileProcent;
    }

    public void wykonaj (){
        procesy.get(0).dlugosc--;
        if (procesy.get(0).dlugosc == 0){
            zapelnienie -= procesy.get(0).ileProcent;
            procesy.remove(0);
        }
    }
}
