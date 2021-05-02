package com.example.safiofyp.utils;

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
