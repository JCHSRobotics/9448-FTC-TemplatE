package org.firstinspires.ftc.teamcode.Robot;


import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Subsystems.Drive;


@TeleOp(name = "Robot TeleOp")
public class Robot extends LinearOpMode {

    private Drive drive = new Drive();

    private ElapsedTime runtime = new ElapsedTime();

    @Override
    public void runOpMode() {
        drive.init(hardwareMap);

        waitForStart();
        runtime.reset();


        while (opModeIsActive()) {
            // robot code
            drive.driveCommand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

            telemetry.update();
        }
    }
}
