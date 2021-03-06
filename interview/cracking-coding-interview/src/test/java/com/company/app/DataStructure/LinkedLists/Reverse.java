package com.company.app.DataStructure.LinkedLists;

import org.junit.Test;

/*
Reverse Linkedlist
 */
public class Reverse {
    static class Node{
        public int Data;
        public Node Next;
    }

    @Test
    public void Test() {
        Node head = createLinkedList(new int[]{1,2,3,4,5});
        printLinkedList(head);
        Node reversedHead = reverse(head);
        System.out.println("\nAfter reversed");
        printLinkedList(reversedHead);

    }

    static Node reverse(Node head){
        Node previous = null;
        Node next;
        Node current = head;
        while (current != null){
            next = current.Next;
            current.Next = previous;
            previous = current;
            current = next;
        }
        return previous;
    }
    static void printLinkedList(Node head){
        Node current = head;
        while(current != null){
            System.out.print(current.Data + "->");
            current = current.Next;
        }
    }
    static Node createLinkedList(int[] numbers){
        Node head = new Node();
        Node current = head;
        for(int i = 0; i < numbers.length; i++){
            current.Data = numbers[i];
            if(i < numbers.length - 1){
                current.Next = new Node();
                current = current.Next;
            }
        }
        return head;
    }
}
/*
output:
1->2->3->4->5->
After reversed
5->4->3->2->1->
 */
