--
-- PostgreSQL database dump
--

-- Dumped from database version 15.1
-- Dumped by pg_dump version 15.1

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: ingredients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredients (
    id_ingredient integer NOT NULL,
    name character varying(100) NOT NULL,
    id_ingredient_type integer NOT NULL
);


ALTER TABLE public.ingredients OWNER TO postgres;

--
-- Name: ingredients_id_ingredient_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ingredients_id_ingredient_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ingredients_id_ingredient_seq OWNER TO postgres;

--
-- Name: ingredients_id_ingredient_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ingredients_id_ingredient_seq OWNED BY public.ingredients.id_ingredient;


--
-- Name: ingredients_types; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.ingredients_types (
    id_ingredient_type integer NOT NULL,
    type_name character varying(100) NOT NULL
);


ALTER TABLE public.ingredients_types OWNER TO postgres;

--
-- Name: ingredients_types_id_ingredient_type_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.ingredients_types_id_ingredient_type_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.ingredients_types_id_ingredient_type_seq OWNER TO postgres;

--
-- Name: ingredients_types_id_ingredient_type_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.ingredients_types_id_ingredient_type_seq OWNED BY public.ingredients_types.id_ingredient_type;


--
-- Name: recipes; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recipes (
    id_recipe integer NOT NULL,
    recipe_name character varying(100) NOT NULL,
    description text NOT NULL
);


ALTER TABLE public.recipes OWNER TO postgres;

--
-- Name: recipes_id_recipe_seq; Type: SEQUENCE; Schema: public; Owner: postgres
--

CREATE SEQUENCE public.recipes_id_recipe_seq
    AS integer
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


ALTER TABLE public.recipes_id_recipe_seq OWNER TO postgres;

--
-- Name: recipes_id_recipe_seq; Type: SEQUENCE OWNED BY; Schema: public; Owner: postgres
--

ALTER SEQUENCE public.recipes_id_recipe_seq OWNED BY public.recipes.id_recipe;


--
-- Name: recipes_ingredients; Type: TABLE; Schema: public; Owner: postgres
--

CREATE TABLE public.recipes_ingredients (
    id_recipe integer NOT NULL,
    id_ingredient integer NOT NULL
);


ALTER TABLE public.recipes_ingredients OWNER TO postgres;

--
-- Name: ingredients id_ingredient; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients ALTER COLUMN id_ingredient SET DEFAULT nextval('public.ingredients_id_ingredient_seq'::regclass);


--
-- Name: ingredients_types id_ingredient_type; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients_types ALTER COLUMN id_ingredient_type SET DEFAULT nextval('public.ingredients_types_id_ingredient_type_seq'::regclass);


--
-- Name: recipes id_recipe; Type: DEFAULT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes ALTER COLUMN id_recipe SET DEFAULT nextval('public.recipes_id_recipe_seq'::regclass);


