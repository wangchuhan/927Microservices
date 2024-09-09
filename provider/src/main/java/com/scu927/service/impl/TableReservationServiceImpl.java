package com.scu927.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.scu927.common.Response;
import com.scu927.controller.request.TableReservationRequest;
import com.scu927.controller.response.ReservationDetailsResponse;
import com.scu927.controller.response.TableRecommendationResponse;
import com.scu927.entity.Table;
import com.scu927.entity.TableReservation;
import com.scu927.mapper.TableMapper;
import com.scu927.mapper.TableReservationMapper;
import com.scu927.producer.MessageProducer;
import com.scu927.service.TableReservationService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;


/**
 * @author Chuhan
 * @date 2024/9/8
 */
@Service
public class TableReservationServiceImpl extends ServiceImpl<TableReservationMapper, TableReservation> implements TableReservationService {

    @Autowired
    private TableMapper tableMapper;


    @Autowired
    private TableReservationMapper tableReservationMapper;


    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private MessageProducer messageProducer;

    @Override
    public Response<?> reserveTable(TableReservationRequest request) {
        try {
            // Validate non-null parameters
            if (request == null) {
                return Response.error(400, "Request cannot be null");
            }

            Long restaurantCafeId = request.getRestaurantCafeId();
            String reservationDate = request.getReservationDate();
            String timeSlot = request.getTimeSlot();
            int quantity = request.getQuantity();
            String username = request.getUsername();
            String email = request.getEmail();
            String name = request.getName();
            String phoneNumber = request.getPhoneNumber();


            // Check for null or empty parameters
            if (restaurantCafeId == null || isEmpty(reservationDate) || isEmpty(timeSlot) ||
                    isEmpty(username) || isEmpty(email) || isEmpty(name) || isEmpty(phoneNumber)) {
                return Response.error(400, "All fields must be filled out and non-empty");
            }

            if (quantity <= 0) {
                return Response.error(400, "Quantity must be greater than 0");
            }

            // Validate the timeSlot format (HH:MM-HH:MM)
            if (!isValidTimeSlotFormat(timeSlot)) {
                return Response.error(400, "Invalid time slot format. Expected format: HH:MM-HH:MM");
            }

            // Validate timeSlot is one of the fixed time intervals (e.g., 08:00-10:00)
            if (!isFixedTimeSlot(timeSlot)) {
                String[] alternativeSlots = suggestNearestTimeSlots(timeSlot);
                return Response.error(400, "Invalid time slot. Try " + alternativeSlots[0] + " or " + alternativeSlots[1]);
            }



            // Find available tables matching the criteria
            List<Table> availableTables = tableMapper.findAvailableTables(restaurantCafeId, reservationDate, timeSlot, quantity);
            if (!availableTables.isEmpty()) {
                // Create a new reservation
                Table selectedTable = availableTables.get(0);  // Choose the first available table
                TableReservation reservation = new TableReservation();
                reservation.setTableId(selectedTable.getId());
                reservation.setReservationDate(reservationDate);
                reservation.setTimeSlot(timeSlot);
                reservation.setQuantity(quantity);
                reservation.setUsername(username);
                reservation.setEmail(email);
                reservation.setName(name);
                reservation.setPhoneNumber(phoneNumber);

                // Save reservation using MyBatis-Plus
                this.save(reservation);
                // when success booking,get reservation's details
                ReservationDetailsResponse reservationDetails =
                        tableMapper.getReservationDetails(reservation.getId());
                // Construct a message containing email and reservation info
//                String message = String.format("Email: %s; Reservation: %s for %d people on %s at %s",
//                        request.getEmail(), request.getRestaurantCafeId(),
//                        request.getQuantity(), request.getReservationDate(),
//                        request.getTimeSlot());
                StringBuilder message = new StringBuilder();
                message.append("Email: ").append(request.getEmail()).append(";\n\n")
                        .append("Dear ").append(request.getName()).append(",\n\n")
                        .append("Thank you for choosing our service! We are pleased to confirm that your reservation has been successfully made. Here are the details of your reservation:\n\n")
                        .append("Reservation Number: ").append(reservation.getId()).append("\n")
                        .append("Restaurant/Café: ").append(reservationDetails.getRestaurantName()).append("\n")
                        .append("Restaurant Location: ").append(reservationDetails.getRestaurantLocation()).append("\n")
                        .append("Table Number: ").append(reservationDetails.getTableNumber()).append("\n")
                        .append("Table Location: ").append(reservationDetails.getTableLocation()).append("\n")
                        .append("Reservation Date: ").append(request.getReservationDate()).append("\n")
                        .append("Time Slot: ").append(request.getTimeSlot()).append("\n")
                        .append("Number of Guests: ").append(request.getQuantity()).append("\n\n")
                        .append("We look forward to welcoming you on ").append(request.getReservationDate()).append(". If you have any questions or need further assistance, please feel free to contact us.\n\n")
                        .append("Best regards,\n")
                        .append("The Restaurant Team");

              String finalMessage = message.toString();

                // Send the message to the RabbitMQ queue
                messageProducer.sendReservationMessage(finalMessage);
                return Response.success(reservation).setMessage("Reservation confirmed!");
            } else {
                // If no tables are available, find alternative time slots
                List<TableRecommendationResponse> alternativeTimeSlots = tableReservationMapper.
                        findAlternativeTimeSlots(restaurantCafeId, reservationDate, quantity);
                if (!alternativeTimeSlots.isEmpty()) {
                    // Return a list of available time slots as recommendations
                    return Response.success(alternativeTimeSlots).setMessage("No available tables at the selected time. Here are some alternative time slots.");
                } else {
                    return Response.success("No available tables at any time for the selected date.");
                }
            }
        } catch (Exception e) {
            return Response.error(500, "An error occurred while processing your reservation: " + e.getMessage());
        }
    }

