package demo.tests.service.deck;

import io.restassured.response.ValidatableResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class RestOperation {

    @Autowired
    RestAssuredUtils restUtils;

    public ValidatableResponse getCallWithQueryParam(String uri, Map<String, String> queryParam) {
        return restUtils.getReqSpec().queryParams(queryParam).get(uri).then();
    }

    public ValidatableResponse getCallWithPathParam(String uri, String pathParamKey, String value) {
        return restUtils.getReqSpec().pathParam(pathParamKey,value).get(uri).then();
    }

    public ValidatableResponse getCallWithPathParamAndQueryParam(String uri, String pathParamKey, String pathParamValue,Map<String, String> queryParam) {
        return restUtils.getReqSpec().pathParam(pathParamKey,pathParamValue).queryParams(queryParam).get(uri).then();
    }

    public ValidatableResponse getCall(String uri) {
        return restUtils.getReqSpec().get(uri).then();
    }

    public ValidatableResponse postCall(String uri, List<Map<String, Object>> postBody) {
        return restUtils.getReqSpec().body(postBody).post(uri).then();
    }

    public ValidatableResponse postCallWithJsonStringBody(String uri, String postBody, Map<String, String> ccaTokenCookie) {
        return restUtils.getReqSpec().body(postBody).post(uri).then();
    }
}
