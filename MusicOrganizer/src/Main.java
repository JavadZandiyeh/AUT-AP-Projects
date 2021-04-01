public class Main {

    public static void main(String[] args){
        MusicCollection collection = new MusicCollection();

        //tests for working truly
        MusicPlayer player1 = new MusicPlayer("c:user:Musics:Alferedo");
        MusicPlayer player2 = new MusicPlayer("c:user:Musics:Alferedo15");
        MusicPlayer player3 = new MusicPlayer("c:user:Musics:Alferedo12");
        MusicPlayer player4 = new MusicPlayer("c:user:Musics:Alferedo32");

        collection.addMusic(player1.getAddress(),collection.getAllMusic());
        collection.addMusic(player2.getAddress(),collection.getAllMusic());
        collection.addMusic(player3.getAddress(),collection.getAllMusic());
        collection.addMusic(player4.getAddress(),collection.getAllMusic());

        collection.addMusic(player3.getAddress(),collection.getLoveMusic());
        collection.addMusic(player1.getAddress(),collection.getLoveMusic());

        System.out.println(collection.getNumberOfMusic(collection.getAllMusic()));System.out.println();
        System.out.println(collection.getNumberOfMusic(collection.getLoveMusic()));System.out.println();

        collection.listAllMusic(collection.getLoveMusic());System.out.println();
        collection.listAllMusic(collection.getAllMusic());System.out.println();

        collection.listMusic(1,collection.getLoveMusic());System.out.println();
        collection.listMusic(3,collection.getAllMusic());System.out.println();

        collection.removeMusic(1,collection.getLoveMusic());System.out.println();

        collection.startPlaying(2,collection.getAllMusic());System.out.println();
        collection.stopPlaying(2,collection.getAllMusic());System.out.println();

        collection.search("c:user:Musics:Alferedo15", collection.getAllMusic());
    }
}
