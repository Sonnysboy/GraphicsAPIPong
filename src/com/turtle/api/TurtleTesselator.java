package com.turtle.api;

import java.awt.Color;
import java.util.ArrayDeque;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import com.turtle.api.util.VertexUtils;

public class TurtleTesselator {

    private static TurtleTesselator i;

    private BrilliantTurtle turtle;

    private  ArrayDeque<TurtleVertex> vertices = new ArrayDeque<>();


    private int translateX, translateY;
    private boolean started = false;

    private int mode;

    public void begin(int mode) {
        if (started) {
            throw new RuntimeException("Turtle Tesselator has already begun!");
        }
        this.mode = mode;
        this.started = true;
        this.vertices
                 = new ArrayDeque<>();
    }

    public void draw() {
        if (!started) {
            throw new RuntimeException("Turtle Tesselator has not started!");
        }

        started = false;

        if ((mode & BrilliantTurtle.FILL) != 0) {




            vec2d min = VertexUtils.getMinVertex(
                    this.vertices.stream().map(e -> new vec2d(e.getX(), e.getY())).collect(Collectors.toList()));
            vec2d max = VertexUtils.getMaxVertex(
                    this.vertices.stream().map(e -> new vec2d(e.getX(), e.getY())).collect(Collectors.toList()));

            turtle.getCheatyGraphics().fillRect((int) min.getX(), (int) min.getY(),
                    (int) max.getX() - (int) min.getX() + 1, (int) (max.getY() - min.getY()) + 1);

        }
        switch (mode & ~BrilliantTurtle.FILL) {
            case BrilliantTurtle.STRUCT_2D: {
//        connects all the vertices, and connects the last one to the first one.
                drawStruct2d();
                break;
            }
            case BrilliantTurtle.STRUCT_PTS: {
                drawPoints();
                break;
            }
            default:
                break;
        }

    }

    public TurtleVertex pos(double x, double y) {
        if (!started) {
            throw new RuntimeException("Turtle Tesselator has not started!");
        }
        x += this.translateX;
        y += this.translateY;

        TurtleVertex vertex = new TurtleVertex(new vec2d(x, y));
        vertices.push(vertex);
        return vertex;
    }

    public TurtleVertex pos(int x, int y) {
        x += this.translateX;
        y += this.translateY;
        if (!started) {
            throw new RuntimeException("Turtle Tesselator has not started!");
        }

        TurtleVertex vertex = new TurtleVertex(new vec2d(x, y));
        vertices.push(vertex);
        return vertex;
    }

    public static TurtleTesselator getInstance() {
        if (i == null)
            i = new TurtleTesselator();
        if (i.turtle == null) {
            i.turtle = BrilliantTurtle.getInstance();
        }
        if (i.turtle == null) {
            throw new NullPointerException("There is no turtle!");
        }
        return i;
    }

    public void resetTranslations() {
        this.translateX = 0;
        this.translateY = 0;
    }
    public void updateTranslation(int x, int y)
    {
        this.translateY = y;
        this.translateX = x;
    }

    public static class TurtleVertex {
        private volatile int color;

        private final vec2d pos;

        public TurtleVertex(vec2d vec, Color color) {
            this.color = color.getRGB();
            this.pos = vec;
        }

        public TurtleVertex(vec2d vec, int color) {
            this.color = color;
            this.pos = vec;
        }

        public TurtleVertex(vec2d vec) {
            this.color = 0;
            this.pos = vec;
        }

        public void color(int color) {
            this.color = color;
        }

        public void color(Color color) {
            this.color = color.getRGB();
        }

        public int getX() {
            return (int) pos.getX();
        }

        private int getY() {
            return (int) pos.getY();
        }

        public void color(int i, int i1, int i2) {
            this.color = ColorUtils.componentsToInt(i, i1, i2);
        }
    }

    private void drawPoints() {

        TurtleVertex vertex;
        while ((vertex = vertices.poll()) != null) {
            turtle.drawPoint(vertex.getX(), vertex.getY(), new Color(vertex.color));
        }
    }

    private void drawStruct2d() {

        TurtleVertex vertex;
        TurtleVertex last = vertices.getLast();

        while ((vertex = vertices.poll()) != null) {
            turtle.drawLine(vertex.getX(), vertex.getY(), last.getX(), last.getY(), new Color(vertex.color));
            last = vertex;
        }

    }

}