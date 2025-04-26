package part1;

import java.util.*;

public class Series implements Iterable<Episode> {
    private final List<Season> seasons = new ArrayList<>();

    public void addSeason(Season s) {
        seasons.add(s);
    }

    public EpisodeIterator getBingeIterator(BingeIterator.Mode mode) {
        return new BingeIterator(seasons, mode);
    }

    @Override
    public Iterator<Episode> iterator() {
        return new Iterator<Episode>() {
            private final EpisodeIterator it = getBingeIterator(BingeIterator.Mode.NORMAL);

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