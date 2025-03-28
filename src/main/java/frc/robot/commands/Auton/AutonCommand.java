package frc.robot.commands.Auton;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import frc.robot.subsystems.Drive;


public class AutonCommand {
    public boolean SimpleAuton = true;

    public AutonCommand() {
        super();
        //initializing compressor and solenoid  
        
    }
    public void arcader(Drive drive, double rotation, double speed, double time) {
        Timer timer = new Timer();
        timer.start();
        while (timer.get() < time) {
            drive.arcadeDrive(rotation, speed);
        }
        timer.stop();
    }
    public void autonCommand(Drive drive){
        arcader(drive, 0, 0.5, 1);
        arcader(drive, 0.9, 0.5, 1.23);
        arcader(drive, 0, 0.5, 1);
    }  
    public Command runAutonCommand(Drive drive) {
        return Commands.runOnce(() -> {
            System.out.println("Driving");
            autonCommand(drive);
        });
    }
    
        
}


