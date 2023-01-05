package com.leeshuyun.app.workshop12_randomNum.controller;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.leeshuyun.app.workshop12_randomNum.exception.RandNoException;
import com.leeshuyun.app.workshop12_randomNum.models.Generate;

// this is the controller that allows us to get paths like http://localhost:8080/rand, 
// or http://localhost:8080/rand/show
@Controller
@RequestMapping(path="/rand") // use this like a superclass group for your pages. sensei says that's industry standard
public class GenRandNoController {
    /*
     * Redirect  to generate.html and show input form
     */
    //there's 3 ways to get a index.html. by @GetMapping, by @RequestMapping, by directly loading index.html
    //if we want localhost:8080/cat, both @GetMapping and @RequestMapping are able to serve u that page
    // if we want localhost:8080/cat/food, same as /cat we can just use /cat/food for both, but we can nest @GetMapping/food under @RequestMapping/cat 
    @GetMapping(path="/show") //all pages have a get and post method
    public String showRandForm(Model model){
        //bind the noOfRandNo to the text field
        Generate g = new Generate();
        model.addAttribute("generateObj", g);
        return "generate";
    }

    //use by appending generate?numberval=3 to url
    @GetMapping(path="/generate")
    public String generateRandNumByGet(@RequestParam Integer numberVal, Model model){
        this.randomizeNum(model, numberVal.intValue());
        return "result";
    }

    //use by appending generate/number eg generate/3
    @GetMapping(path="/generate/{numberVal}")
    public String generateRandNumByGetPV(@PathVariable Integer numberVal, Model model){
        this.randomizeNum(model, numberVal.intValue());
        return "result";
    }

        // returns the results page after we input number in /generate page
    @PostMapping(path="/generate")
    public String postRandNum(@ModelAttribute Generate generate, Model model){
        // modelAttribute tells springboot to bring the class called Generate automatically
        this.randomizeNum(model, generate.getNumberVal());
        return "result";
    }

    //for getting the rand numbers and returning the right imgs
    private void randomizeNum(Model model, Integer noOfGenerateNo){
        int maxGenNo = 30;
        String[] imgNumbers = new String[maxGenNo+1]; //keep all generated file names

        //validate if the inputted number is out of the range 0 to 30, bc we only have imgs till 30
        if(noOfGenerateNo < 1 || noOfGenerateNo > maxGenNo){
            throw new RandNoException();
        }

        //generate all the number images and store into String[] imgNumbers
        for (int i = 0; i < maxGenNo + 1; i++) {
            imgNumbers[i] = "number" + i + ".jpg";
        }

        //list to keep all the generated numbers, set makes it unique
        List<String> selectedImg = new ArrayList<String>();
        Random rnd = new Random();
        Set<Integer> uniqueResult = new LinkedHashSet<Integer>(); //allows you to store only unique items
        //any dupes will be automatically deleted for us, so we keep adding until the set is full
        while (uniqueResult.size() < noOfGenerateNo){
            Integer resultOfRand = rnd.nextInt(maxGenNo);
            uniqueResult.add(resultOfRand);
            System.out.println("hello we have a new unique result");
        }

        //we iterate through the list and get the associated image for each number we alr generated
        Iterator<Integer> it = uniqueResult.iterator();
        Integer currElem = null;
        while(it.hasNext()){
            currElem = it.next();
            selectedImg.add(imgNumbers[currElem.intValue()]);
        }


        model.addAttribute("numberRandomNum", noOfGenerateNo);
        model.addAttribute("randNumResult", selectedImg.toArray());


    }
}
