package com.github.zaplatynski;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Objects;

@RestController
public class Json2YamlController {

    private final ObjectMapper objectMapper;
    private final YAMLMapper yamlMapper;

    public Json2YamlController(final ObjectMapper objectMapper, @Qualifier("yamlMapper") final YAMLMapper yamlMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "ObjectMapper is null");
        this.yamlMapper = Objects.requireNonNull(yamlMapper, "YAMLMapper is null");
    }

    @PostMapping(value = "/json2yaml",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.TEXT_PLAIN_VALUE)
    public String convert(@NotNull @NotEmpty @RequestBody final String json) throws IOException {
        final JsonNode jsonNode = objectMapper.readTree(json.replaceAll("\\t", " "));
        return yamlMapper.writeValueAsString(jsonNode);
    }

}
