package Lab2_10;

public class Main {

	public static void main(String[] args) {
		
		BST bst = new BST();
		bst.add(5);
		bst.add(2);
		bst.add(4);
		bst.add(9);
		bst.add(3);
		bst.add(7);
		bst.add(0);
		
		System.out.println("zawiera 1: " + bst.contains(1));
		System.out.println("zawiera 2: " + bst.contains(2));
		System.out.println("Szukana: " + bst.get(2).value);
		
		System.out.println("Max: " + bst.findMax());
		System.out.println("Min: " + bst.findMin());
		System.out.println("Height: " + bst.height());
		
		System.out.print("InOrder: ");
		bst.inOrder();
		System.out.print("PreOrder: ");
		bst.preOrder();
		System.out.print("PostOrder: ");
		bst.postOrder();
		
		System.out.print("InOrder after Delete 4: ");
		bst.delete(4);
		bst.inOrder();
		
		System.out.println("Nodes: " + bst.amounOfNodes());
		System.out.println("Leafs: " + bst.amountOfLeafs());
		System.out.println("Height: " + bst.height());
		System.out.println("Succesor of 5: " + bst.successor(bst.get(5)).value);
		System.out.println("Precessor of 5: " + bst.predecessor(bst.get(5)).value);
		
		System.out.print("Poziom 1: ");
		bst.printLevel(1);
		System.out.println();
		bst.printByLevels();
		
		System.out.println("Succesor of 3: " + bst.successor(bst.get(3)).value);
		System.out.println("Precessor of 7: " + bst.predecessor(bst.get(7)).value);
		
		bst.delete(5);
		bst.printByLevels();
	}

}
