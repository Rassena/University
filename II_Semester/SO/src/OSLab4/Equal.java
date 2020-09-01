package OSLab4;

import java.util.ArrayList;

public class Equal {
	private ArrayList<ArrayList<Integer>> procesy;
	private ArrayList<Integer> errors;
	private int ramki;
	
	
	public Equal(ArrayList<ArrayList<Integer>> proces, int ramki) {
		/*
		for (int i=0;i<proces.size();i++) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			temp.add(-1);
			procesy.add(temp);
			for(int j=0;j<proces.get(i).size();j++){
				procesy.get(i).add(proces.get(i).get(j));
				System.out.println(proces.get(i).get(j));
			}
			procesy.get(i).remove(0);
			System.out.println();
		}
		*/
		procesy=proces;
		this.ramki = ramki/procesy.size();
		oblicz();
	}
	
	public void proceses() { 
		System.out.println("Procesy w Equals: ");
		for(int i=0;i<procesy.size();i++) {
			System.out.println(procesy.get(i) + " " + ramki);
		}
	}
	
	public void show() {
		System.out.println("Bledy w Equals: ");
		for(int i=0;i<errors.size();i++) {
			System.out.println("proces nr." + i + ": "+errors.get(i));
		}
	}
	
	private void oblicz() {
		errors =new ArrayList<Integer>();
		for(int i=0;i<procesy.size();i++) {
			LRU lru= new LRU(ramki,procesy.get(i));
			errors.add(lru.run());
		}
		
	
	}

}
