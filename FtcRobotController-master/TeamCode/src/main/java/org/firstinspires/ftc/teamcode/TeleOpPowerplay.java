package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp POWERPLAY")

public class TeleOpPowerplay extends LinearOpMode {
    Hardawiwary hw = new Hardawiwary();
    @Override
    public void runOpMode(){
        hw.init();
        hw.deActivateEncodersMove();
        hw.deActivateEncodersLift();
        hw.deActivateEncodersIntake();
        waitForStart();
        while (opModeIsActive()){
            hw.move(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_trigger - gamepad1.left_trigger, 0);
            hw.intakeRun(gamepad2.left_trigger - gamepad2.right_trigger);
            hw.liftRun(gamepad2.right_stick_y);
        }
    }
}
