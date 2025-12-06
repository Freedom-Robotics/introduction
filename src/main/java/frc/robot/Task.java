package frc.robot;

/**
 * An enum to list all the tasks that need to be completed. You can safely
 * ignore this file.
 */
public enum Task {
    EXAMPLE_COMMAND(
            "A message will be printed indicating the start of the Command. Every 20ms, another message will be printed. After 5 seconds, a final message will be printed indicating the Command has ended."),
    TASK_1("Until a random number between 0-1 is greater than 0.95, a variable will be incremented by 1 every 20ms and its value printed to the console."),
    TASK_2("If a random number between 0-1 is greater than 0.5, the Command from task 1 is scheduled, otherwise 'Task 2 Instant Command Executed' is printed to the console."),
    TASK_3("'Task 3 Starting' will be printed. Then, the Command from Task 1 is scheduled and runs up to 5 seconds. Then, the XRP drives forward 5 inches."),
    EXAMPLE_TRIGGER(
            "Every 20ms, a random number is generated. If a number greater than 0.5 is generated 5 times in a row(aka 5 periods, or 20ms * 5 = 100ms), 'The random number was over 0.5!' is printed to the console."),
    TASK_4("While the B button is held on a connected controller, the XRP drives back and forth up to 5 inches, waiting 1 second between each move."),
    TASK_5("While Task 5 is selected from the dashboard, the left stick Y axis controls forward and backward movement and the right stick X axis controls turning."),
    TASK_5_BONUS("While Task 5 Bonus is selected from the dashboard, the left stick Y axis controls the left motor and the right stick Y axis controls the right motor."),
    TASK_6("Prints out the angle of the Servo, then sets the angle of the servo to 90 degrees, then prints out the angle again."),
    TASK_7("The Servo will move to each of its presets(0, 45, 90, 135, 180) in order, waiting 1 second between each move."),
    TASK_8("The Servo arm will move up or down based on controller input"),
    TASK_9("The robot will print the distance between the rangefinder and the object in front of it every 20ms."),
    TASK_10("The robot will drive forward unless an object is detected within 6 inches, in which case it will stop."),
    ADV_TASK_A("Robot will maintain a distance of 6 inches with an object in front of it."),
    ADV_TASK_1("Will print alliance color.");

    public String shouldHappen;

    Task(String message) {
        this.shouldHappen = message;
    }

    @Override
    public String toString() {
        return toCamelCase(toCamelCase(super.toString()));
    }

    private static String toCamelCase(String s) {
        String[] parts = s.split("_");
        String camelCaseString = "";
        for (String part : parts) {
            camelCaseString = camelCaseString + toProperCase(part) + " ";
        }
        return camelCaseString.substring(0, camelCaseString.length() - 1);
    }

    private static String toProperCase(String s) {
        return s.substring(0, 1).toUpperCase() +
                s.substring(1).toLowerCase();
    }

}
