package uk.gov.hmcts.reform.laubackend.cases.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uk.gov.hmcts.reform.laubackend.cases.domain.CaseSearchAudit;
import uk.gov.hmcts.reform.laubackend.cases.domain.CaseSearchAuditCases;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ApiModel(description = "Data model for the case search log")
public class SearchLogPostResponse implements Serializable {

    public static final long serialVersionUID = 432973322;

    @ApiModelProperty(notes = "The database unique id")
    private String id;

    @ApiModelProperty(notes = "The user on whose behalf the operation took place")
    private String userId;

    @ApiModelProperty(notes = "The caseRefs effected by the search operation")
    private List<String> caseRefs;

    @ApiModelProperty(notes = "When the operation took place with microseconds in iso-8601-date-and-time-format")
    private String timestamp;

    public SearchLogPostResponse toDto(final CaseSearchAudit caseSearchAuditResponse, final String timestamp) {
        this.id = caseSearchAuditResponse.getId().toString();
        this.userId = caseSearchAuditResponse.getUserId();
        this.caseRefs = getCaseRefs(caseSearchAuditResponse.getCaseSearchAuditCases());
        this.timestamp = timestamp;
        return this;
    }

    private List<String> getCaseRefs(final List<CaseSearchAuditCases> caseSearchAuditCases) {
        final List<String> caseRefs = new ArrayList<>();
        caseSearchAuditCases.forEach(caseRef -> caseRefs.add(caseRef.getCaseRef()));

        return caseRefs;
    }
}
