package io.viff.comparator.endpoints;


import io.viff.comparator.ComparatorApplication;
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

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(ComparatorApplication.class)
@WebIntegrationTest("server.port:0")
public class ComparatorEndpointTest {

    RestTemplate restTemplate = new TestRestTemplate();

    @Value("${local.server.port}")
    int port;


    @Test
    public void testCompareEndpointShouldReturnTrueOfIsSameWhenTwoImageIsSame() throws Exception {
        Map<String, String> argMap = new HashMap<String, String>();
        argMap.put("from", "http://img1.bdstatic.com/static/home/widget/search_box_home/logo/home_white_logo_0ddf152.png");
        argMap.put("to", "http://img1.bdstatic.com/static/home/widget/search_box_home/logo/home_white_logo_0ddf152.png");
        argMap.put("port", String.valueOf(port));

        ResponseEntity<CompareResult> responseEntity = restTemplate.getForEntity("http://localhost:{port}/compare?from={from}&to={to}", CompareResult.class, argMap);

        assertThat(responseEntity.getStatusCode(), is(HttpStatus.OK));
        CompareResult compareResult = responseEntity.getBody();

        assertThat(compareResult.isSame(), is(true));
    }

    @Test
    public void testCompareEndpointShouldReturnFalseOfIsSameWhenTwoImageIsDifferent() throws Exception {
        // TODO
    }
}
