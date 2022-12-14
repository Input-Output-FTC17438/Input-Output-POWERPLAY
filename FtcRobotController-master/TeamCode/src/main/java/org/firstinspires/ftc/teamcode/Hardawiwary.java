package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.linearOpMode;
import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.telemetry;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Autonomous(name = "HW")
@Disabled
public class Hardawiwary extends LinearOpMode {
    public DcMotor TL, TR, BL, BR;
    //public DcMotor intake;
    public DcMotor lift;
    public Servo intakeSL, intakeSR;
    double tl, tr, bl, br, r, d, x, y;
    boolean activeMove, activeLift, activeIntake;

    //public LinearOpMode opMode;

    double servoPos = 0.0;

    HardwareMap hwMap = null;
    ElapsedTime Timer = new ElapsedTime();

    public Hardawiwary(HardwareMap hardwareMap) { //конструктор - как тебе
        // конструировать
        hwMap = hardwareMap;

        TL = hwMap.dcMotor.get("tl");
        TR = hardwareMap.dcMotor.get("tr");
        BL = hardwareMap.dcMotor.get("bl");
        BR = hardwareMap.dcMotor.get("br");

        //intake = hardwareMap.dcMotor.get("intake");

        lift = hardwareMap.dcMotor.get("lift");

        intakeSL = hardwareMap.servo.get("ssl");
        intakeSR = hardwareMap.servo.get("ssr");



        TL.setDirection(DcMotorSimple.Direction.FORWARD);
        TR.setDirection(DcMotorSimple.Direction.REVERSE);
        BL.setDirection(DcMotorSimple.Direction.FORWARD);
        BR.setDirection(DcMotorSimple.Direction.REVERSE);

        //intake.setDirection(DcMotorSimple.Direction.FORWARD);

        lift.setDirection(DcMotorSimple.Direction.REVERSE);
    }


    public void activateEncodersMove() {
        TL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        activeMove = true;
    }


    public void activateEncodersLift() {
        lift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        activeLift = true;
    }


    public void activateEncodersIntake() {
        //intake.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        activeIntake = true;
    }


    public void deActivateEncodersLift() {
        lift.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        activeLift = true;
    }


    public void deActivateEncodersIntake() {
        //intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        activeIntake = true;
    }


    public void deActivateEncodersMove() {
        TL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        TR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BL.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        BR.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);


        activeMove = false;
    }


    public void move(double x, double y, double r, double k) {
        x = x * k;
        y = y * k;
        r = r * 0.3;

        tl = (x + y + r);
        tr = (-x + y - r);
        bl = (-x + y + r);
        br = (x + y - r);

        TL.setPower(tl);
        TR.setPower(tr);
        BL.setPower(bl);
        BR.setPower(br);



    }


    public void moveTimer(double x, double y, double r, int t, boolean f) {
        double k = 0.5; // сбавь обороты
        x = x * k;
        y = y * k;
        r = r * 0.2;

        tl = (x + y - r);
        tr = (-x + y + r);
        bl = (-x + y - r);
        br = (x + y + r);

        TL.setPower(tl);
        TR.setPower(tr);
        BL.setPower(bl);
        BR.setPower(br);

        while (Timer.milliseconds() < t){}
        stopMove(f);
        debugDelay();
    }


    public void moveEnc(double x, double y, double r, int tE, boolean f) {
        telemetry.addLine(activeMove ? "activeMove" : "inactiveMove");

        double k = 1; // сбавь обороты
        x = x * k;
        y = y * k;
        r = r * 0.2;

        tl = (x + y - r);
        tr = (-x + y + r);
        bl = (-x + y - r);
        br = (x + y + r);

        int stpos = TL.getCurrentPosition();
        while (linearOpMode.opModeIsActive() && ((Math.abs(Math.abs(TL.getCurrentPosition()) - Math.abs(stpos)) < tE)
                || (Math.abs(Math.abs(TL.getCurrentPosition()) - Math.abs(stpos)) < tE)
                || (Math.abs(Math.abs(TL.getCurrentPosition()) - Math.abs(stpos)) < tE)
                || (Math.abs(Math.abs(TL.getCurrentPosition()) - Math.abs(stpos)) < tE))) {
            TL.setPower(tl);
            TR.setPower(tr);
            BL.setPower(bl);
            BR.setPower(br);
        }
        stopMove(f);
        debugDelay();
    }


    public void stopMove(boolean f) {
        if (f) {
            TL.setPower(-TL.getPower());
            BL.setPower(-BL.getPower());
            TR.setPower(-TR.getPower());
            BR.setPower(-BR.getPower());
            Timer.reset();
            while (Timer.milliseconds() < 10) {}
        }
        TL.setPower(0);
        BL.setPower(0);
        TR.setPower(0);
        BR.setPower(0);
    }


    public void intakeRunTimer(double intakeD, int t) {
        //intake.setPower(intakeD);
        Timer.reset();
        while (linearOpMode.opModeIsActive() && Timer.milliseconds() < t){}
        //intakeStop();
        debugDelay();
    }


    public void intakeStop() {
        //intake.setPower(0);
    }


    public void intakeRun(double intakeD){
        //intake.setPower(intakeD);
    }


    public void liftRun(double liftD){
        lift.setPower(liftD);
    }


    public void liftStop(){
        lift.setPower(0);
    }


    public void liftRunTimer(double liftD, int t){
        lift.setPower(liftD);
        Timer.reset();
        while (linearOpMode.opModeIsActive() && Timer.milliseconds() < t){}
        liftStop();
        debugDelay();
    }


    public void liftEnc(int tE) {
        lift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        lift.setTargetPosition(tE);
        lift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        lift.setPower(0.7);
        while ((linearOpMode.opModeIsActive()) && (lift.isBusy())){}
        lift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        lift.setPower(0);
        debugDelay();
    }

    public void servoRun(double servoPos){
        intakeSL.setPosition(servoPos);
        intakeSR.setPosition(1.0-servoPos);
    }


    public void debugDelay() {
        Timer.reset();
        while (Timer.milliseconds() < 10){}
    }
    @Override
    public void runOpMode() {

    }
}