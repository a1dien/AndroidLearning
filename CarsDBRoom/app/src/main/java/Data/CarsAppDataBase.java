package Data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import Model.Car;

@Database(entities = {Car.class}, version = 1)
public abstract class CarsAppDataBase extends RoomDatabase{
    public abstract CarDAO getCarDAO();

}
