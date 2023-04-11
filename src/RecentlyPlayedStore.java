import java.util.*;

public class RecentlyPlayedStore {
    private int capacity = 3;
    private int numSongsPerUser = 4;
    private Map<String, String> songUserMap; // song -> user
    private static Map<String, LinkedList<String>> userSongListMap; // user -> song list

    public RecentlyPlayedStore(int capacity, int numSongsPerUser) {
        this.capacity = capacity;
        this.numSongsPerUser = numSongsPerUser;
        songUserMap = new HashMap<>();
        userSongListMap = new HashMap<>();
    }

    public List<String> getRecentlyPlayedSongs(String user) {
        List<String> songList = userSongListMap.get(user);
        if (songList == null) {
            return new ArrayList<>();
        } else {
            return new ArrayList<>(songList);
        }
    }

    public void addSongForUser(String user, String song) {
        LinkedList<String> songList = userSongListMap.get(user);
        if (songList == null) {
            songList = new LinkedList<>();
            userSongListMap.put(user, songList);
        }
        if (songUserMap.containsKey(song)) {
            String prevUser = songUserMap.get(song);
            userSongListMap.get(prevUser).remove(song);
        }
        songUserMap.put(song, user);
        songList.add(song);

        if (songList.size() > numSongsPerUser) {
            String oldestSong = songList.removeFirst();
            songUserMap.remove(oldestSong);
        }
        if (songUserMap.size() > capacity) {
            String oldestSong = userSongListMap.get(songUserMap.entrySet().iterator().next().getValue()).removeFirst();
            songUserMap.remove(oldestSong);
        }
    }
  public static void main(String[] args){
      RecentlyPlayedStore obj= new RecentlyPlayedStore(3,4);
      obj.getRecentlyPlayedSongs("Abhijit");
      obj.addSongForUser("Abhijit","S1");
      obj.addSongForUser("Abhijit","S2");
      obj.addSongForUser("Abhijit","S3");
      obj.addSongForUser("Abhijit","S4");
      obj.addSongForUser("Abhijit","S5");
     System.out.println(userSongListMap);
  }
}


