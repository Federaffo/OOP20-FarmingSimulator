using System;
using System.Collections.Generic;
using System.Text;

namespace FarmingSimulator
{
    class ShopImpl : Shop
    {
        private HashSet<SeedType> seedItemList = new HashSet<SeedType>();
        private HashSet<FoodType> foodItemList = new HashSet<FoodType>();

        public ShopImpl()
        {
            /* carico la set di Food(enum) */
            foreach (FoodType f in FoodTypeMethods.GetValues())
            {
                foodItemList.Add(f);
            }
            /* carico la set di SeedType(enum) */
            foreach (SeedType st in SeedTypeMethods.GetValues())
            {
                seedItemList.Add(st);

            }
        }
        public Seed buy(SeedType st)
        {
            throw new NotImplementedException();
        }

        public HashSet<FoodType> GetFoodItemList()
        {
            return this.foodItemList;
        }

        public HashSet<SeedType> GetSeedItemList()
        {
            return this.seedItemList;
        }

        public double sell(FoodType f)
        {
            return FoodTypeMethods.GetPrice(f);
        }

        public double sellAll(Dictionary<FoodType, int> foodToSell)
        {
            double somma = 0;
            foreach(FoodType f in foodToSell.Keys)
            {
                for(int i=0; i< foodToSell[f]; i++)
                {
                    somma += FoodTypeMethods.GetPrice(f);
                }
            }
            return somma;
        }
    }
}
