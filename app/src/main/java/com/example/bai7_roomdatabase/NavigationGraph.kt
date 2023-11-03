import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.bai7_roomdatabase.ContactDetailScreen
import com.example.bai7_roomdatabase.ContactListScreen

sealed class NavRoute(val route: String){
    object LIST_SCREEN : NavRoute("contactlist")
    object DETAIL_SCREEN : NavRoute("contactdetail")
}
@Composable
fun NavigationGraph(navController : NavHostController){
    NavHost(navController = navController, 
        startDestination = NavRoute.LIST_SCREEN.route){
        composable(NavRoute.LIST_SCREEN.route){
            ContactListScreen(navController)
        }
        composable(NavRoute.DETAIL_SCREEN.route+"?id={id}",
            arguments = listOf(navArgument("id"){
                nullable=true
            })
        ){
            var id = it.arguments?.getString("id")
            if(id!=null){
                ContactDetailScreen(navController = navController,id.toInt())
            }
            else{
                ContactDetailScreen(navController = navController)
            }
        }
    }

}