/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.subsystems.Conveyor;
import frc.robot.subsystems.Flywheel;
import frc.robot.subsystems.Preventor;
import frc.robot.subsystems.Racker;
import frc.robot.subsystems.Tower;

/**
 * This class is where the bulk of the robot should be declared.  Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls).  Instead, the structure of the robot
 * (including subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Tower                 m_tower                     = new Tower();
  private final XboxController        m_XboxController            = new XboxController(1);
  private final Racker                m_Racker                    = new Racker();
  private final Flywheel              m_Flywheel                  = new Flywheel();
  private final Conveyor              m_Conveyor                  = new Conveyor();
  private final Preventor             m_Preventor                 = new Preventor();
  //private InstantCommand test = new InstantCommand(()->m_tower.aimming());
  



  /**
   * The container for the robot.  Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings.  Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a
   * {@link edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {

    new JoystickButton(m_XboxController, 5)  .whenHeld    (new RunCommand(()->m_tower.aimming(),m_tower))
                                             .whenHeld    (new RunCommand(()->m_Racker.PortDistance(),m_Racker))
                                             .whenReleased(new InstantCommand(()->m_tower.towerStop(),m_tower))
                                             .whenReleased(new InstantCommand(()->m_Racker.rackerstop(),m_Racker));
    new JoystickButton(m_XboxController, 1)  .whenHeld    (new InstantCommand(()->m_tower.towerForward(),m_tower))
                                             .whenReleased(new InstantCommand(()->m_tower.towerStop(), m_tower));
    new JoystickButton(m_XboxController, 2)  .whenHeld    (new InstantCommand(()->m_tower.towerReverse(), m_tower))
                                             .whenReleased(new InstantCommand(()->m_tower.towerStop(), m_tower));
    new JoystickButton(m_XboxController, 3)  .whenHeld    (new InstantCommand(()->m_Racker.rackerForward(), m_Racker))
                                             .whenReleased(new InstantCommand(()->m_Racker.rackerstop(), m_Racker));
    new JoystickButton(m_XboxController, 4)  .whenHeld    (new InstantCommand(()->m_Racker.rackerReverse(), m_Racker))
                                             .whenReleased(new InstantCommand(()->m_Racker.rackerstop(), m_Racker));
    new JoystickButton(m_XboxController, 6)  .whenHeld    (new InstantCommand(()->m_Flywheel.flywheelspeedadjust(),m_Flywheel))
                                             .whenReleased(new InstantCommand(()->m_Flywheel.flywheelstop(),m_Flywheel));
    new JoystickButton(m_XboxController, 7)  .whenHeld    (new InstantCommand(()->m_Conveyor.conveyorForward()))
                                             .whenReleased(new InstantCommand((()->m_Conveyor.conveyorStop())));
    new JoystickButton(m_XboxController, 8)  .whenHeld    (new InstantCommand(()->m_Preventor.preventorForward(), m_Preventor))
                                             .whenReleased(new InstantCommand(()->m_Preventor.preventorStop(), m_Preventor));
    
    // new JoystickButton(m_Joystick, 10) .whenHeld(new InstantCommand();

  
  }


  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return null;
  }
}
