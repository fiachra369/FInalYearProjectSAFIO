package com.example.SAFIOFYP.utils;

import java.util.ArrayList;
import java.util.List;

public class SingletonClass {

    public static SingletonClass instance = null;

    synchronized public static SingletonClass getInstance()
    {
        if (instance == null)
        {
            instance = new SingletonClass();
            return instance;
        }
        else
            return instance;
    }

}
