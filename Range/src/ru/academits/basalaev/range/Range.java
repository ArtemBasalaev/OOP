package ru.academits.basalaev.range;

public class Range {
    private double from;
    private double to;

    public Range(double from, double to) {
        this.from = from;
        this.to = to;
    }

    public double getFrom() {
        return from;
    }

    public void setFrom(double from) {
        this.from = from;
    }

    public double getTo() {
        return to;
    }

    public void setTo(double to) {
        this.to = to;
    }

    public double getLength() {
        return to - from;
    }

    public boolean isInside(double number) {
        return number >= from && to >= number;
    }

    public Range[] getRangesUnion(Range range) {
        if (to < range.from) {
            return new Range[]{this, range};
        }

        if (from > range.to) {
            return new Range[]{range, this};
        }

        double from = Math.min(this.from, range.from);
        double to = Math.max(this.to, range.to);

        return new Range[]{new Range(from, to)};
    }

    public Range getRangesIntersection(Range range) {
        if (to <= range.from || from >= range.to) {
            return null;
        }

        double from = Math.max(this.from, range.from);
        double to = Math.min(this.to, range.to);

        return new Range(from, to);
    }

    public Range[] getRangesAsymmetricalDifference(Range range) {
        if (to <= range.from || from >= range.to) {
            return new Range[]{this};
        }

        if (from >= range.from && to <= range.to) {
            return new Range[0];
        }

        if (from < range.from && this.to > range.to) {
            return new Range[]{new Range(from, range.from), new Range(range.to, to)};
        }

        double from = this.from > range.from ? range.to : this.from;
        double to = this.to > range.to ? this.to : range.from;

        return new Range[]{new Range(from, to)};
    }
}