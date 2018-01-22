package org.firstinspires.ftc.teamcode.lift;

import org.firstinspires.ftc.teamcode.Revbot;

/**
 * Abstract class that all Lifts should extend from.
 */

public abstract class Lift {
    double raiseSpeed, lowerSpeed, stopSpeed;

    // constructor method
    Lift(double down, double stop, double up) {
        this.lowerSpeed = down;
        this.stopSpeed = stop;
        this.raiseSpeed = up;
    }

    public abstract void raise();
    public void raise(long ms) {
        raise();
        Revbot.sleep(ms);
        stop();
    }

    public abstract void lower();
    public void lower(long ms) {
        lower();
        Revbot.sleep(ms);
        stop();
    }

    public abstract void stop();
}
