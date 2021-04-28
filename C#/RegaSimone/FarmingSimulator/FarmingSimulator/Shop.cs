using System;
using System.Collections.Generic;
using System.Text;

namespace FarmingSimulator
{
    interface Shop
    {
        HashSet<SeedType> GetSeedItemList();
        HashSet<FoodType> GetFoodItemList();
        double sell(FoodType f);
        double sellAll(Dictionary<FoodType, int> foodToSell);
        Seed buy(SeedType st);
    }
}
