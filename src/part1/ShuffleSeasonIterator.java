package part1;

import java.util.*;


public class ShuffleSeasonIterator implements EpisodeIterator {
    private final List<Episode> shuffled;
    private int index = 0;

    public ShuffleSeasonIterator(List<Episode> episodes) {
        this.shuffled = new ArrayList<>(episodes);
        // Fixed seed for repeatable order
        Collections.shuffle(this.shuffled, new Random(42));
    }

    @Override
    public boolean hasNext() {
        return index < shuffled.size();
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return shuffled.get(index++);
    }
}