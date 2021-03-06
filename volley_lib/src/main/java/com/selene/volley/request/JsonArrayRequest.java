/*
 * Copyright (C) 2011 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.selene.volley.request;

import com.selene.volley.core.Listener;
import com.selene.volley.core.NetworkResponse;
import com.selene.volley.core.ParseError;
import com.selene.volley.core.Response;
import com.selene.volley.tool.HttpHeaderParser;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;



/**
 * A request for retrieving a {@link JSONArray} response body at a given URL.
 */
public class JsonArrayRequest extends JsonRequest<JSONArray> {
	/**
	 * Creates a new request.
	 * 
	 * @param method
	 *            the HTTP method to use
	 * @param url
	 *            URL to fetch the JSON from
	 * @param requestBody
	 *            A {@link String} to post with the request. Null is allowed and
	 *            indicates no parameters will be posted along with request.
	 * @param listener
	 *            Listener to receive the JSON response
	 */
	public JsonArrayRequest(int method, String url, String requestBody, Listener<JSONArray> listener) {
		super(method, url, requestBody, listener);
	}
	/**
	 * Creates a new request.
	 * 
	 * @param method
	 *            the HTTP method to use
	 * @param url
	 *            URL to fetch the JSON from
	 * @param listener
	 *            Listener to receive the JSON response
	 */
	public JsonArrayRequest(int method, String url, Listener<JSONArray> listener) {
		this(method, url, null, listener);
	}
	/**
	 * Creates a new request.
	 * 
	 * @param url
	 *            URL to fetch the JSON from
	 * @param listener
	 *            Listener to receive the JSON response or error message
	 */
	public JsonArrayRequest(String url, Listener<JSONArray> listener) {
		this(Method.GET, url, null, listener);
	}

	@Override
	protected Response<JSONArray> parseNetworkResponse(NetworkResponse response) {
		try {
			String jsonString = new String(response.data, HttpHeaderParser.parseCharset(response.headers, PROTOCOL_CHARSET));
			return Response.success(new JSONArray(jsonString), HttpHeaderParser.parseCacheHeaders(response));
		} catch (UnsupportedEncodingException e) {
			return Response.error(new ParseError(e));
		} catch (JSONException je) {
			return Response.error(new ParseError(je));
		}
	}
}
