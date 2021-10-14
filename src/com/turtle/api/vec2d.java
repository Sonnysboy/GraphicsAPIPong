package com.turtle.api;

import com.turtle.api.util.Rectangle;

public class vec2d {

  private volatile double x, y;

  public vec2d(double x, double y) {
    this.x = x;
    this.y = y;
  }

  public synchronized double getX() {
    return this.x;
  }

  public synchronized double getY() {
    return this.y;
  }

  public synchronized vec2d add(double x, double y) {

    return new vec2d(this.x + x, this.y + y);
  }

  public synchronized vec2d add(vec2d other) {

    return new vec2d(this.x + other.x, this.y + other.y);
  }

  public synchronized vec2d times(double scalar) {

    return new vec2d(this.x * scalar, this.y * scalar);
  }

  public synchronized vec2d times(vec2d scalar) {
    return new vec2d(this.x * scalar.x, this.y * scalar.y);
  }

  public synchronized vec2d sub(vec2d scalar) {
    return new vec2d(this.x - scalar.x, this.y - scalar.y);
  }

  public synchronized vec2d sub(double x, double y) {
    return new vec2d(this.x - x, this.y - y);
  }

  public synchronized void setX(int x) {
    this.x = x;
  }

  public synchronized void setY(int y) {
    this.y = y;
  }

  public double magnitude() {
    return (double) Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
  }

  public boolean inBounds(Rectangle rec) {
    return rec.contains(this);
  }

  public boolean inBounds(long x1, long y1, long x2, long y2) {
    return new Rectangle(x1, y1, x2, y2).contains(this);
  }

}