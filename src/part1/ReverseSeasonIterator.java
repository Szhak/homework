package part1;

import java.util.List;
import java.util.NoSuchElementException;

public class ReverseSeasonIterator implements EpisodeIterator {
    private final List<Episode> episodes;
    private int index;

    public ReverseSeasonIterator(List<Episode> episodes) {
        this.episodes = episodes;
        this.index = episodes.size() - 1;
    }

    @Override
    public boolean hasNext() {
        return index >= 0;
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return episodes.get(index--);
    }
}