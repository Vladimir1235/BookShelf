package com.android.testapp.http;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface BookService {
    /**
     * Interface implements simplest case of retrofit request
     * Contains JSON keys also used as extra-parameters keys
     */
    String TITLE_KEY = "name";
    String IMAGE_KEY = "image";
    String YEAR_KEY = "year";
    String AUTHOR_KEY = "author";
    String ISBN_KEY = "isbn";
    String DESCRIPTION_KEY = "excerpt";
    String BOOKS_ARRAY_TITLE = "books";

    @GET
    Call<ResponseBody> getBook(
        @Url String url
    );
}
