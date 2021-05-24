package br.com.unipac.exemploretrofit

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import br.com.unipac.exemploretrofit.model.Posts
import br.com.unipac.exemploretrofit.model.client.PostClient
import br.com.unipac.exemploretrofit.utils.NetworkUtils
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getData()
    }

    private fun getData() {
        var retrofitClient  =
            NetworkUtils.getRetrofitInstance("https://jsonplaceholder.typicode.com")

        var endpoint = retrofitClient.create(PostClient::class.java)
        var callback = endpoint.getPosts()

        callback.enqueue(object : Callback<List<Posts>> {
            override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }

            override fun onResponse(call: Call<List<Posts>>, response: Response<List<Posts>>) {
                response.body()?.forEach {
                    postsTV.text = postsTV.text.toString().plus(it.body)
                }
            }
        })

    }
}