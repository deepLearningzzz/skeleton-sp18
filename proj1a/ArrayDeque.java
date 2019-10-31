public class ArrayDeque<Item>{
	private int size;
	private int nextFirst;
	private int nextLast;
	private Item[] items;

	private int minusOne(int index){
		int i = index - 1;
		if(i<0){
			i = items.length - 1;
		}
		return i;
	}


	private int plusOne(int index){
		int i = index + 1;
		if (i==items.length) {
            i = 0;
		}
		return i;
	}


	private void resize(int capacity){
        Item[] newItems = (Item[]) new Object[capacity];
        // Item[] newItems = new Item[capacity];
    	int beginning = plusOne(nextFirst);
    	int end = minusOne(nextLast);

    	if(beginning > end){
    		int sizeOfFirstHalf = items.length - beginning;
    		int sizeOfSecondHalf = size - sizeOfFirstHalf;
    		System.arraycopy(items,beginning,newItems,0,sizeOfFirstHalf);
    		System.arraycopy(items,0,newItems,sizeOfFirstHalf,sizeOfSecondHalf);   		
    	}
    	else{
    		System.arraycopy(items,beginning,newItems,0,size);
    	}
    	nextFirst = newItems.length - 1;
    	nextLast = size;
    	items = newItems;
	}


    // Creates an empty Deque.
    public ArrayDeque(){
    	nextFirst = 0;
    	nextLast = 1;
    	size = 0;
    	items = (Item[]) new Object[8];
    }


    //Create a Deque.
    public ArrayDeque(Item x){
    	 items = (Item[]) new Object[8];
    	 items[0] = x;
    	 size = 1;
    	 nextFirst = 7;
    	 nextLast = 1;
    }



    public void addFirst(Item x){
    	if(size==items.length){
    		resize(size*2);
    	}
    	items[nextFirst] = x;
    	nextFirst = minusOne(nextFirst);
    	size += 1;
    }


    public void addLast(Item x){
    	if (size == items.length) {
    		resize(size*2);
    	}
    	items[nextLast] = x;
    	nextLast = plusOne(nextLast);
    	size += 1;
    }

    
    public boolean isEmpty(){
    	if (size == 0) {
    		return true;
    	}
    	else{
    		return false;
    	}
    }


    public int size(){
    	return size;
    }


    public Item removeFirst(){
        if (isEmpty()) {
            return null;
        }
        if ((float)size/items.length < 0.25 && items.length >= 16) {
            resize(items.length/2);
        }
    int fP = plusOne(nextFirst);
    Item first = items[fP];
    items[fP] = null;
    size = size - 1;
    nextFirst = fP;
    return first;

    }


    public Item removeLast(){
        if (isEmpty()) {
            return null;
        }
        if ((float)size/items.length < 0.25 && items.length >= 16) {
            resize(items.length/2);
        }
    int fP = minusOne(nextLast);
    Item last = items[fP];
    items[fP] = null;
    size = size - 1;
    nextLast = fP;
    return last;

    }


    private boolean isExist(int i) {
        if (i > nextFirst || i < nextLast) {
            return true;
        }
        return false;
    }


    public Item get(int i) {
        if (isExist(i)) {
            return items[i];
        }
        return null;
    }

    public void printDeque(){
    	int i = 0;
    	int firstPos = plusOne(nextFirst);
    	while(i<size){
    		System.out.print(items[firstPos] + "");
    		firstPos = plusOne(firstPos);
    		i = i+1;
    	}
    }

}