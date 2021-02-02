package emotivsavecsv;

import com.emotiv.Iedk.*;
import com.sun.jna.Pointer;
import com.sun.jna.ptr.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class AverageBandPowers {

    private static String className;

    public static void main(String[] args) throws FileNotFoundException {

        PrintWriter pwF3 = new PrintWriter(new File("omisteadyF3.arff"));//added this
        PrintWriter pwFC5 = new PrintWriter(new File("omisteadyFC5.arff"));
        PrintWriter pwFC6 = new PrintWriter(new File("omisteadyFC6.arff"));
        PrintWriter pwF4 = new PrintWriter(new File("omisteadyF4.arff"));
        className = "steady";
        //className = "lefthand";
        //className = "righthand";
        //className = "movement";

        Pointer eEvent = Edk.INSTANCE.IEE_EmoEngineEventCreate();
        Pointer eState = Edk.INSTANCE.IEE_EmoStateCreate();

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
        //added this
        pwF3.write("@relation relationName.attribute.NumericToNominal-weka.filters.unsupervised.attribute.NumericToNominal\n\n");
        pwF3.write("@attribute Theta numeric\n");
        pwF3.write("@attribute ' Alpha' numeric\n");
        pwF3.write("@attribute ' Low_beta' numeric\n");
        pwF3.write("@attribute ' High_beta' numeric\n");
        pwF3.write("@attribute ' Gamma' numeric\n");
        pwF3.write("@attribute Class {lefthand,righthand,steady}\n\n");
        pwF3.write("@data\n");
      
         //added this
        pwFC5.write("@relation relationName.attribute.NumericToNominal-weka.filters.unsupervised.attribute.NumericToNominal\n\n");
        pwFC5.write("@attribute Theta numeric\n");
        pwFC5.write("@attribute ' Alpha' numeric\n");
        pwFC5.write("@attribute ' Low_beta' numeric\n");
        pwFC5.write("@attribute ' High_beta' numeric\n");
        pwFC5.write("@attribute ' Gamma' numeric\n");
        pwFC5.write("@attribute Class {lefthand,righthand,steady}\n\n");
        pwFC5.write("@data\n");
        
          //added this
        pwFC6.write("@relation relationName.attribute.NumericToNominal-weka.filters.unsupervised.attribute.NumericToNominal\n\n");
        pwFC6.write("@attribute Theta numeric\n");
        pwFC6.write("@attribute ' Alpha' numeric\n");
        pwFC6.write("@attribute ' Low_beta' numeric\n");
        pwFC6.write("@attribute ' High_beta' numeric\n");
        pwFC6.write("@attribute ' Gamma' numeric\n");
        pwFC6.write("@attribute Class {lefthand,righthand,steady}\n\n");
        pwFC6.write("@data\n");
        
           //added this
        pwF4.write("@relation relationName.attribute.NumericToNominal-weka.filters.unsupervised.attribute.NumericToNominal\n\n");
        pwF4.write("@attribute Theta numeric\n");
        pwF4.write("@attribute ' Alpha' numeric\n");
        pwF4.write("@attribute ' Low_beta' numeric\n");
        pwF4.write("@attribute ' High_beta' numeric\n");
        pwF4.write("@attribute ' Gamma' numeric\n");
        pwF4.write("@attribute Class {lefthand,righthand,steady}\n\n");
        pwF4.write("@data\n");
        
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

               // for (int i = 3; i < 17; i++) {
               //F3/5
                    int result = Edk.INSTANCE.IEE_GetAverageBandPowers(userID.getValue(), 5, theta, alpha, low_beta, high_beta, gamma);
                    //if (true) {//edited
                         if(result == EdkErrorCode.EDK_OK.ToInt()){

                        System.out.print(theta.getValue());
                        System.out.print(", ");
                        System.out.print(alpha.getValue());
                        System.out.print(", ");
                        System.out.print(low_beta.getValue());
                        System.out.print(", ");
                        System.out.print(high_beta.getValue());
                        System.out.print(", ");
                        System.out.print(gamma.getValue());
                        System.out.print(", ");

                        //added this
                        pwF3.write(String.valueOf(theta.getValue()));
                        pwF3.write(",");
                        pwF3.write(String.valueOf(alpha.getValue()));
                        pwF3.write(",");
                        pwF3.write(String.valueOf(low_beta.getValue()));
                        pwF3.write(",");
                        pwF3.write(String.valueOf(high_beta.getValue()));
                        pwF3.write(",");
                        pwF3.write(String.valueOf(gamma.getValue()));
                        pwF3.write(",");
                        pwF3.write(className);

                    }

                    System.out.println();
                    //added this
                    if(theta.getValue()!=0) 
                        {
                   // if (true) {
                        pwF3.write("\n");
                    }//edited
                    //*************
                    //FC5/6
                     result = Edk.INSTANCE.IEE_GetAverageBandPowers(userID.getValue(), 6, theta, alpha, low_beta, high_beta, gamma);
                    //if (true) {//edited
                         if(result == EdkErrorCode.EDK_OK.ToInt()){

                        System.out.print(theta.getValue());
                        System.out.print(", ");
                        System.out.print(alpha.getValue());
                        System.out.print(", ");
                        System.out.print(low_beta.getValue());
                        System.out.print(", ");
                        System.out.print(high_beta.getValue());
                        System.out.print(", ");
                        System.out.print(gamma.getValue());
                        System.out.print(", ");

                        //added this
                        pwFC5.write(String.valueOf(theta.getValue()));
                        pwFC5.write(",");
                        pwFC5.write(String.valueOf(alpha.getValue()));
                        pwFC5.write(",");
                        pwFC5.write(String.valueOf(low_beta.getValue()));
                        pwFC5.write(",");
                        pwFC5.write(String.valueOf(high_beta.getValue()));
                        pwFC5.write(",");
                        pwFC5.write(String.valueOf(gamma.getValue()));
                        pwFC5.write(",");
                        pwFC5.write(className);

                    }

                    System.out.println();
                    //added this
                    if(theta.getValue()!=0) 
                        {
                   // if (true) {
                        pwFC5.write("\n");
                    }//edited
                    //****************
                    //FC6/13
                     result = Edk.INSTANCE.IEE_GetAverageBandPowers(userID.getValue(), 13, theta, alpha, low_beta, high_beta, gamma);
                    //if (true) {//edited
                         if(result == EdkErrorCode.EDK_OK.ToInt()){

                        System.out.print(theta.getValue());
                        System.out.print(", ");
                        System.out.print(alpha.getValue());
                        System.out.print(", ");
                        System.out.print(low_beta.getValue());
                        System.out.print(", ");
                        System.out.print(high_beta.getValue());
                        System.out.print(", ");
                        System.out.print(gamma.getValue());
                        System.out.print(", ");

                        //added this
                        pwFC6.write(String.valueOf(theta.getValue()));
                        pwFC6.write(",");
                        pwFC6.write(String.valueOf(alpha.getValue()));
                        pwFC6.write(",");
                        pwFC6.write(String.valueOf(low_beta.getValue()));
                        pwFC6.write(",");
                        pwFC6.write(String.valueOf(high_beta.getValue()));
                        pwFC6.write(",");
                        pwFC6.write(String.valueOf(gamma.getValue()));
                        pwFC6.write(",");
                        pwFC6.write(className);

                    }

                    System.out.println();
                    //added this
                    if(theta.getValue()!=0) 
                        {
                   // if (true) {
                        pwFC6.write("\n");
                    }//edited
                    //*******************
                    //F4/14
                     result = Edk.INSTANCE.IEE_GetAverageBandPowers(userID.getValue(), 14, theta, alpha, low_beta, high_beta, gamma);
                    //if (true) {//edited
                         if(result == EdkErrorCode.EDK_OK.ToInt()){

                        System.out.print(theta.getValue());
                        System.out.print(", ");
                        System.out.print(alpha.getValue());
                        System.out.print(", ");
                        System.out.print(low_beta.getValue());
                        System.out.print(", ");
                        System.out.print(high_beta.getValue());
                        System.out.print(", ");
                        System.out.print(gamma.getValue());
                        System.out.print(", ");

                        //added this
                        pwF4.write(String.valueOf(theta.getValue()));
                        pwF4.write(",");
                        pwF4.write(String.valueOf(alpha.getValue()));
                        pwF4.write(",");
                        pwF4.write(String.valueOf(low_beta.getValue()));
                        pwF4.write(",");
                        pwF4.write(String.valueOf(high_beta.getValue()));
                        pwF4.write(",");
                        pwF4.write(String.valueOf(gamma.getValue()));
                        pwF4.write(",");
                        pwF4.write(className);

                    }

                    System.out.println();
                    //added this
                    if(theta.getValue()!=0) 
                        {
                   // if (true) {
                        pwF4.write("\n");
                    }//edited
                //}
            }
        }

        Edk.INSTANCE.IEE_EngineDisconnect();
        Edk.INSTANCE.IEE_EmoStateFree(eState);
        Edk.INSTANCE.IEE_EmoEngineEventFree(eEvent);
        System.out.println("Disconnected!");
        //added this
        pwF3.close();
        pwFC5.close();
        pwFC6.close();
        pwF4.close();
    }
}
