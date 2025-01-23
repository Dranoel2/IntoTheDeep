package org.firstinspires.ftc.teamcode.intake;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;

public class IntakeRollerCommand extends CommandBase {
    private final IntakeSubsystem subsystem;
    private final GamepadEx gamepad;

    public IntakeRollerCommand(IntakeSubsystem subsystem, GamepadEx gamepad) {
        this.subsystem = subsystem;
        this.gamepad = gamepad;
    }

    @Override
    public void execute() {
        double power = gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER)
                - gamepad.getTrigger(GamepadKeys.Trigger.RIGHT_TRIGGER);
        subsystem.roller(power);
    }
}
