package com.madeinhk.stardict;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.madeinhk.stardict.StardictInfo;
import com.madeinhk.stardict.StardictInfoParser;

public class StarDictInfoParserTest {

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testParse() throws IOException {
        File file = new File("oxford", "oxford-big5.ifo");
        StardictInfoParser parser = new StardictInfoParser();
        StardictInfo info = parser.parse(file);
        Assert.assertEquals(39429, info.mWordCount);
        Assert.assertEquals("m", info.mSameTypeSequence);
    }

}
