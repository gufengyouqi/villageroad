package com.villageroad.web.test;

public class ShowStyleZ {
    public String convert(String s, int numRows) {
        if(numRows==1){
            return s;
        }
        StringBuilder sb = new StringBuilder();
        int a = 2 * (numRows - 1);
        for (int i = 0; i < numRows; i++) {
            int c = i;
            if(i==0||i==numRows-1){
                while (c<s.length()){
                    sb.append(s.charAt(c));
                    c = c + a;
                }
                sb.append("\n");
                System.out.println("\n");
            }else {
                int b = a-2*i;
                while (c < s.length()) {
                    sb.append(s.charAt(c));
                    c = c + b;
                    b = a - b;
                }
                sb.append("\n");
                System.out.println("\n");
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
       String  s = "ABCDEFGHIJKLMN";
       int numRows = 6;
        ShowStyleZ showStyleZ = new ShowStyleZ();
        String convert = showStyleZ.convert(s, numRows);
        System.out.println(convert);
    }
}
