package org.firstinspires.ftc.teamcode;


import com.arcrobotics.ftclib.command.CommandScheduler;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Drive.Drive;
import org.firstinspires.ftc.teamcode.Subsystems.Intake.Intake;


@TeleOp(name = "Robot TeleOp")
public class Robot extends LinearOpMode {

    private Drive drive = new Drive();
    private Intake intake = new Intake();

    private GamepadEx gamepad;

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        drive.init(hardwareMap);
        intake.init(hardwareMap);

        gamepad = new GamepadEx(gamepad1);

        drive.setDefaultCommand(
                drive.driveCommand(
                        () -> gamepad1.left_stick_y,
                        () -> gamepad1.left_stick_x,
                        () -> gamepad1.right_stick_x
                )
        );
        intake.setDefaultCommand(intake.intakeStop());


        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            // robot code

            CommandScheduler.getInstance().run();

            telemetry.update();



            gamepad.getGamepadButton(GamepadKeys.Button.RIGHT_BUMPER).whileActiveContinuous(intake.intakeRun());
            gamepad.getGamepadButton(GamepadKeys.Button.LEFT_BUMPER).whileActiveContinuous(intake.intakeReverse());

        }
    }
}
