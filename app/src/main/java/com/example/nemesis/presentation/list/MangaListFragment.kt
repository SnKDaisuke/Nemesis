package com.example.nemesis.presentation.list

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.nemesis.R

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MangaListFragment : Fragment() {

    private lateinit var recycler: RecyclerView
    private val adapter = MangaAdapter(listOf())
    private val layoutManager = LinearLayoutManager(context)

        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_manga_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recycler = view.findViewById(R.id.manga_recyclerview)

        recycler.apply {
            layoutManager = this@MangaListFragment.layoutManager
            adapter = this@MangaListFragment.adapter
        }

        val mangalist = arrayListOf<Manga>().apply {
            add(Manga("Attack on Titan"))
            add(Manga("Sword art Online"))
            add(Manga("Oregairu"))
            add(Manga("UwU"))
        }
        adapter.UpdateList(mangalist)
    }
}