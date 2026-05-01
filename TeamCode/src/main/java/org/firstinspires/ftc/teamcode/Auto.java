package org.firstinspires.ftc.teamcode;

import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.command.RunCommand;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Subsystems.Drive.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.Intake;

@Autonomous(name = "Robot Auton")
public class Auto extends LinearOpMode {
    private Drive drive = new Drive();
    private Intake intake = new Intake();
    private ElapsedTime runtime = new ElapsedTime();
    @Override
    public void runOpMode() {
        CommandScheduler.getInstance().reset();
        drive.init(hardwareMap);
        intake.init(hardwareMap);

        drive.setDefaultCommand(
                drive.driveCommand(
                        () -> 0.0, () -> 0.0, () -> 0.0));
        intake.setDefaultCommand(intake.intakeStop());

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            new RunCommand(() -> drive.driveCommand(()-> 0, ()-> 0, ()-> 1)).andThen(intake.intakeRun());

        }
    }

}
