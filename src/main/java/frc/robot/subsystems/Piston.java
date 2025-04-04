package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Piston extends SubsystemBase{
    private static boolean isExtended = false;
    private final Compressor compressor;
    private final Solenoid armSolenoid;
    public static DigitalInput bigRedButton = new DigitalInput(4);

    public Piston() {
        super();
        //initializing compressor and solenoid  
        compressor = new Compressor(0, PneumaticsModuleType.CTREPCM); //need to change module id 
        armSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 1); 
    }
   public void stop(){
    compressor.disable();
   }  

   public void start(){
        compressor.enableDigital();
    }  

   public void extend(){
    //sets the solenoid output to on
    armSolenoid.set(false);
    isExtended = false;
   }

   public void retract(){
    //sets the solenoid output to off
    armSolenoid.set(true);
    isExtended = true;
   }

   public void toggle(){
    if(isExtended){
        this.extend();
    } else{
        this.retract();
    }
   }

   public Command togglePistonCommand1() {
    return runOnce(() -> {
        System.out.println("Toggling");
        this.toggle();
    });
   }


   public Command extendCommand() {
    return runOnce(()->{
        this.extend();
    });
   }

   public Command retractCommand() {
    return runOnce(()->{
        this.retract();
    });
   }

   public boolean getArmState(){
    return isExtended;
   }
   @Override
   public void periodic() 
   {
    SmartDashboard.putBoolean("Wallabaloon", isExtended);
    SmartDashboard.putBoolean("King", bigRedButton.get());
   }
}