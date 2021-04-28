using System;
using System.Collections.Generic;
using System.Text;

namespace FarmingSimulator
{
    static class FoodTypeMethods
    {
        public static List<FoodType> GetValues()
        {
            return new List<FoodType> { FoodType.PORK_MEAT, FoodType.EGG, FoodType.MILK,
                FoodType.WHEAT, FoodType.CARROT, FoodType.POTATO, FoodType.TOMATO,
                FoodType.APPLE, FoodType.ORANGE, FoodType.CHERRY };
        }

        public static double GetPrice(FoodType f)
        {
            switch (f)
            {
                case FoodType.PORK_MEAT:
                    return 20;
                case FoodType.EGG:
                    return 5;
                case FoodType.MILK:
                    return 8;
                case FoodType.WHEAT:
                    return 4;
                case FoodType.CARROT:
                    return 13;
                case FoodType.POTATO:
                    return 22;
                case FoodType.TOMATO:
                    return 40;
                case FoodType.APPLE:
                    return 100;
                case FoodType.ORANGE:
                    return 200;
                case FoodType.CHERRY:
                    return 500;
                default:
                    return 0;
            }
        }
    }
}
