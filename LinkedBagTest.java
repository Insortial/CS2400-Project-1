public class LinkedBagTest
{
    public static void main(String args[]) 
    {
        BagInterface<String> bag1 = new LinkedBag<String>();
        BagInterface<String> bag2 = new LinkedBag<String>();
        
        //adds entries into bag 1.
        bag1.add("a");
        bag1.add("b");
        bag1.add("b");
        bag1.add("b");
        bag1.add("c");
        bag1.add("d");

        //adds entries into bag 2.
        bag2.add("a");
        bag2.add("b");
        bag2.add("s");
        bag2.add("c");
        bag2.add("n");

        //displays entries of both bags.
        System.out.println("bag1:");
        displayBag(bag1);
        System.out.println("bag2:");
        displayBag(bag2);

        //Creates intended answer arrays for each function and uses it to compare to output of each function.
        Object[] inArray = {"a", "a", "b", "b", "b", "s", "b", "c", "c", "n", "d"};
        System.out.println("Union:");
        displayBag(bag1.union(bag2), inArray);

        Object[] inArray2 = {"a", "b", "c"};
        System.out.println("Intersection:");
        displayBag(bag1.intersection(bag2), inArray2);

        Object[] inArray3 = {"s", "n"};
        System.out.println("Difference:");
        displayBag(bag2.difference(bag1), inArray3);

        Object[] inArray4 = {"b", "b", "d"};
        System.out.println("Difference 2:");
        displayBag(bag1.difference(bag2), inArray4);
    }

    /** Displays the contents of a bag and checks if output is equal to intended answer.
     * @param bag the bag to be displayed in console.
     * @param intendedAnswer array to check if intended answer equals outputted answer. */
    public static void displayBag(BagInterface<String> bag, Object[] intendedAnswer) 
    {
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = bag.toArray();
        boolean testStatus = true;
        for (int index = 0; index < bagArray.length; index++)
        {
            System.out.print(bagArray[index] + " ");
            if(bagArray[index] != intendedAnswer[index]) //compares if bag's entry is not equal to indendedAnswer's entry, if it is not equal return false
                testStatus = false;
        } // end for

        System.out.println();
        System.out.println("Passed Test: " + testStatus + "\n");
    }

    /** Displays the contents of a bag.
     * @param bag the bag to be displayed in console. */
    public static void displayBag(BagInterface<String> bag) 
    {
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = bag.toArray();
        for (int index = 0; index < bagArray.length; index++) //iterates through bag's array
        { 
            System.out.print(bagArray[index] + " ");
        } // end for
        System.out.println("\n");
    }
}