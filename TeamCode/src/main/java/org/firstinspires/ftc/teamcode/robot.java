import org.firstinspires.ftc.teamcode.Subsystems.Drive.DriveCommand;


public class robot extends LinearOpMode {

    private ElapsedTime runtime = new ELapsedTime();

    @Override
    public void runOpMode() {
        driveCommand(gamepad1.left_stick_x, gamepad1.left_stick_y, gamepad1.right_stick_x);

        telemetry.update();
    }
}
