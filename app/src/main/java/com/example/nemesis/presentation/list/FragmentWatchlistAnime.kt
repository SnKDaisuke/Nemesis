package com.example.nemesis.presentation.list

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import com.example.nemesis.presentation.api.AnimeResponseDB
import com.example.nemesis.presentation.api.AnimeStorage
import com.example.nemesis.presentation.api.AnimeStorageDB
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.getField
import com.google.firebase.ktx.Firebase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class FragmentWatchlistAnime : Fragment(){

    private lateinit var recycler: RecyclerView

    private val adapter = AnimeAdapterDB(mutableListOf(), ::onClickedAnime)

    //private val layoutManager = LinearLayoutManager(context) // créer un bug lors de l'appui de la touche retour en arrière

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_list_now, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.anime_next_recyclerview)

        recycler.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@FragmentWatchlistAnime.adapter
        }

        callAnimeListApi()

    }

    private fun callAnimeListApi() {
        val currentuser = FirebaseAuth.getInstance().currentUser
        var listDB: MutableList<AnimeStorage> = mutableListOf()
        var animeDB: AnimeStorage
        if (currentuser != null) {
            currentuser.let {
                for (profile in it.providerData) {
                    val uid = profile.uid

                    val db = Firebase.firestore // Connexion à la DataBase FireStore
                    db.collection("user$uid")
                        .get()
                        .addOnSuccessListener { result ->
                            for (document in result) {
                                val mal_id = document.getField<Int>("mal_id") as Int
                                mal_id.toString()
                                val url = document.get("url") as String
                                url
                                val img_url = document.get("image_url") as String
                                img_url
                                val title = document.get("title") as String
                                title
                                val type = document.get("type") as String
                                type
                                val status = document.get("status") as String
                                status
                                val airing = document.get("airing") as Boolean
                                airing.toString()
                                val synopsis = document.get("synopsis") as String
                                synopsis

                                animeDB = AnimeStorage(mal_id,url,img_url,title,type,status,airing,synopsis)
                                animeDB.title
                                animeDB.image_url
                                listDB.add(animeDB)
                                listDB.size
                                val list: AnimeResponseDB = AnimeResponseDB(listDB)
                                val animeResponseDB: AnimeResponseDB = list
                                adapter.UpdateListDB(animeResponseDB.anime)



                            }

                        }
                        .addOnFailureListener { exception ->
                            Log.d(TAG, "Error getting documents: ", exception)
                        }




                }
            }
        }

    }

    private fun onClickedAnime(anime: AnimeStorage) {
        findNavController().navigate(R.id.action_Watchlist_to_AnimeDetails, bundleOf("anime_id" to anime.mal_id))
    }

}
