
package frc.robot.subsystems;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import com.ctre.phoenix6.hardware.TalonFX;
import frc.robot.Constants;
import edu.wpi.first.wpilibj2.command.Command;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.signals.NeutralModeValue;
import com.ctre.phoenix6.StatusCode;


public class Intake extends SubsystemBase {
    private final TalonFX rollerMotor =
    new TalonFX(Constants.IntakeConstants.ROLLER_MOTOR_ID);

    public Intake() {

    // Create the motor configuration
    TalonFXConfiguration rollerConfigs =
        new TalonFXConfiguration();

        TalonFXConfiguration hatchConfigs =
        new TalonFXConfiguration();

    // Set the current limit
    rollerConfigs.CurrentLimits.StatorCurrentLimit =
        Constants.IntakeConstants.ROLLER_CURRENT_LIMIT;


    rollerConfigs.CurrentLimits.StatorCurrentLimitEnable = true;

    // Set neutral mode to Coast
    rollerConfigs.MotorOutput.NeutralMode =
        NeutralModeValue.Coast;

        hatchConfigs.CurrentLimits.StatorCurrentLimit =
    Constants.IntakeConstants.HATCH_MOTOR_LIMIT;

   hatchConfigs.CurrentLimits.StatorCurrentLimitEnable = true;

    //Set neutral mode to coast
    hatchConfigs.MotorOutput.NeutralMode =
        NeutralModeValue.Coast; 



    StatusCode status =
    rollerMotor.getConfigurator().apply(rollerConfigs);

if (!status.isOK()) {
    System.out.println(
        "Intake motor configuration failed: " + status
    );
}


StatusCode hatchStatus =
    hatchMotor.getConfigurator().apply(hatchConfigs);

if (!hatchStatus.isOK()) {
    System.out.println(
        "Hatch motor configuration failed: " + hatchStatus
    );
}
}


private final TalonFX hatchMotor =
    new TalonFX(Constants.IntakeConstants.HATCH_MOTOR_ID);


    // Controls the cargo roller
private void setRoller(double speed) {
    rollerMotor.set(speed);
}

// This code runs the roller to collect cargo
public Command intakeCargo() {
    return runEnd(
        () -> setRoller(Constants.IntakeConstants.INTAKE_SPEED),
        () -> setRoller(0.0)
    );
}

// This code runs the roller in reverse to eject cargo
public Command ejectCargo() {
    return runEnd(
        () -> setRoller(Constants.IntakeConstants.EJECT_SPEED),
        () -> setRoller(0.0)
    );
}

public void stopRoller() {
    rollerMotor.set(0.0);

}


// Rotates the hatch mechanism to grab a hatch panel
public Command grabHatch() {
    return runEnd(
        () -> hatchMotor.set(Constants.IntakeConstants.HATCH_GRAB_SPEED),
        () -> hatchMotor.set(0.0)
    );
}

// Rotates the hatch mechanism to release a hatch panel
public Command releaseHatch() {
    return runEnd(
        () -> hatchMotor.set(Constants.IntakeConstants.HATCH_RELEASE_SPEED),
        () -> hatchMotor.set(0.0)


    );
}
}


