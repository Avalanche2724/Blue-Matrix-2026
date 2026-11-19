
package frc.robot.subsystems;

import com.ctre.phoenix6.swerve.SwerveRequest;
import com.ctre.phoenix6.swerve.SwerveModule.DriveRequestType;
import com.ctre.phoenix6.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;

import edu.wpi.first.math.MathUtil;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Subsystem;

import frc.robot.generated.TunerConstants;
import frc.robot.generated.TunerConstants.TunerSwerveDrivetrain;

import java.util.function.Supplier;
import java.util.function.DoubleSupplier;

import static edu.wpi.first.units.Units.MetersPerSecond;





//This constructor below basically is creating the CTRE drivetrain using those configurations made by the tunerConstants
public class Drivetrain extends TunerSwerveDrivetrain implements Subsystem {

    //All these values for MAXSPEED, MAX_ANGULAR_RATE, and DEADBAND are just placeholders can will be changed as required.
    // Maximum speed of the drivetrain, MAX_SPEED converts the joystick value (-1 to 1) into an actual speed in meters per second.
    private static final double MAX_SPEED =
        TunerConstants.SPEED_AT_12_VOLTS.in(MetersPerSecond);

    //This code is the Maximum rotational speed
    private static final double MAX_ANGULAR_RATE = 2.0 * Math.PI;

    // This code is the Joystick deadband
    private static final double DEADBAND = 0.08;




    // The code below is basically making the four swerve modules (front right, front left, back right and back left) to CTRE's drivetrain system.
    public Drivetrain(
        SwerveDrivetrainConstants drivetrainConstants,
        SwerveModuleConstants<?, ?, ?>... modules) {

        super(drivetrainConstants, modules);
    }


    private final SwerveRequest.FieldCentric fieldCentricRequest =
        new SwerveRequest.FieldCentric()
            .withDriveRequestType(DriveRequestType.OpenLoopVoltage);

//This code below is for the brakes request
            private final SwerveRequest.SwerveDriveBrake brakeRequest =
    new SwerveRequest.SwerveDriveBrake();

//This code below is for the stop request 
    private final SwerveRequest.FieldCentric stopRequest =
        new SwerveRequest.FieldCentric()
            .withVelocityX(0)
            .withVelocityY(0)
            .withRotationalRate(0);


    // This command continuously tells the drivetrain to follow the newest request. So when u continuously move the joysticks when controlling robot, it keeps doing the newest request rather then the old requests only. 
    public Command applyRequest(Supplier<SwerveRequest> requestSupplier) {

        return run(() -> {
            setControl(requestSupplier.get());
        });
    }


    // This code converts joystick values into actual movement speeds. And the DEADBAND is there so the robot stops moving when you are not moving the joysticks 
    public Command drive(
        DoubleSupplier xSpeed,
        DoubleSupplier ySpeed,
        DoubleSupplier rotSpeed) {

        return applyRequest(() -> {

            // Get joystick values and apply deadband
            double x = MathUtil.applyDeadband(
                xSpeed.getAsDouble(), DEADBAND);

            double y = MathUtil.applyDeadband(
                ySpeed.getAsDouble(), DEADBAND);

            double rotation = MathUtil.applyDeadband(
                rotSpeed.getAsDouble(), DEADBAND);


            // Convert joystick values into actual speeds
            return fieldCentricRequest
                .withVelocityX(x * MAX_SPEED)
                .withVelocityY(y * MAX_SPEED)
                .withRotationalRate(rotation * MAX_ANGULAR_RATE);

        });
    }
//This is for the X-shaped brake request,this is what positions the wheels will be to resist being pushed.
    public Command brake() {
        return applyRequest(() -> brakeRequest);
    }


    // This sends the zero velocity requests. 
    public Command stop() {
        return applyRequest(() -> stopRequest);
    }

}


