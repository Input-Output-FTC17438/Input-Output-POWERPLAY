package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "Autonomous POWERPLAY Lucky RIGHT")

public class AutonomousPowerplayLuckyRight extends LinearOpMode {
    Hardawiwary hw = new Hardawiwary();
    @Override
    public void runOpMode(){
        hw.init();
        hw.activateEncodersMove();
        hw.activateEncodersLift();
        hw.deActivateEncodersIntake();
        waitForStart();
        if (opModeIsActive()){
            hw.moveEnc(0 ,1, 0, 1000);
            hw.liftEnc(1000);
            hw.moveEnc(0, 0.5, 0, 100);
            hw.intakeRunTimer(1, 1000);
            hw.moveEnc(0, -1, -0.5, 500);
            hw.moveEnc(0, 1, 0, 1000);
        }
    }
}
