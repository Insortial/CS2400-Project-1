public class LinkedBag<T> implements BagInterface<T>
{
    private Node firstNode;
    private int numberOfEntries;
    public LinkedBag()
    {
        firstNode = null;
        numberOfEntries = 0;
    }

    /** Adds a new entry to this bag.
    @param newEntry  The object to be added as a new entry
    @return  True if the addition is successful, or false if not. */
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
    @return  Either the removed entry, if the removal was successful, or null. */
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


    private class Node
    {
        private T data;
        private Node next;

        private Node(T dataPortion)
        {
            this(dataPortion, null);
        }

        private Node(T dataPortion, Node nextNode)
        {
            data = dataPortion;
            next = nextNode;
        }

        private T getData()
        {
            return data;
        }

        private void setData(T newData)
        {
            data = newData;
        }

        private Node getNextNode()
        {
            return next;
        }

        private void setNextNode(Node nextNode)
        {
            next = nextNode;
        }
    }
}