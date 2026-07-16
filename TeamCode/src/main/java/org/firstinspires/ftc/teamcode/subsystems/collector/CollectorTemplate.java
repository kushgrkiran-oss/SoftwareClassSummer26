package org.firstinspires.ftc.teamcode.subsystems.collector;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
public class CollectorTemplate {
    public enum CollectorState {
        OFF, INTAKE, EXTAKE
    }
    // declare your static variables below

    // declare your instance data below
    private CollectorState collectorState;
    private DcMotorEx collectorMotor;
    private Telemetry telemetry;

    public CollectorTemplate(HardwareMap hardwareMap, Telemetry telemetry) {
        this.collectorState = CollectorState.OFF;
        this.collectorMotor = hardwareMap.get(DcMotorEx.class, "intake");
        this.telemetry = telemetry;
    }

    public void update() {
        switch(collectorState) {
            case OFF:
                collectorMotor.setPower(0);
                break;
            case INTAKE:
                collectorMotor.setPower(1);
                break;
            case EXTAKE:
                collectorMotor.setPower(-1);
                break;

        }
        telemetry.addLine("---COLLECTOR---");
        telemetry.addData("collectorState", collectorState);
        telemetry.addData("motorPower", collectorMotor.getPower());
    }

    public CollectorState getCollectorState() {
        return collectorState;
    }
    public void setCollectorState(CollectorState collectorState) {
        this.collectorState = collectorState;
    }
}