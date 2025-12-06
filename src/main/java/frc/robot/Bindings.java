package frc.robot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.Supplier;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.InstantCommand;
import edu.wpi.first.wpilibj2.command.RepeatCommand;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Servo;
import frc.robot.commands.DriveCommands;
import frc.robot.commands.ExampleCommands;

public class Bindings {
    private static CommandXboxController controller = new CommandXboxController(0);

    private static Set<Trigger> triggers = new HashSet<Trigger>();

    public static void configure(Drivetrain drive,  Servo servo, Supplier<Task> taskSupplier) {
        /**
         * Example of a trigger that is activitated when it generates a random number
         * over 0.5. This condition is checked every 20ms. Also, the trigger is only run
         * when Example Trigger is selected from the dashboard.
         */
        Trigger exampleTrigger = new Trigger(() -> Math.random() > 0.5)
                .and(() -> taskSupplier.get() == Task.EXAMPLE_TRIGGER).debounce(0.1);

        // When the trigger becomes active, a message is printed. If the nested Command was 
        // not instant, the Command would not cancel upon become false.
        exampleTrigger.onTrue(new InstantCommand(() -> System.out.println("The random number was over 0.5!")));

        Trigger task4Trigger = controller.b().and(() -> taskSupplier.get() == Task.TASK_4);
        setupTask4Trigger(task4Trigger, drive);

        new Trigger(() -> taskSupplier.get() == Task.TASK_5 || taskSupplier.get() == Task.TASK_8).whileTrue(task5Control(drive)); //Smart of you
        new Trigger(() -> taskSupplier.get() == Task.TASK_5_BONUS).whileTrue(bonusTask5Control(drive));
        Trigger leftBumper = task8LeftBumper().and(() -> taskSupplier.get() == Task.TASK_8);
        Trigger rightBumper = task8RightBumper().and(() -> taskSupplier.get() == Task.TASK_8);
        setupTask8Triggers(leftBumper, rightBumper, servo);
    }


    /*
     * TODO: Complete Task 4
     * 
     * Write code in this method to set up the task4Trigger.
     * 
     * While the trigger is active, the xrp drivetrain should move forward for five
     * inches, turn around, move five inches, turn around, then
     * repeat infinitely until the trigger is no longer active.
     * 
     * Hint: You can use the RepeatCommand class to repeat a command
     * 
     * Constructors:
     * - RepeatCommand(Command command)
     * - SequentialCommandGroup(Command... commands)
     * 
     * Methods:
     * - DriveCommands.driveDistance(Drivetrain drive, double inches)
     * - Some method of Trigger(oooh, you'll have to remember/look up this one, how
     * fun)
     * - DriveCommands.altTurnDegrees(double speed, double degrees, Drivetrain drive)
     * 
     * Bonus: Use the class based definition of the Command that turns the robot.
     */
    public static void setupTask4Trigger(Trigger task4Trigger, Drivetrain drive) {
        // Replace the line below with your code
        // You haven't completed task 4 yet! I'm not putting an exception here cause 
        // that breaks stuff.
    }

    /*
     * TODO: Complete Task 5
     * 
     * Write code to drive the robot using the joysticks of the Xbox controller
     * using an Arcade Drive Scheme. See:
     * https://frc1756-argos.github.io/ArgoBot-Drive-Training/assets/images/tutorials/2/Arcade_Control.png
     * 
     * Methods:
     * - DriveCommands.arcadeDrive(Supplier<Double> forwardSpeed, Supplier<Double>
     * turnSpeed, Drivetrain drive)
     * - controller.get[joystick][axis]() (ie. getLeftY): Returns a double between
     * -1 and 1 denoting the position of the given joystick axis.
     * 
     * Bonus: Create tank drive control of the robot instead.
     * See: https://xiaoxiae.github.io/Robotics-Simplified-Website/assets/images/drivetrain-control/tank-drive.png
     * 
     * You will need to modify Drivetrain and DriveCommands to do this. 
     * The necessary method definitions are already in both files.
     * 
     * Hint: tankDrive and arcadeDrive are very similar. Thus, it makes sense that
     * you could use a similar structure to the code necessary for arcadeDrive.
     * Additionally, it also makes sense that the methods would be in the same
     * place...
     */
    public static Command task5Control(Drivetrain drive) {
        // Replace the line below with your code
        return new InstantCommand();
    }

    public static Command bonusTask5Control(Drivetrain drive) {
        // Replace the line below with your code
        return new InstantCommand();
    }

    /*
     * TODO: Complete Task 8
     * 
     * Write code to create Triggers that are active when the left bumper is
     * pressed and when the right bumper is pressed.
     * 
     * Then, write code to move the servo up when the left bumper is pressed, and
     * down when the right bumper is pressed.
     * 
     * Methods:
     * - ServoCommands.moveArmUp(Servo arm)
     * - ServoCommands.moveArmDown(Servo arm)
     * - Controller Documentation: 
     * https://github.wpilib.org/allwpilib/docs/release/java/edu/wpi/first/wpilibj2/command/button/CommandXboxController.html
     * 
     */

    public static Trigger task8LeftBumper() {
        // Replace the line below with your code
        return new Trigger(() -> false);
    }

    public static Trigger task8RightBumper() {
        // Replace the line below with your code
        return new Trigger(() -> false);
    }

    public static void setupTask8Triggers(Trigger leftBumper, Trigger rightBumper, Servo servo) {
        // Replace this line with your code
    }
    
}
