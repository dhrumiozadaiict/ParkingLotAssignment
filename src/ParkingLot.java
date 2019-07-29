import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeSet;
public class ParkingLot {
	private Car[] slots;
	private int maxNoOfSlots;
	private TreeSet<Integer> emptySlotsSet;
	private HashMap<String,ArrayList<Car>> colourToCarListMap;
	private HashMap<String,Integer> registrationNumberToSlotNumberMap;
	ParkingLot(int maxNoOfSlots)
	{
		this.maxNoOfSlots = maxNoOfSlots;
		slots = new Car[maxNoOfSlots];
		emptySlotsSet = new TreeSet<Integer>();
		for(int i=1;i<=maxNoOfSlots;i++)
		{
			emptySlotsSet.add(i);
		}
		colourToCarListMap = new HashMap<>();
		registrationNumberToSlotNumberMap = new HashMap<>();
	}
	public int getMaxNoOfSlots()
	{
		return maxNoOfSlots;
	}
	public int ParkCar(Car c1)
	{
		if(emptySlotsSet.isEmpty())
			return -1;
		else {
			int nearestSlot = emptySlotsSet.pollFirst();
			slots[nearestSlot-1] = c1;
			addIntoColourToCarListMap(c1);
			registrationNumberToSlotNumberMap.put(c1.getCarRegistrationNo(),nearestSlot);
			return nearestSlot;
		}
	}
	public boolean leaveCar(int leaveCarSlotNumber)
	{
		if(leaveCarSlotNumber < 0 || leaveCarSlotNumber > maxNoOfSlots)
			return false;
		else {
			removeFromColourToCarListMap(slots[leaveCarSlotNumber-1]);
			registrationNumberToSlotNumberMap.remove(slots[leaveCarSlotNumber-1].getCarRegistrationNo());
			slots[leaveCarSlotNumber-1] = null;
			emptySlotsSet.add(leaveCarSlotNumber);
			return true;
		}
	}
	public String status()
	{
		String statusString = "Slot No. Registration No"+"\n"+"Colour";
		for(int i=0;i<maxNoOfSlots;i++) {
			if(slots[i] != null) {
				statusString += "\n"+(i+1)+"\n"+slots[i].getCarRegistrationNo()+"\n"+slots[i].getCarColor();
			}
		}
		return statusString;
	}
	public ArrayList<Car> getCarListForSpecifiedColour(String s)
	{
		return colourToCarListMap.get(s);
	}
	public void addIntoColourToCarListMap(Car c)
	{
		if(!(colourToCarListMap.containsKey(c.getCarColor()))) {
			ArrayList<Car> newColourSpecifiedCarList = new ArrayList<Car>();
			newColourSpecifiedCarList.add(c);
			colourToCarListMap.put(c.getCarColor(),newColourSpecifiedCarList);
		}
		else {
			ArrayList<Car> colourSpecifiedCarList = colourToCarListMap.get(c.getCarColor());
			colourSpecifiedCarList.add(c);
			colourToCarListMap.put(c.getCarColor(),colourSpecifiedCarList);
		}
	}
	public void removeFromColourToCarListMap(Car c)
	{
		if(colourToCarListMap.containsKey(c.getCarColor())) {
			ArrayList<Car> colourSpecifiedCarList = colourToCarListMap.get(c.getCarColor());
			colourSpecifiedCarList.remove(c);
			colourToCarListMap.put(c.getCarColor(),colourSpecifiedCarList);
		}
	}
	public int getSlotNumberForRegistrationNumber(String registrationNumber)
	{
		if(registrationNumberToSlotNumberMap.containsKey(registrationNumber))
			return registrationNumberToSlotNumberMap.get(registrationNumber);
		else
			return -1;
	}
	public boolean checkIfColourExists(String colour)
	{
		if(colourToCarListMap.containsKey(colour))
			return true;
		else
			return false;
	}
	public boolean checkIfRegistrationNumberExists(String registrationNumber)
	{
		if(registrationNumberToSlotNumberMap.containsKey(registrationNumber))
			return true;
		else
			return false;
	}
}