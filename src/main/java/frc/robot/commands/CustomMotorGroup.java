package frc.robot.commands;

import edu.wpi.first.wpilibj.motorcontrol.MotorController;

    public class CustomMotorGroup implements MotorController {
    private final MotorController[] motors;

    public CustomMotorGroup(MotorController... motors) {
        this.motors = motors;
    }

    @Override
    public void set(double speed) {
        for (MotorController motor : motors) {
            motor.set(speed);
        }
    }

    @Override
    public double get() {
        return motors[0].get(); // Return the speed of the first motor
    }

    @Override
    public void setInverted(boolean isInverted) {
        for (MotorController motor : motors) {
            motor.setInverted(isInverted);
        }
    }

    @Override
    public boolean getInverted() {
        return motors[0].getInverted(); // Assume all motors have the same inversion
    }

    @Override
    public void disable() {
        for (MotorController motor : motors) {
            motor.disable();
        }
    }

    @Override
    public void stopMotor() {
        for (MotorController motor : motors) {
            motor.stopMotor();
        }
    }
}
