package com.example.nemesis.presentation.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemesis.R
import com.example.nemesis.presentation.Singletons.Singletons
import com.example.nemesis.presentation.api.AnimeResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AnimeListUpcommingFragment : Fragment() {

    private lateinit var recycler: RecyclerView

    private val adapter = AnimeAdapter(listOf(), ::onClickedAnime)

    //private val layoutManager = LinearLayoutManager(context) // créer un bug lors de l'appui de la touche retour en arrière

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_list_upco, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.anime_up_recyclerview)

        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@AnimeListUpcommingFragment.adapter
        }

        callAnimeListApi()

    }

    private fun callAnimeListApi() {
        Singletons.jikanApi.getAnimeUpcomming().enqueue(object : Callback<AnimeResponse> {
            override fun onFailure(call: Call<AnimeResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: Call<AnimeResponse>,
                response: Response<AnimeResponse>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    val animeLaterResponse: AnimeResponse = response.body()!!
                    adapter.UpdateList(animeLaterResponse.anime)
                }
            }

        })
    }

    private fun onClickedAnime(anime: Anime) {
        findNavController().navigate(R.id.action_AnimeList_to_AnimeDetails, bundleOf("anime_id" to anime.mal_id))
    }
}