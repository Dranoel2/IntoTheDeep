package org.firstinspires.ftc.teamcode.back_arm;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class BackArmSubsystem extends SubsystemBase {
    public static double K_P = 0.4;
    public static double K_I = 0;
    public static double K_D = 0.005;

    public static double SENSITIVITY = 3;

    public static double MIN_POS = 0;
    public static double MAX_POS = 144;

    public static double SPEED = 144;

    private final MotorEx motor;
    private final PIDController controller;
    private final VoltageSensor voltageSensor;
    private final ServoEx bucketServo;

    private double targetPosition;
    private double targetTargetPosition;

    private ElapsedTime runtime = new ElapsedTime();
    private double lastTime = 0;

    public BackArmSubsystem(HardwareMap hardwareMap) {
        motor = new MotorEx(hardwareMap, "backArm");
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor.stopAndResetEncoder();
        controller = new PIDController(K_P, K_I, K_D);
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
        bucketServo = new SimpleServo(hardwareMap, "bucketServo", 0, 270);
        targetPosition = 0;
        targetTargetPosition = 0;
    }

    @Override
    public void periodic() {
        double now = runtime.milliseconds() / 1000;
        double delta = now - lastTime;
        lastTime = now;

        double speed = delta * SPEED;

        if (Math.abs(targetTargetPosition - targetPosition) > speed) {
            if (targetPosition < targetTargetPosition) {
                targetPosition += speed;
            } else {
                targetPosition -= speed;
            }
        } else {
            targetPosition = targetTargetPosition;
        }

        controller.setPID(K_P, K_I, K_D);
        double currentPosition = motor.getCurrentPosition();
        double power = controller.calculate(currentPosition, targetPosition);
        double voltage = voltageSensor.getVoltage();
        motor.set(power / voltage);
    }

    public void open() {
        bucketServo.turnToAngle(90);
    }

    public void close() {
        bucketServo.turnToAngle(0);
    }

    public void home() {
        motor.set(-0.1);
    }

    public void start() {
        motor.stopAndResetEncoder();
    }

    public void up() {
        targetTargetPosition = MAX_POS;
    }

    public void down() {
        targetTargetPosition = MIN_POS;
    }
}