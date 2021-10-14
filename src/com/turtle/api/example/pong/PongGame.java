package com.turtle.api.example.pong;

import com.turtle.api.BrilliantTurtle;
import com.turtle.api.TurtleTesselator;

import java.awt.*;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * 
 * WOW!
 *
 */
public class PongGame {



	public static void main(String[] args) {
		final AtomicBoolean gameStarted = new AtomicBoolean(false);
		BrilliantTurtle turtle = new BrilliantTurtle();



		final Ball ball = new Ball(750, 570);

		final Paddle humanControlledPaddle = new Paddle(100, 230, ball);

		turtle.addKeyHandler((event) -> {
			if (!gameStarted.get()) {
				gameStarted.set(true);
				ball.initMovement();
			}
			return gameStarted.get();
		});

		turtle.setLoop(new Thread(() -> {


			turtle.reset(Color.BLACK);

			ball.draw();
			humanControlledPaddle.draw();
			ball.updatePosition();
			ball.doCollisionLogic(humanControlledPaddle, turtle.getBounds());
		}));
	}

}
