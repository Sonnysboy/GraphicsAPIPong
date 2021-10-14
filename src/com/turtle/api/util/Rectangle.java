package com.turtle.api.util;

import com.turtle.api.vec2d;

/**
 * Simple class, can be used to store a bounding box for collission purposes
 */
public class Rectangle {
  public final long x1, y1, x2, y2;

  public Rectangle(long x1, long y1, long x2, long y2) {
    this.x1 = x1;
    this.y1 = y1;
    this.x2 = x2;
    this.y2 = y2;

  }

  public Rectangle(double x, double y, double v, double v1) {
    this((long)x, (long)y, (long)v, (long)v1);
  }

  public boolean contains(vec2d vec) {
    double x = vec.getX();
    double y = vec.getY();
    return x >= x1 && x <= x2 && y >= y1 && y <= y2;
  }



}