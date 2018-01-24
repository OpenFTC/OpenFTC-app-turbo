package org.firstinspires.ftc.teamcode.auto;


import org.firstinspires.ftc.teamcode.enums.Alliance;
import org.firstinspires.ftc.teamcode.enums.Direction;
import org.firstinspires.ftc.teamcode.enums.Location;
import org.firstinspires.ftc.teamcode.vuforia.Vuforia;

/**
 * Abstract Autonomous class from which all Auto classes extend from.
 */

public abstract class AutoTop extends Auto {
    AutoTop(Alliance alliance) {
        super(alliance, Location.UPPER);
    }

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();

        boxLocation = Vuforia.readImage();
        telemetry.addData("Vuforia Target Seen:", boxLocation);
        telemetry.update();

        cubeClaw.close();

        telemetry.addData("Cube Claw", cubeClaw.getPosition());
        telemetry.update();

        servoSwivel.swivelCenter();

        armWinch.lower(3500);
        cubeLift.raise(125);

        drivetrain.strafe(Direction.LEFT, 0.5, 750);
        safeSleep(500);

        knockBalls(alliance);
        safeSleep(500);

        servoSwivel.swivelCenter();
        drivetrain.strafe(Direction.RIGHT, 0.5, 125);
        armWinch.raise(4000);
        safeSleep(500);

        drivetrain.drive(alliance.equals(Alliance.RED) ? Direction.BACKWARD : Direction.FORWARD, 0.5, 700);
        safeSleep(500);

        drivetrain.strafe(Direction.RIGHT, 0.5, 1000);
        safeSleep(500);

        cubeIntoCrypotbox();
        safeSleep(500);

        drivetrain.turn(Direction.LEFT, 0.5, 1000);
        safeSleep(500);

        drivetrain.drive(Direction.FORWARD, 0.5, 1000);
        safeSleep(500);

        cubeLift.lower(125);

        cubeClaw.open();


        drivetrain.drive(Direction.BACKWARD, 0.5, 125);

        for (int i = 0; i < 3; i++) {
            robot.beep();
        }
    }

    void cubeIntoCrypotbox() {
        Direction direction = alliance.equals(Alliance.RED) ? Direction.BACKWARD : Direction.FORWARD;
        if (alliance.equals(Alliance.RED)) {
            switch (boxLocation) {
                case RIGHT:
                    drivetrain.drive(direction, 0.5, 300);
                    break;

                case CENTER:
                    drivetrain.drive(direction, 0.5, 550);
                    break;

                case LEFT:
                    drivetrain.drive(direction, 0.5, 800);
                    break;
            }
        } else if (alliance.equals(Alliance.BLUE)) {
            switch (boxLocation) {
                case LEFT:
                    drivetrain.drive(direction, 0.5, 300);
                    break;

                case CENTER:
                    drivetrain.drive(direction, 0.5, 550);
                    break;

                case RIGHT:
                    drivetrain.drive(direction, 0.5, 800);
                    break;
            }
        }
    }
}
