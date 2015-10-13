public class SongBook {
    private final List<Song> mSongs;

    public SongBook() {
        mSongs = new ArrayList<>();
    }

    public void exportTo(String filename) throws FileNotFoundException {
        FileOutputStream fileOutputStream = new FileOutputStream(filename);
        PrintWriter printWriter = new PrintWriter(fileOutputStream);
        for (Song song : mSongs) {
            printWriter.printf("%s|%s|%s%n", song.getArtist(), song.getTitle(), song.getVideoUrl());
        }
    }

    public void importFrom(String filename) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(filename);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileInputStream));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            String[] args = line.split("\\|");
            addSong(new Song(args[0], args[1], args[3]));
        }
    }

    public void addSong(Song song) {
        mSongs.add(song);
    }

    public int getSongCount() {
        return mSongs.size();
    }

    private Map<String, List<Song>> byArtist() {
        Map<String, List<Song>> byArtist = new HashMap<>();
        /*
        * change hashmap with treemap for sorted inputs from txt file.
        * */
        for (Song song : mSongs) {
            List<Song> artistSongs = byArtist.get(song.getArtist());
            if (artistSongs == null) {
                artistSongs = new ArrayList<>();
                byArtist.put(song.getArtist(), artistSongs);
            }
            artistSongs.add(song);
        }
        return byArtist;
    }

    public Set<String> getArtists() {
        return byArtist().keySet();
    }

    public List<Song> getSongsForArtist(String artistName) {
        return byArtist().get(artistName);
    }
}
