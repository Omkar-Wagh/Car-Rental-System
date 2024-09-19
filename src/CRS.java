import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Car1{
    private String model;
    private String  car_id;
    private int rent;
    private boolean isavailable;
    public Car1(String model, String car_id,int rent ){
        this.model=model;
        this.car_id=car_id;
        this.rent=rent;
        this.isavailable=true;
    }
    public String getmodel(){
        return model;
    }
    public String getcar_id(){
        return car_id;
    }
    public int getrent(){
        return rent;
    }
    public int calculate_rent(int days){
        return rent * days;
    }
    public boolean isIsavailable(){
        return isavailable;
    }
    public void return1(){
        isavailable=true;
    }
    public void rent(){
        isavailable=false;
    }

}
class Customer1{
    private String cus_name;
    private String cus_id;
    public Customer1(String cus_name,String cus_id){
        this.cus_name=cus_name;
        this.cus_id=cus_id;
    }
    public String getCus_name(){
        return cus_name;
    }
    public String getCus_id(){
        return cus_id;
    }
}
class Rental1{
    private Car1 car;
    private Customer1 customer;
    int days;
    public Rental1(Car1 car, Customer1 customer, int days) {
        this.car = car;
        this.customer = customer;
        this.days = days;
    }
    public Car1 getCar(){
        return  car;
    }

    public Customer1 getCustomer() {
        return customer;
    }
    public int getDays(){
        return days;
    }
}
class Main1{
    private List<Car1> cars;
    private List<Customer1> customers;
    private List<Rental1> rentals;
    public Main1(){
        cars =new ArrayList<>();
        customers =new ArrayList<>();
        rentals =new ArrayList<>();
    }
    public void addcar(Car1 car){
        cars.add(car);
    }
    public void addcustomer(Customer1 customer){
        customers.add(customer);
    }
    public void rent_car(Car1 car,Customer1 customer, int days) {
        if (car.isIsavailable()) {
            car.rent();
            rentals.add(new Rental1(car, customer, days));

        } else {
            System.out.println("Car is not available for rent.");
        }
    }
    public void  return_car(Car1 car){
        car.return1();
        Rental1 rentaltoremove= null;
        for(Rental1 rental:rentals){
            if(rental.getCar()==car){
                rentaltoremove = rental;
                break;
            }
        }
        if(rentaltoremove != null){
            rentals.remove(rentaltoremove);
        }
    }

    public void rentcar() {
        Main1 main1 = new Main1();
        Scanner scanner1 = new Scanner(System.in);
        System.out.print("enter your name:-");
        String name = scanner1.nextLine();
        System.out.println("Available cars -");
        for (Car1 car : cars) {
            if (car.isIsavailable()) {
                System.out.println(car.getcar_id() + " " + car.getmodel() + " " + car.getrent());
            }
        }
        System.out.print("enter car id you want to rent:-");
        String car_id = scanner1.nextLine();
        System.out.print("enter the no of days for car to be rented:-");
        int days = scanner1.nextInt();
        if (days != 0) {
            Customer1 newCustomer = new Customer1(name, "CUS" + (customers.size() + 1));
            addcustomer(newCustomer);
            Car1 selectedcar = null;
            for (Car1 car : cars) {
                if (car.getcar_id().equals(car_id) && car.isIsavailable()) {
                    selectedcar = car;
                    break;
                }
            }
            if (selectedcar != null) {
                double totalPrice = selectedcar.calculate_rent(days);
                System.out.println("\n== Rental Information ==\n");
                System.out.println("Customer ID: " + newCustomer.getCus_id());
                System.out.println("Customer Name: " + newCustomer.getCus_name());
                System.out.println("Car: " + " " + selectedcar.getmodel());
                System.out.println("Rental Days: " + days);
                System.out.print("Total Price:-" + totalPrice);
                String confirm;
                System.out.print("\nConfirm rental (Y/N):-");
                confirm = scanner1.next();
                if (confirm.equalsIgnoreCase("Y")) {
                    rent_car(selectedcar, newCustomer, days);
                    System.out.println("\nCar rented successfully.");
                } else {
                    System.out.println("\nRental canceled.");
                }
            } else {
                System.out.println("car is not available currently!");
            }
        }else{
            System.out.println("enter valid  no of days");
        }
    }

    public void returncar(){
        /* to remove the rental info we need the both the things car and customer
         both are the object so need specifically one detail of them
         through car_id we can get car (object)
         now we are having one object of rentals i.e(car) so we just need to know  detail of
          car object in rental->ArrayList and at same location we will get the info of customer (object)
          */
        Scanner scanner = new Scanner(System.in);
        System.out.print("enter the car id you want to return:-");
        String car_id = scanner.nextLine();
        Car1 cartoreturn = null;
        for(Car1 car : cars) {
            if (car.getcar_id().equals(car_id) && !car.isIsavailable()) {
                cartoreturn = car;
                break;
            }
        }
        if(cartoreturn != null){
            Customer1 customer = null;
            for(Rental1 rental : rentals){
                if(rental.getCar()==cartoreturn) {
                        /*rental has both the details of car and customer with it
                        using rental we can get both the details  */
                    customer = rental.getCustomer();
                    break;
                }
            }
            if(customer != null){
                return_car(cartoreturn);
                System.out.printf("car return successfully by %s ",customer.getCus_name());
                System.out.println("");
            }else{
                System.out.println("failed to return a car!");
            }
        }
        else{
            System.out.println("no data found for respective car id or customer!");
        }
    }
}
public class CRS {
    static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        Car1 car1 = new Car1("thar","c1",1200);
        Car1 car2 = new Car1("supra","c2",2200);
        Car1 car3 = new Car1("land_rover","c3",1700);
        Main1 main1 = new Main1();
        main1.addcar(car1);
        main1.addcar(car2);
        main1.addcar(car3);
        while(true){
            System.out.println("");
            System.out.println("-----------------------------------");
            System.out.println("CAR RENTAL SYSTEMS WELCOMES YOU!!!");
            System.out.println("1.Rent a Car");
            System.out.println("2.Return a Car");
            System.out.println("3.Exit");
            int choice ;
            System.out.print("Choice:-");
            choice = scanner.nextInt();
            switch (choice){
                case 1:
                    main1.rentcar();
                    break;
                case 2:
                    main1.returncar();
                    break;
                case 3:
                    System.out.println("thanks for using CAR RENTAL SYSTEMS ");
                    System.out.println("-----------------------------------");
                    return;
                default:
                    System.out.println("Enter the valid choice:");
                    break;
            }

        }

    }
}
