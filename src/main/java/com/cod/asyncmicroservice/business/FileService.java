package com.cod.asyncmicroservice.business;


import com.cod.asyncmicroservice.domain.FileData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class FileService {

    @Value("${cod.demo.file}")
    String fileName;

    public String readFile() {
        log.info("Reading the file using Asynch thread");
        List<String> list = new ArrayList<>();

        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

            //br returns as stream and convert it into a List
            list = br.lines().collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        for(String s : list) {
            sb.append(s);
        }
        return sb.toString();
    }

    public boolean writeFile(FileData data)  {
        log.info("Writing to file using Asynch thread");
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter(fileName));
            writer.write(data.getData());
            writer.close();
        } catch(IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;


    }
}
