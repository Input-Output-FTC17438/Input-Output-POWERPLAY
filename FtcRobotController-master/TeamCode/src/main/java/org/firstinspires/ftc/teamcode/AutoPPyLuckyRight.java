package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoPP Lucky RIGHT")

public class AutoPPyLuckyRight extends LinearOpMode {
    Hardawiwary hw = new Hardawiwary();
    @Override
    public void runOpMode(){
        hw.init();
        hw.activateEncodersMove();
        hw.activateEncodersLift();
        hw.deActivateEncodersIntake();
        waitForStart();
        if (opModeIsActive()){
            hw.moveEnc(0 ,1, 0, 1000, true);
            hw.liftEnc(1000);
            hw.moveEnc(0, 0.5, 0, 100, false);
            hw.intakeRunTimer(1, 1000);
            hw.liftEnc(0);
            hw.moveEnc(0, -1, 0, 500, true);
            hw.moveEnc(0, 0, -0.5, 100, false);
            hw.moveEnc(0, 1, 0, 1000, true);
        }
    }
}
