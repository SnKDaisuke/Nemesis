package com.example.nemesis.presentation.choice

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemesis.R
import com.example.nemesis.presentation.Singletons.Singletons
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

        val spinner: Spinner = view.findViewById(R.id.spinner)
        val spinnerYear = arrayOf("1958","1959","1960","1961","1962","1963","1964","1965","1966","1967","1968","1969","1970","1971","1972","1973","1974","1975","1976","1977","1978","1979","1980","1981","1982","1983","1984","1985","1986","1987","1988","1989","1990","1991","1992","1993","1994","1995","1996","1997","1998","1999","2000","2001","2002","2003","2004","2005","2006","2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018","2019","2020","2021")
        val spinner2: Spinner = view.findViewById(R.id.spinner2)
        val spinnerSeason = arrayOf("fall","winter","spring","summer")
        val search_btn: Button = view.findViewById(R.id.searching_season)
        val watchlist_btn: Button = view.findViewById(R.id.my_watchlist)

        // Create an ArrayAdapter using the string array and a default spinner layout
        context?.let {
            spinner.adapter = ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,spinnerYear)
            spinner2.adapter = ArrayAdapter<String>(it,android.R.layout.simple_list_item_1,spinnerSeason)
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Singletons.y = spinnerYear.get(position)
            }
        }
        spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                TODO("Not yet implemented")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
               Singletons.s = spinnerSeason.get(position)
            }
        }

        search_btn.setOnClickListener {
                findNavController().navigate(R.id.action_fragment_season_choice_to_fragment_custom_anime_list)

        }

        watchlist_btn.setOnClickListener {
            findNavController().navigate(R.id.action_fragment_season_choice_to_fragment_custom_anime_list)

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