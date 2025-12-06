// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Rangefinder;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.ConditionalCommand;
import edu.wpi.first.wpilibj2.command.FunctionalCommand;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.PrintCommand;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.DriveCommands;

/** Class for writing basic Commands */
public class ExampleCommands {
    /**
     * An example of a Command that uses a FunctionalCommand. This Command will run
     * for 5 seconds, after which it will end.
     * 
     * @param drivetrain The drivetrain subsystem to use in the command. If another
     *                   Command that requires the drive is scheduled, this Command
     *                   ends.
     * 
     * @return A FunctionalCommand to be scheduled when necessary
     */
    private static long startTime = 0;

    public static Command exampleFunctionalCommand(Drivetrain drivetrain) {
        return new FunctionalCommand(() -> {
            System.out.println("This gets printed once on initialization");
            startTime = System.currentTimeMillis();
        }, () -> {
            System.out.println("This gets printed every 20ms while the command is active");
        }, (interrupted) -> { // IMPORTANT: Make sure to include the (interrupted) parameter, even if you
                              // don't use it. Otherwise, your code will start throwing a bunch of errors.
            System.out.println("This gets printed when the Command ends.");
        }, () -> {
            return (System.currentTimeMillis() - startTime) / 1000 == 5;
            // This condition is run every 20ms to check if the Command should end.
            // This Command will end after 5 seconds.
            // This can be replaced with any other boolean condition. REMEMBER TO RETURN A
            // BOOLEAN, otherwise the code will throw an error.
        }, drivetrain);
    }

    /*
     * TODO: Complete Task 1
     * 
     * Delete the UnsupportedOperationException.
     * 
     * Write amd return a Command to set exampleVar to 0 when the command starts.
     * The Command
     * should, every 20ms, increment exampleVar by 1 and print its value.
     * 
     * NOTE: This Command needs to be able to be run multiple times with the
     * intended output each time, so look out for a secret pitfall that could cause
     * this code to break after the first run.
     * 
     * This Command should also generate a random number every 20ms between
     * 0-1. If that number is greater than 0.95, the command should end.
     * 
     * Constructors:
     * - FunctionalCommand(Runnable onInit, Runnable onExecute, Consumer<Boolean>
     * onEnd, BooleanSupplier isFinished)
     * 
     * Methods:
     * - Math.random() (generates a random number between 0-1)
     */
    static int exampleVar = 1432913;

    public static FunctionalCommand task1Command() {
        // Replace the line below with your code
        return new FunctionalCommand(
            () -> {
                //sets exampleVar to 0 when task1Command is first called
                exampleVar=0;
            },
            () -> {
                //adds 1 to exampleVar every single loop of the robot (20ms by default)
                //then prints exampleVar
                exampleVar++;
                System.out.println(exampleVar);
            },
            //does nothing on end
            (interrupted) -> {},
            () -> {
                //5% chance of stopping
                double probability = Math.random();
                return (probability>=0.95);
            }
        );
    }

    /*
     * TODO: Complete Task 2
     * 
     * Delete the UnsupportedOperationException.
     * 
     * Write and return a Conditional Command that, if a random number between 0-1
     * is greater
     * than 0.5, schedules your task1Command, and otherwise schedules an
     * InstantCommand that prints "Task 2 Instant Command Executed" to the console.
     * 
     * Constructors:
     * - ConditionalCommand(Command onTrue, Command onFalse, BooleanSupplier
     * condition)
     * - InstantCommand(Runnable toRun)
     * 
     * Methods:
     * - Math.random() (generates a random number between 0-1)
     * 
     * Do not use a PrintCommand for this, because a PrintCommand would print the
     * same value every single time.
     */
    public static ConditionalCommand task2Command() {
        // Replace the line below with your code
        return new ConditionalCommand(
            //condition true do this
            task1Command(), 
            //condition false do this
            new InstantCommand(
                ()->{System.out.println("Task 2 Instant Command Executed");}
            ), 
            //condition
            () -> {
                double probability = Math.random();
                return (probability>=0.5);
            }
            );
    }

    /*
     * TODO: Complete Task 3
     * 
     * Delete the UnsupportedOperationException.
     * 
     * Write and return a SequentialCommandGroup that runs the following:
     * 1. An InstantCommand that prints "Task 3 Starting" to the console.
     * 2. Your task1Command in parallel with a WaitCommand of 3 seconds. Whenever
     * either ends, the other should as well immediately.
     * 3. Uses DriveCommands.driveDistance to the XRP forward 5 inches.
     * 
     * If you know of the other type of command that can replace the InstantCommand,
     * feel free to use that instead
     * 
     * Constructors
     * - SequentialCommandGroup(Command... commands) NOTE: This constructor is
     * really similar to the constructor necessary for the command described in step
     * 2.
     * - WaitCommand(double seconds)
     * - InstantCommand(Runnable toRun)
     * 
     * Methods:
     * - DriveCommands.driveDistance(double distance, Drivetrain drive)
     * 
     * Hint: It almost feels like the Commands that should run in parallel are
     * racing...
     */
    public static SequentialCommandGroup task3Command(Drivetrain drive) {
        // Replace the line below with your code
        
        throw new UnsupportedOperationException("You haven't completed Task 3!");
    }

    /*
     * TODO: Complete Task 10
     * 
     * Delete the UnsupportedOperationException.
     * 
     * Write and return a functional command that will drive the robot forward until
     * the rangefinder distance is less than 10 inches.
     * You may want to create additional variables to assist you in your code.
     * 
     * NOTE: This Command needs to be able to be run multiple times with the
     * intended output each time, so reset any created variables.
     * 
     * Constructors:
     * - FunctionalCommand(Runnable onInit, Runnable onExecute, Consumer<Boolean>
     * onEnd, BooleanSupplier isFinished)
     * 
     * Methods:
     * - Rangefinder.getDistanceInches()
     * - Drivetrain.arcadeDriveCommand(Supplier<Double> forwardSpeed,
     * Supplier<Double> turnSpeed, Drivetrain drive)
     */
    public static FunctionalCommand task10Command(Drivetrain drive, Rangefinder rangefinder) {
        // Replace the line below with your code
        throw new UnsupportedOperationException("You haven't completed Task 10!");
    }

    /*
     * TODO: Complete Adv Task A
     * 
     * Delete the UnsupportedOperationException.
     * 
     * This method should return a command that when run, will make the xrp maintain
     * a distance of 6 inches with an object in front of the range finder. It should
     * move back and forward
     * with the object.
     * 
     * This should utilize PID for positional accuracy.
     * https://docs.wpilib.org/en/stable/docs/software/advanced-controls/controllers/pidcontroller.html
     * 
     * 
     * Change the return type of the function to the appropriate command.
     * 
     */
    public static Command advtaskACommand(Drivetrain drive, Rangefinder rangefinder) {
        // Replace the line below with your code
        throw new UnsupportedOperationException("You haven't completed Adv Task A!");
    }

}
