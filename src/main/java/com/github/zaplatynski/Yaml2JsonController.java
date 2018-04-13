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
public class Yaml2JsonController {

    private final ObjectMapper objectMapper;
    private final YAMLMapper yamlMapper;

    public Yaml2JsonController(final ObjectMapper objectMapper, @Qualifier("yamlMapper") final YAMLMapper yamlMapper) {
        this.objectMapper = Objects.requireNonNull(objectMapper, "ObjectMapper is null");
        this.yamlMapper = Objects.requireNonNull(yamlMapper, "YAMLMapper is null");
    }

    @PostMapping(value = "/yaml2json", consumes =  MediaType.TEXT_PLAIN_VALUE, produces =MediaType.APPLICATION_JSON_VALUE)
    public String convert(@NotNull @NotEmpty @RequestBody final String yaml) throws IOException {
        JsonNode jsonNode = yamlMapper.readValue(yaml, JsonNode.class);
        return objectMapper.writeValueAsString(jsonNode);
    }
}
