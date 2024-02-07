package com.anton_kulakov.action;

import com.anton_kulakov.World;
import java.util.Random;

public abstract class Action {
    public static Random random = new Random();
    public abstract void perform(World world);
}
