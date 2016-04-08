package io.viff.comparator.endpoints;


import io.viff.comparator.ComparatorApplication;
import io.viff.sdk.request.CompareRequest;
import io.viff.sdk.response.CompareResult;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ComparatorApplication.class)
@WebIntegrationTest("server.port:0")
public class ComparatorEndpointTest {

    RestTemplate restTemplate = new TestRestTemplate();

    @Value("${local.server.port}")
    private int port;


    @Test
    public void testCompareEndpointShouldReturnTrueOfIsSameWhenTwoImageIsSame() throws Exception {
        CompareRequest compareRequest = new CompareRequest("http://img1.bdstatic.com/static/home/widget/search_box_home/logo/home_white_logo_0ddf152.png", "http://img1.bdstatic.com/static/home/widget/search_box_home/logo/home_white_logo_0ddf152.png");

        ResponseEntity<CompareResult> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "/compare/network", compareRequest, CompareResult.class);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        CompareResult compareResult = responseEntity.getBody();

        assertThat(compareResult.isSame(), is(true));
    }

    @Test
    public void testCompareEndpointShouldReturnFalseOfIsSameWhenTwoImageIsDifferent() throws Exception {
        // TODO
    }
}
