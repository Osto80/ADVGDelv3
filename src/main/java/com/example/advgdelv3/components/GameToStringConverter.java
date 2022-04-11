package com.example.advgdelv3.components;

import com.example.advgdelv3.entities.Game;
import com.vaadin.flow.data.converter.Converter;
import org.springframework.core.convert.ConversionException;
//import org.springframework.core.convert.converter.Converter;

import java.util.Locale;


/*public class GameToStringConverter implements Converter<Game, String> {

    public Game convertToModel(String text, Locale locale)throws ConversionException{
        if(text == null){
            return null;
        }
        String[] parts = text.split(" ");
    }

    public String convertToPresentation(Game game, Locale locale)throws ConversionException{
        if(game.getGameTitle() == null){
            return null;
        }else{
            return game.getGameTitle();
        }
    }

}

 */
