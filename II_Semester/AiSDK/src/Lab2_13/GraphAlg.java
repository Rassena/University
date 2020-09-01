package Lab2_13;

import Lab2_12.Graph;
import java.util.Iterator;
import java.util.LinkedList;

public class GraphAlg {
	private int v; 
	private LinkedList<Integer> adjListArray[]; //angielska nazwa na graf
   //private int[][] motherBoard; //Macierz
	
	
	public GraphAlg(Graph graph) {
		this.v = graph.v;
		this.adjListArray = graph.adjListArray;
		//this.motherBoard = graph.motherBoard;
	}
	
	public void BFS(int s) {
        boolean visited[] = new boolean[v]; 
        LinkedList<Integer> queue = new LinkedList<Integer>();  
        
        visited[s]=true; 
        queue.add(s); 
        
		System.out.println("Kolejnosc odwedzonych w BFS od: " + s);
        while (queue.size() != 0)  { 
            s = queue.remove();
            System.out.print(s+" "); 
            
            Iterator<Integer> it = adjListArray[s].listIterator(); 
            while (it.hasNext()) { 
                int next = it.next(); 
                if (!visited[next]) { 
                    visited[next] = true; 
                    queue.add(next); 
                } 
            } 
        } 
        System.out.println("\n");
	}

	private void DFSUtil(int s,boolean visited[])  { 
        visited[s] = true; 
        System.out.print(s+" "); 
  
        Iterator<Integer> it = adjListArray[s].listIterator(); 
        while (it.hasNext()) { 
            int next = it.next(); 
            if (!visited[next]) 
                DFSUtil(next, visited); 
        } 
    } 
  
    public void DFS(int s) { 
		System.out.println("Kolejnosc odwedzonych w DFS od: " + s);
        boolean visited[] = new boolean[v]; 
        DFSUtil(s, visited); 
		System.out.println("\n");

    } 
}
