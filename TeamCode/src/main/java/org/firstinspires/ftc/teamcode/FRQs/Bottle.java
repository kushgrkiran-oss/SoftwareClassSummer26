package org.firstinspires.ftc.teamcode.FRQs;

public class Bottle {
    private double amt;
    private final double maxCapacity;//added final after checking answer key
    public Bottle(double amt) {
        this.amt = amt;
        this.maxCapacity = amt;
    }
    public double updateAmount(double liquid) {
        amt = amt-liquid;
        if (amt<0.25*maxCapacity) {
            amt = maxCapacity;
        }
        return amt;
    }
}

