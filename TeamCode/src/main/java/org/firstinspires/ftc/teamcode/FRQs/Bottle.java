package org.firstinspires.ftc.teamcode.FRQs;

public class Bottle {
    private double amt;
    private double maxCapacity;
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

