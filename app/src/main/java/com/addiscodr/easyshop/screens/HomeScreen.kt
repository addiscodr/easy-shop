package com.addiscodr.easyshop.screens


import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import com.addiscodr.easyshop.pages.CartPage
import com.addiscodr.easyshop.pages.FavoritePage
import com.addiscodr.easyshop.pages.HomePage
import com.addiscodr.easyshop.pages.ProfilePage
import com.google.android.play.integrity.internal.t
import org.w3c.dom.Text


@Composable
fun HomeScreen(modifier: Modifier = Modifier, navController: NavController){

    val navItemList = listOf(
        NavItem(label = "Home", icon = Icons.Default.Home),

        NavItem(label = "Favorite", icon = Icons.Default.Favorite),

        NavItem(label = "Cart", icon = Icons.Default.ShoppingCart),

        NavItem(label = "Profile", icon = Icons.Default.Person),

    )

    var selectedIndex by remember {
        mutableStateOf(value = 0)
    }

    Scaffold (
        bottomBar = {
            NavigationBar() {
                navItemList.forEachIndexed { index, navItem ->
                    NavigationBarItem(
                        selected = index == selectedIndex,
                        onClick = {
                            selectedIndex = index
                        },
                        icon = {
                            Icon(imageVector = navItem.icon, contentDescription = navItem.label)
                        },
                        label = {
                            Text(text = navItem.label)
                        }

                    )
                }
            }
        }
    ){
        ContentScreen(modifier = Modifier.padding(it), selectedIndex)
    }
}

@Composable
fun ContentScreen(modifier: Modifier = Modifier, selectedIndex : Int){
    when(selectedIndex){
        0 -> HomePage()
        1 -> FavoritePage()
        2 -> CartPage()
        3 -> ProfilePage()
    }

}

data class NavItem (
    val  label: String,
    val icon: ImageVector
)