package org.firstinspires.ftc.teamcode.intake;

import com.arcrobotics.ftclib.command.CommandBase;

public class IntakeRollerCommand extends CommandBase {
    private final IntakeSubsystem subsystem;

    public IntakeRollerCommand(IntakeSubsystem subsystem) {
        this.subsystem = subsystem;
    }

    @Override
    public void initialize() {
        subsystem.go();
    }

    @Override
    public void end(boolean interrupted) {
        subsystem.stop();
    }
}
