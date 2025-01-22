package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandOpMode;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.drive.DriveCommand;
import org.firstinspires.ftc.teamcode.drive.DriveSubsystem;

@TeleOp(name = "Driver Controlled", group = "Into the Deep")
public class DriverControlled extends CommandOpMode {
    @Override
    public void initialize() {
        GamepadEx driveGamepad = new GamepadEx(gamepad1);

        DriveSubsystem driveSubsystem = new DriveSubsystem(hardwareMap);
        DriveCommand driveCommand = new DriveCommand(driveSubsystem, driveGamepad);

        driveSubsystem.setDefaultCommand(driveCommand);

        register(driveSubsystem);
    }
}
