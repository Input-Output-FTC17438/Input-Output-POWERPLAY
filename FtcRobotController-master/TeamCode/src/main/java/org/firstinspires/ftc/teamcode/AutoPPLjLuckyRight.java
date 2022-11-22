package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "AutoPP Lj Lucky")

public class AutoPPLjLuckyRight extends LinearOpMode {
    //переменные и объекты классов, которые
    // используются во всех процедурах внутри
    // класса

    // CR, CB - Catch Motors Right, Left
    public DcMotor TL, TR, BL, BR;
    //public DcMotor intake;
    public DcMotor Lift;
    public Servo intakeSL, intakeSR;
    double tl, tr, bl, br, r, d, x, y;
    double servoPos = 0.0;



    @Override
    public void runOpMode() {
        //действия исполняемые до нажатия
        // кнопки "пуск"

        TL = hardwareMap.dcMotor.get("tl");
        TR = hardwareMap.dcMotor.get("tr");
        BL = hardwareMap.dcMotor.get("bl");
        BR = hardwareMap.dcMotor.get("br");

        intakeSL = hardwareMap.servo.get("ssl");
        intakeSR = hardwareMap.servo.get("ssr");

        intakeSL.setPosition(servoPos);
        intakeSR.setPosition(1.0-servoPos);

        Lift = hardwareMap.dcMotor.get("lift");


        TL.setDirection(DcMotorSimple.Direction.FORWARD);
        TR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);

        Lift.setDirection(DcMotorSimple.Direction.REVERSE);


        // Send telemetry message to signify robot waiting;
        telemetry.addLine("Робот готов, хуярь (кого)!!!!!");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // действия, которые выполняются
            // после нажатия кнопки "пуск"
            // (включая логические процессы, тдтп)

            intakeSL.setPosition(0.7);
            intakeSR.setPosition(0.3);
            liftgo(1, 3000);
            gogogo (0.0, 0.3, 0, 260);
            //liftgo(1, 1500);
            //gogogo(0.0, 0.3, 0, 100);
            liftgo(-1, 300);
            intakeSL.setPosition(0);
            intakeSR.setPosition(1);
            liftgo(1, 500);
            gogogo(0.0, -0.3, 0, 900);
            liftgo(-1, 2800);
            gogogo(0.0, 0.0, 0.3, 220);
            gogogo(0.0, 0.5, 0.0, 310);


            telemetry.addData("TL = ", TL);
            telemetry.addData("TR = ", TR);
            telemetry.addData("BL = ", BR);
            telemetry.addData("BR = ", BR);
            telemetry.addData("CATCH = ", d);
            telemetry.addData("R = ", r);
            telemetry.addData("lift = ", gamepad2.right_stick_y);
            telemetry.update();

        }

    }

    void gogogo(double x, double y, double  r, int t){
        double k = 1; // сбавь обороты
        x = x*k;
        y = y*k;
        r = r*k;

        tl = (x + y + r);
        tr = (-x + y - r);
        bl = (- x*0.5 + y + r);
        br = (x*0.5 + y - r);

        TL.setPower(tl);
        TR.setPower(tr);
        BL.setPower(bl);
        BR.setPower(br);
        sleep(t);
        TL.setPower(0);
        TR.setPower(0);
        BL.setPower(0);
        BR.setPower(0);
        sleep(100);
    }
    void liftgo(double lift, int t){
        Lift.setPower(lift);
        sleep(t);
        Lift.setPower(0);
        sleep(100);
    }
}