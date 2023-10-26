package com.villageroad.web.test;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
class MergeLink {
    private Integer data;   //存储数据
    private MergeLink next;   //后继引用

    public MergeLink(Integer data) {
        this.data = data;
    }

    public MergeLink(Integer data, MergeLink next) {
        this.data = data;
        this.next = next;
    }

    public static void main(String[] args) {
        MergeLink node1 = readyNode1();
        MergeLink node2 = readyNode2();
        MergeLink node = mergeNode(node1, node2);
        while (node != null) {
            System.out.println(node.getData());
            node = node.next;
        }

    }

    public static MergeLink mergeNode(MergeLink node1, MergeLink node2) {
        if (node1 == null) {
            return node2;
        }
        if (node2 == null) {
            return node1;
        }
        if (node1.getData() > node2.getData()) {
            return new MergeLink(node2.getData(),mergeNode(node1, node2.next));
        }else {
            return new MergeLink(node1.getData(),mergeNode(node1.next, node2));
        }
    }

    //准备第一个有序链表
    public static MergeLink readyNode1() {
        MergeLink node = new MergeLink(1);
        MergeLink node1 = new MergeLink(6);
        MergeLink node2 = new MergeLink(9);
        MergeLink node3 = new MergeLink(10);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        return node;
    }

    //准备第二个有序链表
    public static MergeLink readyNode2() {
        MergeLink node = new MergeLink(2);
        MergeLink node1 = new MergeLink(3);
        MergeLink node2 = new MergeLink(8);
        MergeLink node3 = new MergeLink(14);
        MergeLink node4 = new MergeLink(22);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        return node;
    }
}
