package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp(name = "Autonomus Far POWERPLAY")
@Disabled

public class autonomuuus_far extends LinearOpMode {
    //переменные и объекты классов, которые
    // используются во всех процедурах внутри
    // класса

    // CR, CB - Catch Motors Right, Left
    DcMotor LT, RT, LB, RB, CR, CL, LIFT;
    double lt, rt, lb, rb, x, y, r;



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

            gogogo(-0.2,0.5,0,8000);
            //gogogo()
            viplunb(1500);

            telemetry.addData("LT = ", lt);
            telemetry.addData("RT = ", rt);
            telemetry.addData("LB = ", rb);
            telemetry.addData("RB = ", rb);

            telemetry.addData("R = ", r);
            telemetry.addData("LIFT = ", gamepad2.right_stick_y);
            telemetry.update();

        }

    }

    void viplunb(int t){

        CL.setPower(1);
        CR.setPower(1);
        sleep(t);
        CL.setPower(0);
        CR.setPower(0);
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
    }
}
