package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TeleOP POWERPLAY LOXOTRON")

public class TeleOp1 extends LinearOpMode {
    //переменные и объекты классов, которые
    // используются во всех процедурах внутри
    // класса

    // CR, CB - Catch Motors Right, Left
    DcMotor LT, RT, LB, RB, Catch/*, CR, CL*/;
//    Servo CLServo, CRServo;
    double lt, rt, lb, rb, x, y, r, d;
//
    double servoPosition = 0.0;



    @Override
    public void runOpMode() {
        //действия исполняемые до нажатия
        // кнопки "пуск"

        LT = hardwareMap.get(DcMotor.class, "lt");
        RT = hardwareMap.dcMotor.get("rt");
        LB = hardwareMap.dcMotor.get("lb");
        RB = hardwareMap.dcMotor.get("rb");

        /*CL = hardwareMap.dcMotor.get("cl");
        CR = hardwareMap.dcMotor.get("cr");*/

        /*CLServo = hardwareMap.servo.get("cls");
        CRServo = hardwareMap.servo.get("crs");*/

        Catch = hardwareMap.dcMotor.get("catch");


        LT.setDirection(DcMotorSimple.Direction.REVERSE);
        RT.setDirection(DcMotorSimple.Direction.FORWARD);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.FORWARD);

        /*CL.setDirection(DcMotorSimple.Direction.FORWARD);
        CR.setDirection(DcMotorSimple.Direction.REVERSE);*/

        /*CLServo.setDirection(Servo.Direction.FORWARD);
        CRServo.setDirection(Servo.Direction.REVERSE);

        CLServo.setPosition(servoPosition);
        CRServo.setPosition(1.0-servoPosition);*/


        // Send telemetry message to signify robot waiting;
        telemetry.addLine("Initialized");
        telemetry.update();

        waitForStart();

        while (opModeIsActive()) {
            // действия, которые выполняются
            // после нажатия кнопки "пуск"
            // (включая логические процессы, тдтп)


            // КБ
            x = gamepad1.left_stick_x;
            y = gamepad1.left_stick_y;
            r = gamepad1.right_trigger - gamepad1.left_trigger;

            // кб без проблем
            /*if(gamepad1.dpad_up){
                x = 0;
                y = 1;
                r = 0;
            }
            if(gamepad1.dpad_right){
                x = 1;
                y = 0;
                r = 0;
            }
            if(gamepad1.dpad_down){
                x = 0;
                y = -1;
                r = 0;
            }
            if(gamepad1.dpad_left){
                x = -1;
                y = 0;
                r = 0;
            }*/


            double k = 0.6; // сбавь обороты
            x = x*k;
            y = y*k;
            r = r*k;

            lt = (x + y - r);
            rt = (-x + y + r);
            lb = (- x + y - r);
            rb = (x + y + r);


            LT.setPower(lt);
            RT.setPower(rt);
            LB.setPower(lb);
            RB.setPower(rb);



            // Захват
            d = gamepad2.left_trigger - gamepad2.right_trigger;

            Catch.setPower(d);

            // Захват лохотрон на перекрестья
            /*if(gamepad2.dpad_up){
                servoPosition = 1.0;
                CLServo.setPosition(servoPosition);
                CRServo.setPosition(1.0-servoPosition);
            }
            if(gamepad2.dpad_right){
                servoPosition = 0.5;
                CLServo.setPosition(servoPosition);
                CRServo.setPosition(1.0-servoPosition);
            }
            if(gamepad2.dpad_down){
                servoPosition = 0.8;
                CLServo.setPosition(servoPosition);
                CRServo.setPosition(1.0-servoPosition);
            }
            if(gamepad2.dpad_left){
                servoPosition = 0.7;
                CLServo.setPosition(servoPosition);
                CRServo.setPosition(1.0-servoPosition);
            }

            if(gamepad2.left_bumper){
                servoPosition = servoPosition - 0.08;
                CLServo.setPosition(servoPosition);
                CRServo.setPosition(1.0-servoPosition);
            }
            if(gamepad2.right_bumper){
                servoPosition = servoPosition + 0.08;
                CLServo.setPosition(servoPosition);
                CRServo.setPosition(1.0-servoPosition);
            }*/



            telemetry.addData("LT = ", lt);
            telemetry.addData("RT = ", rt);
            telemetry.addData("LB = ", rb);
            telemetry.addData("RB = ", rb);
            telemetry.update();

        }
    }
}
