package com.example.humblrvsv

const val CLIENT_ID = "zdwsgJ9mGSF2PtavB-3vjQ"
const val CLIENT_SECRET = ""
const val RESPONSE_TYPE = "code"
const val STATE = "my_state"
const val REDIRECT_URI = "https://humblrvsv/redirect"
const val DURATION = "permanent"
const val SCOPE =
    "identity edit flair history modconfig" +
            " modflair modlog modposts modwiki " +
            "mysubreddits privatemessages read report " +
            "save submit subscribe vote wikiedit wikiread"

const val CALL =
    "https://www.reddit.com/api/v1/authorize.compact" +
            "?client_id=" + CLIENT_ID +
            "&response_type=" + RESPONSE_TYPE +
            "&state=" + STATE +
            "&redirect_uri=" + REDIRECT_URI +
            "&duration=" + DURATION +
            "&scope=" + SCOPE


const val TOKEN_SHARED_NAME = "pref_token"
const val TOKEN_SHARED_KEY = "token"
const val TOKEN_ENABLED_KEY = "token_enabled"
const val ONBOARDING_SHARED_NAME = "onboarding_name"
const val ONBOARDING_IS_SHOWN = "onboarding_is_shown"
const val SHARED_SELECTED_TAB_NAME = "selected_tab"
const val SHARED_SELECTED_TAB_MODEL = "selected_model"
const val SHARED_SELECTED_TAB_SOURCE = "selected_source"
const val SHARED_SELECTED_TAB_SAVED_MODEL = "selected_saved_model"
const val SHARED_PROFILE = "pref_profile"
const val SHARED_PROFILE_USER_NAME = "user_name"

