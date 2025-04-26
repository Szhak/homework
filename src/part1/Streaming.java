package part1;


public class Streaming {
    public static void main(String[] args) {
        // Prepare seasons and episodes
        Season season1 = new Season();
        season1.addEpisode(new Episode("Pilot", 300));
        season1.addEpisode(new Episode("The Mystery", 280));
        season1.addEpisode(new Episode("Finale", 320));

        Season season2 = new Season();
        season2.addEpisode(new Episode("Return", 290));
        season2.addEpisode(new Episode("Twists", 310));

        Series series = new Series();
        series.addSeason(season1);
        series.addSeason(season2);

        // Normal season iteration
        System.out.println("Normal Season 1:");
        EpisodeIterator it1 = season1.getIterator();
        while (it1.hasNext()) {
            System.out.println("- " + it1.next().getTitle());
        }

        // Reverse season iteration
        System.out.println("\nReverse Season 1:");
        EpisodeIterator rev1 = season1.getReverseIterator();
        while (rev1.hasNext()) {
            System.out.println("- " + rev1.next().getTitle());
        }

        // Shuffle season iteration
        System.out.println("\nShuffle Season 1:");
        EpisodeIterator shuf1 = season1.getShuffleIterator();
        while (shuf1.hasNext()) {
            System.out.println("- " + shuf1.next().getTitle());
        }

        // Binge across series
        System.out.println("\nBinge (normal across all seasons):");
        EpisodeIterator binge = series.getBingeIterator(BingeIterator.Mode.NORMAL);
        while (binge.hasNext()) {
            System.out.println("* " + binge.next().getTitle());
        }
    }
}