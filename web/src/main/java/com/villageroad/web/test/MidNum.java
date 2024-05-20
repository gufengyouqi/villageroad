package com.villageroad.web.test;

public class MidNum {
    public double findMedianSortedArrays(int[] a, int[] b) {
        int n1=a.length,n2=b.length,n3=n1+n2,aindex=0,bindex=0;
        boolean ds=n3%2==0;
        int end = n3/2+1;
        int ne=0,ne1=0,ne2=0;
        for(int i=0;i<end;i++) {
            ne = bindex==n2 || ( aindex<n1 &&a[aindex]<=b[bindex] )? a[aindex++]:b[bindex++];
            if(i==end-2) ne1=ne;
            if(i==end-1) ne2=ne;
        }
        return ds?(double)(ne1+ne2)/2:ne2;
    }

    public static void main(String[] args) {
        MidNum midNum = new MidNum();
        int[] a = new int[]{1,3};
        int[] b = new int[]{2};
        double res = midNum.findMedianSortedArrays(a,b);
        System.out.println(res);
    }
}
