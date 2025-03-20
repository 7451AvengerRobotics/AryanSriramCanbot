

package frc.robot.commands;


import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drive;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;


public class TankDrive extends Command {
  private final Drive drive;
  private final DoubleSupplier left;
  private final DoubleSupplier right;
  private final BooleanSupplier turbo;


  /**
   * Creates a new DefaultDrive.
   *
   * @param drive     The drive subsystem this command wil run on.
   * @param left      The control input for driving lefts/backwards
   * @param right     The control input for turning
   * @param turbo     The button input for toggling the robot speed
   */
  public TankDrive(
      Drive drive,
      DoubleSupplier left,
      DoubleSupplier right,
      BooleanSupplier turbo) {
        super();


    this.drive = drive;
    this.left = left;
    this.right = right;
    this.turbo = turbo;

    addRequirements(drive);
  }


  @Override
  public void execute() {
    if (turbo.getAsBoolean() == true){
    double scalar = turbo.getAsBoolean() ? 1 : 1;
    drive.tankDrive(right.getAsDouble() * scalar, left.getAsDouble() * scalar);
    SmartDashboard.putNumber("DriveTrain Power", left.getAsDouble());
    }
    else{
    double scalar = turbo.getAsBoolean() ? 0.7 : 0.7;
    drive.tankDrive(right.getAsDouble() * scalar, left.getAsDouble() * scalar);
    SmartDashboard.putNumber("DriveTrain Power", left.getAsDouble());
    }
  }
}