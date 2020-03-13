package com.villageroad.web.test;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) {
            return list2;
        } else if (list2 == null) {
            return list1;
        } else if (list1.val < list2.val) {
            list1.next = this.mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    @Data
    public static class Node{
        private int val;
        private Node left;
        private Node right;
    }
//    从前序与中序遍历序列构造二叉树
    public static Node buildNode(List<Integer> list, int index){
        Node node = new Node();
        if(list.size()<=index){
            return null;
        }
        node.setVal(list.get(index));
        if((index+1)%3==1){
            node.setLeft(buildNode(list,index+1));
        }
        if((index+2)%3==2) {
            node.setRight(buildNode(list, index + 2));
        }
        return node;

    }

    public static void main(String[] args) {
        List<Integer> preorder = Arrays.asList(3,9,20,15,7);
        Node node = buildNode(preorder, 0);
        System.out.println(node);
    }
}