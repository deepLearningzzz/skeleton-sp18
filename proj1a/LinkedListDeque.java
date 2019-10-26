public class LinkedListDeque<AnyType>{
	public class Node{
		public AnyType item;
		public Node prev;
		public Node next;
		public Node(AnyType i,Node p, Node n){
			item = i;
			prev = p;
			next = n;
		}

	}

	private Node sentinel;
	private int size;

	public LinkedListDeque(){
		sentinel = new Node(null,null,null);
		sentinel.prev = sentinel;
		sentinel.next = sentinel;
		size = 0;
	}

	public void LinkedListDeque(AnyType item){
		sentinel = new Node(null,null,null);
		Node newnode = new Node(item, sentinel,sentinel);
		sentinel.prev = newnode;
		sentinel.next = newnode;
		size += 1;
	}

	public void addFirst(AnyType item){
		Node first = new Node(item,sentinel,sentinel.next);
		sentinel.next.prev = first;
		if (size == 0) {
			sentinel.next = new Node(item,sentinel,sentinel.next);
			sentinel.prev = sentinel.next;	
		}
		else{
			sentinel.next = new Node(item,sentinel.prev,sentinel.next);
		}
        size += 1;
	}

	public void addLast(AnyType item){
		Node last = new Node(item,sentinel.prev,sentinel);
		sentinel.prev.next = last;
		if(size==0){
			sentinel.prev = last;
			sentinel.next = sentinel.prev;
		}
		else{
			sentinel.prev = last;
		}
		size += 1;
	}
    public boolean isEmpty() {
        if (size == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return size;
    }

        /** Removes and returns the item at the front of the deque. If no such item exists, returns null. */
    public AnyType removeFirst() {
        if (isEmpty()) {
            return null;
        }
        AnyType first = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size = size - 1;
        return first;
    }

    /** Removes and returns the item at the back of the deque. If no such item exists, returns null. */
    public AnyType removeLast() {
        if (isEmpty()) {
            return null;
        }
        AnyType last = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size = size - 1;
        return last;
    }

    /** Gets the item at the given index, where 0 is the front, 1 is the next item, and so forth. If no such item exists, returns null. */
    public AnyType get(int index) {
        if (index > size-1) {
            return null;
        }
        int i = 0;
        Node p = sentinel.next;
        while (i < index) {
            p = p.next;
            i++;
        }
        return p.item;
    }

    /** Same as get, but uses recursion. */
    public AnyType getRecursive(int index) {
        return getHelper(sentinel.next, index);
    }

    private AnyType getHelper(Node n, int index) {
        if (index == 0) {
            return n.item;
        }
        return getHelper(n.next, index - 1);
    }


    public void printDeque() {
    Node n = sentinel.next;
    while (n != sentinel) {
        System.out.print(n.item + " ");
        n = n.next;
        }
    }

}