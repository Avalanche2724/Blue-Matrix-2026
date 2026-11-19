package frc.robot.generated;

import static edu.wpi.first.units.Units.*;

import com.ctre.phoenix6.CANBus;
import com.ctre.phoenix6.configs.*;
import com.ctre.phoenix6.swerve.*;
import com.ctre.phoenix6.swerve.SwerveModuleConstants.*;
import edu.wpi.first.units.measure.*;
import frc.robot.subsystems.Drivetrain;

import com.ctre.phoenix6.hardware.*;
import com.ctre.phoenix6.signals.StaticFeedforwardSignValue;
import edu.wpi.first.math.Matrix;
import edu.wpi.first.math.numbers.N1;
import edu.wpi.first.math.numbers.N3;
import com.ctre.phoenix6.swerve.SwerveDrivetrainConstants;
import com.ctre.phoenix6.swerve.SwerveModuleConstants;




public class TunerConstants {


    //The code below tells Phoenix 6 which CAN bus your devices will use.
    //"rio" is a reasonable default.
    public static final CANBus kCANBus = new CANBus("rio");

//The code below are the 8 motor IDs 
    public static final int FRONT_LEFT_DRIVE_ID= 1;
    public static final int BACK_LEFT_DRIVE_ID= 2;

    public static final int FRONT_RIGHT_DRIVE_ID = 3;
    public static final int BACK_RIGHT_DRIVE_ID = 4;

    public static final int FRONT_LEFT_STEER_ID= 5;
    public static final int BACK_LEFT_STEER_ID= 6;

    public static final int FRONT_RIGHT_STEER_ID = 7;
    public static final int BACK_RIGHT_STEER_ID = 8;
// The code below are the 4 CANcoder IDs
    public static final int FRONT_LEFT_ENCODER_ID = 9;
public static final int BACK_LEFT_ENCODER_ID = 10;
public static final int FRONT_RIGHT_ENCODER_ID = 11;
public static final int BACK_RIGHT_ENCODER_ID = 12;

//The code below is the pigeon id 
public static final int PIGEON_ID = 13;


    //the code below are the estimated drivetrain dimensions
   public static final Distance WHEEL_RADIUS = Meters.of(0.0508);
    public static final double TRACK_WIDTH_METERS = 0.55;
    public static final double WHEEL_BASE_METERS = 0.55;

    //The code below are the estimated gear ratios 
    public static final double DRIVE_GEAR_RATIO = 6.75;
    public static final double STEER_GEAR_RATIO = 12.8;
    public static final double COUPLING_GEAR_RATIO = 0.0;

    //The code below are the estimated PID values and its feedward values - THESE VALUES NEED TO BE CHANGED BASED ON ACTUAL HARDWARE
    // Drive PID
public static final double DRIVE_KP = 0.01;
public static final double DRIVE_KI = 0.0;
public static final double DRIVE_KD = 0.0;

// Drive feedforward
public static final double DRIVE_KS = 0.0;
public static final double DRIVE_KV = 0.0;
public static final double DRIVE_KA = 0.0;

// Steer PID
public static final double STEER_KP = 10.0;
public static final double STEER_KI = 0.0;
public static final double STEER_KD = 0.0;

// Steer feedforward
public static final double STEER_KS = 0.0;
public static final double STEER_KV = 0.0;
public static final double STEER_KA = 0.0;


        //This code basically takes the PID values and turns them into CTRE configurations.
        private static final Slot0Configs driveGains =
    new Slot0Configs()
        .withKP(DRIVE_KP)
        .withKI(DRIVE_KI)
        .withKD(DRIVE_KD)
        .withKS(DRIVE_KS)
        .withKV(DRIVE_KV)
        .withKA(DRIVE_KA);

private static final Slot0Configs steerGains =
    new Slot0Configs()
        .withKP(STEER_KP)
        .withKI(STEER_KI)
        .withKD(STEER_KD)
        .withKS(STEER_KS)
        .withKV(STEER_KV)
        .withKA(STEER_KA)
        .withStaticFeedforwardSign(StaticFeedforwardSignValue.UseClosedLoopSign);

//The code was added to becuase they will be used by the drive and steering motors.
        private static final DriveMotorArrangement DRIVE_MOTOR_TYPE =
    DriveMotorArrangement.TalonFX_Integrated;

private static final SteerMotorArrangement STEER_MOTOR_TYPE =
    SteerMotorArrangement.TalonFX_Integrated;

private static final SteerFeedbackType STEER_FEEDBACK =
    SteerFeedbackType.RemoteCANcoder;



