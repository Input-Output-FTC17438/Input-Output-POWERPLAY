package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

//TODO: РџР РћР›Р•РўРђР РР™, РџР•Р Р•Р” РўР•РњР¬, РљРђРљ РњР•РќРЇРўР¬ Р§РўРћ-РўРћ Р’ Р“РђРњРђРџР•Р”Р•, РџР РћР’Р•Р Р¬ РЎРќРђР§РђР›Рђ РњРђРўР¬ РђР“РђРџРђ!!!
@TeleOp(name = "Gamepad_based", group = "TeleOP")
public class TELEDED extends OpMode {
    DcMotor leftF, rightF, leftB, rightB;
    Servo right, left;
    private ElapsedTime runtime = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);


    @Override
    public void init() {
        leftF = hardwareMap.dcMotor.get("tl");
        leftB = hardwareMap.dcMotor.get("bl");
        rightF = hardwareMap.dcMotor.get("tr");
        rightB = hardwareMap.dcMotor.get("br");
        left = hardwareMap.servo.get("ssl");
        right = hardwareMap.servo.get("ssr");


    }


    @Override

    public void loop() {
        float pwrTrigger = (gamepad1.left_trigger);
        float pwrTrigger2 = (gamepad1.right_trigger);
        float pwrTrigger6 = (gamepad2.left_trigger);
        float pwrTrigger5 = (gamepad2.right_trigger);
        float pwrTrigger3 = (float) (gamepad2.left_trigger * 0.66);
        float pwrTrigger4 = (float) (gamepad2.right_trigger * 0.66);
        float StickX = (gamepad1.right_stick_x);
        float StickY = (gamepad1.right_stick_y);
        float Stick2X = (float) (gamepad1.left_stick_x * 0.3);
        float Stick2Y = (float) (gamepad1.left_stick_y * 0.3);
        //korob.setTargetPosition(720);
        double power = -1;
        // Р·Р°РЅСЏС‚Рѕ 1 РіРµР№РјРїР°Рґ: СЃС‚РёРєРё , С‚СЂРёРіРіРµСЂС‹ , Р±Р°РјРїРµСЂР°
        // Р·Р°РЅСЏС‚Рѕ 2 РіРµР№РјРїР°Рґ: РєСЂРµСЃС‚РѕРІРёРЅР° РІРІРµСЂС… Рё РІРЅРёР·, Р±Р°РјРїРµСЂР°, Р±СѓРєРІС‹, С‚СЂРёРіРіРµСЂС‹

        if (StickY != 0 || StickX != 0) {
            leftF.setPower((+StickY - StickX) + pwrTrigger);
            leftB.setPower((+StickY + StickX) + pwrTrigger);
            rightB.setPower((-StickY + StickX) + pwrTrigger2);
            rightF.setPower((-StickY - StickX) - pwrTrigger2);
        } else if (Stick2Y != 0 || Stick2X != 0) {
            leftF.setPower((+Stick2Y - Stick2X) + pwrTrigger);
            rightB.setPower((-Stick2Y + Stick2X) + pwrTrigger2);
            rightF.setPower((-Stick2Y - Stick2X) + pwrTrigger2);
            leftB.setPower((+Stick2Y + Stick2X) + pwrTrigger);
        } else if (pwrTrigger != 0) {
            leftF.setPower(0.6 * pwrTrigger);
            rightB.setPower(0.6 * pwrTrigger);
            rightF.setPower(0.6 * pwrTrigger);
            leftB.setPower(0.6 * pwrTrigger);
        } else if (pwrTrigger2 != 0) {
            leftF.setPower(-0.6 * pwrTrigger2);
            rightB.setPower(-0.6 * pwrTrigger2);
            rightF.setPower(-0.6 * pwrTrigger2);
            leftB.setPower(-0.6 * pwrTrigger2);
        } else if (gamepad1.left_bumper) {
            leftF.setPower(0.2);
            rightB.setPower(0.2);
            rightF.setPower(0.2);
            leftB.setPower(0.2);
        } else if (gamepad1.right_bumper) {
            leftF.setPower(-0.2);
            rightB.setPower(-0.2);
            rightF.setPower(-0.2);
            leftB.setPower(-0.2);
        } else {
            leftF.setPower(0);
            rightB.setPower(0);
            rightF.setPower(0);
            leftB.setPower(0);

        }
        if (gamepad2.dpad_up){
            right.setPosition(0.2);
            left.setPosition(0.2);
        }
       else if (gamepad2.dpad_right){
            right.setPosition(0.6);
            left.setPosition(0.6);
        }
        else if (gamepad2.dpad_down){
            right.setPosition(0.7);
            left.setPosition(0.7);
        }
        else if (gamepad2.dpad_left){
            right.setPosition(0.8);
            left.setPosition(0.8);
        } else {  right.setPosition(0);
            left.setPosition(0);
    }


        /*if (pwrTrigger5 != 0) {
            vobla.setPower(0.5 * pwrTrigger5);
        } else if (pwrTrigger6 != 0) {
            vobla.setPower(-0.5 * pwrTrigger6);
        }  else {
            vobla.setPower(0);
        }*/

       /* if (gamepad2.dpad_left) {
            krut.setPower(0.9);
        }
        else if (gamepad2.dpad_right){
            krut.setPower(-0.9);
        }
        else {
            krut.setPower(0);
        }*/
    }

}
      /*  if (gamepad1.start) {
            runtime.reset();
            while (runtime.time() <= 450) {
                tolk.setPower(0.75);
            }
            runtime.reset();
            while (runtime.time() <= 450) {
                tolk.setPower(0);
            }
            runtime.reset();
            while (runtime.time() <= 450) {
                tolk.setPower(0.75);
            }
            runtime.reset();
            while (runtime.time() <= 450) {
                tolk.setPower(0);
            }
            runtime.reset();
            while (runtime.time() <= 450) {
                tolk.setPower(0.75);
            }
            runtime.reset();
            while (runtime.time() <= 450) {
                tolk.setPower(0);
            }
            runtime.reset();
            while (runtime.time() <= 450) {
                tolk.setPower(0.75);
            }
            runtime.reset();
            while (runtime.time() <= 450) {
                tolk.setPower(0);
            }
        }
*/