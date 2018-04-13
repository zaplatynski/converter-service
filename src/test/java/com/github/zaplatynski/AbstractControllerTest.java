package com.github.zaplatynski;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public abstract class AbstractControllerTest {

    protected String readStringFrom(String file) throws IOException {
        String content = new String(Files.readAllBytes(Paths.get("src/test/resources/" + file)), "UTF-8");
        return content.trim();
    }

}
