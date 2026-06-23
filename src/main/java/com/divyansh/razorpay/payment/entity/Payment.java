package com.divyansh.razorpay.payment.entity;

import com.divyansh.razorpay.common.entity.Money;
import com.divyansh.razorpay.common.enums.PaymentMethd;
import com.divyansh.razorpay.common.enums.PaymentStatus;
import com.divyansh.razorpay.payment.entity.OrderRecord;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.UUID;

@Entity
@Table(name = "payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID) // better for UUID
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private OrderRecord order;

    @Column(name = "merchant_id", nullable = false)
    private UUID merchantId;

    @Embedded
    private Money amount;

    @Column(name = "idempotency_key", nullable = false, length = 100, unique = true)
    private String idempotencyKey;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "expires_at")
    private LocalDateTime expiresAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethd method;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(name = "method_details", columnDefinition = "jsonb")
    private Map<String, Object> methodDetails;

    @Column(name = "bank_reference", length = 100)
    private String bankReference;

    @Column(name = "error_code", length = 255)
    private String errorCode;

    @Column(name = "error_description", length = 1000)
    private String errorDescription;

    @Column(name = "authorized_at")
    private LocalDateTime authorizedAt;

    @Column(name = "captured_at")
    private LocalDateTime capturedAt;

    @Column(name = "failed_at")
    private LocalDateTime failedAt;

    @Column(name = "refunded_at")
    private LocalDateTime refundedAt;

    @Column(name = "settled_at")
    private LocalDateTime settledAt;
}