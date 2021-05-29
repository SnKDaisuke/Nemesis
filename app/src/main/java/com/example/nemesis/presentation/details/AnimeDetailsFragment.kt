package com.example.nemesis.presentation.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.nemesis.R
import com.example.nemesis.presentation.Singletons.Singletons
import com.example.nemesis.presentation.api.AnimeDetailsResponse
import com.example.nemesis.presentation.api.AnimeStorage
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.squareup.picasso.Picasso
import retrofit2.Callback
import retrofit2.Response


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class AnimeDetailsFragment : Fragment() {

    private lateinit var titleDetails : TextView
    private lateinit var synopsisDetails : TextView
    private lateinit var airingAndTypeDetails : TextView
    private lateinit var imageDetails : ImageView
    private lateinit var addw_btn : Button
    private lateinit var current_anime : AnimeStorage


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
        imageDetails = view.findViewById(R.id.anime_details_image)
        titleDetails = view.findViewById(R.id.anime_details_title)
        synopsisDetails = view.findViewById(R.id.anime_details_synopsis)
        airingAndTypeDetails = view.findViewById(R.id.anime_details_typeAndAiring)
        addw_btn = view.findViewById(R.id.add_watchlist)

        addw_btn.setOnClickListener {

            val currentuser = FirebaseAuth.getInstance().currentUser
            val animeWithoutSpecialCase :String = current_anime.title.replace(".","").replace("#","").replace("$","").replace("[","").replace("]","").replace("{","").replace("}","")
            if (currentuser != null) {
                currentuser.let {
                    for (profile in it.providerData) {
                        val uid = profile.uid
                        FirebaseDatabase.getInstance("https://nemesis-3d5ff-default-rtdb.europe-west1.firebasedatabase.app")
                        .reference.child("users").child("user$uid")
                            .child("Watchlist").child(animeWithoutSpecialCase)
                            .setValue(current_anime)
                        Toast.makeText(activity,"${current_anime.title} has been added to your watchlist",Toast.LENGTH_SHORT).show()
                        break

                    }

                }

            }

        }
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
                    if (response.body()!!.airing) {
                        val typeAndAiring = response.body()!!.type + "  Airing"
                        airingAndTypeDetails.text = typeAndAiring
                    }else {
                        val typeAndAiring = response.body()!!.type + "  Not Yet Aired"
                        airingAndTypeDetails.text = typeAndAiring
                    }
                    current_anime = AnimeStorage(response.body()!!.url,response.body()!!.image_url, response.body()!!.title,response.body()!!.type,response.body()!!.status,response.body()!!.airing,response.body()!!.synopsis )
                    Picasso.get().load(response.body()!!.image_url).into(imageDetails);
                    titleDetails.text = response.body()!!.title
                    synopsisDetails.text = response.body()!!.synopsis


                }

            }
        })
    }
}

annotation class BindView
