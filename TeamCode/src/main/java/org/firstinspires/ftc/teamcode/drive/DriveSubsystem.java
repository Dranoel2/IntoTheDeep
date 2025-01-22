package org.firstinspires.ftc.teamcode.drive;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class DriveSubsystem extends SubsystemBase {
    private final MecanumDrive drive;

    public DriveSubsystem(HardwareMap hardwareMap) {
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

    public void drive(double x, double y, double t) {
        drive.driveRobotCentric(x, y, t, false);
    }
}
