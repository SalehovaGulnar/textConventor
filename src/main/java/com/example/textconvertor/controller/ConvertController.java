package com.example.textconvertor.controller;

import com.example.textconvertor.model.ConvertModel;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ConvertController {

    @GetMapping("/")
    public String getHome(@ModelAttribute("convertModel") ConvertModel convertModel) {
        return "index";
    }

    @PostMapping("/")
    public String postIndex(@ModelAttribute("convertModel") ConvertModel convertModel,
                            @RequestParam(value="action", required=true) String action) {
        if(convertModel.getText()!= null && !convertModel.getText().isEmpty()) {
            String text = convertModel.getText();
            if(action != null && !action.isEmpty()) {
                if (action.equalsIgnoreCase("sentence"))
                    text = toSentence(text);
                else if (action.equalsIgnoreCase("lowercase"))
                    text = text.toLowerCase();
                else if(action.equalsIgnoreCase("uppercase"))
                    text = text.toUpperCase();
            }
            convertModel.setText(text);
        }
        return "index";
    }

    private String toSentence(String text) {
        String[] textArr = text.split("\\.");
        if(textArr.length >= 0 ) {
            text = "";
            for (int i = 0; i < textArr.length; i++) {
                textArr[i] =  textArr[i].toLowerCase();
                String trimTextArr = textArr[i].toLowerCase().trim();
                int subText = textArr[i].indexOf(trimTextArr);
                if (subText >= 0) {
                    textArr[i] = textArr[i].substring(0, subText) + textArr[i].substring(subText, subText + 1).toUpperCase() + textArr[i].substring(subText + 1);
                }
                text = text + textArr[i] + ".";
            }
        }
        return text;
    }
}
