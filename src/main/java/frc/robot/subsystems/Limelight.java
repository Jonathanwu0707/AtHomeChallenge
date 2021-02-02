package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Limelight {

private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-unicorn");
private static NetworkTableEntry tx = table.getEntry("tx");
private static NetworkTableEntry ty = table.getEntry("ty");
private static NetworkTableEntry ta = table.getEntry("ta");

//read values periodically
double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);



public static double getarea() {
    double area = ta.getDouble(0.0);
    return area;
}

public static double getTx() {
    double x = tx.getDouble(0.0);
    return x;
}

public static double getTy() {
    double y = ty.getDouble(0.0);
    return y;
}

public static double getdistances(){
    double distance = (250-55)/(Math.tan(Math.toRadians(54+Limelight.getTy())));
    return distance;
}


public void board() {
    SmartDashboard.putNumber("LimelightTx", getTx());
    SmartDashboard.putNumber("LImelightTy", getTy());
    SmartDashboard.putNumber("LimelightArea", getarea());
    //y = ty.getDouble(0.0);
    //SmartDashboard.putNumber("Y", y);
    
}

}



