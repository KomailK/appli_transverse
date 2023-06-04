package fr.fruitsintelligence.fruitclassifier

import android.app.Dialog
import android.net.Uri
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import fr.fruitsintelligence.fruitclassifier.adapter.FruitAdapter
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository

class FruitPopup(
    private val adapter: FruitAdapter,
    private val currentFruit:FruitModel
): Dialog(adapter.context)
{

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.popup_fruits_details)
        setupComponents()
        setupCloseButton()
        setupDeleteButton()
        setupLikeButton()
    }


    private fun updateLike(button: ImageView)
    {
        if(currentFruit.liked) {
            button.setImageResource(R.drawable.ic_like)
        }

        else{

            button.setImageResource(R.drawable.ic_unlike)
        }

    }




    private fun setupLikeButton() {
        //récupérer
        val likeButton=findViewById<ImageView>(R.id.like_button)
        updateLike(likeButton)

        //interaction

        likeButton.setOnClickListener{
            currentFruit.liked=!currentFruit.liked
            val repo= FruitRepository()
            repo.updateFruit(currentFruit)
            updateLike(likeButton)
        }

    }

    private fun setupDeleteButton() {
        findViewById<ImageView>(R.id.delete_button).setOnClickListener{
            //supprimer un fruit de la base de données
            val repo= FruitRepository()
            repo.deleteFruit(currentFruit)
            dismiss()


        }
    }

    private fun setupCloseButton() {
        findViewById<ImageView>(R.id.close_button).setOnClickListener{
            // fermer la popup
            dismiss()
        }

    }

    private fun setupComponents() {
        //actualiser l'image du fruit
        val fruitImage=findViewById<ImageView>(R.id.image_item)
        Glide.with(adapter.context).load(Uri.parse(currentFruit.imageUrl)).into(fruitImage)

        //actualisser le nom du fruit
        findViewById<TextView>(R.id.popup_fruit_name).text=currentFruit.name

        //actualisser la description du fruit
        findViewById<TextView>(R.id.popup_fruit_description_subtitle).text=currentFruit.description


        //actualisser le nombre de calories du fruit
        findViewById<TextView>(R.id.popup_fruit_calorie_subtitle).text= currentFruit.calories

        //actualisser la description des recettes du fruit
        findViewById<TextView>(R.id.popup_fruit_recette_subtitle).text= currentFruit.recettes







    }


}