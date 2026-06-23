package com.divyansh.razorpay.operations.entity;

import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.UUID;
import lombok.Data;

@Embeddable
@Data
public class SettlementPaymentId implements Serializable {

    private UUID settlementId;

    private UUID paymentId;
}
