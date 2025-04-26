package part1;

import java.util.*;

public class Season implements Iterable<Episode> {
    private final List<Episode> episodes = new ArrayList<>();

    public void addEpisode(Episode e) {
        episodes.add(e);
    }

    // Normal order iterator
    public EpisodeIterator getIterator() {
        return new SeasonIterator(episodes);
    }

    // Reverse order iterator
    public EpisodeIterator getReverseIterator() {
        return new ReverseSeasonIterator(episodes);
    }

    // Shuffle order iterator with fixed seed
    public EpisodeIterator getShuffleIterator() {
        return new ShuffleSeasonIterator(episodes);
    }

    // To support for-each loops: returns normal iterator
    @Override
    public Iterator<Episode> iterator() {
        return new Iterator<Episode>() {
            private final EpisodeIterator it = new SeasonIterator(episodes);

            @Override
            public boolean hasNext() {
                return it.hasNext();
            }

            @Override
            public Episode next() {
                return it.next();
            }
        };
    }
}