    //the code below will help us position the modules relative to the center of the robot.
    public static final Distance WHEEL_BASE =
    Meters.of(WHEEL_BASE_METERS);

public static final Distance TRACK_WIDTH =
    Meters.of(TRACK_WIDTH_METERS);




//Here we are defining the initial configurations for the motors and encoders.
//These give the module factory configuration objects to work with

    private static final TalonFXConfiguration driveInitialConfigs =
    new TalonFXConfiguration();

private static final TalonFXConfiguration steerInitialConfigs =
    new TalonFXConfiguration()
        .withCurrentLimits(
            new CurrentLimitsConfigs()
                .withStatorCurrentLimit(Amps.of(60))
                .withStatorCurrentLimitEnable(true)
        );

private static final CANcoderConfiguration encoderInitialConfigs =
    new CANcoderConfiguration();



    public static final LinearVelocity SPEED_AT_12_VOLTS =
    MetersPerSecond.of(4.0);



//The code below configure the motor output type and the swerve traction-control current threshold.
private static final ClosedLoopOutputType DRIVE_OUTPUT =
    ClosedLoopOutputType.Voltage;

private static final ClosedLoopOutputType STEER_OUTPUT =
    ClosedLoopOutputType.Voltage;

private static final Current SLIP_CURRENT = Amps.of(60);




//This code below basically combines all the configurations that were just created.

public static final SwerveModuleConstantsFactory<
    TalonFXConfiguration,
    TalonFXConfiguration,
    CANcoderConfiguration
> ConstantCreator =
    new SwerveModuleConstantsFactory<
        TalonFXConfiguration,
        TalonFXConfiguration,
        CANcoderConfiguration>()

        .withDriveMotorGearRatio(DRIVE_GEAR_RATIO)
        .withSteerMotorGearRatio(STEER_GEAR_RATIO)
        .withCouplingGearRatio(COUPLING_GEAR_RATIO)
        .withWheelRadius(WHEEL_RADIUS)
        .withDriveMotorGains(driveGains)
        .withSteerMotorGains(steerGains)
        .withDriveMotorType(DRIVE_MOTOR_TYPE)
        .withSteerMotorType(STEER_MOTOR_TYPE)
        .withFeedbackSource(STEER_FEEDBACK)
        .withDriveMotorInitialConfigs(driveInitialConfigs)
        .withSteerMotorInitialConfigs(steerInitialConfigs)
        .withEncoderInitialConfigs(encoderInitialConfigs)
        .withDriveMotorClosedLoopOutput(DRIVE_OUTPUT)
        .withSteerMotorClosedLoopOutput(STEER_OUTPUT)
        .withSlipCurrent(SLIP_CURRENT)
        .withSpeedAt12Volts(SPEED_AT_12_VOLTS);


private static final boolean INVERT_LEFT_SIDE = false;
private static final boolean INVERT_RIGHT_SIDE = true;

//This code below is basically saying "I'm creating a configuration called FrontLeft that describes my front-left swerve module."
       public static final SwerveModuleConstants<
    TalonFXConfiguration,
    TalonFXConfiguration,
    CANcoderConfiguration
> FrontLeft =
//This code below is there becuase it will tell eveything about the code above. 
    ConstantCreator.createModuleConstants(
        FRONT_LEFT_STEER_ID,
        FRONT_LEFT_DRIVE_ID,
        FRONT_LEFT_ENCODER_ID,
        Rotations.of(0),
        WHEEL_BASE.div(2),
        TRACK_WIDTH.div(2),
        INVERT_LEFT_SIDE,
        false,
        false


    );

//We will be doing the same thing to the FrontRight 
    public static final SwerveModuleConstants<
    TalonFXConfiguration,
    TalonFXConfiguration,
    CANcoderConfiguration
> FrontRight =

ConstantCreator.createModuleConstants(
    FRONT_RIGHT_STEER_ID,
    FRONT_RIGHT_DRIVE_ID,
    FRONT_RIGHT_ENCODER_ID,
    Rotations.of(0),
    WHEEL_BASE.div(2),
    TRACK_WIDTH.div(-2),
    INVERT_RIGHT_SIDE,
    false,
    false

);

//We will be doing the same thing to the BackLeft
public static final SwerveModuleConstants<
    TalonFXConfiguration,
    TalonFXConfiguration,
    CANcoderConfiguration
    > BackLeft =

