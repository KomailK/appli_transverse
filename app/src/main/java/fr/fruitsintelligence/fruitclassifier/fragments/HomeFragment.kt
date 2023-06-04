package fr.fruitsintelligence.fruitclassifier.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import fr.fruitsintelligence.fruitclassifier.FruitRepository.Singleton.fruitList
import fr.fruitsintelligence.fruitclassifier.MainActivity
import fr.fruitsintelligence.fruitclassifier.R
import fr.fruitsintelligence.fruitclassifier.adapter.FruitAdapter
import fr.fruitsintelligence.fruitclassifier.adapter.FruitItemDecoration

class HomeFragment (private val context:MainActivity) : Fragment()
{
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater?.inflate(R.layout.fragment_home,container,false)


        //recuperer le recyclerview
        val horizontalRecyclerView= view?.findViewById<RecyclerView>(R.id.horizontal_recycler_view)
        horizontalRecyclerView?.adapter = FruitAdapter(context,fruitList,R.layout.item_horizontal_fruit)

        //recuperer le second recyclerview
        val verticalRecyclerView= view?.findViewById<RecyclerView>(R.id.vertical_recycler_view)
        verticalRecyclerView?.adapter= FruitAdapter(context,fruitList,R.layout.item_vertical_fruit)
        verticalRecyclerView?.addItemDecoration(FruitItemDecoration())
        return view
    }
}