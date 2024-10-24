package com.sample.radiostations.app.presentation.radiostation.shows.component

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radiostations.core.design.system.RadioStationsTheme
import com.sample.radiostations.app.presentation.radiostation.shows.model.RadioStationShowUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun RadioStationsShowsContentView(
    modifier: Modifier,
    radioStationShows: ImmutableList<RadioStationShowUi>
) {
    LazyColumn(
        modifier = modifier.padding(8.dp)
    ) {
        itemsIndexed(
            items = radioStationShows.toList(),
            key = { _: Int, radioStationShow: RadioStationShowUi -> radioStationShow.id },
        ) { _, radioStationShow ->
            Column(
                modifier = Modifier
                    .padding(8.dp)
                    .defaultMinSize(minHeight = 40.dp),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = radioStationShow.title,
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.titleLarge
                )
                HorizontalDivider(color = Color.DarkGray)
                if (radioStationShow.subTitle.isNotEmpty()) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = radioStationShow.subTitle,
                        style = MaterialTheme.typography.bodyLarge,
                    )
                }
                if (radioStationShow.description?.trim()?.isNotEmpty() == true) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = radioStationShow.description,
                        style = MaterialTheme.typography.bodyMedium,
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun RadioStationsShowsContentViewPreview() {
    RadioStationsTheme {
        Surface {
            RadioStationsShowsContentView(
                modifier = Modifier,
                persistentListOf(
                    RadioStationShowUi("1", "title1", "subtitle1", "description1"),
                    RadioStationShowUi("2", "title2", "subtitle2", "description2"),
                    RadioStationShowUi("3", "title3", "", ""),
                    RadioStationShowUi("4", "title4", "subtitle4", "")
                )
            )
        }
    }
}
