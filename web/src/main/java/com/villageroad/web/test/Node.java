package com.villageroad.web.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class Node {
    private Integer data;   //存储数据
    private Node next;   //后继引用

    public Node(Integer data) {
        this.data = data;
    }

    public Node(Integer data, Node next) {
        this.data = data;
        this.next = next;
    }

    public static void main(String[] args) {
        Node node1 = readyNode1();
        Node node2 = readyNode2();
        Node node = mergeNode(node1, node2);
        while (node != null) {
            System.out.println(node.getData());
            node = node.next;
        }

    }

    public static Node mergeNode(Node node1, Node node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (node1.getData() > node2.getData()) {
            return new Node(node2.getData(),mergeNode(node1, node2.next));
        }else {
            return new Node(node1.getData(),mergeNode(node1.next, node2));
        }
    }

    //准备第一个有序链表
    public static Node readyNode1() {
        Node node = new Node(1);
        Node node1 = new Node(6);
        Node node2 = new Node(9);
        Node node3 = new Node(10);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        return node;
    }

    //准备第二个有序链表
    public static Node readyNode2() {
        Node node = new Node(2);
        Node node1 = new Node(3);
        Node node2 = new Node(8);
        Node node3 = new Node(14);
        Node node4 = new Node(22);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return node;
    }
}
