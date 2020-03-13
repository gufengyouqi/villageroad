package com.villageroad.web.test;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

public class LinkNodeMergeTest {
    // 连表合并
    public static void main(String[] args) {
        Node n1 = new Node(1, new Node(4, null));
        Node n2 = new Node(3, null);
        Node result = merge(n1, n2);
        do{
            System.out.println(result.getVal());
            result=result.getNext();
        }while (result!=null);
        System.out.println(result);
    }

    public static Node merge(Node n1, Node n2) {
        if (null == n1) {
            return n2;
        }
        if (null == n2) {
            return n1;
        }
        if (n1.getVal() < n2.getVal()) {
            return new Node(n1.getVal(), merge(n1.getNext(), n2));
        } else {
            return new Node(n2.getVal(), merge(n1, n2.getNext()));
        }

    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class Node {
        private int val;
        private Node next;
        @Override
        public String toString(){
            if (next==null){
                return String.valueOf(val);
            }
            return next.toString();
        }
   }

}
