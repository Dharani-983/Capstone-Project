package com.loan_transaction.dto;

import java.time.LocalDateTime;

import com.loan_transaction.domain.LoanStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApprovalHistoryDTO {
    private Long approvalId;
    private Long loanApplicationId;
    private String adminEmail;
    private LoanStatus action;
    private LocalDateTime actionDate;
    private String remarks;
}
