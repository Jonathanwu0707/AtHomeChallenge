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

public class Preventor extends SubsystemBase {
  
  private VictorSPX preventor = new VictorSPX(Constants.MotorPort.conveyorPreventer);
  private PowerDistributionPanel PDP = new PowerDistributionPanel(0);
   
  /** Creates a new Preventor. */
  public Preventor() {

  
    preventor.configFactoryDefault();
    preventor.configPeakOutputForward(0.8);
    preventor.configPeakOutputReverse(-0.8);
    preventor.configNeutralDeadband(0.04);
    preventor.setNeutralMode(NeutralMode.Brake);
    preventor.setInverted(true);
  }

  public void preventorForward(){
    preventor.set(ControlMode.PercentOutput, 0.6);
  }

  public void preventorStop(){
    preventor.set(ControlMode.PercentOutput, 0.0);
  }

  public void preventorReverse(){
    preventor.set(ControlMode.PercentOutput, -0.6);
  }
  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
