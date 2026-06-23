package com.divyansh.razorpay.payment.entity;

import com.divyansh.razorpay.common.enums.PaymentActor;
import com.divyansh.razorpay.common.enums.PaymentEvent;
import com.divyansh.razorpay.common.enums.PaymentStatus;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "payment_transition_log")
public class PaymentTransitionLog {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
   private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "from_status", length = 30)
   private Payment payment;

    @Column(name = "to_status", nullable = false, length = 300)
    private PaymentStatus fromStatus;

    @Column(name = "event", nullable = false , length=300)
    private PaymentEvent event;

    @Enumerated(EnumType.STRING)
    @Column(name = "actor", length = 100)
    private PaymentActor actor;

    @Column(name = "occurred_at",nullable = false)
    private LocalDateTime occurredAt;
}
