package com.domanski.backend.admin.adminProduct.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class UploadedFilesUtilsTest {

    @ParameterizedTest
    @CsvSource({
            "test test.png, test-test.png",
            "Hello world.png, hello-world.png",
            "gość.png, gosc.png",
            "Produkt 1.png, produkt-1.png",
            "Produkt_1.png, produkt-1.png"
    })
    void shouldSlugifyFileName(String in, String out) {
        String fileName = UploadedFilesUtils.slugifyFileName(in);
        assertEquals(fileName, out);
    }

}