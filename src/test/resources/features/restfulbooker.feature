Feature: RESTful Booker API Testing

  @restfulbooker
  Scenario: Create and Update Booking
    Given I have an authentication token for "admin" and "password123"
    When I create a booking with firstname "Lucas", lastname "Liu", totalprice 111, depositpaid "true", checkin "2025-03-08", checkout "2025-03-09", and additionalneeds "Breakfast"
    And I update the booking with firstname "Lucas", lastname "Liu", totalprice 111, depositpaid "true", checkin "2025-03-08", checkout "2025-03-10", and additionalneeds "Breakfast"
    And I Patch the booking with firstname "Iris", lastname "Chen"
    Then I should receive a successful restful-booker api response
    And The updated response jsonpath value "bookingdates.checkout" should be different from original response jsonpath value "booking.bookingdates.checkout"
    And The updated response jsonpath value "totalprice" should be same as original response jsonpath value "booking.totalprice"
    And I delete the booking
