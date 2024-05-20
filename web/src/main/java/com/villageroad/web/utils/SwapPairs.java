package com.villageroad.web.utils;

import lombok.Data;

@Data
public class SwapPairs {
    Integer val;
    SwapPairs next;

    public SwapPairs(Integer val, SwapPairs next) {
        this.val = val;
        this.next = next;
    }

    public static void main(String[] args) {
        // 构造测试shuju
        SwapPairs root = new SwapPairs(1, new SwapPairs(2, new SwapPairs(3, new SwapPairs(4, null))));

        SwapPairs root1  = execute(root);
        //验证
        while (root1 != null) {
            System.out.println(root1.getVal());
            root1 = root1.getNext();
        }
    }
    
    public static SwapPairs execute(SwapPairs root){
        if(root==null|| root.getNext()==null){
            return root;
        }
        SwapPairs next = root.getNext();
        root.next = execute(root.next.next);
        next.next = root;
        return next;
    }

}
