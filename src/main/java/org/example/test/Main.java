package org.example.test;

import org.example.models.recipe;
import org.example.models.review;
import org.example.services.recipeService;
import org.example.services.reviewService;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        recipeService recipeService = new recipeService();
        reviewService reviewService = new reviewService();
        review rev = new review(1, "25", "non");
        review rev1 = new review(2, "2563", "wow");
        review rev2 = new review(3, "2762", "bien");
        review rev3 = new review(4, "2562", "good");
        review rev4 = new review(5, "2862", "excellent");
        reviewService.ajouter(rev);
        reviewService.ajouter(rev1);
        System.out.println("*******************Ajouter review**************");

        for (review r : reviewService.fetch()) {
            System.out.println(r);
        }

        reviewService.modifier(rev1, "SKAN21");
        System.out.println("********************Modification review******************");

        for (review r : reviewService.fetch()) {
            System.out.println(r);
        }

        reviewService.supprimer(rev.getIdrevw());
        System.out.println("**********************SUPRESSION review***************");

        for (review r : reviewService.fetch()) {
            System.out.println(r);
        }

        recipe rec = new recipe(1,"makrouna", "Tmatem", "tourner");
        recipeService.ajouter(rec);
        System.out.println("*********************Ajout RECIPE*****************");

        List<recipe> recipes = recipeService.fetch();
        for (recipe r : recipes) {
            System.out.println(r);
        }

        recipeService.modifier(rec, "nouvelle instruction");
        System.out.println("********************MODIFICATION RECIPE*************************");

        recipes = recipeService.fetch();
        for (recipe r : recipes) {
            System.out.println(r);
        }

        recipeService.supprimer(rec.getIdrecp());
        System.out.println("********************SUPRESSION recipe*************************");

        recipes = recipeService.fetch();
        for (recipe r : recipes) {
            System.out.println(r);
        }

        System.out.println("********************RECHERCHE recipe*************************");
        recipes = recipeService.rechercherecipe(1213);
        for (recipe r : recipes) {
            System.out.println(r);
        }

        System.out.println("********************FILTRAGE recipe*************************");
        recipes = recipeService.filtrerecipe("Tunis");
        for (recipe r : recipes) {
            System.out.println(r);
        }
    }
    }


