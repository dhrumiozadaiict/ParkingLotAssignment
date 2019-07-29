public class ParkingLotController {
	private ParkingLot currentParkingLot;
	String workParserAndGetWorkDone(String s) {
		String[] breakWork = s.split(" ");
		if(breakWork[0].equals("create_parking_lot"))
			return createParkingLot(Integer.valueOf(breakWork[1]));
		else if(breakWork[0].equals("park"))
			return parkCarInParkigLot(breakWork[1],breakWork[2]);
		else if(breakWork[0].equals("leave"))
			return leaveCarFromParkingSlot(Integer.valueOf(breakWork[1]));
		else if(breakWork[0].equals("status"))
			return getCurrentParkingLotStatus();
		else if(breakWork[0].equals("registration_numbers_for_cars_with_colour"))
			return getRegistrationNumbersFromColour(breakWork[1]);
		else if(breakWork[0].equals("slot_numbers_for_cars_with_colour"))
			return getSlotNumbersFromColour(breakWork[1]);
		else if(breakWork[0].equals("slot_number_for_registration_number"))
			return getSlotNumberForRegistrationNumber(breakWork[1]);
		else if(breakWork[0].equals("exit"))
			return "Terminated";
		else
			return "Operation Failed,Enter A Valid Operation";
	}
	private String createParkingLot(Integer parkingLotSize) {
		if(currentParkingLot != null)
			return "create Parking Lot Operation Failed";
		currentParkingLot = new  ParkingLot(parkingLotSize);
		return "Created a parking lot with "+currentParkingLot.getMaxNoOfSlots()+" slots";
	}
	private String parkCarInParkigLot(String carRegistrationNumber, String color) {
		int slotAllocationNumber = currentParkingLot.ParkCar(new Car(carRegistrationNumber,color));
		if(slotAllocationNumber != -1)
			return "Allocated slot number: " + slotAllocationNumber;
		else
			return "Sorry, parking lot is full";
	}
	private String leaveCarFromParkingSlot(int leaveCarSlotNumber)
	{
		if(currentParkingLot.leaveCar(leaveCarSlotNumber))
			return "Slot number "+leaveCarSlotNumber+" is free";
		else 
			return "Leave Car From Parking Lot Operation Failed";
	}
	private String getCurrentParkingLotStatus()
	{
		return currentParkingLot.status();
	}
	private String getRegistrationNumbersFromColour(String colour) {
		if(currentParkingLot.checkIfColourExists(colour)) {
			StringBuilder registrationNumbersForSpecifiedColour = new StringBuilder("");
			for(Car c : currentParkingLot.getCarListForSpecifiedColour(colour)) {
				registrationNumbersForSpecifiedColour.append(c.getCarRegistrationNo());
				registrationNumbersForSpecifiedColour.append(", ");
			}
			registrationNumbersForSpecifiedColour.deleteCharAt(registrationNumbersForSpecifiedColour.length() - 2);
			registrationNumbersForSpecifiedColour.deleteCharAt(registrationNumbersForSpecifiedColour.length() - 1);
			return registrationNumbersForSpecifiedColour.toString();
		}
		else
			return "Not found";
	}
	private String getSlotNumbersFromColour(String colour)
	{
		if(currentParkingLot.checkIfColourExists(colour)) {
			StringBuilder registrationNumbersForSpecifiedColour = new StringBuilder("");
			for(Car c : currentParkingLot.getCarListForSpecifiedColour(colour)) {
				registrationNumbersForSpecifiedColour.append(currentParkingLot.getSlotNumberForRegistrationNumber(c.getCarRegistrationNo()));
				registrationNumbersForSpecifiedColour.append(", ");
			}
			registrationNumbersForSpecifiedColour.deleteCharAt(registrationNumbersForSpecifiedColour.length() - 2);
			registrationNumbersForSpecifiedColour.deleteCharAt(registrationNumbersForSpecifiedColour.length() - 1);
			return registrationNumbersForSpecifiedColour.toString();
		}
		else
			return "Not found";
	}
	private String getSlotNumberForRegistrationNumber(String registrationNumber)
	{
		if(currentParkingLot.checkIfRegistrationNumberExists(registrationNumber))
			return String.valueOf(currentParkingLot.getSlotNumberForRegistrationNumber(registrationNumber));
		else
			return "Not found";
	}
}
