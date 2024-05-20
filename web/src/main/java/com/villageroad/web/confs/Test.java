package com.villageroad.web.confs;

import lombok.Data;

public class Test {
    // 单向链表
//    a->b->c
    @Data
    public static class Node{
        private int val;
        private Node next;
        public Node(int val, Node next){
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {

    }

    public static Node findLastNode(Node root, int index){
        Node tmp = new Node(0,root);
        if(null==root){
            return null;
        }
        if(root.getNext()==null&&index==1){
            return root;
        }

        int num =0;
        while (tmp.getNext()!=null){
            num++;
            tmp = tmp.getNext();
        }
        if(num == index){
            return root;
        }
        if(num<index){
            return null;
        }
        Node tmpNode = new Node(0,root);
        int i=1;
        while (tmpNode.getNext()!=null&& num-index!=i){
        i++;
        tmpNode = tmpNode.getNext();

        }

        return tmpNode;
    }
}
