using System;
using System.Collections.Generic;
using System.Text;

namespace FarmingSimulator
{
    interface Seed
    {
        void Grow();
        FoodType Harvest();
        SeedState GetSeedState();
        double GetGrowTime();
        FoodType GetFoodType();
        SeedType GetSeedType();

    }
}
