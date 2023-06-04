package fr.fruitsintelligence.fruitclassifier.adapter

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import fr.fruitsintelligence.fruitclassifier.FruitModel
import fr.fruitsintelligence.fruitclassifier.FruitPopup
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository
import fr.fruitsintelligence.fruitclassifier.MainActivity
import fr.fruitsintelligence.fruitclassifier.R

class FruitAdapter (
    val context:MainActivity,
    private val fruitList:List<FruitModel>,
    private val layoutId:Int
    ):RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    //boite pour ranger tous les composants à controler
    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
       val fruitImage=view.findViewById<ImageView>(R.id.image_item)
        val fruitName:TextView?=view.findViewById(R.id.name_item)
        val fruitDescription:TextView?=view.findViewById(R.id.description_item)
        val likeIcon=view.findViewById<ImageView>(R.id.like_icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view=LayoutInflater
            .from(parent.context)
            .inflate(layoutId,parent,false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int =fruitList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        //recuperer les infos du fruit
        val currentFruit=fruitList[position]
        
        //recuperer le repository
        val repo=FruitRepository()
        //utiliser glide pour récupérer l'image à partir de son lien
        Glide.with(context).load(Uri.parse(currentFruit.imageUrl)).into(holder.fruitImage)


        //mettre à jour le nom et la description du fruit
        holder.fruitName?.text=currentFruit.name
        holder.fruitDescription?.text =currentFruit.description

        //vérifier si le fruit a été liké
        if(currentFruit.liked){
            holder.likeIcon.setImageResource(R.drawable.ic_like)
        }
       else{
            holder.likeIcon.setImageResource(R.drawable.ic_unlike)
       }
        //Rajouter une interaction sur cette étoile
        holder.likeIcon.setOnClickListener{
            //inverse si le bouton est like ou non
            currentFruit.liked=!currentFruit.liked
            //mettre à jour l'objet fruit
            repo.updateFruit(currentFruit)

        }

        //interaction lors du clic sur un fruit
        holder.itemView.setOnClickListener{
            //afficher popup
            FruitPopup(this,currentFruit).show()
        }










    }


}