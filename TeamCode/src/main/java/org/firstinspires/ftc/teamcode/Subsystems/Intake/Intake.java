package org.firstinspires.ftc.teamcode.Subsystems.Intake;

import static org.firstinspires.ftc.teamcode.Subsystems.Drive.DriveConstants.ID0;
import static org.firstinspires.ftc.teamcode.Subsystems.Drive.DriveConstants.ID1;
import static org.firstinspires.ftc.teamcode.Subsystems.Intake.IntakeConstants.*;

import com.arcrobotics.ftclib.command.RunCommand;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.command.Command;

import java.util.function.DoubleSupplier;
public class Intake extends SubsystemBase {
    private DcMotor rightIntake;
    private DcMotor leftIntake;

    public void init(HardwareMap hardwareMap) {
        // initialize motors here

        rightIntake = hardwareMap.get(DcMotor.class, ID4);
        leftIntake = hardwareMap.get(DcMotor.class, ID5);
        rightIntake.setDirection(DcMotor.Direction.FORWARD);
        leftIntake.setDirection(DcMotor.Direction.REVERSE);

    }

    private double leftIntakePower;
    private double rightIntakePower;

    @Override
    public void periodic() {
        UpdateInputs(leftIntakePower, rightIntakePower);
    }

    public void UpdateInputs(double leftIntakePower, double rightIntakePower) {
        rightIntake.setPower(rightIntakePower);
        leftIntake.setPower(leftIntakePower);
    }

    private void intake(double volts){
        rightIntakePower = volts;
        leftIntakePower = volts;
    }


    public Command intakeStop() {
        return new RunCommand(()-> intake(0), this);
    }

    public Command intakeRun() {
        return new RunCommand(()-> intake(1), this);
    }

    public Command intakeReverse() {
        return new RunCommand(()-> intake(-1), this);
    }


}
