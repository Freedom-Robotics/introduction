// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// Credit to Team 5338 for any changes to the base command XRP template.

package frc.robot.commands;

import frc.robot.RobotContainer;
import frc.robot.subsystems.Drivetrain;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;

/** A file that should contain all the commands that relate to the drivetrain. */
public class DriveCommands {

  /**
   * Method that causes the XRP to drive a certain distance
   * @param distance Distance that the XRP drives in inches
   * @param drive The drivetrain subsystem to use in the command.
   * @return Command that causes the XRP to execute the functions below
   */
  public static Command driveDistance(double distance, Drivetrain drive){
    //A functional command is returned. A functional command is a basic command with a constructor with parameters for initialization code, execution code, ending code, a boolean supplier to cause the command to end, and required subsystems.
    return new FunctionalCommand(
      /* 
       * The arrow -> causes the code to be what is called a "lambda". This means that the code is considered to be a method without needing to define it like normal.
       * If you want to make multiple lines of code to occur, you can use 
       * () -> {
       *  line1; 
       *  line2; 
       *  line3;
       * }
      */
      //The first parameter is what happens upon initialization of the command, aka what should prepare the robot for the command to occur.
      //Causes the XRP to stop to prepare for movement, and resets the sensors that detect how much the wheels have turned.
      () -> {
        drive.arcadeDrive(0, 0); 
        drive.resetEncoders();
      }, 
      
      //The second parameter is what is called every time the scheduler runs while the command is scheduled., aka what the main portion of the command is.
      //Causes the XRP to drive.
      () -> drive.arcadeDrive(1, 0),

      //The third parameter is what happens upon ending the command.
      //Causes the XRP to stop.
      interrupted -> drive.arcadeDrive(0, 0),

      //The fourth parameter is the boolean that determines if the command ended.
      //Checks if the average distance traveled by both of the wheels is at least the distance the XRP needs to travel.
      () -> Math.abs(drive.getAverageDistanceInch()) >= distance,

      //The fifth parameter onwards are the required subsystems. This means that the command doesn't occur if there is a conflicting command that uses the same subsystem.
      //Sets the drivetrain as a requirement.
      drive
      );
  }

  /**
   * Alternate implementation of the above method using a class instead of a command. Slightly more verbose, but easier to read.
   * Our team usually uses code that looks like the above command.
   * Usage is basically the same, however add new in front of the function call.
   * Example: new DriveCommands.AltDriveDistance(5)
   * NOTE: Class Commands do not extend FunctionalCommand, however Function Commands do return FunctionalCommand.
   */
  public static class AltDriveDistance extends Command{
    /**
     * Distance in inches the command makes the XRP move.
     */
    private double distance;
    /**
     * 
     */
    private Drivetrain drive;
    /**
     * Constructor, or what parameters the code takes in.
     * @param distance The distance the XRP drives in inches
     * @param drive The drivetrain subsystem to use in the command.
     */
    public AltDriveDistance(double distance, Drivetrain drive){
      //Adds drivetrain as a requirement. This means that the command doesn't occur if there is a conflicting command that uses the same subsystem.
      //Copy and paste the command for more subsystems.
      addRequirements(drive);
      this.distance = distance;
      this.drive = drive;

    }

    /** 
     * Called when the command is initially scheduled.
     * Sets the XRP's speed to zero to prepare it for moving, and resets the sensors that detect how much the wheels have turned.
     */
    @Override
    public void initialize() {
      drive.arcadeDrive(0, 0);
      drive.resetEncoders();
    }

    /** 
     * Called every time the scheduler runs while the command is scheduled.
     * Makes the XRP drive forward.
     */
    @Override
    public void execute() {
      drive.arcadeDrive(1, 0);
    }

    /** 
     * Called once the command ends or is interrupted.
     * Stops the XRP.
     */
    @Override
    public void end(boolean interrupted) {
      drive.arcadeDrive(0, 0);
    }

    /** 
     * Returns true when the command should end.
     * Checks if the average distance traveled by the XRP's wheels is high enough.
     */
    @Override
    public boolean isFinished() {
      return Math.abs(drive.getAverageDistanceInch()) >= distance;
    }

  }

