package org.firstinspires.ftc.teamcode;

import android.media.AudioManager;
import android.media.ToneGenerator;

import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.ColorSensor;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * This is the hardware map for the 2017-2018 robot.
 */
@SuppressWarnings("unused")
public class Revbot {

    // Define hardware
    public DcMotor leftDrive, rightDrive, strafeDrive;
    public DcMotor cubeLift;
    public Servo clawRight, clawLeft, relicClaw;
    public Servo ballKnock, relicSlide;
    public CRServo armWinch;
    public ColorSensor color;
    private HardwareMap hardwareMap;
    private ToneGenerator tone;

    /**
     * This method puts the current thread to sleep for the given time in msec.
     *
     * @param sleepTime specifies sleep time in msec.
     */
    public static void sleep(long sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            System.out.println("got interrupted!");
        }
    }   //sleep

    /**
     * void init() Initializes the hardware, call this method first
     *
     * @param aHwMap HardwareMap to pass in
     */
    public void init(HardwareMap aHwMap) {
        // Save reference to HardwareMap
        this.hardwareMap = aHwMap;

        // Initalize drive motors
        leftDrive = hardwareMap.get(DcMotor.class, "leftDrive");
        rightDrive = hardwareMap.get(DcMotor.class, "rightDrive");
        strafeDrive = hardwareMap.get(DcMotor.class, "strafe");

        // Initialize other motors
        cubeLift = hardwareMap.get(DcMotor.class, "cubeLift");

        // Initialize servos
        clawRight = hardwareMap.get(Servo.class, "clawRight");
        clawLeft = hardwareMap.get(Servo.class, "clawLeft");
        relicClaw = hardwareMap.get(Servo.class, "relicClaw");

        ballKnock = hardwareMap.get(Servo.class, "ballKnock");

        armWinch = hardwareMap.get(CRServo.class, "armWinch");
        relicSlide = hardwareMap.get(Servo.class, "relicSlide");

        // Initialize sensors
        color = hardwareMap.get(ColorSensor.class, "color");

        //Create a tone
        tone = new ToneGenerator(AudioManager.STREAM_NOTIFICATION, 100);

        // Set motor directions
        leftDrive.setDirection(DcMotor.Direction.REVERSE);
        rightDrive.setDirection(DcMotor.Direction.FORWARD);
        strafeDrive.setDirection(DcMotor.Direction.FORWARD);
        cubeLift.setDirection(DcMotor.Direction.REVERSE);

        // Initialize the motors to run without encoders.
        setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        clawLeft.setDirection(Servo.Direction.REVERSE);
    }

    /**
     * void beep() Make the robot play a tone.
     */
    public void beep() {
        tone.startTone(ToneGenerator.TONE_CDMA_KEYPAD_VOLUME_KEY_LITE);
    }

    /**
     * void setMode(DcMotor.RunMode mode) Set all drive motors to the same mode.
     *
     * @param mode desired motor mode.
     */
    private void setMode(DcMotor.RunMode mode) {
        leftDrive.setMode(mode);
        rightDrive.setMode(mode);
        strafeDrive.setMode(mode);
    }
}
