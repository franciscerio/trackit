package com.fcerio.trackit.features.main.composable

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.fcerio.trackit.navigation.HomeSections

@Composable
fun MainNavigationBarComposable(
    tabs: List<HomeSections>,
    currentRoute: () -> HomeSections,
    favoriteTracksCount: () -> Int,
    navigateToRoute: (HomeSections) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(modifier = modifier) {
        NavigationBar(
            modifier = Modifier
                .fillMaxWidth()
                .border(width = 1.dp, color = MaterialTheme.colorScheme.outlineVariant)
                .padding(top = 2.dp, end = 2.dp, start = 0.dp, bottom = 0.dp),
            containerColor = MaterialTheme.colorScheme.background
        ) {
            tabs.forEachIndexed { _, item ->
                NavigationBarItem(
                    icon = {
                        BadgedBox(badge = {
                            if (item == HomeSections.FAVORITES)
                                if (favoriteTracksCount() > 0)
                                    Badge {
                                        Text(text = favoriteTracksCount().toString())
                                    }
                        }) {
                            Icon(painter = painterResource(item.icon), contentDescription = item.route)
                        }
                    },
                    label = {
                        Text(modifier = Modifier.wrapContentSize(), text = item.title)
                    },
                    selected = currentRoute() == item,
                    onClick = {
                        navigateToRoute(item)
                    },
                    colors = NavigationBarItemColors(
                        selectedIconColor = MaterialTheme.colorScheme.primary,
                        selectedTextColor = MaterialTheme.colorScheme.primary,
                        selectedIndicatorColor = MaterialTheme.colorScheme.background,
                        unselectedIconColor = MaterialTheme.colorScheme.tertiary,
                        unselectedTextColor = MaterialTheme.colorScheme.tertiary,
                        disabledIconColor = MaterialTheme.colorScheme.surfaceContainer,
                        disabledTextColor = MaterialTheme.colorScheme.surfaceContainer
                    )
                )
            }
        }
    }
}