package com.anton_kulakov;

import com.anton_kulakov.action.SetupDefaultWorldAction;

public class Main {
        public static void main(String[] args) {
                World world = new World();
                SetupDefaultWorldAction.doAction(world);

                WorldConsoleRenderer worldConsoleRenderer = new WorldConsoleRenderer();
                worldConsoleRenderer.render(world);
        }
}
