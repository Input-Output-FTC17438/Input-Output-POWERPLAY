package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Autonomus LJ LUCKY Right POWERPLAY")

public class autonom_lj_lucky extends LinearOpMode {
    //переменные и объекты классов, которые
    // используются во всех процедурах внутри
    // класса

    // CR, CB - Catch Motors Right, Left
    DcMotor LT, RT, LB, RB, CR, CL, LIFT;
    double lt, rt, lb, rb, x, y, r, d, lift;



    @Override
    public void runOpMode() {
        //действия исполняемые до нажатия
        // кнопки "пуск"

        LT = hardwareMap.dcMotor.get("lt");
        RT = hardwareMap.dcMotor.get("rt");
        LB = hardwareMap.dcMotor.get("lb");
        RB = hardwareMap.dcMotor.get("rb");

        CL = hardwareMap.dcMotor.get("cl");
        CR = hardwareMap.dcMotor.get("cr");

        LIFT = hardwareMap.dcMotor.get("lift");


        LT.setDirection(DcMotorSimple.Direction.FORWARD);
        RT.setDirection(DcMotorSimple.Direction.REVERSE);
        LB.setDirection(DcMotorSimple.Direction.FORWARD);
        RB.setDirection(DcMotorSimple.Direction.REVERSE);

        CL.setDirection(DcMotorSimple.Direction.FORWARD);
        CR.setDirection(DcMotorSimple.Direction.REVERSE);

        LIFT.setDirection(DcMotorSimple.Direction.FORWARD);


        // Send telemetry message to signify robot waiting;
        telemetry.addLine("Робот готов, хуярь (кого)!!!!!");
        telemetry.update();

        waitForStart();

        if (opModeIsActive()) {
            // действия, которые выполняются
            // после нажатия кнопки "пуск"
            // (включая логические процессы, тдтп)

            gogogo (0.0, 0.3, 0, 600);
            //liftencgo(500);
            liftgo(0.5, 6000);
            gogogo(0.0, 0.3, 0, 250);
            catchgo(-1, 5000);
            liftgo(-0.5, 4000);
            //liftencgo(-5000);
            gogogo(0.0, -0.3, 0, 900);
            gogogo(0.0, 0.0, 0.3, 400);
            gogogo(0.0, 0.5, 0.0, 500);


            telemetry.addData("LT = ", lt);
            telemetry.addData("RT = ", rt);
            telemetry.addData("LB = ", rb);
            telemetry.addData("RB = ", rb);
            telemetry.addData("CATCH = ", d);
            telemetry.addData("R = ", r);
            telemetry.addData("LIFT = ", gamepad2.right_stick_y);
            telemetry.update();

        }

    }

    void gogogo(double x, double y, double  r, int t){
        double k = 1; // сбавь обороты
        x = x*k;
        y = y*k;
        r = r*k;

        lt = (x + y + r);
        rt = (-x + y - r);
        lb = (- x*0.5 + y + r);
        rb = (x*0.5 + y - r);

        LT.setPower(lt);
        RT.setPower(rt);
        LB.setPower(lb);
        RB.setPower(rb);
        sleep(t);
        LT.setPower(0);
        RT.setPower(0);
        LB.setPower(0);
        RB.setPower(0);
        sleep(100);
    }
    void catchgo(double dc, int t){
        CL.setPower(dc);
        CR.setPower(dc);
        sleep(t);
        CL.setPower(0);
        CR.setPower(0);
        sleep(100);
    }
    void liftgo(double lift, int t){
        LIFT.setPower(lift);
        sleep(t);
        LIFT.setPower(0);
        sleep(100);
    }
    void liftencgo(int level){
        LIFT.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        LIFT.setTargetPosition(level);
        LIFT.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        LIFT.setPower(0.5);
        while ((opModeIsActive()) && (LIFT.isBusy())){

        }
        LIFT.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        LIFT.setPower(0);
        sleep(100);
    }
}
