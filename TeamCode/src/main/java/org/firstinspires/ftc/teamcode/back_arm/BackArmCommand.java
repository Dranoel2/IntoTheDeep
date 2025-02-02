package org.firstinspires.ftc.teamcode.back_arm;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

public class BackArmCommand extends CommandBase {
    private final BackArmSubsystem subsystem;
    private final GamepadEx gamepad;

    public BackArmCommand(BackArmSubsystem subsystem, GamepadEx gamepad) {
        this.subsystem = subsystem;
        this.gamepad = gamepad;

        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.drive(-gamepad.getRightY());
    }
}
