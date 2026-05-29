package com.countmycals;

import android.os.Bundle;
import android.view.Gravity;
import android.graphics.Typeface;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView title;
    TextView dashboard;

    int dailyCalorieGoal = 2200;
    int caloriesConsumed = 0;
    int caloriesBurned = 0;

    ArrayList<Meal> meals = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ScrollView scrollView = new ScrollView(this);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(40, 40, 40, 40);

        title = new TextView(this);
        title.setText("COUNTMYCALS");
        title.setTextSize(32);
        title.setTypeface(Typeface.DEFAULT_BOLD);
        title.setGravity(Gravity.CENTER);

        dashboard = new TextView(this);
        dashboard.setTextSize(18);
        dashboard.setPadding(0, 30, 0, 30);

        Button breakfastBtn = new Button(this);
        breakfastBtn.setText("Scan Breakfast");

        Button lunchBtn = new Button(this);
        lunchBtn.setText("Scan Lunch");

        Button dinnerBtn = new Button(this);
        dinnerBtn.setText("Scan Dinner");

        Button dashboardBtn = new Button(this);
        dashboardBtn.setText("View Dashboard");

        layout.addView(title);
        layout.addView(breakfastBtn);
        layout.addView(lunchBtn);
        layout.addView(dinnerBtn);
        layout.addView(dashboardBtn);
        layout.addView(dashboard);

        scrollView.addView(layout);

        setContentView(scrollView);

        breakfastBtn.setOnClickListener(v -> addMeal("Oats + Milk", 350));
        lunchBtn.setOnClickListener(v -> addMeal("Rice + Paneer", 700));
        dinnerBtn.setOnClickListener(v -> addMeal("Chicken Wrap", 600));
        dashboardBtn.setOnClickListener(v -> showDashboard());

        updateDashboard("Welcome to CountMyCals!");
    }

    private void addMeal(String mealName, int calories) {
        Meal meal = new Meal(mealName, calories);
        meals.add(meal);
        caloriesConsumed += calories;

        updateDashboard(
                "Meal Added Successfully!\n\n" +
                "Food: " + mealName + "\n" +
                "Calories: " + calories + " kcal"
        );
    }

    private void showDashboard() {

        int extraCalories = caloriesConsumed - dailyCalorieGoal;

        StringBuilder builder = new StringBuilder();

        builder.append("===== DAILY DASHBOARD =====\n\n");
        builder.append("Daily Goal: ").append(dailyCalorieGoal).append(" kcal\n");
        builder.append("Consumed: ").append(caloriesConsumed).append(" kcal\n");
        builder.append("Burned: ").append(caloriesBurned).append(" kcal\n");
        builder.append("Extra Calories: ").append(extraCalories).append(" kcal\n\n");

        builder.append("MEALS TODAY:\n\n");

        for (Meal meal : meals) {
            builder.append(meal.name)
                    .append(" - ")
                    .append(meal.calories)
                    .append(" kcal\n");
        }

        updateDashboard(builder.toString());
    }

    private void updateDashboard(String message) {
        dashboard.setText(message);
    }

    class Meal {
        String name;
        int calories;

        Meal(String name, int calories) {
            this.name = name;
            this.calories = calories;
        }
    }
}
