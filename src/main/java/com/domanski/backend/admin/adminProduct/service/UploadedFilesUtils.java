package com.domanski.backend.admin.adminProduct.service;

import com.github.slugify.Slugify;
import org.apache.commons.io.FilenameUtils;

class UploadedFilesUtils {
    public static String slugifyFileName(String fileName) {
        String name = FilenameUtils.getBaseName(fileName);
        Slugify slg = new Slugify();
        String changedName = slg.withCustomReplacement("_","-").slugify(name);
        return changedName + "." + FilenameUtils.getExtension(fileName);
    }
}
