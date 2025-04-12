package com.ood.parkingLot;

import com.ood.parkingLot.configuration.Resilience4jConfig;
import com.ood.parkingLot.enums.ParkingSpotType;
import com.ood.parkingLot.enums.VehicleType;
import com.ood.parkingLot.exception.ParkingSpotNotFoundException;
import com.ood.parkingLot.factory.ParkingSpotFactory;
import com.ood.parkingLot.model.Account;
import com.ood.parkingLot.model.ParkingSpot;
import com.ood.parkingLot.model.Price;
import com.ood.parkingLot.model.Ticket;
import com.ood.parkingLot.payment.CardPayment;
import com.ood.parkingLot.payment.PaymentContext;
import com.ood.parkingLot.payment.PaymentStrategy;
import com.ood.parkingLot.payment.UPIPayment;
import com.ood.parkingLot.service.*;
import com.ood.parkingLot.tarrifcalculator.FixedRateStrategy;
import com.ood.parkingLot.tarrifcalculator.ProgressiveRateStrategy;
import com.ood.parkingLot.tarrifcalculator.TariffCalculator;
import com.ood.parkingLot.tarrifcalculator.TariffCalculatorStrategy;
import io.github.resilience4j.retry.Retry;

import java.math.BigDecimal;
import java.util.*;
import java.util.function.Supplier;

/**
 * Hello world!
 */
public class App {

    //Queue<ParkingSpot> availableParkingSpots;
    Map<UUID, ParkingSpot> parkingSpotsMap;
    Map<ParkingSpotType, Queue<ParkingSpot>> availableParkingSpots;
    Map<ParkingSpotType, Price> priceMap;
    List<ParkingSpot> parkingSpotList = new ArrayList<>();
    ExitGate exitGate = null;
    String registeredVehicleNumber = null;
    TariffCalculator tariffCalculator = null;
    TariffCalculatorStrategy tariffCalculatorStrategy = null;
    PaymentStrategy paymentStrategy = null;
    PaymentContext paymentContext = null;

    App() {
        //  availableParkingSpots = new LinkedList<>();
        parkingSpotsMap = new HashMap<>();
        availableParkingSpots = new HashMap<>();
        priceMap = new HashMap<>();
    }



    public void display() {

        int cols = 5;
        System.out.println("BOARD:");
        for (int i = 0; i < parkingSpotList.size(); i++) {
            ParkingSpot parkingSpot = parkingSpotList.get(i);
            String parkingSpotId = parkingSpot.getParkingSpotId().toString().substring(0, 4);
            String isAvailable = parkingSpot.getVehicleId() == null? "A": "R";
            ParkingSpotType parkingSpotType = parkingSpot.getParkingSpotType();
            String spotType = null;
            switch (parkingSpotType) {
                case CAR:
                    spotType = "C" ;
                    break;
                case MOTORCYCLE:
                    spotType = "M" ;
                    break;
                case TRUCK:
                    spotType = "T" ;
                    break;
            }

            System.out.print(parkingSpotId+"|"+isAvailable+"|"+spotType +" ");
            // Print a newline after every 'cols' elements
            if ((i + 1) % cols == 0) {
                System.out.println();
            }
        }

    }

    void initialization() {

        priceMap.put(ParkingSpotType.CAR, new Price(new BigDecimal("5.24"), Currency.getInstance("USD")));
        priceMap.put(ParkingSpotType.MOTORCYCLE, new Price(new BigDecimal("2.0"), Currency.getInstance("USD")));
        priceMap.put(ParkingSpotType.TRUCK, new Price(new BigDecimal("8.30"), Currency.getInstance("USD")));
        ParkingSpotType[] parkingSpotTypes = ParkingSpotType.values();

        ParkingSpotFactory parkingSpotFactory = new ParkingSpotFactory();
        for (int i = 0; i < 20; i++) {

            int parkingSpotId = new Random().nextInt(ParkingSpotType.values().length);
            ParkingSpotType parkingSpotType = parkingSpotTypes[parkingSpotId];
            ParkingSpot parkingSpot = parkingSpotFactory.getParkingSpot(parkingSpotType, priceMap.get(parkingSpotType));

            //availableParkingSpots.add(parkingSpot);
            parkingSpotList.add(parkingSpot);
            parkingSpotsMap.put(parkingSpot.getParkingSpotId(), parkingSpot);

            Queue<ParkingSpot> parkingSpots = availableParkingSpots.getOrDefault(parkingSpotType, new LinkedList<>());
            parkingSpots.add(parkingSpot);
            availableParkingSpots.put(parkingSpotType, parkingSpots);
        }
    }

    TariffCalculatorStrategy getTariffCalStrategy(ParkingSpotManager parkingSpotManager, Scanner scanner) {
        System.out.println("Enter tariff calculation method: ");
        System.out.println("Select an option:");
        System.out.println("1. FixedRate");
        System.out.println("2. Progressive");
        System.out.print("Enter your choice: ");
        int tariffCalStrategy = scanner.nextInt();

        switch (tariffCalStrategy) {
            case 1:
                return new FixedRateStrategy(parkingSpotManager);
            case 2:
                return new ProgressiveRateStrategy(parkingSpotManager);
            default:
                throw new IllegalArgumentException("Invalid tariff calculation method.");
        }
    }

