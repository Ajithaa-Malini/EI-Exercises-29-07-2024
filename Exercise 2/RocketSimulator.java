package RocketLaunchSimulator;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;


class RocketSimulator {
    private static final int INITIAL_FUEL = 150; // Initial fuel
    private static final int FUEL_CONSUMPTION_RATE = 10; // Fuel consumption per second
    private static final int ALTITUDE_INCREMENT = 100; // Altitude increment per second (100 km per second)
    private static final int STAGE_1_DURATION = 15; // Duration of Stage 1 in seconds
    private static final int SPACE_STATION_DISTANCE = 200; // Distance to space station in km
    private static final int MIN_DISTANCE_TO_REQUEST_FUEL = 100; // Minimum distance to request fuel in km
    private static final int ORBIT_ALTITUDE = 200; // Altitude required for orbit in km

    private int fuel = INITIAL_FUEL;
    private int altitude = 0;
    private int speed = 0; // Speed in km/s
    private String stage = "Pre-Launch";
    private boolean missionInProgress = false;
    private Timer timer;
    private int elapsedTime = 0;
    private Scanner scanner = new Scanner(System.in);

    public void start() {
        System.out.println("\t\t\t\t\tRocket Launch Simulator");
        System.out.println("\n\nCommands: start_checks, launch, fast_forward X");

        // Ask user for distance to be traveled per hour
        System.out.print("Enter the distance to be traveled per hour (in km): ");
        int distancePerHour = 0;
        try {
            distancePerHour = scanner.nextInt();
            if (distancePerHour <= 0) {
                throw new IllegalArgumentException("Distance must be a positive number.");
            }
        } catch (Exception e) {
            System.out.println("Invalid input. Please enter a positive integer for distance.");
            scanner.next(); // Clear the invalid input
            return;
        }
        speed = distancePerHour / 3600; // Calculate speed in km/s
        scanner.nextLine(); // Consume the remaining newline character

        while (true) {
            System.out.println("\n\nCommands: start_checks, launch, fast_forward X");
            System.out.print("Enter command: ");
            String command = scanner.nextLine();

            if (command.equals("start_checks")) {
                preLaunchChecks();
            } else if (command.equals("launch")) {
                if (stage.equals("Pre-Launch")) {
                    startLaunch();
                } else {
                    System.out.println("Already launched or invalid command.");
                }
            } else if (command.startsWith("fast_forward")) {
                try {
                    int seconds = Integer.parseInt(command.split(" ")[1]);
                    if (seconds <= 0) {
                        throw new IllegalArgumentException("Time must be a positive integer.");
                    }
                    fastForward(seconds);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid time format. Use fast_forward X.");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            } else {
                System.out.println("Unknown command.");
            }
        }
    }

    private void preLaunchChecks() {
        System.out.println("Pre-Launch Checks:");
        System.out.printf("Stage: %s\n", stage);
        System.out.printf("Fuel: %d%%\n", fuel);
        System.out.printf("Altitude: %d km\n", altitude);
        System.out.printf("Speed: %d km/h\n", speed * 3600); // Convert km/s to km/h
        System.out.println("All systems are 'Go' for launch.");
    }

    private void startLaunch() {
        System.out.println("Launch initiated.");
        stage = "Stage 1";
        missionInProgress = true;
        timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                if (fuel <= 0) {
                    if (altitude >= MIN_DISTANCE_TO_REQUEST_FUEL) {
                        requestFuel();
                    } else {
                        missionFailure();
                        timer.cancel();
                    }
                } else if (stage.equals("Stage 1") && elapsedTime >= STAGE_1_DURATION) {
                    stageSeparation();
                    stage = "Stage 2";
                } else if (stage.equals("Stage 2") && altitude >= ORBIT_ALTITUDE) {
                    orbitPlacement();
                    timer.cancel();
                } else {
                    updateFlightStatus();
                }
            }
        }, 0, 1000); // Update every second
    }

    private void fastForward(int seconds) {
        if (!missionInProgress) {
            System.out.println("Launch has not started. Use launch command first.");
            return;
        }

        for (int i = 0; i < seconds; i++) {
            if (fuel <= 0) {
                if (altitude >= MIN_DISTANCE_TO_REQUEST_FUEL) {
                    requestFuel();
                } else {
                    missionFailure();
                    return;
                }
            } else if (stage.equals("Stage 1") && elapsedTime >= STAGE_1_DURATION) {
                stageSeparation();
                stage = "Stage 2";
            } else if (stage.equals("Stage 2") && altitude >= ORBIT_ALTITUDE) {
                orbitPlacement();
                return;
            } else {
                elapsedTime++;
                fuel -= FUEL_CONSUMPTION_RATE;
                altitude += ALTITUDE_INCREMENT;
                System.out.printf("Fast Forward: Stage: %s, Fuel: %d%%, Altitude: %d km, Speed: %d km/h\n",
                        stage, fuel, altitude, speed * 3600); // Convert km/s to km/h
            }
        }
    }

    private void updateFlightStatus() {
        elapsedTime++;
        fuel -= FUEL_CONSUMPTION_RATE;
        altitude += ALTITUDE_INCREMENT;
        System.out.printf("Stage: %s, Fuel: %d%%, Altitude: %d km, Speed: %d km/h\n",
                stage, fuel, altitude, speed * 3600); // Convert km/s to km/h
    }

    private void stageSeparation() {
        System.out.println("Stage 1 complete. Separating stage. Entering Stage 2.");
    }

    private void orbitPlacement() {
        System.out.println("Orbit achieved! Mission Successful.");
        System.exit(0); // Terminate the program on mission success
    }

    private void missionFailure() {
        System.out.println("Mission Failed due to insufficient fuel.");
        System.exit(1); // Terminate the program on mission failure
    }

    private void requestFuel() {
        System.out.println("Fuel depleted. Requesting additional fuel from space station...");
        if (altitude < SPACE_STATION_DISTANCE) {
            System.out.println("Space station is too far away to request fuel. Mission Failed.");
            missionFailure();
        } else {
            System.out.println("Space station is reachable. Refueling...");
            fuel = INITIAL_FUEL; // Refill fuel
            System.out.println("Fuel replenished. Continuing the mission.");
        }
    }
}