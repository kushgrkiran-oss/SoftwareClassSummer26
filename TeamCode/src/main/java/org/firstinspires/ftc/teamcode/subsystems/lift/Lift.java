package org.firstinspires.ftc.teamcode.subsystems.lift;
import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
@Config
public class Lift {
    public static double kP=0, kI=0, kD=0;
    public static double kG=0;
    private DcMotorEx liftMotor;
    private final PIDController pid;
    private final Telemetry telemetry;
    private double targetPos;
    private double currentPos;
    public Lift(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.liftMotor = hardwareMap.get(DcMotorEx.class, "Lift Motor");
        pid = new PIDController(kP, kI, kD);
    }
    public void update() {
        int currentPos = liftMotor.getCurrentPosition();
        pid.setPID(kP, kI, kD);
        double frictionFeedForward = .2;
        double pidPower = pid.calculate(targetPos, currentPos);
        double motorPower = kG+frictionFeedForward+pidPower;//originally just calling a function before looking at the answer key
        liftMotor.setPower(motorPower);//syntax didnt work cuz couldnt store a void in double idk why

        telemetry.addLine("LIFT");
        telemetry.addData("Current Pos", currentPos);
        telemetry.addData("Target Pos", targetPos);
        telemetry.addData("Lift Motor Power", motorPower);//added this after looking at answer key
        //telemetry.addData("PID Power", pidPower);
        //telemetry.addData("Friction Feed Forward", frictionFeedForward);
    }

}
