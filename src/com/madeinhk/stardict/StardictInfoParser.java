package com.madeinhk.stardict;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by tony on 1/10/14.
 */
public class StardictInfoParser {
    public StardictInfoParser() {
    }

    private static final String KEY_WORDCOUNT = "wordcount";
    private static final String KEY_SAME_TYPE_SEQ = "sametypesequence";

    /**
     * Example info file
     * StarDict's dict ifo file
     * version=2.4.2
     * wordcount=57508
     * idxfilesize=1033658
     * bookname=CDICT5英漢辭典
     * description=王煒轉換到stardict1.33+，胡正再將其轉換到stardict2。
     * date=2003.05.13
     * sametypesequence=tm
     *
     * @param line
     * @param info
     */
    private void processLine(String line, StardictInfo info) {
        String[] tokens = line.split("=");
        if (tokens.length == 2) {
            String key = tokens[0];
            String value = tokens[1];
            if (KEY_WORDCOUNT.equals(key)) {
                info.mWordCount = Integer.parseInt(value);
            } else if (KEY_SAME_TYPE_SEQ.equals(key)) {
                info.mSameTypeSequence = value;
            }
        }
    }

    public StardictInfo parse(File infoFile) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(infoFile));
        StardictInfo info = new StardictInfo();
        try {
            String line;
            while ((line = reader.readLine()) != null) {
                processLine(line, info);
            }
        } finally {
            reader.close();
        }
        return info;
    }
}