--
-- Data for Name: ingredients; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ingredients VALUES (1, 'Egg', 5);
INSERT INTO public.ingredients VALUES (2, 'Milk', 5);
INSERT INTO public.ingredients VALUES (3, 'Butter', 5);
INSERT INTO public.ingredients VALUES (4, 'Gouda', 5);
INSERT INTO public.ingredients VALUES (5, 'Mozzarella', 5);
INSERT INTO public.ingredients VALUES (6, 'Cheddar', 5);
INSERT INTO public.ingredients VALUES (7, 'Feta', 5);
INSERT INTO public.ingredients VALUES (8, 'Yoghurt', 5);
INSERT INTO public.ingredients VALUES (9, 'Margarine', 5);
INSERT INTO public.ingredients VALUES (10, 'Cottage Cheese', 5);
INSERT INTO public.ingredients VALUES (11, 'Cream', 5);
INSERT INTO public.ingredients VALUES (12, 'Kefir', 5);
INSERT INTO public.ingredients VALUES (13, 'Onion', 2);
INSERT INTO public.ingredients VALUES (14, 'Garlic', 2);
INSERT INTO public.ingredients VALUES (15, 'Carrot', 2);
INSERT INTO public.ingredients VALUES (16, 'Pepper', 2);
INSERT INTO public.ingredients VALUES (17, 'Potato', 2);
INSERT INTO public.ingredients VALUES (18, 'Tomato', 2);
INSERT INTO public.ingredients VALUES (19, 'Mushroom', 2);
INSERT INTO public.ingredients VALUES (20, 'Celery', 2);
INSERT INTO public.ingredients VALUES (21, 'Leek', 2);
INSERT INTO public.ingredients VALUES (22, 'Zucchini', 2);
INSERT INTO public.ingredients VALUES (23, 'Cucumber', 2);
INSERT INTO public.ingredients VALUES (24, 'Corn', 2);
INSERT INTO public.ingredients VALUES (25, 'Cabbage', 2);
INSERT INTO public.ingredients VALUES (26, 'Beetroot', 2);
INSERT INTO public.ingredients VALUES (27, 'Lettuce', 2);
INSERT INTO public.ingredients VALUES (28, 'Spinach', 2);
INSERT INTO public.ingredients VALUES (29, 'Broccoli', 2);
INSERT INTO public.ingredients VALUES (30, 'Apple', 3);
INSERT INTO public.ingredients VALUES (31, 'Lemon', 3);
INSERT INTO public.ingredients VALUES (32, 'Strawberry', 3);
INSERT INTO public.ingredients VALUES (33, 'Orange', 3);
INSERT INTO public.ingredients VALUES (34, 'Banana', 3);
INSERT INTO public.ingredients VALUES (35, 'Raspberry', 3);
INSERT INTO public.ingredients VALUES (36, 'Plum', 3);
INSERT INTO public.ingredients VALUES (37, 'Pear', 3);
INSERT INTO public.ingredients VALUES (38, 'Pineapple', 3);
INSERT INTO public.ingredients VALUES (39, 'Peach', 3);
INSERT INTO public.ingredients VALUES (40, 'Lime', 3);
INSERT INTO public.ingredients VALUES (41, 'Berry', 3);
INSERT INTO public.ingredients VALUES (42, 'Cherry', 3);
INSERT INTO public.ingredients VALUES (43, 'Ham', 4);
INSERT INTO public.ingredients VALUES (44, 'Bacon', 4);
INSERT INTO public.ingredients VALUES (45, 'Sausage', 4);
INSERT INTO public.ingredients VALUES (46, 'White Sausage', 4);
INSERT INTO public.ingredients VALUES (47, 'Chicken Breast', 4);
INSERT INTO public.ingredients VALUES (48, 'Minced Meat', 4);
INSERT INTO public.ingredients VALUES (49, 'Pork', 4);
INSERT INTO public.ingredients VALUES (50, 'Whole Chicken', 4);
INSERT INTO public.ingredients VALUES (51, 'Chicken Thigh', 4);
INSERT INTO public.ingredients VALUES (52, 'Ribs', 4);
INSERT INTO public.ingredients VALUES (53, 'Chicken Wings', 4);
INSERT INTO public.ingredients VALUES (54, 'Beef', 4);
INSERT INTO public.ingredients VALUES (55, 'Turkey Breast', 4);
INSERT INTO public.ingredients VALUES (56, 'Flour', 1);
INSERT INTO public.ingredients VALUES (57, 'Pasta', 1);
INSERT INTO public.ingredients VALUES (58, 'Sugar', 1);
INSERT INTO public.ingredients VALUES (59, 'Rice', 1);
INSERT INTO public.ingredients VALUES (60, 'Bread', 1);
INSERT INTO public.ingredients VALUES (61, 'Oil', 1);
INSERT INTO public.ingredients VALUES (62, 'Corn Flakes', 1);
INSERT INTO public.ingredients VALUES (63, 'Tortilla', 1);
INSERT INTO public.ingredients VALUES (64, 'Olive Oil', 1);
INSERT INTO public.ingredients VALUES (65, 'Potato Flour', 1);
INSERT INTO public.ingredients VALUES (66, 'Cake Flour', 1);
INSERT INTO public.ingredients VALUES (67, 'Semolina', 1);
INSERT INTO public.ingredients VALUES (68, 'Buckwheat Groats', 1);
INSERT INTO public.ingredients VALUES (69, 'Millet', 1);
INSERT INTO public.ingredients VALUES (70, 'Beans', 2);
INSERT INTO public.ingredients VALUES (71, 'Chickpea', 2);
INSERT INTO public.ingredients VALUES (72, 'Peas', 2);
INSERT INTO public.ingredients VALUES (73, 'Lentils', 2);
INSERT INTO public.ingredients VALUES (74, 'Sweet Chilli Sauce', 7);
INSERT INTO public.ingredients VALUES (75, 'Ketchup', 7);
INSERT INTO public.ingredients VALUES (76, 'Mayonnaise', 7);
INSERT INTO public.ingredients VALUES (77, 'Mustard', 7);
INSERT INTO public.ingredients VALUES (78, 'Soy Sauce', 7);
INSERT INTO public.ingredients VALUES (79, 'Vinegar', 7);
INSERT INTO public.ingredients VALUES (80, 'Pesto', 7);
INSERT INTO public.ingredients VALUES (81, 'Oregano', 6);
INSERT INTO public.ingredients VALUES (82, 'Cinnamon', 6);
INSERT INTO public.ingredients VALUES (83, 'Rosemary', 6);
INSERT INTO public.ingredients VALUES (84, 'Garlic Powder', 6);
INSERT INTO public.ingredients VALUES (85, 'Thyme', 6);
INSERT INTO public.ingredients VALUES (86, 'Paprika', 6);
INSERT INTO public.ingredients VALUES (87, 'Curry', 6);
INSERT INTO public.ingredients VALUES (88, 'Nutmeg', 6);
INSERT INTO public.ingredients VALUES (89, 'Cumin', 6);
INSERT INTO public.ingredients VALUES (90, 'Cauliflower', 2);
INSERT INTO public.ingredients VALUES (91, 'Red Onion', 2);
INSERT INTO public.ingredients VALUES (92, 'Sweet Onion', 2);
INSERT INTO public.ingredients VALUES (93, 'Dill Weed', 2);
INSERT INTO public.ingredients VALUES (94, 'Baking Powder', 6);


