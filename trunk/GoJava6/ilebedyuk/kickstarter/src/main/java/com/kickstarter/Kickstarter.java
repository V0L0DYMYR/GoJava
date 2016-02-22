package com.kickstarter;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by Игорь on 05.02.2016.
 */
public class Kickstarter {
    private Categories categories;
    private Projects projects;
    private IO io;

    public Kickstarter(Categories categories, Projects projects, IO io) {
        this.categories = categories;
        this.projects = projects;
        this.io = io;
    }

    public void run() {

        QuoteGenerator generator = new QuoteGenerator(new Random());
        println(generator.nextQuote());

        while (true) {
            askCategory();
            int menu = io.read();
            Category category = chooseCategory(menu);
            if (category == null) {
                continue;
            }
            Project[] found = projects.getProjects(category);
            printProjects(found);

            projectMenu(found);
        }
    }

    private void projectMenu(Project[] found) {
        while (true){
            askProject(found);
            int menu = io.read();
            if (menu == 0) {
                break;
            }
            Project project = chooseProject(menu, found);
            if (project == null) {
                continue;
            }
            chooseProject(project);
            printProjectDetails(project);
        }
    }

    private Project chooseProject(int menu, Project[] found) {
        if (menu <= 0 || found.length < menu) {
            println("Неверный индекс меню " + menu);
            return null;
        }
        return found[menu - 1];
    }

    private void println(String message) {
        io.print(message + "\n");
    }

    private void askProject(Project[] found) {
        if (found.length == 0) {
            println("Проектов в категории нет!. Нажмите 0 - для выхода");
        } else {
            int from = 1;
            int to = found.length;
            println("Выберите проект: [" + from + ".." + to + "] или 0 для выхода");
        }
    }


    private void printProjectDetails(Project project) {
        printProject(project);
        println(project.getHistory());
        println(project.getDemoVideo());
        String questionAnswers = project.getQuestionAnswers();
        if (questionAnswers != null) {
            println(questionAnswers);
        }
        println("------------------------------------------");
    }

    private Project chooseProject(Project project) {
        println("Вы выбрали проект: " + project.getName());
        println("------------------------------------------");
        return project;
    }

    private void printProjects(Project[] found) {

        for (int i = 0; i < found.length; i++) {
            Project project = found[i];
            io.print((i + 1) + " - ");
            printProject(project);
        }
    }

    private void printProject(Project project) {
        println(project.getName());
        println(project.getDescription());
        println("Нужно собрать " + project.getAmount() + " грн за " + project.getDays() + " дней");
        println("Уже собрали: " + project.getExist() + " грн");
        println("------------------------------------------");
        System.out.println();
    }

    private void askCategory() {
        println("Выберите категорию:");
        println(Arrays.toString(categories.getCategories()));
    }

    private Category chooseCategory(int menu) {
        if (menu <= 0 || menu > categories.size()) {
            println("Неверный индекс меню " + menu);
            return null;
        }

        Category category = categories.get(menu - 1);
        println("Вы выбрали категорию: " + category.getName());
        println("------------------------------------------");
        return category;
    }
}
