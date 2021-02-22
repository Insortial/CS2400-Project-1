public class ArrayBagTest
{
    public static void main(String args[]) 
    {
        BagInterface<String> bag1 = new ResizeableArrayBag<String>();
        BagInterface<String> bag2 = new ResizeableArrayBag<String>();

        bag1.add("a");
        bag1.add("b");
        bag1.add("c");
        bag1.add("d");

        bag2.add("z");
        bag2.add("b");
        bag2.add("c");
        bag2.add("x");

        System.out.print("Union: ");
        displayBag(bag1.union(bag2));
        System.out.print("Intersection: ");
        displayBag(bag1.intersection(bag2));
        System.out.print("Difference: ");
        displayBag(bag1.difference(bag2));
        System.out.print("Difference 2: ");
        displayBag(bag2.difference(bag1));
    }

    public static void displayBag(BagInterface<String> bag) 
    {
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = bag.toArray();
        for (int index = 0; index < bagArray.length; index++)
        {
            if(bagArray[index] != null)
                System.out.print(bagArray[index] + " ");
        } // end for

        System.out.println();
    }
}