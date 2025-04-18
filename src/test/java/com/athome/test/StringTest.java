package com.athome.test;

import org.junit.jupiter.api.Test;

import java.util.UUID;

public class StringTest {
    @Test
    public void test(){
        String s = "D:/Users/Maroon/OneDrive/图片/美图/Snipaste_2024-09-03_14-20-42.png";
//        s = UUID.randomUUID()+s.substring(s.lastIndexOf('.'));
//        System.out.println(s);
        System.out.println(UUID.randomUUID()+s.substring(s.lastIndexOf('.')));
    }
}
