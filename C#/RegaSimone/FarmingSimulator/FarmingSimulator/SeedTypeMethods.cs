using System;
using System.Collections.Generic;
using System.Text;

namespace FarmingSimulator
{
    static class SeedTypeMethods
    {
        public static double GetPrice(SeedType s)
        {
            switch (s)
            {
                case SeedType.APPLE_SEED:
                    return 300;
                case SeedType.CARROT_SEED:
                    return 30;
                case SeedType.WHEAT_SEED:
                    return 10;
                case SeedType.POTATO_SEED:
                    return 50;
                case SeedType.TOMATO_SEED:
                    return 100;
                case SeedType.ORANGE_SEED:
                    return 500;
                case SeedType.CHERRY_SEED:
                    return 1000;
                default:
                    return 0;
            }
        }

        public static String GetName(SeedType s)
        {
            switch (s)
            {
                case SeedType.APPLE_SEED:
                    return "Apple Seed";
                case SeedType.CARROT_SEED:
                    return "Carrot Seed";
                case SeedType.WHEAT_SEED:
                    return "Wheat Seed";
                case SeedType.POTATO_SEED:
                    return "Potato Seed";
                case SeedType.TOMATO_SEED:
                    return "Tomato Seed";
                case SeedType.ORANGE_SEED:
                    return "Orange Seed";
                case SeedType.CHERRY_SEED:
                    return "Cherry Seed";
                default:
                    return "A seed";
            }
        }

        public static FoodType GetFoodType(SeedType s)
        {
            switch (s)
            {
                case SeedType.APPLE_SEED:
                    return FoodType.APPLE;
                case SeedType.CARROT_SEED:
                    return FoodType.CARROT;
                case SeedType.WHEAT_SEED:
                    return FoodType.WHEAT;
                case SeedType.POTATO_SEED:
                    return FoodType.POTATO;
                case SeedType.TOMATO_SEED:
                    return FoodType.TOMATO;
                case SeedType.ORANGE_SEED:
                    return FoodType.ORANGE;
                case SeedType.CHERRY_SEED:
                    return FoodType.CHERRY;
                default:
                    return 0;
            }
        }

        public static long GetGrowTime(SeedType s)
        {
            switch (s)
            {
                case SeedType.APPLE_SEED:
                    return ItemConstants.MEDIUM_GROW_TIME;
                case SeedType.CARROT_SEED:
                    return ItemConstants.MEDIUM_GROW_TIME;
                case SeedType.WHEAT_SEED:
                    return ItemConstants.SHORT_GROW_TIME;
                case SeedType.POTATO_SEED:
                    return ItemConstants.MEDIUM_GROW_TIME;
                case SeedType.TOMATO_SEED:
                    return ItemConstants.LONG_GROW_TIME;
                case SeedType.ORANGE_SEED:
                    return ItemConstants.MEDIUM_GROW_TIME;
                case SeedType.CHERRY_SEED:
                    return ItemConstants.LONG_GROW_TIME;
                default:
                    return 0;
            }
        }

        public static SeedType GetSeedType(String name)
        {
            switch (name)
            {
                case "Apple Seed":
                    return SeedType.APPLE_SEED;
                case "Carrot Seed":
                    return SeedType.CARROT_SEED;
                case "Wheat Seed":
                    return SeedType.WHEAT_SEED;
                case "Potato Seed":
                    return SeedType.POTATO_SEED;
                case "Tomato Seed":
                    return SeedType.TOMATO_SEED;
                case "Orange Seed":
                    return SeedType.ORANGE_SEED;
                case "Cherry Seed":
                    return SeedType.CHERRY_SEED;
                default:
                    return 0;
            }
        }
        public static List<SeedType> GetValues()
        {
            return new List<SeedType> { SeedType.WHEAT_SEED, SeedType.CARROT_SEED, SeedType.POTATO_SEED, SeedType.TOMATO_SEED,
                SeedType.APPLE_SEED, SeedType.ORANGE_SEED, SeedType.CHERRY_SEED };
        }
    }
}
