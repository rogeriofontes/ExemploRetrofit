package br.com.unipac.exemploretrofit.model.client

import br.com.unipac.exemploretrofit.model.Posts
import retrofit2.Call
import retrofit2.http.GET

interface PostClient {

    @GET("posts")
    fun getPosts() : Call<List<Posts>>
}