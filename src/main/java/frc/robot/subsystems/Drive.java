package frc.robot.subsystems;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

//import edu.wpi.first.wpilibj.motorcontrol.MotorController;
//import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
import frc.robot.commands.CustomMotorGroup;

public class Drive extends SubsystemBase {

    // Spark MAX motor controllers
    private final SparkMax leftLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightLeader;
    private final SparkMax rightFollower;

    // Groups of motors for each side
    private final CustomMotorGroup leftGroup;
    private final CustomMotorGroup rightGroup;

    // Differential Drive
    private final DifferentialDrive differentialDrive;

    
    public Drive() {
        super();
        
        // Instantiate Spark MAX controllers (brushed mode)
        leftLeader = new SparkMax(Constants.frontLeft, MotorType.kBrushed);
        leftFollower = new SparkMax(Constants.backLeft, MotorType.kBrushed);

        rightLeader = new SparkMax(Constants.frontRight, MotorType.kBrushed);
        rightFollower = new SparkMax(Constants.backRight, MotorType.kBrushed);

        SparkMaxConfig config = new SparkMaxConfig();

        config.idleMode(IdleMode.kBrake);
        // Set the open-loop ramp rate to 0.7 seconds
        config.openLoopRampRate(0.7);
        leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // Set the idle mode to Brake


        // Group each side's motors
        // Set up left and right side motors manually
        leftGroup = new CustomMotorGroup(leftLeader, leftFollower);
        rightGroup = new CustomMotorGroup(rightLeader, rightFollower);

        // Initialize DifferentialDrive with the motor groups
        differentialDrive = new DifferentialDrive(leftGroup, rightGroup);
    }

    /**
     * Arcade drive method.
     * @param speed    forward/backward speed
     * @param rotation turning rate
     */
    public void arcadeDrive(double speed, double rotation) {
        differentialDrive.arcadeDrive(speed, 0 - rotation);
    }

    /**
     * Tank drive method.
     * @param leftTrain  speed for the left side
     * @param rightTrain speed for the right side
     */
    public void tankDrive(double leftTrain, double rightTrain) {
        differentialDrive.tankDrive(leftTrain, rightTrain);
        differentialDrive.feed();
    }
}


