package frc.robot.subsystems;
import com.ctre.phoenix.motorcontrol.*;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix.motorcontrol.can.TalonFX;
import frc.robot.Constants;

public class Flywheel extends SubsystemBase  {
  private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true, 40, 41, 1);
  private TalonFX flywheelLeft = new TalonFX(Constants.MotorPort.flywheelLeft);
  private TalonFX flywheelRight = new TalonFX(Constants.MotorPort.flywheelRight); 


  public Flywheel() {
    // Factory default hardware to prevent unexpected behavior 
    flywheelLeft.configFactoryDefault();
    flywheelRight.configFactoryDefault();
    
    SmartDashboard.putNumber("flywheel",flywheelLeft.getSelectedSensorVelocity());
    //set sensor
    flywheelLeft.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, Constants.Motor.kTimesOut);
    flywheelRight.configSelectedFeedbackSensor(TalonFXFeedbackDevice.IntegratedSensor, 0, Constants.Motor.kTimesOut);
  
    //adjust kP,kF 
    flywheelLeft.config_kP(0, 0.1);
    flywheelLeft.config_kF(0 , 0.05);
    flywheelRight.config_kP(0, 0.1);
    flywheelRight.config_kF(0 , 0.05);

    
    //adjust CruiseVel, Accler,SensorPos
    flywheelLeft.configMotionCruiseVelocity(1000, Constants.Motor.kTimesOut);
		flywheelLeft.configMotionAcceleration(6000, Constants.Motor.kTimesOut);
		flywheelLeft.setSelectedSensorPosition(0, 0, Constants.Motor.kTimesOut);
    flywheelRight.configMotionCruiseVelocity(1000, Constants.Motor.kTimesOut);
		flywheelRight.configMotionAcceleration(6000, Constants.Motor.kTimesOut);
		flywheelRight.setSelectedSensorPosition(0, 0, Constants.Motor.kTimesOut);

    //PeakOutput , CurrentLimit , NeutralDeadband 
    flywheelLeft.configPeakOutputForward(1, Constants.Motor.kTimesOut);
    flywheelRight.configPeakOutputForward(1, Constants.Motor.kTimesOut);
    flywheelLeft.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    flywheelRight.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    
    flywheelLeft.setNeutralMode(NeutralMode.Coast);
    flywheelRight.setNeutralMode(NeutralMode.Coast);
    flywheelLeft.configNeutralDeadband(0.005, Constants.Motor.kTimesOut);
    flywheelRight.configNeutralDeadband(0.005, Constants.Motor.kTimesOut);
    
    //Closedloop,Openedloop
    flywheelLeft.configClosedloopRamp(1, 10);
    flywheelRight.configClosedloopRamp(1, 10);
    
    //InvertType

    flywheelLeft.setInverted(false);
    flywheelRight.setInverted(true);
  }

  public void flywheelspeedadjust() {
    if(Limelight.getdistances()<=200){
      flywheelRight.set(ControlMode.Velocity, 11000);
      flywheelLeft.set(ControlMode.Velocity, 11000);
    }else{
      flywheelLeft.set(ControlMode.Velocity,12000);
      flywheelRight.set(ControlMode.Velocity, 12000);
    }
  }
  public void flywheelstop() {
    flywheelLeft.set(ControlMode.PercentOutput,0);
    flywheelRight.set(ControlMode.PercentOutput, 0);
    SmartDashboard.putBoolean("hello", true);
  }
  @Override
  public void periodic(){
    SmartDashboard.putNumber("flyspeed", flywheelLeft.getSelectedSensorVelocity());
    SmartDashboard.putNumber("distance", Limelight.getdistances());
  }
}