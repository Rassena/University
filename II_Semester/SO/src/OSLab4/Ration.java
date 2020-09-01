package OSLab4;

import java.util.ArrayList;

public class Ration {
	private ArrayList<ArrayList<Integer>> procesy;
	private ArrayList<Integer> errors;
	private ArrayList<Integer> frames;
	
	
	public Ration(ArrayList<ArrayList<Integer>> proces, int ramki) {
		double wszystkieStrony=0;
		frames = new ArrayList<Integer>();
		for(int i=0;i<proces.size();i++) {
			wszystkieStrony+=proces.get(i).size();
			frames.add(1);
			ramki--;
		}
		procesy=proces;
		for(int i=0;i<proces.size();i++) {
			double temp = ramki * proces.get(i).size()/wszystkieStrony;
			if((int)temp!=0) {
				frames.set(i,(int)temp +1);
			}
		}
		oblicz();
	}
	
	public void proceses() { 
		System.out.println("Procesy w Ration: ");
		for(int i=0;i<procesy.size();i++) {
			System.out.println(procesy.get(i) + " " + frames.get(i));
		}
	}
	
	public void show() {
		System.out.println("Bledy w Ration: ");
		for(int i=0;i<errors.size();i++) {
			System.out.println("proces nr." + i + ": "+errors.get(i));
		}
	}
	
	private void oblicz() {
		errors =new ArrayList<Integer>();
		for(int i=0;i<procesy.size();i++) {
			LRU lru= new LRU(frames.get(i),procesy.get(i));
			errors.add(lru.run());
		}
	}
	
	

}
