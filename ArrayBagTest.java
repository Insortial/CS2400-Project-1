public class ArrayBagTest
{
    public static void main(String args[]) 
    {
        BagInterface<String> bag1 = new ResizeableArrayBag<String>();
        BagInterface<String> bag2 = new ResizeableArrayBag<String>();

        //adds entries into bag 1.
        bag1.add("a");
        bag1.add("b");
        bag1.add("b");
        bag1.add("c");
        bag1.add("c");
        bag1.add("c");
        bag1.add("d");

        //adds entries into bag 2.
        bag2.add("z");
        bag2.add("b");
        bag2.add("b");
        bag2.add("c");
        bag2.add("c");
        bag2.add("x");

        //displays entries of both bags.
        System.out.println("bag1:");
        displayBag(bag1);
        System.out.println("bag2:");
        displayBag(bag2);

        //Creates intended answer arrays for each function and uses it to compare to output of each function.
        Object[] inAnswer1 = {"a", "b", "b", "c", "c", "c", "d", "z", "b", "b", "c", "c", "x"};
        System.out.print("Union: ");
        displayBag(bag1.union(bag2), inAnswer1);

        Object[] inAnswer2 = {"b", "b", "c", "c"};
        System.out.print("Intersection: ");
        displayBag(bag1.intersection(bag2), inAnswer2);

        Object[] inAnswer3 = {"a", "d", "c"};
        System.out.print("Difference: ");
        displayBag(bag1.difference(bag2), inAnswer3);

        Object[] inAnswer4 = {"z", "x"};
        System.out.print("Difference 2: ");
        displayBag(bag2.difference(bag1), inAnswer4);
    }

    /** Displays the contents of a bag and checks if output is equal to intended answer.
     * @param bag the bag to be displayed in console.
     * @param intendedAnswer array to check if intended answer equals outputted answer. */
    public static void displayBag(BagInterface<String> bag, Object[] intendedAnswer) 
    {
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = bag.toArray();
        boolean testStatus = true;
        for (int index = 0; index < bagArray.length; index++) //iterates through bag's array
        { 
            if(bagArray[index] != null) //checks if bag entry is null or not
            {
                System.out.print(bagArray[index] + " ");
                if(bagArray[index] != intendedAnswer[index]) //compares if bag's entry is not equal to indendedAnswer's entry, if it is not equal return false
                    testStatus = false;
            }
     
            
        } // end for
        System.out.println();
        System.out.println("Passed Test: " + testStatus + "\n");
    }

    /** Displays the contents of a bag and checks if output is equal to intended answer.
     * @param bag the bag to be displayed in console. */
    public static void displayBag(BagInterface<String> bag) 
    {
        System.out.println("The bag contains the following string(s):");
        Object[] bagArray = bag.toArray();
        for (int index = 0; index < bagArray.length; index++) //iterates through bag's array
        { 
            if(bagArray[index] != null) //checks if bag entry is null or not
            {
                System.out.print(bagArray[index] + " ");
            }
     
            
        } // end for
        System.out.println("\n");
    }
}