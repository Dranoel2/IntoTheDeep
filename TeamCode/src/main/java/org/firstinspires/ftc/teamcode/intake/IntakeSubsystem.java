package org.firstinspires.ftc.teamcode.intake;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config
public class IntakeSubsystem extends SubsystemBase {
    public static double MIN_ANGLE = 0;
    public static double MAX_ANGLE = 270;

    public static double DOWN_ANGLE = 0;
    public static double UP_ANGLE = 135;

    public static double SPEED = 10.0f;

    private final MotorEx motor;
    private final ServoEx leftServo;
    private final ServoEx rightServo;

    private double angleTarget;
    private double angle;

    private ElapsedTime runtime = new ElapsedTime();
    private double lastTime = 0;

    public IntakeSubsystem(HardwareMap hardwareMap) {
        motor = new MotorEx(hardwareMap, "intakeMotor");
        leftServo = new SimpleServo(hardwareMap, "intakeServoLeft", MIN_ANGLE, MAX_ANGLE);
        rightServo = new SimpleServo(hardwareMap, "intakeServoRight", MIN_ANGLE, MAX_ANGLE);
        rightServo.setInverted(true);
        angle = UP_ANGLE;
        angleTarget = UP_ANGLE;
    }

    @Override
    public void periodic() {
        double now = runtime.milliseconds() / 1000;
        double delta = now - lastTime;
        lastTime = now;

        double speed = delta * SPEED;

        if (Math.abs(angleTarget - angle) > speed) {
            if (angle < angleTarget) {
                angle += speed;
            } else {
                angle -= speed;
            }
        } else {
            angle = angleTarget;
        }

        leftServo.turnToAngle(angle);
        rightServo.turnToAngle(angle);
    }

    public void down() {
        angleTarget = DOWN_ANGLE;
    }

    public void up() {
        angleTarget = UP_ANGLE;
    }

    public void roller(double power) {
        motor.set(power);
    }
}
