package com.example.meow.data.local

import com.example.meow.domain.model.BreedInfo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Created by Bharath on 10/29/2022.
 */
class CatsLocalDataSource @Inject constructor(
    private val catsDatabase: CatsDatabase
) {
    val dao = catsDatabase.dao
//    val catsDataStream = dao.searchCatsListing("").map { catList ->
//        val breedsList = mutableListOf<BreedInfo>()
//        catList.forEach {
//            breedsList.add(
//                BreedInfo(
//                    breedName = it.breedName,
//                    imageUrl = it.imageUrl,
//                    origin = it.origin,
//                    nature = it.nature,
//                    description = it.description,
//                    lifeSpan = it.lifeSpan,
//                    alternativeNames = it.alternativeNames,
//                    ratings = it.ratings
//                )
//            )
//        }
//        breedsList.toList()
//    }
}