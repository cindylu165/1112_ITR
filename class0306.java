public class class0306 
{
    private Node first = null;
    private int N = 0 ;
    public FixedCapacityStackOfStrings(int capacity)
    {
        s = new String[capacity];
    }
    public boolean isEmpty()
    { 
        return N == 0; 
    }
    private class Node
    {
        String item;
        Node next;
    }
    public void push(String item)
    {
        // newNode = new Node();
        // newNode.item = item;
        // newNode.item = first;
        // first = newNode;
        Node oldfirst = first;
        first = new Node();
        first.item = item;
        first.next = oldfirst;

    }
    // public String pop()
    // {
    //     String item = first.item;
    //     first = first.next;
        // return item;
    // }
    // private void resize(int capacity)
    // {
    //     String[] copy = new String[capacity];
    //     for (int i = 0; i < N; i++)
    //         copy[i] = s[i];
    //     s = copy;
    // }
    // public String pop()
    // {
    //     String item = s[--N];
    //     s[N] = null;
    //     if(N == s.length/4&&N > 0) resize(2 * s.length);
    //     return item
    // }
}
