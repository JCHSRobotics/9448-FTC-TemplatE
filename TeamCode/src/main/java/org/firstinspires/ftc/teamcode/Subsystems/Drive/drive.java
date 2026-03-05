package org.firstinspires.ftc.teamcode.Subsystems.Drive;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.arcrobotics.ftclib.command.CommandOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import com.qualcomm.robotcore.hardware.HardwareMap;

import static org.firstinspires.ftc.teamcode.Subsystems.driveConstants.*;
;

public class Drive extends Subsystem {
    public void init(HardwareMap hardwareMap) {
        // initialize motors here
        private DCMotor frontLeftDrive = hardwareMap.get(DcMotor.class, ID0);
        private DCMotor backLeftDrive = hardwareMap.get(DcMotor.class, ID1);
        private DCMotor frontRightDrive = hardwareMap.get(DcMotor.class, ID2);
        private DCMotor backRightDrive = hardwareMap.get(DcMotor.class, ID3);
    }

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
