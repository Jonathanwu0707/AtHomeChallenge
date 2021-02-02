// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.VictorSPX;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Conveyor extends SubsystemBase {
  private VictorSPX wingSpx = new VictorSPX(Constants.MotorPort.wing);
  private VictorSPX middleSpx = new VictorSPX(Constants.MotorPort.middleWing);
  private PowerDistributionPanel PDP = new PowerDistributionPanel(0);
  /** Creates a new Conveyor. */
  public Conveyor() {
    wingSpx.configFactoryDefault();
    wingSpx.configPeakOutputForward(0.8);
    wingSpx.configPeakOutputReverse(-0.8);
    wingSpx.configNeutralDeadband(0.04);
    wingSpx.setNeutralMode(NeutralMode.Brake);
    wingSpx.setInverted(false);

    middleSpx.configFactoryDefault();
    middleSpx.configPeakOutputForward(0.8);
    middleSpx.configPeakOutputReverse(-0.8);
    middleSpx.configNeutralDeadband(0.04);
    middleSpx.setNeutralMode(NeutralMode.Brake);
    middleSpx.setInverted(true);

  }

  public void conveyorForward(){
    wingSpx.set(ControlMode.PercentOutput, 0.6);
    middleSpx.set(ControlMode.PercentOutput, 0.4);
  }

  public void conveyorStop(){
    wingSpx.set(ControlMode.PercentOutput, 0.0);
    middleSpx.set(ControlMode.PercentOutput, 0.0);
  }

  public void conveyorReverse(){
    wingSpx.set(ControlMode.PercentOutput, -0.6);
    middleSpx.set(ControlMode.PercentOutput, -0.4);
  }
  

  
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    if(PDP.getCurrent(4)>25){
      wingSpx.set(ControlMode.PercentOutput, 0);
    }else if(PDP.getCurrent(5)>25){
      middleSpx.set(ControlMode.PercentOutput, 0);
    }
  }
}
