package org.firstinspires.ftc.teamcode.subsystems.turret;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config //justin told me how to do this :)
public class TurretTemplate {
    public enum TurretState {
        OFF, POINT_AT_ANGLE, SWING_PAST_ANGLE
    }
    // declare your static variables below
    private static double radiansPerEncoder;
    private static double kP = 0;
    private static double kI = 0;
    private static double kD = 0;
    public static double turretPower = 0;
    private static double bangBangPower;
    // declare your instance data below
    private final DcMotorEx turretMotor;
    private double currentAngle;
    private PIDController pid;
    private double targetAngle;
    private TurretState turretState;
    private final Telemetry telemetry; //I forgot what final was but know im good
    private double targetDirection;
    //I switched the order of the constructor after looking at answer key idk if that did anything
    public TurretTemplate(HardwareMap hardwareMap, Telemetry telemetry) {
        this.telemetry = telemetry;
        this.turretMotor = hardwareMap.get(DcMotorEx.class, "turretMotor");
        pid = new PIDController(kP, kI, kD);//didnt do this had to look at the answer key but i get it now
        setTurretState(TurretState.OFF);
    }

    public void update() {
        // calculate the current turret angle below
        double turretEncoder = turretMotor.getCurrentPosition();
        currentAngle = turretEncoder * radiansPerEncoder;//didnt do any of this but i get it now

        // switch statement goes here
        switch(turretState) {
            case OFF:
                turretMotor.setPower(0);
                break;
            case POINT_AT_ANGLE:
                double power = pid.calculate(currentAngle, targetAngle);//didnt do it but i get it now
                turretMotor.setPower(power);//did this
                break;
            case SWING_PAST_ANGLE:
                if(Math.signum(currentAngle-targetAngle) == targetDirection) {//didnt do the math.signum part but again i get it now
                    turretMotor.setPower(bangBangPower);//did this
                }
                else {
                    turretMotor.setPower(0);
                    setTurretState(TurretState.OFF);
                }
                break;
        }
        telemetry.addLine("TURRET----------");
        telemetry.addData("Current Angle", currentAngle);
        telemetry.addData("Target Angle", targetAngle);
        telemetry.addData("Turret Power", turretMotor.getPower());
    }//looked off of the answer key for turret power cuz i wanted to add one more and it seemed like valuable info

    // finish these functions below
    public TurretState getTurretState() {
        return turretState;
    }
    public void setTurretState(TurretState turretState) {
        this.turretState = turretState;
        if (turretState == TurretState.SWING_PAST_ANGLE) {//guess what; didnt do it but i get it now
            targetDirection = Math.signum(targetAngle-getCurrentAngle());//did this cuz i figured out what math.signum was after the first one
        }//i had a syntax error where i flipped the two sides of the "equation" on line 76 but now ik that the order is the thing ur tryna define = def not the other way around
    }
    public double getCurrentAngle() {
        return currentAngle;
    }
    //set this.targetAngle = targetAngle instead of = target before
    public void setTargetAngle(double target) {//didnd do it but i get it now
        this.targetAngle = target;
    }
    //had no idea what setMode was and what the specific modes were before checking answer key
    public void resetTurretEncoder() {
        turretMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        currentAngle = 0;//wrote this
        turretMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
    }
}//I GET IT NOW YIPEE
