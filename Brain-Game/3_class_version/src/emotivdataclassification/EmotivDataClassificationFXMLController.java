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
    static int leftHandTotalCount;
    static int rightHandTotalCount;
    static int steadyTotalCount;
    static String previousState = "Steady";
    static String currentState;
    private int dataRowCount = 1;

    TranslateTransition translateTransition, translateTransition2, translateTransition3, translateTransition4, translateTransition5;
    ScaleTransition scaleTransition, scaleTransition2, scaleTransition3, scaleTransition4, scaleTransition5;
    RotateTransition rotateTransition, rotateTransition2, rotateTransition3, rotateTransition4, rotateTransition5;
    ParallelTransition parallelTransition;
    static double animationDiration;
    Boolean animationEnd = true;

    @FXML
    private Label totalTrialsLabelTxt;
    @FXML
    private Label leftHandTotalCountLabelTxt;
    @FXML
    private Label rightHandTotalCountLabelTxt;
    @FXML
    private Label steadyTotalCountLabelTxt;
    @FXML
    private Label currentStateLabelTxt;
    @FXML
    private TextField numberOfDataRowCount;
    @FXML
    private Button circleBtn;
    @FXML
    private Button circleBtn2;
    @FXML
    private Button circleBtn3;
    @FXML
    private Button circleBtn4;
    @FXML
    private Button circleBtn5;

    @FXML
    private void handleButtonAction(ActionEvent event) {

        if (tryParseInt(numberOfDataRowCount.getText())) {
            dataRowCount = Integer.parseInt(numberOfDataRowCount.getText());
            if (dataRowCount > 10000) {
                animationDiration = .2;
            } else {
                animationDiration = .2;
            }
        } else {
            dataRowCount = 5000;
            animationDiration = .2;
        }
        totalTrials = 0;
        leftHandTotalCount = 0;
        rightHandTotalCount = 0;
        steadyTotalCount = 0;

        translateTransition = new TranslateTransition(Duration.seconds(animationDiration), circleBtn);
        scaleTransition = new ScaleTransition(Duration.seconds(animationDiration), circleBtn);
        rotateTransition = new RotateTransition(Duration.seconds(animationDiration), circleBtn);
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
        circleBtn.setStyle("-fx-background-image: url('" + image + "'); "
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
        final Circle clip = new Circle(25, 25, 23);
        final Circle clip2 = new Circle(25, 25, 23);
        final Circle clip3 = new Circle(25, 25, 23);
        final Circle clip4 = new Circle(25, 25, 23);
        final Circle clip5 = new Circle(25, 25, 23);
        circleBtn.setClip(clip);
        circleBtn2.setClip(clip2);
        circleBtn3.setClip(clip3);
        circleBtn4.setClip(clip4);
        circleBtn5.setClip(clip5);

    }

    private void AnimationBall() {
        animationEnd = false;
        if (previousState == "Left Hand") {
            if (currentState == "Right Hand") {
                translateTransition.setToX(100);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                rotateTransition.setFromAngle(180);
                rotateTransition.setToAngle(315);
                
                translateTransition2.setToX(120);
                translateTransition2.setToY(-35);
                scaleTransition2.setToX(1);
                scaleTransition2.setToY(1);
                rotateTransition2.setFromAngle(180);
                rotateTransition2.setToAngle(315);
                
                translateTransition3.setToX(163);
                translateTransition3.setToY(-70);
                scaleTransition3.setToX(1);
                scaleTransition3.setToY(1);
                rotateTransition3.setFromAngle(180);
                rotateTransition3.setToAngle(315);
                
                translateTransition4.setToX(120);
                translateTransition4.setToY(35);
                scaleTransition4.setToX(1);
                scaleTransition4.setToY(1);
                rotateTransition4.setFromAngle(180);
                rotateTransition4.setToAngle(315);
                
                translateTransition5.setToX(163);
                translateTransition5.setToY(70);
                scaleTransition5.setToX(1);
                scaleTransition5.setToY(1);
                rotateTransition5.setFromAngle(180);
                rotateTransition5.setToAngle(315);

            } else if (currentState == "Left Hand") {
                translateTransition.setToX(-100);
                scaleTransition.setToX(1.5);
                scaleTransition.setToY(1.5);
                rotateTransition.setFromAngle(-45);
                rotateTransition.setToAngle(-270);
                
                translateTransition2.setToX(-120);
                translateTransition2.setToY(-35);
                scaleTransition2.setToX(1.5);
                scaleTransition2.setToY(1.5);
                rotateTransition2.setFromAngle(-45);
                rotateTransition2.setToAngle(-270);
                
                translateTransition3.setToX(-163);
                translateTransition3.setToY(-70);
                scaleTransition3.setToX(1.5);
                scaleTransition3.setToY(1.5);
                rotateTransition3.setFromAngle(-45);
                rotateTransition3.setToAngle(-270);
                
                translateTransition4.setToX(-120);
                translateTransition4.setToY(35);
                scaleTransition4.setToX(1.5);
                scaleTransition4.setToY(1.5);
                rotateTransition4.setFromAngle(-45);
                rotateTransition4.setToAngle(-270);
                
                translateTransition5.setToX(-163);
                translateTransition5.setToY(70);
                scaleTransition5.setToX(1.5);
                scaleTransition5.setToY(1.5);
                rotateTransition5.setFromAngle(-45);
                rotateTransition5.setToAngle(-270);

            } else {
                translateTransition.setToX(0);
                translateTransition3.setToY(0);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                rotateTransition.setFromAngle(45);
                rotateTransition.setToAngle(315);
                
                translateTransition2.setToX(0);
                translateTransition2.setToY(0);
                scaleTransition2.setToX(1);
                scaleTransition2.setToY(1);
                rotateTransition2.setFromAngle(45);
                rotateTransition2.setToAngle(315);
                
                translateTransition3.setToX(0);
                scaleTransition3.setToX(1);
                scaleTransition3.setToY(1);
                rotateTransition3.setFromAngle(45);
                rotateTransition3.setToAngle(315);
                
                translateTransition4.setToX(0);
                translateTransition4.setToY(0);
                scaleTransition4.setToX(1);
                scaleTransition4.setToY(1);
                rotateTransition4.setFromAngle(45);
                rotateTransition4.setToAngle(315);
                
                translateTransition5.setToX(0);
                translateTransition5.setToY(0);
                scaleTransition5.setToX(1);
                scaleTransition5.setToY(1);
                rotateTransition5.setFromAngle(45);
                rotateTransition5.setToAngle(315);
            }

        } else if (previousState == "Right Hand") {
            if (currentState == "Left Hand") {
                translateTransition.setToX(-100);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                rotateTransition.setFromAngle(180);
                rotateTransition.setToAngle(315);
                
                translateTransition2.setToX(-120);
                translateTransition2.setToY(-35);
                scaleTransition2.setToX(1);
                scaleTransition2.setToY(1);
                rotateTransition2.setFromAngle(180);
                rotateTransition2.setToAngle(315);
                
                translateTransition3.setToX(-163);
                translateTransition3.setToY(-70);
                scaleTransition3.setToX(1);
                scaleTransition3.setToY(1);
                rotateTransition3.setFromAngle(180);
                rotateTransition3.setToAngle(315);
                
                translateTransition4.setToX(-120);
                translateTransition4.setToY(35);
                scaleTransition4.setToX(1);
                scaleTransition4.setToY(1);
                rotateTransition4.setFromAngle(180);
                rotateTransition4.setToAngle(315);
                
                translateTransition5.setToX(-163);
                translateTransition5.setToY(70);
                scaleTransition5.setToX(1);
                scaleTransition5.setToY(1);
                rotateTransition5.setFromAngle(180);
                rotateTransition5.setToAngle(315);

            } else if (currentState == "Steady") {
                translateTransition.setToX(0);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                rotateTransition.setFromAngle(45);
                rotateTransition.setToAngle(315);
                
                translateTransition2.setToX(0);
                translateTransition2.setToY(0);
                scaleTransition2.setToX(1);
                scaleTransition2.setToY(1);
                rotateTransition2.setFromAngle(45);
                rotateTransition2.setToAngle(315);
                
                translateTransition3.setToX(0);
                translateTransition3.setToY(0);
                scaleTransition3.setToX(1);
                scaleTransition3.setToY(1);
                rotateTransition3.setFromAngle(45);
                rotateTransition3.setToAngle(315);
                
                translateTransition4.setToX(0);
                translateTransition4.setToY(0);
                scaleTransition4.setToX(1);
                scaleTransition4.setToY(1);
                rotateTransition4.setFromAngle(45);
                rotateTransition4.setToAngle(315);
                
                translateTransition5.setToX(0);
                translateTransition5.setToY(0);
                scaleTransition5.setToX(1);
                scaleTransition5.setToY(1);
                rotateTransition5.setFromAngle(45);
                rotateTransition5.setToAngle(315);
            } else {
                translateTransition.setToX(100);
                scaleTransition.setToX(1.5);
                scaleTransition.setToY(1.5);
                rotateTransition.setFromAngle(-45);
                rotateTransition.setToAngle(-270);
                
                translateTransition2.setToX(120);
                translateTransition2.setToY(-35);
                scaleTransition2.setToX(1.5);
                scaleTransition2.setToY(1.5);
                rotateTransition2.setFromAngle(-45);
                rotateTransition2.setToAngle(-270);
                
                translateTransition3.setToX(163);
                translateTransition3.setToY(-70);
                scaleTransition3.setToX(1.5);
                scaleTransition3.setToY(1.5);
                rotateTransition3.setFromAngle(-45);
                rotateTransition3.setToAngle(-270);
                
                translateTransition4.setToX(120);
                translateTransition4.setToY(35);
                scaleTransition4.setToX(1.5);
                scaleTransition4.setToY(1.5);
                rotateTransition4.setFromAngle(-45);
                rotateTransition4.setToAngle(-270);
                
                translateTransition5.setToX(163);
                translateTransition5.setToY(70);
                scaleTransition5.setToX(1.5);
                scaleTransition5.setToY(1.5);
                rotateTransition5.setFromAngle(-45);
                rotateTransition5.setToAngle(-270);
            }
        } else {

            if (currentState == "Left Hand") {
                translateTransition.setToX(-100);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                rotateTransition.setFromAngle(180);
                rotateTransition.setToAngle(315);
                
                translateTransition2.setToX(-120);
                translateTransition2.setToY(-35);
                scaleTransition2.setToX(1);
                scaleTransition2.setToY(1);
                rotateTransition2.setFromAngle(180);
                rotateTransition2.setToAngle(315);
                
                translateTransition3.setToX(-163);
                translateTransition3.setToY(-70);
                scaleTransition3.setToX(1);
                scaleTransition3.setToY(1);
                rotateTransition3.setFromAngle(180);
                rotateTransition3.setToAngle(315);
                
                translateTransition4.setToX(-120);
                translateTransition4.setToY(35);
                scaleTransition4.setToX(1);
                scaleTransition4.setToY(1);
                rotateTransition4.setFromAngle(180);
                rotateTransition4.setToAngle(315);
                
                translateTransition5.setToX(-163);
                translateTransition5.setToY(70);
                scaleTransition5.setToX(1);
                scaleTransition5.setToY(1);
                rotateTransition5.setFromAngle(180);
                rotateTransition5.setToAngle(315);

            } else if (currentState == "Right Hand") {
                translateTransition.setToX(100);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                rotateTransition.setFromAngle(45);
                rotateTransition.setToAngle(315);
                
                translateTransition2.setToX(120);
                translateTransition2.setToY(-35);
                scaleTransition2.setToX(1);
                scaleTransition2.setToY(1);
                rotateTransition2.setFromAngle(45);
                rotateTransition2.setToAngle(315);
                
                translateTransition3.setToX(163);
                translateTransition3.setToY(-70);
                scaleTransition3.setToX(1);
                scaleTransition3.setToY(1);
                rotateTransition3.setFromAngle(45);
                rotateTransition3.setToAngle(315);
                
                translateTransition4.setToX(120);
                translateTransition4.setToY(35);
                scaleTransition4.setToX(1);
                scaleTransition4.setToY(1);
                rotateTransition4.setFromAngle(45);
                rotateTransition4.setToAngle(315);
                
                translateTransition5.setToX(163);
                translateTransition5.setToY(70);
                scaleTransition5.setToX(1);
                scaleTransition5.setToY(1);
                rotateTransition5.setFromAngle(45);
                rotateTransition5.setToAngle(315);

            } else {
                translateTransition.setToX(0);
                scaleTransition.setToX(1);
                scaleTransition.setToY(1);
                rotateTransition.setFromAngle(-45);
                rotateTransition.setToAngle(-270);
                
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

            }

        }
        parallelTransition = new ParallelTransition(translateTransition, translateTransition2, translateTransition3, translateTransition4, translateTransition5, scaleTransition, scaleTransition2, scaleTransition3, scaleTransition4, scaleTransition5, rotateTransition, rotateTransition2, rotateTransition3, rotateTransition4, rotateTransition5);
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
                int leftHandCurrentCount = 0;
                int rightHandCurrentCount = 0;
                int leftFootCurrentCount = 0;
                int rightFootCurrentCount = 0;
                int steadyCurrentCount = 0;
                int[] counterArray = new int[4];

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

                    if (true) {
                   // if (ready) {

                        DoubleByReference alpha = new DoubleByReference(0);
                        DoubleByReference low_beta = new DoubleByReference(0);
                        DoubleByReference high_beta = new DoubleByReference(0);
                        DoubleByReference gamma = new DoubleByReference(0);
                        DoubleByReference theta = new DoubleByReference(0);

                        for (int i = 4; i < 16; i++) {

                            int result = Edk.INSTANCE.IEE_GetAverageBandPowers(userID.getValue(), i, theta, alpha, low_beta, high_beta, gamma);
                            if (true) {//edited
                           // if (result == EdkErrorCode.EDK_OK.ToInt()) {

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
                            double gammaValue = gamma.getValue();
                           // if (gammaValue != 0 && animationEnd == true) 
                             if (animationEnd == true) 
                            {
                                int randomNumber = totalTrials % 3 + totalTrials %2;
                                if (randomNumber == 1) {
                                    leftHandCurrentCount++;
                                } else if (randomNumber == 2) {
                                    rightHandCurrentCount++;
                                } else if (randomNumber == 3) {
                                    steadyCurrentCount++;
                                }

                               /* if (gammaValue < 0.005072252186533857) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.006802435776292941) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.007344306109092959) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.0078041735816908875) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.008526992578365651) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.009021998091728513) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.009520290673810087) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.010670782884125951) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.010812544879293156) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.011040449076516688) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.011147504315911534) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.011155805801253376) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.01165783798335064) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.011750274765603055) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.011991878588902521) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.012150917392372484) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.012739205530378113) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.013320286448680468) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.01386473876277632) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.014286295047601006) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.014958639705651696) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.015153917681799963) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.015402596002847931) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.015936899460652493) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.01620770094399511) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.016523001660879298) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.01696044086602634) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.01699304982145982) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.017933169092696676) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.01817769707796915) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.01885952205380844) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.018944417556311974) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.019039589546881154) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.019612633620094598) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.02071079037965929) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.020823398700207045) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.021234442102609233) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.021268840442077885) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.021285075906355123) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.021340882637984337) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.02162444956634521) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.021786308247955313) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.021954436427623562) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.02208239852896844) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.022310976612749826) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.022685303382698682) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.022900693067599596) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.023348659262292498) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.024125217910867837) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.024768287788538616) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.025351807385968067) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.025655822762809935) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.026191463318499253) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.026791605072725294) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.027179074962124697) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.027476623615907324) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.028000412347286008) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.029005093655770604) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.029479309205240563) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.029737780599615918) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.030113670801496743) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.030970698297651773) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.0316069039976392) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.0328136452998908) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.035544500950707866) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.03579102175854096) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.036049090396196456) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.03638206594674854) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.03765521692708187) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.04029280602510216) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.04309561184197798) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.05004540527398328) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.059068932996450045) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.06320789165269472) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.06474124007617726) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.0676555998869634) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.07116963177846206) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.07292500095788053) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.07622300590880797) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.07710282602956617) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.07795957019168208) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.07908837506278416) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.07995702312978004) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.0814282847114639) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.08195978129664062) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.08969383346951286) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.09145665957918239) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.09262763018036556) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.09348325773255754) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.09632577804379139) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.09786847059706164) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.0997939467162787) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.10182148510482092) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.1050544544422882) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.10896508531765131) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.11056277856268248) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.11203891008740897) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.11403904673348325) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.12031270333032784) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.12183631929758607) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.12354339779135315) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.13248972576382606) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.13439838778141003) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.13842375525552952) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.1411569328995308) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.1428119238468402) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.1476058286936982) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.15245517258583813) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.153688301400205) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.15487761980017684) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.15526332341424184) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.15554346705312533) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.15619691463725266) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.16037518224627173) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.16492719889781604) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.16531235358556554) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.16585942265961107) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.16658901979243687) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.17242839172570126) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.17342922204078579) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.1926432165961567) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.19314127554962923) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.19333704698737247) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.1935910242224641) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.1979057260933137) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.20056120857864018) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.20255083779270244) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.20348271768520726) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.20434669723516816) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.20537698849564384) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.20942762004717644) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.2102683237822714) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.2149302693766609) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.2188419625538675) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.21980934268743327) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.2279683430285896) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.23020702607641552) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.23107947686262398) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.23133867226986451) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.23357237264134856) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.23389070456348046) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.23627723200483147) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.239736613207093) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.24197057020228402) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.25079545217572297) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.2515672216302177) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.25310032450990533) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.25424634699584786) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.25478202781879566) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.26193253615737766) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.26313080236562686) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.26564125930257265) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.26740400928327) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.2803646016663129) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.2816060411107062) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.2862086044085886) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.29014434714664317) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.3206160835344246) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.3325371507418124) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.33734599857778214) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.3380892693783284) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.34234136783155383) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.34783889021798964) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.35291842300940945) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.3542617162991796) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.37656662588952816) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.3813789202247845) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.38220150143095466) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.3868068370993887) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.3880642630301411) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.3919765516852619) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.3932029396358304) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.3956345236914768) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.39893310760491263) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.40051173716669725) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.4022035296197242) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.40301452909545205) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.40660943731096155) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.4128803705629316) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.4295088384686942) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.43235685485623576) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.4340517429495843) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.4352361859246362) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.43580942289326197) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.4362233338493132) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.43718455585445326) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.43892798958965085) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.44029878184360727) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.44094882052974493) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.44439768299793553) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.4614349145834983) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.4630126734242713) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.46394317616528985) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.4659514756601044) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.4690003843918087) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.47027362922076316) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.4765875330524011) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.47857964308470613) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.4831127726949642) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.4858953451879233) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.4969484461234161) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.4999692708707191) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.5008163339537937) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.5154167941258672) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.5201470607725998) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.5232520999501722) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.524993451557382) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.5262421306694747) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.5328627559125625) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.534986188724327) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.5397372209509468) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.5430614238726027) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.5470157661782813) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.5523586454498397) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.5605223142527574) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.5700247409908956) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.5776031896681442) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.5810737527766048) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.5900862744014992) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.600077422726397) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.6067623568259258) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.6090318773951866) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.6216809311899626) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.6311837170194461) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.6421437104310829) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.6514153323805898) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.6549115974640733) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.6663751319310511) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.6724174722979607) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.6778124111821147) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.6821902619805593) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.6847688836588286) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.7116152570023504) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.7236463568858219) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.7281569583816038) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.779961252359161) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.7818680402145304) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.7899225733346353) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.7971831782046896) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.8070272849893303) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.8400382135348339) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.8475190665103266) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.8803820539571087) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.8876175354726429) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.8937337746685313) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 0.9106365491972208) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 0.9278030114571767) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 0.9503016835473239) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.0284064837544564) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.0360760886862246) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.0383979069954625) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.046048168081987) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.0505738182651299) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.0519715424919116) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.0558507928156722) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.062144739564013) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.0658363372987623) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.0678881034596777) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.0692079969780053) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.0795968788264139) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.0846926424458856) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.1007608683493753) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.1071168948938621) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.1130322085211835) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.1204281844249349) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.1390595427267607) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.1547152653287238) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.1576114006828218) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.161296509532367) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.1623661527304425) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.1675927849350396) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.1731163822431006) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.18449215775244) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.200744961637419) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.2013199537854193) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.2029240996255117) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.204693026682032) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.2083453970113645) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.2137996040635781) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.221623886081066) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.227601742673396) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.2329670265145234) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.236588290927459) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.2371313895176437) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.2385479941902178) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.2407156017141259) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.2421467206661294) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.2435694742067391) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.2464232924924552) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.2513639383081956) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.2564772807171485) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.2609192821528517) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.2728709933185645) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.2886317594580186) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.297829557283559) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.3045735010669484) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.3069269989027545) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.3119969256902722) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.3145642425428643) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.3266780960712503) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.3377991655230916) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.3408638079267385) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.3524682414634759) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.362259956338645) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.3796663876317277) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.4012392235778242) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.4136951266241895) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.4184684231133087) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.4198843566442236) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.4238171317650958) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.4339883568530558) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.4550421669821247) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.4709071824640771) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.4791114626173414) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.4845591206084994) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.493285939327673) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.4990160216773338) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.5005766895537296) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.5359606520378097) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.5394225213873811) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.5515105568990983) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.5641899711604128) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.572204847499504) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.5906090509792925) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.5983027745652731) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.6050214408751633) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.6146257813625664) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.622211155330242) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.6390029440627631) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.6512020680013235) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.6629621528683884) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.6781861424840656) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.6841910267876945) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.70857396082278) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.7579662284564637) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.7814669769396385) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.7948975754859333) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.8129449658071457) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.8292378180834432) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.8352173988016087) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.8384496374373174) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.849226662578412) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.8577898970561806) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.8771379626831408) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.921183250260209) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 1.9507893506248652) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 1.9570393055768123) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 1.981145455050519) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.002895663580559) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.015227419116271) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.024128548594833) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.083508240416153) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.1200981204487177) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.1265640220435924) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 2.1516695748807324) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.1687369410061725) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.2230260965459627) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.2902409540148505) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.406263957983401) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.4294700466392865) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.531988237302973) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.608912434711395) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.6442516544495653) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 2.6526302859593764) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.6802308183354473) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.7334423663038727) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.7880437799360944) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.887803499515223) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.909292771304707) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 2.9488946895530876) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 2.9932128599931302) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 3.0262578169168144) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 3.0537970756868957) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 3.0949419657826707) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 3.1500692372513983) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 3.386840710320314) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 3.4038682337470023) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 3.413071671472947) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 3.4615248166811847) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 3.6087808853025827) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 3.6442932505440147) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 3.746732878263457) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 3.7593530760893086) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 4.249234417484567) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 4.259044375636631) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 4.283790772024654) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 4.326160058258664) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 6.929004408505042) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 7.394212053007484) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 8.71342459835991) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 9.075782301814105) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 9.29803679775884) {
                                    leftHandCurrentCount++;
                                } else if (gammaValue < 9.729355964981744) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 10.30347008950128) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 16.961935216267822) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue < 18.166027467870766) {
                                    steadyCurrentCount++;
                                } else if (gammaValue < 19.171423570518286) {
                                    rightHandCurrentCount++;
                                } else if (gammaValue >= 19.171423570518286) {
                                    steadyCurrentCount++;
                                }
                                */
                                counter++;
                                //System.out.printf("Counter:%d", counter);

                               if (counter == dataRowCount) {
                                    //System.out.printf("\n\n");

                                    counterArray[0] = leftHandCurrentCount;
                                    counterArray[1] = rightHandCurrentCount;
                                    int maxCount = steadyCurrentCount;
                                    int maxCountIndex = 2;
                                    for (int j = 0; j < 2; j++) {
                                        if (maxCount < counterArray[j]) {
                                            maxCountIndex = j;
                                        }
                                    }
                                    if (maxCountIndex == 0) {
                                        leftHandTotalCount++;
                                        currentState = "Left Hand";
                                    } else if (maxCountIndex == 1) {
                                        rightHandTotalCount++;
                                        currentState = "Right Hand";
                                    } else {
                                        steadyTotalCount++;
                                        currentState = "Steady";
                                    }
                                    totalTrials++;

                                    counter = 0;
                                    leftHandCurrentCount = 0;
                                    rightHandCurrentCount = 0;
                                    steadyCurrentCount = 0;

                                   /* System.out.printf("Current State: " + currentState + "\n");
                                    if (leftHandTotalCount > 0) {
                                        System.out.printf("Percentage of Left Hand: %.2f %%\n", ((float) leftHandTotalCount / totalTrials) * 100);
                                    } else {
                                        System.out.printf("Percentage of Left Hand: 0 %%\n");
                                    }
                                    if (rightHandTotalCount > 0) {
                                        System.out.printf("Percentage of Right Hand: %.2f %%\n", ((float) rightHandTotalCount / totalTrials) * 100);
                                    } else {
                                        System.out.printf("Percentage of Right Hand: 0%%\n");
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
                                            if (leftHandTotalCount > 0) {
                                                leftHandTotalCountLabelTxt.setText("" + Math.round(((float) leftHandTotalCount / totalTrials) * 10000) / 100.0 + " %");
                                            } else {
                                                leftHandTotalCountLabelTxt.setText("0 %");
                                            }
                                            if (rightHandTotalCount > 0) {
                                                rightHandTotalCountLabelTxt.setText("" + Math.round(((float) rightHandTotalCount / totalTrials) * 10000) / 100.0 + " %");
                                            } else {
                                                rightHandTotalCountLabelTxt.setText("0 %");
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

                Edk.INSTANCE.IEE_EngineDisconnect();
                Edk.INSTANCE.IEE_EmoStateFree(eState);
                Edk.INSTANCE.IEE_EmoEngineEventFree(eEvent);
                System.out.println("Disconnected!");

            }
        }

    }
}

