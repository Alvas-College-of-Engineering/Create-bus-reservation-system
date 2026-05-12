class Reservation {

    private String passengerName;
    private int seatNumber;
    private String travelDate;

    public Reservation(String passengerName, int seatNumber, String travelDate) {
        this.passengerName = passengerName;
        this.seatNumber = seatNumber;
        this.travelDate = travelDate;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public String getTravelDate() {
        return travelDate;
    }

    @Override
    public String toString() {
        return "Passenger Name: " + passengerName +
                "\nSeat Number: " + seatNumber +
                "\nTravel Date: " + travelDate +
                "\n-----------------------------";
    }
}