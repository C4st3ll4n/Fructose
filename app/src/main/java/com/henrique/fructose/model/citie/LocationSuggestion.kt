package com.henrique.fructose.model.citie


import com.google.gson.annotations.SerializedName

data class LocationSuggestion(
    @SerializedName("country_flag_url")
    val countryFlagUrl: String?,
    @SerializedName("country_id")
    val countryId: Int?,
    @SerializedName("country_name")
    val countryName: String?,
    @SerializedName("discovery_enabled")
    val discoveryEnabled: Int?,
    @SerializedName("has_new_ad_format")
    val hasNewAdFormat: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("is_state")
    val isState: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("should_experiment_with")
    val shouldExperimentWith: Int?,
    @SerializedName("state_code")
    val stateCode: String?,
    @SerializedName("state_id")
    val stateId: Int?,
    @SerializedName("state_name")
    val stateName: String?
)