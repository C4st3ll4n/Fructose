package com.henrique.fructose.model


import com.google.gson.annotations.SerializedName

data class Geocode(
    @SerializedName("link")
    val link: String,
    @SerializedName("locality")
    val locality: Locality,
    @SerializedName("nearby_restaurants")
    val nearbyRestaurants: List<NearbyRestaurant>,
    @SerializedName("popularity")
    val popularity: Popularity
) {
    data class Locality(
        @SerializedName("city_id")
        val cityId: String,
        @SerializedName("city_name")
        val cityName: String,
        @SerializedName("country_id")
        val countryId: String,
        @SerializedName("country_name")
        val countryName: String,
        @SerializedName("entity_id")
        val entityId: String,
        @SerializedName("entity_type")
        val entityType: String,
        @SerializedName("latitude")
        val latitude: String,
        @SerializedName("longitude")
        val longitude: String,
        @SerializedName("title")
        val title: String
    )

    data class Popularity(
        @SerializedName("nightlife_index")
        val nightlifeIndex: String,
        @SerializedName("popularity")
        val popularity: String,
        @SerializedName("top_cuisines")
        val topCuisines: List<String>
    )

    data class NearbyRestaurant(
        @SerializedName("all_reviews")
        val allReviews: List<AllReview>,
        @SerializedName("all_reviews_count")
        val allReviewsCount: String,
        @SerializedName("average_cost_for_two")
        val averageCostForTwo: String,
        @SerializedName("cuisines")
        val cuisines: String,
        @SerializedName("currency")
        val currency: String,
        @SerializedName("deeplink")
        val deeplink: String,
        @SerializedName("events_url")
        val eventsUrl: String,
        @SerializedName("featured_image")
        val featuredImage: String,
        @SerializedName("has_online_delivery")
        val hasOnlineDelivery: String,
        @SerializedName("has_table_booking")
        val hasTableBooking: String,
        @SerializedName("id")
        val id: String,
        @SerializedName("is_delivering_now")
        val isDeliveringNow: String,
        @SerializedName("location")
        val location: Location,
        @SerializedName("menu_url")
        val menuUrl: String,
        @SerializedName("name")
        val name: String,
        @SerializedName("phone_numbers")
        val phoneNumbers: String,
        @SerializedName("photo_count")
        val photoCount: String,
        @SerializedName("photos")
        val photos: List<Photo>,
        @SerializedName("photos_url")
        val photosUrl: String,
        @SerializedName("price_range")
        val priceRange: String,
        @SerializedName("thumb")
        val thumb: String,
        @SerializedName("url")
        val url: String,
        @SerializedName("user_rating")
        val userRating: UserRating
    ) {
        data class Location(
            @SerializedName("address")
            val address: String,
            @SerializedName("city")
            val city: String,
            @SerializedName("country_id")
            val countryId: String,
            @SerializedName("latitude")
            val latitude: String,
            @SerializedName("locality")
            val locality: String,
            @SerializedName("longitude")
            val longitude: String,
            @SerializedName("zipcode")
            val zipcode: String
        )

        data class AllReview(
            @SerializedName("comments_count")
            val commentsCount: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("likes")
            val likes: String,
            @SerializedName("rating")
            val rating: String,
            @SerializedName("rating_color")
            val ratingColor: String,
            @SerializedName("rating_text")
            val ratingText: String,
            @SerializedName("review_text")
            val reviewText: String,
            @SerializedName("review_time_friendly")
            val reviewTimeFriendly: String,
            @SerializedName("timestamp")
            val timestamp: String,
            @SerializedName("user")
            val user: User
        ) {
            data class User(
                @SerializedName("foodie_color")
                val foodieColor: String,
                @SerializedName("foodie_level")
                val foodieLevel: String,
                @SerializedName("foodie_level_num")
                val foodieLevelNum: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("profile_deeplink")
                val profileDeeplink: String,
                @SerializedName("profile_image")
                val profileImage: String,
                @SerializedName("profile_url")
                val profileUrl: String,
                @SerializedName("zomato_handle")
                val zomatoHandle: String
            )
        }

        data class UserRating(
            @SerializedName("aggregate_rating")
            val aggregateRating: String,
            @SerializedName("rating_color")
            val ratingColor: String,
            @SerializedName("rating_text")
            val ratingText: String,
            @SerializedName("votes")
            val votes: String
        )

        data class Photo(
            @SerializedName("caption")
            val caption: String,
            @SerializedName("comments_count")
            val commentsCount: String,
            @SerializedName("friendly_time")
            val friendlyTime: String,
            @SerializedName("height")
            val height: String,
            @SerializedName("id")
            val id: String,
            @SerializedName("likes_count")
            val likesCount: String,
            @SerializedName("res_id")
            val resId: String,
            @SerializedName("thumb_url")
            val thumbUrl: String,
            @SerializedName("timestamp")
            val timestamp: String,
            @SerializedName("url")
            val url: String,
            @SerializedName("user")
            val user: User,
            @SerializedName("width")
            val width: String
        ) {
            data class User(
                @SerializedName("foodie_color")
                val foodieColor: String,
                @SerializedName("foodie_level")
                val foodieLevel: String,
                @SerializedName("foodie_level_num")
                val foodieLevelNum: String,
                @SerializedName("name")
                val name: String,
                @SerializedName("profile_deeplink")
                val profileDeeplink: String,
                @SerializedName("profile_image")
                val profileImage: String,
                @SerializedName("profile_url")
                val profileUrl: String,
                @SerializedName("zomato_handle")
                val zomatoHandle: String
            )
        }
    }
}