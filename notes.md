## Main Activity

- Contains bottom navbar (BottomNavigationView), FrameLayout for Fragments which are changed
- Add Button to open FoodAddFragment ()

main activity is "always present", only fragments in framelayout are changed.

## Food

- FoodFragment - RecyclerView for food entities, calculated/target values for current day, linear layout click listener for HistoryFragment
- FoodAdapter - click listener on itemView to open FoodUpdateFragment
- FoodUpdateFragment - as a parameter it get food entity from FoodAdapter

## Account

- 

## Workout

- 

## Meal

- 

<hr/>

<sub>
Maybe create a tree for fragment/activities
</sub>