package com.divyansh.razorpay.payment.entity;

import com.divyansh.razorpay.common.entity.Money;
import com.divyansh.razorpay.common.enums.OrderStatus;
import com.divyansh.razorpay.merchant.entity.Merchant;
import jakarta.persistence.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "order_record")
public class OrderRecord {

    @Id
    private UUID id;

    //no Fk - cross-service boundary
    @Column(name = "merchant_id", nullable = false)
    private UUID MerchantId;

    @Embedded
    private Money amount;

    @Column(length = 100)
    private String receipt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private OrderStatus orderStatus = OrderStatus.CREATED;

    @Column(nullable = false)
    private Integer attempts = 0;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "jsonb")
    private Map<String, Objects> notes;

    @Column(name = "expires_at" , nullable = false)
    private LocalDateTime expiresAt;
}
