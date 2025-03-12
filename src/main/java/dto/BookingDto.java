package dto;

public class BookingDto {
    private int bookingId;

    // Constructor, Getters, and Setters
    public BookingDto(int bookingId) {
        this.bookingId = bookingId;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }
}