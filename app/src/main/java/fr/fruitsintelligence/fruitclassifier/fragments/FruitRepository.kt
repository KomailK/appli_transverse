package fr.fruitsintelligence.fruitclassifier.fragments

import android.net.Uri
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.UploadTask
import fr.fruitsintelligence.fruitclassifier.FruitModel
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository.Singleton.databaseRef
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository.Singleton.fruitList
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository.Singleton.storageReference
import java.util.UUID

class FruitRepository {


    object Singleton {

        // donner le ien pour acceder au bucket
        private val BUCKET_URL: String="gs://fruitclassifier-80543.appspot.com"

        //se connecter à l'espace de stockage
        val storageReference=FirebaseStorage.getInstance().getReferenceFromUrl(BUCKET_URL)


        //se connecter à la référence Fruits

        val databaseRef = FirebaseDatabase.getInstance().getReference("fruits")


        //créer une liste qui va contenir nos fruits

        val fruitList = arrayListOf<FruitModel>()


    }

    fun updateData(callback: () ->Unit) {
        //absorber les données récupérer dans la databaseRef-@ liste de fuits

        databaseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

                //retirer les anciennes
                fruitList.clear()
                //récolter la liste
                for (ds in snapshot.children) {
                    //construire un objet fruit
                    val fruit = ds.getValue(FruitModel::class.java)
                    //verifier que le fruit n'est pas nulle
                    if (fruit != null) {
                        //ajouter fruit à la liste
                        fruitList.add(fruit) }
                }
                //actionner le callback
                callback()
            }

            override fun onCancelled(error: DatabaseError) {}


        })

    }


    //créer une fonction pour envoyer des fichiers sur le storage
    fun uploadImage(file:Uri)
    {
        //vérifier que le fichier n'est pas null

    }



    //mettre à jour objet fruit en repo

    fun updateFruit(fruit: FruitModel) {
        databaseRef.child(fruit.id).setValue(fruit)

    }


    //supprimer un fruit de la base
    fun deleteFruit(fruit: FruitModel)= databaseRef.child(fruit.id).removeValue()

}