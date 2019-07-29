public class ParkingLotTest {
	public static void main(String[] args) {
		makeParkingLotTest();
		carParkInParkingLotTest();
		leaveCarTest();
		statusTest();
		carParkInNearestParkingLotTest();
		carParkInFullParkingLotAndShowsParkingLotIsFull();
		showCarRegistrationNumbersForSpecifiedColour();
		showSlotNumbersForSpecifiedColour();
		showSlotNumberForRegistrationNumber();
		System.out.println("All test case passed!!!");
	}
	static void check(String expected,String actual)
	{
		if(!expected.equals(actual))
			throw new RuntimeException(String.format("Error in value,expected: %s,actual: %s",expected,actual));
	}
	private static void makeParkingLotTest()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		check("Created a parking lot with 6 slots",outputString);
	}
	private static void carParkInParkingLotTest()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-1234 White");
		check("Allocated slot number: 1",outputString);
		outputString = parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-9999 White");
		check("Allocated slot number: 2",outputString);
		outputString = parkinglotcontroller.workParserAndGetWorkDone("park KA-01-BB-0001 Black");
		check("Allocated slot number: 3",outputString);
		outputString = parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-7777 Red");
		check("Allocated slot number: 4",outputString);
		outputString = parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-2701 Blue");
		check("Allocated slot number: 5",outputString);
		outputString = parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-3141 Black");
		check("Allocated slot number: 6",outputString);
	}
	private static void leaveCarTest()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-1234 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-9999 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-BB-0001 Black");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-7777 Red");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-2701 Blue");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-3141 Black");
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("leave 4");
		check("Slot number 4 is free",outputString);
		outputString = parkinglotcontroller.workParserAndGetWorkDone("leave 5");
		check("Slot number 5 is free",outputString);
	}
	private static void statusTest()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-1234 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-9999 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-BB-0001 Black");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-7777 Red");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-2701 Blue");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-3141 Black");
		parkinglotcontroller.workParserAndGetWorkDone("leave 4");
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("status");
		check("Slot No. Registration No\n" + 
				"Colour\n" + 
				"1\n" + 
				"KA-01-HH-1234\n" + 
				"White\n" + 
				"2\n" + 
				"KA-01-HH-9999\n" + 
				"White\n" + 
				"3\n" + 
				"KA-01-BB-0001\n" + 
				"Black\n" + 
				"5\n" + 
				"KA-01-HH-2701\n" + 
				"Blue\n" + 
				"6\n" + 
				"KA-01-HH-3141\n" + 
				"Black",outputString);
	}
	private static void carParkInNearestParkingLotTest()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-1234 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-9999 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-BB-0001 Black");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-7777 Red");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-2701 Blue");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-3141 Black");
		parkinglotcontroller.workParserAndGetWorkDone("leave 4");
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("park KA-01-P-333 White");
		check("Allocated slot number: 4",outputString);
	}
	private static void carParkInFullParkingLotAndShowsParkingLotIsFull()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-1234 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-9999 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-BB-0001 Black");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-7777 Red");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-2701 Blue");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-3141 Black");
		parkinglotcontroller.workParserAndGetWorkDone("leave 4");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-P-333 White");
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("park DL-12-AA-9999 White");
		check("Sorry, parking lot is full",outputString);
	}
	private static void showCarRegistrationNumbersForSpecifiedColour()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-1234 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-9999 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-BB-0001 Black");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-7777 Red");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-2701 Blue");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-3141 Black");
		parkinglotcontroller.workParserAndGetWorkDone("leave 4");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-P-333 White");
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("registration_numbers_for_cars_with_colour White");
		check("KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333",outputString);
	}
	private static void showSlotNumbersForSpecifiedColour()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-1234 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-9999 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-BB-0001 Black");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-7777 Red");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-2701 Blue");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-3141 Black");
		parkinglotcontroller.workParserAndGetWorkDone("leave 4");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-P-333 White");
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("slot_numbers_for_cars_with_colour White");
		check("1, 2, 4",outputString);
	}
	private static void showSlotNumberForRegistrationNumber()
	{
		ParkingLotController parkinglotcontroller = new ParkingLotController();
		parkinglotcontroller.workParserAndGetWorkDone("create_parking_lot 6");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-1234 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-9999 White");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-BB-0001 Black");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-7777 Red");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-2701 Blue");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-HH-3141 Black");
		parkinglotcontroller.workParserAndGetWorkDone("leave 4");
		parkinglotcontroller.workParserAndGetWorkDone("park KA-01-P-333 White");
		String outputString = parkinglotcontroller.workParserAndGetWorkDone("slot_number_for_registration_number KA-01-HH-3141");
		check("6",outputString);
		outputString = parkinglotcontroller.workParserAndGetWorkDone("slot_number_for_registration_number MH-04-AY-1111");
		check("Not found",outputString);
	}
}
 