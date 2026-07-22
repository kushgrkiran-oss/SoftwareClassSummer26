package org.firstinspires.ftc.teamcode.subsystems.arm;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.lift.Lift;

public class Arm {
    public static double kP=0, kI=0, kD=0;
    public static double kG=0, kS=0;
    public static double radiansPerEncoder = 0;//didnt do the radians per encoder ticks before looking at the answer key
    private DcMotorEx armMotor;
    private final Telemetry telemetry;
    private double targetAngle;
    private final PIDController pid;
    public Arm(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        pid = new PIDController(kP, kI, kD);
        this.armMotor = hardwareMap.get(DcMotorEx.class, "Arm Motor");//forgor about the .class in intializing the motor
    }

    public void update() {
        double encoder = armMotor.getCurrentPosition();
        double currentAngle = encoder*radiansPerEncoder;
        pid.setPID(kP, kI, kD);
        double pidPower = pid.calculate(currentAngle, targetAngle);
        double frictionFeedForward = (targetAngle-currentAngle)*kS;//forgot how to do target dir
        double gravityFeedForward = kG*Math.cos(currentAngle);
        double power = Range.clip(frictionFeedForward+gravityFeedForward+pidPower, -1, 1);//did not clip
        armMotor.setPower(power);
        telemetry.addLine("ARM");
        telemetry.addData("Arm Current Angle", currentAngle);
        telemetry.addData("Arm Target Angle", targetAngle);
        telemetry.addData("Arm Motor Power", power);
    }

    public void setTargetAngle(double targetAngle) {
        this.targetAngle = targetAngle;
    }

}
