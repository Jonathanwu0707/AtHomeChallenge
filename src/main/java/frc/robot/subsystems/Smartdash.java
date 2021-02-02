/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Smartdash extends SubsystemBase {
  
private static NetworkTable table = NetworkTableInstance.getDefault().getTable("limelight-unicorn");
private static NetworkTableEntry tx = table.getEntry("tx");
private static NetworkTableEntry ty = table.getEntry("ty");
private static NetworkTableEntry ta = table.getEntry("ta");
  
double x = tx.getDouble(0.0);
double y = ty.getDouble(0.0);
double area = ta.getDouble(0.0);
  
  /**
   * Creates a new Smartdash.
   */
public Smartdash() {

}
  
public static double getTx() {
  double x = tx.getDouble(0.0);
  return x;
}

public static double getTy() {
  double y = ty.getDouble(0.0);
  return y;
}

public static double getarea() {
  double area = ta.getDouble(0.0);
  return area;
}

  @Override
  public void periodic() {
    SmartDashboard.putNumber("LimelightTx", getTx());
    SmartDashboard.putNumber("LImelightTy", getTy());
    if(getarea()!=0){
      SmartDashboard.putString("LimelightArea", "Have picture");
    }
    else{
    SmartDashboard.putString("LimelightArea", "didn't hava picture");
   }
}
}
