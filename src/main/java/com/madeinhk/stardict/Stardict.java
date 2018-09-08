package com.madeinhk.stardict;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

import com.madeinhk.stardict.StardictIndex.StarDictIndexEntry;

public class Stardict {
    private StardictInfo mInfo;
    private StardictIndex mIndex;
    private RandomAccessFile mDictFile;

    public void loadDictionary(String infoFilePath, String indexFilePath,
            String dictFilePath) throws IOException {
        StardictInfoParser infoParser = new StardictInfoParser();
        mInfo = infoParser.parse(new File(infoFilePath));
        StardictIndexParser indexParser = new StardictIndexParser();
        mIndex = indexParser.parse(new File(indexFilePath));
        mDictFile = new RandomAccessFile(new File(dictFilePath), "r");
    }

    public String lookupWord(String word) throws IOException {
        StarDictIndexEntry entry = mIndex.lookupWord(word);
        return getMeaning(entry.mOffset, entry.mLength);
    }

    private String getMeaning(int offset, int length) throws IOException {
        long unsignedOffset = offset & 0xFFFFFFFF;
        long unsignedLength = length & 0xFFFFFFFF;

        mDictFile.seek(unsignedOffset);

        int count = 0;

        if (mInfo.mSameTypeSequence.equals("tm")) {
            while (mDictFile.readByte() != 0) {
                count++;
            }
        }

        byte[] buffer = new byte[(int) unsignedLength - count];
        mDictFile.read(buffer);

        return new String(buffer, "UTF-8");
    }
}