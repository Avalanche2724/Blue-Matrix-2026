package frc.robot;

public class Constants {
//This is for intake
   public static final class IntakeConstants {

    // Motor IDs
    public static final int ROLLER_MOTOR_ID = 14;
    public static final int HATCH_MOTOR_ID = 15;

    // Cargo roller speeds 
    public static final double INTAKE_SPEED = 0.60;
    public static final double EJECT_SPEED = -0.40;
    
    //Hatch speeds 
   public static final double HATCH_GRAB_SPEED = 0.25;
public static final double HATCH_RELEASE_SPEED = -0.25;

public static final double ROLLER_CURRENT_LIMIT = 20.0;
public static final double HATCH_MOTOR_LIMIT = 20.0;

}

//This is for Pivot
public static final class PivotConstants {
   public static final int MOTOR_ID = 16;
    public static final int ENCODER_ID = 17;

    public static final double GEAR_RATIO = 75.0;
    public static final double CURRENT_LIMIT = 80.0;
}

//This is for Elevator
public static final class ElevatorConstants {
    
    public static final int MOTOR_ID = 19;
    public static final int ENCODER_ID = 20;

    public static final double CURRENT_LIMIT = 40.0;

    public static final double UP_SPEED = 0.15;
    public static final double DOWN_SPEED = -0.15;

}
    
}
