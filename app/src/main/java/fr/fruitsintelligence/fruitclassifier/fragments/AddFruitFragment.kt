package fr.fruitsintelligence.fruitclassifier.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.fragment.app.Fragment
import fr.fruitsintelligence.fruitclassifier.MainActivity
import fr.fruitsintelligence.fruitclassifier.R

class AddFruitFragment(
    private val context : MainActivity
): Fragment(){


    private var uploadedImage:ImageView?=null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater?.inflate(R.layout.fragment_add_fruit, container, false)


        //recuperer upload image

        uploadedImage=view?.findViewById(R.id.preview2_image)

        //recuperation du boutton
        val pickupImageButton = view?.findViewById<Button>(R.id.upload_button)

        //lorsqu'on clique dessus
        pickupImageButton?.setOnClickListener{pickupImage()}






        return view
    }

    private fun pickupImage() {
        val intent=Intent()
        intent.type="image/"
        intent.action=Intent.ACTION_GET_CONTENT
        startActivityForResult(Intent.createChooser(intent,"Select Picture"),47)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if(requestCode==47 && resultCode==Activity.RESULT_OK){}
    }





}