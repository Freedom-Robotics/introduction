package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Servo;

public class ServoCommands {
    /*
     * TODO: Complete Task 7
     * 
     * Write a Command that moves the arm up while scheduled, and another that moves
     * the arm down while scheduled. Then, write a third command that moves the arm
     * to a preset from Constants.servoPresets and ends when the arm reaches the
     * preset.
     * 
     * Note: You cannot just set the arm's position to be the preset or the max/min
     * value and then check the angle, because the arm update's its position
     * immediately while in reality it takes time to move.
     * 
     * Instead, you will need to use Constants.servoSecondsPerDegree to calculate
     * the time it will take for the servo to reach its target, and use that to
     * determine the behavior of the command.
     * 
     * Hint: Moving the arm up and down is essentially setting the angle to the max
     * position the arm can be in either direction after 20 ms(every period frame).
     * How would you calculate this using the constants you are given?
     */
    public static Command moveArmUp(Servo arm) {
        // Replace the line below with your code
        throw new UnsupportedOperationException("Unimplemented method 'moveArmUp'");
    }

    public static Command moveArmDown(Servo arm) {
        // Replace the line below with your code
        throw new UnsupportedOperationException("Unimplemented method 'moveArmDown'");
    }

    /*
     * Hint: You will need a variable to store the time at which the command should
     * be finished. Feel free to use this one, but make sure to reset it each time.
     */
    public static double startingMovementTime = 0;

    public static Command moveArmToPreset(int preset, Servo arm) {
        // Replace the line below with your code
        throw new UnsupportedOperationException("Unimplemented method 'moveArmToPreset'");
    }
}
