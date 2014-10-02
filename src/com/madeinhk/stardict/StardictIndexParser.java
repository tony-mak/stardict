package com.madeinhk.stardict;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class StardictIndexParser {
    public StardictIndex parse(File indexFile) throws IOException {
        StardictIndex index = new StardictIndex();
        DataInputStream in = new DataInputStream(new BufferedInputStream(
                new FileInputStream(indexFile)));
        byte byteRead;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        boolean eof = false;
        while (!eof) {
            try {
                byteRead = in.readByte();
                if (byteRead == 0) {
                    String word = bos.toString("UTF-8");
                    int offset = in.readInt();
                    int length = in.readInt();

                    index.addToIndex(word, offset, length);
                    bos.reset();
                } else {
                    bos.write(byteRead);
                }
            } catch (EOFException ex) {
                eof = true;
            }
        }
        in.close();
        return index;
    }
}
