package fr.fruitsintelligence.fruitclassifier

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import fr.fruitsintelligence.fruitclassifier.fragments.AddFruitFragment
import fr.fruitsintelligence.fruitclassifier.fragments.CollectionFragment
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository
import fr.fruitsintelligence.fruitclassifier.fragments.HomeFragment
import fr.fruitsintelligence.fruitclassifier.ui.theme.FruitClassifierTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //charger fruiRepository
        val repo= FruitRepository()

        //mettre à jour la liste de fruits
        repo.updateData{
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,CollectionFragment(this))
            transaction.addToBackStack(null)
            transaction.commit()
        }





    }
}

