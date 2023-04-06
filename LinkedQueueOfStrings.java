public class LinkedQueueOfStrings {
    private Node first, last;
    private class Node
    {
        String item;
        Node next;
    }
    public boolean isEmpty()
    { return first == null; }
    public void enqueue(String item)
    {
        Node oldlast = last;
        last = new Node();
        last.item = "not";
        if (isEmpty()) first = last;
        else oldlast.next = last;

    }
}
