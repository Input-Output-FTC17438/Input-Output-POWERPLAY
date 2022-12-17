package org.firstinspires.ftc.teamcode;

import static org.firstinspires.ftc.robotcore.external.BlocksOpModeCompanion.hardwareMap;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.Point;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;


@Autonomous(name= "autoded", group="autonomous")
//
public class autoded extends Methods {
    private final ElapsedTime runtime = new ElapsedTime();


    private static int valLeft = -1;
    private static int valRight = -1;
    private static float rectHeight = 0.5f / 8f;
    private static float rectWidth = 0.5f / 8f;
    private static float rectHeight1 = 0.5f / 8f;
    private static float rectWidth1 = 0.5f / 8f;

    private static float offsetX = 0f / 8f;//changing this moves the three rects and the three circles left or right, range : (-2, 2) not inclusive
    private static float offsetY = 0f / 8f;//changing this moves the three rects and circles up or down, range: (-4, 4) not inclusive

    private static float[] leftPos = {6.1f / 8f + offsetX, 4f / 8f + offsetY};
    private static float[] rightPos = {5.3f / 8f + offsetX, 4 / 8f + offsetY};

    private final int rows = 640;
    private final int cols = 480;
    @Override
    public void runOpMode() throws InterruptedException {
        int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        phoneCam = OpenCvCameraFactory.getInstance().createWebcam(hardwareMap.get(WebcamName.class, "Webcam 1"), cameraMonitorViewId);
        phoneCam.openCameraDevice();
        phoneCam.setPipeline(new StageSwitchingPipeline());
        phoneCam.startStreaming(rows, cols, OpenCvCameraRotation.UPRIGHT);
        leftF = hardwareMap.dcMotor.get("tl");
        leftB = hardwareMap.dcMotor.get("bl");
        rightF = hardwareMap.dcMotor.get("tr");
        rightB = hardwareMap.dcMotor.get("br");
        left = hardwareMap.servo.get("ssl");
        right = hardwareMap.servo.get("ssr");

        waitForStart();

        runtime.reset();
        while (opModeIsActive()) {
            telemetry.addData("Values", valLeft + "  " + valRight);
            telemetry.update();
            vikidisch.setPower(0.05);

            if (valLeft == 255) {
                vikidish_castom(500);
                vpered(260, 0.4);
                razvarot(-550, 0.3);
                vpered(200, 0.4);
                vikidish_castom(-500);
                vikidisch.setPower(0.5);
                nazad(20, 0.4);
                vikidish_castom(200);
                razvarot(250, 0.4);
                razvarot(-250, 0.4);
                sleep(1000);
                nazad(150, 0.4);
                vlevo(550, 0.4);
                vikidisch.setPower(0.05);
                nazad(450, 0.4);
                sleep(3
                        0000);
            } else if (valRight == 255) {
                vikidish_castom(500);
                vpered(260, 0.4);
                razvarot(-550, 0.3);
                vpered(250, 0.4);
                vikidish_castom(-500);
                vikidisch.setPower(0.5);
                nazad(20, 0.4);
                vikidish_castom(200);
                razvarot(250, 0.4);
                razvarot(-250, 0.4);
                sleep(1000);
                nazad(200, 0.4);
                vlevo(550, 0.4);
                vikidisch.setPower(0.05);
                sleep(30000);
            } else {
                vikidish_castom(500);
                vpered(260, 0.4);
                razvarot(-550, 0.3);
                vpered(250, 0.4);
                vikidish_castom(-500);
                vikidisch.setPower(0.5);
                nazad(20, 0.4);
                vikidish_castom(200);
                razvarot(250, 0.4);
                razvarot(-250, 0.4);
                sleep(1000);
                nazad(200, 0.4);
                vlevo(550, 0.4);
                vikidisch.setPower(0.05);
                vpered(500, 0.4);
                sleep(30000);
            }
        }}
            /*
            if (valLeft == 255) {
            } else if (valRight == 255) {
            } else {
            }*/


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

        private Stage stageToRenderToViewport = Stage.detection;
        private  Stage[] stages = Stage.values();

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

