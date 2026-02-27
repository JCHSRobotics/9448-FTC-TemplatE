import com.arcrobotics.ftclib.command.CommandOpMode;
public class drive exetends SubsystemsBase {

    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    private void periodic() {
        UpdateInputs(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }
    
    
    public void Drive (double forward, double turn, double straif){
        frontLeftPower = forward + turn - straif;
        frontRightPower = forward - turn + straif;
        backLeftPower = forward + turn - straif;
        backRightPower = forward - turn + straif;
    }

    public void UpdateInputs(double frontLeftPower, double frontRightPower, double backLeftPower, double backRightPower) {
        frontLeftDrive.setPower(frontLeftPower); 
        frontRightDrive.setPower(frontRightPower);
        backLeftDrive.setPower(backLeftPower);
        backRightDrive.setPower(backRightPower);
    }

    public Command DriveCommand(double forward, double turn, double straif) {
        return run(Drive(forward, turn, straif));
    }

}
