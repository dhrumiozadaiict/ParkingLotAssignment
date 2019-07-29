import java.io.*;
import java.util.*;
public class MainClass {
	public static void main(String[] args) {
		BufferedReader br;
		ParkingLotController currentParkingLotContoller;
		if(args.length == 0) {
			br= new BufferedReader(new InputStreamReader(System.in));
			currentParkingLotContoller = new ParkingLotController();
			while(true) {
				try {
					String outputString = currentParkingLotContoller.workParserAndGetWorkDone(br.readLine());
					if(outputString.equals("Terminated"))
						break;
					System.out.println(outputString);
				} catch (IOException e) {
					continue;
				}
			}
		}
		else {
			try {
				br= new BufferedReader(new FileReader(args[0]));
				currentParkingLotContoller = new ParkingLotController();
				while(true) {
					try {
						String outputString = currentParkingLotContoller.workParserAndGetWorkDone(br.readLine());
						if(outputString.equals("Terminated"))
							break;
						System.out.println(outputString);
					} catch (IOException e) {
						continue;
					}
				}
			} catch (FileNotFoundException e1) {
				System.out.println("Enter A Valid File Name, Enter Valid File Name");
				System.exit(0);
			}
		}
	}
}
