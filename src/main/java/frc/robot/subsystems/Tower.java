package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;

import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Tower extends SubsystemBase {
    private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true, 10, 10, 1);
    private TalonSRX towerSrx = new TalonSRX(Constants.MotorPort.tower);
    
    public Tower(){
        towerSrx.configFactoryDefault();
        towerSrx.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Relative, 0, Constants.Motor.kTimesOut);
        // towerSrx.configMotionAcceleration(1000 ,Constants.Motor.kTimesOut);
        // towerSrx.configMotionCruiseVelocity(1000, Constants.Motor.kTimesOut);

        // towerSrx.configClosedLoopPeakOutput(0, 0.9); 

        towerSrx.setNeutralMode(NeutralMode.Brake);      
        towerSrx.setInverted(true);
       
        towerSrx.configPeakOutputForward(0.25,5);
        towerSrx.configPeakOutputReverse(-0.25,5);
        towerSrx.configNominalOutputForward(0,10);
        towerSrx.configNominalOutputReverse(0,10);
        towerSrx.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);

       
    }
    public void aimming(){
        double horizenError = Limelight.getTx()*Constants.Motor.towerConst;
        double targetArea = Limelight.getarea();
        if(targetArea != 0){
            if((Limelight.getTx()<0.1)&&(Limelight.getTx()>(-0.1))){
                towerSrx.set(ControlMode.PercentOutput, 0);
            }else{
                towerSrx.set(ControlMode.PercentOutput, horizenError);
            }
        }
        else{
            towerSrx.set(ControlMode.PercentOutput, 0);
        }
    }
    public void towerForward(){
        towerSrx.set(ControlMode.PercentOutput, 0.1);

    }
    public void towerStop(){
        towerSrx.set(ControlMode.PercentOutput, 0);
    }
    public void towerReverse(){
        towerSrx.set(ControlMode.PercentOutput, -0.1);

    }
} 
    