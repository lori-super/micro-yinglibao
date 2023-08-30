package com.micro.utils;

import com.micro.entity.UUser;

public class ThreadUserUtils {
    private static final ThreadLocal<UUser> t1  = new ThreadLocal<>();
    public static void saveUser(UUser uUser){t1.set(uUser);}
    public static UUser getUser(){
        return t1.get();
    }

    public static void removeUser(){
        t1.remove();
    }
}
