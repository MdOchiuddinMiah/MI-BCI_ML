package emotivdataclassification;

import com.emotiv.Iedk.Edk;
import com.emotiv.Iedk.EdkErrorCode;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.DoubleByReference;
import com.sun.jna.ptr.IntByReference;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.animation.ScaleTransition;
import javafx.animation.TranslateTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.scene.control.Button;
import javafx.scene.shape.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.util.Duration;

/**
 *
 * @author Rashed
 */
public class EmotivDataClassificationFXMLController implements Initializable {

    static int totalTrials;
    static int movementTotalCount;
    static int steadyTotalCount;
    static String previousState = "Steady";
    static String currentState;
    private int dataRowCount = 1;

    TranslateTransition translateTransition1, translateTransition2, translateTransition3, translateTransition4, translateTransition5;
    ScaleTransition scaleTransition1, scaleTransition2, scaleTransition3, scaleTransition4, scaleTransition5, scaleTransition6;
    RotateTransition rotateTransition1, rotateTransition2, rotateTransition3, rotateTransition4, rotateTransition5;
    ParallelTransition parallelTransition;
    static double animationDiration;
    Boolean animationEnd = true;

    @FXML
    private Label totalTrialsLabelTxt;
    @FXML
    private Label movementTotalCountLabelTxt;
    @FXML
    private Label steadyTotalCountLabelTxt;
    @FXML
    private Label currentStateLabelTxt;
    @FXML
    private TextField numberOfDataRowCount;
    @FXML
    private Button circleBtn1;
    @FXML
    private Button circleBtn2;
    @FXML
    private Button circleBtn3;
    @FXML
    private Button circleBtn4;
    @FXML
    private Button circleBtn5;
    @FXML
    private Button circleBtnCenter;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (tryParseInt(numberOfDataRowCount.getText())) {
            dataRowCount = Integer.parseInt(numberOfDataRowCount.getText());
            if (dataRowCount > 10000) {
                animationDiration = 2;
            } else {
                animationDiration = 1;
            }
        } else {
            dataRowCount = 5000;
            animationDiration = 1;
        }
        totalTrials = 0;
        movementTotalCount = 0;
        steadyTotalCount = 0;

