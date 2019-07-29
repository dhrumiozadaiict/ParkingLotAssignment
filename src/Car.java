public class Car {
	private String carRegistrationNo;
	private String carColor;
	Car(String carRegistrationNo,String carColor)
	{
		this.carRegistrationNo = carRegistrationNo;
		this.carColor = carColor;
	}
	public String getCarColor() {
		return carColor;
	}
	public String getCarRegistrationNo() {
		return carRegistrationNo;
	}
}
