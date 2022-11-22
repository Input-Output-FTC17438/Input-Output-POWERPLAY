package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoPP MoveNStop")

public class AutoPPSimple extends LinearOpMode {
    @Override
    public void runOpMode(){
        Hardawiwary hw = new Hardawiwary(hardwareMap);
        waitForStart();
        if(opModeIsActive()){
            hw.deActivateEncodersMove();
            hw.moveTimer(0.7, 0.7, 0, 1500, false);
        }
    }
}
