package io.viff.storage.endpoint;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.viff.storage.StorageApplication;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration(classes = StorageApplication.class)
public class FileEndpointTest {

    @Resource
    private WebApplicationContext webApplicationContext;

    private MockMvc mockMvc;

    @Value("${application.files.localPath}")
    private String storagePath;

    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void should_return_file_and_saved_file_in_path_when_provide_a_file() throws Exception {
        MockMultipartFile file = new MockMultipartFile("file", "1.png", "image/png", new byte[20 * 1024 * 1024]);

        String projectID = "project";
        String tag = "tag";


        MockMvc mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file/upload/{projectID}/{tag}", projectID, tag)
                .file(file)
        ).andExpect(status().is(200)).andDo(print()).andReturn();
        ObjectMapper objectMapper = new ObjectMapper();
        UploadFileResponse uploadFileResponse = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UploadFileResponse.class);
        assertThat(uploadFileResponse.getUrl(), containsString(String.format("%s/%s/%s/%s", storagePath, projectID, tag, uploadFileResponse.getBuildNumber(), "1.png")));
        assertThat(Files.exists(Paths.get(uploadFileResponse.getUrl())), is(true));
        Files.delete(Paths.get(uploadFileResponse.getUrl()));
    }

    @Test
    @Ignore // TODO: reason: FileUploadConfig not work...
    public void should_return_error_when_upload_an_oversize_file() throws Exception {
        String projectID = "project";
        String tag = "tag";
        MockMultipartFile file = new MockMultipartFile("file", "1.png", "image/png", new byte[15 * 1024 * 1024]);
        MvcResult mvcResult = mockMvc.perform(MockMvcRequestBuilders.fileUpload("/file/upload/{projectID}/{tag}", projectID, tag)
                .file(file)
        ).andReturn();
        MockHttpServletResponse response = mvcResult.getResponse();
        Object result = mvcResult.getAsyncResult();
    }
}