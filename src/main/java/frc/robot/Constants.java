/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide numerical or boolean
 * constants.  This class should not be used for any other purpose.  All constants should be
 * declared globally (i.e. public static).  Do not put anything functional in this class.
 *
 * <p>It is advised to statically import this class (or one of its inner classes) wherever the
 * constants are needed, to reduce verbosity.
 */
public final class Constants {
    public static class Motor{
        public static final int kTimesOut = 10;  
        public static final double towerConst = 0.010;
        public static final double rackerKP = 0.40;
        public static final double rackerKI = 0.001;
        public static final int rackerIZone = 100;
       
    }
    public static class PowerPort{
        public static final int portHigh = 250;//cm
        public static final int robotHigh = 55+5;//cm 
    }

    public static class MotorPort{
        public static final int tower = 36;
        public static final int racker = 25;
        public static final int flywheelLeft = 17;
        public static final int flywheelRight = 15;
        public static final int wing = 1;
        public static final int middleWing = 2;
        public static final int conveyorPreventer = 3;
    
    public static class PDPport{
        public static final int wingPDP = 6;
        public static final int middlewingPDP = 5;
        public static final int conveyorPreventerPDP = 10;
    }
    }
        

}
