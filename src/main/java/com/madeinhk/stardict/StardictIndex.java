package com.madeinhk.stardict;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class StardictIndex {

    private Map<Character, ArrayList<StarDictIndexEntry>> mMap;

    public StardictIndex() {
        mMap = new HashMap<Character, ArrayList<StarDictIndexEntry>>();
        for (char c = 'a'; c <= 'z'; c++) {
            mMap.put(c, new ArrayList<StarDictIndexEntry>());
        }
    }

    /**
     * Add one entry to the index Entries must be added in sorted order
     * 
     * @param item
     */
    public void addToIndex(String word, int offset, int length) {
        word = word.toLowerCase();
        Character firstCharacter = word.charAt(0);
        if (Character.isAlphabetic(firstCharacter)) {
            ArrayList<StarDictIndexEntry> list = mMap.get(firstCharacter);
            StarDictIndexEntry entry = new StarDictIndexEntry(word, offset,
                    length);
            list.add(entry);
        }
    }

    public StarDictIndexEntry lookupWord(String word) {
        word = word.toLowerCase();
        StarDictIndexEntry needle = new StarDictIndexEntry(word, 0, 0);
        Character firstCharacter = word.charAt(0);
        ArrayList<StarDictIndexEntry> list = mMap.get(firstCharacter);
        int index = Collections.binarySearch(list, needle);
        if (index == -1) {
            return null;
        }
        return list.get(index);
    }

    public static class StarDictIndexEntry implements
            Comparable<StarDictIndexEntry> {

        public StarDictIndexEntry(String word, int offset, int length) {
            super();
            this.mWord = word;
            this.mOffset = offset;
            this.mLength = length;
        }

        public String mWord;
        public int mOffset;
        public int mLength;

        @Override
        public int compareTo(StarDictIndexEntry o) {
            return this.mWord.compareTo(o.mWord);
        }
    }
}
