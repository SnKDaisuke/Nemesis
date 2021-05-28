package com.example.nemesis.presentation.choice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemesis.R
import com.example.nemesis.presentation.list.*

class SeasonChoiceAnimeFragment : Fragment(){


    private lateinit var recycler_choice: RecyclerView

    private val adapter = ChoiceAdapter(listOf(), ::onClickedChoice)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_season_choice_anime, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler_choice = view.findViewById(R.id.anime_choice_recyclerview)

        recycler_choice.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = this@SeasonChoiceAnimeFragment.adapter
        }

        choiceList()

    }

    private fun choiceList() {
        val choicelist: List<Choice> = listOf(Choice.choice1,Choice.choice2)

        adapter.UpdateList(choicelist)
    }

    private fun onClickedChoice(choice: Choice) {
        if (choice.id === 1) {
            findNavController().navigate(R.id.action_fragment_season_choice_to_fragment_anime_list_now)
        }else if (choice.id === 2){
            findNavController().navigate(R.id.action_fragment_season_choice_to_fragment_anime_list_up)
        }
        else{
            Log.wtf("Erreur !", "Erreur !")
        }
    }



}