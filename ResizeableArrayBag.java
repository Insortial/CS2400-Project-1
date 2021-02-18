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


    public boolean remove(T entry)
    {
        int index = getIndexOf(entry);
        T result
    }

    /** Gets the contents of two collections
        @param bag2 used to combine with the first bag
        @returns new collection with the contents of both bags */
    public BagInterface<T> union(BagInterface<T> bag2)
    {
        ResizeableArrayBag<T> newBag = new ResizeableArrayBag<>();
        T[] otherBag = bag2.toArray();
        newBag.copy(bag);
        for(int counter = 0; counter < bag2.checkEntries(); counter++)
        {
            newBag.add(otherBag[counter]);
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
                if(bag[counter] == otherBag[counter2]) {
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

    }
    
}