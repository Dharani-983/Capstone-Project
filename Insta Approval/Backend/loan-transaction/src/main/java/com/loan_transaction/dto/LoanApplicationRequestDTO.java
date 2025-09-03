package com.loan_transaction.dto;

import java.math.BigDecimal;
import lombok.Data;

@Data
public class LoanApplicationRequestDTO {

    private Long userId;
	
    private BigDecimal loanAmount;

    private Long loanTypeId;
}