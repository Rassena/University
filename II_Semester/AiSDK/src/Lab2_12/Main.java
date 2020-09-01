package Lab2_12;

public class Main {
	
	
	
	

	public static void main(String[] args) {
		/*
//		int[] set = new int[] {0,1,2,3,4,5,6,7,8};
//		int[] set = new int[9]; 
		
		int size= 9;
		
		Tree tr= new Tree(size);
		for(int i=0;i<size;i++) {
			tr.add(i);
		}
		
		
		tr.union(0,1);
		tr.union(2,3);
		tr.union(4,5);
		tr.union(6,7);
		tr.union(5,3);
		
		tr.show();
		tr.showSets();
		*/
		
		int v = 8; 
        Graph graph = new Graph(v); 
        graph.addEdge(graph, 0, 1); 
        graph.addEdge(graph, 0, 4); 
        graph.addEdge(graph, 1, 2); 
        graph.addEdge(graph, 1, 6); 
        graph.addEdge(graph, 1, 4); 
        graph.addEdge(graph, 2, 3); 
        graph.addEdge(graph, 3, 4); 
        graph.addEdge(graph, 3, 7); 
        graph.addEdge(graph, 4, 5); 
        graph.addEdge(graph, 5, 6); 
        graph.addEdge(graph, 6, 7); 
       

        graph.printGraph(graph);
        graph.printMother(graph);
        graph.addVertex(graph);
        
       
        graph.addEdge(graph, 1, 8);
        graph.addEdge(graph, 0, 8);
        
	}

}
