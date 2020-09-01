package Lab2_12;

public class Tree {
	Node heap;
	Node[] p;
	int[][] tab;
	
	public Tree(int size) {
		p = new Node[size];
		tab=new int[size][2];
	}
	
	public class Node{
		int value;
		Node parent;
		Node head;
		
		
		public Node(int value) {
			this.value=value;
			parent=this;
		}
		
		public String toString() {
			String s =Integer.toString(value);
			return s;
		}
	}

	public void union(int left,int right) {
		if(p[left].parent==p[left]&&p[right].parent==p[right]) {
			p[left].parent=p[right];
		}
		else
			System.out.println("lewy ma rodzica,lub prawy nie jest najstarszy");
	}
	
	public void show() {
		for(int i=0; i<p.length;i++) {
			System.out.print(p[i]+ " ");
		}
		System.out.println();
		
		for(int i=0; i<p.length;i++) {
			System.out.print(p[i].parent+ " ");
		}
		System.out.println();
	}
	
	public void showSets() {
		for(int i=0;i<p.length;i++) {
			int j=i;
			while(p[j].parent!=p[j]) {
				j=p[j].parent.value;
				System.out.print(p[j].value + " ");
			}
			System.out.print(p[i].value);
			System.out.println();
		}
	}
	
	public void add(int value) {
		Node n = new Node(value);
		int index=0;
		while(p[index]!=null) {
			index++;
		}
		p[index]=n;
	}
	
}
