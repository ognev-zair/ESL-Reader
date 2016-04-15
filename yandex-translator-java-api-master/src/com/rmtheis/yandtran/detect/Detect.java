/*
 * Copyright 2013 Robert Theis
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
package com.rmtheis.yandtran.detect;

import java.net.URL;
import java.net.URLEncoder;

import com.rmtheis.yandtran.ApiKeys;
import com.rmtheis.yandtran.YandexTranslatorAPI;
import com.rmtheis.yandtran.language.Language;
import com.rmtheis.yandtran.translate.Translate;

/**
 * Provides an interface to the Yandex Translator Detect service method
 */
public final class Detect extends YandexTranslatorAPI {
    private static final String SERVICE_URL = "https://translate.yandex.net/api/v1.5/tr.json/detect?";
    private static final String DETECTION_LABEL = "lang";
    
    // prevent instantiation
    private Detect(){};

    /**
	  * Detects the language of a supplied String.
	  * 
	  * @param text The String to detect the language of.
	  * @return A String containing the language
	  * @throws Exception on error.
	  */
	  public static Language execute(final String text) throws Exception {
	    validateServiceState(text); 
	    final String params = 
	        PARAM_API_KEY + URLEncoder.encode(apiKey,ENCODING) 
	        + PARAM_TEXT + URLEncoder.encode(text,ENCODING);
	    final URL url = new URL(SERVICE_URL + params);
      return Language.fromString(retrievePropString(url, DETECTION_LABEL));
	  }
        
    private static void validateServiceState(final String text) throws Exception {
    	final int byteLength = text.getBytes(ENCODING).length;
        if(byteLength>10240) { // TODO What is the maximum text length allowable for Yandex?
            throw new RuntimeException("TEXT_TOO_LARGE - Yandex Translator (Detect) can handle up to 10,240 bytes per request");
        }
        validateServiceState();
    }
    
    public static void main(String[] args) {
      try {
        Translate.setKey(ApiKeys.YANDEX_API_KEY);
        Language translation = Detect.execute("The quick brown fox jumps over the lazy dog.");
        System.out.println("Detected: " + translation.toString());
      } catch (Exception e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
      }
    }

}
