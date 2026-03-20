package org.firstinspires.ftc.teamcode.Subsystems.Drive;



import static org.firstinspires.ftc.teamcode.Subsystems.Drive.DriveConstants.*;

import com.arcrobotics.ftclib.command.RunCommand;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.Command;

import java.util.function.DoubleSupplier;

public class Drive extends SubsystemBase {

    private DcMotor frontLeftDrive;
    private DcMotor backLeftDrive;
    private DcMotor frontRightDrive;
    private DcMotor backRightDrive;
    public void init(HardwareMap hardwareMap) {
        // initialize motors here
        frontLeftDrive = hardwareMap.get(DcMotor.class, ID0);
        backLeftDrive = hardwareMap.get(DcMotor.class, ID1);
        frontRightDrive = hardwareMap.get(DcMotor.class, ID2);
        backRightDrive = hardwareMap.get(DcMotor.class, ID3);
    }

    private double frontLeftPower;
    private double frontRightPower;
    private double backLeftPower;
    private double backRightPower;

    @Override
    public void periodic() {
        UpdateInputs(frontLeftPower, frontRightPower, backLeftPower, backRightPower);
    }
    
    
    public void drive (double forward, double turn, double straif){
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

    public Command driveCommand(DoubleSupplier forward, DoubleSupplier turn, DoubleSupplier straif) {
        return new RunCommand(() -> drive(forward.getAsDouble(), turn.getAsDouble(), straif.getAsDouble()), this);
    }

}
