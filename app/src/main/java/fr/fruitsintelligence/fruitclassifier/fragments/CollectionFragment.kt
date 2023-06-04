package fr.fruitsintelligence.fruitclassifier.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository.Singleton.fruitList
import fr.fruitsintelligence.fruitclassifier.MainActivity
import fr.fruitsintelligence.fruitclassifier.R
import fr.fruitsintelligence.fruitclassifier.adapter.FruitAdapter
import fr.fruitsintelligence.fruitclassifier.adapter.FruitItemDecoration

class CollectionFragment (
    private val context: MainActivity
): Fragment(){

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view= inflater?.inflate(R.layout.fragment_collection, container, false)

        // récupérer la recycle view
        val collectionRecyclerView = view?.findViewById<RecyclerView>(R.id.collection_recycler_list)
        collectionRecyclerView?.adapter= FruitAdapter(context, fruitList.filter { it.liked }, R.layout.item_vertical_fruit)
        collectionRecyclerView?.layoutManager= LinearLayoutManager(context)
        collectionRecyclerView?.addItemDecoration(FruitItemDecoration())
        return view
    }

}
