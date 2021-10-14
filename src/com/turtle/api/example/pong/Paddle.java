package com.turtle.api.example.pong;

import com.turtle.api.BrilliantTurtle;
import com.turtle.api.TurtleTesselator;
import com.turtle.api.managers.TurtleStateManager;
import com.turtle.api.util.Rectangle;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.concurrent.atomic.AtomicInteger;

public class Paddle extends Renderable {

    /**
     * This thing better not change, or else we will have
     * BIIIIG PROBLEMS
     */
    private final Ball ball;


    private AtomicInteger yTranslate = new AtomicInteger(0);

    public Paddle(long x, long y, Ball ball) {
        super(x, y);
        this.ball = ball;
        BrilliantTurtle.getInstance().addKeyHandler((keyEvent -> {
            if (keyEvent.getKeyCode() == KeyEvent.VK_DOWN) {
                this.yTranslate.getAndAdd(15);
            }
            if (keyEvent.getKeyCode() == KeyEvent.VK_UP) {
                this.yTranslate.getAndAdd(-15);

            }
            return true;
        }));
    }


    private final int width = 4, height = 600;

    @Override
    public void draw(long x, long y) {

        TurtleTesselator tess = TurtleTesselator.getInstance();
        tess.begin(BrilliantTurtle.STRUCT_2D | BrilliantTurtle.FILL);
        {
            TurtleStateManager.pushState();
            {

                TurtleStateManager.translate(0, this.yTranslate.get());
                BrilliantTurtle.getInstance().getCheatyGraphics().setColor(Color.WHITE);
                tess.pos(x, y).color(255, 255, 255);
                tess.pos(x, y + height / 2).color(255, 255, 255);
                tess.pos(x + (width / 2), y).color(255, 255, 255);
                tess.pos(x + width / 2, y + height / 2).color(255, 255, 255);
            }
            TurtleStateManager.popState();
        }
        tess.draw();


    }

    /**
     * Used for detecting whether or not something is intersecting this paddle.
     * @return (width, height) + (0, this.y)
     */
    public Rectangle getHitboxCoordinates() {
        return new Rectangle(pos.getX() , this.pos.getY(), pos.getX() + width, pos.getY() + height);
    }
}