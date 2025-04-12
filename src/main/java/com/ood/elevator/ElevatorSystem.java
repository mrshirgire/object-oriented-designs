package com.ood.elevator;

import com.ood.elevator.button.*;
import com.ood.elevator.button.Number;
import com.ood.elevator.dispatcher.DispatchFactory;
import com.ood.elevator.dispatcher.Dispatcher;
import com.ood.elevator.enums.ButtonType;
import com.ood.elevator.enums.Direction;
import com.ood.elevator.enums.DispatcherType;
import com.ood.elevator.model.ElevatorCar;
import com.ood.elevator.model.Floor;
import com.ood.elevator.panel.ExternalPanel;
import com.ood.elevator.panel.InternalPanel;
import com.ood.elevator.panel.Panel;

import java.util.*;
import java.util.List;

public class ElevatorSystem {

    ElevatorController elevatorController;
    Map<Integer, Map<Integer, ExternalPanel>> floorsExternalPanels;
    Dispatcher externalDispatcher;
    Dispatcher internalDispatcher;
    Map<Integer, ElevatorCar> elevatorCars ;

    ElevatorSystem(){
        floorsExternalPanels = new HashMap<>();
        elevatorCars = new HashMap<>();
    }

    public void initialization(Scanner scanner) {

        System.out.println("Please enter the number of elevators in the system: ");
        int numberOfElevators = scanner.nextInt();

        System.out.println("Please enter the number of floors: ");
        int numberOfFloors = scanner.nextInt();

        // initialize floors
        List<Floor> floors = new ArrayList<>();
        for(int floorIdx = 1; floorIdx <= numberOfFloors; floorIdx++) {
            Floor floor = getFloor(numberOfElevators, floorIdx);
            floors.add(floor);
        }

        // initialize elevator car
        Map<Integer, ElevatorCar> elevatorCars = new HashMap<>();
        for(int elevatorId = 1; elevatorId <= numberOfElevators; elevatorId++) {

           ElevatorCar elevatorCar = getElevatorCar(elevatorId, numberOfElevators);
           elevatorCars.put(elevatorId, elevatorCar);
        }

        elevatorController = new ElevatorController(elevatorCars);
        DispatchFactory dispatchFactory = new DispatchFactory();
        externalDispatcher = dispatchFactory.getDispatcher(DispatcherType.EXTERNAL, elevatorController);
        internalDispatcher = dispatchFactory.getDispatcher(DispatcherType.INTERNAL, elevatorController);
    }

    private ElevatorCar getElevatorCar(int elevatorId, int numberOfFloors) {

        List<Button> floorNumberButtons = new ArrayList<>();
        for (int floorIdx = 1; floorIdx <= numberOfFloors; floorIdx++) {
            floorNumberButtons.add(new Number(String.valueOf(floorIdx)));
        }

        Button openDoorButton = new OpenDoorButton(ButtonType.OPEN_DOOR, "OPEN_DOOR");
        Button clouseButton = new CloseDoorButton(ButtonType.CLOSE_DOOR, "CLOSE_DOOR");
        Display display = new Display(-1, null);
        InternalPanel internalPanel = new InternalPanel(elevatorId, floorNumberButtons, openDoorButton, clouseButton);
        return new ElevatorCar(elevatorId, internalPanel, display);
    }

    private Floor getFloor(int numberOfElevators, int floorIdx) {
        List<Display> displays = new ArrayList<>();
        List<Panel> externalPanels = new ArrayList<>();
        for(int elevatorIdx = 1; elevatorIdx <= numberOfElevators; elevatorIdx++) {

            Button upButton = new Up();
            Button downButton = new Down();
            Panel externalPanel = new ExternalPanel(elevatorIdx, upButton, downButton);
            Display display = new Display(-1, null);
            displays.add(display);
            externalPanels.add(externalPanel);
        }

        return new Floor(floorIdx, displays, externalPanels);
    }

    public void elevatorCarOperation(Scanner scanner) {

        System.out.println("Enter the elevator Id: ");
        int elevatorId = scanner.nextInt();


        System.out.println("Enter the floorId where you want to go?");
        int destinationFloorId = scanner.nextInt();

        ElevatorCar elevatorCar = elevatorCars.get(elevatorId);
        Display display = elevatorCar.getDisplay();
        int currentFloor = display.getCurrFloorId();

        if(currentFloor == destinationFloorId) {
            System.out.println("current floor and destination floor are the same");
            return;
        }
        Direction direction;
        if(destinationFloorId < currentFloor) {
            direction = Direction.DOWN;
        }else {
            direction = Direction.UP;
        }

        internalDispatcher.dispatch(elevatorId, destinationFloorId, direction);
    }


    public void floorOperation(Scanner scanner) {
        System.out.println("Enter the current floor: ");
        int floorId = scanner.nextInt();

        System.out.println("Enter the elevator Id: ");
        int elevatorId = scanner.nextInt();


        System.out.println("Where you want to go?");
        System.out.println("Select an option:");
        System.out.println("1. UP");
        System.out.println("2. DOWN");
        System.out.println("3. Exit");
        System.out.print("Enter your choice: ");
        int dir = scanner.nextInt();

        Map<Integer, ExternalPanel> elevatorCarMap = floorsExternalPanels.get(floorId);
        ExternalPanel externalPanel = elevatorCarMap.get(elevatorId);
        if(externalPanel.getUpButton().isPressed()){
            System.out.println("UP button already pressed");
        }

        if(dir == 1) {
            externalPanel.getUpButton().setPressed(true);
            externalDispatcher.dispatch(elevatorId, floorId, Direction.UP);
        }
        if(dir == 2) {
            externalPanel.getDownButton().setPressed(true);
            externalDispatcher.dispatch(elevatorId, floorId, Direction.DOWN);
        }
    }

    public static void main(String[] args) {

        ElevatorSystem elevatorSystem = new ElevatorSystem();
        Scanner scanner = new Scanner(System.in);
        elevatorSystem.initialization(scanner);



    }
}
