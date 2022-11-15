package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous(name = "autoboti vperdu")

public class autonomuuus extends LinearOpMode {
    //переменные и объекты классов, которые
    // используются во всех процедурах внутри
    // класса

    // CR, CB - Catch Motors Right, Left
    DcMotor LT, RT, LB, RB, CR, CL;
    double lt, rt, lb, rb, x, y, r, d;



    @Override
    public void runOpMode() {
        //действия исполняемые до нажатия
        // кнопки "пуск"

        LT = hardwareMap.get(DcMotor.class, "lt");
        RT = hardwareMap.dcMotor.get("rt");
        LB = hardwareMap.dcMotor.get("lb");
        RB = hardwareMap.dcMotor.get("rb");

        CL = hardwareMap.dcMotor.get("cl");
        CR = hardwareMap.dcMotor.get("cr");


        LT.setDirection(DcMotorSimple.Direction.REVERSE);
        RT.setDirection(DcMotorSimple.Direction.FORWARD);
        LB.setDirection(DcMotorSimple.Direction.REVERSE);
        RB.setDirection(DcMotorSimple.Direction.FORWARD);

        CL.setDirection(DcMotorSimple.Direction.FORWARD);
        CR.setDirection(DcMotorSimple.Direction.REVERSE);


        // Send telemetry message to signify robot waiting;
        telemetry.addLine("Робот готов, хуярь (кого)!!!!!");
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


            double k = 0.5;
            x = x*k;
            y = y*k;
            r = r*k; //хуйня уменьшающая скорость




            // Захват
            d = gamepad2.left_trigger - gamepad2.right_trigger;

            CL.setPower(d);
            CR.setPower(d);


            //вывод в телеметрию
            telemetry.addData("LT = ", lt);
            telemetry.addData("RT = ", rt);
            telemetry.addData("LB = ", rb);
            telemetry.addData("RB = ", rb);
            telemetry.addData("CATCH = ", d);
            telemetry.update();

        }
    }
    public void move(double x, double y, double r){
        lt = x + y - r; //хуйня задающая мощности моторов
        rt = -x + y + r;
        lb = - x + y - r;
        rb = x + y + r;


        LT.setPower(lt);//хуйня передающая значения мощностей моторов
        RT.setPower(rt);
        LB.setPower(lb);
        RB.setPower(rb);
    }
}
