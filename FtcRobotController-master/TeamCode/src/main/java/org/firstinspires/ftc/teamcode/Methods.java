package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.hardware.bosch.JustLoggingAccelerationIntegrator;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;

import  com.qualcomm.robotcore.hardware.VoltageSensor;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;
import org.openftc.easyopencv.OpenCvWebcam;

import java.util.ArrayList;
import java.util.List;


@Autonomous(name= "Methods", group="Autonomous")
public class Methods extends LinearOpMode {
    DcMotor leftF, rightF, leftB, rightB, pod, drin;
    CRServo zaxvat, pisun, big;
    BNO055IMU imu;
    Orientation angles;
    VoltageSensor sensor;
    double speed;
    OpenCvWebcam phoneCam;

    private static int valLeft = -1;
    private static int valRight = -1;
    private static float rectHeight = 0.7f / 8f;
    private static float rectWidth = 0.7f / 8f;
    private static float rectHeight1 = 0.7f / 8f;
    private static float rectWidth1 = 0.7f / 8f;

    private static float offsetX = 0f / 8f;//changing this moves the three rects and the three circles left or right, range : (-2, 2) not inclusive
    private static float offsetY = 0f / 8f;//changing this moves the three rects and circles up or down, range: (-4, 4) not inclusive

    private static float[] leftPos = {5.5f / 8f + offsetX, 4f / 8f + offsetY};
    private static float[] rightPos = {6.5f / 8f + offsetX, 4f / 8f + offsetY};

    private final int rows = 640;
    private final int cols = 480;


    public void kub_verx () {
        pod.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pod.setTargetPosition(3000); //2100 verx
        pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pod.setPower(1);
        while ((opModeIsActive() && (pod.isBusy()))){
        }
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(0);
        sleep(100);

    }
    public void kub_mid () {
        pod.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pod.setTargetPosition(2250); //2100 verx
        pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pod.setPower(1);
        while ((opModeIsActive() && (pod.isBusy()))){
        }
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(0);
        sleep(100);

    }
    public void kub_niz (){
        pod.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pod.setTargetPosition(1250); //2100 verx
        pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pod.setPower(1);
        while ((opModeIsActive() && (pod.isBusy()))){
        }
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(0);
        sleep(100);

    }

    public void kub_down (int uroven) {
        pod.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pod.setTargetPosition(-uroven);
        pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pod.setPower(1);
        while ((opModeIsActive() && (pod.isBusy()))){
        }
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        sleep(100);

        pod.setPower(0);
        sleep(1);
    }

    public void vikidish_castom(int uroven){
        pod.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pod.setTargetPosition(uroven);
        pod.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        pod.setPower(1);
        while ((opModeIsActive() && (pod.isBusy()))){
        }
        pod.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pod.setPower(0);
        sleep(100);
    }

    /*public void vikidisch_verx(double napr){ //1 - left, -1 - right
        kub_verx();
        stop_all();
        sleep(100);
        vikidisch.setPower(napr);
        sleep(2000);
        vikidisch.setPower(0.05);
        sleep(500);
    }
    public void vikidisch_mid(double napr){
        kub_mid();
        stop_all();
        sleep(100);
        vikidisch.setPower(napr); //-1
        sleep(2000);
        vikidisch.setPower(0.05);
        sleep(500);
    }
    public void vikidisch_niz(double napr){
        kub_niz();
        stop_all();
        sleep(100);
        vikidisch.setPower(napr); //-1
        sleep(2000);
        vikidisch.setPower(0.05);
        sleep(500);
    }*/


    public void resetEncoders() {
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
    }

    public void vpravo(int pos, double speed) {
        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftF.setTargetPosition(-pos);
        rightB.setTargetPosition(pos);
        rightF.setTargetPosition(-pos);
        leftB.setTargetPosition(pos);
        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftF.setPower(speed);
        rightB.setPower(speed);
        rightF.setPower(speed);
        leftB.setPower(speed);
        while (opModeIsActive() && (leftF.isBusy()) && (rightF.isBusy()) && (rightB.isBusy()) && (leftB.isBusy())) {

        }

        // Stop all motion;
        rightB.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        leftF.setPower(0);
        sleep(100);

    }

    public void vlevo(int pos, double speed) {
        vpravo(-pos, speed);
    }



