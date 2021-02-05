package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.*;
import com.ctre.phoenix.motorcontrol.can.*;
import com.ctre.phoenix.motorcontrol.LimitSwitchNormal;
import com.ctre.phoenix.motorcontrol.LimitSwitchSource;
import edu.wpi.first.wpilibj.MedianFilter;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import edu.wpi.first.wpilibj.DigitalInput;

public class Tower extends SubsystemBase {
    private SupplyCurrentLimitConfiguration supplyCurrentLimitConfiguration = new SupplyCurrentLimitConfiguration(true, 10, 10, 1);
    private static TalonSRX towerSrx = new TalonSRX(Constants.MotorPort.tower);
    private MedianFilter filter = new MedianFilter(3);
    private DigitalInput digInput = new DigitalInput(0);

    public  Tower() {
        towerSrx.configFactoryDefault();
        towerSrx.configSelectedFeedbackSensor(TalonSRXFeedbackDevice.CTRE_MagEncoder_Absolute, 0,
                Constants.Motor.kTimesOut);
        // towerSrx.configMotionAcceleration(1000 ,Constants.Motor.kTimesOut);
        // towerSrx.configMotionCruiseVelocity(1000, Constants.Motor.kTimesOut);

        // towerSrx.configClosedLoopPeakOutput(0, 0.9);
        towerSrx.configForwardSoftLimitThreshold(3600, 0);
        towerSrx.configReverseSoftLimitThreshold(-3600, 0);
        towerSrx.configForwardSoftLimitEnable(true, 0);
        towerSrx.configReverseSoftLimitEnable(true, 0);

        
        towerSrx.setNeutralMode(NeutralMode.Brake);
        towerSrx.setInverted(true);

        towerSrx.configPeakOutputForward(0.25, 5);
        towerSrx.configPeakOutputReverse(-0.25, 5);
        towerSrx.configNominalOutputForward(0, 10);
        towerSrx.configNominalOutputReverse(0, 10);
        towerSrx.configSupplyCurrentLimit(supplyCurrentLimitConfiguration);
    }

    public static int towerPosition() {
        int Position = towerSrx.getSelectedSensorPosition();
        return Position;
    }

    public void aimming(){
        double horizenError = Limelight.getTx()*Constants.Motor.towerConst;
        double targetArea = Limelight.getarea();
        filter.calculate(horizenError);

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
    @Override
    public void periodic(){
        if(digInput.get()){
            towerSrx.setSelectedSensorPosition(0,0,10);
        }  
        SmartDashboard.putNumber("towerpo", towerSrx.getSelectedSensorPosition(0));
    }
} 
    