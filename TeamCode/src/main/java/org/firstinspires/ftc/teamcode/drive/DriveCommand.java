package org.firstinspires.ftc.teamcode.drive;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

public class DriveCommand extends CommandBase {
    private final DriveSubsystem subsystem;
    private final GamepadEx gamepad;

    public DriveCommand(DriveSubsystem subsystem, GamepadEx gamepad) {
        this.gamepad = gamepad;
        this.subsystem = subsystem;

        this.addRequirements(subsystem);
    }

    @Override
    public void execute() {
        this.subsystem.drive(this.gamepad.getLeftX(), this.gamepad.getLeftY(), this.gamepad.getRightX());
    }
}
