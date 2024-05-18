import java.lang.StringBuilder;
public class LinkedFrontBackCappedList<T> implements FrontBackCappedList<T> {

	private Node head, tail;
	private int capacity;
	private int size;

	public LinkedFrontBackCappedList(int capacity) {
		if (!isValidCapacity(capacity)) {
			throw new IllegalArgumentException("Invalid capacity: " + capacity);
		}
		this.capacity = capacity;
	}

	private boolean isValidCapacity(int capacity) {
		return capacity >= 0;
	}

	@Override
	public String toString() {
		String str = " ";
		Node<T> current = head;

		while (current != null) {
			str += current.data.toString();
			str += ", ";
			current = current.next;
		}
		return "[" + str + "]";
	}

	@Override
	public boolean addFront(T newEntry) {
		if (head == null) {
			head = new Node(newEntry);
			size++;
			return true;
		}
		else if (size < capacity) {
			head.next = head;
			head = new Node(newEntry);
			size++;
			return true;
		}
		else {
			return false;
		}
	}

    /**
     * Adds a new entry to the end of the list if the list is not full.
     * Entries currently in the list are unaffected.
     * If the entry is added, the list size is increased by 1.
     *
     * @param newEntry The object to be added as a new entry.
     * @return true if the object was added, false if the list was full and thus the object was not added
     */
	@Override
    public boolean addBack(T newEntry) {
		if (size >= capacity) {
			return false;
		}
		Node<T> newNode = new Node<>(newEntry);
		if (isEmpty()) {
			head = newNode;
			tail = newNode;
		}
		else if (tail == null) {
			tail = newNode;
		}
		else {
			tail.next = newNode;
			tail = newNode;
		}
		size++;
        return true;
    }


    /**
     * Removes an entry from the beginning of the list.
     * Entries currently in the list are shifted up.
     * If an entry is removed, the list size is decreased by 1.
     *
     * @return A reference to the removed entry or null if the list is empty.
     */
	@Override
    public T removeFront() {
		if (isEmpty()) {
			return null;
		}
        T returnVal = (T) head.getData();
		head = head.next;
		size--;
		return returnVal;
    }

    /**
     * Removes an entry from the end of the list.
     * Entries currently in the list are unaffected.
     * If an entry is removed, the list size is decreased by 1.
     *
     * @return A reference to the removed entry or null if the list is empty.
     */
	@Override
    public T removeBack() {
		if (isEmpty()) {
			return null;
		}

		T returnVal;

		if (size == 1) {
			returnVal = (T) head.getData();
			head = null;
			tail = null;
		} else {
			Node<T> secondToLast = head;
			while (secondToLast.getNextNode() != tail) {
				secondToLast = secondToLast.getNextNode();
			}

			returnVal = (T) tail.getData();
			tail = secondToLast;
			tail.setNextNode(null);
		}

		size--;
		return returnVal;
    }


    /**
     * Removes all entries from this list.
     */
	@Override
    public void clear() {
		head = null;
		tail = null;
		size = 0;
    }


    /**
     * Retrieves the entry at a given position in this list.
     *
     * @param givenPosition An integer that indicates the position of the desired entry.
     * @return A reference to the indicated entry or null if the index is out of bounds.
     */
	@Override
    public T getEntry(int givenPosition) {
		if (!isValidPosition(givenPosition)) {
			return null;
		}
		Node<T> current = head;
		if (current == null) {
			return null;
		}
		for (int i = 0; i < givenPosition; i++) {
			current = current.next;
		}
		return current.data;
    }

	private boolean isValidPosition(int position) {
		return position >= 0 && position < size;
	}

    /**
     * Determines the position in the list of a given entry.
     * If the entry appears more than once, the first index is returned.
     *
     * @param anEntry the object to search for in the list.
     * @return the first position the entry that was found or -1 if the object is not found.
     */
	@Override
    public int indexOf(T anEntry) {
		int index = 0;
		Node<T> current = head;
		while (current != null) {
			if (current.data.equals(anEntry)) {
				return index;
			}
			index++;
			current = current.next;
		}
		return -1;
    }

    /**
     * Determines the position in the list of a given entry.
     * If the entry appears more than once, the last index is returned.
     *
     * @param anEntry the object to search for in the list.
     * @return the last position the entry that was found or -1 if the object is not found.
     */
	@Override
    public int lastIndexOf(T anEntry) {
		int index = 0;
		int lastIndex = -1;
		Node<T> current = head;
		while (current != null) {
			if (current.data.equals(anEntry)) {
				lastIndex = index;
			}
			index++;
			current = current.next;
		}

        return lastIndex;
    }

    /**
     * Determines whether an entry is in the list.
     *
     * @param anEntry the object to search for in the list.
     * @return true if the list contains the entry, false otherwise
     */
	@Override
    public boolean contains(T anEntry) {
		Node <T> current = head;
		while (current != null) {
			if (current.getData().equals(anEntry)) {
				return true;
			}
			current = current.next;
		}
        return false;
    }


    /**
     * Gets the length of this list.
     *
     * @return The integer number of entries currently in the list.
     */
	@Override
    public int size() {
        return size;
    }

    /**
     * Checks whether this list is empty.
     *
     * @return True if the list is empty, or false if the list contains one or more elements.
     */
    public boolean isEmpty() {
		return head == null;
    }

    /**
     * Checks whether this list is full.
     *
     * @return True if the list is full, or false otherwise.
     */
    public boolean isFull() {
		return size == capacity;
    }



    public static class Node<T> {
		public T data;
		public Node next;

		private Node(T dataValue) {
			data = dataValue;
			next = null;
		}

		private Node(T dataValue, Node nextNode) {
			data = dataValue;
			next = nextNode;
		}

		private T getData() {
			return data;
		}

		private void setData(T newData) {
			data = newData;
		}

		private Node getNextNode() {
			return next;
		}

		private void setNextNode(Node nextNode) {
			next = nextNode;
		}
	}
}
