package com.sample.radiostations.app.presentation.radiostation.list.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.radiostations.core.design.system.RadioStationsTheme
import com.sample.radiostations.app.presentation.radiostation.list.model.RadioStationUi
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Composable
fun RadioStationsContentView(
    modifier: Modifier,
    radioStations: ImmutableList<RadioStationUi>,
    onClickRadioStation: (String) -> Unit
) {
    LazyColumn(
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        contentPadding = PaddingValues(8.dp),
    ) {
        itemsIndexed(
            items = radioStations.toList(),
            key = { _: Int, radioStation: RadioStationUi -> radioStation.id },
        ) { _, radioStation ->
            OutlinedCard(
                modifier = Modifier
                    .clickable { onClickRadioStation(radioStation.id) },
                elevation = CardDefaults.elevatedCardElevation()
            ) {
                Column(
                    modifier = Modifier
                        .padding(8.dp)
                        .defaultMinSize(minHeight = 60.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = radioStation.title,
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.titleLarge
                    )
                    radioStation.baseline?.let {
                        Spacer(modifier.padding(top = 8.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = it,
                            style = MaterialTheme.typography.bodyLarge,
                        )
                    }
                    if (radioStation.description.isNotEmpty()) {
                        Spacer(modifier.padding(top = 8.dp))
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            text = radioStation.description,
                            style = MaterialTheme.typography.bodyMedium,
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun RadioStationsContentViewPreview() {
    RadioStationsTheme {
        Surface {
            RadioStationsContentView(
                modifier = Modifier,
                persistentListOf(
                    RadioStationUi("1", "title1", "baseline1", "description1description1description1description1description1description1description1description1description1"),
                    RadioStationUi("2", "title2", null, "description2"),
                    RadioStationUi("3", "title3", "baseline3", ""),
                    RadioStationUi("4", "title4", null, "")
                ),
                onClickRadioStation = {}
            )
        }
    }
}