    ConstantCreator.createModuleConstants(

    BACK_LEFT_STEER_ID,
    BACK_LEFT_DRIVE_ID,
    BACK_LEFT_ENCODER_ID,
    Rotations.of(0),
    WHEEL_BASE.div(-2),
    TRACK_WIDTH.div(2),
    INVERT_LEFT_SIDE,
        false,
        false

    
    );
//We will be doing the same thing to the BackRight 
    public static final SwerveModuleConstants<
    TalonFXConfiguration,
    TalonFXConfiguration,
    CANcoderConfiguration
    > BackRight =

    ConstantCreator.createModuleConstants(

    BACK_RIGHT_STEER_ID,
    BACK_RIGHT_DRIVE_ID,
    BACK_RIGHT_ENCODER_ID,
    Rotations.of(0),
    WHEEL_BASE.div(-2),
    TRACK_WIDTH.div(-2),
    INVERT_RIGHT_SIDE,
        false,
        false



    );

    //* In WPILib's coordinate system: 
    // Positive X = forward. Positive Y = left. Negative X = backward. Negative Y = right.


// This code below will create the actual drivetrain configuration. It is telling Which CAN bus the drivetrain uses. Which Pigeon2 gyro it uses. Which four modules belong to the drivetrain.
    public static final SwerveDrivetrainConstants DrivetrainConstants =
    new SwerveDrivetrainConstants()
        .withCANBusName(kCANBus.getName())
        .withPigeon2Id(PIGEON_ID);

 // Builds the drivetrain. RobotContainer calls this once.
    public static Drivetrain createDrivetrain() {
    return new Drivetrain(DrivetrainConstants, FrontLeft, FrontRight, BackLeft, BackRight);
}

    // The code below tells CTRE how to construct your drivetrain.
    // TalonFX::new constructs the drive and steering motors.
    // CANcoder::new constructs the absolute encoders.
    public static class TunerSwerveDrivetrain
        extends SwerveDrivetrain<TalonFX, TalonFX, CANcoder> {

        public TunerSwerveDrivetrain(
            SwerveDrivetrainConstants drivetrainConstants,
            SwerveModuleConstants<?, ?, ?>... modules
        ) {
            super(
                TalonFX::new,
                TalonFX::new,
                CANcoder::new,
                drivetrainConstants,
                modules
            );
        }

        public TunerSwerveDrivetrain(
            SwerveDrivetrainConstants drivetrainConstants,
            double odometryUpdateFrequency,
            SwerveModuleConstants<?, ?, ?>... modules
        ) {
            super(
                TalonFX::new,
                TalonFX::new,
                CANcoder::new,
                drivetrainConstants,
                odometryUpdateFrequency,
                modules
            );
        }

        public TunerSwerveDrivetrain(
            SwerveDrivetrainConstants drivetrainConstants,
            double odometryUpdateFrequency,
            Matrix<N3, N1> odometryStandardDeviation,
            Matrix<N3, N1> visionStandardDeviation,
            SwerveModuleConstants<?, ?, ?>... modules
        ) {
            super(
                TalonFX::new,
                TalonFX::new,
                CANcoder::new,
                drivetrainConstants,
                odometryUpdateFrequency,
                odometryStandardDeviation,
                visionStandardDeviation,
                modules
            );
        }
    }

    private TunerConstants() {}

}
//ALL THE NUMBERS HERE ARE PLACEHOLDERS AND CAN/WILL BE CHANGED BASED ON ACTUAL HARDWARE.