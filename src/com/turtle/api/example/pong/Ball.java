package com.turtle.api.example.pong;

import com.turtle.api.BrilliantTurtle;
import com.turtle.api.TurtleTesselator;
import com.turtle.api.util.Rectangle;
import com.turtle.api.vec2d;
import com.turtle.api.managers.TurtleStateManager;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 
 * WOW!
 *
 */

public class Ball extends Renderable {

	/**
	 * The ball's current x, y motion The `volatile` keyword makes sure that java
	 * updates the value in a different memory register, otherwise the thread could
	 * potentially break and the ball could just randomly teleport.
	 */
	private volatile long dx = 0, dy = 0;

	private int width = 5, height = 5;

	public Ball(long x, long y) {
		super(x, y);
	}

	public synchronized void updatePosition() {
		this.pos = pos.add(new vec2d(dx, dy));
	}

	/**
	 * This ball's drawing method.
	 */
	@Override
	public synchronized void draw(long x, long y) {
		TurtleTesselator tess = TurtleTesselator.getInstance();
		tess.begin(BrilliantTurtle.STRUCT_PTS);
		TurtleStateManager.pushState();
		TurtleStateManager.scale(4);
		tess.pos(x, y).color(0xffffff);
		TurtleStateManager.popState();
		tess.draw();
	}
//	start moving.
	public void initMovement() {
		this.dx = -1 * 25;
	}

	public void doCollisionLogic(Paddle humanControlledPaddle, Rectangle gameBounds) {
		if (humanControlledPaddle.getHitboxCoordinates().contains(this.pos)) {
			this.dx = 20;
			this.dy = ThreadLocalRandom.current().nextInt(-40, 40);
		}
//		if (gameBounds.y2 <= this.pos.getY()) {
//			this.dy = ThreadLocalRandom.current().nextInt(20, 40);
//		}
	}
}
