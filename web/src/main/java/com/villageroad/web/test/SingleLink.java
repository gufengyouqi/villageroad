package com.villageroad.web.test;

import lombok.Data;

public class SingleLink {
    @Data
    public static class Node{
        private Integer val;
        private Node next;
        public Node(){}
        public Node(Integer val,Node node){
            this.val = val;
            this.next = node;
        }
    }
    /**
     * 赋值法 翻转
     * @param node
     * @return
     */
    public static Node reverseList(Node node){
        Node res = null;
        while(node!=null){
            res = new Node(node.getVal(),res);
            node = node.getNext();
        }
        return res;
    }
    /**
     * 翻转颠倒
     * @param head
     * @return
     */
    public static Node reverseList1(Node head){
        Node pre = null;
        Node next = null;
        while(head!=null){
            next = head.next;
            head.next = pre;
            pre = head;
            head = next;
        }

        return pre;
    }

    public static void main(String[] args) {
        Node n = new Node(0,null);
        Node n1 = new Node(1, n);
        Node n2 = new Node(2, n1);
        Node n3 = new Node(3, n2);
        printInfo(n3);

        Node res = reverseList(n3);
        Node node = reverseList1(n3);
        printInfo(res);
        printInfo(node);
    }

    private static void printInfo(Node res) {
        Node tmp;
        tmp = res;
        while (tmp != null){
            System.out.print(tmp.val+",");
            tmp = tmp.getNext();
        }
    }
}
