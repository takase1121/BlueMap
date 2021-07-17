package de.bluecolored.bluemap.core.util.math;

public class Color {

    public float r, g, b, a;
    public boolean premultiplied;

    public Color set(float r, float g, float b, float a, boolean premultiplied) {
        this.r = r;
        this.g = g;
        this.b = b;
        this.a = a;
        this.premultiplied = premultiplied;
        return this;
    }

    public Color set(Color color) {
        this.r = color.r;
        this.g = color.g;
        this.b = color.b;
        this.a = color.a;
        this.premultiplied = color.premultiplied;
        return this;
    }

    public Color set(int color) {
        this.r = ((color >> 16) & 0xFF) / 255f;
        this.g = ((color >> 8) & 0xFF) / 255f;
        this.b = (color & 0xFF) / 255f;
        this.a = ((color >> 24) & 0xFF) / 255f;
        this.premultiplied = false;

        if (this.a == 0) this.a = 1f; // if the given integer does not define an alpha, we assume 1f

        return this;
    }

    public Color add(Color color) {
        if (color.a < 1f && !color.premultiplied){
            throw new IllegalArgumentException("Can only add premultiplied colors with alpha!");
        }

        premultiplied();

        this.r += color.r;
        this.g += color.g;
        this.b += color.b;
        this.a += color.a;

        return this;
    }

    public Color multiply(Color color) {
        if (color.premultiplied) premultiplied();
        else straight();

        this.r *= color.r;
        this.g *= color.g;
        this.b *= color.b;
        this.a *= color.a;

        return this;
    }

    public Color overlay(Color color) {
        if (color.a < 1f && !color.premultiplied) throw new IllegalArgumentException("Can only overlay premultiplied colors with alpha!");

        premultiplied();

        float p = 1 - color.a;
        this.a = p * this.a + color.a;
        this.r = p * this.r + color.r;
        this.g = p * this.g + color.g;
        this.b = p * this.b + color.b;

        return this;
    }

    public Color flatten() {
        if (this.a == 1f) return this;

        if (premultiplied) {
            this.r /= this.a;
            this.g /= this.a;
            this.b /= this.a;
        }

        this.a = 1f;

        return this;
    }

    public Color premultiplied() {
        if (!premultiplied) {
            this.r *= this.a;
            this.g *= this.a;
            this.b *= this.a;
            this.premultiplied = true;
        }
        return this;
    }

    public Color straight() {
        if (premultiplied) {
            float m = 1f / this.a;
            this.r *= m;
            this.g *= m;
            this.b *= m;
            this.premultiplied = false;
        }
        return this;
    }

    @Override
    public String toString() {
        return "Color{" +
               "r=" + r +
               ", g=" + g +
               ", b=" + b +
               ", a=" + a +
               ", premultiplied=" + premultiplied +
               '}';
    }

}
