package OSLab5;

import java.util.ArrayList;
import java.util.Random;

public class System1 {

    int min;
    int max;
    ArrayList <Procesor> procesory = new ArrayList<>();
    ArrayList <Proces> procesy = new ArrayList<>();

    public System1 (int ileProcesorow, int ileProcesow, int min , int max){
    	this.min=min;
    	this.max=max;
        for (int i = 0; i<ileProcesorow; i++){
            procesory.add(new Procesor());
        }
        for (int i = 0; i<ileProcesow; i++){
            Random r = new Random();
            int temp1 = r.nextInt(5) + 100;
            int temp2 = r.nextInt(5) + 1;
            procesy.add(new Proces(temp1, temp2));
        }
    }


    public void run (){
        double srZapelnienie = 0;
        int time = 0;
        Random r = new Random();
        while (!procesy.isEmpty()){
            time++;
            Procesor x = procesory.get(r.nextInt(procesory.size()-1));
            int nastepny = r.nextInt(procesory.size()-1);
            int h = 0;
            Procesor procesor = procesory.get(nastepny);
            while (procesor.zapelnienie>max && h < 3){
                nastepny = r.nextInt(procesory.size()-1);
                procesor = procesory.get(nastepny);
                h++;
            }
            
            if (procesor.zapelnienie < max) {
                procesor.dodajProces(procesy.get(0));
                procesy.remove(0);
            } 
            else {
                x.dodajProces(procesy.get(0));
                procesy.remove(0);
            }


            for (Procesor procesor1: procesory){
                if (procesor1.zapelnienie!= 0){
                    srZapelnienie+=procesor1.zapelnienie;
                    procesor1.wykonaj();
                    if (procesor1.maxZapelnienie< procesor1.zapelnienie){
                        procesor1.maxZapelnienie = procesor1.zapelnienie;
                    }
                }
            }
        }

        double srZapelnienieMax = 0;
        for (Procesor procesor: procesory){
            srZapelnienieMax += procesor.maxZapelnienie;
        }

        srZapelnienieMax  = srZapelnienieMax/procesory.size();
        srZapelnienie = srZapelnienie / time;
        srZapelnienie = srZapelnienie / procesory.size();
        System.out.print("Srednie zapelnienie procesora w trakcie dzialania: ");
        System.out.println(srZapelnienie);
        System.out.print("Srednia maksymalnego zapelnienia procesora: ");
        System.out.println(srZapelnienieMax);

        double odchylenie = 0;
        for (Procesor procesor: procesory){
            odchylenie += Math.abs(procesor.maxZapelnienie - srZapelnienieMax);
        }
        
        odchylenie = odchylenie/procesory.size();
        System.out.print("Odchylenie: ");
        System.out.println(odchylenie);



    }




}
