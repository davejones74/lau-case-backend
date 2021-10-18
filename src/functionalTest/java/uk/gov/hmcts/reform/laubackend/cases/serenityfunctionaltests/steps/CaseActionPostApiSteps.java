package uk.gov.hmcts.reform.laubackend.cases.serenityfunctionaltests.steps;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.restassured.response.Response;
import net.thucydides.core.annotations.Step;
import uk.gov.hmcts.reform.laubackend.cases.serenityfunctionaltests.model.ActionLog;
import uk.gov.hmcts.reform.laubackend.cases.serenityfunctionaltests.model.CaseActionRequestVO;
import uk.gov.hmcts.reform.laubackend.cases.serenityfunctionaltests.utils.TestConstants;

public class CaseActionPostApiSteps extends BaseSteps {
    @Step("Given the POST service body is generated")
    public CaseActionRequestVO generateCaseViewPostRequestBody() {
        ActionLog actionLog = new ActionLog();
        actionLog.setUserId("3748240");
        actionLog.setCaseAction("VIEW");
        actionLog.setCaseRef("1615817621013549");
        actionLog.setCaseJurisdictionId("CMC");
        actionLog.setCaseTypeId("Caveats");
        actionLog.setTimestamp("2021-08-23T22:20:05.023Z");
        CaseActionRequestVO caseActionRequestVO = new CaseActionRequestVO();
        caseActionRequestVO.setActionLog(actionLog);
        return caseActionRequestVO;
    }

    @Step("When the POST service is invoked")
    public Response whenThePostServiceIsInvoked(String serviceToken, Object actionLog) throws JsonProcessingException {
        return performPostOperation(TestConstants.AUDIT_CASE_VIEW_ENDPOINT, null, null, actionLog, serviceToken);
    }

}
