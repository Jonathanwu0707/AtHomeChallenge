package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;


public class Racker extends SubsystemBase {
    private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true, 30, 30, 1);
    public TalonSRX rackerSrx = new TalonSRX(Constants.MotorPort.racker);

    public Racker(){
        rackerSrx.configFactoryDefault();
        rackerSrx.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.Motor.kTimesOut);
        rackerSrx.configMotionAcceleration(1000 ,Constants.Motor.kTimesOut);
        rackerSrx.configMotionCruiseVelocity(1000, Constants.Motor.kTimesOut);

        rackerSrx.configClosedLoopPeakOutput(0, 0.5); 
        rackerSrx.setNeutralMode(NeutralMode.Brake);  
        rackerSrx.configNeutralDeadband(0.2);    
        rackerSrx.setInverted(true);
        rackerSrx.configPeakOutputForward(0.5);
        rackerSrx.configPeakOutputReverse(-0.5);

        rackerSrx.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
        rackerSrx.config_kP(0, Constants.Motor.rackerKP);
        rackerSrx.config_kI(0, Constants.Motor.rackerKI);
        rackerSrx.config_IntegralZone(0, Constants.Motor.rackerIZone);

        rackerSrx.setSelectedSensorPosition(0, 0, 10);

    }
   

    public void CertainDistance(){
        int targetDistance = 5000;
        rackerSrx.set(ControlMode.Position, (targetDistance-rackerSrx.getSelectedSensorPosition()));
    }

    public void rackerForward(){
        rackerSrx.set(ControlMode.PercentOutput,0.3);
    }
   
    public void rackerstop(){
        rackerSrx.set(ControlMode.PercentOutput, 0);
    }

    public void rackerReverse(){
        rackerSrx.set(ControlMode.PercentOutput, -0.3);
    }

    public void PortDistance(){
        double distance = Limelight.getdistances();
        
        if(distance<=200){
            rackerSrx.set(ControlMode.Position, 0);
        }else if(225>=distance&&distance>200){

        }else if(250>=distance&&distance>225){

        }else if(275>=distance&&distance>250){

        }else if(300>=distance&&distance>275){

        }else if(325>=distance&&distance>300){

        }else if(350>=distance&&distance>325){

        }else if(375>=distance&&distance>350){
            
        }else{

        }

    }
  @Override
    public void periodic(){
        SmartDashboard.putNumber("rackPoistion", rackerSrx.getSelectedSensorPosition());
    }
} 