package com.mastercoding.pagingapp.viewmodel;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelKt;
import androidx.paging.Pager;
import androidx.paging.PagingConfig;
import androidx.paging.PagingData;
import androidx.paging.rxjava3.PagingRx;

import com.mastercoding.pagingapp.model.Movie;
import com.mastercoding.pagingapp.paging.MoviePagingSource;

import io.reactivex.rxjava3.core.Flowable;
import kotlinx.coroutines.CoroutineScope;

public class MovieViewModel extends ViewModel {
    public Flowable<PagingData<Movie>> moviePagingDataFlowable;

    public MovieViewModel() {
        init();
    }

    private void init() {

        // Defining paging source
        MoviePagingSource moviePagingSource = new MoviePagingSource();

        Pager<Integer, Movie> pager = new Pager(
                new PagingConfig(20, 20, false, 20, 20 * 499),
                () -> moviePagingSource);

        // Flowable
        moviePagingDataFlowable = PagingRx.getFlowable(pager);
        CoroutineScope coroutineScope = ViewModelKt.getViewModelScope(this);
        PagingRx.cachedIn(moviePagingDataFlowable, coroutineScope);
    }

}
