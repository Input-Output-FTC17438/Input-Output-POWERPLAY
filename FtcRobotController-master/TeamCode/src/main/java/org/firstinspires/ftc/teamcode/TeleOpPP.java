package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp POWERPLAY")

public class TeleOpPP extends LinearOpMode {
    @Override
    public void runOpMode(){
        Hardawiwary hw = new Hardawiwary(this);//конструктор конструировать

        hw.deActivateEncodersMove();
        hw.deActivateEncodersLift();
        hw.deActivateEncodersIntake();

        waitForStart();
        while (opModeIsActive()){
            hw.move(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.right_trigger - gamepad1.left_trigger);
            hw.intakeRun(gamepad2.left_trigger - gamepad2.right_trigger);
            hw.liftRun(gamepad2.right_stick_y);

            if (gamepad2.dpad_up){
                hw.servoRun(0.0);
            }
            if (gamepad2.dpad_right){
                hw.servoRun(0.3);
            }
            if (gamepad2.dpad_down){
                hw.servoRun(0.7);
            }
            if (gamepad2.dpad_left){
                hw.servoRun(1);
            }
        }
    }
}