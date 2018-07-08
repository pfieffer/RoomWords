package np.com.ravigarbuja.roomwords.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import np.com.ravigarbuja.roomwords.model.Word;

@Database(entities = {Word.class}, version = 1)
public abstract class WordRoomDatabase extends RoomDatabase {

    public abstract WordDao wordDao();

    private static WordRoomDatabase INSTANCE;


    //Define the DAOs that work with the database. Provide an abstract "getter" method for each @Dao.
    static WordRoomDatabase getDatabase(final Context context) {

        //Make the WordRoomDatabase a singleton to prevent having multiple instances of the database opened at the same time.
        if (INSTANCE == null) {
            synchronized (WordRoomDatabase.class) {
                if (INSTANCE == null) {
                    //creating database
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WordRoomDatabase.class, "word_database")
                            .build();

                }
            }
        }
        return INSTANCE;
    }
}
