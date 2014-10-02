package com.madeinhk.stardict.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.madeinhk.stardict.StardictIndex;
import com.madeinhk.stardict.StardictIndex.StarDictIndexEntry;
import com.madeinhk.stardict.StardictIndexParser;

public class StarDictIndexParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void test() throws IOException {
        File file = new File("oxford", "oxford-big5.idx");
        StardictIndexParser parser = new StardictIndexParser();
        StardictIndex index = parser.parse(file);
        StarDictIndexEntry entry = index.lookupWord("hello");
        System.out.println(entry.mOffset);
        System.out.println(entry.mLength);
    }

}
