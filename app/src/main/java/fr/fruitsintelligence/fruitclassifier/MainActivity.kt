package fr.fruitsintelligence.fruitclassifier

import android.os.Bundle
import android.widget.TextView
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
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import fr.fruitsintelligence.fruitclassifier.fragments.AddFruitFragment
import fr.fruitsintelligence.fruitclassifier.fragments.CollectionFragment
import fr.fruitsintelligence.fruitclassifier.fragments.FruitRepository
import fr.fruitsintelligence.fruitclassifier.fragments.HomeFragment
import fr.fruitsintelligence.fruitclassifier.ui.theme.FruitClassifierTheme

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadFragment(HomeFragment(this),R.string.home_page_title)

        //importer la barre de navigation
        val navigationView=findViewById<BottomNavigationView>(R.id.navigation_view)
        navigationView.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home_page->{
                    loadFragment(HomeFragment(context = this),R.string.home_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.collection_page->{
                    loadFragment(CollectionFragment(context = this),R.string.collection_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.add_fruit_page->{
                    loadFragment(AddFruitFragment(context = this),R.string.add_fruit_page_title)
                    return@setOnNavigationItemSelectedListener true
                }

                else -> false
            }
        }


    }

    private fun loadFragment(fragment: Fragment,string: Int) {
        //charger fruiRepository
        val repo= FruitRepository()

        //actualiser le titre de la page
        findViewById<TextView>(R.id.page_title).text=resources.getString(string)

        //mettre à jour la liste de fruits
        repo.updateData{
            //injecter le fragment dans notre boite (fragment_container)
            val transaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.fragment_container,fragment)
            transaction.addToBackStack(null)
            transaction.commit()
        }



    }
}

