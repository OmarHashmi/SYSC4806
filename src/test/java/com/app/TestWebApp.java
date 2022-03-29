package com.app;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.List;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class TestWebApp {

    @Autowired
    private Surveys surveyRepo;
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private DefaultController dc;
    @InjectMocks
    private DefaultController defaultController;
    @InjectMocks
    private RestController rc;
    @Mock
    private Surveys mockSurveyRepo;

    @Test
    public void contextLoads() {
        assertThat(dc).isNotNull();
    }
    @Test
    public void restController() {
        assertThat(rc).isNotNull();
    }

    @Test
    public void getDefaultPage() throws Exception {
    }
    @Test
    public void testSurveyRepo() throws Exception {
        Survey survey = new Survey("Test");
        surveyRepo.save(survey);
        List<Survey> test = surveyRepo.findAll();
        assertThat(test.get(test.size()-1).getSurveyName()).isEqualTo("Test");
    }
    @Test
    public void testDeletingSurvey() throws Exception {
        Survey survey = new Survey("Test");
        surveyRepo.save(survey);
        List<Survey> test = surveyRepo.findAll();
        surveyRepo.delete(survey);
        List<Survey> test1 = surveyRepo.findAll();
        assertThat(test.get(test1.size()).getSurveyName()).isNotEqualTo(test.size());
    }
    @Test
    public void testPersistQuestions() throws Exception {
        Survey survey = new Survey("Test");
        Question question = new Question("Does this work");
        survey.addQuestion(question);
        surveyRepo.save(survey);
        List<Survey> test = surveyRepo.findAll();
        Survey main = test.get(test.size()-1);
        assertThat(main.getQuestions().size()).isEqualTo(1);
    }
    @Test
    public void testPersistQuestionsString() throws Exception {
        Survey survey = new Survey("Test");
        Question question = new Question("Does this work");
        survey.addQuestion(question);
        surveyRepo.save(survey);
        List<Survey> test = surveyRepo.findAll();
        Survey main = test.get(test.size()-1);
        assertThat(main.getQuestions().get(0).getQuestion()).isEqualTo("Does this work");

    }
    @Test
    public void testFindAll() throws Exception {
        Survey survey = new Survey("Test");
        Question question = new Question("Does this work");
        survey.addQuestion(question);
        surveyRepo.save(survey);
        List<Survey> test = surveyRepo.findAll();
        Survey main = test.get(test.size()-1);
        assertThat(test.size()).isEqualTo(7);

    }

   /* @Test
    public void testAddQuestion()
    {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

       // when(mockSurveyRepo.save(any(Survey.class))).thenReturn(true);

        Question question = new Question("test");
        RedirectView responseEntity = rc.newQuestion(question);

        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
        assertThat(responseEntity.getHeaders().getLocation().getPath()).isEqualTo("/1");
    }*/
    /*@Test
    public void testController() throws Exception {
        MockMvc mvc;
        HttpClient client = HttpClient.newHttpClient();
        Survey survey = new Survey("Test");
        surveyRepo.save(survey);
        ObjectMapper mapper = new ObjectMapper();

        HashMap values = new HashMap<String,String>(){{
            put("survery_id", survey.getId().toString());
        }};
        String requestValue = mapper.writeValueAsString(values);
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("http://localhost:8080/questions/add"))
                .POST(HttpRequest.BodyPublishers.ofString(requestValue))
                .build();
        client.send(request, HttpResponse.BodyHandlers.ofString());
        TimeUnit.SECONDS.sleep(4);
        List<Survey> newList = surveyRepo.findAll();
        assertThat(newList.get(newList.size()-1).getQuestions().size()).isEqualTo(1);
    }*/
}
