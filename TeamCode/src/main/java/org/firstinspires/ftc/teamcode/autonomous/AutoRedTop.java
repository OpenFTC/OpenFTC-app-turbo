package org.firstinspires.ftc.teamcode.autonomous;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

 /**
 * Auto Red Top.
 */

@SuppressWarnings("unused")
@Autonomous(name = "Auto Red Top", group = "auto")
public class AutoRedTop extends AutoBase {

    @Override
    public void runOpMode() throws InterruptedException {
        super.runOpMode();
        auto.strafeLeft(1000, 0.5);
        auto.fondleBalls("red");

        auto.strafeRight(0.5);
        auto.backward(0.5);
        sleep(900);
        auto.strafeRight(0);
        auto.backward(0);
        robot.beep();
        sleep(500);

        boxLocation = auto.readImage();

        auto.backward(500, 0.5);
        sleep(500);

        switch (boxLocation) {
            case "RIGHT":
                auto.backward(500, 0.5);
                break;

            case "CENTER":
                auto.backward(1000, 0.5);
                break;

            case "LEFT":
                auto.backward(1500, 0.5);
                break;

            default:
                auto.backward(750, 0.5);
                break;
        }

        robot.cubeLift.setPower(-0.5);
        sleep(500);
        robot.cubeLift.setPower(0);
        auto.turnLeft(500, 0.75);
        sleep(500);
        auto.forward(1000, 1);

        for (int i = 0; i < 3; i++) {
            robot.beep();
        }
    }

}