// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.commands.ArcadeDrive;
import frc.robot.commands.Auton.AutonCommand;
import frc.robot.commands.Auton.ComplexAuton;
import frc.robot.commands.Auton.SimpleAuton;
import frc.robot.subsystems.Drive;
import frc.robot.subsystems.Piston;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.WaitCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Piston pistonSubsystem = new Piston();

  final AutonCommand autonCommand = new AutonCommand();
  
  Trigger buttonSmasher = new Trigger(Piston.bigRedButton::get);
  Drive drivetrain = new Drive();

  private final Command simpleAuto = new SimpleAuton(drivetrain, 0, 0.5, 1);
  private final Command complexAuto = new ComplexAuton(drivetrain, 0, 0.5, 1, 0.9, 0.5, 1.5, 0, 0.5, 1);
  SendableChooser<Command> m_chooser = new SendableChooser<>();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final Joystick m_driverController =
      new Joystick(OperatorConstants.kDriverControllerPort);

  private JoystickButton buttonY = new JoystickButton(m_driverController, 4);

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    configureDriveTrain();

    m_chooser.setDefaultOption("Simple Auto", simpleAuto);
    m_chooser.addOption("Complex Auto", complexAuto);

    SmartDashboard.putData(m_chooser);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.command.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */

   private void configureDriveTrain() {

/*
Sets the state of the type of drivetrain. For example if driver wants Arcade than the driver presses the A button to enter arcade mode.
If the driver presses the B button than the drivtrain will reset back to Tank Drive
*/

      drivetrain.setDefaultCommand(
        new ArcadeDrive(
            drivetrain,
            m_driverController::getX,
            m_driverController::getY));
}

  private void configureBindings() {
    // Schedule `exampleMethodCommand` when the Xbox controller's B button is pressed,
    // cancelling on release.
    // buttonSmasher.onTrue(pistonSubsystem.togglePistonCommand1());
    buttonSmasher.toggleOnTrue(pistonSubsystem.extendCommand());
    buttonSmasher.toggleOnFalse(pistonSubsystem.retractCommand());
    buttonY.onTrue(drivetrain.turboToggler());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    //System.out.println(autoChooser.get());
    //return autoChooser.get();
    //return autonCommand.runAutonCommand(drivetrain);
    return m_chooser.getSelected();
  }
}