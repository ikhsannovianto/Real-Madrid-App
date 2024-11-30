package com.l0122077.ikhsanari.responsi1

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.MenuProvider
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ListFragment : Fragment() {
    private lateinit var rvPlayers: RecyclerView
    private val list = ArrayList<Item>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val toolbar = view.findViewById<Toolbar>(R.id.toolbar)
        (requireActivity() as AppCompatActivity).setSupportActionBar(toolbar)

        rvPlayers = view.findViewById(R.id.rv_players)
        rvPlayers.setHasFixedSize(true)
        list.addAll(getListPlayers())
        showRecyclerList()

        // Aktifkan action bar pada ListFragment
        (requireActivity() as AppCompatActivity).addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.option_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_list -> {
                        rvPlayers.layoutManager = LinearLayoutManager(requireContext())
                        true
                    }
                    R.id.action_grid -> {
                        rvPlayers.layoutManager = GridLayoutManager(requireContext(), 2)
                        true
                    }
                    else -> false
                }
            }
        }, viewLifecycleOwner, Lifecycle.State.RESUMED)
    }

    fun getListPlayers(): ArrayList<Item> {
        val dataName = resources.getStringArray(R.array.data_name)
        val dataDesc = resources.getStringArray(R.array.data_desc)
        val dataImg = resources.obtainTypedArray(R.array.data_img)
        val listPlayer = ArrayList<Item>()
        for (i in dataName.indices) {
            val player = Item(dataName[i], dataDesc[i], dataImg.getResourceId(i, -1))
            listPlayer.add(player)
        }
        dataImg.recycle()
        return listPlayer
    }

    private fun showRecyclerList() {
        rvPlayers.layoutManager = LinearLayoutManager(requireContext())
        val listPlayerAdapter = ItemAdapter(list)
        rvPlayers.adapter = listPlayerAdapter
        listPlayerAdapter.setOnItemClickCallback(object : ItemAdapter.OnItemClickCallback {
            override fun onItemClicked(data: Item) {
                showSelectedPlayer(data)
            }
        })
    }

    private fun showSelectedPlayer(player: Item) {
        Toast.makeText(requireContext(), "${player.name} is selected", Toast.LENGTH_SHORT).show()
        val intent = Intent(requireContext(), DetailedActivity::class.java).apply {
            putExtra("player_image_id", player.img)
            putExtra("player_name", player.name)
            putExtra("player_desc", player.desc)
        }
        startActivity(intent)
    }
}