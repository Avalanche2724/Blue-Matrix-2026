package frc.robot;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import frc.robot.generated.TunerConstants;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.Pivot;
import frc.robot.subsystems.Elevator;



public class RobotContainer {
    // represents your Xbox controller connected to port 0
    private final CommandXboxController controller =
    new CommandXboxController(0);
//This code below creates your CTRE swerve drivetrain using the configurations in TunerConstants.

//This is so we can access to the drivetrain, intake, pivot, elevator
    private final Drivetrain drivetrain = TunerConstants.createDrivetrain();
    private final Intake intake = new Intake();
    private final Pivot pivot = new Pivot();
    private final Elevator elevator = new Elevator();

    public RobotContainer() {
//The code below is Basically telling the drivetrain: “every cycle, read the joysticks and turn those values into robot movement.”
//There are negatives before "controller" because , pushing a stick up or left gives a negative number. WPILib’s coordinate system says forward and left are positive, so the minus flips the sign to match.
        drivetrain.setDefaultCommand(
    drivetrain.drive(
        () -> -controller.getLeftY(),   // This is for the forward/back. left stick up/down controls driving forward and backward
        () -> -controller.getLeftX(),   // This is for the left/right. left stick left/right controls strafing sideways
        () -> -controller.getRightX() // This is for the rotation. right stick left/right controls turning
        
    )

);
   // Connects controller buttons to commands
        configureBindings();

    }

    private void configureBindings() {

        // Hold B to put the swerve modules into brake mode
        controller.b().whileTrue(
                drivetrain.brake()
        );

        // Press Start to reset the field-centric heading
        controller.start().onTrue(
                drivetrain.runOnce(() -> drivetrain.seedFieldCentric())

                
        );
        //This is the intake controlling. A- Collect cargo X- Eject cargo Y - Grab hatch. Right bumper (RB)- Release hatch. (B- is being used for the drivetrain brake)
        // Hold A to collect cargo
controller.a().whileTrue(
    intake.intakeCargo()
);

// Hold X to eject cargo
controller.x().whileTrue(
    intake.ejectCargo()
);

// Hold Y to grab hatch
controller.y().whileTrue(
    intake.grabHatch()
);

// Hold Right Bumper to release hatch
controller.rightBumper().whileTrue(
    intake.releaseHatch()
);

//This is for the pivot controlling 
// Hold D-pad Up to move pivot
controller.povUp().whileTrue(
    pivot.movePivot(0.15)
);

// Hold D-pad Down to move pivot in the opposite direction
controller.povDown().whileTrue(
    pivot.movePivot(-0.15)
);

 //Elevator controls
// Hold left trigger to move elevator up
controller.leftTrigger().whileTrue(
    elevator.moveElevator(0.15)
);

// Hold right trigger to move elevator down
controller.rightTrigger().whileTrue(
    elevator.moveElevator(-0.15)
);
    }
}
