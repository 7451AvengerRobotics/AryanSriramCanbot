package frc.robot.subsystems;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;
import com.revrobotics.spark.config.SparkMaxConfig;

import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drive extends SubsystemBase {

    // Spark MAX motor controllers
    private final SparkMax leftLeader;
    private final SparkMax leftFollower;
    private final SparkMax rightLeader;
    private final SparkMax rightFollower;

    // Groups of motors for each side
    private final MotorControllerGroup leftGroup;
    private final MotorControllerGroup rightGroup;

    // Differential Drive
    private final DifferentialDrive differentialDrive;

    public Drive() {
        super();

        // Instantiate Spark MAX controllers (brushed mode)
        leftLeader = new SparkMax(4, MotorType.kBrushed);
        leftFollower = new SparkMax(5, MotorType.kBrushed);

        rightLeader = new SparkMax(2, MotorType.kBrushed);
        rightFollower = new SparkMax(1, MotorType.kBrushed);

        SparkMaxConfig config = new SparkMaxConfig();

        config.idleMode(IdleMode.kBrake);
        leftLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        leftFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightLeader.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        rightFollower.configure(config, ResetMode.kResetSafeParameters, PersistMode.kPersistParameters);
        // Set the idle mode to Brake

        // Set the open-loop ramp rate to 0.7 seconds
        leftLeader.setOpenLoopRampRate(0.7);
        leftFollower.setOpenLoopRampRate(0.7);
        rightLeader.setOpenLoopRampRate(0.7);
        rightFollower.setOpenLoopRampRate(0.7);

        // Group each side's motors
        leftGroup = new MotorControllerGroup(leftLeader, leftFollower);
        rightGroup = new MotorControllerGroup(rightLeader, rightFollower);

        // Initialize DifferentialDrive with the motor groups
        differentialDrive = new DifferentialDrive(leftGroup, rightGroup);
    }

    /**
     * Arcade drive method.
     * @param speed    forward/backward speed
     * @param rotation turning rate
     */
    public void arcadeDrive(double speed, double rotation) {
        differentialDrive.arcadeDrive(speed, rotation);
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

