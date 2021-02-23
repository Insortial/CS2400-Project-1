public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;
    private int numberOfEntries;

    /** Constructor for LinkedBag */
    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    /** Adds a new entry to this bag.
    * @param newEntry  The object to be added as a new entry
    * @return  True if the addition is successful, or false if not. */
    public boolean add(T entry)        // OutOfMemoryError possible
    {
            // Add to beginning of chain:
        Node newNode = new Node(entry);
        newNode.next = firstNode; // Make new node reference rest of chain
                    // (firstNode is null if chain is empty)        
        
        firstNode = newNode;      // New node is at beginning of chain
        numberOfEntries++;
            
        return true;
    } // end add
    
    /** Removes one unspecified entry from this bag, if possible.
    * @return  Either the removed entry, if the removal was successful, or null. */
    public T remove()
    {
        T result = null;
        if (firstNode != null)
        {
            result = firstNode.getData();
            firstNode = firstNode.getNextNode(); // Remove first node from chain
            numberOfEntries--;
        } // end if
        return result;
    } // end remove

    /** Removes one occurrence of a given entry from this bag, if possible.
    * @param anEntry  The entry to be removed.
    * @return  True if the removal was successful, or false otherwise. */
   public boolean remove(T anEntry)
   {
      boolean result = false;
      Node nodeN = getReferenceTo(anEntry);
      
      if (nodeN != null)
      {
	// Replace located entry with entry in first node
         nodeN.setData(firstNode.getData()); 
	// Remove first node
         firstNode = firstNode.getNextNode(); 

	    numberOfEntries--;
         
         result = true;
      } // end if
  
      return result;
   } // end remove

   /**Gets the number of entries in the bag
    * @return the integer number of entries currently in this bag. */
   public int checkEntries()
   {
       return numberOfEntries;
   }

   /** Sees whether this bag is empty.
    * @return True if bag is empty, or false if not. */
    public boolean isEmpty()
    {
        return numberOfEntries == 0;
    }

    /** Locates a given entry within this bag.
     * @param anEntry The entry to be found within the linkedBag.
     * @return the Node from the given entry */
    private Node getReferenceTo(T anEntry)
    {
        boolean found = false;
        Node currentNode = firstNode;

        while (!found && (currentNode != null))
        {
            if (anEntry.equals(currentNode.getData()))
                found = true;
            else
                currentNode = currentNode.getNextNode();
        } // end while
            
        return currentNode;
    } // end getReferenceTo

    /** Retrieves all entries that are in this bag.
    * @return A newly allocated array of all the entries in this bag. */
    public T[] toArray()
    {
        @SuppressWarnings("unchecked")
        T[] result = (T[]) new Object[numberOfEntries];

        int index = 0;
        Node currentNode = firstNode;
        while ((index < numberOfEntries) && (currentNode != null))
        {
            result[index] = currentNode.getData();
            index++;
            currentNode = currentNode.getNextNode();
        }
        return result;
    }

    /** Gets the contents of two collections
    * @param bag2 used to combine with the first bag
    * @return new collection with the contents of both bags */
    public BagInterface<T> union(BagInterface<T> bag2)
    {
        BagInterface<T> newBag = new LinkedBag<>(); //creates a new bag to be returned.
        T[] array1 = toArray();
        T[] array2 = bag2.toArray();
        int entries = 0;

        if(checkEntries() > bag2.checkEntries()) //compares which bag is larger in order to iterate through all possible entries and add it to the new bag,
            entries = checkEntries();
        else
            entries = bag2.checkEntries();

        for(int counter = 0; counter < entries; counter++) //adds both bags to the new bag simultaneously.
        {
            if(counter < array1.length)     //checks if the counter does not surpass the size of the array
                newBag.add(array1[counter]);
            if(counter < array2.length)
                newBag.add(array2[counter]);
        }
        
        return newBag;
    }


    /** Gets the contents of two collections that occur in both
    * @param bag2 used to combine with the first bag
    * @return new collection with the contents of both bags that occur in both */
    public BagInterface<T> intersection(BagInterface<T> bag2)
    {
        BagInterface<T> newBag = new LinkedBag<>(); //creates a new bag to be returned.
        T[] array1 = toArray();
        T[] array2 = bag2.toArray();

        for(int counter = 0; counter < checkEntries(); counter++)
        {
            for(int counter2 = 0; counter2 < bag2.checkEntries(); counter2++)
            {
                if(array1[counter] == array2[counter2] && array1[counter] != null)  //compares the two bags and checks if they have equal entries, then adds it to the new bag.
                {
                    newBag.add(array1[counter]);
                    array2[counter2] = null;
                }
            }
        }
        
        return newBag;
    }

    /** Gets the contents that would be left in one collection after removing that which occurs in the second
    * @param bag2 used to combine with the first bag
    * @return new collection with the difference of the bag receiving the call with the bag placed in the parameter */
    public BagInterface<T> difference(BagInterface<T> bag2)
    {
        LinkedBag<T> newBag = new LinkedBag<>(); //creates a new bag to be returned.
        T[] array1 = toArray();
        T[] array2 = bag2.toArray();
        
        for(int counter = 0; counter < array1.length; counter++) //adds the content of the bag where the function is being called from to the new bag.
        {
            newBag.add(array1[counter]);
        }
        
        for(int counter = 0; counter < array1.length; counter++)
        {
            for(int counter2 = 0; counter2 < array2.length; counter2++)
            {
                if(array1[counter] == array2[counter2] && array2[counter2] != null) //removes entries that are the same in both the bag where the function is being called from and the bag being passed in via parameter.
                {
                    newBag.remove(array1[counter]);
                    array2[counter2] = null;
                    break;
                }
            }
        }
        
        return newBag;
    }

    private class Node
    {
        private T data;
        private Node next;

        /** Constructor for node class
         * @param dataPortion data to be stored within Node*/
        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }

        /** Constructor for node class
         * @param dataPortion data to be stored within Node 
         * @param nextNode node that is next to this current node*/
        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }

        /** Gets the data found within this node
         * @return the data stored within the node*/
        private T getData()
        {
            return data;
        }

        /** Sets the data of this node
         * @param newData */
        private void setData(T newData)
        {
            data = newData;
        }

        /** Gets the next node
         * @return the nextNode */
        private Node getNextNode()
        {
            return next;
        }
    }
}