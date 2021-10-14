package com.turtle.api.example.pong;


import com.turtle.api.BrilliantTurtle;
import com.turtle.api.*;
import com.turtle.api.managers.*;
import com.turtle.api.TurtleTesselator;
import com.turtle.api.vec2d;

/**
 * 
 * WOW!
 *
 */

public abstract class Renderable {

	protected vec2d pos;

	public Renderable(long x, long y) {
		this.pos = new vec2d(x, y);
	}

	abstract void draw(long x, long y);

	public synchronized void draw() {
		this.draw((long) pos.getX(), (long) pos.getY());
	}

}