    public void LB(int pos, double speed) {

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftF.setTargetPosition(pos);
        rightB.setTargetPosition(-pos);
        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftF.setPower(speed);
        rightB.setPower(speed);

        while (opModeIsActive() && (leftF.isBusy()) && (rightB.isBusy())) {

            telemetry.addData("Path2", "Running at %7d :%7d : %7d :%7d",
                    leftF.getCurrentPosition(),
                    rightB.getCurrentPosition(), rightF.getCurrentPosition(), leftB.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        rightB.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        leftF.setPower(0);
        sleep(100);
    }

    public void RB(int pos, double speed) {

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setTargetPosition(pos);
        rightF.setTargetPosition(-pos);
        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setPower(speed);
        leftB.setPower(speed);

        while (opModeIsActive() && (leftB.isBusy()) && (rightF.isBusy())) {

            telemetry.addData("Path2", "Running at %7d :%7d : %7d :%7d",
                    leftF.getCurrentPosition(),
                    rightB.getCurrentPosition(), rightF.getCurrentPosition(), leftB.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        rightB.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        leftF.setPower(0);
        sleep(100);

    }

    public void LF(int pos, double speed) {

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setTargetPosition(-pos);
        rightF.setTargetPosition(pos);
        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setPower(speed);
        leftB.setPower(speed);

        while (opModeIsActive() && (leftB.isBusy()) && (rightF.isBusy())) {

            telemetry.addData("Path2", "Running at %7d :%7d : %7d :%7d",
                    leftF.getCurrentPosition(),
                    rightB.getCurrentPosition(), rightF.getCurrentPosition(), leftB.getCurrentPosition());
            telemetry.update();
        }

        // Stop all motion;
        rightB.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        leftF.setPower(0);
        sleep(100);

    }




    /*public void nazad() {
        leftF.setPower(speed);
        rightB.setPower(-speed);
        rightF.setPower(-speed);
        leftB.setPower(speed);
    }*/
    public void nazad(int pos, double speed) {
        vpered(-pos, speed);
    }

    public void vpered(int pos, double speed) {

        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftF.setTargetPosition(-pos);
        rightB.setTargetPosition(pos);
        rightF.setTargetPosition(pos);
        leftB.setTargetPosition(-pos);
        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftF.setPower(speed);
        rightB.setPower(speed);
        rightF.setPower(speed);
        leftB.setPower(speed);
        while (opModeIsActive() && (leftF.isBusy()) && (rightF.isBusy()) && (rightB.isBusy()) && (leftB.isBusy())) {

            telemetry.addData("Path2", "Running at %7d :%7d : %7d :%7d",
                    leftF.getCurrentPosition(),
                    rightB.getCurrentPosition(), rightF.getCurrentPosition(), leftB.getCurrentPosition());
            telemetry.update();
        }
        rightB.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        leftF.setPower(0);
        sleep(100);
    }

    public void razvarot(int pos, double speed) {
        leftF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightB.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightF.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        leftF.setTargetPosition(pos);
        rightB.setTargetPosition(pos);
        rightF.setTargetPosition(pos);
        leftB.setTargetPosition(pos);
        leftF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightF.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        rightB.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftF.setPower(speed);
        rightB.setPower(speed);
        rightF.setPower(speed);
        leftB.setPower(speed);
        while (opModeIsActive() && (leftF.isBusy()) && (rightF.isBusy()) && (rightB.isBusy()) && (leftB.isBusy())) {

            telemetry.addData("Path2", "Running at %7d :%7d : %7d :%7d",
                    leftF.getCurrentPosition(),
                    rightB.getCurrentPosition(), rightF.getCurrentPosition(), leftB.getCurrentPosition());
            telemetry.update();
        }
        rightB.setPower(0);
        leftB.setPower(0);
        rightF.setPower(0);
        leftF.setPower(0);
    }

    public void vpered2() {
        leftF.setPower(-speed);
        rightB.setPower(speed);
        rightF.setPower(speed);
        leftB.setPower(-speed);

    }


    public void vpravo2() {
        leftF.setPower(-speed);
        rightB.setPower(speed);
        rightF.setPower(-speed);
        leftB.setPower(speed);

    }

    /*public void vlevo() {
        leftF.setPower(speed);
        rightB.setPower(-speed);
        rightF.setPower(speed);
        leftB.setPower(-speed);
    }*/

    public void razvarotplus() {
        leftF.setPower(speed);
        rightB.setPower(speed);
        rightF.setPower(speed);
        leftB.setPower(speed);
    }


    public void razvarotminus() {
        leftF.setPower(-speed);
        rightB.setPower(-speed);
        rightF.setPower(-speed);
        leftB.setPower(-speed);
    }

    public void stop_all() {
        rightB.setPower(-0);
        leftB.setPower(0);
        rightF.setPower(-0);
        leftF.setPower(0);
    }

    public void stop_all3() {
        rightB.setPower(-0);
        leftB.setPower(0);
        rightF.setPower(-0);
        leftF.setPower(0);
    }

    public void turnl(double ugol, double speed) {
        leftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        double degrees = angles.firstAngle;
        while ((ugol - degrees) >= 4) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
            degrees = angles.firstAngle;
            telemetry.addData("degrees", degrees);
            telemetry.addData("ugol", ugol);
            telemetry.addData("rasn", (ugol - degrees));
            telemetry.update();

            leftF.setPower(speed);
            rightF.setPower(speed);
            leftB.setPower(speed);
            rightB.setPower(speed);
        }
        leftF.setPower(0);
        rightF.setPower(0);
        leftB.setPower(0);
        rightB.setPower(0);
        sleep(500);
        while ((ugol - degrees) <= -0.1) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
            degrees = angles.firstAngle;
            telemetry.addData("degrees", degrees);
            telemetry.addData("ugol", ugol);
            telemetry.addData("rasn", (ugol - degrees));
            telemetry.update();
            leftF.setPower(-speed);
            rightF.setPower(-speed);
            leftB.setPower(-speed);
            rightB.setPower(-speed);


        }

        leftB.setPower(0);
        rightB.setPower(0);
        leftB.setPower(0);
        rightB.setPower(0);
    }

    public void turnr(double ugol, double speed) {
        leftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
        double degrees = angles.firstAngle;
        while ((ugol - degrees) <= -4.0) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
            degrees = angles.firstAngle;
            telemetry.addData("degrees", degrees);
            telemetry.addData("ugol", ugol);
            telemetry.addData("rasn", Math.abs(ugol - degrees));
            telemetry.update();

            leftF.setPower(-speed);
            rightF.setPower(-speed);
            leftF.setPower(-speed);
            rightB.setPower(-speed);
        }
        leftF.setPower(0);
        rightF.setPower(0);
        leftB.setPower(0);
        rightB.setPower(0);
        sleep(500);
        while ((ugol - degrees) >= 4) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.XYZ, AngleUnit.DEGREES);
            degrees = angles.firstAngle;
            telemetry.addData("degrees", degrees);
            telemetry.addData("ugol", ugol);
            telemetry.addData("rasn", Math.abs(ugol - degrees));
            telemetry.update();
            leftF.setPower(speed);
            rightF.setPower(speed);
            leftB.setPower(speed);
            rightB.setPower(speed);


        }
        leftB.setPower(0);
        rightB.setPower(0);
        leftB.setPower(0);
        rightB.setPower(0);

    }

    public void turnr2(double ugol, double speed) {
        leftF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightF.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        leftB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightB.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
        double degrees = angles.firstAngle;
        while (Math.abs(ugol - degrees) >= 4) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
            degrees = angles.firstAngle;
            telemetry.addData("degrees", degrees);
            telemetry.addData("ugol", ugol);
            telemetry.addData("rasn", Math.abs(ugol - degrees));
            telemetry.update();
            leftF.setPower(-speed);
            rightF.setPower(-speed);
            leftB.setPower(-speed);
            rightB.setPower(-speed);


        }
        leftF.setPower(0);
        rightF.setPower(0);
        leftB.setPower(0);
        rightB.setPower(0);
        sleep(500);
        while ((ugol - degrees) >= 4.0) {
            angles = imu.getAngularOrientation(AxesReference.INTRINSIC, AxesOrder.YXZ, AngleUnit.DEGREES);
            degrees = angles.firstAngle;
            telemetry.addData("degrees", degrees);
            telemetry.addData("ugol", ugol);
            telemetry.addData("rasn", (ugol - degrees));
            telemetry.update();

            leftF.setPower(speed);
            rightF.setPower(speed);
            leftF.setPower(speed);
            rightB.setPower(speed);
        }
        leftF.setPower(0);
        rightF.setPower(0);
        leftB.setPower(0);
        rightB.setPower(0);
        sleep(500);

        leftB.setPower(0);
        rightB.setPower(0);
        leftB.setPower(0);
        rightB.setPower(0);

    }

    public void initGyro() {
        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
        parameters.angleUnit = BNO055IMU.AngleUnit.DEGREES;
        parameters.accelUnit = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
        //parameters.calibrationDataFile = "GyroCal.json"; // see the calibration sample opmode
        parameters.loggingEnabled = true;
        parameters.loggingTag = "IMU";
        parameters.accelerationIntegrationAlgorithm = new JustLoggingAccelerationIntegrator();
        //
        imu = hardwareMap.get(BNO055IMU.class, "imu");
        imu.initialize(parameters);
    }

    /*public void vobla228() {
        vobla.setPower(-0.6);
        sleep(500);
        vobla.setPower(0.1);
        sleep(200);
        vobla.setPower(0);
        sleep(300);
        zaxvat.setPower(-0.3);
        vobla.setPower(0);
        sleep(400);
        vobla.setPower(0.6);
        sleep(250);
        vobla.setPower(0);
        sleep(300);
        zaxvat.setPower(0.53);
        sleep(20);
    }*/

    double getBatteryVoltage() {
        double result = Double.POSITIVE_INFINITY;
        for (VoltageSensor sensor : hardwareMap.voltageSensor) {
            double voltage = sensor.getVoltage();
            if (voltage > 0) {
                result = Math.min(result, voltage);
            }
        }
        return result;
    }



    public void runOpMode() throws InterruptedException {}

    static class StageSwitchingPipeline extends OpenCvPipeline {
        Mat yCbCrChan2Mat = new Mat();
        Mat thresholdMat = new Mat();
        Mat all = new Mat();
        List<MatOfPoint> contoursList = new ArrayList<>();

        enum Stage {//color difference. greyscale
            detection,//includes outlines
            THRESHOLD,//b&w
            RAW_IMAGE,//displays raw view
        }

        private A_Test_cam.StageSwitchingPipeline.Stage stageToRenderToViewport = A_Test_cam.StageSwitchingPipeline.Stage.detection;
        private A_Test_cam.StageSwitchingPipeline.Stage[] stages = A_Test_cam.StageSwitchingPipeline.Stage.values();

        @Override
        public void onViewportTapped() {
            /*
             * Note that this method is invoked from the UI thread
             * so whatever we do here, we must do quickly.
             */

            int currentStageNum = stageToRenderToViewport.ordinal();

            int nextStageNum = currentStageNum + 1;

            if (nextStageNum >= stages.length) {
                nextStageNum = 0;
            }

            stageToRenderToViewport = stages[nextStageNum];
        }

        @Override
        public Mat processFrame(Mat input) {
            contoursList.clear();
            /*
             * This pipeline finds the contours of yellow blobs such as the Gold Mineral
             * from the Rover Ruckus game.
             */

            //color diff cb.
            //lower cb = more blue = skystone = white
            //higher cb = less blue = yellow stone = grey
            Imgproc.cvtColor(input, yCbCrChan2Mat, Imgproc.COLOR_RGB2YCrCb);//converts rgb to ycrcb
            Core.extractChannel(yCbCrChan2Mat, yCbCrChan2Mat, 2);//takes cb difference and stores

            //b&w
            Imgproc.threshold(yCbCrChan2Mat, thresholdMat, 112, 255, Imgproc.THRESH_BINARY_INV);

            //outline/contour
            Imgproc.findContours(thresholdMat, contoursList, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);
            yCbCrChan2Mat.copyTo(all);//copies mat object
            //Imgproc.drawContours(all, contoursList, -1, new Scalar(255, 0, 0), 3, 8);//draws blue contours


            //get values from frame


            double[] pixLeft = thresholdMat.get((int) (input.rows() * leftPos[1]), (int) (input.cols() * leftPos[0]));//gets value at circle
            valLeft = (int) pixLeft[0];

            double[] pixRight = thresholdMat.get((int) (input.rows() * rightPos[1]), (int) (input.cols() * rightPos[0]));//gets value at circle
            valRight = (int) pixRight[0];

            //create three points
            Point pointLeft = new Point((int) (input.cols() * leftPos[0]), (int) (input.rows() * leftPos[1]));
            Point pointRight = new Point((int) (input.cols() * rightPos[0]), (int) (input.rows() * rightPos[1]));

            //draw circles on those points
            Imgproc.circle(all, pointLeft, 5, new Scalar(255, 0, 0), 1);//draws circle
            Imgproc.circle(all, pointRight, 5, new Scalar(255, 0, 0), 1);//draws circle

            //draw 3 rectangles
            Imgproc.rectangle(//1-3
                    all,
                    new Point(
                            input.cols() * (leftPos[0] - rectWidth1 / 2),
                            input.rows() * (leftPos[1] - rectHeight1 / 2)),
                    new Point(
                            input.cols() * (leftPos[0] + rectWidth1 / 2),
                            input.rows() * (leftPos[1] + rectHeight1 / 2)),
                    new Scalar(0, 255, 0), 3);

            Imgproc.rectangle(//5-7
                    all,
                    new Point(
                            input.cols() * (rightPos[0] - rectWidth / 2),
                            input.rows() * (rightPos[1] - rectHeight / 2)),
                    new Point(
                            input.cols() * (rightPos[0] + rectWidth / 2),
                            input.rows() * (rightPos[1] + rectHeight / 2)),
                    new Scalar(0, 255, 0), 3);

            switch (stageToRenderToViewport) {
                case THRESHOLD: {
                    return thresholdMat;
                }

                case detection: {
                    return all;
                }

                case RAW_IMAGE: {
                    return input;
                }

                default: {
                    return input;
                }
            }
        }
    }
}
