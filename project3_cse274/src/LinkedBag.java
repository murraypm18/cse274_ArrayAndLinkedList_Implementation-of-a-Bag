import java.util.LinkedList; 
import java.util.NoSuchElementException;
/**
 * 
 * @author Patrick Murray
 * Date: 10/14/21
 * This this the implementation of the LinkedBag class
 * @param <E>
 */
public class LinkedBag <E> implements BagInterface <E>{
	private static final int defCapVar = 10;
	private Node head;
	private int capacity; 
	private int numItems;
	LinkedList<E> list;
	
	private class Node {
		E data;
		Node next;

		Node(E data) {
			this(data, null);
		}
		
		Node(E data, Node next) {
			this.data = data;
			this.next = next;
		}		
	}
	
	public LinkedBag() {
		this(defCapVar);
	}

	public LinkedBag(int capacity) {
		if(capacity < 5)
			throw new IllegalArgumentException();
		this.capacity = capacity;
		list = new LinkedList<E>();
		head = null;		
		this.numItems = 0;
	}
	
	@Override
	public int size() {
		return numItems;
	}

	@Override
	public boolean isEmpty() {
		if(numItems == 0)
			return true;
		return false;
	}

	@Override
	public boolean add(E newEntry) {
		if(head == null) {
			head = new Node(newEntry);
			numItems++;
			return true;
		}
		else { //list is not empty
			Node tmp = new Node(newEntry);
			tmp.next = head;
			head = tmp;
			numItems++;
			return true;
		}
	}

	@Override
	public E remove() {
		E num = null;
		E hold = head.data;
		if(head != null) {
			num = head.data;
			head = head.next;
			numItems--;
			return hold;
		}
		return null;
	}

	@Override
	public boolean remove(E anEntry) {
		int find = this.find(anEntry);
		String tmp = (String) this.removeAt(find);
		String tmp2 = (String) anEntry;
		if(tmp.equals(tmp2)) {
			this.removeAt(find);
			numItems--;
			return true;
		}
		return false;
		
	}

	@Override
	public void clear() {
		while(numItems != 0) {
			remove();
		}		
	}

	@Override
	public int getFrequencyOf(E anEntry) {
		int num = 0;
		int hold = 0;
		Node cur = head;
		while((hold < numItems) && (cur != null)){ 
			if(anEntry.equals(cur.data))
				num++;
			hold++;
			cur = cur.next;
		}
		return num;
	}

	@Override
	public boolean contains(E anEntry) {
		boolean hold = false;
		Node cur = head;
		while(!hold && (cur != null)) {
			if(anEntry.equals(cur.data))
				hold = true;
			cur = cur.next;
		}
		return hold;
	}

	@Override
	public E[] toArray() {
		String[] tmp = new String[numItems];
		int i = 0;
		Node cur = head;
		while((i < numItems) && cur != null) {
			tmp[i] = (String)cur.data;
			i++;
			cur = cur.next;
		}
		return (E[])tmp;
	}

	@Override
	public void removeDuplicates() {
		String[] newBag = (String[])this.toArray();
		int i = 0, j;
		while (i < this.size() - 2) {
			j = i + 1;
			while (j < this.size()-1) {
				while (newBag[i] == newBag[j]) {
					if (newBag[i] == null & newBag[j] == null)
						break;
					newBag = remove(j);
				}
				j++;
			}
			i++;
		}
		
	}
	
	private String[] remove(int index) {
		String[] newBag = (String[])this.toArray();
		if (index < 0 || index >= newBag.length) 
			return null;
		for (int i = index; i < newBag.length - 1; i++)
			newBag[i] = newBag[i + 1];
		String g = newBag[newBag.length - 1];
		newBag[newBag.length - 1] = null;
		numItems--;
		numItems--;
		return newBag;
		
		
	}

	@Override
	public boolean containsAll(BagInterface aBag) {		
		E[] arr = (E[])aBag.toArray();
		for(int i = 0; i < arr.length; i++) {
			if(!this.contains(arr[i])){
				return false;
			}
		}
		return true;
	}

	@Override
	public boolean sameItems(BagInterface aBag) {
		String[] tmp = (String[])aBag.toArray();
		int count = 0;
		if(this.size() != aBag.size())
			return false;
		for(int i = 0; i < this.numItems; i++){
				count++;
		}
		if(count == this.size()) 
			return true;
		return false;
	}
//=================================================================Helper Methods
	public int find(E key) {
		Node tmp = head;
		for(int i =0; i < this.size(); i++) {
			if(tmp.data == key) 
				return i;
			tmp = tmp.next;
		}
		return -1; 
	}
	
	public E removeAt(int i) {
		if(i < 0 || i > this.size())
			throw new IndexOutOfBoundsException();
		else { //list is not empty
			Node tmp = head;
            Node prev = tmp;
            if(i == 0) {
            	if(head == null)
        			throw new NoSuchElementException();
        		else { //list is not empty
        			tmp = head;
        			head = head.next; 
        			return (E)tmp.data;
        		}
            }
            else {
	            for(int h = 0; h < i-1; h++) {
	            	tmp = tmp.next;
	            }
	            prev = tmp.next;
	        	tmp.next = tmp.next.next;
	        	numItems--;
				return (E)prev.data;
            }
		}
	}
	
	
}
