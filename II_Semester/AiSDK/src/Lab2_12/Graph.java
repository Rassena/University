package Lab2_12;


import java.util.Iterator;
import java.util.LinkedList; 

public class Graph {
	public int v; 
	public LinkedList<Integer> adjListArray[]; //angielska nazwa na graf
    public int[][] motherBoard; //Macierz
	
    @SuppressWarnings("unchecked")
	public Graph(int v) {   
    	this.v = v; 
    	adjListArray = new LinkedList[v];
    	motherBoard = new int[v][v];
    	
    	for(int i = 0; i < v ; i++){ 
    		adjListArray[i] = new LinkedList<>(); 
    	}
    	for(int i =0; i<motherBoard.length;i++) {
    		for(int j=0;j<motherBoard.length;j++) {
    			motherBoard[i][j] =0;
    		}
    	}
    }  

   public void addEdge(Graph graph, int src, int dest) { 
	   
       graph.adjListArray[src].add(dest); 
       graph.adjListArray[dest].add(src); //to usunac by byl skierowany
       
       graph.motherBoard[src][dest]=1;
       graph.motherBoard[dest][src]=1; // to usunac by byl skierowany
   }
   
   @SuppressWarnings("unchecked")
   public void addVertex(Graph graph) {
	   this.v++;

	   LinkedList<Integer> temp[];
	   temp = new LinkedList[v];

	   int[][] tempBoard = new int[v][v];
   	
	   for(int i = 0; i < adjListArray.length ; i++){ 
   			temp[i] = adjListArray[i]; 
	   }
		temp[v-1] = new LinkedList<>(); 
	   
	   for(int i =0; i<motherBoard.length;i++) {
		   for(int j=0;j<motherBoard.length;j++) {
   				tempBoard[i][j]= motherBoard[i][j];
   			}
	   }
	   motherBoard=tempBoard;
	   adjListArray=temp;
   }
      
   public void printGraph(Graph graph) {        
       for(int v = 0; v < graph.v; v++)  { 
           System.out.println("Wierzchołki polaczone z: "+ v); 
           System.out.print(v+ " -> ("); 
           Iterator<Integer> temp= graph.adjListArray[v].iterator();
           while(temp.hasNext()) {
               System.out.print(temp.next() + ",");
           } 
           System.out.println(")\n"); 
       } 
   }
   
   public void printMother(Graph graph) {
	   System.out.println("Macierz grafu: ");
       for(int i= 0; i < graph.v; i++)  { 
           for(int j=0; j < graph.v;j++) {
        	   System.out.print(graph.motherBoard[i][j] + " ");
           } 
           System.out.println(); 
       }
       System.out.println(); 
   }
   
} 