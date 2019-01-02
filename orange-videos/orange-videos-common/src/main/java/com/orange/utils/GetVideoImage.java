package com.orange.utils;

import org.hibernate.validator.internal.util.privilegedactions.GetClassLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class GetVideoImage {
    private String ffmpegEXE;

    public GetVideoImage(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void getCover(String videoInputPath,String imageOutPutPath) throws IOException {
        List<String> command = new ArrayList<>();

        command.add(ffmpegEXE);

        command.add("-ss");
        command.add("00:00:01");
        command.add("-y");
        command.add("-i");
        command.add(videoInputPath);
        command.add("-vframes");
        command.add("1");
        command.add(imageOutPutPath);
        ProcessBuilder processBuilder = new ProcessBuilder(command);
        Process process=processBuilder.start();
        InputStream errorStream = process.getErrorStream();
        InputStreamReader inputStreamReader = new InputStreamReader(errorStream);
        BufferedReader br = new BufferedReader(inputStreamReader);

        String line="";
        while((line = br.readLine())!=null){
        }
        if(br != null){
            br.close();
        }
        if(inputStreamReader != null){
            inputStreamReader.close();
        }
        if(errorStream != null){
            errorStream.close();
        }
    }
}
