package org.firstinspires.ftc.teamcode.front_arm;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.util.MathUtils;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.TouchSensor;
import com.qualcomm.robotcore.hardware.VoltageSensor;

@Config
public class FrontArmSubsystem extends SubsystemBase {
    public static double K_P = 0.05;
    public static double K_I = 0;
    public static double K_D = 0;

    public static double SENSITIVITY = 1;

    public static double MIN_POS = 0;
    public static double MAX_POS = 1000;

    private final MotorEx motor;
    private final PIDController controller;
    private final TouchSensor limit;
    private final VoltageSensor voltageSensor;

    private double targetPosition;

    public FrontArmSubsystem(HardwareMap hardwareMap) {
        motor = new MotorEx(hardwareMap, "frontArm");
        motor.setZeroPowerBehavior(Motor.ZeroPowerBehavior.BRAKE);
        controller = new PIDController(K_P, K_I, K_D);
        limit = hardwareMap.get(TouchSensor.class, "frontArmLimit");
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

        TelemetryPacket packet = new TelemetryPacket();
        packet.put("targetPosition", targetPosition);
        packet.put("currentPosition", currentPosition);

        FtcDashboard dashboard = FtcDashboard.getInstance();
        dashboard.sendTelemetryPacket(packet);
    }

    public void drive(double value) {
        targetPosition = MathUtils.clamp(targetPosition + value * SENSITIVITY, MIN_POS, MAX_POS);
    }

    public void home() {
        while (!limit.isPressed()) {
            motor.set(-0.5);
        }
        while (limit.isPressed()) {
            motor.set(0.1);
        }
        while (!limit.isPressed()) {
            motor.set(-0.1);
        }
        motor.set(0);
        motor.stopAndResetEncoder();
    }
}
