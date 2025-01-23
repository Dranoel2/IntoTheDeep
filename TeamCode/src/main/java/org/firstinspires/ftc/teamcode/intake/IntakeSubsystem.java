package org.firstinspires.ftc.teamcode.intake;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

@Config
public class IntakeSubsystem extends SubsystemBase {
    public static final double DOWN_ANGLE = 0;
    public static final double UP_ANGLE = 235;

    private final MotorEx motor;
    private final ServoEx servo;

    public IntakeSubsystem(HardwareMap hardwareMap) {
        motor = new MotorEx(hardwareMap, "intakeMotor");
        servo = new SimpleServo(hardwareMap, "intakeServo", DOWN_ANGLE, UP_ANGLE);
    }

    public void down() {
        servo.setPosition(DOWN_ANGLE);
    }

    public void up() {
        servo.setPosition(UP_ANGLE);
    }

    public void roller(double power) {
        motor.set(power);
    }
}
