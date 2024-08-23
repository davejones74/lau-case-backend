package uk.gov.hmcts.reform.laubackend.cases.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import uk.gov.hmcts.reform.laubackend.cases.constants.AccessRequestAction;
import uk.gov.hmcts.reform.laubackend.cases.constants.AccessRequestType;
import uk.gov.hmcts.reform.laubackend.cases.constants.RegexConstants;
import uk.gov.hmcts.reform.laubackend.cases.domain.AccessRequest;
import uk.gov.hmcts.reform.laubackend.cases.utils.TimestampUtil;


@Getter
@Setter
@Builder
public class AccessRequestLog {

    @NotNull(message = "requestType is required")
    private AccessRequestType requestType;

    @NotBlank(message = "userId is required")
    @Size(min = 1, max = 64, message = "userId length must be less than or equal to 64")
    private String userId;

    @NotBlank(message = "caseRef is required")
    @Pattern(regexp = RegexConstants.CASE_REF_REGEX, message = "caseRef must be 16 digits")
    private String caseRef;

    @NotBlank(message = "reason is required")
    private String reason;

    @NotNull(message = "action is required")
    private AccessRequestAction action;

    private String timeLimit;

    @NotNull(message = "timestamp is required")
    private String timestamp;

    public AccessRequest toModel() {
        final TimestampUtil timestampUtil = new TimestampUtil();

        AccessRequest accessRequest = new AccessRequest();
        accessRequest.setRequestType(this.getRequestType().name());
        accessRequest.setUserId(this.getUserId());
        accessRequest.setCaseRef(this.getCaseRef());
        accessRequest.setReason(this.getReason());
        accessRequest.setAction(this.getAction().name());

        if (this.getTimeLimit() != null) {
            accessRequest.setTimeLimit(timestampUtil.getUtcTimestampValue(this.getTimeLimit()));
        }
        accessRequest.setLogTimestamp(timestampUtil.getUtcTimestampValue(this.getTimestamp()));
        return accessRequest;
    }

    public static AccessRequestLog modelToDto(AccessRequest accessRequest) {
        TimestampUtil timestampUtil = new TimestampUtil();

        AccessRequestLogBuilder builder = AccessRequestLog.builder()
            .requestType(AccessRequestType.valueOf(accessRequest.getRequestType()))
            .userId(accessRequest.getUserId())
            .caseRef(accessRequest.getCaseRef())
            .reason(accessRequest.getReason())
            .action(AccessRequestAction.valueOf(accessRequest.getAction()))
            .timestamp(timestampUtil.timestampConvertor(accessRequest.getLogTimestamp()));
        if (accessRequest.getTimeLimit() != null) {
            builder.timeLimit(timestampUtil.timestampConvertor(accessRequest.getTimeLimit()));
        }
        return builder.build();

    }
}