    // Helper methods to validate empty strings


    // Helper methods to validate time
    private boolean isValidTime(String time) {
        return time.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");  // Validates HH:MM format
    }

    private boolean isTimeInRange(String time, String start, String end) {
        return time.compareTo(start) >= 0 && time.compareTo(end) <= 0;  // Time should be within the range
    }

    // Helper method to check if the duration between start and end time is valid (not more than 2 hours)
    private boolean isDurationValid(String startTime, String endTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        Date start = sdf.parse(startTime);
        Date end = sdf.parse(endTime);

        long differenceInMinutes = (end.getTime() - start.getTime()) / (1000 * 60);  // Convert milliseconds to minutes
        return differenceInMinutes <= 120;  // Limit the duration to 2 hours (120 minutes)
    }



    // Helper method to check if the time slot matches the fixed 2-hour intervals
    private boolean isFixedTimeSlot(String timeSlot) {
        List<String> validTimeSlots = Arrays.asList(
                "08:00-10:00", "10:00-12:00", "12:00-14:00", "14:00-16:00", "16:00-18:00", "18:00-20:00"
        );
        return validTimeSlots.contains(timeSlot);
    }

    // Helper method to suggest the nearest valid time slots
    private String[] suggestNearestTimeSlots(String timeSlot) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        String[] fixedTimeSlots = {"08:00", "10:00", "12:00", "14:00", "16:00", "18:00"};

        String[] timeParts = timeSlot.split("-");
        String startTime = timeParts[0];
        Date inputTime = sdf.parse(startTime);

        // Find the nearest valid time slots (previous and next)
        Date closestPrevious = null;
        Date closestNext = null;

        for (String slot : fixedTimeSlots) {
            Date slotTime = sdf.parse(slot);
            if (slotTime.before(inputTime)) {
                closestPrevious = slotTime;
            } else if (slotTime.after(inputTime) && closestNext == null) {
                closestNext = slotTime;
            }
        }

        // Suggest the two nearest time slots
        Calendar cal = Calendar.getInstance();
        cal.setTime(closestPrevious);
        cal.add(Calendar.HOUR_OF_DAY, 2);
        String previousSlot = sdf.format(closestPrevious) + "-" + sdf.format(cal.getTime());

        cal.setTime(closestNext);
        cal.add(Calendar.HOUR_OF_DAY, 2);
        String nextSlot = sdf.format(closestNext) + "-" + sdf.format(cal.getTime());

        return new String[]{previousSlot, nextSlot};
    }

    // Helper methods to validate empty strings
    private boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }

    // Helper method to validate the timeSlot format (HH:MM-HH:MM)
    private boolean isValidTimeSlotFormat(String timeSlot) {
        return timeSlot.matches("^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]-(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$");
    }
}
