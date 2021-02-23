import java.util.Arrays;

public class ResizeableArrayBag<T> implements BagInterface<T>
{
    private T[] bag;
    private static final int DEFAULT_CAPACITY = 25;
    private int CURRENT_CAPACITY;
    private int numberOfEntries;
    private boolean integrityOK = false;
    private static final int MAX_CAPACITY = 10000;



    /**Creates an empty bag with initial capacity 25 */
    public ResizeableArrayBag()
    {
        this(DEFAULT_CAPACITY);
    }

	/** Creates an empty bag having a given capacity.
    * @param desiredCapacity  The integer capacity desired. */
    public ResizeableArrayBag(int desiredCapacity)
    {
        if (desiredCapacity <= MAX_CAPACITY)
        {
            // The cast is safe because the new array contains null entries
            @SuppressWarnings("unchecked")
            T[] tempBag = (T[])new Object[desiredCapacity]; // Unchecked cast
            bag = tempBag;
            CURRENT_CAPACITY = desiredCapacity;
            numberOfEntries = 0;
            integrityOK = true;
        }
        else
            throw new IllegalStateException("Attempt to create a bag whose" +  "capacity exceeds allowed maximum.");	
    } // end constructor
   

   /** Throws an exception if this object is not initialized. */
   private void checkIntegrity()
   {
      if (!integrityOK)
         throw new SecurityException("ArrayBag object is corrupt.");
   } // end checkIntegrity


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

    /** Gets the contents that would be left in one collection after removing that which occurs in the second
        @returns new collection with the difference of the bag receiving the call with the bag placed in the parameter */
    public int checkEntries()
    {
        return numberOfEntries;
    }

    /** Converts bag into an array.
     * @return array that contains the entries stored in the bags. */
    public T[] toArray()
    {
        return Arrays.copyOf(bag, CURRENT_CAPACITY);
    }

    /** Copies the array from the array given in the parameter to the bag that the function is called from.
     * @param bag2 the array that will be copied. */
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

   /** Adds a new entry to this bag.
       @param newEntry  The object to be added as a new entry.
       @return  True.  */
    public boolean add(T newEntry)
    {
        checkIntegrity();
        if (isFull())
        {
        resize();
        } // end if

        bag[numberOfEntries] = newEntry;
        numberOfEntries++;

        return true;
    } // end add
    
    /** Provides the index in the bag of a given entry
     * @param anEntry the entry you want to find the index of
     * @return the index of the entry that you provide in the parameter. */
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

    /** Removes one unspecified entry from this bag, if possible.
     @return  Either the removed entry, if the removal was successful,
    or null otherwise. */
    public T remove()
    {
        checkIntegrity();
        T result = removeEntry(numberOfEntries - 1);		
        return result;
    } // end remove
            

	/** Removes one occurrence of a given entry from this bag.
    @param anEntry  The entry to be removed.
    @return  True if the removal was successful, or false if not. */
    public boolean remove(T entry)
    {
        int index = getIndexOf(entry);
        T result = removeEntry(index);
        return entry.equals(result);
    }

    /**Removes and returns the entry at a given index within the array bag. If no such entry exists, returns null.
     * @param givenIndex represents a position where you want to remove an entry. 
     * @return the entry that is removed from the bag. */
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
    * @param bag2 used to combine with the first bag
    * @returns new collection with the contents of both bags */
    public BagInterface<T> union(BagInterface<T> bag2)
    {
        ResizeableArrayBag<T> newBag = new ResizeableArrayBag<>(); //creates a new bag to be returned.
        T[] otherBag = bag2.toArray();  
        newBag.copy(bag);               //copies the bag from the object that this object is being called on .

        for(int counter = 0; counter < bag2.checkEntries(); counter++) //adds entries from bag inserted via parameter to the bag with copied entries.
        {
            newBag.add(otherBag[counter]);
        }
        
        return newBag;
    }

    /** Gets the contents of two collections that occur in both
    * @param bag2 used to combine with the first bag
    * @returns new collection with the contents of both bags that occur in both */
    public BagInterface<T> intersection(BagInterface<T> bag2)
    {
        ResizeableArrayBag<T> newBag = new ResizeableArrayBag<>(); //creates a new bag to be returned.
        T[] otherBag = bag2.toArray();

        for(int counter = 0; counter < checkEntries(); counter++)
        {
            for(int counter2 = 0; counter2 < bag2.checkEntries(); counter2++)
            {
                if(bag[counter] == otherBag[counter2] && bag[counter] != null) //compares the two bags and checks if they have equal entries, then adds it to the new bag.
                {
                    newBag.add(bag[counter]);
                    otherBag[counter2] = null; 
                }
            }
        }

        return newBag;
    }

    /** Gets the contents that would be left in one collection after removing that which occurs in the second
    * @param bag2 used to combine with the first bag
    * @returns new collection with the difference of the bag receiving the call with the bag placed in the parameter */
    public BagInterface<T> difference(BagInterface<T> bag2)
    {
        ResizeableArrayBag<T> newBag = new ResizeableArrayBag<>(); //creates a new bag to be returned.
        newBag.copy(bag);                                          //copies the bag from the object that this object is being called on .
        T[] otherBag = bag2.toArray();

        for(int counter = 0; counter < bag.length; counter++)
        {
            for(int counter2 = 0; counter2 < otherBag.length; counter2++)
            {
                if(bag[counter] == otherBag[counter2] && bag[counter] != null) //checks if the other bag has the same entry and then removes the entry from the newBag.
                {
                    newBag.remove(bag[counter]); 
                    otherBag[counter2] = null;
                    break;
                }
            }
        }

        return newBag;
    }
}