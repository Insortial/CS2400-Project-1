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
        print(bag1.union(bag2));
        System.out.print("Intersection: ");
        print(bag1.intersection(bag2));
        System.out.print("Difference: ");
        print(bag1.difference(bag2));
        System.out.print("Difference 2: ");
        print(bag2.difference(bag1));
    }

    public static void print(BagInterface<String> bag) 
    {
        int counter = 0;
        while(counter < bag.checkEntries())
        {
            System.out.print(bag.removeEntry(counter) + ", ");
        }
        System.out.println();
    }
}