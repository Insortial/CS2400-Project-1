public interface BagInterface<T> {

    /** Gets the contents of two collections
        @param bag2 used to combine with the first bag
        @returns new collection with the contents of both bags */
    public BagInterface<T> union(BagInterface<T> bag2);

    /** Gets the contents of two collections that occur in both
        @param bag2 used to combine with the first bag
        @returns new collection with the contents of both bags that occur in both */
    public BagInterface<T> intersection(BagInterface<T> bag2);

    /** Gets the contents that would be left in one collection after removing that which occurs in the second
        @param bag2 used to combine with the first bag
        @returns new collection with the difference of the bag receiving the call with the bag placed in the parameter */
    public BagInterface<T> difference(BagInterface<T> bag2);

    /** Gets the contents that would be left in one collection after removing that which occurs in the second
        @returns new collection with the difference of the bag receiving the call with the bag placed in the parameter */
    public int checkEntries();
    
    public T[] toArray();

    /** Removes one unspecified entry from this bag, if possible.
       @return  Either the removed entry, if the removal was successful,
                or null otherwise. */
	public T remove();

    /** Adds a new entry to this bag.
    @param newEntry  The object to be added as a new entry
    @return  True if the addition is successful, or false if not. */
    public boolean add(T entry);

    public boolean isEmpty();
}