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
        // It is safer to initialize these as null and set them in runOpMode
        private Drive drive;
        private Intake intake;

        @Override
        public void runOpMode() {
            // 1. Reset the scheduler (Crucial for Auto)
            CommandScheduler.getInstance().reset();

            drive = new Drive();
            intake = new Intake();

            drive.init(hardwareMap);
            intake.init(hardwareMap);


//            SequentialCommandGroup autoSequence = new SequentialCommandGroup(
//                    drive.driveCommand(() -> -1.0, () -> 0.0, () -> 0.0)
//                            .withTimeout(1500)
//                            .andThen(intake.intakeRun()
//                                    .alongWith(drive.driveCommand(()-> 0.05, ()-> 0, ()->0)))
//            );

            SequentialCommandGroup autoSequence = new SequentialCommandGroup(
                    drive.driveCommand(() -> -1.0, () -> 0.0, () -> 0.0)
                            .withTimeout(1500)
                            .andThen(drive.driveCommand(() -> 0.0, () -> 1.0, () -> 0.0).withTimeout(1500).andThen(intake.intakeRun()).withTimeout(250), intake.intakeReverse().withTimeout(437), drive.driveCommand(() -> 0.0, () -> 0.0, () -> 1.0).withTimeout(400))
            );


            waitForStart();

            autoSequence.schedule();

            while (opModeIsActive() && !isStopRequested()) {
                CommandScheduler.getInstance().run();

                telemetry.addData("Status", "Running Auto");
                telemetry.update();
            }

            CommandScheduler.getInstance().reset();
        }
    }
