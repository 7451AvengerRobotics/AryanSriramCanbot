package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.subsystems.Drive;

import java.util.function.DoubleSupplier;

public class ArcadeDrive extends Command {
  private final Drive drive;
  private final DoubleSupplier power;
  private final DoubleSupplier turn;
  private double turboValue = Constants.buttonY_toggle;
  
  /**
   * Creates a new DefaultDrive.
   *
   * @param drive       The drive subsystem this command wil run on.
   * @param power       The control input for driving 
   * @param rotation    The control input for turning
   * @param turbo       The button input for toggling the robot speed
   */

   
  public ArcadeDrive(Drive drive, DoubleSupplier power, 
                    DoubleSupplier turn) {
        super();

    this.drive = drive;
    this.power = power;
    this.turn = turn;
    addRequirements(drive);
  }


@Override
  public void execute() {
      turboValue = Constants.buttonY_toggle;
      double scalar = turboValue;
      drive.arcadeDrive(power.getAsDouble() * 1.8 * scalar, turn.getAsDouble() * -scalar);
      }
 }