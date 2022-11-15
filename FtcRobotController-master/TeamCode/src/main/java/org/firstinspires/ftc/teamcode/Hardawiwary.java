package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

abstract class Hardawiwary extends LinearOpMode {
    DcMotor TL, TR, BL, BR;
    DcMotor intake;
    DcMotor lift;
    public void runOpMode(){
        TL.setMode(RUN_WITHOUT_ENCODER);

    }

}
