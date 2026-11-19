
package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {


    private static final String kDefaultAuto = "Default";
    private static final String kCustomAuto = "My Auto";

    private String m_autoSelected;
    private final SendableChooser<String> m_chooser =
            new SendableChooser<>();

    // Stores our RobotContainer
    private RobotContainer m_robotContainer;

    public Robot() {
        m_chooser.setDefaultOption("Default Auto", kDefaultAuto);
        m_chooser.addOption("My Auto", kCustomAuto);

        SmartDashboard.putData("Auto choices", m_chooser);
    }

    // Runs once when the robot starts
    @Override
    public void robotInit() {
        m_robotContainer = new RobotContainer();
    }

    // Runs every 20 milliseconds
    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void autonomousInit() {
        m_autoSelected = m_chooser.getSelected();
        System.out.println("Auto selected: " + m_autoSelected);
    }

    @Override
    public void autonomousPeriodic() {
        switch (m_autoSelected) {
            case kCustomAuto:
                break;

            case kDefaultAuto:
            default:
                break;
        }
    }

    @Override
    public void teleopInit() {}

    @Override
    public void teleopPeriodic() {}

    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    @Override
    public void testInit() {}

    @Override
    public void testPeriodic() {}

    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}
}
