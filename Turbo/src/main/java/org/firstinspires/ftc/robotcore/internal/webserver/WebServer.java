package org.firstinspires.ftc.robotcore.internal.webserver;

import org.openftc.turbo.TurboException;

/**
 * This is a dummy class, used in the OpenFTC Turbo build variant
 */

public class WebServer {
    public RobotControllerWebInfo getConnectionInformation() {
        throw new TurboException();
    }
}
