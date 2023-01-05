package com.example.humblrvsv

const val CLIENT_ID = "zdwsgJ9mGSF2PtavB-3vjQ"
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
const val ONBOARDING_IS_SHOWN = "onboarding_is_shown"

//раз
//const val ACCESS_KEY = "OOlbm6FJx2pFPfaU8uLWLbsQ1Ez0u1J-tXX5L8WLya8"
//const val SECRET_KEY = "70SAVhXIiZ6ySujad4zR7b4-PZ9TymGOU_H_qDu-9_A"

//два
//const val ACCESS_KEY = "TQBtxa7iGDE6aUYk12mA1mWdM7qLwJrkKgIsNXYEbtY"
//const val SECRET_KEY = "rA8Ntvr_vPSHofWpYz4Ib4coselWLvv8hcwjTv8lOA0"

//три
//const val ACCESS_KEY = "aFYYZK6kx8GMEoIHFhzT27rhwgZZtnENvc0M8mAW7XI"
//const val SECRET_KEY = "cOKBHRzF_cc6Cer9SKQ4gR_Y4vD-YFE2u-YGbLYqEMA"

//четыре
//const val ACCESS_KEY = "mj_2vu5NRd4iwh6pYzpqOymkmK79_WqclNutm5-O2UQ"
//const val SECRET_KEY = "C_vgTr-b2b6-ZdhllFr35MO5uNLk5mxGmFpuoq4-Bt0"
