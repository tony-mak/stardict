package com.madeinhk.stardict;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.zip.GZIPInputStream;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.madeinhk.stardict.Stardict;

public class StardictTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testLookupWord() throws IOException {
        Stardict dict = new Stardict();
        dict.loadDictionary("oxford/oxford-big5.ifo", "oxford/oxford-big5.idx",
                "oxford/oxford-big5.dict");
        String meaning = dict.lookupWord("zoo");
        Assert.assertEquals(true, meaning.contains("動物園"));
    }

    @Test
    public void testLookupWord2() throws IOException {

        GZIPInputStream in = new GZIPInputStream(new FileInputStream(new File(
                "cdict/cdict-big5.dict.dz")));

        FileOutputStream fos = new FileOutputStream(new File(
                "cdict/cdict-big5.dict"));
        byte[] buffer = new byte[4096];
        int readLen = 0;
        while ((readLen = in.read(buffer)) > 0) {
            fos.write(buffer, 0, readLen);
        }
        in.close();
        fos.close();

        Stardict dict = new Stardict();
        dict.loadDictionary("cdict/cdict-big5.ifo", "cdict/cdict-big5.idx",
                "cdict/cdict-big5.dict");
        String meaning = dict.lookupWord("sun");
        System.out.println("meaning: " + meaning);
        Assert.assertEquals(true, meaning.contains("太陽"));
    }

}
