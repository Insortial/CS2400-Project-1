import java.util.Arrays;

public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int CURRENT_CAPACITY;
    private int numberOfEntries;

    /**Creates an empty bag with initial capacity 25 */
    public ResizeableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }

    /**Creates an empty bag given a capacity
       @param capacity the initial integer capacity */
    public ResizeableArrayBag(int capacity) 
    {
        numberOfEntries = 0;
        CURRENT_CAPACITY = DEFAULT_CAPACITY;
        @SuppressWarnings("unchecked")
        T[] initialBag = (T[])new Object[capacity];
        bag = initialBag;
    }

    /** Checks if bag is full
    @returns true or false if the bag is full */
    public boolean isFull()
    {
        return numberOfEntries == CURRENT_CAPACITY;
    }    

    /** Resizes Bag */
    private void resize()
    {
        CURRENT_CAPACITY = CURRENT_CAPACITY * 2;
        bag = Arrays.copyOf(bag, CURRENT_CAPACITY);
    }    

    public int checkEntries()
    {
        return numberOfEntries;
    }

    public int getCapacity() 
    {
        return CURRENT_CAPACITY;
    }

    public T[] toArray()
    {
        return bag;
    }

    public void copy(T[] bag2)
    {
        bag = Arrays.copyOf(bag2, bag2.length);
        int counter = 0;
        while(counter < bag2.length)
        {
            if(bag2[counter] != null)
            {
                numberOfEntries++;
            }
            counter++;
        }
    }

    /** Adds an item to the bag
    @param entry what element is added to the bag */
    public boolean add(T entry)
    {
        if(isFull()) 
        {
            resize();
        } 
        bag[numberOfEntries] = entry;
        numberOfEntries++;
        return true;
    }    


    private int getIndexOf(T anEntry)
    {
        int where = -1;
        boolean found = false;
        int index = 0;

        while (!found && (index < numberOfEntries))
        {
            if (anEntry.equals(bag[index]))
            {
                found = true;
                where = index;
            } 
            index++;
        } 
    return where;
    } // end getIndexOf

	/** Removes one occurrence of a given entry from this bag.
    @param anEntry  The entry to be removed.
    @return  True if the removal was successful, or false if not. */
    public boolean remove(T entry)
    {
        int index = getIndexOf(entry);
        T result = removeEntry(index);
        return entry.equals(result);
    }

    /**Removes and returns the entry at a given index within the array bag.
       If no such entry exists, returns null.
       Preconditions: 0 <= givenIndex < numberOfEntries;
                   checkIntegrity has been called. */
    public T removeEntry(int givenIndex)
    {
            T result = null;
            if (!isEmpty() && (givenIndex >= 0))
            {
                result = bag[givenIndex];                   // Entry to remove
                bag[givenIndex] = bag[numberOfEntries - 1]; // Replace entry with last entry
                bag[numberOfEntries - 1] = null;            // Remove last entry
                numberOfEntries--;
            } 
        return result;
    } 

    /** Sees whether this bag is empty.
     @return  True if this bag is empty, or false if not. */
	public boolean isEmpty()
	{
     	return numberOfEntries == 0;
	} 


    /** Gets the contents of two collections
        @param bag2 used to combine with the first bag
        @returns new collection with the contents of both bags */
    public BagInterface<T> union(BagInterface<T> bag2)
    {
        ResizeableArrayBag<T> newBag = new ResizeableArrayBag<>();
        T[] otherBag = bag2.toArray();
        newBag.copy(bag);
        for(int counter2 = 0; counter2 < bag2.checkEntries(); counter2++)
            {
                newBag.add(otherBag[counter2]);
            }
        
        return newBag;
    }

    /** Gets the contents of two collections that occur in both
        @param bag2 used to combine with the first bag
        @returns new collection with the contents of both bags that occur in both */
    public BagInterface<T> intersection(BagInterface<T> bag2)
    {
        ResizeableArrayBag<T> newBag = new ResizeableArrayBag<>();
        T[] otherBag = bag2.toArray();
        for(int counter = 0; counter < checkEntries(); counter++)
        {
            for(int counter2 = 0; counter2 < bag2.checkEntries(); counter2++)
            {
                if(bag[counter] == otherBag[counter2] && bag[counter] != null) {
                    newBag.add(bag[counter]);
                }
            }
        }
        return newBag;
    }

    /** Gets the contents that would be left in one collection after removing that which occurs in the second
        @param bag2 used to combine with the first bag
        @returns new collection with the difference of the bag receiving the call with the bag placed in the parameter */
    public BagInterface<T> difference(BagInterface<T> bag2)
    {
        ResizeableArrayBag<T> newBag = new ResizeableArrayBag<>();
        T[] otherBag = bag2.toArray();
        newBag.copy(bag);
        for(int counter = 0; counter < bag.length; counter++)
        {
            for(int counter2 = 0; counter2 < otherBag.length; counter2++)
            {
                if(newBag.toArray()[counter] == otherBag[counter2] && otherBag[counter2] != null)
                {
                    newBag.removeEntry(counter);
                    break;
                }
            }
        }
        return newBag;
    }
}