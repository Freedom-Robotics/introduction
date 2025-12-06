// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ExampleCommands;
import frc.robot.commands.ServoCommands;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Servo;
import frc.robot.subsystems.Rangefinder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.RunCommand;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final Drivetrain drive = new Drivetrain();
  private final SendableChooser<Task> chooser = new SendableChooser<>();
  private final Servo servo = new Servo();
  private final Rangefinder rangefinder = new Rangefinder();

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the button bindings
    for (Task task : Task.values()) {
      chooser.addOption(task.toString(), task);
    }
    SmartDashboard.putData("Task Choice", chooser);
    configureButtonBindings();
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be
   * created by
   * instantiating a {@link edu.wpi.first.wpilibj.GenericHID} or one of its
   * subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing
   * it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
    Bindings.configure(drive, servo, () -> chooser.getSelected());
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    SmartDashboard.putString("What should happen:", chooser.getSelected().shouldHappen);
    System.out.println(chooser.getSelected().shouldHappen);
    System.out.println("---------------------\n---------------------");
    return switch (chooser.getSelected()) {
      case EXAMPLE_COMMAND -> ExampleCommands.exampleFunctionalCommand();
      case TASK_1 -> ExampleCommands.task1Command();
      case TASK_2 -> ExampleCommands.task2Command();
      case TASK_3 -> ExampleCommands.task3Command(drive);
      case TASK_6 -> Commands.runOnce(() -> {
        System.out.println(servo.getAngle());
        servo.setAngle(90);
        System.out.println(servo.getAngle());
      });
      case TASK_7 -> Commands.sequence(
          ServoCommands.moveArmToPreset(0, servo),
          Commands.waitSeconds(1),
          ServoCommands.moveArmToPreset(1, servo),
          Commands.waitSeconds(1),
          ServoCommands.moveArmToPreset(2, servo),
          Commands.waitSeconds(1),
          ServoCommands.moveArmToPreset(3, servo));
      case TASK_9 -> new RunCommand(() -> System.out.println(rangefinder.getDistanceInches())).until(() -> chooser.getSelected()!=Task.TASK_9);
      case TASK_10 -> ExampleCommands.task10Command(drive, rangefinder);
      case ADV_TASK_A -> ExampleCommands.advtaskACommand(drive, rangefinder);
      case ADV_TASK_1 -> advtask1Command();
      default -> new InstantCommand();
    };
  }

  /*
     * TODO: Complete Adv Task 1
     * 
     * Delete the UnsupportedOperationException.
     * 
     * This method should return a command print the alliance color seen in driver station.
     * If you didn't figure this out, you need to research the methods to use.
     * 
     * Change return value to desired command type.
     * 
     */
    public static Command advtask1Command() {
      // Replace the line below with your code
      throw new UnsupportedOperationException("You haven't completed Adv Task 1!");
  }
}
