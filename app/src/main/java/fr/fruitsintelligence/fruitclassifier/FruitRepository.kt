package fr.fruitsintelligence.fruitclassifier

import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import fr.fruitsintelligence.fruitclassifier.FruitRepository.Singleton.databaseRef
import fr.fruitsintelligence.fruitclassifier.FruitRepository.Singleton.fruitList

class FruitRepository {


    object Singleton {
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

    //mettre à jour objet fruit en repo

    fun updateFruit(fruit: FruitModel) {
        databaseRef.child(fruit.id).setValue(fruit)

    }


    //supprimer un fruit de la base
    fun deleteFruit(fruit:FruitModel)= databaseRef.child(fruit.id).removeValue()

}