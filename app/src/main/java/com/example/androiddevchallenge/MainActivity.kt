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
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.MyTheme

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
    val openDialog = remember { mutableStateOf(false)  }
    val clickedPuppy = remember { mutableStateOf(puppyList.get(0)) }
    Surface(color = MaterialTheme.colors.background) {
        Column(modifier = Modifier
            .fillMaxWidth(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = "Today's top " + puppyList.size + " puppies",
                style = TextStyle(fontSize = 24.sp),
                modifier = Modifier
                    .padding(10.dp)
            )
            if(openDialog.value){
                PuppyDetails(clickedPuppy.value, openDialog)
            }
            else {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    PuppyList(puppyList, openDialog, clickedPuppy)
                }
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
    var image = 0
}
@Composable
fun PuppyList(puppies: List<Puppy>, showPuppyDetails: MutableState<Boolean>, clickedPuppy: MutableState<Puppy>) {
    puppies.forEach { puppy ->
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(4.dp)
                .background(Color(R.color.purple_500))
                .clickable(onClick = {
                    showPuppyDetails.value = true
                    clickedPuppy.value = puppy
                }),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
//

            Text(
                text = puppy.name,
                color = Color(0xFF3700B3),
                modifier = Modifier
                    .offset(10.dp, 0.dp)
                    .weight(1f)
                    .wrapContentWidth(Alignment.Start),
            )
            Text(
                text = puppy.breed,
                style = TextStyle(color = Color.White),
                modifier = Modifier
                    .offset(-10.dp, 0.dp)
                    .weight(1f)
                    .wrapContentWidth(Alignment.End)

            )
        }
    }
}

@Composable
fun PuppyDetails(puppy: Puppy, showPuppyDetails: MutableState<Boolean>) {
    Image(
        painter = painterResource(id = puppy.image),
        contentDescription = "Puppy image",
        modifier = Modifier
            .height(200.dp)
            .padding(5.dp)
    )
    Text(text = "Name: "+puppy.name, modifier = Modifier.padding(4.dp))
    Text("Breed: "+puppy.breed, modifier = Modifier.padding(4.dp))
    Text("Color: "+puppy.color, modifier = Modifier.padding(4.dp))
    Text("Age: "+puppy.age_month + " months", modifier = Modifier.padding(4.dp))
    
    Button(onClick = { showPuppyDetails.value = false }, modifier = Modifier.padding(24.dp)) {
        Text(text = "Back")
    }
}


fun initializeListOfPuppies(size:Int){
    val namesList = mutableListOf<String>("Apple", "Beer", "Cider", "Donut", "Eggy", "Falcon", "Gazza", "Hercules", "Ink", "Johnny", "Kotlin", "Luna", "Neon", "Magnolia", "Opal", "Pikachu", "Quartz", "Reggie", "Stella", "Tuffy", "Umbra", "Venice", "Wally", "Xylo", "Yappy", "Zeno")
    val breedsList = listOf<String>("German Shepherd", "Rottweiler", "Pug", "Chihuahua", "Hound", "Labrador", "Terrier", "Golden Retriever", "Beagle", "Pit bull", "Great Dane", "Husky")
    val colorList = listOf<String>("Black", "Brown", "White", "Brown-White", "Black-White", "Black-Brown", "Blonde", "Red")
    val ageMonthList = listOf<Int>(1,2,3,4,5,6,7,8,9,10,11,12)

    val drawablePath = R.drawable.p1
    for (i in 1..size){
        val puppyName = namesList.random()
        namesList.remove(puppyName)
        val puppy = Puppy(puppyName)
        puppy.age_month = ageMonthList.random()
        puppy.breed = breedsList.random()
        puppy.color = colorList.random()
        val picDrawable = PicDrawable.values()[i % size]
        puppy.image = picDrawable.drawable
        puppyList.add(puppy)
    }
}
enum class PicDrawable (val drawable: Int){
    P1(R.drawable.p1),
    P2(R.drawable.p2),
    P3(R.drawable.p3),
    P4(R.drawable.p4),
    P5(R.drawable.p5),
    P6(R.drawable.p6),
    P7(R.drawable.p7),
    P8(R.drawable.p8),
    P9(R.drawable.p9),
    P10(R.drawable.p10),
    P11(R.drawable.p11),
    P12(R.drawable.p12),
    P13(R.drawable.p13),
    P14(R.drawable.p14),
    P15(R.drawable.p15),
    P16(R.drawable.p16)
}
