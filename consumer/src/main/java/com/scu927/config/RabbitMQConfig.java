package com.scu927.config;
import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Chuhan
 * @date 2024/9/8
 */


@Configuration
public class RabbitMQConfig {
    /// Queue names
    private static final String SCENIC_SPOT_QUEUE = "scenicSpotQueue";
    private static final String ROOM_BOOKING_QUEUE = "roomBookingQueue";
    private static final String TABLE_RESERVATION_QUEUE = "tableReservationQueue";
    private static final String PAYMENT_REMINDER_QUEUE = "paymentReminderQueue";  // New queue
    private static final String ROOM_CANCEL_REMINDER_QUEUE = "roomCancelReminderQueue";  // New queue

    // Dead letter queue and exchange names
    private static final String DLQ_SCENIC_SPOT = "dlq-scenicSpot";
    private static final String DLQ_ROOM_BOOKING = "dlq-roomBooking";
    private static final String DLQ_TABLE_RESERVATION = "dlq-tableReservation";
    private static final String DLQ_PAYMENT_REMINDER = "dlq-paymentReminder";  // New dead letter queue
    private static final String DLQ_ROOM_CANCEL_REMINDER = "dlq-roomCancelReminder";  // New dead letter queue
    private static final String DLX_EXCHANGE = "dlx-exchange";  // Common Dead Letter Exchange for all queues

    // Define scenic spot queue with dead letter configuration
    @Bean
    public Queue scenicSpotQueue() {
        return QueueBuilder.durable(SCENIC_SPOT_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_SCENIC_SPOT)
                .withArgument("x-message-ttl", 10000)  // Example TTL
                .build();
    }

    // Define room booking queue with dead letter configuration
    @Bean
    public Queue roomBookingQueue() {
        return QueueBuilder.durable(ROOM_BOOKING_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_ROOM_BOOKING)
                .withArgument("x-message-ttl", 10000)  // Example TTL
                .build();
    }

    // Define table reservation queue with dead letter configuration
    @Bean
    public Queue tableReservationQueue() {
        return QueueBuilder.durable(TABLE_RESERVATION_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_TABLE_RESERVATION)
                .withArgument("x-message-ttl", 10000)  // Example TTL
                .build();
    }

    // Define payment reminder queue with dead letter configuration (New)
    @Bean
    public Queue paymentReminderQueue() {
        return QueueBuilder.durable(PAYMENT_REMINDER_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_PAYMENT_REMINDER)
                .withArgument("x-message-ttl", 10000)  // Example TTL
                .build();
    }

    // Define room cancel reminder queue with dead letter configuration (New)
    @Bean
    public Queue roomCancelReminderQueue() {
        return QueueBuilder.durable(ROOM_CANCEL_REMINDER_QUEUE)
                .withArgument("x-dead-letter-exchange", DLX_EXCHANGE)
                .withArgument("x-dead-letter-routing-key", DLQ_ROOM_CANCEL_REMINDER)
                .withArgument("x-message-ttl", 10000)  // Example TTL
                .build();
    }

    // Define dead letter queues
    @Bean
    public Queue deadLetterScenicSpotQueue() {
        return QueueBuilder.durable(DLQ_SCENIC_SPOT).build();
    }

    @Bean
    public Queue deadLetterRoomBookingQueue() {
        return QueueBuilder.durable(DLQ_ROOM_BOOKING).build();
    }

    @Bean
    public Queue deadLetterTableReservationQueue() {
        return QueueBuilder.durable(DLQ_TABLE_RESERVATION).build();
    }

    // Define dead letter queue for payment reminder (New)
    @Bean
    public Queue deadLetterPaymentReminderQueue() {
        return QueueBuilder.durable(DLQ_PAYMENT_REMINDER).build();
    }

    // Define dead letter queue for room cancel reminder (New)
    @Bean
    public Queue deadLetterRoomCancelReminderQueue() {
        return QueueBuilder.durable(DLQ_ROOM_CANCEL_REMINDER).build();
    }

    // Define dead letter exchange
    @Bean
    public DirectExchange deadLetterExchange() {
        return new DirectExchange(DLX_EXCHANGE);
    }

    // Bind dead letter queues to the dead letter exchange
    @Bean
    public Binding deadLetterScenicSpotBinding() {
        return BindingBuilder.bind(deadLetterScenicSpotQueue()).to(deadLetterExchange()).with(DLQ_SCENIC_SPOT);
    }

    @Bean
    public Binding deadLetterRoomBookingBinding() {
        return BindingBuilder.bind(deadLetterRoomBookingQueue()).to(deadLetterExchange()).with(DLQ_ROOM_BOOKING);
    }

    @Bean
    public Binding deadLetterTableReservationBinding() {
        return BindingBuilder.bind(deadLetterTableReservationQueue()).to(deadLetterExchange()).with(DLQ_TABLE_RESERVATION);
    }

    // Bind dead letter queue for payment reminder (New)
    @Bean
    public Binding deadLetterPaymentReminderBinding() {
        return BindingBuilder.bind(deadLetterPaymentReminderQueue()).to(deadLetterExchange()).with(DLQ_PAYMENT_REMINDER);
    }

    // Bind dead letter queue for room cancel reminder (New)
    @Bean
    public Binding deadLetterRoomCancelReminderBinding() {
        return BindingBuilder.bind(deadLetterRoomCancelReminderQueue()).to(deadLetterExchange()).with(DLQ_ROOM_CANCEL_REMINDER);
    }
}