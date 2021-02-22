public class LinkedBagTest
{
    public static void main(String args[]) 
    {
        BagInterface<String> bag1 = new LinkedBag<String>();
        BagInterface<String> bag2 = new LinkedBag<String>();
        
        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag1.add("d");

        bag2.add("a");
        bag2.add("s");
        bag2.add("c");
        bag2.add("n");

        System.out.println("bag1:");
        displayBag(bag1);
        System.out.println("bag2:");
        displayBag(bag2);
        System.out.println("Union:");
        displayBag(bag1.union(bag2));
        System.out.println("Intersection:");
        displayBag(bag1.intersection(bag2));
        System.out.println("Difference:");
        displayBag(bag1.difference(bag2));
    }

    public static void displayBag(BagInterface<String> bag) 
    {
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = bag.toArray();
        for (int index = 0; index < bagArray.length; index++)
        {
            System.out.print(bagArray[index] + " ");
        } // end for

        System.out.println();
    }
}