  /**
   * A command that sets the speed of the robot based on an arcade control scheme, using an Instant Command.
   * Because this value is something that changes based on controller input, we want the values to be rechecked periodically.
   * For this, we use what is called a supplier, which is any function which returns a double.
   * By using a supplier, instead of the drive speed being a constant 0, the command instead runs the supplier each time it runs to determine the correct speed.
   * @param forwardSpeed Speed the robot goes forward. Is a supplier, and therefore must be a method or a lambda.
   * @param turnSpeed Speed the robot turns. Is a supplier, and therefore must be a method or a lambda.
   * @param drive The drivetrain subsystem to use in the command.
   * @return Command to arcade drive the XRP
   */
  public static Command arcadeDriveCommand(Supplier<Double> forwardSpeed, Supplier<Double> turnSpeed, Drivetrain drive){
    //This uses an InstantCommand, which shouldn't be a class. An Instant Command immediately executes, and only takes in fields for what it should do
    //and the required subsystems.
    //Useful for simple commands.
    return new InstantCommand(
      //Tells the XRP to drive at the given speeds by using .get(), which gets the value returned by the supplier
      ()-> drive.arcadeDrive(forwardSpeed.get(), turnSpeed.get()), 
      drive
      );
  }

  public static Command tankDriveCommand(DoubleSupplier leftSupplier, DoubleSupplier rightSupplier, Drivetrain drive){
    throw new UnsupportedOperationException("Unimplemented method 'tankDriveCommand'");
  }

  public static class TurnDegrees extends Command {
    private final double m_degrees;
    private final double m_speed;
    private final Drivetrain m_drive;
    /**
     * Creates a new TurnDegrees. This command will turn your robot for a desired rotation (in
     * degrees) and rotational speed.
     *
     * @param speed The speed which the robot will drive. Negative is in reverse.
     * @param degrees Degrees to turn. Leverages encoders to compare distance.
     * @param drive The drivetrain subsystem to use in the command.
     */
    public TurnDegrees(double speed, double degrees, Drivetrain drive) {
      m_degrees = degrees;
      m_speed = speed;
      m_drive = drive;
      addRequirements(m_drive);
    }
  
    // Called when the command is initially scheduled.
    @Override
    public void initialize() {
      // Set motors to stop, read encoder values for starting point
      m_drive.arcadeDrive(0, 0);
      m_drive.resetEncoders();
    }
  
    // Called every time the scheduler runs while the command is scheduled.
    @Override
    public void execute() {
      m_drive.arcadeDrive(0, m_speed);
    }
  
    // Called once the command ends or is interrupted.
    @Override
    public void end(boolean interrupted) {
      m_drive.arcadeDrive(0, 0);
    }
  
    // Returns true when the command should end.
    @Override
    public boolean isFinished() {
      /* Need to convert distance travelled to degrees. The Standard
         XRP Chassis found here, https://www.sparkfun.com/products/22230,
         has a wheel placement diameter (163 mm) - width of the wheel (8 mm) = 155 mm
         or 6.102 inches. We then take into consideration the width of the tires.
      */
      double inchPerDegree = Math.PI * 6.102 / 360;
      // Compare distance travelled from start to distance based on degree turn
      return m_drive.getAverageTurningDistance() >= (inchPerDegree * m_degrees);
    }
  
  }

  public static Command altTurnDegrees(double speed, double degrees, Drivetrain drive){
    return new TurnDegrees(speed, degrees, drive);
  }

  /**
   * Commands can also a part of command groups, which are sets of commands that run in certain ways. Sequential Command Groups
   * are commands that execute one after another. This is useful for auto behavior.
   * @param drive The drivetrain subsystem to use in the command.
   * @return A command that drives forward 5 inches, turns 90 degrees, and drives forward 4 inches.
   */
  public static Command sequentialExampleCommand(Drivetrain drive){
    return new SequentialCommandGroup(
      driveDistance(5, drive),
      new DriveCommands.TurnDegrees(1, 90, drive),
      driveDistance(4, drive)
    );
  }
}
