import java.util.Arrays;

public class ArrayBag<T> implements BagInterface<T> {

    private static final int DEFAULT_CAPACITY = 25;
    private T[] bag;
    private int numOfEntries;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;

    // Default Constructor
    public ArrayBag() {
        this(DEFAULT_CAPACITY); // Passes down int to another constructer with int param
    }

    public ArrayBag(int capacity) {
        if (capacity <= MAX_CAPACITY) {
            @SuppressWarnings("unchecked")
            T[] temp = (T[]) new Object[capacity]; // Unchecked cast
            bag = temp;
            numOfEntries = 0;
            integrityOK = true;
        }
        else {
            throw new IllegalStateException("Attempt to create a bag whose capacity exceeds allowed maximum.");
        }
    }

    /** Gets the current number of entries in this bag.
    @return The integer number of entries currently in the bag. */
    public int getCurrentSize() {
        return numOfEntries;
    }

    /** Sees whether this bag is empty.
    @return True if the bag is empty, or false if not. */
    public boolean isEmpty() {
        return numOfEntries == 0;
    }

    /** Adds a new entry to this bag.
    @param newEntry The object to be added as a new entry.
    @return True if the addition is successful, or false if not. */
    public boolean add(T newEntry) {
        boolean result = true;
        if(isArrayFull())
            doubleCapacity();
        bag[numOfEntries] = newEntry;
        numOfEntries++;

        return result;
    }

    /** Removes all entries within the array */
    public void clear() {
        while(!isEmpty())
            remove();
    }

    /** Tests whether this bag contains a given entry.
    @param anEntry The entry to find.
    @return True if the bag contains anEntry, or false if not. */
    public boolean contains(T anEntry) {
        checkIntegrity();
        return getIndexOf(anEntry) > -1;
    }

    /** Counts the number of times a given entry appears in this bag.
    @param anEntry The entry to be counted.
    @return The number of times anEntryappears in the bag. */
    public int getFrequencyOf(T anEntry) {
        checkIntegrity();
        int counter = 0;

        for(int i = 0; i < numOfEntries; i++)
            if (anEntry.equals(bag[i]))
                counter++;
        return counter;
    }

    /** Removes one unspecified entry from this bag, if possible.
     @return Either the removed entry, if the removal was successful, or null otherwise. */
    public T remove() {
        checkIntegrity();
        T result = removeEntry(numOfEntries - 1);
        return result;
    }

    /** Removes one occurrence of a given entry from this bag, if possible.
    @param anEntry The entry to be removed.
    @return True if the removal was successful, or false if not. */
    public boolean remove(T anEntry) {
        checkIntegrity();
        int index = getIndexOf(anEntry);
        T result = removeEntry(index);
        return result != null && anEntry.equals(result);
    }

    /**
     * Retrieves all entries that are in this bag.
     * 
     * @return A newly allocated array of all the entries in the bag.
     *         Note: If the bag is empty, the returned array is empty.
     */
    public T[] toArray() {
        //The cast is safe because the new array, contains null entries
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numOfEntries];

        for (int index = 0; index < result.length; index++) {
            result[index] = bag[index];
        }
        return result;
    }

    /**
     * Checks if the bag is full
     * @return True or False if the number of entries is equal or greater than the bag capacity
     */
    private boolean isArrayFull() {
        return numOfEntries >= bag.length;
    }

    // Throws an exception if this object is not initialized
    private void checkIntegrity() {
        if (!integrityOK) {
            throw new SecurityException("Bag Object is corrupt");
        }
    }

    

    // Removes and returns the entry at a given index within the array bag.
    // If no such entry exist, returns null
        // Precondition: 0 <= givenIndex < numberOfEntries;
    //            checkIntegrity has been called.
    private T removeEntry(int givenIndex) {
        T result = null;

        if (!isEmpty() && (givenIndex >= 0)) {
            result = bag[givenIndex]; // Entry to remove
            bag[givenIndex] = bag[numOfEntries - 1]; // Replace entry with the last entry
            bag[numOfEntries - 1] = null; // Remove last entry
            numOfEntries--;
        }
        return result;
    }

    
    // Locates a given array within the array bag.
    // Returns the index of the entry, if located, or -1 otherwise.
    // Precondition: checkIntegrity has been called.
    private int getIndexOf(T anEntry) {
        int where = -1;
        boolean found = false;
        int index = 0;

        while(!found && (index < numOfEntries)) {
            if(anEntry.equals(bag[index])) {
                found = true;
                where = index;
            }
            index++;
        }
        // Assertion: If where > -1, anEntry is in the array bag, and it
        // equals bag[where]; otherwise, anEntry is not in the array
        return where;
    }

    

    // Throws an exception if the client requests a capacity that is too large.
    private void checkCapacity(int capacity) {
        if(capacity > MAX_CAPACITY) {
            throw new IllegalStateException("Attempt to create a bag whose capacity exeeds allowed maximum of " + MAX_CAPACITY);
        }
    }

    // Doubles the size of the array bag.
    // Precondition: checkIntegrity has been called.
    private void doubleCapacity() {
        try {
            int newLength = bag.length * 2;
            checkCapacity(newLength);
            bag = Arrays.copyOf(bag, newLength);
        } catch (OutOfMemoryError ofMemoryError) {
            ofMemoryError.printStackTrace();
            System.out.println("Cannot extend array bag. Out of Memory");
        }
    }
}
