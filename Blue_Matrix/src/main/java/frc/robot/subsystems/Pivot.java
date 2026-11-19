
package frc.robot.subsystems;

import frc.robot.Constants;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

import com.ctre.phoenix6.hardware.TalonFX;
import com.ctre.phoenix6.hardware.CANcoder;

import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.StatusCode;

public class Pivot extends SubsystemBase {

    private final TalonFX pivotMotor =
        new TalonFX(Constants.PivotConstants.MOTOR_ID);

    private final CANcoder pivotEncoder =
        new CANcoder(Constants.PivotConstants.ENCODER_ID);

    // Minimum and maximum angles
    private static final double MIN_ANGLE = 0.0;
    private static final double MAX_ANGLE = 90.0;

    public Pivot() {

        TalonFXConfiguration pivotConfigs =
            new TalonFXConfiguration();

        pivotConfigs.CurrentLimits.StatorCurrentLimit =
            Constants.PivotConstants.CURRENT_LIMIT;

        pivotConfigs.CurrentLimits.StatorCurrentLimitEnable = true;

        StatusCode status =
            pivotMotor.getConfigurator().apply(pivotConfigs);

        if (!status.isOK()) {
            System.out.println("Pivot configuration failed!");
        }

        pivotMotor.setNeutralMode(NeutralModeValue.Brake);
    }

    // Read pivot position in degrees
    public double getPivotPosition() {
        double rotations = pivotEncoder
            .getAbsolutePosition()
            .getValueAsDouble();

        return MathUtil.inputModulus(rotations, 0.0, 1.0) * 360.0;
    }

    // Move pivot with minimum and maximum limits
    public Command movePivot(double speed) {
        return runEnd(
            () -> {
                double position = getPivotPosition();

                if ((speed > 0 && position < MAX_ANGLE) ||
                    (speed < 0 && position > MIN_ANGLE)) {

                    pivotMotor.set(speed);

                } else {
                    pivotMotor.set(0);
                }
            },
            () -> pivotMotor.set(0)
        );
    }

    // Stop pivot
    public Command stopPivot() {
        return runOnce(() -> pivotMotor.set(0));
    }
}
