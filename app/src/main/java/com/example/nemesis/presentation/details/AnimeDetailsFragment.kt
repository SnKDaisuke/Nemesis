package com.example.nemesis.presentation.details

import android.content.ContentValues.TAG
import android.os.Bundle
import android.util.Log
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
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
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
    //public var ide:Int = 0

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_anime_detail, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imageDetails = view.findViewById(R.id.anime_details_image)
        titleDetails = view.findViewById(R.id.anime_details_title)
        synopsisDetails = view.findViewById(R.id.anime_details_synopsis)
        airingAndTypeDetails = view.findViewById(R.id.anime_details_typeAndAiring)
        addw_btn = view.findViewById(R.id.add_watchlist) // ADD/DELETE Button



        addw_btn.setOnClickListener {


            val currentuser = FirebaseAuth.getInstance().currentUser // Récupération de l'utilisateur actuel grâce à l'instance en cours d'utilisation
            // Permet d'éviter les noms d'anime contenant des caractères incompatibles
            val animeWithoutSpecialCase :String = current_anime.title.replace(".","").replace("#","").replace("$","").replace("[","").replace("]","").replace("{","").replace("}","")

            if (currentuser != null) {
                currentuser.let {
                    for (profile in it.providerData) {
                        val uid = profile.uid // Récupération de l'UID Unique de l'user pour lui donner accès aux bonnes données
                        val db = Firebase.firestore // Connexion à la DataBase FireStore

                        db.collection("user$uid").document("$animeWithoutSpecialCase")
                            .get()
                            .addOnCompleteListener(OnCompleteListener<DocumentSnapshot?> { task ->
                                if (task.isSuccessful) {
                                    val document = task.result
                                    if (document!!.exists()) {
                                        db.collection("user$uid").document("$animeWithoutSpecialCase")
                                            .delete()
                                            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!")
                                            }
                                            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e)
                                            }
                                        Toast.makeText(activity,"${current_anime.title} has been deleted from your watchlist",Toast.LENGTH_SHORT).show()
                                        addw_btn.text = "ADD in your Watchlist"
                                    } else {
                                        db.collection("user$uid").document("$animeWithoutSpecialCase")
                                            .set(current_anime)
                                            .addOnSuccessListener {
                                                Log.d(TAG, "DocumentSnapshot added with ID:")
                                            }
                                            .addOnFailureListener { e ->
                                                Log.w(TAG, "Error adding document", e)
                                            }
                                        Toast.makeText(activity,"${current_anime.title} has been added to your watchlist",Toast.LENGTH_SHORT).show()
                                        addw_btn.text = "DELETE from your Watchlist"
                                    }
                                } else {
                                    Log.d(
                                        TAG,
                                        "Failed with: ",
                                        task.exception
                                    )
                                }
                            })
                            //Test
                            db.collection("user$uid")
                                .get()
                                .addOnSuccessListener { result ->
                                    for (document in result) {
                                        Log.d(TAG, "${document.id} => ${document.data}")
                                    }
                                }
                                .addOnFailureListener { exception ->
                                    Log.w(TAG, "Error getting documents.", exception)
                                }



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
                    current_anime = AnimeStorage(response.body()!!.mal_id, response.body()!!.url,response.body()!!.image_url, response.body()!!.title,response.body()!!.type,response.body()!!.status,response.body()!!.airing,response.body()!!.synopsis )
                    Picasso.get().load(response.body()!!.image_url).into(imageDetails);
                    titleDetails.text = response.body()!!.title
                    synopsisDetails.text = response.body()!!.synopsis

                    val currentuser = FirebaseAuth.getInstance().currentUser // Récupération de l'utilisateur actuel grâce à l'instance en cours d'utilisation
                    // Permet d'éviter les noms d'anime contenant des caractères incompatibles
                    val animeWithoutSpecialCase :String = current_anime.title.replace(".","").replace("#","").replace("$","").replace("[","").replace("]","").replace("{","").replace("}","")

                    if (currentuser != null) {
                        currentuser.let {
                            for (profile in it.providerData) {
                                val uid = profile.uid // Récupération de l'UID Unique de l'user pour lui donner accès aux bonnes données
                                val db = Firebase.firestore // Connexion à la DataBase FireStore

                                db.collection("user$uid").document("$animeWithoutSpecialCase")
                                    .get()
                                    .addOnCompleteListener(OnCompleteListener<DocumentSnapshot?> { task ->
                                        if (task.isSuccessful) {
                                            val document = task.result
                                            if (document!!.exists()) {
                                                addw_btn.text = "DELETE from your Watchlist"
                                            } else {
                                                addw_btn.text = "ADD in your Watchlist"
                                            }
                                        } else {
                                            Log.d(
                                                TAG,
                                                "Failed with: ",
                                                task.exception
                                            )
                                        }
                                    })
                                break

                            }

                        }

                    }

                }




            }
        })
    }
}

annotation class BindView
