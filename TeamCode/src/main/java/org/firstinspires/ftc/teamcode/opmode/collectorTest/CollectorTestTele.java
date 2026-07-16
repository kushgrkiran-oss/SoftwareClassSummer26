package org.firstinspires.ftc.teamcode.opmode.collectorTest;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.subsystems.collector.CollectorTemplate;

@TeleOp(name = "CollectorTestTele")
public class CollectorTestTele extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        CollectorTemplate collector = new CollectorTemplate(hardwareMap, telemetry);
        telemetry.addLine("READY");
        telemetry.update();
        waitForStart();
        while(opModeIsActive()) {
            if (gamepad1.right_trigger > 0.1)
                collector.setCollectorState(CollectorTemplate.CollectorState.INTAKE);
            else if (gamepad1.left_trigger > 0.1)
                collector.setCollectorState(CollectorTemplate.CollectorState.EXTAKE);
            else {
                collector.setCollectorState(CollectorTemplate.CollectorState.OFF);
            }
            collector.update();
            telemetry.update();
        }
    }
}