--
-- Data for Name: ingredients_types; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.ingredients_types VALUES (1, 'Basic');
INSERT INTO public.ingredients_types VALUES (5, 'Dairy');
INSERT INTO public.ingredients_types VALUES (3, 'Fruit');
INSERT INTO public.ingredients_types VALUES (4, 'Meat');
INSERT INTO public.ingredients_types VALUES (7, 'Sauces');
INSERT INTO public.ingredients_types VALUES (6, 'Spices');
INSERT INTO public.ingredients_types VALUES (2, 'Vegetables');


--
-- Data for Name: recipes; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.recipes VALUES (2, 'French Toasts', 'Ingredients:
- 2 large eggs
- 1 teaspoon vanilla extract (Optional)
- ¼ teaspoon ground cinnamon (Optional)
- salt to taste
- 6 thick slices bread
- 1 tablespoon unsalted butter, or more as needed

1. Whisk milk, eggs, vanilla, cinnamon, and salt together in a shallow bowl.
2. Lightly butter a griddle and heat over medium-high heat.
3. Dunk bread in the egg mixture, soaking both sides. Transfer to the hot skillet and cook until golden, 3 to 4 minutes per side. Serve hot.');
INSERT INTO public.recipes VALUES (6, 'Marinated Cucumber, Onion, and Tomato Salad', 'Ingredients:
- 1 cup water
- ½ cup distilled white vinegar
- ¼ cup vegetable oil
- ¼ cup sugar
- 1 teaspoon salt, or to taste
- 1 teaspoon freshly ground black pepper, or to taste
- 3 cucumbers, peeled and sliced 1/4-inch thick
- 3 tomatoes, cut into wedges
- 1 onion, sliced and separated into rings

1. Whisk water, vinegar, oil, sugar, salt, and pepper together in a large bowl until smooth; add cucumbers, tomatoes, and onion and stir to coat.
2. Cover bowl with plastic wrap; refrigerate for at least 2 hours.');
INSERT INTO public.recipes VALUES (3, 'Soft-Boiled Egg', 'Ingredients: 
- 1 egg

1. Fill a medium sized saucepan with water and bring to a rolling boil.
2. Make sure your eggs aren''t fridge cold.
3. Set your timer for 4-5 mins for runny/dippy eggs to serve with soldiers, or 6-7 mins for soft-boiled eggs for a salad.');
INSERT INTO public.recipes VALUES (5, 'Dad''s Creamy Cucumber Salad', 'Ingredients: 
- 2 large cucumbers, peeled and thinly sliced
- 1 sweet onion, thinly sliced
- 1 tablespoon sea salt
Dressing:
- 1 ½ cups mayonnaise, or more to taste
- 2 tablespoons vinegar
- 1 tablespoon white sugar
- 1 teaspoon dried dill weed
- 1 teaspoon garlic powder
- 1 teaspoon ground black pepper

1. Mix cucumbers, onion, and salt together in a bowl. Cover with plastic wrap and let sit for 15 to 30 minutes.
2. Turn cucumber mixture into a colander set over a bowl or in a sink. Let drain, stirring occasionally, until most of the liquid and salt has drained, 15 to 30 minutes. Transfer drained cucumber mixture to a large bowl.
3. Whisk mayonnaise, vinegar, sugar, dill, garlic powder, and pepper for dressing together in a bowl until smooth. Pour over cucumber mixture and stir until coated.
4. Cover with plastic wrap and refrigerate for 1 to 2 hours before serving.');
INSERT INTO public.recipes VALUES (1, 'Scrambled Eggs', 'Ingredients:
- 2 eggs
- Pinch each salt and pepper
- 1 tbsp butter

1. Whisk eggs, salt and pepper in small bowl. Melt butter in non-stick skillet over medium heat.
2. Pour in egg mixture and reduce heat to medium-low. As eggs begin to set, gently move spatula across bottom and side of skillet to form large, soft curds.
3. Cook until eggs are thickened and no visible liquid egg remains, but the eggs are not dry.');
INSERT INTO public.recipes VALUES (4, 'Omelette with ham and cheese', 'Ingredients:
- 2 teaspoons butter
- 4 slices deli ham, chopped into small pieces
- 2 eggs
- 2 teaspoons chopped fresh chives, divided
- ¼ teaspoon salt
- freshly ground black pepper to taste
- ¼ cup shredded Cheddar cheese

1. Heat oil in a skillet over medium heat. Add ham and cook until browned and crispy, stirring frequently, about 7 minutes.
2. Beat eggs with 1 teaspoon chives, salt, and pepper in a small bowl.
3. Pour egg mixture into the skillet; let cook for 30 seconds. Lift the edges of the omelet so that uncooked egg runs under cooked edges and comes into contact with the hot skillet. Shake and tilt the skillet to move uncooked egg. Sprinkle Cheddar cheese on top and fold omelette in half. Flip and cook until egg is entirely set and cheese has melted, 30 seconds to 1 minute.');


--
-- Data for Name: recipes_ingredients; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO public.recipes_ingredients VALUES (1, 1);
INSERT INTO public.recipes_ingredients VALUES (1, 3);
INSERT INTO public.recipes_ingredients VALUES (2, 1);
INSERT INTO public.recipes_ingredients VALUES (2, 3);
INSERT INTO public.recipes_ingredients VALUES (2, 60);
INSERT INTO public.recipes_ingredients VALUES (2, 2);
INSERT INTO public.recipes_ingredients VALUES (3, 1);
INSERT INTO public.recipes_ingredients VALUES (4, 1);
INSERT INTO public.recipes_ingredients VALUES (4, 43);
INSERT INTO public.recipes_ingredients VALUES (4, 6);
INSERT INTO public.recipes_ingredients VALUES (4, 3);
INSERT INTO public.recipes_ingredients VALUES (5, 23);
INSERT INTO public.recipes_ingredients VALUES (5, 92);
INSERT INTO public.recipes_ingredients VALUES (5, 76);
INSERT INTO public.recipes_ingredients VALUES (5, 79);
INSERT INTO public.recipes_ingredients VALUES (5, 93);
INSERT INTO public.recipes_ingredients VALUES (5, 84);
INSERT INTO public.recipes_ingredients VALUES (5, 58);
INSERT INTO public.recipes_ingredients VALUES (6, 79);
INSERT INTO public.recipes_ingredients VALUES (6, 61);
INSERT INTO public.recipes_ingredients VALUES (6, 58);
INSERT INTO public.recipes_ingredients VALUES (6, 23);
INSERT INTO public.recipes_ingredients VALUES (6, 18);
INSERT INTO public.recipes_ingredients VALUES (6, 13);


--
-- Name: ingredients_id_ingredient_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ingredients_id_ingredient_seq', 1, true);


--
-- Name: ingredients_types_id_ingredient_type_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.ingredients_types_id_ingredient_type_seq', 1, false);


--
-- Name: recipes_id_recipe_seq; Type: SEQUENCE SET; Schema: public; Owner: postgres
--

SELECT pg_catalog.setval('public.recipes_id_recipe_seq', 3, true);


--
-- Name: ingredients ingredients_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients
    ADD CONSTRAINT ingredients_pk PRIMARY KEY (id_ingredient);


--
-- Name: ingredients_types ingredients_types_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients_types
    ADD CONSTRAINT ingredients_types_pk PRIMARY KEY (id_ingredient_type);


--
-- Name: recipes recipes_pk; Type: CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes
    ADD CONSTRAINT recipes_pk PRIMARY KEY (id_recipe);


--
-- Name: ingredients ingredients_ingredients_types_id_ingredient_type_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.ingredients
    ADD CONSTRAINT ingredients_ingredients_types_id_ingredient_type_fk FOREIGN KEY (id_ingredient_type) REFERENCES public.ingredients_types(id_ingredient_type);


--
-- Name: recipes_ingredients recipes_ingredients_ingredients_id_ingredient_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes_ingredients
    ADD CONSTRAINT recipes_ingredients_ingredients_id_ingredient_fk FOREIGN KEY (id_ingredient) REFERENCES public.ingredients(id_ingredient) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: recipes_ingredients recipes_ingredients_recipes_id_recipe_fk; Type: FK CONSTRAINT; Schema: public; Owner: postgres
--

ALTER TABLE ONLY public.recipes_ingredients
    ADD CONSTRAINT recipes_ingredients_recipes_id_recipe_fk FOREIGN KEY (id_recipe) REFERENCES public.recipes(id_recipe) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

