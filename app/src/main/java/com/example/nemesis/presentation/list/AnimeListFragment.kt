package com.example.nemesis.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemesis.R
import com.example.nemesis.presentation.api.AnimeLaterResponse
import com.example.nemesis.presentation.api.JikanApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AnimeListFragment : Fragment() {

    private lateinit var recycler: RecyclerView

    private val adapter = AnimeAdapter(listOf(), ::OnClickedAnime)

    private val layoutManager = LinearLayoutManager(context)

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.anime_recyclerview)

        recycler.apply {
            layoutManager = this@AnimeListFragment.layoutManager
            adapter = this@AnimeListFragment.adapter
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v3/season/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val jikanApi: JikanApi = retrofit.create(JikanApi::class.java)

        jikanApi.getAnimeSeasonLater().enqueue(object: Callback<AnimeLaterResponse>{
            override fun onFailure(call: Call<AnimeLaterResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<AnimeLaterResponse>,
                response: Response<AnimeLaterResponse>
            ) {
                if(response.isSuccessful && response.body() != null){
                    val animeLaterResponse: AnimeLaterResponse = response.body()!!
                    adapter.UpdateList(animeLaterResponse.anime)
                }
            }

        })

        val mangalist = arrayListOf<Anime>().apply {
            add(Anime(1,"","Attack on Titan","","","","",0,"",8.1))
            //add(Anime("Sword art Online"))
            //add(Anime("Oregairu"))
            //add(Anime("UwU"))
        }
        adapter.UpdateList(mangalist)
    }
    private fun OnClickedAnime(anime: Anime) {
        findNavController().navigate(R.id.action_AnimeList_to_AnimeDetails)
    }
}