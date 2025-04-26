package part1;

import java.util.List;
import java.util.NoSuchElementException;

public class SeasonIterator implements EpisodeIterator {
    private final List<Episode> episodes;
    private int index = 0;

    public SeasonIterator(List<Episode> episodes) {
        this.episodes = episodes;
    }

    @Override
    public boolean hasNext() {
        return index < episodes.size();
    }

    @Override
    public Episode next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        return episodes.get(index++);
    }
}