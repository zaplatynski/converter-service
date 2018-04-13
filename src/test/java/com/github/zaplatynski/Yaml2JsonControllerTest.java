package com.github.zaplatynski;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class Yaml2JsonControllerTest extends AbstractControllerTest {

    @Autowired
    private Yaml2JsonController controller;

    @Autowired
    private ObjectMapper mapper;

    private JsonNode readJsonFrom(String file) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/test/resources/" + file)), "UTF-8");
        return mapper.readTree(content);
    }

    @Test
    public void convertSimple() throws Exception {
        String input = readStringFrom("simple.yml");
        String result = controller.convert(input);

        JsonNode expectedOutput = readJsonFrom("simple.json");
        assertThat(mapper.readTree(result), is(expectedOutput));
    }

    @Test
    public void convertComplex() throws Exception {
        String input = readStringFrom("complex.yml");
        String result = controller.convert(input);

        JsonNode expectedOutput = readJsonFrom("complex.json");
        assertThat(mapper.readTree(result), equalTo(expectedOutput));
    }
}