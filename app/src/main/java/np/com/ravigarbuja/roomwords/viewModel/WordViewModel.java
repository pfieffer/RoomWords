package np.com.ravigarbuja.roomwords.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

import np.com.ravigarbuja.roomwords.model.Word;
import np.com.ravigarbuja.roomwords.data.WordRepository;

public class WordViewModel extends AndroidViewModel {

    //a private member variable to hold a reference to the repository.
    private WordRepository mRepository;

    //a private LiveData member variable to cache the list of words.
    private LiveData<List<Word>> mAllWords;


    public WordViewModel(@NonNull Application application) {
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getAllWords();
    }

    /*
        a "getter" method for all the words. This completely hides the implementation from the UI.
     */
    public LiveData<List<Word>> getAllWords() {
        return mAllWords;
    }


    /*
        a wrapper insert() method that calls the Repository's insert() method.
         In this way, the implementation of insert() is completely hidden from the UI.
     */
    public void insert(Word word) {
        mRepository.insert(word);
    }
}