        translateTransition1 = new TranslateTransition(Duration.seconds(animationDiration), circleBtn1);
        scaleTransition1 = new ScaleTransition(Duration.seconds(animationDiration), circleBtn1);
        rotateTransition1 = new RotateTransition(Duration.seconds(animationDiration), circleBtn1);
        translateTransition2 = new TranslateTransition(Duration.seconds(animationDiration), circleBtn2);
        scaleTransition2 = new ScaleTransition(Duration.seconds(animationDiration), circleBtn2);
        rotateTransition2 = new RotateTransition(Duration.seconds(animationDiration), circleBtn2);
        translateTransition3 = new TranslateTransition(Duration.seconds(animationDiration), circleBtn3);
        scaleTransition3 = new ScaleTransition(Duration.seconds(animationDiration), circleBtn3);
        rotateTransition3 = new RotateTransition(Duration.seconds(animationDiration), circleBtn3);
        translateTransition4 = new TranslateTransition(Duration.seconds(animationDiration), circleBtn4);
        scaleTransition4 = new ScaleTransition(Duration.seconds(animationDiration), circleBtn4);
        rotateTransition4 = new RotateTransition(Duration.seconds(animationDiration), circleBtn4);
        translateTransition5 = new TranslateTransition(Duration.seconds(animationDiration), circleBtn5);
        scaleTransition5 = new ScaleTransition(Duration.seconds(animationDiration), circleBtn5);
        rotateTransition5 = new RotateTransition(Duration.seconds(animationDiration), circleBtn5);
        scaleTransition6 = new ScaleTransition(Duration.seconds(animationDiration), circleBtnCenter);
        final EmotiveDataSaveAndClassify classifyObj = new EmotiveDataSaveAndClassify();
        classifyObj.start();
    }

    boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        String image = EmotivDataClassification.class.getResource("Tennis-Ball2.png").toExternalForm();
        circleBtn1.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-size:cover;"
                + "-fx-background-repeat: no-repeat;");
        circleBtn2.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-size:cover;"
                + "-fx-background-repeat: no-repeat;");
        circleBtn3.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-size:cover;"
                + "-fx-background-repeat: no-repeat;");
        circleBtn4.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-size:cover;"
                + "-fx-background-repeat: no-repeat;");
        circleBtn5.setStyle("-fx-background-image: url('" + image + "'); "
                + "-fx-background-position: center center; "
                + "-fx-background-size:cover;"
                + "-fx-background-repeat: no-repeat;");
        final Circle clip1 = new Circle(25, 25, 23);
        final Circle clip2 = new Circle(25, 25, 23);
        final Circle clip3 = new Circle(25, 25, 23);
        final Circle clip4 = new Circle(25, 25, 23);
        final Circle clip5 = new Circle(25, 25, 23);
        circleBtn1.setClip(clip1);
        circleBtn2.setClip(clip2);
        circleBtn3.setClip(clip3);
        circleBtn4.setClip(clip4);
        circleBtn5.setClip(clip5);

    }

    private void AnimationBall() {
        animationEnd = false;
        if (previousState == "HandMovement") {
            if (currentState == "Steady") {
                translateTransition1.setToX(0);
                translateTransition1.setToY(0);
                scaleTransition1.setToX(1);
                scaleTransition1.setToY(1);
                rotateTransition1.setFromAngle(180);
                rotateTransition1.setToAngle(315);

                translateTransition2.setToX(0);
                translateTransition2.setToY(0);
                scaleTransition2.setToX(1);
                scaleTransition2.setToY(1);
                rotateTransition2.setFromAngle(180);
                rotateTransition2.setToAngle(315);

                translateTransition3.setToX(0);
                translateTransition3.setToY(0);
                scaleTransition3.setToX(1);
                scaleTransition3.setToY(1);
                rotateTransition3.setFromAngle(180);
                rotateTransition3.setToAngle(315);

                translateTransition4.setToX(0);
                translateTransition4.setToY(0);
                scaleTransition4.setToX(1);
                scaleTransition4.setToY(1);
                rotateTransition4.setFromAngle(180);
                rotateTransition4.setToAngle(315);

                translateTransition5.setToX(0);
                translateTransition5.setToY(0);
                scaleTransition5.setToX(1);
                scaleTransition5.setToY(1);
                rotateTransition5.setFromAngle(180);
                rotateTransition5.setToAngle(315);

                scaleTransition6.setToX(1);
                scaleTransition6.setToY(1);

            } else {
                translateTransition1.setToX(144.626);
                translateTransition1.setToY(-83.5);
                scaleTransition1.setToX(1.5);
                scaleTransition1.setToY(1.5);
                rotateTransition1.setFromAngle(45);
                rotateTransition1.setToAngle(315);

                translateTransition2.setToX(-34.721);
                translateTransition2.setToY(-163.35);
                scaleTransition2.setToX(1.5);
                scaleTransition2.setToY(1.5);
                rotateTransition2.setFromAngle(45);
                rotateTransition2.setToAngle(315);

                translateTransition3.setToX(-166.085);
                translateTransition3.setToY(17.456);
                scaleTransition3.setToX(1.5);
                scaleTransition3.setToY(1.5);
                rotateTransition3.setFromAngle(45);
                rotateTransition3.setToAngle(315);

                translateTransition4.setToX(-67.925);
                translateTransition4.setToY(152.562);
                scaleTransition4.setToX(1.5);
                scaleTransition4.setToY(1.5);
                rotateTransition4.setFromAngle(45);
                rotateTransition4.setToAngle(315);

                translateTransition5.setToX(124.105);
                translateTransition5.setToY(111.745);
                scaleTransition5.setToX(1.5);
                scaleTransition5.setToY(1.5);
                rotateTransition5.setFromAngle(45);
                rotateTransition5.setToAngle(315);

                scaleTransition6.setToX(5.5);
                scaleTransition6.setToY(5.5);
            }

        } else {
            if (currentState == "HandMovement") {
                translateTransition1.setToX(144.626);
                translateTransition1.setToY(-83.5);
                scaleTransition1.setToX(1.5);
                scaleTransition1.setToY(1.5);
                rotateTransition1.setFromAngle(180);
                rotateTransition1.setToAngle(315);

                translateTransition2.setToX(-34.721);
                translateTransition2.setToY(-163.35);
                scaleTransition2.setToX(1.5);
                scaleTransition2.setToY(1.5);
                rotateTransition2.setFromAngle(180);
                rotateTransition2.setToAngle(315);

                translateTransition3.setToX(-166.085);
                translateTransition3.setToY(17.456);
                scaleTransition3.setToX(1.5);
                scaleTransition3.setToY(1.5);
                rotateTransition3.setFromAngle(180);
                rotateTransition3.setToAngle(315);

                translateTransition4.setToX(-67.925);
                translateTransition4.setToY(152.562);
                scaleTransition4.setToX(1.5);
                scaleTransition4.setToY(1.5);
                rotateTransition4.setFromAngle(180);
                rotateTransition4.setToAngle(315);

                translateTransition5.setToX(124.105);
                translateTransition5.setToY(111.745);
                scaleTransition5.setToX(1.5);
                scaleTransition5.setToY(1.5);
                rotateTransition5.setFromAngle(180);
                rotateTransition5.setToAngle(315);

                scaleTransition6.setToX(5.5);
                scaleTransition6.setToY(5.5);

            } else {
                translateTransition1.setToX(0);
                translateTransition1.setToY(0);
                scaleTransition1.setToX(1);
                scaleTransition1.setToY(1);
                rotateTransition1.setFromAngle(-45);
                rotateTransition1.setToAngle(-270);

                translateTransition2.setToX(0);
                translateTransition2.setToY(0);
                scaleTransition2.setToX(1);
                scaleTransition2.setToY(1);
                rotateTransition2.setFromAngle(-45);
                rotateTransition2.setToAngle(-270);

                translateTransition3.setToX(0);
                translateTransition3.setToY(0);
                scaleTransition3.setToX(1);
                scaleTransition3.setToY(1);
                rotateTransition3.setFromAngle(-45);
                rotateTransition3.setToAngle(-270);

                translateTransition4.setToX(0);
                translateTransition4.setToY(0);
                scaleTransition4.setToX(1);
                scaleTransition4.setToY(1);
                rotateTransition4.setFromAngle(-45);
                rotateTransition4.setToAngle(-270);

                translateTransition5.setToX(0);
                translateTransition5.setToY(0);
                scaleTransition5.setToX(1);
                scaleTransition5.setToY(1);
                rotateTransition5.setFromAngle(-45);
                rotateTransition5.setToAngle(-270);

                scaleTransition6.setToX(1);
                scaleTransition6.setToY(1);
            }
        }
        parallelTransition = new ParallelTransition(translateTransition1, translateTransition2, translateTransition3, translateTransition4, translateTransition5, scaleTransition1, scaleTransition2, scaleTransition3, scaleTransition4, scaleTransition5, scaleTransition6, rotateTransition1, rotateTransition2, rotateTransition3, rotateTransition4, rotateTransition5);
        parallelTransition.play();
        parallelTransition.setOnFinished((e) -> {
            previousState = currentState;
            animationEnd = true;
        });
    }

    public class EmotiveDataSaveAndClassify extends Thread {

        public EmotiveDataSaveAndClassify() {
            setDaemon(true);
        }

        @Override
        public void run() {
            while (true && animationEnd == true) {
                Pointer eEvent = Edk.INSTANCE.IEE_EmoEngineEventCreate();
                Pointer eState = Edk.INSTANCE.IEE_EmoStateCreate();
                int counter = 0;
                int rightHandCurrentCount = 0;
                int steadyCurrentCount = 0;

                IntByReference userID = null;
                boolean ready = false;
                int state = 0;
                Edk.IEE_DataChannels_t dataChannel;

                userID = new IntByReference(0);

                if (Edk.INSTANCE.IEE_EngineConnect("Emotiv Systems-5") != EdkErrorCode.EDK_OK
                        .ToInt()) {
                    System.out.println("Emotiv Engine start up failed.");
                    return;
                }

                System.out.println("Start receiving Data!");
                System.out.println("Theta, Alpha, Low_beta, High_beta, Gamma");

                while (true) {
                    state = Edk.INSTANCE.IEE_EngineGetNextEvent(eEvent);

                    // New event needs to be handled
                    if (state == EdkErrorCode.EDK_OK.ToInt()) {
                        int eventType = Edk.INSTANCE.IEE_EmoEngineEventGetType(eEvent);
                        Edk.INSTANCE.IEE_EmoEngineEventGetUserId(eEvent, userID);

                        // Log the EmoState if it has been updated
                        if (eventType == Edk.IEE_Event_t.IEE_UserAdded.ToInt()) {
                            if (userID != null) {
                                System.out.println("User added");
                                ready = true;
                            }
                        }
                    } else if (state != EdkErrorCode.EDK_NO_EVENT.ToInt()) {
                        System.out.println("Internal error in Emotiv Engine!");
                        break;
                    }

                    //if (true) {
                    if (ready) {

                        DoubleByReference alpha = new DoubleByReference(0);
                        DoubleByReference low_beta = new DoubleByReference(0);
                        DoubleByReference high_beta = new DoubleByReference(0);
                        DoubleByReference gamma = new DoubleByReference(0);
                        DoubleByReference theta = new DoubleByReference(0);

                        for (int i = 4; i < 16; i++) {

                            int result = Edk.INSTANCE.IEE_GetAverageBandPowers(userID.getValue(), i, theta, alpha, low_beta, high_beta, gamma);
                            // if (true) {//edited
                            if (result == EdkErrorCode.EDK_OK.ToInt()) {

                                /* System.out.print(theta.getValue());
                                       System.out.print(", ");
                                       System.out.print(alpha.getValue());
                                       System.out.print(", ");
                                       System.out.print(low_beta.getValue());
                                       System.out.print(", ");
                                       System.out.print(high_beta.getValue());
                                       System.out.print(", ");
                                       System.out.print(gamma.getValue());
                                       System.out.print(", ");*/
                            }
                            double Low_beta = low_beta.getValue();
                            double High_beta = high_beta.getValue();
                            double Theta = theta.getValue();
                            double Alpha = alpha.getValue();
                            double Gamma = gamma.getValue();

                            //if (animationEnd == true) 
                            if (Alpha != 0) {
                                //if (Alpha != 0 && animationEnd == true) {
                                /*int randomNumber = totalTrials % 3 + totalTrials % 2;
                                if (randomNumber == 1) {
                                    movementCurrentCount++;
                                } else {
                                    steadyCurrentCount++;
                                }*/

                                if (Gamma <= 0.604081) {
                                    if (Gamma <= 0.066597) {
                                        if (Gamma <= 0.023499) {
                                            if (Alpha <= 0.037432) {
                                                if (Alpha <= 0.037013) {
                                                    if (Theta <= 0.05345) {
                                                        if (Theta <= 0.044339) {
                                                            if (Gamma <= 0.019915) {
                                                                if (Gamma <= 0.0194) {
                                                                    if (Theta <= 0.026088) {
                                                                        rightHandCurrentCount++;
                                                                    } else if (Theta > 0.026088) {
                                                                        if (Gamma <= 0.015285) {
                                                                            steadyCurrentCount++;
                                                                        } else if (Gamma > 0.015285) {
                                                                            if (Theta <= 0.042352) {
                                                                                rightHandCurrentCount++;
                                                                            } else if (Theta > 0.042352) {
                                                                                steadyCurrentCount++;
                                                                            }
                                                                        }
                                                                    } else if (Gamma > 0.0194) {
                                                                        steadyCurrentCount++;
                                                                    }
                                                                } else if (Gamma > 0.019915) {
                                                                    rightHandCurrentCount++;
                                                                }
                                                            } else if (Theta > 0.044339) {
                                                                rightHandCurrentCount++;
                                                            }
                                                        } else if (Theta > 0.05345) {
                                                            steadyCurrentCount++;
                                                        }
                                                    } else if (Alpha > 0.037013) {
                                                        steadyCurrentCount++;
                                                    }
                                                } else if (Alpha > 0.037432) {
                                                    if (Theta <= 0.012762) {
                                                        if (Theta <= 0.009156) {
                                                            rightHandCurrentCount++;
                                                        } else if (Theta > 0.009156) {
                                                            steadyCurrentCount++;
                                                        }
                                                    } else if (Theta > 0.012762) {
                                                        rightHandCurrentCount++;
                                                    }
                                                }
                                            } else if (Gamma > 0.023499) {
                                                if (Alpha <= 0.051453) {
                                                    if (Low_beta <= 0.060763) {
                                                        if (Theta <= 0.088441) {
                                                            if (Alpha <= 0.026033) {
                                                                if (Alpha <= 0.026) {
                                                                    if (Alpha <= 0.011678) {
                                                                        if (Alpha <= 0.011437) {
                                                                            steadyCurrentCount++;
                                                                        } else if (Alpha > 0.011437) {
                                                                            rightHandCurrentCount++;
                                                                        }
                                                                    } else if (Alpha > 0.011678) {
                                                                        steadyCurrentCount++;
                                                                    }
                                                                } else if (Alpha > 0.026) {
                                                                    rightHandCurrentCount++;
                                                                }
                                                            } else if (Alpha > 0.026033) {
                                                                steadyCurrentCount++;
                                                            }
                                                        } else if (Theta > 0.088441) {
                                                            rightHandCurrentCount++;
                                                        }
                                                    } else if (Low_beta > 0.060763) {
                                                        rightHandCurrentCount++;
                                                    }
                                                } else if (Alpha > 0.051453) {
                                                    if (Theta <= 0.038796) {
                                                        steadyCurrentCount++;
                                                    } else if (Theta > 0.038796) {
                                                        rightHandCurrentCount++;
                                                    }

                                                }
                                            }
                                        } else if (Gamma > 0.066597) {
                                            steadyCurrentCount++;
                                        }
                                    } else if (Gamma > 0.604081) {
                                        rightHandCurrentCount++;
                                    }

                                    counter++;
                                
                                    //System.out.printf("Counter:%d", counter);

                                    if (counter == dataRowCount) {
                                        //System.out.printf("\n\n");
                                        if (steadyCurrentCount >= rightHandCurrentCount) {
                                            movementTotalCount++;
                                            currentState = "HandMovement";
                                        } else {
                                            steadyTotalCount++;
                                            currentState = "Steady";
                                        }

                                        totalTrials++;

                                        counter = 0;
                                        rightHandCurrentCount = 0;
                                        steadyCurrentCount = 0;
                                    
                                        /* System.out.printf("Current State: " + currentState + "\n");
                                    if (movementTotalCount > 0) {
                                        System.out.printf("Percentage of HandMovement: %.2f %%\n", ((float) movementTotalCount / totalTrials) * 100);
                                    } else {
                                        System.out.printf("Percentage of HandMovement: 0 %%\n");
                                    }
                                    if (steadyTotalCount > 0) {
                                        System.out.printf("Percentage of Steady: %.2f %%\n", ((float) steadyTotalCount / totalTrials) * 100);
                                    } else {
                                        System.out.printf("Percentage of Steady: 0 %%\n");
                                    }
                                    System.out.printf("Total trials: %d \n", totalTrials);
                                         */
 /*
                                   *** update ui
                                         */
                                        Platform.runLater(new Runnable() {
                                            @Override
                                            public void run() {

                                                currentStateLabelTxt.setText("" + currentState);
                                                if (movementTotalCount > 0) {
                                                    movementTotalCountLabelTxt.setText("" + Math.round(((float) movementTotalCount / totalTrials) * 10000) / 100.0 + " %");
                                                } else {
                                                    movementTotalCountLabelTxt.setText("0 %");
                                                }
                                                if (steadyTotalCount > 0) {
                                                    steadyTotalCountLabelTxt.setText("" + Math.round(((float) steadyTotalCount / totalTrials) * 10000) / 100.0 + " %");
                                                } else {
                                                    steadyTotalCountLabelTxt.setText("0 %");
                                                }
                                                totalTrialsLabelTxt.setText("" + totalTrials);

                                                //animation method call                
                                                AnimationBall();

                                            }
                                        });
                                    }
                                    // System.out.println();
                                }
                                // System.out.printf("Pointer:%d\n",i);
                                if (i == 5) {
                                    i = 13;
                                }
                            }
                        }
                    }
            }
                    Edk.INSTANCE.IEE_EngineDisconnect();
                    Edk.INSTANCE.IEE_EmoStateFree(eState);
                    Edk.INSTANCE.IEE_EmoEngineEventFree(eEvent);
                    System.out.println("Disconnected!");

                }
            }

        }
    }
