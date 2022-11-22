package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleOp POWERPLAY")

public class TeleOpPP extends LinearOpMode {
    //
    double k = 0.5;
    @Override
    public void runOpMode(){
        //
        Hardawiwary hw = new Hardawiwary(hardwareMap);//конструктор конструировать

        hw.deActivateEncodersMove();
        hw.deActivateEncodersLift();
        hw.deActivateEncodersIntake();

        waitForStart();
        while (opModeIsActive()){
            //

            if(gamepad1.x){
                k = 0.4;
            }

            if(gamepad1.y){
                k = 0.7;
            }

            hw.move(gamepad1.left_stick_x, gamepad1.left_stick_y,
                    gamepad1.left_trigger - gamepad1.right_trigger, k);
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