    PaymentStrategy getPaymentStrategy(Scanner scanner) {
        System.out.println("Enter payment strategy: ");
        System.out.println("Select an option:");
        System.out.println("1. UPI");
        System.out.println("2. CARD PAYMENT");
        System.out.print("Enter your choice: ");
        int paymentStrategy = scanner.nextInt();
        switch (paymentStrategy) {
            case 1:
                return new UPIPayment();
            case 2:
                return new CardPayment();
            default:
                throw new IllegalArgumentException("Invalid payment strategy.");
        }
    }

    private ParkingSpotType getParkingSpotType(Scanner scanner) {
        System.out.println("Enter parking spot type: ");
        System.out.println("Select an option:");
        System.out.println("1. CAR");
        System.out.println("2. MOTORCYCLE");
        System.out.println("3. TRUCK");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");

        int parkingSpotType = scanner.nextInt();
        switch (parkingSpotType) {
            case 1:
                return ParkingSpotType.CAR;
            case 2:
                return ParkingSpotType.MOTORCYCLE;
            case 3:
                return ParkingSpotType.TRUCK;
            default:
                throw new IllegalArgumentException("Invalid parking spot type.");
        }
    }

    String getRegisteredVehicleNumber(Scanner scanner) {
        System.out.print("Enter vehicle number: ");
        return scanner.next();
    }

    public void unparkVehicle(String registeredVehicleNumber ) throws InterruptedException {
        exitGate.unparkVehicle(registeredVehicleNumber);
    }

    public void run() throws InterruptedException {

        ParkingSpotManager parkingSpotManager = new CarSpotManager(parkingSpotsMap, availableParkingSpots);
        //AccountService accountService = new AccountService();
        Account account = Account.builder().build();
        Resilience4jConfig resilience4jConfig = new Resilience4jConfig();
        Retry retry = resilience4jConfig.getRetry();

        EntryGate entryGate = new EntryGateImpl(parkingSpotManager);


        Scanner scanner = new Scanner(System.in);
        int choice;
        do {
            // Display menu options
            System.out.println("Select an option:");
            System.out.println("1. Park vehicle");
            System.out.println("2. Unpark vehicle");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            // Read user choice
            choice = scanner.nextInt();

            switch (choice) {
                case 1:

                    ParkingSpotType parkingSpotType = getParkingSpotType(scanner);
                    registeredVehicleNumber = getRegisteredVehicleNumber(scanner);
                    UUID parkingSpotId = null;
                    Queue<ParkingSpot> parkingSpots;
                    do {
                        try{
                            parkingSpots = entryGate.getParkingSpotsByParkingSpotType(parkingSpotType);
                        }catch (ParkingSpotNotFoundException e){
                            System.out.println("No parking spots found. please try again.");
                            break;
                        }
                        parkingSpotId = Objects.requireNonNull(parkingSpots.poll()).getParkingSpotId();
                        Ticket ticket = entryGate.generateTicket(registeredVehicleNumber, parkingSpotId);
                        if (ticket != null) {
                            System.out.println("Ticket created: " + ticket);
                            break;
                        }
                    } while (!parkingSpots.isEmpty());
                    break;
                case 2:

                    registeredVehicleNumber = getRegisteredVehicleNumber(scanner);

                    tariffCalculatorStrategy = getTariffCalStrategy(parkingSpotManager, scanner);
                    paymentStrategy = getPaymentStrategy(scanner);

                    tariffCalculator = new TariffCalculator(tariffCalculatorStrategy);
                    paymentContext = new PaymentContext(paymentStrategy);
                    exitGate = new ExitGateServiceImpl(parkingSpotManager, tariffCalculator, paymentContext);


                    Ticket ticket = exitGate.getTicketByRegisteredVehicleNumber(registeredVehicleNumber);

                    BigDecimal amountTobePaid = exitGate.calculateTariff(ticket);

                    exitGate.processPayment(amountTobePaid, account);

                    Supplier<String> supplier = Retry.decorateSupplier(retry, () ->{
                        try {
                            unparkVehicle(registeredVehicleNumber);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                        return "Success!";
                    });

                    // Execute the decorated supplier
                    try {
                        System.out.println(supplier.get());
                    } catch (Exception e) {
                        System.out.println("Retries exhausted to unpark vehicle do manually unpark: " + e.getMessage());
                    }

                    System.out.println("exit processed.");
                case 3:
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

            display();
            System.out.println(); // Add a blank line for readability
        } while (choice != 3);

        scanner.close();


        while (true) {


        }
    }


    public static void main(String[] args) throws InterruptedException {

        App app = new App();
        app.initialization();
        app.display();
        app.run();


    }


}
