
package frc.robot.subsystems;

import frc.robot.Constants;


import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.StatusCode;

public class Elevator extends SubsystemBase {

    private final TalonFX elevatorMotor =
        new TalonFX(Constants.ElevatorConstants.MOTOR_ID);

    private final CANcoder elevatorEncoder =
        new CANcoder(Constants.ElevatorConstants.ENCODER_ID);

    // Minimum and maximum height
     private static final double MIN_HEIGHT = 7.0;
    private static final double MAX_HEIGHT = 83.25;

    public Elevator() {

        TalonFXConfiguration elevatorConfigs =
            new TalonFXConfiguration();

        elevatorConfigs.CurrentLimits.StatorCurrentLimit =
            Constants.ElevatorConstants.CURRENT_LIMIT;

        elevatorConfigs.CurrentLimits.StatorCurrentLimitEnable = true;

        StatusCode status =
            elevatorMotor.getConfigurator().apply(elevatorConfigs);

        if (!status.isOK()) {
            System.out.println("Elevator configuration failed!");
        }

        elevatorMotor.setNeutralMode(NeutralModeValue.Brake);
    
    
    }
   // Read elevator encoder position in rotations
    public double getElevatorPosition() {

        double rotations = elevatorEncoder
            .getAbsolutePosition()
            .getValueAsDouble();

        return rotations;
    }


   // Move elevator with minimum and maximum limits
    public Command moveElevator(double speed) {
        return runEnd(
            () -> {
                double position = getElevatorPosition();

                if ((speed > 0 && position < MAX_HEIGHT) ||
                    (speed < 0 && position > MIN_HEIGHT)) {

                    elevatorMotor.set(speed);

                } else {
                    elevatorMotor.set(0);
                }
            },
            () -> elevatorMotor.set(0)
        );
    }

    // Stop elevator
    public Command stopElevator() {
        return runOnce(() -> elevatorMotor.set(0));
    }
}
