package com.example.nemesis.presentation.details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.example.nemesis.R
import com.example.nemesis.presentation.Singletons.Singletons
import com.example.nemesis.presentation.api.AnimeDetailsResponse
import kotlinx.android.synthetic.main.fragment_anime_detail.*
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimeDetailsFragment : Fragment() {

    private lateinit var textName : TextView
    private lateinit var imageDetails : ImageView
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

       /* view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_AnimeDetails_to_AnimeList)
        }*/
        textName = view.findViewById(R.id.anime_details_title)
        callsAnimeDetailsApi()
    }
    private fun callsAnimeDetailsApi() {
        val id = arguments?.getInt("anime_id") ?: -1
        Singletons.jikanApi.getAnimeDetails(id).enqueue(object : Callback<AnimeDetailsResponse>{
            override fun onFailure(call: retrofit2.Call<AnimeDetailsResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(
                call: retrofit2.Call<AnimeDetailsResponse>,
                response: Response<AnimeDetailsResponse>
            ) {
                if(response.isSuccessful && response.body() != null) {
                    
                    textName.text = response.body()!!.synopsis
                }
            }
        })
    }
}