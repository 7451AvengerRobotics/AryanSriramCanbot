package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Piston extends SubsystemBase{
    private static boolean isExtended = false;
    private final Compressor compressor;
    private final Solenoid armSolenoid;
    private final Solenoid lockSolenoid;

    public Piston() {
        super();
        //initializing compressor and solenoid  
       compressor = new Compressor(0, PneumaticsModuleType.CTREPCM); //need to change module id 
        armSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, Constants.Arm[1]); 
        lockSolenoid = new Solenoid(PneumaticsModuleType.CTREPCM, 6);
        //need to change module id
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


   public void unlockSolenoid(){
    lockSolenoid.set(false);
   }

   public void lockSolenoid(){
    lockSolenoid.set(true);
   }

   public boolean getArmState(){
    return isExtended;
   }
}