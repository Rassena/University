package OSLab4;

import java.util.ArrayList;

public class Sector {
	private ArrayList<ArrayList<Integer>> procesy;
	private ArrayList<Integer> errors;
	private ArrayList<Integer> frames;
	private int okres;
	
	
	public Sector(ArrayList<ArrayList<Integer>> proces, int ramki, int okres) {
		
		this.okres=okres;
		procesy=proces;
		frames = new ArrayList<Integer>();
		
		for(int i=0;i<proces.size();i++) {
			frames.add(ramki/procesy.size());
		}
		
		oblicz();
	}
	
	public void proceses() { 
		System.out.println("Procesy w Sector: ");
		for(int i=0;i<procesy.size();i++) {
			System.out.println(procesy.get(i) + " " + frames.get(i));
		}
	}
	
	public void show() {
		System.out.println("Bledy w Sector: ");
		for(int i=0;i<errors.size();i++) {
			System.out.println("proces nr." + i + ": "+errors.get(i));
		}
	}
	
	private void oblicz() {
		errors =new ArrayList<Integer>();
		for(int i=0;i<procesy.size();i++) {
			LRU lru= new LRU(frames.get(i),procesy.get(i));
		//	for(int j=0; j<)
			
			//errors.add(lru.run());
		}
	}
	
	private void korekta() {
		
	}

}
