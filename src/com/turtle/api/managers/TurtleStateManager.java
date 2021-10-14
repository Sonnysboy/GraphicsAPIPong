package com.turtle.api.managers;

import com.turtle.api.BrilliantTurtle;
import com.turtle.api.ColorUtils;
import com.turtle.api.TurtleTesselator;

public class TurtleStateManager {

    public static double scale = 1.0;


    protected static boolean statePushed;


    public static int[] translation = new int[2];



    public static void scale(double newScale) {
        if (!statePushed) {
            throw new RuntimeException("State not active..");
        }
        scale = newScale;
    }
    public static void pushState() {
        statePushed = true;
    }

    public static void popState() {
        statePushed = false;
        BrilliantTurtle.getInstance().updateState();
        TurtleTesselator.getInstance().resetTranslations();
    }
    public static void translate(int x, int y) {
        translation[0] = x;
        translation[1] = y;

        TurtleTesselator.getInstance().updateTranslation(x, y);
    }


}
