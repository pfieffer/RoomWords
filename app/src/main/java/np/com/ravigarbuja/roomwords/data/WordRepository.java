package np.com.ravigarbuja.roomwords.data;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

import np.com.ravigarbuja.roomwords.model.Word;

public class WordRepository {
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    public WordRepository(Application application) {
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    /*
     s wrapper for getAllWords(). Room executes all queries on a separate thread.
     Observed LiveData will notify the observer when the data has changed.
     */
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }

    /*
       a wrapper for the insert() method. You must call this on a non-UI thread or your app will crash.
       Room ensures that you don't do any long-running operations on the main thread, blocking the UI.
     */
    public void insert (Word word) {
        new insertAsyncTask(mWordDao).execute(word);
    }

    public void clearDB(){
        new clearDBAsyncTAsk(mWordDao).execute();
    }

    /*
        AsyncTask to inset a Word to the database;
     */
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void> {

        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Word... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }

    /*
        AsyncTask to clear the database;
     */
    private static class clearDBAsyncTAsk extends AsyncTask<Void, Void, Void> {

        private WordDao mAsyncTaskDao;

        clearDBAsyncTAsk(WordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mAsyncTaskDao.deleteAll();
            return null;
        }
    }
}
