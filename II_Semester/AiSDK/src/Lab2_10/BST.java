package Lab2_10;

public class BST {
	Node node=null;
	private int leafs;
	private int nodes;
	
	public class Node {
		int value;
		Node left;
		Node right;
		
		public Node(int value) {
			this.value=value;
		}

	}
	
	public void add(int value) {
		if(node==null) {
			node = new Node(value);
		}
		else {
			Node temp = node;
			boolean work=true;
			while(work) {
				if(value<=temp.value) {
					if(temp.left==null) {
						temp.left=new Node(value);
						work=false;
					}
					else {
						temp=temp.left;
					}
				}
				else {
					if(temp.right==null) {
						temp.right=new Node(value);
						work=false;
					}
					else {
						temp=temp.right;
					}
				}
			}
		}
	}
	
	public boolean contains(int value) {
		boolean found=false;
		boolean end=false;
		Node temp=node;
		while(!found&&!end) {
			if (temp.value==value)
				return true;
			if(value<temp.value)
				if(temp.left==null)
					return false;
				else
					temp=temp.left;
			if(value>temp.value)
				if(temp.right==null)
					return false;
				else
					temp=temp.right;
		}
		return false;
	}
	
	public Node get(int value) {
		if(contains(value)) {
			Node temp=node;
			while(value!=temp.value&&temp!=null) {
				if(value<temp.value)
					temp=temp.left;
				else
					temp=temp.right;
			}
			return temp;
		}
		else
			return null;
	}
	
	public int findMax() {
		Node temp=node;
		while(temp.right!=null)
			temp=temp.right;
		return temp.value;
	}
	
	public int findMin() {
		Node temp=node;
		while(temp.left!=null)
			temp=temp.left;
		return temp.value;
	}
	
	private Node findMin(Node n) {
		Node temp=n;
		while(temp.left!=null)
			temp=temp.left;
		return temp;
	}
	
	private Node findMax(Node n) {
		Node temp=n;
		while(temp.right!=null)
			temp=temp.right;
		return temp;
	}
	
	public void inOrder() {
		inOrder(node);
		System.out.println();
	}
	
	private void inOrder(Node n) {
		if(n==null)
			return;
		inOrder(n.left);
		System.out.print(n.value + " ");
		inOrder(n.right);
	}
	
	public void preOrder() {
		preOrder(node);
		System.out.println();
	}
	
	private void preOrder(Node n) {
		if(n==null)
			return;
		System.out.print(n.value + " ");
		preOrder(n.left);
		preOrder(n.right);
	}
	
	public void postOrder() {
		postOrder(node);
		System.out.println();
	}
	
	private void postOrder(Node n) {
		if(n==null)
			return;
		postOrder(n.left);
		postOrder(n.right);
		System.out.print(n.value + " ");
	}
	
	public int amountOfLeafs() {
		leafs=0;
		amountOfLeafs(node);
		return leafs;
	}
	
	private void amountOfLeafs(Node n) {
		if(n==null)
			return;
		if(n.left==null&&n.right==null) {
			leafs++;
			return;
		}
		amountOfLeafs(n.left);
		amountOfLeafs(n.right);
	}
	
	public int amounOfNodes() {
		nodes=0;
		amountOfNodes(node);
		return nodes;
	}
	
	private void amountOfNodes(Node n) {
		if(n==null)
			return;
		amountOfNodes(n.left);
		amountOfNodes(n.right);
		nodes++;
	}
	
	public int height(){
	       return findHeight(node) -1;
	}
	
	private int findHeight(Node n){
	    int heightLeft = 0;
	    int heightRight = 0;
	    if(n.left!=null)
	        heightLeft = findHeight(n.left);
	    if(n.right!=null)
	        heightRight = findHeight(n.right);
	    if(heightLeft > heightRight){
	        return heightLeft+1;
	    }
	    else{
	        return heightRight+1;
	    }
	}
	
	public Node successor(Node node){
		  
	    Node successor = null;
	    Node current  = this.node;
	    if(node==null)
	        return null;
	  
	    while(current.value!= node.value){
	        if(current.value > node.value){
	            successor = current;
	            current= current.left;
	        }
	        else
	            current = current.right;
	    }
	    if(current!=null && current.right!=null){
	        successor = findMin(current.right);
	    }
	  
	    return successor;
	}
	
	public Node predecessor(Node node){
		  
	    Node predecessor = null;
	    Node current  = this.node;
	    if(node==null)
	        return null;
	  
	    while(current.value!= node.value){
	        if(current.value < node.value){
	            predecessor = current;
	            current= current.right;
	        }
	        else
	            current = current.left;
	    }
	    if(current!=null && current.left!=null){
	        predecessor = findMax(current.left);
	    } 
	    return predecessor;
	}
	
	public void delete(int key) { 
	        node = delete(node, key); 
	    } 

	private Node delete(Node n, int key) { 
		 if (n == null)  return n;
	     if (key < n.value) 
	    	 n.left = delete(n.left, key); 
	     else 
	    	 if (key > n.value) 
	            n.right = delete(n.right, key); 
	        else { 
	            if (n.left == null) 
	                return n.right; 
	            else if (n.right == null) 
	                return n.left; 
	            n.value = findMin(n.right).value; 
	            n.right = delete(n.right, n.value); 
	        } 
	     return n; 
	 }
	 
	public void printLevel(int height) {
		 printLevel(node, height); 
	     System.out.println();       
	 } 
	    	     
	private void printLevel(Node n ,int level) { 
		if (n == null) 
			return; 
	    if (level == 0) 
	        System.out.print(n.value + " "); 	
	    else 
	    	if (level > 0) { 
	            printLevel(n.left, level-1); 
	            printLevel(n.right, level-1);
	        } 
	    } 
	 
	public void printByLevels() {
		for(int i=0; i<=height();i++) {
			System.out.print("Poziom " + i +": ");
			printLevel(i);
		}
	}
}
