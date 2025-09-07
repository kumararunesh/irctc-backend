package com.arunesh.irctc.irctc_backend.helper;

import java.util.UUID;

public class Helper {

    static public String getFileName(String folder, String originalFilename  )
    {
       return folder+ UUID.randomUUID()+ originalFilename;

    }
}
