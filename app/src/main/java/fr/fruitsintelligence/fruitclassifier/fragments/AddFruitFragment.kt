package fr.fruitsintelligence.fruitclassifier.fragments

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.fragment.app.Fragment
import fr.fruitsintelligence.fruitclassifier.MainActivity
import fr.fruitsintelligence.fruitclassifier.R
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository.Singleton.downloadUri

class AddFruitFragment(
    private val context : MainActivity
): Fragment(){


    private var file:Uri?=null
    private var uploadedImage:ImageView?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_fruit, container, false)


        //recuperer upload image
        uploadedImage=view?.findViewById(R.id.preview3_image)

        //recuperation du boutton
        val pickupImageButton = view?.findViewById<Button>(R.id.upload_button)

        //lorsqu'on clique dessus
        pickupImageButton?.setOnClickListener{pickupImage()}

        //récupérer le boutton confirmer
        val confirmButton=view?.findViewById<Button>(R.id.confirm_button)
        confirmButton?.setOnClickListener{sendForm(view)}




        return view
    }

    private fun sendForm(view: View) {
        val repo=FruitRepository()
        repo.uploadImage(file!!)
        {
            val fruitName=view.findViewById<EditText>(R.id.name_input).text.toString()
            val fruitDescription=view.findViewById<EditText>(R.id.description_input).text.toString()
            val fruitCalories=view.findViewById<EditText>(R.id.calorie_input).text.toString()
            val fruitRecettes=view.findViewById<EditText>(R.id.recettes_input).text.toString()
            val downloadImageUrl= downloadUri






        }

    }

    private fun pickupImage() {
        val intent=Intent()
        intent.type="image/"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==47 && resultCode==Activity.RESULT_OK){
            //vérifier si les données sont nulles
            if(data==null || data.data==null)return

            //recuperer l'image
            file=data.data

            //mettre à jour
            uploadedImage?.setImageURI(file)







        }
    }





}