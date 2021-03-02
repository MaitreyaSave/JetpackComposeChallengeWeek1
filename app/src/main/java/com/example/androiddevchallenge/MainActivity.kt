/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.Group
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.MyTheme
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeListOfPuppies(10)
        setContent {
            MyTheme {
                MyApp()
            }
        }
    }
}

// Start building your app here!
val puppyList = mutableListOf<Puppy>()

@Composable
fun MyApp() {
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = "Today's top " + puppyList.size + " puppies")
            Column(modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(Color.Cyan),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally) {
                PuppyList(puppyList)
            }
        }
    }
}

@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}

@Preview("Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun DarkPreview() {
    MyTheme(darkTheme = true) {
        MyApp()
    }
}

data class Puppy (val name:String){
    lateinit var color:String
    lateinit var breed:String
    var age_month = 0
}

@Composable
fun PuppyList(puppies: List<Puppy>) {
    puppies.forEach { puppy ->
        Card(
            Modifier
                .padding(4.dp)
                .clickable { /* Do nothing */ }) {
            Text(text = puppy.name,
                Modifier.padding(4.dp))
        }
    }
}




fun initializeListOfPuppies(size:Int){
    val namesList = mutableListOf<String>("Apple", "Beer", "Cider", "Donut", "Eggy", "Falcon", "Gazza", "Hercules", "Ink", "Johnny", "Kotlin", "Luna", "Neon", "Magnolia", "Opal", "Pikachu", "Quartz", "Reggie", "Stella", "Tuffy", "Umbra", "Venice", "Wally", "Xylo", "Yappy", "Zeno")
    val breedsList = listOf<String>("German Shepherd", "Rottweiler", "Pug", "Chihuahua", "Hound", "Labrador", "Terrier", "Golden Retriever", "Beagle", "Pit bull", "Great Dane", "Husky")
    val colorList = listOf<String>("Black", "Brown", "White", "Brown-White", "Black-White", "Black-Brown", "Blonde", "Red")
    val ageMonthList = listOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12)


    for (i in 1..size){
        val puppyName = namesList.random()
        namesList.remove(puppyName)
        val puppy = Puppy(puppyName)
        puppy.age_month = ageMonthList.random()
        puppy.breed = breedsList.random()
        puppy.color = colorList.random()
        puppyList.add(puppy)
    }
}
