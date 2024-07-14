package com.pismo.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Transaction")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", columnDefinition = "SERIAL")
    private int id;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "account_id", nullable = false)
    @NotNull
    private Account account;

    @Column(name = "amount")
    private BigDecimal amount;

    @ManyToOne
    @JoinColumn(name = "operation_id",referencedColumnName = "operation_id", nullable = false)
    @NotNull(message = "Campo Operação obrigatório")
    private Operation operation;

    @CreationTimestamp
    @Column(name = "eventdate", columnDefinition = "TIMESTAMP")
    private LocalDateTime eventDate ;

}
