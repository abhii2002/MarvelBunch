package com.example.marvelbunch.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import coil.load
import com.example.marvelbunch.R
import com.example.marvelbunch.constants.Constants
import com.example.marvelbunch.databinding.ActivityEventsDetailBinding
import com.example.marvelbunch.models.Characters
import com.example.marvelbunch.models.Events
import com.google.gson.Gson

class EventsDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEventsDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEventsDetailBinding.inflate(layoutInflater)

        setContentView(binding.root)


        var eventsModel: Events? = null

        if(intent.hasExtra(Constants.EVENTS_DETAILS)){
            val gson = Gson()
            eventsModel = gson.fromJson(intent.getStringExtra(Constants.EVENTS_DETAILS), Events::class.java)


        }

        if (eventsModel != null){
            val path = eventsModel.thumbnail.path
            val extension = eventsModel.thumbnail.extension
            val image = "${path}.${extension}"
            binding.tvTitle.text = eventsModel.title
            binding.ivCharacter.load(image)
            binding.tvDescription.text = eventsModel.description
            binding.tvCreators.text = eventsModel.creators.available.toString()
            binding.tvCharacters.text = eventsModel.characters.available.toString()
            binding.tvStories.text = eventsModel.stories.available.toString()
            binding.tvComics.text = eventsModel.comics.available.toString()
        }
    }
}