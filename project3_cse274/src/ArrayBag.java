import java.sql.Array;
/**
 * 
 * @author Patrick Murray
 * Date: 10/14/21
 * This this the implementation of the ArrayBag class
 * @param <E>
 */
public class ArrayBag <E> implements BagInterface <E>{
	private static final int defCapVar = 10;
	private E[] newBag;
	private int capacity;
	private int numItems;
	
	public ArrayBag() {
		this(defCapVar);
	}

	public ArrayBag(int capacity) {
		if(capacity < 5)
			throw new IllegalArgumentException();
		this.capacity = capacity;
		this.newBag = (E[])new Object[capacity];
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
		if(this.capacity == this.size())
			resize();
		for(int i = 0; i <= this.size(); i++) {
			if(i == this.size()) {
				this.newBag[i] = newEntry;
				numItems++;
				return true;
			}
		}
		return false;
	}

	@Override
	public E remove() {
		if(this.isEmpty())
			return null;
		E hold = newBag[this.size()];
		newBag[this.size()] = null;
		numItems--;
		if(this.size() < (capacity/2)-1)
			resize2();
		return hold;
	}

	@Override
	public boolean remove(E anEntry) {
		E[] tmp = (E[])new Object[capacity];
		if(this.isEmpty())
			return false;
		for(int i = 0; i < this.size(); i++) {
			if((String)this.newBag[i] != (String)anEntry) {
				tmp[i] = this.newBag[i];
			}
		}
		newBag = tmp;
		if(this.size() < (capacity/2)-1)
			resize2();
		if(this.contains(anEntry))
			return false;
		numItems--;
		return true;
	}

	@Override
	public void clear() {
		E[] tmp = (E[])new Object[capacity];
		for(int i = 0; i < numItems; i++) {
			tmp[i] = null;
		}
		newBag = tmp;
		numItems = 0;
	}

	@Override
	public int getFrequencyOf(E anEntry) {
		int hold = 0;
		for(int i = 0; i < this.size(); i++) {
			if(newBag[i].equals(anEntry))
				hold++;
		}
		return hold;
	}

	@Override
	public boolean contains(E anEntry) {
		for(int i = 0; i < this.size(); i++) {
			if(newBag[i] == anEntry)
				return true;
		}
		return false;
	}

	@Override 
	public E[] toArray() {
		String[] tmp = new String[this.size()];
		for(int i = 0; i < this.size(); i++) {
			tmp[i] = (String)newBag[i];
		}
		return (E[])tmp;
	}
	
	@Override
	public void removeDuplicates() {
		int i = 0, j;
		while (i < this.size() - 2) {
			j = i + 1;
			while (j < this.size()-1) {
				while (newBag[i] == newBag[j]) {
					if (newBag[i] == null & newBag[j] == null)
						break;
					remove(j);
				}
				j++;
			}
			i++;
		}
	}

	private void remove(int index) {
	if (index < 0 || index >= newBag.length) return;
	for (int i = index; i < newBag.length - 1; i++)
		newBag[i] = newBag[i + 1];
	newBag[newBag.length - 1] = null;
	numItems--;
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
		for(int i = 0; i < this.size(); i++) {
			if(this.contains(newBag[i]) && aBag.contains(tmp[i]))
				count++;
		}
		if(count == this.size()) 
			return true;
		return false;
	}
	
	public void resize() {
		E[] tmp = (E[])new Object[capacity*2];
		this.capacity = capacity*2;
		for(int i = 0; i < numItems; i++) {
			tmp[i] = this.newBag[i];
		}
		newBag = tmp;
	}
	
	public void resize2() {
		E[] tmp = (E[])new Object[capacity/2];
		if(this.newBag.length < 5) 
			return;
		this.capacity = capacity/2;
		for(int i = 0; i < numItems; i++) {
			tmp[i] = this.newBag[i];
		}
		newBag = tmp;
	}
	
}
