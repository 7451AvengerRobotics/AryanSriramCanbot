package frc.robot.commands.Auton;


import frc.robot.subsystems.Drive;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;


public class ComplexAuton extends Command{
    private final Drive drivetrain;
    private double rotation;
    private double speed;
    private double time;
    private double rotation2;
    private double speed2;
    private double time2;
    private double rotation3;
    private double speed3;
    private double time3;
    
    Timer timer = new Timer();

    public ComplexAuton(Drive drive, double driveRotation, double driveSpeed, double driveTime, double driveRotation2, double driveSpeed2, double driveTime2, double driveRotation3, double driveSpeed3, double driveTime3) {
        drivetrain = drive;
        rotation = driveRotation;
        speed = driveSpeed;
        time = driveTime;
        rotation2 = driveRotation2;
        speed2 = driveSpeed2;
        time2 = driveTime2;
        rotation3 = driveRotation3;
        speed3 = driveSpeed3;
        time3 = driveTime3;


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
        timer.reset();
        System.out.println("It finish");


        timer.start();

        while (timer.get() < time2) {
            drivetrain.arcadeDrive(rotation2, speed2);
        }
        timer.stop();
        timer.reset();
        System.out.println("It finish");


        timer.start();

        while (timer.get() < time3) {
            drivetrain.arcadeDrive(rotation3, speed3);
        }
        timer.stop();
        timer.reset();
        System.out.println("It finish");
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
