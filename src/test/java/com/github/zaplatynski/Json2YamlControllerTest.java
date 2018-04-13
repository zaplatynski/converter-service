package com.github.zaplatynski;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {Application.class})
public class Json2YamlControllerTest extends AbstractControllerTest {

    @Autowired
    private Json2YamlController controller;

    @Autowired
    @Qualifier("yamlMapper")
    private YAMLMapper mapper;

    private JsonNode readYamlFrom(String file) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/test/resources/" + file)), "UTF-8");
        return mapper.readTree(content);
    }

    @Test
    public void convertSimple() throws Exception {
        String input = readStringFrom("simple.json");
        String result = controller.convert(input);

        JsonNode expectedOutput = readYamlFrom("simple.yml");
        assertThat(mapper.readTree(result), is(expectedOutput));
    }

    @Test
    public void convertComplex() throws Exception {
        String input = readStringFrom("complex.json");
        String result = controller.convert(input);

        JsonNode expectedOutput = readYamlFrom("complex.yml");
        assertThat(mapper.readTree(result), equalTo(expectedOutput));
    }
}