package org.firstinspires.ftc.teamcode.front_arm;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Config
public class FrontArmSubsystem extends SubsystemBase {
    public static double K_P = 0.5;
    public static double K_I = 0;
    public static double K_D = 0.004;

    public static double SENSITIVITY = 3;

    public static double MIN_POS = 0;
    public static double MAX_POS = 1600;

    private final MotorEx motor;
    private final PIDController controller;
    private final VoltageSensor voltageSensor;

    private double targetPosition;

    public FrontArmSubsystem(HardwareMap hardwareMap) {
        motor = new MotorEx(hardwareMap, "frontArm");
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        motor.stopAndResetEncoder();
        controller = new PIDController(K_P, K_I, K_D);
        voltageSensor = hardwareMap.get(VoltageSensor.class, "Control Hub");
        targetPosition = 0;
    }

    @Override
    public void periodic() {
        controller.setPID(K_P, K_I, K_D);
        double currentPosition = motor.getCurrentPosition();
        double power = controller.calculate(currentPosition, targetPosition);
        double voltage = voltageSensor.getVoltage();
        motor.set(power / voltage);
    }

    public void drive(double value) {
        targetPosition = MathUtils.clamp(targetPosition + value * SENSITIVITY, MIN_POS, MAX_POS);
    }
}
