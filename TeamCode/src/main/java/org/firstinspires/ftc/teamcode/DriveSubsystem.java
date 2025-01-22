package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem extends SubsystemBase {
    private final MecanumDrive drive;
    private final GamepadEx gamepad;

    public DriveSubsystem(GamepadEx gamepad, HardwareMap hardwareMap) {
        this.gamepad = gamepad;

        Motor frontLeft = new Motor(hardwareMap, "frontLeft");
        Motor backRight = new Motor(hardwareMap, "backRight");
        Motor frontRight = new Motor(hardwareMap, "frontRight");
        Motor backLeft = new Motor(hardwareMap, "backLeft");

        drive = new MecanumDrive(
                frontLeft,
                frontRight,
                backLeft,
                backRight
        );
    }

    @Override
    public void periodic() {
        drive.driveRobotCentric(
                gamepad.getLeftX(),
                -gamepad.getLeftY(),
                gamepad.getRightX(),
                false
        );
    }
}
