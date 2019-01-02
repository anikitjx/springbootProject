package com.orange.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class MergeVideo {
    private String ffmpegEXE;

    public MergeVideo(String ffmpegEXE) {
        super();
        this.ffmpegEXE = ffmpegEXE;
    }

    public void convertor(String videoInputPath,String mp3InputPath,String videoOutPutPath) throws IOException {
        List<String> command = new ArrayList<>();

        command.add(ffmpegEXE);
        command.add("-i");
        command.add(videoInputPath);
        if(mp3InputPath != null){
            command.add("-i");
            command.add(mp3InputPath);
            command.add("-filter_complex");
            command.add("[0:a][1:a]amerge=inputs=2[a]");
            command.add("-map");
            command.add("0:v");
            command.add("-map");
            command.add("[a]");
            command.add("-b:a");
            command.add("64k");
            command.add("-ac");
            command.add("2");
        }
        command.add("-b:v");
        command.add("800k");
        command.add("-bufsize");
        command.add("1000k");
        command.add("-strict");
        command.add("-2");
        command.add("-shortest");
        command.add("-y");
        command.add(videoOutPutPath);
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
