package frc.robot.commands;


import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;


public class DriveCommand extends Command{
    private final Drive drivetrain;
    private double rotation;
    private double speed;
    private double time;
    
    Timer timer = new Timer();

    public DriveCommand(Drive drive, double driveRotation, double driveSpeed, double driveTime) {
        drivetrain = drive;
        rotation = driveRotation;
        speed = driveSpeed;
        time = driveTime;

        // Use addRequirements() here to declare subsystem dependencies.
        addRequirements(drive);
      }

      @Override
      public void execute() {

        timer.start();

        while (timer.get() < time) {
            drivetrain.arcadeDrive(rotation, speed);
        }
        timer.stop();
        this.isFinished();
      }

      public void end() {
        drivetrain.arcadeDrive(0, 0);
      }

      @Override
      public boolean isFinished() {
        return true;
      }

}
