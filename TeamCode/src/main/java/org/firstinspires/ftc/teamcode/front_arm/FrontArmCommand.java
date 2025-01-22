package org.firstinspires.ftc.teamcode.front_arm;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

public class FrontArmCommand extends CommandBase {
    private FrontArmSubsystem subsystem;
    private GamepadEx gamepad;

    public FrontArmCommand(FrontArmSubsystem subsystem, GamepadEx gamepad) {
        this.subsystem = subsystem;
        this.gamepad = gamepad;

        addRequirements(subsystem);
    }

    @Override
    public void execute() {
        subsystem.drive(-gamepad.getLeftY());
    }
}
