package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;

import java.util.function.DoubleSupplier;

public class ArcadeDrive extends Command {
  private final Drive drive;
  private final DoubleSupplier power;
  private final DoubleSupplier turn;
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
      double scalar =  0.7;
      drive.arcadeDrive(power.getAsDouble() * scalar, turn.getAsDouble() * -scalar);
      }
 }