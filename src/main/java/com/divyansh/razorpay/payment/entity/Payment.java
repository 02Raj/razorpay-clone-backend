package com.divyansh.razorpay.payment.entity;

import com.divyansh.razorpay.common.entity.Money;
import com.divyansh.razorpay.common.enums.OrderStatus;
import com.divyansh.razorpay.common.enums.PaymentMethd;
import com.divyansh.razorpay.common.enums.PaymentStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name="order_id", nullable=false)
    private OrderRecord order;

    @Column(nullable=false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Column(nullable=false,length = 100)
    private String idempotencyKey;

    private LocalDateTime createdAt;
    private LocalDateTime expiresAt;
    private PaymentStatus status;

    @Column(nullable=false)
    private PaymentMethd  method;

    @JdbcTypeCode(name=)
    private Map<String, Object> methodDetails;
    private String bankReference;
    private String errorCode;
    private String errorDescription;
    private LocalDateTime authorizedAt;
    private LocalDateTime capturedAt;
    private LocalDateTime failledAt;
    private LocalDateTime refundedAt;
    private LocalDateTime settledAt;

}
