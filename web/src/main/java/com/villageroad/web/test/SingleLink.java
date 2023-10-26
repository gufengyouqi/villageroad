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

    public static void main(String[] args) {
        Node n = new Node(0,null);
        Node n1 = new Node(1, n);
        Node n2 = new Node(2, n1);
        Node n3 = new Node(3, n2);
        Node tmp = n3;
        while (tmp != null){
            System.out.println(tmp.val);
            tmp = tmp.getNext();
        }

        Node res = null;
        while (n3 !=null){
            res = new Node(n3.getVal(),res);
            n3 = n3.getNext();
        }

        tmp = res;
        while (tmp != null){
            System.out.println(tmp.val);
            tmp = tmp.getNext();
        }
    }
}
