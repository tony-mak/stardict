package com.madeinhk.stardict;

/**
 * Created by tony on 1/10/14.
 */
public class StardictInfo {
    public int mWordCount;
    public String mSameTypeSequence;

    public boolean hasSameTypeSequence() {
        return mSameTypeSequence != null && !mSameTypeSequence.isEmpty();
    }
}
