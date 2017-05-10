package com.riski.dav.riski;

import org.json.JSONObject;

/**
 * Created by dav on 26.04.17.
 */

interface AsyncResult
{
    void onResult(JSONObject object);
}