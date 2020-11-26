package com.github.zrff.backend.util.Map2;

public class Tester {

    static Map2<String,Object> res = new HashMap2();
    static {
        res.put("name","ml");
        res.put("age",23);
    }

    public static void main(String args[]){
        String a = res.get("name","");
        System.out.println(a);
    }
}
