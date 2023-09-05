package com.villageroad.web.test;

import lombok.Data;

/**
 * @author houshengbin
 */
@Data
public class FullTree<T> {
    private T val;
    private FullTree<T> parent;
    private FullTree<T> left;
    private FullTree<T> right;

    public FullTree<T> buildFullTree(FullTree<T> parent, Integer n){
        if (n<1){
            return null;
        }
        if (parent.getLeft()==null){
            FullTree<T> left = new FullTree<>();
            left.setParent(parent);
            parent.setLeft(left);
            buildFullTree(left,n-1);
        }

        if (parent.getRight()==null){
            FullTree<T> right = new FullTree<>();
            right.setParent(parent);
            parent.setRight(right);
            buildFullTree(right,n-1);
        }
        return null;
    }

    @Override
    public String toString(){
        return String.valueOf(val);
    }

    public static void main(String[] args) {
        FullTree<Integer> root = new FullTree<>();
        root.setVal(1);
        root.buildFullTree(root,3);
        System.out.println(root.getVal());
    }
}
