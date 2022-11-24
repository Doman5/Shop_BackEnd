package com.domanski.backend.admin.common.utils;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

public class SlugifyUtils {
    public static String slugifyFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        Slugify slg = createSlugifyInstance();
        String changedName = slg.slugify(name);
        return changedName + "." + FilenameUtils.getExtension(fileName);
    }

    public static String slugifySlug(String slug) {
        Slugify slg = createSlugifyInstance();
        return slg.slugify(slug);
    }

    private static Slugify createSlugifyInstance() {
        return Slugify.builder()
                .customReplacement("_", "-")
                .build();
    }
}
